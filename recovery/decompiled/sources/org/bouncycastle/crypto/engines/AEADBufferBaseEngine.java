package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
abstract class AEADBufferBaseEngine extends AEADBaseEngine {
    protected int AADBufferSize;
    protected int BlockSize;
    protected boolean aadFinished;
    protected byte[] m_aad;
    protected int m_aadPos;
    protected byte[] m_buf;
    protected int m_bufPos;
    protected boolean initialised = false;
    protected State m_state = State.Uninitialized;

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

    AEADBufferBaseEngine() {
    }

    private void finishAAD(State state) {
        int iOrdinal = this.m_state.ordinal();
        if (iOrdinal == 2 || iOrdinal == 6) {
            processFinalAAD();
        }
        this.m_aadPos = 0;
        this.m_state = state;
    }

    protected void bufferReset() {
        Arrays.fill(this.m_buf, (byte) 0);
        Arrays.fill(this.m_aad, (byte) 0);
        this.m_bufPos = 0;
        this.m_aadPos = 0;
        switch (this.m_state.ordinal()) {
            case 1:
            case 5:
                return;
            case 2:
            case 3:
            case 4:
                this.m_state = State.EncFinal;
                return;
            case 6:
            case 7:
            case 8:
                this.m_state = State.DecInit;
                return;
            default:
                throw new IllegalStateException(getAlgorithmName() + " needs to be initialized");
        }
    }

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
    public int doFinal(byte[] bArr, int i) throws InvalidCipherTextException, IllegalStateException {
        int i2;
        boolean zCheckData = checkData();
        int i3 = this.m_bufPos;
        int i4 = this.MAC_SIZE;
        if (zCheckData) {
            i2 = i3 + i4;
        } else {
            if (i3 < i4) {
                throw new InvalidCipherTextException("data too short");
            }
            i2 = this.m_bufPos - this.MAC_SIZE;
            this.m_bufPos = i2;
        }
        if (i > bArr.length - i2) {
            throw new OutputLengthException("output buffer too short");
        }
        processFinalBlock(bArr, i);
        if (zCheckData) {
            System.arraycopy(this.mac, 0, bArr, (i + i2) - this.MAC_SIZE, this.MAC_SIZE);
        } else if (!Arrays.constantTimeAreEqual(this.MAC_SIZE, this.mac, 0, this.m_buf, this.m_bufPos)) {
            throw new InvalidCipherTextException(this.algorithmName + " mac does not match");
        }
        reset(!zCheckData);
        return i2;
    }

    public int getBlockSize() {
        return this.BlockSize;
    }

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

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x000d. Please report as an issue. */
    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getUpdateOutputSize(int i) {
        int i2;
        int iMax = Math.max(0, i) - 1;
        switch (this.m_state.ordinal()) {
            case 3:
            case 4:
                i2 = iMax + this.m_bufPos;
                iMax = Math.max(0, i2);
                break;
            case 7:
            case 8:
                iMax += this.m_bufPos;
            case 5:
            case 6:
                i2 = iMax - this.MAC_SIZE;
                iMax = Math.max(0, i2);
                break;
        }
        return iMax - (iMax % this.BlockSize);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADByte(byte b) {
        checkAAD();
        if (this.m_aadPos == this.AADBufferSize) {
            processBufferAAD(this.m_aad, 0);
            this.m_aadPos = 0;
        }
        byte[] bArr = this.m_aad;
        int i = this.m_aadPos;
        this.m_aadPos = i + 1;
        bArr[i] = b;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0032 A[LOOP:0: B:14:0x002e->B:16:0x0032, LOOP_END] */
    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADBytes(byte[] bArr, int i, int i2) {
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 <= 0) {
            return;
        }
        checkAAD();
        int i3 = this.m_aadPos;
        if (i3 <= 0) {
            while (i2 > this.AADBufferSize) {
                processBufferAAD(bArr, i);
                int i4 = this.AADBufferSize;
                i += i4;
                i2 -= i4;
            }
            System.arraycopy(bArr, i, this.m_aad, this.m_aadPos, i2);
        } else {
            int i5 = this.AADBufferSize - i3;
            if (i2 <= i5) {
                System.arraycopy(bArr, i, this.m_aad, i3, i2);
            } else {
                System.arraycopy(bArr, i, this.m_aad, i3, i5);
                i += i5;
                i2 -= i5;
                processBufferAAD(this.m_aad, 0);
                this.m_aadPos = 0;
                while (i2 > this.AADBufferSize) {
                    processBufferAAD(bArr, i);
                    int i6 = this.AADBufferSize;
                    i += i6;
                    i2 -= i6;
                }
                System.arraycopy(bArr, i, this.m_aad, this.m_aadPos, i2);
            }
        }
        this.m_aadPos += i2;
    }

    protected abstract void processBuffer(byte[] bArr, int i, byte[] bArr2, int i2);

    protected abstract void processBufferAAD(byte[] bArr, int i);

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        int i4;
        int i5;
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (checkData()) {
            int i6 = this.m_bufPos;
            if (i6 > 0) {
                int i7 = this.BlockSize - i6;
                if (i2 <= i7) {
                    System.arraycopy(bArr, i, this.m_buf, i6, i2);
                    this.m_bufPos += i2;
                    return 0;
                }
                System.arraycopy(bArr, i, this.m_buf, i6, i7);
                i += i7;
                i2 -= i7;
                validateAndProcessBuffer(this.m_buf, 0, bArr2, i3);
                i4 = this.BlockSize;
            } else {
                i4 = 0;
            }
            while (i2 > this.BlockSize) {
                validateAndProcessBuffer(bArr, i, bArr2, i3 + i4);
                int i8 = this.BlockSize;
                i += i8;
                i2 -= i8;
                i4 += i8;
            }
            System.arraycopy(bArr, i, this.m_buf, 0, i2);
            this.m_bufPos = i2;
            return i4;
        }
        int i9 = this.BlockSize + this.MAC_SIZE;
        int i10 = this.m_bufPos;
        int i11 = i9 - i10;
        if (i2 <= i11) {
            System.arraycopy(bArr, i, this.m_buf, i10, i2);
            this.m_bufPos += i2;
            return 0;
        }
        if (this.BlockSize >= this.MAC_SIZE) {
            if (this.m_bufPos > this.BlockSize) {
                validateAndProcessBuffer(this.m_buf, 0, bArr2, i3);
                int i12 = this.m_bufPos;
                int i13 = this.BlockSize;
                int i14 = i12 - i13;
                this.m_bufPos = i14;
                byte[] bArr3 = this.m_buf;
                System.arraycopy(bArr3, i13, bArr3, 0, i14);
                i5 = this.BlockSize;
                if (i2 <= i11 + i5) {
                    System.arraycopy(bArr, i, this.m_buf, this.m_bufPos, i2);
                    this.m_bufPos += i2;
                    return i5;
                }
            } else {
                i5 = 0;
            }
            int i15 = this.BlockSize;
            int i16 = this.m_bufPos;
            int i17 = i15 - i16;
            System.arraycopy(bArr, i, this.m_buf, i16, i17);
            i += i17;
            i2 -= i17;
            validateAndProcessBuffer(this.m_buf, 0, bArr2, i3 + i5);
            i4 = i5 + this.BlockSize;
        } else {
            i4 = 0;
            while (true) {
                int i18 = this.m_bufPos;
                int i19 = this.BlockSize;
                if (i18 <= i19 || i18 + i2 <= i19 + this.MAC_SIZE) {
                    break;
                }
                validateAndProcessBuffer(this.m_buf, i4, bArr2, i3 + i4);
                int i20 = this.m_bufPos;
                int i21 = this.BlockSize;
                this.m_bufPos = i20 - i21;
                i4 += i21;
            }
            int i22 = this.m_bufPos;
            if (i22 != 0) {
                byte[] bArr4 = this.m_buf;
                System.arraycopy(bArr4, i4, bArr4, 0, i22);
                if (this.m_bufPos + i2 <= this.BlockSize + this.MAC_SIZE) {
                    System.arraycopy(bArr, i, this.m_buf, this.m_bufPos, i2);
                    this.m_bufPos += i2;
                    return i4;
                }
                int iMax = Math.max(this.BlockSize - this.m_bufPos, 0);
                System.arraycopy(bArr, i, this.m_buf, this.m_bufPos, iMax);
                i += iMax;
                validateAndProcessBuffer(this.m_buf, 0, bArr2, i3 + i4);
                i4 += this.BlockSize;
                i2 -= iMax;
            }
        }
        while (i2 > this.BlockSize + this.MAC_SIZE) {
            validateAndProcessBuffer(bArr, i, bArr2, i3 + i4);
            int i23 = this.BlockSize;
            i += i23;
            i2 -= i23;
            i4 += i23;
        }
        System.arraycopy(bArr, i, this.m_buf, 0, i2);
        this.m_bufPos = i2;
        return i4;
    }

    protected abstract void processFinalAAD();

    protected abstract void processFinalBlock(byte[] bArr, int i);

    protected void validateAndProcessBuffer(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (i2 > bArr2.length - this.BlockSize) {
            throw new OutputLengthException("output buffer too short");
        }
        processBuffer(bArr, i, bArr2, i2);
    }
}
