package org.bouncycastle.crypto.engines;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import kotlin.UByte;
import kotlin.io.encoding.Base64;
import kotlin.jvm.internal.ByteCompanionObject;
import okio.Utf8;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.signers.PSSSigner;

/* JADX INFO: loaded from: classes3.dex */
public class ElephantEngine extends AEADBaseEngine {
    private final int BLOCK_SIZE;
    private byte[] ad;
    private int adOff;
    private int adlen;
    private final byte[] buffer;
    private byte[] current_mask;
    private byte[] expanded_key;
    private boolean initialised;
    private byte[] inputMessage;
    private int inputOff;
    private byte lfsrIV;
    private int messageLen;
    private int nBits;
    private final int nRounds;
    private int nSBox;
    private int nb_its;
    private byte[] next_mask;
    private byte[] npub;
    private final ElephantParameters parameters;
    private byte[] previous_mask;
    private final byte[] previous_outputMessage;
    private final byte[] tag_buffer;
    private State m_state = State.Uninitialized;
    private final ByteArrayOutputStream aadData = new ByteArrayOutputStream();
    private final byte[] sBoxLayer = {-18, -19, -21, -32, -30, -31, -28, -17, -25, -22, -24, -27, -23, -20, -29, -26, -34, -35, -37, -48, -46, -47, -44, -33, -41, -38, -40, -43, -39, -36, -45, -42, -66, -67, -69, -80, -78, -79, -76, -65, -73, -70, -72, -75, -71, PSSSigner.TRAILER_IMPLICIT, -77, -74, 14, 13, 11, 0, 2, 1, 4, 15, 7, 10, 8, 5, 9, 12, 3, 6, 46, 45, 43, 32, 34, 33, 36, 47, 39, 42, 40, 37, 41, 44, 35, 38, 30, 29, 27, 16, 18, 17, 20, 31, 23, 26, 24, 21, 25, 28, 19, 22, 78, 77, 75, 64, 66, 65, 68, 79, 71, 74, 72, 69, 73, 76, 67, 70, -2, -3, -5, -16, -14, -15, -12, -1, -9, -6, -8, -11, -7, -4, -13, -10, 126, 125, 123, 112, 114, 113, 116, ByteCompanionObject.MAX_VALUE, 119, 122, 120, 117, 121, 124, 115, 118, -82, -83, -85, -96, -94, -95, -92, -81, -89, -86, -88, -91, -87, -84, -93, -90, -114, -115, -117, ByteCompanionObject.MIN_VALUE, -126, -127, -124, -113, -121, -118, -120, -123, -119, -116, -125, -122, 94, 93, 91, 80, 82, 81, 84, 95, 87, 90, 88, 85, 89, 92, 83, 86, -98, -99, -101, -112, -110, -111, -108, -97, -105, -102, -104, -107, -103, -100, -109, -106, -50, -51, -53, -64, -62, -63, -60, -49, -57, -54, -56, -59, -55, -52, -61, -58, 62, Base64.padSymbol, 59, 48, 50, 49, 52, Utf8.REPLACEMENT_BYTE, 55, 58, 56, 53, 57, 60, 51, 54, 110, 109, 107, 96, 98, 97, 100, 111, 103, 106, 104, 101, 105, 108, 99, 102};
    private final byte[] KeccakRoundConstants = {1, -126, -118, 0, -117, 1, -127, 9, -118, -120, 9, 10, -117, -117, -119, 3, 2, ByteCompanionObject.MIN_VALUE};
    private final int[] KeccakRhoOffsets = {0, 1, 6, 4, 3, 4, 4, 6, 7, 4, 3, 2, 3, 1, 7, 1, 5, 7, 5, 0, 2, 2, 5, 0, 6};

    public enum ElephantParameters {
        elephant160,
        elephant176,
        elephant200
    }

    private enum State {
        Uninitialized,
        EncInit,
        EncAad,
        EncData,
        EncFinal,
        DecInit,
        DecAad,
        DecData,
        DecFinal
    }

    public ElephantEngine(ElephantParameters elephantParameters) {
        this.KEY_SIZE = 16;
        this.IV_SIZE = 12;
        int iOrdinal = elephantParameters.ordinal();
        if (iOrdinal == 0) {
            this.BLOCK_SIZE = 20;
            this.nBits = 160;
            this.nSBox = 20;
            this.nRounds = 80;
            this.lfsrIV = (byte) 117;
            this.MAC_SIZE = 8;
            this.algorithmName = "Elephant 160 AEAD";
        } else if (iOrdinal == 1) {
            this.BLOCK_SIZE = 22;
            this.nBits = 176;
            this.nSBox = 22;
            this.nRounds = 90;
            this.lfsrIV = (byte) 69;
            this.algorithmName = "Elephant 176 AEAD";
            this.MAC_SIZE = 8;
        } else {
            if (iOrdinal != 2) {
                throw new IllegalArgumentException("Invalid parameter settings for Elephant");
            }
            this.BLOCK_SIZE = 25;
            this.nRounds = 18;
            this.algorithmName = "Elephant 200 AEAD";
            this.MAC_SIZE = 16;
        }
        this.parameters = elephantParameters;
        int i = this.BLOCK_SIZE;
        this.tag_buffer = new byte[i];
        this.previous_mask = new byte[i];
        this.current_mask = new byte[i];
        this.next_mask = new byte[i];
        this.buffer = new byte[i];
        this.previous_outputMessage = new byte[i];
        this.initialised = false;
        reset(false);
    }

    private void KeccakP200Round(byte[] bArr, int i) {
        byte[] bArr2 = new byte[25];
        for (int i2 = 0; i2 < 5; i2++) {
            for (int i3 = 0; i3 < 5; i3++) {
                bArr2[i2] = (byte) (bArr2[i2] ^ bArr[index(i2, i3)]);
            }
        }
        int i4 = 0;
        while (i4 < 5) {
            int i5 = i4 + 1;
            bArr2[i4 + 5] = (byte) (bArr2[(i4 + 4) % 5] ^ ROL8(bArr2[i5 % 5], 1));
            i4 = i5;
        }
        for (int i6 = 0; i6 < 5; i6++) {
            for (int i7 = 0; i7 < 5; i7++) {
                int iIndex = index(i6, i7);
                bArr[iIndex] = (byte) (bArr[iIndex] ^ bArr2[i6 + 5]);
            }
        }
        for (int i8 = 0; i8 < 5; i8++) {
            for (int i9 = 0; i9 < 5; i9++) {
                bArr2[index(i8, i9)] = ROL8(bArr[index(i8, i9)], this.KeccakRhoOffsets[index(i8, i9)]);
            }
        }
        for (int i10 = 0; i10 < 5; i10++) {
            for (int i11 = 0; i11 < 5; i11++) {
                bArr[index(i11, ((i10 * 2) + (i11 * 3)) % 5)] = bArr2[index(i10, i11)];
            }
        }
        for (int i12 = 0; i12 < 5; i12++) {
            int i13 = 0;
            while (i13 < 5) {
                int i14 = i13 + 1;
                bArr2[i13] = (byte) (bArr[index(i13, i12)] ^ ((~bArr[index(i14 % 5, i12)]) & bArr[index((i13 + 2) % 5, i12)]));
                i13 = i14;
            }
            for (int i15 = 0; i15 < 5; i15++) {
                bArr[index(i15, i12)] = bArr2[i15];
            }
        }
        bArr[0] = (byte) (this.KeccakRoundConstants[i] ^ bArr[0]);
    }

    private byte ROL8(byte b, int i) {
        int i2 = b;
        if (i != 0) {
            int i3 = b & 255;
            i2 = (i3 >>> (8 - i)) ^ (i3 << i);
        }
        return (byte) i2;
    }

    private void checkAad() {
        int iOrdinal = this.m_state.ordinal();
        if (iOrdinal == 3) {
            throw new IllegalArgumentException(this.algorithmName + " cannot process AAD when the length of the ciphertext to be processed exceeds the a block size");
        }
        if (iOrdinal == 4) {
            throw new IllegalArgumentException(this.algorithmName + " cannot be reused for encryption");
        }
        if (iOrdinal != 7) {
            return;
        }
        throw new IllegalArgumentException(this.algorithmName + " cannot process AAD when the length of the plaintext to be processed exceeds the a block size");
    }

    private int index(int i, int i2) {
        return i + (i2 * 5);
    }

    private void lfsr_step(byte[] bArr, byte[] bArr2) {
        int iOrdinal = this.parameters.ordinal();
        if (iOrdinal == 0) {
            int i = this.BLOCK_SIZE - 1;
            byte b = bArr2[0];
            bArr[i] = (byte) (((bArr2[13] & UByte.MAX_VALUE) >>> 7) ^ (((bArr2[3] & UByte.MAX_VALUE) << 7) ^ (((b & UByte.MAX_VALUE) >>> 5) | ((b & UByte.MAX_VALUE) << 3))));
        } else if (iOrdinal == 1) {
            bArr[this.BLOCK_SIZE - 1] = (byte) ((rotl(bArr2[0]) ^ ((bArr2[3] & UByte.MAX_VALUE) << 7)) ^ ((bArr2[19] & UByte.MAX_VALUE) >>> 7));
        } else if (iOrdinal == 2) {
            bArr[this.BLOCK_SIZE - 1] = (byte) ((bArr2[13] << 1) ^ (rotl(bArr2[2]) ^ rotl(bArr2[0])));
        }
        System.arraycopy(bArr2, 1, bArr, 0, this.BLOCK_SIZE - 1);
    }

    private void permutation(byte[] bArr) {
        int i;
        int iOrdinal = this.parameters.ordinal();
        if (iOrdinal != 0 && iOrdinal != 1) {
            if (iOrdinal != 2) {
                return;
            }
            for (int i2 = 0; i2 < this.nRounds; i2++) {
                KeccakP200Round(bArr, i2);
            }
            return;
        }
        byte b = this.lfsrIV;
        byte[] bArr2 = new byte[this.nSBox];
        for (int i3 = 0; i3 < this.nRounds; i3++) {
            bArr[0] = (byte) (bArr[0] ^ b);
            int i4 = this.nSBox - 1;
            int i5 = b & 32;
            int i6 = b & 64;
            bArr[i4] = (byte) (bArr[i4] ^ ((byte) (((((((((b & 1) << 7) | ((b & 2) << 5)) | ((b & 4) << 3)) | ((b & 8) << 1)) | ((b & 16) >>> 1)) | (i5 >>> 3)) | (i6 >>> 5)) | ((b & ByteCompanionObject.MIN_VALUE) >>> 7))));
            b = (byte) (((b << 1) | ((i6 >>> 6) ^ (i5 >>> 5))) & 127);
            for (int i7 = 0; i7 < this.nSBox; i7++) {
                bArr[i7] = this.sBoxLayer[bArr[i7] & UByte.MAX_VALUE];
            }
            Arrays.fill(bArr2, (byte) 0);
            int i8 = 0;
            while (true) {
                i = this.nSBox;
                if (i8 < i) {
                    for (int i9 = 0; i9 < 8; i9++) {
                        int i10 = (i8 << 3) + i9;
                        int i11 = this.nBits;
                        if (i10 != i11 - 1) {
                            i10 = ((i10 * i11) >> 2) % (i11 - 1);
                        }
                        int i12 = i10 >>> 3;
                        bArr2[i12] = (byte) (((((bArr[i8] & UByte.MAX_VALUE) >>> i9) & 1) << (i10 & 7)) ^ bArr2[i12]);
                    }
                    i8++;
                }
            }
            System.arraycopy(bArr2, 0, bArr, 0, i);
        }
    }

    private int processAADBytes() {
        byte[] byteArray = this.aadData.toByteArray();
        int iOrdinal = this.m_state.ordinal();
        if (iOrdinal == 1 || iOrdinal == 5) {
            processAADBytes(this.tag_buffer);
        }
        return byteArray.length;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:24:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:25:0x00c2 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:26:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:29:0x00de  */
    /* JADX WARN: Code duplicated, block: B:32:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:33:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:37:? A[RETURN, SYNTHETIC] */
    private void processAADBytes(byte[] bArr) {
        int i;
        State state;
        int i2;
        int i3;
        int i4;
        int iOrdinal;
        State state2;
        checkAad();
        if (this.adOff == -1) {
            this.adlen = this.aadData.size();
            this.ad = this.aadData.toByteArray();
            this.adOff = 0;
        }
        switch (this.m_state.ordinal()) {
            case 1:
                System.arraycopy(this.expanded_key, 0, this.current_mask, 0, this.BLOCK_SIZE);
                System.arraycopy(this.npub, 0, bArr, 0, this.IV_SIZE);
                i = this.IV_SIZE;
                state = State.EncAad;
                this.m_state = state;
                i2 = this.BLOCK_SIZE - i;
                int i5 = this.adlen;
                i3 = this.adOff;
                i4 = i5 - i3;
                if (i2 <= i4) {
                    System.arraycopy(this.ad, i3, bArr, i, i2);
                    this.adOff += i2;
                    return;
                }
                if (i4 > 0) {
                    System.arraycopy(this.ad, i3, bArr, i, i4);
                    this.adOff += i4;
                }
                int i6 = i4 + i;
                Arrays.fill(bArr, i6, i + i2, (byte) 0);
                bArr[i6] = 1;
                iOrdinal = this.m_state.ordinal();
                if (iOrdinal != 2) {
                    state2 = State.EncData;
                } else if (iOrdinal != 6) {
                    return;
                } else {
                    state2 = State.DecData;
                }
                this.m_state = state2;
                return;
            case 2:
            case 6:
                if (this.adOff == this.adlen) {
                    Arrays.fill(bArr, 0, this.BLOCK_SIZE, (byte) 0);
                    bArr[0] = 1;
                    return;
                }
                i = 0;
                i2 = this.BLOCK_SIZE - i;
                int i7 = this.adlen;
                i3 = this.adOff;
                i4 = i7 - i3;
                if (i2 <= i4) {
                    System.arraycopy(this.ad, i3, bArr, i, i2);
                    this.adOff += i2;
                    return;
                }
                if (i4 > 0) {
                    System.arraycopy(this.ad, i3, bArr, i, i4);
                    this.adOff += i4;
                }
                int i8 = i4 + i;
                Arrays.fill(bArr, i8, i + i2, (byte) 0);
                bArr[i8] = 1;
                iOrdinal = this.m_state.ordinal();
                if (iOrdinal != 2) {
                    state2 = State.EncData;
                } else if (iOrdinal != 6) {
                    return;
                } else {
                    state2 = State.DecData;
                }
                this.m_state = state2;
                return;
            case 3:
                throw new IllegalArgumentException(this.algorithmName + " cannot process AAD when the length of the ciphertext to be processed exceeds the a block size");
            case 4:
                throw new IllegalArgumentException(this.algorithmName + " cannot be reused for encryption");
            case 5:
                System.arraycopy(this.expanded_key, 0, this.current_mask, 0, this.BLOCK_SIZE);
                System.arraycopy(this.npub, 0, bArr, 0, this.IV_SIZE);
                i = this.IV_SIZE;
                state = State.DecAad;
                this.m_state = state;
                i2 = this.BLOCK_SIZE - i;
                int i9 = this.adlen;
                i3 = this.adOff;
                i4 = i9 - i3;
                if (i2 <= i4) {
                    System.arraycopy(this.ad, i3, bArr, i, i2);
                    this.adOff += i2;
                    return;
                }
                if (i4 > 0) {
                    System.arraycopy(this.ad, i3, bArr, i, i4);
                    this.adOff += i4;
                }
                int i10 = i4 + i;
                Arrays.fill(bArr, i10, i + i2, (byte) 0);
                bArr[i10] = 1;
                iOrdinal = this.m_state.ordinal();
                if (iOrdinal != 2) {
                    state2 = State.EncData;
                } else if (iOrdinal != 6) {
                    return;
                } else {
                    state2 = State.DecData;
                }
                this.m_state = state2;
                return;
            case 7:
                throw new IllegalArgumentException(this.algorithmName + " cannot process AAD when the length of the plaintext to be processed exceeds the a block size");
            default:
                i = 0;
                i2 = this.BLOCK_SIZE - i;
                int i11 = this.adlen;
                i3 = this.adOff;
                i4 = i11 - i3;
                if (i2 <= i4) {
                    System.arraycopy(this.ad, i3, bArr, i, i2);
                    this.adOff += i2;
                    return;
                }
                if (i4 > 0) {
                    System.arraycopy(this.ad, i3, bArr, i, i4);
                    this.adOff += i4;
                }
                int i12 = i4 + i;
                Arrays.fill(bArr, i12, i + i2, (byte) 0);
                bArr[i12] = 1;
                iOrdinal = this.m_state.ordinal();
                if (iOrdinal != 2) {
                    state2 = State.EncData;
                } else if (iOrdinal != 6) {
                    return;
                } else {
                    state2 = State.DecData;
                }
                this.m_state = state2;
                return;
        }
    }

    private int processBytes(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        byte[] bArr3 = new byte[this.BLOCK_SIZE];
        int i7 = this.nb_its;
        int i8 = 0;
        int i9 = i;
        while (i7 < i2) {
            int i10 = i7 == i3 + (-1) ? i5 - (this.BLOCK_SIZE * i7) : this.BLOCK_SIZE;
            if (!z) {
                int i11 = this.BLOCK_SIZE;
                if (i5 <= i7 * i11 || i10 % i11 != 0) {
                    break;
                }
            }
            lfsr_step(this.next_mask, this.current_mask);
            if (i7 < i3) {
                System.arraycopy(this.npub, 0, this.buffer, 0, this.IV_SIZE);
                Arrays.fill(this.buffer, this.IV_SIZE, this.BLOCK_SIZE, (byte) 0);
                xor_block(this.buffer, this.current_mask, 0, this.BLOCK_SIZE);
                xor_block(this.buffer, this.next_mask, 0, this.BLOCK_SIZE);
                permutation(this.buffer);
                xor_block(this.buffer, this.current_mask, 0, this.BLOCK_SIZE);
                xor_block(this.buffer, this.next_mask, 0, this.BLOCK_SIZE);
                xor_block(this.buffer, bArr, i8, i10);
                System.arraycopy(this.buffer, 0, bArr2, i9, i10);
                if (this.forEncryption) {
                    System.arraycopy(this.buffer, 0, bArr3, 0, i10);
                } else {
                    System.arraycopy(bArr, i8, bArr3, 0, i10);
                }
                i9 += i10;
                i8 += i10;
            }
            if (i7 > 0 && i7 <= i4) {
                int i12 = this.BLOCK_SIZE;
                int i13 = (i7 - 1) * i12;
                if (i13 == i5) {
                    Arrays.fill(this.buffer, 0, i12, (byte) 0);
                    this.buffer[0] = 1;
                } else {
                    int i14 = i5 - i13;
                    if (i12 <= i14) {
                        System.arraycopy(this.previous_outputMessage, 0, this.buffer, 0, i12);
                    } else if (i14 > 0) {
                        System.arraycopy(this.previous_outputMessage, 0, this.buffer, 0, i14);
                        Arrays.fill(this.buffer, i14, this.BLOCK_SIZE, (byte) 0);
                        this.buffer[i14] = 1;
                    }
                }
                xor_block(this.buffer, this.previous_mask, 0, this.BLOCK_SIZE);
                xor_block(this.buffer, this.next_mask, 0, this.BLOCK_SIZE);
                permutation(this.buffer);
                xor_block(this.buffer, this.previous_mask, 0, this.BLOCK_SIZE);
                xor_block(this.buffer, this.next_mask, 0, this.BLOCK_SIZE);
                xor_block(this.tag_buffer, this.buffer, 0, this.BLOCK_SIZE);
            }
            i7++;
            if (i7 < i6) {
                processAADBytes(this.buffer);
                xor_block(this.buffer, this.next_mask, 0, this.BLOCK_SIZE);
                permutation(this.buffer);
                xor_block(this.buffer, this.next_mask, 0, this.BLOCK_SIZE);
                xor_block(this.tag_buffer, this.buffer, 0, this.BLOCK_SIZE);
            }
            byte[] bArr4 = this.previous_mask;
            this.previous_mask = this.current_mask;
            this.current_mask = this.next_mask;
            this.next_mask = bArr4;
            System.arraycopy(bArr3, 0, this.previous_outputMessage, 0, this.BLOCK_SIZE);
        }
        this.nb_its = i7;
        return i8;
    }

    private byte rotl(byte b) {
        int i = b & UByte.MAX_VALUE;
        return (byte) ((i >>> 7) | (i << 1));
    }

    private void xor_block(byte[] bArr, byte[] bArr2, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = (byte) (bArr[i3] ^ bArr2[i3 + i]);
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int doFinal(byte[] bArr, int i) throws InvalidCipherTextException, IllegalStateException {
        if (!this.initialised) {
            throw new IllegalArgumentException(this.algorithmName + " needs call init function before doFinal");
        }
        int i2 = this.inputOff;
        if ((this.forEncryption && i2 + i + this.MAC_SIZE > bArr.length) || (!this.forEncryption && (i2 + i) - this.MAC_SIZE > bArr.length)) {
            throw new OutputLengthException("output buffer is too short");
        }
        int i3 = (i2 + this.messageLen) - (this.forEncryption ? 0 : this.MAC_SIZE);
        int i4 = i3 - this.messageLen;
        int iProcessAADBytes = processAADBytes();
        int i5 = this.BLOCK_SIZE;
        int i6 = i3 / i5;
        int i7 = i6 + 1;
        int i8 = i3 % i5 != 0 ? i7 : i6;
        int i9 = (this.IV_SIZE + iProcessAADBytes) / this.BLOCK_SIZE;
        int iProcessBytes = i + processBytes(this.inputMessage, bArr, i, Math.max(i6 + 2, i9), i8, i7, i3, i9 + 1, true);
        this.mac = new byte[this.MAC_SIZE];
        xor_block(this.tag_buffer, this.expanded_key, 0, this.BLOCK_SIZE);
        permutation(this.tag_buffer);
        xor_block(this.tag_buffer, this.expanded_key, 0, this.BLOCK_SIZE);
        if (this.forEncryption) {
            System.arraycopy(this.tag_buffer, 0, this.mac, 0, this.MAC_SIZE);
            System.arraycopy(this.mac, 0, bArr, iProcessBytes, this.mac.length);
            i4 += this.MAC_SIZE;
        } else {
            this.inputOff -= this.MAC_SIZE;
            for (int i10 = 0; i10 < this.MAC_SIZE; i10++) {
                if (this.tag_buffer[i10] != this.inputMessage[this.inputOff + i10]) {
                    throw new IllegalArgumentException("Mac does not match");
                }
            }
        }
        reset(false);
        return i4;
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ String getAlgorithmName() {
        return super.getAlgorithmName();
    }

    public int getBlockSize() {
        return this.BLOCK_SIZE;
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

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getOutputSize(int i) {
        int iOrdinal = this.m_state.ordinal();
        if (iOrdinal == 0) {
            throw new IllegalArgumentException(this.algorithmName + " needs call init function before getUpdateOutputSize");
        }
        if (iOrdinal == 1 || iOrdinal == 2 || iOrdinal == 3) {
            return i + this.inputOff + this.MAC_SIZE;
        }
        if (iOrdinal == 4 || iOrdinal == 8) {
            return 0;
        }
        return Math.max(0, (i + this.inputOff) - this.MAC_SIZE);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getUpdateOutputSize(int i) {
        switch (this.m_state) {
            case Uninitialized:
                throw new IllegalArgumentException(this.algorithmName + " needs call init function before getUpdateOutputSize");
            case EncInit:
            case EncAad:
            case EncData:
                int i2 = this.inputOff + i;
                return i2 - (i2 % this.BLOCK_SIZE);
            case EncFinal:
            case DecFinal:
                return 0;
            case DecInit:
            case DecAad:
            case DecData:
                int iMax = Math.max(0, (this.inputOff + i) - this.MAC_SIZE);
                return iMax - (iMax % this.BLOCK_SIZE);
            default:
                return Math.max(0, (i + this.inputOff) - this.MAC_SIZE);
        }
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void init(boolean z, CipherParameters cipherParameters) {
        super.init(z, cipherParameters);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void init(byte[] bArr, byte[] bArr2) throws IllegalArgumentException {
        this.npub = bArr2;
        byte[] bArr3 = new byte[this.BLOCK_SIZE];
        this.expanded_key = bArr3;
        System.arraycopy(bArr, 0, bArr3, 0, this.KEY_SIZE);
        permutation(this.expanded_key);
        this.initialised = true;
        this.m_state = this.forEncryption ? State.EncInit : State.DecInit;
        this.inputMessage = new byte[(this.BLOCK_SIZE * 2) + (this.forEncryption ? 0 : this.MAC_SIZE)];
        reset(false);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADByte(byte b) {
        this.aadData.write(b);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADBytes(byte[] bArr, int i, int i2) {
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        this.aadData.write(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
        return super.processByte(b, bArr, i);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if ((this.inputOff + i2) - (this.forEncryption ? 0 : this.MAC_SIZE) < this.BLOCK_SIZE) {
            System.arraycopy(bArr, i, this.inputMessage, this.inputOff, i2);
            this.inputOff += i2;
            return 0;
        }
        int i4 = ((this.inputOff + this.messageLen) + i2) - (this.forEncryption ? 0 : this.MAC_SIZE);
        int iProcessAADBytes = processAADBytes();
        int i5 = this.BLOCK_SIZE;
        int i6 = i4 / i5;
        int i7 = i6 + 1;
        int i8 = i4 % i5 != 0 ? i7 : i6;
        int i9 = (this.IV_SIZE + iProcessAADBytes) / this.BLOCK_SIZE;
        int i10 = i9 + 1;
        int iMax = Math.max(i6 + 2, i9);
        int iMax2 = Math.max(i7, 1) * this.BLOCK_SIZE;
        byte[] bArr3 = new byte[iMax2];
        System.arraycopy(this.inputMessage, 0, bArr3, 0, this.inputOff);
        int i11 = this.inputOff;
        System.arraycopy(bArr, i, bArr3, i11, Math.min(i2, iMax2 - i11));
        int iProcessBytes = processBytes(bArr3, bArr2, i3, iMax, i8, i7, i4, i10, false);
        int i12 = this.inputOff;
        int i13 = iProcessBytes - i12;
        if (i13 >= 0) {
            int i14 = (i12 + i2) - iProcessBytes;
            this.inputOff = i14;
            System.arraycopy(bArr, i13 + i, this.inputMessage, 0, i14);
        } else {
            byte[] bArr4 = this.inputMessage;
            int i15 = -i13;
            System.arraycopy(bArr4, i12 + i13, bArr4, 0, i15);
            System.arraycopy(bArr, i, this.inputMessage, i15, i2);
            this.inputOff = i2 - i13;
        }
        this.messageLen += iProcessBytes;
        return iProcessBytes;
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void reset() {
        super.reset();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void reset(boolean z) {
        this.aadData.reset();
        Arrays.fill(this.tag_buffer, (byte) 0);
        Arrays.fill(this.previous_outputMessage, (byte) 0);
        this.inputOff = 0;
        this.nb_its = 0;
        this.adOff = -1;
        this.messageLen = 0;
        super.reset(z);
    }
}
