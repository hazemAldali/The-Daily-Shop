package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Longs;

/* JADX INFO: loaded from: classes3.dex */
abstract class AsconBaseEngine extends AEADBaseEngine {
    protected int ASCON_AEAD_RATE;
    protected long ASCON_IV;
    protected long K0;
    protected long K1;
    protected long N0;
    protected long N1;
    protected long dsep;
    protected byte[] m_buf;
    protected int m_bufferSizeDecrypt;
    protected int nr;
    protected long x0;
    protected long x1;
    protected long x2;
    protected long x3;
    protected long x4;
    protected State m_state = State.Uninitialized;
    protected int m_bufPos = 0;

    protected enum State {
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

    AsconBaseEngine() {
    }

    private void finishAAD(State state) {
        int iOrdinal = this.m_state.ordinal();
        if (iOrdinal == 2 || iOrdinal == 6) {
            processFinalAadBlock();
            p(this.nr);
        }
        this.x4 ^= this.dsep;
        this.m_bufPos = 0;
        this.m_state = state;
    }

    private void round(long j) {
        long j2 = this.x0;
        long j3 = this.x1;
        long j4 = this.x2;
        long j5 = this.x3;
        long j6 = this.x4;
        long j7 = ((((j2 ^ j3) ^ j4) ^ j5) ^ j) ^ ((((j2 ^ j4) ^ j6) ^ j) & j3);
        long j8 = ((((j2 ^ j4) ^ j5) ^ j6) ^ j) ^ (((j3 ^ j4) ^ j) & (j3 ^ j5));
        long j9 = (((j3 ^ j4) ^ j6) ^ j) ^ (j5 & j6);
        long j10 = ((j4 ^ (j2 ^ j3)) ^ j) ^ ((~j2) & (j5 ^ j6));
        long j11 = ((j2 ^ j6) & j3) ^ ((j5 ^ j3) ^ j6);
        this.x0 = (Longs.rotateRight(j7, 19) ^ j7) ^ Longs.rotateRight(j7, 28);
        this.x1 = Longs.rotateRight(j8, 61) ^ (Longs.rotateRight(j8, 39) ^ j8);
        this.x2 = ~(Longs.rotateRight(j9, 6) ^ (Longs.rotateRight(j9, 1) ^ j9));
        this.x3 = (Longs.rotateRight(j10, 10) ^ j10) ^ Longs.rotateRight(j10, 17);
        this.x4 = Longs.rotateRight(j11, 41) ^ (Longs.rotateRight(j11, 7) ^ j11);
    }

    protected abstract void ascon_aeadinit();

    protected void checkAAD() {
        State state;
        int iOrdinal = this.m_state.ordinal();
        if (iOrdinal == 1) {
            state = State.EncAad;
        } else {
            if (iOrdinal == 2) {
                return;
            }
            if (iOrdinal == 4) {
                throw new IllegalStateException(getAlgorithmName() + " cannot be reused for encryption");
            }
            if (iOrdinal != 5) {
                if (iOrdinal == 6) {
                    return;
                }
                throw new IllegalStateException(getAlgorithmName() + " needs to be initialized");
            }
            state = State.DecAad;
        }
        this.m_state = state;
    }

    protected boolean checkData() {
        switch (this.m_state.ordinal()) {
            case 1:
            case 2:
                finishAAD(State.EncData);
                return true;
            case 3:
                return true;
            case 4:
                throw new IllegalStateException(getAlgorithmName() + " cannot be reused for encryption");
            case 5:
            case 6:
                finishAAD(State.DecData);
                return false;
            case 7:
                return false;
            default:
                throw new IllegalStateException(getAlgorithmName() + " needs to be initialized");
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int doFinal(byte[] bArr, int i) throws InvalidCipherTextException, IllegalStateException, DataLengthException {
        int i2;
        if (checkData()) {
            i2 = this.m_bufPos + this.MAC_SIZE;
            if (i + i2 > bArr.length) {
                throw new OutputLengthException("output buffer too short");
            }
            processFinalEncrypt(this.m_buf, this.m_bufPos, bArr, i);
            this.mac = new byte[this.MAC_SIZE];
            setBytes(this.x3, this.mac, 0);
            setBytes(this.x4, this.mac, 8);
            System.arraycopy(this.mac, 0, bArr, i + this.m_bufPos, this.MAC_SIZE);
            reset(false);
        } else {
            if (this.m_bufPos < this.MAC_SIZE) {
                throw new InvalidCipherTextException("data too short");
            }
            i2 = this.m_bufPos - this.MAC_SIZE;
            this.m_bufPos = i2;
            if (i + i2 > bArr.length) {
                throw new OutputLengthException("output buffer too short");
            }
            processFinalDecrypt(this.m_buf, i2, bArr, i);
            this.x3 ^= loadBytes(this.m_buf, this.m_bufPos);
            long jLoadBytes = this.x4 ^ loadBytes(this.m_buf, this.m_bufPos + 8);
            this.x4 = jLoadBytes;
            if ((jLoadBytes | this.x3) != 0) {
                throw new InvalidCipherTextException("mac check in " + getAlgorithmName() + " failed");
            }
            reset(true);
        }
        return i2;
    }

    public abstract String getAlgorithmVersion();

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getOutputSize(int i) {
        int iMax = Math.max(0, i);
        switch (this.m_state.ordinal()) {
            case 3:
            case 4:
                return iMax + this.m_bufPos + this.MAC_SIZE;
            case 5:
            case 6:
                return Math.max(0, iMax - this.MAC_SIZE);
            case 7:
            case 8:
                return Math.max(0, (iMax + this.m_bufPos) - this.MAC_SIZE);
            default:
                return iMax + this.MAC_SIZE;
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getUpdateOutputSize(int i) {
        int iMax = Math.max(0, i);
        switch (this.m_state.ordinal()) {
            case 3:
            case 4:
                iMax += this.m_bufPos;
                break;
            case 7:
            case 8:
                iMax += this.m_bufPos;
            case 5:
            case 6:
                iMax = Math.max(0, iMax - this.MAC_SIZE);
                break;
        }
        return iMax - (iMax % this.ASCON_AEAD_RATE);
    }

    protected abstract long loadBytes(byte[] bArr, int i);

    protected void p(int i) {
        if (i == 12) {
            round(240L);
            round(225L);
            round(210L);
            round(195L);
        }
        if (i >= 8) {
            round(180L);
            round(165L);
        }
        round(150L);
        round(135L);
        round(120L);
        round(105L);
        round(90L);
        round(75L);
    }

    protected abstract long pad(int i);

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADByte(byte b) {
        checkAAD();
        byte[] bArr = this.m_buf;
        int i = this.m_bufPos;
        bArr[i] = b;
        int i2 = i + 1;
        this.m_bufPos = i2;
        if (i2 == this.ASCON_AEAD_RATE) {
            processBufferAAD(bArr, 0);
            this.m_bufPos = 0;
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADBytes(byte[] bArr, int i, int i2) {
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 <= 0) {
            return;
        }
        checkAAD();
        int i3 = this.m_bufPos;
        if (i3 > 0) {
            int i4 = this.ASCON_AEAD_RATE - i3;
            if (i2 < i4) {
                System.arraycopy(bArr, i, this.m_buf, i3, i2);
                this.m_bufPos += i2;
                return;
            } else {
                System.arraycopy(bArr, i, this.m_buf, i3, i4);
                i += i4;
                i2 -= i4;
                processBufferAAD(this.m_buf, 0);
            }
        }
        while (i2 >= this.ASCON_AEAD_RATE) {
            processBufferAAD(bArr, i);
            int i5 = this.ASCON_AEAD_RATE;
            i += i5;
            i2 -= i5;
        }
        System.arraycopy(bArr, i, this.m_buf, 0, i2);
        this.m_bufPos = i2;
    }

    protected void processBufferAAD(byte[] bArr, int i) {
        this.x0 ^= loadBytes(bArr, i);
        if (this.ASCON_AEAD_RATE == 16) {
            this.x1 = loadBytes(bArr, i + 8) ^ this.x1;
        }
        p(this.nr);
    }

    protected void processBufferDecrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.ASCON_AEAD_RATE + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        long jLoadBytes = loadBytes(bArr, i);
        setBytes(this.x0 ^ jLoadBytes, bArr2, i2);
        this.x0 = jLoadBytes;
        if (this.ASCON_AEAD_RATE == 16) {
            long jLoadBytes2 = loadBytes(bArr, i + 8);
            setBytes(this.x1 ^ jLoadBytes2, bArr2, i2 + 8);
            this.x1 = jLoadBytes2;
        }
        p(this.nr);
    }

    protected void processBufferEncrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.ASCON_AEAD_RATE + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        long jLoadBytes = this.x0 ^ loadBytes(bArr, i);
        this.x0 = jLoadBytes;
        setBytes(jLoadBytes, bArr2, i2);
        if (this.ASCON_AEAD_RATE == 16) {
            long jLoadBytes2 = loadBytes(bArr, i + 8) ^ this.x1;
            this.x1 = jLoadBytes2;
            setBytes(jLoadBytes2, bArr2, i2 + 8);
        }
        p(this.nr);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        int i4;
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (checkData()) {
            int i5 = this.m_bufPos;
            if (i5 > 0) {
                int i6 = this.ASCON_AEAD_RATE - i5;
                if (i2 < i6) {
                    System.arraycopy(bArr, i, this.m_buf, i5, i2);
                    this.m_bufPos += i2;
                    return 0;
                }
                System.arraycopy(bArr, i, this.m_buf, i5, i6);
                i += i6;
                i2 -= i6;
                processBufferEncrypt(this.m_buf, 0, bArr2, i3);
                i4 = this.ASCON_AEAD_RATE;
            } else {
                i4 = 0;
            }
            while (i2 >= this.ASCON_AEAD_RATE) {
                processBufferEncrypt(bArr, i, bArr2, i3 + i4);
                int i7 = this.ASCON_AEAD_RATE;
                i += i7;
                i2 -= i7;
                i4 += i7;
            }
            System.arraycopy(bArr, i, this.m_buf, 0, i2);
            this.m_bufPos = i2;
            return i4;
        }
        int i8 = this.m_bufferSizeDecrypt;
        int i9 = this.m_bufPos;
        int i10 = i8 - i9;
        if (i2 < i10) {
            System.arraycopy(bArr, i, this.m_buf, i9, i2);
            this.m_bufPos += i2;
            return 0;
        }
        int i11 = 0;
        do {
            int i12 = this.m_bufPos;
            int i13 = this.ASCON_AEAD_RATE;
            if (i12 < i13) {
                int i14 = i13 - i12;
                System.arraycopy(bArr, i, this.m_buf, i12, i14);
                i += i14;
                i2 -= i14;
                processBufferDecrypt(this.m_buf, 0, bArr2, i3 + i11);
                i4 = i11 + this.ASCON_AEAD_RATE;
                while (i2 >= this.m_bufferSizeDecrypt) {
                    processBufferDecrypt(bArr, i, bArr2, i3 + i4);
                    int i15 = this.ASCON_AEAD_RATE;
                    i += i15;
                    i2 -= i15;
                    i4 += i15;
                }
                System.arraycopy(bArr, i, this.m_buf, 0, i2);
                this.m_bufPos = i2;
                return i4;
            }
            processBufferDecrypt(this.m_buf, 0, bArr2, i3 + i11);
            int i16 = this.m_bufPos;
            int i17 = this.ASCON_AEAD_RATE;
            int i18 = i16 - i17;
            this.m_bufPos = i18;
            byte[] bArr3 = this.m_buf;
            System.arraycopy(bArr3, i17, bArr3, 0, i18);
            int i19 = this.ASCON_AEAD_RATE;
            i11 += i19;
            i10 += i19;
        } while (i2 >= i10);
        System.arraycopy(bArr, i, this.m_buf, this.m_bufPos, i2);
        this.m_bufPos += i2;
        return i11;
    }

    protected abstract void processFinalAadBlock();

    protected abstract void processFinalDecrypt(byte[] bArr, int i, byte[] bArr2, int i2);

    protected abstract void processFinalEncrypt(byte[] bArr, int i, byte[] bArr2, int i2);

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void reset(boolean z) {
        Arrays.clear(this.m_buf);
        this.m_bufPos = 0;
        switch (this.m_state.ordinal()) {
            case 1:
            case 5:
                break;
            case 2:
            case 3:
            case 4:
                this.m_state = State.EncFinal;
                return;
            case 6:
            case 7:
            case 8:
                this.m_state = State.DecInit;
                break;
            default:
                throw new IllegalStateException(getAlgorithmName() + " needs to be initialized");
        }
        ascon_aeadinit();
        super.reset(z);
    }

    protected abstract void setBytes(long j, byte[] bArr, int i);
}
