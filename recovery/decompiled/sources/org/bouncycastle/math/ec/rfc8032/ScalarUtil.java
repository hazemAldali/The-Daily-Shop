package org.bouncycastle.math.ec.rfc8032;

import org.bouncycastle.util.Integers;

/* JADX INFO: loaded from: classes3.dex */
abstract class ScalarUtil {
    private static final long M = 4294967295L;

    ScalarUtil() {
    }

    static void addShifted_NP(int i, int i2, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        int[] iArr5 = iArr4;
        int i3 = 0;
        long j = 0;
        if (i2 == 0) {
            long j2 = 0;
            while (i3 <= i) {
                int i4 = iArr3[i3];
                long j3 = j + (((long) iArr[i3]) & M);
                long j4 = ((long) i4) & M;
                long j5 = j2 + j4 + (((long) iArr2[i3]) & M);
                int i5 = (int) j5;
                j2 = j5 >>> 32;
                iArr3[i3] = i5;
                long j6 = j3 + j4 + (((long) i5) & M);
                iArr[i3] = (int) j6;
                j = j6 >>> 32;
                i3++;
            }
            return;
        }
        if (i2 < 32) {
            int i6 = 0;
            long j7 = 0;
            long j8 = 0;
            int i7 = 0;
            int i8 = 0;
            while (i3 <= i) {
                int i9 = iArr3[i3];
                int i10 = -i2;
                long j9 = j7 + (((long) iArr[i3]) & M) + (((long) ((i6 >>> i10) | (i9 << i2))) & M);
                int i11 = iArr2[i3];
                long j10 = j8 + (((long) i9) & M) + (((long) ((i7 >>> i10) | (i11 << i2))) & M);
                int i12 = (int) j10;
                j8 = j10 >>> 32;
                iArr3[i3] = i12;
                long j11 = j9 + (((long) ((i8 >>> i10) | (i12 << i2))) & M);
                iArr[i3] = (int) j11;
                j7 = j11 >>> 32;
                i3++;
                i8 = i12;
                i7 = i11;
                i6 = i9;
            }
            return;
        }
        System.arraycopy(iArr3, 0, iArr5, 0, i);
        int i13 = i2 >>> 5;
        int i14 = i2 & 31;
        if (i14 == 0) {
            long j12 = 0;
            for (int i15 = i13; i15 <= i; i15++) {
                int i16 = i15 - i13;
                long j13 = j + (((long) iArr[i15]) & M) + (((long) iArr5[i16]) & M);
                long j14 = j12 + (((long) iArr3[i15]) & M) + (((long) iArr2[i16]) & M);
                iArr3[i15] = (int) j14;
                j12 = j14 >>> 32;
                long j15 = j13 + (((long) iArr3[i16]) & M);
                iArr[i15] = (int) j15;
                j = j15 >>> 32;
            }
            return;
        }
        int i17 = i13;
        long j16 = 0;
        long j17 = 0;
        int i18 = 0;
        int i19 = 0;
        while (i17 <= i) {
            int i20 = i17 - i13;
            int i21 = iArr5[i20];
            int i22 = -i14;
            int i23 = i13;
            long j18 = j16 + (((long) iArr[i17]) & M) + (((long) ((i21 << i14) | (i3 >>> i22))) & M);
            int i24 = iArr2[i20];
            long j19 = j17 + (((long) iArr3[i17]) & M) + (((long) ((i24 << i14) | (i18 >>> i22))) & M);
            iArr3[i17] = (int) j19;
            j17 = j19 >>> 32;
            int i25 = iArr3[i20];
            long j20 = j18 + (((long) ((i19 >>> i22) | (i25 << i14))) & M);
            iArr[i17] = (int) j20;
            j16 = j20 >>> 32;
            i17++;
            iArr5 = iArr4;
            i19 = i25;
            i18 = i24;
            i3 = i21;
            i13 = i23;
        }
    }

    static void addShifted_UV(int i, int i2, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        int i3 = i;
        int i4 = i2 >>> 5;
        int i5 = i2 & 31;
        long j = 0;
        if (i5 == 0) {
            long j2 = 0;
            for (int i6 = i4; i6 <= i3; i6++) {
                long j3 = j + (((long) iArr[i6]) & M);
                long j4 = j2 + (((long) iArr2[i6]) & M);
                int i7 = i6 - i4;
                long j5 = j3 + (((long) iArr3[i7]) & M);
                long j6 = j4 + (((long) iArr4[i7]) & M);
                iArr[i6] = (int) j5;
                j = j5 >>> 32;
                iArr2[i6] = (int) j6;
                j2 = j6 >>> 32;
            }
            return;
        }
        int i8 = i4;
        int i9 = 0;
        int i10 = 0;
        long j7 = 0;
        while (i8 <= i3) {
            int i11 = i8 - i4;
            int i12 = iArr3[i11];
            int i13 = iArr4[i11];
            int i14 = -i5;
            long j8 = j + (((long) iArr[i8]) & M);
            long j9 = j7 + (((long) iArr2[i8]) & M);
            long j10 = j8 + (((long) ((i9 >>> i14) | (i12 << i5))) & M);
            long j11 = j9 + (((long) ((i10 >>> i14) | (i13 << i5))) & M);
            iArr[i8] = (int) j10;
            j = j10 >>> 32;
            iArr2[i8] = (int) j11;
            j7 = j11 >>> 32;
            i8++;
            i10 = i13;
            i9 = i12;
            i4 = i4;
            i3 = i;
        }
    }

    static int getBitLength(int i, int[] iArr) {
        int i2 = iArr[i] >> 31;
        while (i > 0 && iArr[i] == i2) {
            i--;
        }
        return ((i * 32) + 32) - Integers.numberOfLeadingZeros(iArr[i] ^ i2);
    }

    static int getBitLengthPositive(int i, int[] iArr) {
        while (i > 0 && iArr[i] == 0) {
            i--;
        }
        return ((i * 32) + 32) - Integers.numberOfLeadingZeros(iArr[i]);
    }

    static boolean lessThan(int i, int[] iArr, int[] iArr2) {
        do {
            int i2 = iArr[i] - Integer.MIN_VALUE;
            int i3 = iArr2[i] - Integer.MIN_VALUE;
            if (i2 < i3) {
                return true;
            }
            if (i2 > i3) {
                return false;
            }
            i--;
        } while (i >= 0);
        return false;
    }

    static void subShifted_NP(int i, int i2, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        int[] iArr5 = iArr4;
        int i3 = 0;
        long j = 0;
        if (i2 == 0) {
            long j2 = 0;
            while (i3 <= i) {
                int i4 = iArr3[i3];
                long j3 = j + (((long) iArr[i3]) & M);
                long j4 = ((long) i4) & M;
                long j5 = (j2 + j4) - (((long) iArr2[i3]) & M);
                int i5 = (int) j5;
                j2 = j5 >> 32;
                iArr3[i3] = i5;
                long j6 = (j3 - j4) - (((long) i5) & M);
                iArr[i3] = (int) j6;
                j = j6 >> 32;
                i3++;
            }
            return;
        }
        if (i2 < 32) {
            int i6 = 0;
            long j7 = 0;
            long j8 = 0;
            int i7 = 0;
            int i8 = 0;
            while (i3 <= i) {
                int i9 = iArr3[i3];
                int i10 = -i2;
                long j9 = (j7 + (((long) iArr[i3]) & M)) - (((long) ((i6 >>> i10) | (i9 << i2))) & M);
                int i11 = iArr2[i3];
                long j10 = (j8 + (((long) i9) & M)) - (((long) ((i7 >>> i10) | (i11 << i2))) & M);
                int i12 = (int) j10;
                j8 = j10 >> 32;
                iArr3[i3] = i12;
                long j11 = j9 - (((long) ((i8 >>> i10) | (i12 << i2))) & M);
                iArr[i3] = (int) j11;
                j7 = j11 >> 32;
                i3++;
                i8 = i12;
                i7 = i11;
                i6 = i9;
            }
            return;
        }
        System.arraycopy(iArr3, 0, iArr5, 0, i);
        int i13 = i2 >>> 5;
        int i14 = i2 & 31;
        if (i14 == 0) {
            long j12 = 0;
            for (int i15 = i13; i15 <= i; i15++) {
                int i16 = i15 - i13;
                long j13 = (j + (((long) iArr[i15]) & M)) - (((long) iArr5[i16]) & M);
                long j14 = (j12 + (((long) iArr3[i15]) & M)) - (((long) iArr2[i16]) & M);
                iArr3[i15] = (int) j14;
                j12 = j14 >> 32;
                long j15 = j13 - (((long) iArr3[i16]) & M);
                iArr[i15] = (int) j15;
                j = j15 >> 32;
            }
            return;
        }
        int i17 = i13;
        long j16 = 0;
        long j17 = 0;
        int i18 = 0;
        int i19 = 0;
        while (i17 <= i) {
            int i20 = i17 - i13;
            int i21 = iArr5[i20];
            int i22 = -i14;
            int i23 = i13;
            long j18 = (j16 + (((long) iArr[i17]) & M)) - (((long) ((i21 << i14) | (i3 >>> i22))) & M);
            int i24 = iArr2[i20];
            long j19 = (j17 + (((long) iArr3[i17]) & M)) - (((long) ((i24 << i14) | (i18 >>> i22))) & M);
            iArr3[i17] = (int) j19;
            j17 = j19 >> 32;
            int i25 = iArr3[i20];
            long j20 = j18 - (((long) ((i19 >>> i22) | (i25 << i14))) & M);
            iArr[i17] = (int) j20;
            j16 = j20 >> 32;
            i17++;
            iArr5 = iArr4;
            i19 = i25;
            i18 = i24;
            i3 = i21;
            i13 = i23;
        }
    }

    static void subShifted_UV(int i, int i2, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        int i3 = i;
        int i4 = i2 >>> 5;
        int i5 = i2 & 31;
        long j = 0;
        if (i5 == 0) {
            long j2 = 0;
            for (int i6 = i4; i6 <= i3; i6++) {
                long j3 = j + (((long) iArr[i6]) & M);
                long j4 = j2 + (((long) iArr2[i6]) & M);
                int i7 = i6 - i4;
                long j5 = j3 - (((long) iArr3[i7]) & M);
                long j6 = j4 - (((long) iArr4[i7]) & M);
                iArr[i6] = (int) j5;
                j = j5 >> 32;
                iArr2[i6] = (int) j6;
                j2 = j6 >> 32;
            }
            return;
        }
        int i8 = i4;
        int i9 = 0;
        int i10 = 0;
        long j7 = 0;
        while (i8 <= i3) {
            int i11 = i8 - i4;
            int i12 = iArr3[i11];
            int i13 = iArr4[i11];
            int i14 = -i5;
            long j8 = j + (((long) iArr[i8]) & M);
            long j9 = j7 + (((long) iArr2[i8]) & M);
            long j10 = j8 - (((long) ((i9 >>> i14) | (i12 << i5))) & M);
            long j11 = j9 - (((long) ((i10 >>> i14) | (i13 << i5))) & M);
            iArr[i8] = (int) j10;
            j = j10 >> 32;
            iArr2[i8] = (int) j11;
            j7 = j11 >> 32;
            i8++;
            i10 = i13;
            i9 = i12;
            i4 = i4;
            i3 = i;
        }
    }
}
