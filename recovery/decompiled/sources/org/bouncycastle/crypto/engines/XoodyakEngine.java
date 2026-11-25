package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

/* JADX INFO: loaded from: classes3.dex */
public class XoodyakEngine extends AEADBufferBaseEngine {
    private byte[] K;
    private byte aadcd;
    private boolean encrypted;
    private byte[] iv;
    private MODE mode;
    private int phase;
    private byte[] state;
    private final int f_bPrime_1 = 47;
    private final int PhaseUp = 2;
    private final int[] RC = {88, 56, 960, 208, 288, 20, 96, 44, 896, 240, 416, 18};

    enum MODE {
        ModeHash,
        ModeKeyed
    }

    public XoodyakEngine() {
        this.algorithmName = "Xoodyak AEAD";
        this.KEY_SIZE = 16;
        this.IV_SIZE = 16;
        this.MAC_SIZE = 16;
        this.BlockSize = 24;
        this.AADBufferSize = 44;
        this.m_aad = new byte[this.AADBufferSize];
    }

    private void AbsorbAny(byte[] bArr, int i, int i2, int i3) {
        while (true) {
            if (this.phase != 2) {
                Up(null, 0, 0);
            }
            int iMin = Math.min(i2, this.AADBufferSize);
            Down(bArr, i, iMin, i3);
            i += iMin;
            i2 -= iMin;
            if (i2 == 0) {
                return;
            } else {
                i3 = 0;
            }
        }
    }

    private void Up(byte[] bArr, int i, int i2) {
        XoodyakEngine xoodyakEngine = this;
        if (xoodyakEngine.mode != MODE.ModeHash) {
            byte[] bArr2 = xoodyakEngine.state;
            bArr2[47] = (byte) (bArr2[47] ^ i2);
        }
        int iLittleEndianToInt = Pack.littleEndianToInt(xoodyakEngine.state, 0);
        int iLittleEndianToInt2 = Pack.littleEndianToInt(xoodyakEngine.state, 4);
        int iLittleEndianToInt3 = Pack.littleEndianToInt(xoodyakEngine.state, 8);
        int i3 = 12;
        int iLittleEndianToInt4 = Pack.littleEndianToInt(xoodyakEngine.state, 12);
        int iLittleEndianToInt5 = Pack.littleEndianToInt(xoodyakEngine.state, 16);
        int iLittleEndianToInt6 = Pack.littleEndianToInt(xoodyakEngine.state, 20);
        int iLittleEndianToInt7 = Pack.littleEndianToInt(xoodyakEngine.state, 24);
        int iLittleEndianToInt8 = Pack.littleEndianToInt(xoodyakEngine.state, 28);
        int iLittleEndianToInt9 = Pack.littleEndianToInt(xoodyakEngine.state, 32);
        int iLittleEndianToInt10 = Pack.littleEndianToInt(xoodyakEngine.state, 36);
        int iLittleEndianToInt11 = Pack.littleEndianToInt(xoodyakEngine.state, 40);
        int iLittleEndianToInt12 = Pack.littleEndianToInt(xoodyakEngine.state, 44);
        int i4 = 0;
        while (i4 < i3) {
            int i5 = (iLittleEndianToInt ^ iLittleEndianToInt5) ^ iLittleEndianToInt9;
            int i6 = (iLittleEndianToInt2 ^ iLittleEndianToInt6) ^ iLittleEndianToInt10;
            int i7 = i4;
            int i8 = (iLittleEndianToInt3 ^ iLittleEndianToInt7) ^ iLittleEndianToInt11;
            int i9 = (iLittleEndianToInt4 ^ iLittleEndianToInt8) ^ iLittleEndianToInt12;
            int i10 = iLittleEndianToInt12;
            int iRotateLeft = Integers.rotateLeft(i9, 5) ^ Integers.rotateLeft(i9, 14);
            int i11 = iLittleEndianToInt8;
            int iRotateLeft2 = Integers.rotateLeft(i5, 5) ^ Integers.rotateLeft(i5, 14);
            int iRotateLeft3 = Integers.rotateLeft(i6, 5) ^ Integers.rotateLeft(i6, 14);
            int iRotateLeft4 = Integers.rotateLeft(i8, 14) ^ Integers.rotateLeft(i8, 5);
            int i12 = iLittleEndianToInt ^ iRotateLeft;
            int i13 = iLittleEndianToInt5 ^ iRotateLeft;
            int i14 = iLittleEndianToInt2 ^ iRotateLeft2;
            int i15 = iLittleEndianToInt6 ^ iRotateLeft2;
            int i16 = iRotateLeft2 ^ iLittleEndianToInt10;
            int i17 = iLittleEndianToInt3 ^ iRotateLeft3;
            int i18 = iLittleEndianToInt7 ^ iRotateLeft3;
            int i19 = iRotateLeft3 ^ iLittleEndianToInt11;
            int i20 = iLittleEndianToInt4 ^ iRotateLeft4;
            int i21 = i11 ^ iRotateLeft4;
            int iRotateLeft5 = Integers.rotateLeft(iRotateLeft ^ iLittleEndianToInt9, 11);
            int iRotateLeft6 = Integers.rotateLeft(i16, 11);
            int iRotateLeft7 = Integers.rotateLeft(i19, 11);
            int iRotateLeft8 = Integers.rotateLeft(i10 ^ iRotateLeft4, 11);
            int i22 = i12 ^ this.RC[i7];
            int i23 = ((~i21) & iRotateLeft5) ^ i22;
            int i24 = ((~i13) & iRotateLeft6) ^ i14;
            int i25 = ((~i15) & iRotateLeft7) ^ i17;
            int i26 = ((~i18) & iRotateLeft8) ^ i20;
            int i27 = ((~iRotateLeft5) & i22) ^ i21;
            int i28 = ((~iRotateLeft6) & i14) ^ i13;
            int i29 = ((~iRotateLeft7) & i17) ^ i15;
            int i30 = ((~iRotateLeft8) & i20) ^ i18;
            int i31 = iRotateLeft5 ^ ((~i22) & i21);
            int i32 = ((~i14) & i13) ^ iRotateLeft6;
            int i33 = iRotateLeft7 ^ ((~i17) & i15);
            int i34 = iRotateLeft8 ^ ((~i20) & i18);
            iLittleEndianToInt5 = Integers.rotateLeft(i27, 1);
            iLittleEndianToInt6 = Integers.rotateLeft(i28, 1);
            iLittleEndianToInt7 = Integers.rotateLeft(i29, 1);
            iLittleEndianToInt8 = Integers.rotateLeft(i30, 1);
            iLittleEndianToInt9 = Integers.rotateLeft(i33, 8);
            iLittleEndianToInt10 = Integers.rotateLeft(i34, 8);
            iLittleEndianToInt11 = Integers.rotateLeft(i31, 8);
            iLittleEndianToInt12 = Integers.rotateLeft(i32, 8);
            i4 = i7 + 1;
            iLittleEndianToInt = i23;
            iLittleEndianToInt4 = i26;
            iLittleEndianToInt2 = i24;
            iLittleEndianToInt3 = i25;
            i3 = 12;
            xoodyakEngine = this;
        }
        Pack.intToLittleEndian(iLittleEndianToInt, xoodyakEngine.state, 0);
        Pack.intToLittleEndian(iLittleEndianToInt2, xoodyakEngine.state, 4);
        Pack.intToLittleEndian(iLittleEndianToInt3, xoodyakEngine.state, 8);
        Pack.intToLittleEndian(iLittleEndianToInt4, xoodyakEngine.state, 12);
        Pack.intToLittleEndian(iLittleEndianToInt5, xoodyakEngine.state, 16);
        Pack.intToLittleEndian(iLittleEndianToInt6, xoodyakEngine.state, 20);
        Pack.intToLittleEndian(iLittleEndianToInt7, xoodyakEngine.state, 24);
        Pack.intToLittleEndian(iLittleEndianToInt8, xoodyakEngine.state, 28);
        Pack.intToLittleEndian(iLittleEndianToInt9, xoodyakEngine.state, 32);
        Pack.intToLittleEndian(iLittleEndianToInt10, xoodyakEngine.state, 36);
        Pack.intToLittleEndian(iLittleEndianToInt11, xoodyakEngine.state, 40);
        Pack.intToLittleEndian(iLittleEndianToInt12, xoodyakEngine.state, 44);
        xoodyakEngine.phase = 2;
        if (bArr != null) {
            System.arraycopy(xoodyakEngine.state, 0, bArr, 0, i);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x000c, code lost:
    
        r1 = 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void encrypt(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4;
        byte[] bArr3 = new byte[this.BlockSize];
        if (this.encrypted) {
            i4 = 0;
        } else {
            i4 = 128;
        }
        while (true) {
            if (i2 == 0 && this.encrypted) {
                return;
            }
            int iMin = Math.min(i2, this.BlockSize);
            if (this.forEncryption) {
                System.arraycopy(bArr, i, bArr3, 0, iMin);
            }
            Up(null, 0, i4);
            int i5 = 0;
            while (i5 < iMin) {
                bArr2[i3 + i5] = (byte) (bArr[i] ^ this.state[i5]);
                i5++;
                i++;
            }
            if (this.forEncryption) {
                Down(bArr3, 0, iMin, 0);
            } else {
                Down(bArr2, i3, iMin, 0);
            }
            i3 += iMin;
            i2 -= iMin;
            this.encrypted = true;
            i4 = 0;
        }
    }

    void Down(byte[] bArr, int i, int i2, int i3) {
        int i4 = 0;
        while (i4 < i2) {
            byte[] bArr2 = this.state;
            bArr2[i4] = (byte) (bArr[i] ^ bArr2[i4]);
            i4++;
            i++;
        }
        byte[] bArr3 = this.state;
        bArr3[i2] = (byte) (bArr3[i2] ^ 1);
        byte b = bArr3[47];
        if (this.mode == MODE.ModeHash) {
            i3 &= 1;
        }
        bArr3[47] = (byte) (b ^ i3);
        this.phase = 1;
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
    public void init(byte[] bArr, byte[] bArr2) throws IllegalArgumentException {
        this.K = bArr;
        this.iv = bArr2;
        this.state = new byte[48];
        this.mac = new byte[this.MAC_SIZE];
        this.m_buf = new byte[this.BlockSize + (this.forEncryption ? 0 : this.MAC_SIZE)];
        this.initialised = true;
        this.m_state = this.forEncryption ? AEADBufferBaseEngine.State.EncInit : AEADBufferBaseEngine.State.DecInit;
        reset();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBufferBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void processAADByte(byte b) {
        super.processAADByte(b);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBufferBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void processAADBytes(byte[] bArr, int i, int i2) {
        super.processAADBytes(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBufferBaseEngine
    protected void processBuffer(byte[] bArr, int i, byte[] bArr2, int i2) {
        processFinalAAD();
        encrypt(bArr, i, this.BlockSize, bArr2, i2);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBufferBaseEngine
    protected void processBufferAAD(byte[] bArr, int i) {
        AbsorbAny(bArr, i, this.AADBufferSize, this.aadcd);
        this.aadcd = (byte) 0;
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
        return super.processByte(b, bArr, i);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBufferBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        return super.processBytes(bArr, i, i2, bArr2, i3);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBufferBaseEngine
    protected void processFinalAAD() {
        if (this.mode != MODE.ModeKeyed) {
            throw new IllegalArgumentException("Xoodyak has not been initialised");
        }
        if (this.aadFinished) {
            return;
        }
        AbsorbAny(this.m_aad, 0, this.m_aadPos, this.aadcd);
        this.aadFinished = true;
        this.m_aadPos = 0;
    }

    @Override // org.bouncycastle.crypto.engines.AEADBufferBaseEngine
    protected void processFinalBlock(byte[] bArr, int i) {
        processFinalAAD();
        if (this.forEncryption) {
            Arrays.fill(this.m_buf, this.m_bufPos, this.BlockSize, (byte) 0);
        }
        encrypt(this.m_buf, 0, this.m_bufPos, bArr, i);
        this.mac = new byte[this.MAC_SIZE];
        Up(this.mac, this.MAC_SIZE, 64);
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
        Arrays.fill(this.state, (byte) 0);
        this.aadFinished = false;
        this.encrypted = false;
        this.phase = 2;
        Arrays.fill(this.m_buf, (byte) 0);
        Arrays.fill(this.m_aad, (byte) 0);
        this.m_bufPos = 0;
        this.m_aadPos = 0;
        this.aadcd = (byte) 3;
        int length = this.K.length;
        int length2 = this.iv.length;
        byte[] bArr = new byte[this.AADBufferSize];
        this.mode = MODE.ModeKeyed;
        System.arraycopy(this.K, 0, bArr, 0, length);
        System.arraycopy(this.iv, 0, bArr, length, length2);
        int i = length + length2;
        bArr[i] = (byte) length2;
        AbsorbAny(bArr, 0, i + 1, 2);
        super.reset(z);
    }
}
