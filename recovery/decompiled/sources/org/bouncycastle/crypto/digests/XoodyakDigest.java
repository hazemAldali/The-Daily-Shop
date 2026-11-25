package org.bouncycastle.crypto.digests;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

/* JADX INFO: loaded from: classes3.dex */
public class XoodyakDigest implements Digest {
    private int Rabsorb;
    private MODE mode;
    private int phase;
    private final int f_bPrime = 48;
    private final int Rhash = 16;
    private final int PhaseDown = 1;
    private final int PhaseUp = 2;
    private final int MAXROUNDS = 12;
    private final int TAGLEN = 16;
    private final int[] RC = {88, 56, 960, 208, 288, 20, 96, 44, 896, 240, 416, 18};
    private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private byte[] state = new byte[48];

    enum MODE {
        ModeHash,
        ModeKeyed
    }

    public XoodyakDigest() {
        reset();
    }

    private void Up(byte[] bArr, int i, int i2, int i3) {
        XoodyakDigest xoodyakDigest = this;
        if (xoodyakDigest.mode != MODE.ModeHash) {
            byte[] bArr2 = xoodyakDigest.state;
            bArr2[47] = (byte) (bArr2[47] ^ i3);
        }
        int iLittleEndianToInt = Pack.littleEndianToInt(xoodyakDigest.state, 0);
        int iLittleEndianToInt2 = Pack.littleEndianToInt(xoodyakDigest.state, 4);
        int iLittleEndianToInt3 = Pack.littleEndianToInt(xoodyakDigest.state, 8);
        int i4 = 12;
        int iLittleEndianToInt4 = Pack.littleEndianToInt(xoodyakDigest.state, 12);
        int iLittleEndianToInt5 = Pack.littleEndianToInt(xoodyakDigest.state, 16);
        int iLittleEndianToInt6 = Pack.littleEndianToInt(xoodyakDigest.state, 20);
        int iLittleEndianToInt7 = Pack.littleEndianToInt(xoodyakDigest.state, 24);
        int iLittleEndianToInt8 = Pack.littleEndianToInt(xoodyakDigest.state, 28);
        int iLittleEndianToInt9 = Pack.littleEndianToInt(xoodyakDigest.state, 32);
        int iLittleEndianToInt10 = Pack.littleEndianToInt(xoodyakDigest.state, 36);
        int iLittleEndianToInt11 = Pack.littleEndianToInt(xoodyakDigest.state, 40);
        int iLittleEndianToInt12 = Pack.littleEndianToInt(xoodyakDigest.state, 44);
        int i5 = 0;
        while (i5 < i4) {
            int i6 = (iLittleEndianToInt ^ iLittleEndianToInt5) ^ iLittleEndianToInt9;
            int i7 = (iLittleEndianToInt2 ^ iLittleEndianToInt6) ^ iLittleEndianToInt10;
            int i8 = i5;
            int i9 = (iLittleEndianToInt3 ^ iLittleEndianToInt7) ^ iLittleEndianToInt11;
            int i10 = (iLittleEndianToInt4 ^ iLittleEndianToInt8) ^ iLittleEndianToInt12;
            int i11 = iLittleEndianToInt12;
            int iRotateLeft = Integers.rotateLeft(i10, 5) ^ Integers.rotateLeft(i10, 14);
            int i12 = iLittleEndianToInt8;
            int iRotateLeft2 = Integers.rotateLeft(i6, 5) ^ Integers.rotateLeft(i6, 14);
            int iRotateLeft3 = Integers.rotateLeft(i7, 5) ^ Integers.rotateLeft(i7, 14);
            int iRotateLeft4 = Integers.rotateLeft(i9, 14) ^ Integers.rotateLeft(i9, 5);
            int i13 = iLittleEndianToInt ^ iRotateLeft;
            int i14 = iLittleEndianToInt5 ^ iRotateLeft;
            int i15 = iLittleEndianToInt2 ^ iRotateLeft2;
            int i16 = iLittleEndianToInt6 ^ iRotateLeft2;
            int i17 = iRotateLeft2 ^ iLittleEndianToInt10;
            int i18 = iLittleEndianToInt3 ^ iRotateLeft3;
            int i19 = iLittleEndianToInt7 ^ iRotateLeft3;
            int i20 = iRotateLeft3 ^ iLittleEndianToInt11;
            int i21 = iLittleEndianToInt4 ^ iRotateLeft4;
            int i22 = i12 ^ iRotateLeft4;
            int iRotateLeft5 = Integers.rotateLeft(iRotateLeft ^ iLittleEndianToInt9, 11);
            int iRotateLeft6 = Integers.rotateLeft(i17, 11);
            int iRotateLeft7 = Integers.rotateLeft(i20, 11);
            int iRotateLeft8 = Integers.rotateLeft(i11 ^ iRotateLeft4, 11);
            int i23 = i13 ^ this.RC[i8];
            int i24 = ((~i22) & iRotateLeft5) ^ i23;
            int i25 = ((~i14) & iRotateLeft6) ^ i15;
            int i26 = ((~i16) & iRotateLeft7) ^ i18;
            int i27 = ((~i19) & iRotateLeft8) ^ i21;
            int i28 = ((~iRotateLeft5) & i23) ^ i22;
            int i29 = ((~iRotateLeft6) & i15) ^ i14;
            int i30 = ((~iRotateLeft7) & i18) ^ i16;
            int i31 = ((~iRotateLeft8) & i21) ^ i19;
            int i32 = iRotateLeft5 ^ ((~i23) & i22);
            int i33 = ((~i15) & i14) ^ iRotateLeft6;
            int i34 = iRotateLeft7 ^ ((~i18) & i16);
            int i35 = iRotateLeft8 ^ ((~i21) & i19);
            iLittleEndianToInt5 = Integers.rotateLeft(i28, 1);
            iLittleEndianToInt6 = Integers.rotateLeft(i29, 1);
            iLittleEndianToInt7 = Integers.rotateLeft(i30, 1);
            iLittleEndianToInt8 = Integers.rotateLeft(i31, 1);
            iLittleEndianToInt9 = Integers.rotateLeft(i34, 8);
            iLittleEndianToInt10 = Integers.rotateLeft(i35, 8);
            iLittleEndianToInt11 = Integers.rotateLeft(i32, 8);
            iLittleEndianToInt12 = Integers.rotateLeft(i33, 8);
            i5 = i8 + 1;
            iLittleEndianToInt = i24;
            iLittleEndianToInt4 = i27;
            iLittleEndianToInt2 = i25;
            iLittleEndianToInt3 = i26;
            i4 = 12;
            xoodyakDigest = this;
        }
        Pack.intToLittleEndian(iLittleEndianToInt, xoodyakDigest.state, 0);
        Pack.intToLittleEndian(iLittleEndianToInt2, xoodyakDigest.state, 4);
        Pack.intToLittleEndian(iLittleEndianToInt3, xoodyakDigest.state, 8);
        Pack.intToLittleEndian(iLittleEndianToInt4, xoodyakDigest.state, 12);
        Pack.intToLittleEndian(iLittleEndianToInt5, xoodyakDigest.state, 16);
        Pack.intToLittleEndian(iLittleEndianToInt6, xoodyakDigest.state, 20);
        Pack.intToLittleEndian(iLittleEndianToInt7, xoodyakDigest.state, 24);
        Pack.intToLittleEndian(iLittleEndianToInt8, xoodyakDigest.state, 28);
        Pack.intToLittleEndian(iLittleEndianToInt9, xoodyakDigest.state, 32);
        Pack.intToLittleEndian(iLittleEndianToInt10, xoodyakDigest.state, 36);
        Pack.intToLittleEndian(iLittleEndianToInt11, xoodyakDigest.state, 40);
        Pack.intToLittleEndian(iLittleEndianToInt12, xoodyakDigest.state, 44);
        xoodyakDigest.phase = 2;
        if (bArr != null) {
            System.arraycopy(xoodyakDigest.state, 0, bArr, i, i2);
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

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        if (i + 32 > bArr.length) {
            throw new OutputLengthException("output buffer is too short");
        }
        byte[] byteArray = this.buffer.toByteArray();
        int size = this.buffer.size();
        int i2 = 3;
        int i3 = 0;
        while (true) {
            if (this.phase != 2) {
                Up(null, 0, 0, 0);
            }
            int iMin = Math.min(size, this.Rabsorb);
            Down(byteArray, i3, iMin, i2);
            i3 += iMin;
            size -= iMin;
            if (size == 0) {
                Up(bArr, i, 16, 64);
                Down(null, 0, 0, 0);
                Up(bArr, i + 16, 16, 0);
                reset();
                return 32;
            }
            i2 = 0;
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "Xoodyak Hash";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 32;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        Arrays.fill(this.state, (byte) 0);
        this.phase = 2;
        this.mode = MODE.ModeHash;
        this.Rabsorb = 16;
        this.buffer.reset();
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        this.buffer.write(b);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        this.buffer.write(bArr, i, i2);
    }
}
