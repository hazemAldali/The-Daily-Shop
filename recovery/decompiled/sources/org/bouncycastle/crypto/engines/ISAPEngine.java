package org.bouncycastle.crypto.engines;

import kotlin.UByte;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* JADX INFO: loaded from: classes3.dex */
public class ISAPEngine extends AEADBufferBaseEngine {
    private ISAP_AEAD ISAPAEAD;
    final int ISAP_STATE_SZ = 40;
    private int ISAP_rH;
    private byte[] k;
    private byte[] npub;

    private abstract class ISAPAEAD_A implements ISAP_AEAD {
        protected long ISAP_IV1_64;
        protected long ISAP_IV2_64;
        protected long ISAP_IV3_64;
        protected long[] k64;
        protected long macx0;
        protected long macx1;
        protected long macx2;
        protected long macx3;
        protected long macx4;
        protected long[] npub64;
        protected long t0;
        protected long t1;
        protected long t2;
        protected long t3;
        protected long t4;
        protected long x0;
        protected long x1;
        protected long x2;
        protected long x3;
        protected long x4;

        public ISAPAEAD_A() {
            ISAPEngine.this.ISAP_rH = 64;
            ISAPEngine.this.BlockSize = (ISAPEngine.this.ISAP_rH + 7) >> 3;
        }

        private long ROTR(long j, long j2) {
            return (j << ((int) (64 - j2))) | (j >>> ((int) j2));
        }

        private int getLongSize(int i) {
            return (i >>> 3) + ((i & 7) != 0 ? 1 : 0);
        }

        public void P12() {
            ROUND(240L);
            ROUND(225L);
            ROUND(210L);
            ROUND(195L);
            ROUND(180L);
            ROUND(165L);
            P6();
        }

        protected void P6() {
            ROUND(150L);
            ROUND(135L);
            ROUND(120L);
            ROUND(105L);
            ROUND(90L);
            ROUND(75L);
        }

        protected abstract void PX1();

        protected abstract void PX2();

        protected void ROUND(long j) {
            long j2 = this.x0;
            long j3 = this.x1;
            long j4 = this.x2;
            long j5 = this.x3;
            long j6 = this.x4;
            long j7 = ((((j2 ^ j3) ^ j4) ^ j5) ^ j) ^ ((((j2 ^ j4) ^ j6) ^ j) & j3);
            this.t0 = j7;
            this.t1 = ((((j2 ^ j4) ^ j5) ^ j6) ^ j) ^ (((j3 ^ j4) ^ j) & (j3 ^ j5));
            this.t2 = (((j3 ^ j4) ^ j6) ^ j) ^ (j5 & j6);
            this.t3 = ((j4 ^ (j2 ^ j3)) ^ j) ^ ((~j2) & (j5 ^ j6));
            this.t4 = ((j2 ^ j6) & j3) ^ ((j3 ^ j5) ^ j6);
            this.x0 = (ROTR(j7, 19L) ^ j7) ^ ROTR(this.t0, 28L);
            long j8 = this.t1;
            this.x1 = (j8 ^ ROTR(j8, 39L)) ^ ROTR(this.t1, 61L);
            long j9 = this.t2;
            this.x2 = ~((j9 ^ ROTR(j9, 1L)) ^ ROTR(this.t2, 6L));
            long j10 = this.t3;
            this.x3 = (j10 ^ ROTR(j10, 10L)) ^ ROTR(this.t3, 17L);
            long j11 = this.t4;
            this.x4 = (j11 ^ ROTR(j11, 7L)) ^ ROTR(this.t4, 41L);
        }

        protected long U64BIG(long j) {
            return (ROTR(j, 56L) & 1095216660735L) | (ROTR(j, 8L) & (-72057589759737856L)) | (ROTR(j, 24L) & 71776119077928960L) | (ROTR(j, 40L) & 280375465148160L);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void absorbFinalAADBlock() {
            if (ISAPEngine.this.m_aadPos == ISAPEngine.this.AADBufferSize) {
                absorbMacBlock(ISAPEngine.this.m_aad, 0);
                ISAPEngine.this.m_aadPos = 0;
            } else {
                for (int i = 0; i < ISAPEngine.this.m_aadPos; i++) {
                    this.x0 ^= (((long) ISAPEngine.this.m_aad[i]) & 255) << ((7 - i) << 3);
                }
            }
            this.x0 ^= 128 << ((7 - ISAPEngine.this.m_aadPos) << 3);
            P12();
            this.x4 ^= 1;
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void absorbMacBlock(byte[] bArr, int i) {
            this.x0 = Pack.bigEndianToLong(bArr, i) ^ this.x0;
            P12();
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void init() {
            this.npub64 = new long[getLongSize(ISAPEngine.this.npub.length)];
            this.k64 = new long[getLongSize(ISAPEngine.this.k.length)];
            Pack.bigEndianToLong(ISAPEngine.this.npub, 0, this.npub64);
            Pack.bigEndianToLong(ISAPEngine.this.k, 0, this.k64);
        }

        public void isap_rk(long j, byte[] bArr, int i) {
            long[] jArr = this.k64;
            this.x0 = jArr[0];
            this.x1 = jArr[1];
            this.x2 = j;
            this.x4 = 0L;
            this.x3 = 0L;
            P12();
            for (int i2 = 0; i2 < (i << 3) - 1; i2++) {
                this.x0 ^= (((long) (((bArr[i2 >>> 3] >>> (7 - (i2 & 7))) & 1) << 7)) & 255) << 56;
                PX2();
            }
            this.x0 ^= (((long) bArr[i - 1]) & 1) << 63;
            P12();
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void processEncBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
            long jLittleEndianToLong = Pack.littleEndianToLong(bArr, i) ^ U64BIG(this.x0);
            PX1();
            Pack.longToLittleEndian(jLittleEndianToLong, bArr2, i2);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void processEncFinalBlock(byte[] bArr, int i) {
            if (ISAPEngine.this.m_bufPos == ISAPEngine.this.BlockSize) {
                processEncBlock(ISAPEngine.this.m_buf, 0, bArr, i);
                return;
            }
            byte[] bArrLongToLittleEndian = Pack.longToLittleEndian(this.x0);
            int i2 = ISAPEngine.this.m_bufPos;
            while (i2 > 0) {
                int i3 = (i + i2) - 1;
                byte b = bArrLongToLittleEndian[ISAPEngine.this.BlockSize - i2];
                i2--;
                bArr[i3] = (byte) (b ^ ISAPEngine.this.m_buf[i2]);
            }
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void processMACFinal(byte[] bArr, int i, int i2, byte[] bArr2) {
            if (i2 == ISAPEngine.this.BlockSize) {
                absorbMacBlock(bArr, i);
                i2 = 0;
            } else {
                int i3 = 0;
                while (i3 < i2) {
                    this.x0 ^= (((long) bArr[i]) & 255) << ((7 - i3) << 3);
                    i3++;
                    i++;
                }
            }
            this.x0 ^= 128 << ((7 - i2) << 3);
            P12();
            Pack.longToBigEndian(this.x0, bArr2, 0);
            Pack.longToBigEndian(this.x1, bArr2, 8);
            long j = this.x2;
            long j2 = this.x3;
            long j3 = this.x4;
            isap_rk(this.ISAP_IV2_64, bArr2, ISAPEngine.this.KEY_SIZE);
            this.x2 = j;
            this.x3 = j2;
            this.x4 = j3;
            P12();
            Pack.longToBigEndian(this.x0, bArr2, 0);
            Pack.longToBigEndian(this.x1, bArr2, 8);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void reset() {
            isap_rk(this.ISAP_IV3_64, ISAPEngine.this.npub, ISAPEngine.this.IV_SIZE);
            long[] jArr = this.npub64;
            this.x3 = jArr[0];
            this.x4 = jArr[1];
            PX1();
            swapInternalState();
            long[] jArr2 = this.npub64;
            this.x0 = jArr2[0];
            this.x1 = jArr2[1];
            this.x2 = this.ISAP_IV1_64;
            this.x4 = 0L;
            this.x3 = 0L;
            P12();
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void swapInternalState() {
            long j = this.x0;
            this.t0 = j;
            long j2 = this.x1;
            this.t1 = j2;
            long j3 = this.x2;
            this.t2 = j3;
            long j4 = this.x3;
            this.t3 = j4;
            long j5 = this.x4;
            this.t4 = j5;
            this.x0 = this.macx0;
            this.x1 = this.macx1;
            this.x2 = this.macx2;
            this.x3 = this.macx3;
            this.x4 = this.macx4;
            this.macx0 = j;
            this.macx1 = j2;
            this.macx2 = j3;
            this.macx3 = j4;
            this.macx4 = j5;
        }
    }

    private class ISAPAEAD_A_128 extends ISAPAEAD_A {
        public ISAPAEAD_A_128() {
            super();
            this.ISAP_IV1_64 = 108156764298152972L;
            this.ISAP_IV2_64 = 180214358336080908L;
            this.ISAP_IV3_64 = 252271952374008844L;
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAPAEAD_A
        protected void PX1() {
            P12();
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAPAEAD_A
        protected void PX2() {
            P12();
        }
    }

    private class ISAPAEAD_A_128A extends ISAPAEAD_A {
        public ISAPAEAD_A_128A() {
            super();
            this.ISAP_IV1_64 = 108156764297430540L;
            this.ISAP_IV2_64 = 180214358335358476L;
            this.ISAP_IV3_64 = 252271952373286412L;
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAPAEAD_A
        protected void PX1() {
            P6();
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAPAEAD_A
        protected void PX2() {
            ROUND(75L);
        }
    }

    private abstract class ISAPAEAD_K implements ISAP_AEAD {
        protected short[] ISAP_IV1_16;
        protected short[] ISAP_IV2_16;
        protected short[] ISAP_IV3_16;
        final int ISAP_STATE_SZ_CRYPTO_NPUBBYTES;
        protected short[] iv16;
        protected short[] k16;
        private final int[] KeccakF400RoundConstants = {1, 32898, 32906, 32768, 32907, 1, 32897, 32777, 138, 136, 32777, 10, 32907, 139, 32905, 32771, 32770, 128, 32778, 10};
        protected short[] SX = new short[25];
        protected short[] macSX = new short[25];
        protected short[] E = new short[25];
        protected short[] C = new short[5];
        protected short[] macE = new short[25];
        protected short[] macC = new short[5];

        public ISAPAEAD_K() {
            this.ISAP_STATE_SZ_CRYPTO_NPUBBYTES = 40 - ISAPEngine.this.IV_SIZE;
            ISAPEngine.this.ISAP_rH = 144;
            ISAPEngine.this.BlockSize = (ISAPEngine.this.ISAP_rH + 7) >> 3;
        }

        private short ROL16(short s, int i) {
            int i2 = s & 65535;
            return (short) ((i2 >>> (16 - i)) ^ (i2 << i));
        }

        private void byteToShort(byte[] bArr, short[] sArr, int i) {
            for (int i2 = 0; i2 < i; i2++) {
                sArr[i2] = Pack.littleEndianToShort(bArr, i2 << 1);
            }
        }

        private void byteToShortXor(byte[] bArr, int i, short[] sArr, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                sArr[i3] = (short) (sArr[i3] ^ Pack.littleEndianToShort(bArr, (i3 << 1) + i));
            }
        }

        private void shortToByte(short[] sArr, byte[] bArr) {
            for (int i = 0; i < 8; i++) {
                Pack.shortToLittleEndian(sArr[i], bArr, i << 1);
            }
        }

        protected abstract void PermuteRoundsBX(short[] sArr, short[] sArr2, short[] sArr3);

        protected abstract void PermuteRoundsHX(short[] sArr, short[] sArr2, short[] sArr3);

        protected abstract void PermuteRoundsKX(short[] sArr, short[] sArr2, short[] sArr3);

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void absorbFinalAADBlock() {
            if (ISAPEngine.this.m_aadPos == ISAPEngine.this.AADBufferSize) {
                absorbMacBlock(ISAPEngine.this.m_aad, 0);
                ISAPEngine.this.m_aadPos = 0;
            } else {
                for (int i = 0; i < ISAPEngine.this.m_aadPos; i++) {
                    short[] sArr = this.SX;
                    int i2 = i >> 1;
                    sArr[i2] = (short) (sArr[i2] ^ ((ISAPEngine.this.m_aad[i] & UByte.MAX_VALUE) << ((i & 1) << 3)));
                }
            }
            short[] sArr2 = this.SX;
            int i3 = ISAPEngine.this.m_aadPos >> 1;
            sArr2[i3] = (short) (sArr2[i3] ^ (128 << ((ISAPEngine.this.m_aadPos & 1) << 3)));
            PermuteRoundsHX(this.SX, this.E, this.C);
            short[] sArr3 = this.SX;
            sArr3[24] = (short) (sArr3[24] ^ 256);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void absorbMacBlock(byte[] bArr, int i) {
            byteToShortXor(bArr, i, this.SX, ISAPEngine.this.BlockSize >> 1);
            PermuteRoundsHX(this.SX, this.E, this.C);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void init() {
            this.k16 = new short[ISAPEngine.this.k.length >> 1];
            byte[] bArr = ISAPEngine.this.k;
            short[] sArr = this.k16;
            byteToShort(bArr, sArr, sArr.length);
            this.iv16 = new short[ISAPEngine.this.npub.length >> 1];
            byte[] bArr2 = ISAPEngine.this.npub;
            short[] sArr2 = this.iv16;
            byteToShort(bArr2, sArr2, sArr2.length);
        }

        public void isap_rk(short[] sArr, byte[] bArr, int i, short[] sArr2, int i2, short[] sArr3) {
            short[] sArr4 = new short[25];
            short[] sArr5 = new short[25];
            System.arraycopy(this.k16, 0, sArr4, 0, 8);
            System.arraycopy(sArr, 0, sArr4, 8, 4);
            PermuteRoundsKX(sArr4, sArr5, sArr3);
            for (int i3 = 0; i3 < (i << 3) - 1; i3++) {
                sArr4[0] = (short) (sArr4[0] ^ (((bArr[i3 >> 3] >>> (7 - (i3 & 7))) & 1) << 7));
                PermuteRoundsBX(sArr4, sArr5, sArr3);
            }
            sArr4[0] = (short) (sArr4[0] ^ ((bArr[i - 1] & 1) << 7));
            PermuteRoundsKX(sArr4, sArr5, sArr3);
            System.arraycopy(sArr4, 0, sArr2, 0, i2 == this.ISAP_STATE_SZ_CRYPTO_NPUBBYTES ? 17 : 8);
        }

        protected void prepareThetaX(short[] sArr, short[] sArr2) {
            sArr2[0] = (short) ((((sArr[0] ^ sArr[5]) ^ sArr[10]) ^ sArr[15]) ^ sArr[20]);
            sArr2[1] = (short) ((((sArr[1] ^ sArr[6]) ^ sArr[11]) ^ sArr[16]) ^ sArr[21]);
            sArr2[2] = (short) ((((sArr[2] ^ sArr[7]) ^ sArr[12]) ^ sArr[17]) ^ sArr[22]);
            sArr2[3] = (short) ((((sArr[3] ^ sArr[8]) ^ sArr[13]) ^ sArr[18]) ^ sArr[23]);
            sArr2[4] = (short) (sArr[24] ^ (((sArr[4] ^ sArr[9]) ^ sArr[14]) ^ sArr[19]));
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void processEncBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
            int i3 = 0;
            while (i3 < ISAPEngine.this.BlockSize) {
                bArr2[i2] = (byte) (bArr[i] ^ (this.SX[i3 >> 1] >>> ((i3 & 1) << 3)));
                i3++;
                i2++;
                i++;
            }
            PermuteRoundsKX(this.SX, this.E, this.C);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void processEncFinalBlock(byte[] bArr, int i) {
            int i2 = ISAPEngine.this.m_bufPos;
            int i3 = 0;
            while (i3 < i2) {
                bArr[i] = (byte) ((this.SX[i3 >> 1] >>> ((i3 & 1) << 3)) ^ ISAPEngine.this.m_buf[i3]);
                i3++;
                i++;
            }
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void processMACFinal(byte[] bArr, int i, int i2, byte[] bArr2) {
            int i3 = 0;
            if (i2 == ISAPEngine.this.BlockSize) {
                absorbMacBlock(bArr, i);
                i2 = 0;
            } else {
                while (i3 < i2) {
                    short[] sArr = this.SX;
                    int i4 = i3 >> 1;
                    sArr[i4] = (short) (((bArr[i] & 255) << ((i3 & 1) << 3)) ^ sArr[i4]);
                    i3++;
                    i++;
                }
            }
            short[] sArr2 = this.SX;
            int i5 = i2 >> 1;
            sArr2[i5] = (short) ((128 << ((i2 & 1) << 3)) ^ sArr2[i5]);
            PermuteRoundsHX(sArr2, this.E, this.C);
            shortToByte(this.SX, bArr2);
            isap_rk(this.ISAP_IV2_16, bArr2, ISAPEngine.this.KEY_SIZE, this.SX, ISAPEngine.this.KEY_SIZE, this.C);
            PermuteRoundsHX(this.SX, this.E, this.C);
            shortToByte(this.SX, bArr2);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void reset() {
            Arrays.fill(this.SX, (short) 0);
            isap_rk(this.ISAP_IV3_16, ISAPEngine.this.npub, ISAPEngine.this.IV_SIZE, this.SX, this.ISAP_STATE_SZ_CRYPTO_NPUBBYTES, this.C);
            System.arraycopy(this.iv16, 0, this.SX, 17, 8);
            PermuteRoundsKX(this.SX, this.E, this.C);
            swapInternalState();
            Arrays.fill(this.SX, 12, 25, (short) 0);
            System.arraycopy(this.iv16, 0, this.SX, 0, 8);
            System.arraycopy(this.ISAP_IV1_16, 0, this.SX, 8, 4);
            PermuteRoundsHX(this.SX, this.E, this.C);
        }

        protected void rounds12X(short[] sArr, short[] sArr2, short[] sArr3) {
            prepareThetaX(sArr, sArr3);
            rounds_8_18(sArr, sArr2, sArr3);
        }

        protected void rounds_12_18(short[] sArr, short[] sArr2, short[] sArr3) {
            thetaRhoPiChiIotaPrepareTheta(12, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(13, sArr2, sArr, sArr3);
            thetaRhoPiChiIotaPrepareTheta(14, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(15, sArr2, sArr, sArr3);
            thetaRhoPiChiIotaPrepareTheta(16, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(17, sArr2, sArr, sArr3);
            thetaRhoPiChiIotaPrepareTheta(18, sArr, sArr2, sArr3);
            thetaRhoPiChiIota(sArr2, sArr, sArr3);
        }

        protected void rounds_4_18(short[] sArr, short[] sArr2, short[] sArr3) {
            thetaRhoPiChiIotaPrepareTheta(4, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(5, sArr2, sArr, sArr3);
            thetaRhoPiChiIotaPrepareTheta(6, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(7, sArr2, sArr, sArr3);
            rounds_8_18(sArr, sArr2, sArr3);
        }

        protected void rounds_8_18(short[] sArr, short[] sArr2, short[] sArr3) {
            thetaRhoPiChiIotaPrepareTheta(8, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(9, sArr2, sArr, sArr3);
            thetaRhoPiChiIotaPrepareTheta(10, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(11, sArr2, sArr, sArr3);
            rounds_12_18(sArr, sArr2, sArr3);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAP_AEAD
        public void swapInternalState() {
            short[] sArr = this.SX;
            this.SX = this.macSX;
            this.macSX = sArr;
            short[] sArr2 = this.E;
            this.E = this.macE;
            this.macE = sArr2;
            short[] sArr3 = this.C;
            this.C = this.macC;
            this.macC = sArr3;
        }

        protected void thetaRhoPiChiIota(short[] sArr, short[] sArr2, short[] sArr3) {
            short sROL16 = (short) (sArr3[4] ^ ROL16(sArr3[1], 1));
            short sROL17 = (short) (sArr3[0] ^ ROL16(sArr3[2], 1));
            short sROL18 = (short) (sArr3[1] ^ ROL16(sArr3[3], 1));
            short sROL19 = (short) (sArr3[2] ^ ROL16(sArr3[4], 1));
            short sROL110 = (short) (sArr3[3] ^ ROL16(sArr3[0], 1));
            short s = (short) (sArr[0] ^ sROL16);
            sArr[0] = s;
            short s2 = (short) (sArr[6] ^ sROL17);
            sArr[6] = s2;
            short sROL111 = ROL16(s2, 12);
            short s3 = (short) (sArr[12] ^ sROL18);
            sArr[12] = s3;
            short sROL112 = ROL16(s3, 11);
            short s4 = (short) (sArr[18] ^ sROL19);
            sArr[18] = s4;
            short sROL113 = ROL16(s4, 5);
            short s5 = (short) (sArr[24] ^ sROL110);
            sArr[24] = s5;
            short sROL114 = ROL16(s5, 14);
            sArr2[0] = (short) ((((~sROL111) & sROL112) ^ s) ^ this.KeccakF400RoundConstants[19]);
            sArr2[1] = (short) (((~sROL112) & sROL113) ^ sROL111);
            sArr2[2] = (short) (((~sROL113) & sROL114) ^ sROL112);
            sArr2[3] = (short) (((~sROL114) & s) ^ sROL113);
            sArr2[4] = (short) (((~s) & sROL111) ^ sROL114);
            short s6 = (short) (sArr[3] ^ sROL19);
            sArr[3] = s6;
            short sROL115 = ROL16(s6, 12);
            short s7 = (short) (sArr[9] ^ sROL110);
            sArr[9] = s7;
            short sROL116 = ROL16(s7, 4);
            short s8 = (short) (sArr[10] ^ sROL16);
            sArr[10] = s8;
            short sROL117 = ROL16(s8, 3);
            short s9 = (short) (sArr[16] ^ sROL17);
            sArr[16] = s9;
            short sROL118 = ROL16(s9, 13);
            short s10 = (short) (sArr[22] ^ sROL18);
            sArr[22] = s10;
            short sROL119 = ROL16(s10, 13);
            sArr2[5] = (short) (((~sROL116) & sROL117) ^ sROL115);
            sArr2[6] = (short) (((~sROL117) & sROL118) ^ sROL116);
            sArr2[7] = (short) (((~sROL118) & sROL119) ^ sROL117);
            sArr2[8] = (short) (((~sROL119) & sROL115) ^ sROL118);
            sArr2[9] = (short) (((~sROL115) & sROL116) ^ sROL119);
            short s11 = (short) (sArr[1] ^ sROL17);
            sArr[1] = s11;
            short sROL120 = ROL16(s11, 1);
            short s12 = (short) (sArr[7] ^ sROL18);
            sArr[7] = s12;
            short sROL121 = ROL16(s12, 6);
            short s13 = (short) (sArr[13] ^ sROL19);
            sArr[13] = s13;
            short sROL122 = ROL16(s13, 9);
            short s14 = (short) (sArr[19] ^ sROL110);
            sArr[19] = s14;
            short sROL123 = ROL16(s14, 8);
            short s15 = (short) (sArr[20] ^ sROL16);
            sArr[20] = s15;
            short sROL124 = ROL16(s15, 2);
            sArr2[10] = (short) (((~sROL121) & sROL122) ^ sROL120);
            sArr2[11] = (short) (((~sROL122) & sROL123) ^ sROL121);
            sArr2[12] = (short) (sROL122 ^ ((~sROL123) & sROL124));
            sArr2[13] = (short) (((~sROL124) & sROL120) ^ sROL123);
            sArr2[14] = (short) (((~sROL120) & sROL121) ^ sROL124);
            short s16 = (short) (sArr[4] ^ sROL110);
            sArr[4] = s16;
            short sROL125 = ROL16(s16, 11);
            short s17 = (short) (sArr[5] ^ sROL16);
            sArr[5] = s17;
            short sROL126 = ROL16(s17, 4);
            short s18 = (short) (sArr[11] ^ sROL17);
            sArr[11] = s18;
            short sROL127 = ROL16(s18, 10);
            short s19 = (short) (sArr[17] ^ sROL18);
            sArr[17] = s19;
            short sROL128 = ROL16(s19, 15);
            short s20 = (short) (sArr[23] ^ sROL19);
            sArr[23] = s20;
            short sROL129 = ROL16(s20, 8);
            sArr2[15] = (short) (((~sROL126) & sROL127) ^ sROL125);
            sArr2[16] = (short) (((~sROL127) & sROL128) ^ sROL126);
            sArr2[17] = (short) (sROL127 ^ ((~sROL128) & sROL129));
            sArr2[18] = (short) (((~sROL129) & sROL125) ^ sROL128);
            sArr2[19] = (short) ((sROL126 & (~sROL125)) ^ sROL129);
            short s21 = (short) (sArr[2] ^ sROL18);
            sArr[2] = s21;
            short sROL130 = ROL16(s21, 14);
            short s22 = (short) (sArr[8] ^ sROL19);
            sArr[8] = s22;
            short sROL131 = ROL16(s22, 7);
            short s23 = (short) (sArr[14] ^ sROL110);
            sArr[14] = s23;
            short sROL132 = ROL16(s23, 7);
            short s24 = (short) (sROL16 ^ sArr[15]);
            sArr[15] = s24;
            short sROL133 = ROL16(s24, 9);
            short s25 = (short) (sROL17 ^ sArr[21]);
            sArr[21] = s25;
            short sROL134 = ROL16(s25, 2);
            sArr2[20] = (short) (((~sROL131) & sROL132) ^ sROL130);
            sArr2[21] = (short) (((~sROL132) & sROL133) ^ sROL131);
            sArr2[22] = (short) (sROL132 ^ ((~sROL133) & sROL134));
            sArr2[23] = (short) (sROL133 ^ ((~sROL134) & sROL130));
            sArr2[24] = (short) (((~sROL130) & sROL131) ^ sROL134);
        }

        protected void thetaRhoPiChiIotaPrepareTheta(int i, short[] sArr, short[] sArr2, short[] sArr3) {
            short sROL16 = (short) (sArr3[4] ^ ROL16(sArr3[1], 1));
            short sROL17 = (short) (sArr3[0] ^ ROL16(sArr3[2], 1));
            short sROL18 = (short) (sArr3[1] ^ ROL16(sArr3[3], 1));
            short sROL19 = (short) (sArr3[2] ^ ROL16(sArr3[4], 1));
            short sROL110 = (short) (sArr3[3] ^ ROL16(sArr3[0], 1));
            short s = (short) (sArr[0] ^ sROL16);
            sArr[0] = s;
            short s2 = (short) (sArr[6] ^ sROL17);
            sArr[6] = s2;
            short sROL111 = ROL16(s2, 12);
            short s3 = (short) (sArr[12] ^ sROL18);
            sArr[12] = s3;
            short sROL112 = ROL16(s3, 11);
            short s4 = (short) (sArr[18] ^ sROL19);
            sArr[18] = s4;
            short sROL113 = ROL16(s4, 5);
            short s5 = (short) (sArr[24] ^ sROL110);
            sArr[24] = s5;
            short sROL114 = ROL16(s5, 14);
            short s6 = (short) ((((~sROL111) & sROL112) ^ s) ^ this.KeccakF400RoundConstants[i]);
            sArr2[0] = s6;
            sArr3[0] = s6;
            short s7 = (short) (((~sROL112) & sROL113) ^ sROL111);
            sArr2[1] = s7;
            sArr3[1] = s7;
            short s8 = (short) (((~sROL113) & sROL114) ^ sROL112);
            sArr2[2] = s8;
            sArr3[2] = s8;
            short s9 = (short) (((~sROL114) & s) ^ sROL113);
            sArr2[3] = s9;
            sArr3[3] = s9;
            short s10 = (short) (((~s) & sROL111) ^ sROL114);
            sArr2[4] = s10;
            sArr3[4] = s10;
            short s11 = (short) (sArr[3] ^ sROL19);
            sArr[3] = s11;
            short sROL115 = ROL16(s11, 12);
            short s12 = (short) (sArr[9] ^ sROL110);
            sArr[9] = s12;
            short sROL116 = ROL16(s12, 4);
            short s13 = (short) (sArr[10] ^ sROL16);
            sArr[10] = s13;
            short sROL117 = ROL16(s13, 3);
            short s14 = (short) (sArr[16] ^ sROL17);
            sArr[16] = s14;
            short sROL118 = ROL16(s14, 13);
            short s15 = (short) (sArr[22] ^ sROL18);
            sArr[22] = s15;
            short sROL119 = ROL16(s15, 13);
            short s16 = (short) (((~sROL116) & sROL117) ^ sROL115);
            sArr2[5] = s16;
            sArr3[0] = (short) (sArr3[0] ^ s16);
            short s17 = (short) (((~sROL117) & sROL118) ^ sROL116);
            sArr2[6] = s17;
            sArr3[1] = (short) (sArr3[1] ^ s17);
            short s18 = (short) (sROL117 ^ ((~sROL118) & sROL119));
            sArr2[7] = s18;
            sArr3[2] = (short) (sArr3[2] ^ s18);
            short s19 = (short) (((~sROL119) & sROL115) ^ sROL118);
            sArr2[8] = s19;
            sArr3[3] = (short) (sArr3[3] ^ s19);
            short s20 = (short) (((~sROL115) & sROL116) ^ sROL119);
            sArr2[9] = s20;
            sArr3[4] = (short) (s20 ^ sArr3[4]);
            short s21 = (short) (sArr[1] ^ sROL17);
            sArr[1] = s21;
            short sROL120 = ROL16(s21, 1);
            short s22 = (short) (sArr[7] ^ sROL18);
            sArr[7] = s22;
            short sROL121 = ROL16(s22, 6);
            short s23 = (short) (sArr[13] ^ sROL19);
            sArr[13] = s23;
            short sROL122 = ROL16(s23, 9);
            short s24 = (short) (sArr[19] ^ sROL110);
            sArr[19] = s24;
            short sROL123 = ROL16(s24, 8);
            short s25 = (short) (sArr[20] ^ sROL16);
            sArr[20] = s25;
            short sROL124 = ROL16(s25, 2);
            short s26 = (short) (((~sROL121) & sROL122) ^ sROL120);
            sArr2[10] = s26;
            sArr3[0] = (short) (sArr3[0] ^ s26);
            short s27 = (short) (((~sROL122) & sROL123) ^ sROL121);
            sArr2[11] = s27;
            sArr3[1] = (short) (sArr3[1] ^ s27);
            short s28 = (short) (sROL122 ^ ((~sROL123) & sROL124));
            sArr2[12] = s28;
            sArr3[2] = (short) (s28 ^ sArr3[2]);
            short s29 = (short) (sROL123 ^ ((~sROL124) & sROL120));
            sArr2[13] = s29;
            sArr3[3] = (short) (s29 ^ sArr3[3]);
            short s30 = (short) (((~sROL120) & sROL121) ^ sROL124);
            sArr2[14] = s30;
            sArr3[4] = (short) (s30 ^ sArr3[4]);
            short s31 = (short) (sArr[4] ^ sROL110);
            sArr[4] = s31;
            short sROL125 = ROL16(s31, 11);
            short s32 = (short) (sArr[5] ^ sROL16);
            sArr[5] = s32;
            short sROL126 = ROL16(s32, 4);
            short s33 = (short) (sArr[11] ^ sROL17);
            sArr[11] = s33;
            short sROL127 = ROL16(s33, 10);
            short s34 = (short) (sArr[17] ^ sROL18);
            sArr[17] = s34;
            short sROL128 = ROL16(s34, 15);
            short s35 = (short) (sArr[23] ^ sROL19);
            sArr[23] = s35;
            short sROL129 = ROL16(s35, 8);
            short s36 = (short) (((~sROL126) & sROL127) ^ sROL125);
            sArr2[15] = s36;
            sArr3[0] = (short) (sArr3[0] ^ s36);
            short s37 = (short) (((~sROL127) & sROL128) ^ sROL126);
            sArr2[16] = s37;
            sArr3[1] = (short) (sArr3[1] ^ s37);
            short s38 = (short) (sROL127 ^ ((~sROL128) & sROL129));
            sArr2[17] = s38;
            sArr3[2] = (short) (sArr3[2] ^ s38);
            short s39 = (short) (((~sROL129) & sROL125) ^ sROL128);
            sArr2[18] = s39;
            sArr3[3] = (short) (s39 ^ sArr3[3]);
            short s40 = (short) (((~sROL125) & sROL126) ^ sROL129);
            sArr2[19] = s40;
            sArr3[4] = (short) (s40 ^ sArr3[4]);
            short s41 = (short) (sArr[2] ^ sROL18);
            sArr[2] = s41;
            short sROL130 = ROL16(s41, 14);
            short s42 = (short) (sArr[8] ^ sROL19);
            sArr[8] = s42;
            short sROL131 = ROL16(s42, 7);
            short s43 = (short) (sArr[14] ^ sROL110);
            sArr[14] = s43;
            short sROL132 = ROL16(s43, 7);
            short s44 = (short) (sROL16 ^ sArr[15]);
            sArr[15] = s44;
            short sROL133 = ROL16(s44, 9);
            short s45 = (short) (sROL17 ^ sArr[21]);
            sArr[21] = s45;
            short sROL134 = ROL16(s45, 2);
            short s46 = (short) (((~sROL131) & sROL132) ^ sROL130);
            sArr2[20] = s46;
            sArr3[0] = (short) (s46 ^ sArr3[0]);
            short s47 = (short) (((~sROL132) & sROL133) ^ sROL131);
            sArr2[21] = s47;
            sArr3[1] = (short) (s47 ^ sArr3[1]);
            short s48 = (short) (sROL132 ^ ((~sROL133) & sROL134));
            sArr2[22] = s48;
            sArr3[2] = (short) (s48 ^ sArr3[2]);
            short s49 = (short) (((~sROL134) & sROL130) ^ sROL133);
            sArr2[23] = s49;
            sArr3[3] = (short) (s49 ^ sArr3[3]);
            short s50 = (short) (((~sROL130) & sROL131) ^ sROL134);
            sArr2[24] = s50;
            sArr3[4] = (short) (s50 ^ sArr3[4]);
        }
    }

    private class ISAPAEAD_K_128 extends ISAPAEAD_K {
        public ISAPAEAD_K_128() {
            super();
            this.ISAP_IV1_16 = new short[]{-32767, 400, 3092, 3084};
            this.ISAP_IV2_16 = new short[]{-32766, 400, 3092, 3084};
            this.ISAP_IV3_16 = new short[]{-32765, 400, 3092, 3084};
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAPAEAD_K
        protected void PermuteRoundsBX(short[] sArr, short[] sArr2, short[] sArr3) {
            rounds12X(sArr, sArr2, sArr3);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAPAEAD_K
        protected void PermuteRoundsHX(short[] sArr, short[] sArr2, short[] sArr3) {
            prepareThetaX(sArr, sArr3);
            thetaRhoPiChiIotaPrepareTheta(0, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(1, sArr2, sArr, sArr3);
            thetaRhoPiChiIotaPrepareTheta(2, sArr, sArr2, sArr3);
            thetaRhoPiChiIotaPrepareTheta(3, sArr2, sArr, sArr3);
            rounds_4_18(sArr, sArr2, sArr3);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAPAEAD_K
        protected void PermuteRoundsKX(short[] sArr, short[] sArr2, short[] sArr3) {
            rounds12X(sArr, sArr2, sArr3);
        }
    }

    private class ISAPAEAD_K_128A extends ISAPAEAD_K {
        public ISAPAEAD_K_128A() {
            super();
            this.ISAP_IV1_16 = new short[]{-32767, 400, 272, 2056};
            this.ISAP_IV2_16 = new short[]{-32766, 400, 272, 2056};
            this.ISAP_IV3_16 = new short[]{-32765, 400, 272, 2056};
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAPAEAD_K
        protected void PermuteRoundsBX(short[] sArr, short[] sArr2, short[] sArr3) {
            prepareThetaX(sArr, sArr3);
            thetaRhoPiChiIotaPrepareTheta(19, sArr, sArr2, sArr3);
            System.arraycopy(sArr2, 0, sArr, 0, sArr2.length);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAPAEAD_K
        protected void PermuteRoundsHX(short[] sArr, short[] sArr2, short[] sArr3) {
            prepareThetaX(sArr, sArr3);
            rounds_4_18(sArr, sArr2, sArr3);
        }

        @Override // org.bouncycastle.crypto.engines.ISAPEngine.ISAPAEAD_K
        protected void PermuteRoundsKX(short[] sArr, short[] sArr2, short[] sArr3) {
            prepareThetaX(sArr, sArr3);
            rounds_12_18(sArr, sArr2, sArr3);
        }
    }

    private interface ISAP_AEAD {
        void absorbFinalAADBlock();

        void absorbMacBlock(byte[] bArr, int i);

        void init();

        void processEncBlock(byte[] bArr, int i, byte[] bArr2, int i2);

        void processEncFinalBlock(byte[] bArr, int i);

        void processMACFinal(byte[] bArr, int i, int i2, byte[] bArr2);

        void reset();

        void swapInternalState();
    }

    public enum IsapType {
        ISAP_A_128A,
        ISAP_K_128A,
        ISAP_A_128,
        ISAP_K_128
    }

    public ISAPEngine(IsapType isapType) {
        String str;
        this.KEY_SIZE = 16;
        this.IV_SIZE = 16;
        this.MAC_SIZE = 16;
        int iOrdinal = isapType.ordinal();
        if (iOrdinal == 0) {
            this.ISAPAEAD = new ISAPAEAD_A_128A();
            str = "ISAP-A-128A AEAD";
        } else if (iOrdinal == 1) {
            this.ISAPAEAD = new ISAPAEAD_K_128A();
            str = "ISAP-K-128A AEAD";
        } else {
            if (iOrdinal != 2) {
                if (iOrdinal == 3) {
                    this.ISAPAEAD = new ISAPAEAD_K_128();
                    str = "ISAP-K-128 AEAD";
                }
                this.AADBufferSize = this.BlockSize;
                this.m_aad = new byte[this.AADBufferSize];
            }
            this.ISAPAEAD = new ISAPAEAD_A_128();
            str = "ISAP-A-128 AEAD";
        }
        this.algorithmName = str;
        this.AADBufferSize = this.BlockSize;
        this.m_aad = new byte[this.AADBufferSize];
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
        this.npub = bArr2;
        this.k = bArr;
        this.m_buf = new byte[this.BlockSize + (this.forEncryption ? 0 : this.MAC_SIZE)];
        this.ISAPAEAD.init();
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
        this.ISAPAEAD.processEncBlock(bArr, i, bArr2, i2);
        this.ISAPAEAD.swapInternalState();
        if (this.forEncryption) {
            this.ISAPAEAD.absorbMacBlock(bArr2, i2);
        } else {
            this.ISAPAEAD.absorbMacBlock(bArr, i);
        }
        this.ISAPAEAD.swapInternalState();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBufferBaseEngine
    protected void processBufferAAD(byte[] bArr, int i) {
        this.ISAPAEAD.absorbMacBlock(bArr, i);
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
        if (this.aadFinished) {
            return;
        }
        this.ISAPAEAD.absorbFinalAADBlock();
        this.ISAPAEAD.swapInternalState();
        this.m_aadPos = 0;
        this.aadFinished = true;
    }

    @Override // org.bouncycastle.crypto.engines.AEADBufferBaseEngine
    protected void processFinalBlock(byte[] bArr, int i) {
        processFinalAAD();
        int i2 = this.m_bufPos;
        this.mac = new byte[this.MAC_SIZE];
        this.ISAPAEAD.processEncFinalBlock(bArr, i);
        this.ISAPAEAD.swapInternalState();
        if (this.forEncryption) {
            this.ISAPAEAD.processMACFinal(bArr, i, i2, this.mac);
        } else {
            this.ISAPAEAD.processMACFinal(this.m_buf, 0, i2, this.mac);
        }
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void reset() {
        super.reset();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void reset(boolean z) {
        if (!this.initialised) {
            throw new IllegalStateException("Need call init function before encryption/decryption");
        }
        Arrays.fill(this.m_buf, (byte) 0);
        Arrays.fill(this.m_aad, (byte) 0);
        this.ISAPAEAD.reset();
        this.m_bufPos = 0;
        this.m_aadPos = 0;
        this.aadFinished = false;
        super.reset(z);
    }
}
