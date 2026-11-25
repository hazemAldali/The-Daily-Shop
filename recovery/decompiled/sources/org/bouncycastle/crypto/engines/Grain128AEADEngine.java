package org.bouncycastle.crypto.engines;

import java.io.ByteArrayOutputStream;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.util.Pack;

/* JADX INFO: loaded from: classes3.dex */
public class Grain128AEADEngine extends AEADBaseEngine {
    private static final int STATE_SIZE = 4;
    private int[] authAcc;
    private int[] authSr;
    private int[] lfsr;
    private int[] nfsr;
    private byte[] workingIV;
    private byte[] workingKey;
    private boolean initialised = false;
    private boolean aadFinished = false;
    private final ErasableOutputStream aadData = new ErasableOutputStream();

    private static final class ErasableOutputStream extends ByteArrayOutputStream {
        public byte[] getBuf() {
            return this.buf;
        }
    }

    public Grain128AEADEngine() {
        this.algorithmName = "Grain-128AEAD";
        this.KEY_SIZE = 16;
        this.IV_SIZE = 12;
        this.MAC_SIZE = 8;
    }

    private void accumulate() {
        int[] iArr = this.authAcc;
        int i = iArr[0];
        int[] iArr2 = this.authSr;
        iArr[0] = i ^ iArr2[0];
        iArr[1] = iArr[1] ^ iArr2[1];
    }

    private void authShift(int i) {
        int[] iArr = this.authSr;
        int i2 = iArr[0] >>> 1;
        int i3 = iArr[1];
        iArr[0] = i2 | (i3 << 31);
        iArr[1] = (i << 31) | (i3 >>> 1);
    }

    private void doProcessAADBytes(byte[] bArr, int i) {
        int i2;
        byte[] bArr2;
        if (i < 128) {
            bArr2 = new byte[i + 1];
            bArr2[0] = (byte) i;
            i2 = 0;
        } else {
            int iLen_length = len_length(i);
            byte[] bArr3 = new byte[iLen_length + 1 + i];
            bArr3[0] = (byte) (iLen_length | 128);
            int i3 = i;
            int i4 = 0;
            while (i4 < iLen_length) {
                i4++;
                bArr3[i4] = (byte) i3;
                i3 >>>= 8;
            }
            i2 = iLen_length;
            bArr2 = bArr3;
        }
        for (int i5 = 0; i5 < i; i5++) {
            bArr2[i2 + 1 + i5] = bArr[i5];
        }
        for (byte b : bArr2) {
            for (int i6 = 0; i6 < 8; i6++) {
                this.nfsr = shift(this.nfsr, (getOutputNFSR() ^ this.lfsr[0]) & 1);
                this.lfsr = shift(this.lfsr, getOutputLFSR() & 1);
                updateInternalState((b >> i6) & 1);
            }
        }
    }

    private void getKeyStream(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        for (int i4 = 0; i4 < i2; i4++) {
            byte b = bArr[i + i4];
            byte b2 = 0;
            for (int i5 = 0; i5 < 8; i5++) {
                int output = getOutput();
                this.nfsr = shift(this.nfsr, (getOutputNFSR() ^ this.lfsr[0]) & 1);
                this.lfsr = shift(this.lfsr, getOutputLFSR() & 1);
                int i6 = (b >> i5) & 1;
                b2 = (byte) (b2 | ((output ^ i6) << i5));
                updateInternalState(i6);
            }
            bArr2[i3 + i4] = b2;
        }
    }

    private int getOutput() {
        int[] iArr = this.nfsr;
        int i = iArr[0];
        int i2 = i >>> 12;
        int i3 = iArr[1];
        int i4 = iArr[2];
        int i5 = i4 >>> 9;
        int i6 = i4 >>> 25;
        int i7 = i4 >>> 31;
        int[] iArr2 = this.lfsr;
        int i8 = iArr2[0];
        int i9 = iArr2[1];
        int i10 = iArr2[2];
        int i11 = (i8 >>> 20) & (i8 >>> 13);
        return (((i4 ^ (((((((((i11 ^ ((i8 >>> 8) & i2)) ^ (i7 & (i9 >>> 10))) ^ ((i9 >>> 28) & (i10 >>> 15))) ^ ((i2 & i7) & (i10 >>> 30))) ^ (i10 >>> 29)) ^ (i >>> 2)) ^ (i >>> 15)) ^ (i3 >>> 4)) ^ (i3 >>> 13))) ^ i5) ^ i6) & 1;
    }

    private int getOutputLFSR() {
        int[] iArr = this.lfsr;
        int i = iArr[0];
        int i2 = iArr[1] >>> 6;
        int i3 = iArr[2];
        return (iArr[3] ^ ((((i ^ (i >>> 7)) ^ i2) ^ (i3 >>> 6)) ^ (i3 >>> 17))) & 1;
    }

    private int getOutputNFSR() {
        int[] iArr = this.nfsr;
        int i = iArr[0];
        int i2 = i >>> 25;
        int i3 = iArr[1];
        int i4 = iArr[2];
        int i5 = ((i >>> 26) ^ i) ^ (i3 >>> 24);
        return (((((((((((iArr[3] ^ (i5 ^ (i4 >>> 27))) ^ ((i & i4) >>> 3)) ^ ((i >>> 11) & (i >>> 13))) ^ ((i >>> 17) & (i >>> 18))) ^ ((i & i3) >>> 27)) ^ ((i3 >>> 8) & (i3 >>> 16))) ^ ((i3 >>> 29) & (i4 >>> 1))) ^ ((i4 >>> 4) & (i4 >>> 20))) ^ (((i >>> 22) & (i >>> 24)) & i2)) ^ (((i4 >>> 6) & (i4 >>> 14)) & (i4 >>> 18))) ^ ((((i4 >>> 24) & (i4 >>> 28)) & (i4 >>> 29)) & (i4 >>> 31))) & 1;
    }

    private void initGrain() {
        for (int i = 0; i < 320; i++) {
            int output = getOutput();
            this.nfsr = shift(this.nfsr, ((getOutputNFSR() ^ this.lfsr[0]) ^ output) & 1);
            this.lfsr = shift(this.lfsr, (output ^ getOutputLFSR()) & 1);
        }
        for (int i2 = 0; i2 < 8; i2++) {
            for (int i3 = 0; i3 < 8; i3++) {
                int output2 = getOutput();
                this.nfsr = shift(this.nfsr, (((getOutputNFSR() ^ this.lfsr[0]) ^ output2) ^ (this.workingKey[i2] >> i3)) & 1);
                this.lfsr = shift(this.lfsr, ((output2 ^ getOutputLFSR()) ^ (this.workingKey[i2 + 8] >> i3)) & 1);
            }
        }
        initGrain(this.authAcc);
        initGrain(this.authSr);
        this.initialised = true;
    }

    private void initGrain(int[] iArr) {
        for (int i = 0; i < 2; i++) {
            for (int i2 = 0; i2 < 32; i2++) {
                int output = getOutput();
                this.nfsr = shift(this.nfsr, (getOutputNFSR() ^ this.lfsr[0]) & 1);
                this.lfsr = shift(this.lfsr, getOutputLFSR() & 1);
                iArr[i] = (output << i2) | iArr[i];
            }
        }
    }

    private static int len_length(int i) {
        if ((i & 255) == i) {
            return 1;
        }
        if ((65535 & i) == i) {
            return 2;
        }
        return (16777215 & i) == i ? 3 : 4;
    }

    private void setKey(byte[] bArr, byte[] bArr2) {
        bArr2[12] = -1;
        bArr2[13] = -1;
        bArr2[14] = -1;
        bArr2[15] = ByteCompanionObject.MAX_VALUE;
        this.workingKey = bArr;
        this.workingIV = bArr2;
        Pack.littleEndianToInt(bArr, 0, this.nfsr);
        Pack.littleEndianToInt(this.workingIV, 0, this.lfsr);
    }

    private int[] shift(int[] iArr, int i) {
        int i2 = iArr[0] >>> 1;
        int i3 = iArr[1];
        iArr[0] = i2 | (i3 << 31);
        int i4 = i3 >>> 1;
        int i5 = iArr[2];
        iArr[1] = i4 | (i5 << 31);
        int i6 = iArr[3];
        iArr[2] = (i5 >>> 1) | (i6 << 31);
        iArr[3] = (i << 31) | (i6 >>> 1);
        return iArr;
    }

    private void updateInternalState(int i) {
        int i2 = -i;
        int[] iArr = this.authAcc;
        int i3 = iArr[0];
        int[] iArr2 = this.authSr;
        iArr[0] = i3 ^ (iArr2[0] & i2);
        iArr[1] = (i2 & iArr2[1]) ^ iArr[1];
        authShift(getOutput());
        this.nfsr = shift(this.nfsr, (getOutputNFSR() ^ this.lfsr[0]) & 1);
        this.lfsr = shift(this.lfsr, getOutputLFSR() & 1);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int doFinal(byte[] bArr, int i) throws InvalidCipherTextException, IllegalStateException {
        if (!this.aadFinished) {
            doProcessAADBytes(this.aadData.getBuf(), this.aadData.size());
            this.aadFinished = true;
        }
        accumulate();
        this.mac = Pack.intToLittleEndian(this.authAcc);
        System.arraycopy(this.mac, 0, bArr, i, this.mac.length);
        reset(false);
        return this.mac.length;
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ String getAlgorithmName() {
        return super.getAlgorithmName();
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
        return i + 8;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getUpdateOutputSize(int i) {
        return i;
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void init(boolean z, CipherParameters cipherParameters) {
        super.init(z, cipherParameters);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void init(byte[] bArr, byte[] bArr2) throws IllegalArgumentException {
        byte[] bArr3 = new byte[16];
        this.workingIV = bArr3;
        this.workingKey = bArr;
        this.lfsr = new int[4];
        this.nfsr = new int[4];
        this.authAcc = new int[2];
        this.authSr = new int[2];
        System.arraycopy(bArr2, 0, bArr3, 0, this.IV_SIZE);
        reset();
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADByte(byte b) {
        if (this.aadFinished) {
            throw new IllegalStateException("associated data must be added before plaintext/ciphertext");
        }
        this.aadData.write(b);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADBytes(byte[] bArr, int i, int i2) {
        if (this.aadFinished) {
            throw new IllegalStateException("associated data must be added before plaintext/ciphertext");
        }
        this.aadData.write(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
        return super.processByte(b, bArr, i);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        if (!this.initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (!this.aadFinished) {
            doProcessAADBytes(this.aadData.getBuf(), this.aadData.size());
            this.aadFinished = true;
        }
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        getKeyStream(bArr, i, i2, bArr2, i3);
        return i2;
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void reset() {
        super.reset();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void reset(boolean z) {
        this.aadData.reset();
        this.aadFinished = false;
        setKey(this.workingKey, this.workingIV);
        initGrain();
        super.reset(z);
    }
}
