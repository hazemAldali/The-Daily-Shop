package org.bouncycastle.pqc.crypto.gemss;

import java.math.BigInteger;
import java.security.SecureRandom;
import kotlin.UByte;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMEngine;
import org.bouncycastle.util.Pack;

/* JADX INFO: loaded from: classes3.dex */
class GeMSSEngine {
    final int ACCESS_last_equations8;
    Pointer Buffer_NB_WORD_GFqn;
    Pointer Buffer_NB_WORD_MUL;
    final boolean ENABLED_REMOVE_ODD_DEGREE;
    final int HFEDELTA;
    final int HFEDeg;
    final int HFEDegI;
    final int HFEDegJ;
    final int HFENr8;
    final int HFENr8c;
    int HFE_odd_degree;
    final int HFEm;
    final int HFEmq;
    final int HFEmq8;
    final int HFEmr;
    final int HFEmr8;
    final int HFEn;
    int HFEn1h_rightmost;
    int HFEn_1rightmost;
    final int HFEnq;
    final int HFEnr;
    final int HFEnv;
    final int HFEnvq;
    final int HFEnvr;
    final int HFEnvr8;
    final int HFEv;
    final int HFEvq;
    final int HFEvr;
    int II;
    int KP;
    int KX;
    final int LOST_BITS;
    int LTRIANGULAR_NV_SIZE;
    final int LTRIANGULAR_N_SIZE;
    final long MASK_GF2m;
    final long MASK_GF2n;
    final int MATRIXn_SIZE;
    final int MATRIXnv_SIZE;
    final int MLv_GFqn_SIZE;
    int MQv_GFqn_SIZE;
    final int NB_BYTES_EQUATION;
    final int NB_BYTES_GFqm;
    final int NB_BYTES_GFqn;
    final int NB_BYTES_GFqnv;
    int NB_COEFS_HFEPOLY;
    final int NB_ITE;
    int NB_MONOMIAL_PK;
    int NB_MONOMIAL_VINEGAR;
    int NB_UINT_HFEVPOLY;
    int NB_WORD_GF2m;
    int NB_WORD_GF2nv;
    final int NB_WORD_GF2nvm;
    int NB_WORD_GFqn;
    final int NB_WORD_GFqv;
    int NB_WORD_MMUL;
    final int NB_WORD_MUL;
    final int NB_WORD_UNCOMP_EQ;
    int POW_II;
    final int SIZE_DIGEST;
    final int SIZE_DIGEST_UINT;
    final int SIZE_ROW;
    final int SIZE_SEED_SK;
    final int SIZE_SIGN_UNCOMPRESSED;
    final int Sha3BitStrength;
    final int ShakeBitStrength;
    final int VAL_BITS_M;
    private int buffer;
    Mul_GF2x mul;
    private SecureRandom random;
    Rem_GF2n rem;
    SHA3Digest sha3Digest;
    final int NB_BITS_UINT = 64;
    final int LEN_UNROLLED_64 = 4;

    enum FunctionParams {
        NV,
        V,
        N,
        M
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0231  */
    /* JADX WARN: Code duplicated, block: B:103:0x0239  */
    /* JADX WARN: Code duplicated, block: B:105:0x0241  */
    /* JADX WARN: Code duplicated, block: B:107:0x0252  */
    /* JADX WARN: Code duplicated, block: B:108:0x0254  */
    /* JADX WARN: Code duplicated, block: B:111:0x025d  */
    /* JADX WARN: Code duplicated, block: B:112:0x025f  */
    /* JADX WARN: Code duplicated, block: B:115:0x0274  */
    /* JADX WARN: Code duplicated, block: B:116:0x027e  */
    /* JADX WARN: Code duplicated, block: B:118:0x0282  */
    /* JADX WARN: Code duplicated, block: B:119:0x028e  */
    /* JADX WARN: Code duplicated, block: B:123:0x02bd  */
    /* JADX WARN: Code duplicated, block: B:126:0x02c3  */
    /* JADX WARN: Code duplicated, block: B:128:0x02c7  */
    /* JADX WARN: Code duplicated, block: B:129:0x02c9  */
    /* JADX WARN: Code duplicated, block: B:132:0x02d9  */
    /* JADX WARN: Code duplicated, block: B:133:0x02dc  */
    /* JADX WARN: Code duplicated, block: B:136:0x02e8  */
    /* JADX WARN: Code duplicated, block: B:138:0x02ec A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:140:0x0304  */
    /* JADX WARN: Code duplicated, block: B:142:0x0321  */
    /* JADX WARN: Code duplicated, block: B:143:0x0323  */
    /* JADX WARN: Code duplicated, block: B:149:0x033f  */
    /* JADX WARN: Code duplicated, block: B:151:0x0343  */
    /* JADX WARN: Code duplicated, block: B:153:0x0358  */
    /* JADX WARN: Code duplicated, block: B:155:0x035c  */
    /* JADX WARN: Code duplicated, block: B:156:0x036e  */
    /* JADX WARN: Code duplicated, block: B:158:0x0372  */
    /* JADX WARN: Code duplicated, block: B:159:0x0384  */
    /* JADX WARN: Code duplicated, block: B:161:0x0387  */
    /* JADX WARN: Code duplicated, block: B:163:0x038b  */
    /* JADX WARN: Code duplicated, block: B:166:0x0390  */
    /* JADX WARN: Code duplicated, block: B:167:0x03a2  */
    /* JADX WARN: Code duplicated, block: B:168:0x03b4  */
    /* JADX WARN: Code duplicated, block: B:172:0x03e0 A[LOOP:0: B:170:0x03da->B:172:0x03e0, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:176:0x03f0 A[LOOP:1: B:174:0x03e8->B:176:0x03f0, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:179:0x03e5 A[EDGE_INSN: B:179:0x03e5->B:173:0x03e5 BREAK  A[LOOP:0: B:170:0x03da->B:172:0x03e0], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:180:0x03f3 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:24:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:27:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:28:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:31:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:32:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:35:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:36:0x0101  */
    /* JADX WARN: Code duplicated, block: B:39:0x0118  */
    /* JADX WARN: Code duplicated, block: B:40:0x011a  */
    /* JADX WARN: Code duplicated, block: B:43:0x016e  */
    /* JADX WARN: Code duplicated, block: B:45:0x0172  */
    /* JADX WARN: Code duplicated, block: B:47:0x0176  */
    /* JADX WARN: Code duplicated, block: B:49:0x017a  */
    /* JADX WARN: Code duplicated, block: B:51:0x017e  */
    /* JADX WARN: Code duplicated, block: B:53:0x0182  */
    /* JADX WARN: Code duplicated, block: B:55:0x0186 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:56:0x0188 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:57:0x018a  */
    /* JADX WARN: Code duplicated, block: B:59:0x018e  */
    /* JADX WARN: Code duplicated, block: B:61:0x0192 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:62:0x0194  */
    /* JADX WARN: Code duplicated, block: B:64:0x0198 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:65:0x019a  */
    /* JADX WARN: Code duplicated, block: B:67:0x019e  */
    /* JADX WARN: Code duplicated, block: B:69:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:70:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:72:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:73:0x01bc  */
    /* JADX WARN: Code duplicated, block: B:74:0x01c1  */
    /* JADX WARN: Code duplicated, block: B:75:0x01c8  */
    /* JADX WARN: Code duplicated, block: B:76:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:77:0x01ce  */
    /* JADX WARN: Code duplicated, block: B:78:0x01d2  */
    /* JADX WARN: Code duplicated, block: B:79:0x01d5  */
    /* JADX WARN: Code duplicated, block: B:80:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:81:0x01db  */
    /* JADX WARN: Code duplicated, block: B:82:0x01de  */
    /* JADX WARN: Code duplicated, block: B:83:0x01e1  */
    /* JADX WARN: Code duplicated, block: B:84:0x01e5  */
    /* JADX WARN: Code duplicated, block: B:85:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:87:0x01ee  */
    /* JADX WARN: Code duplicated, block: B:90:0x01f5  */
    /* JADX WARN: Code duplicated, block: B:91:0x01fa  */
    /* JADX WARN: Code duplicated, block: B:94:0x020a  */
    /* JADX WARN: Code duplicated, block: B:96:0x0215 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:97:0x0217 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:98:0x0219  */
    /* JADX WARN: Code duplicated, block: B:99:0x0229  */
    /* JADX WARN: Instruction removed from duplicated block: B:70:0x01a5, please report this as an issue */
    public GeMSSEngine(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Mul_GF2x mul6;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        long jMaskUINT;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int i25;
        int i26;
        int i27;
        int i28;
        int i29;
        int i30;
        Rem_GF2n rem192_specialized_trinomial_gf2x;
        int i31;
        int i32;
        int i33;
        int i34;
        Rem_GF2n rem544_pentanomial_gf2x;
        int i35;
        int i36;
        this.HFEn = i2;
        this.HFEv = i3;
        this.HFEDELTA = i4;
        this.NB_ITE = i5;
        this.HFEDeg = i6;
        this.HFEDegI = i7;
        this.HFEDegJ = i8;
        this.NB_BYTES_GFqn = (i2 >>> 3) + ((i2 & 7) != 0 ? 1 : 0);
        int i37 = i7 + 1;
        this.SIZE_ROW = i37;
        int i38 = i2 + i3;
        this.HFEnv = i38;
        int i39 = i2 >>> 6;
        this.HFEnq = i39;
        int i40 = i2 & 63;
        this.HFEnr = i40;
        int i41 = i38 >>> 6;
        this.HFEnvq = i41;
        int i42 = i38 & 63;
        this.HFEnvr = i42;
        this.SIZE_SEED_SK = i >>> 3;
        int i43 = i2 - 1;
        int i44 = ((i43 << 1) >>> 6) + 1;
        this.NB_WORD_MUL = i44;
        if (i44 == 6) {
            mul6 = new Mul_GF2x.Mul6();
        } else if (i44 == 9) {
            mul6 = new Mul_GF2x.Mul9();
        } else if (i44 == 17) {
            mul6 = new Mul_GF2x.Mul17();
        } else if (i44 != 12) {
            if (i44 == 13) {
                mul6 = new Mul_GF2x.Mul13();
            }
            i9 = 64 - i40;
            int i45 = i2 - i4;
            this.HFEm = i45;
            int i46 = i45 >>> 6;
            this.HFEmq = i46;
            i10 = i45 & 63;
            this.HFEmr = i10;
            int i47 = i3 >>> 6;
            this.HFEvq = i47;
            int i48 = i3 & 63;
            this.HFEvr = i48;
            this.NB_WORD_GFqv = i48 != 0 ? i47 + 1 : i47;
            int i49 = i45 >>> 3;
            this.HFEmq8 = i49;
            i11 = i45 & 7;
            this.HFEmr8 = i11;
            if (i11 != 0) {
                i12 = 1;
            } else {
                i12 = 0;
            }
            this.NB_BYTES_GFqm = i12 + i49;
            this.NB_WORD_UNCOMP_EQ = ((((i41 + 1) * i41) >>> 1) * 64) + ((i41 + 1) * i42);
            i13 = i38 & 7;
            this.HFEnvr8 = i13;
            int i50 = i38 >>> 3;
            if (i13 != 0) {
                i14 = 1;
            } else {
                i14 = 0;
            }
            this.NB_BYTES_GFqnv = i50 + i14;
            this.VAL_BITS_M = Math.min(i4 + i3, 8 - i11);
            this.MASK_GF2m = GeMSSUtils.maskUINT(i10);
            jMaskUINT = GeMSSUtils.maskUINT(i40);
            this.MASK_GF2n = jMaskUINT;
            if (i40 != 0) {
                i15 = 1;
            } else {
                i15 = 0;
            }
            int i51 = i15 + i39;
            this.NB_WORD_GFqn = i51;
            this.LTRIANGULAR_N_SIZE = (((i39 * (i39 + 1)) >>> 1) * 64) + (i51 * i40);
            this.MATRIXn_SIZE = i2 * i51;
            if (i42 != 0) {
                i16 = 1;
            } else {
                i16 = 0;
            }
            int i52 = i16 + i41;
            this.NB_WORD_GF2nv = i52;
            this.MATRIXnv_SIZE = i38 * i52;
            this.LTRIANGULAR_NV_SIZE = (((i41 * (i41 + 1)) >>> 1) * 64) + (i42 * i52);
            int i53 = i3 + 1;
            int i54 = ((i3 * i53) >>> 1) + 1;
            this.NB_MONOMIAL_VINEGAR = i54;
            int i55 = (i38 * (i38 + 1)) >>> 1;
            int i56 = i55 + 1;
            this.NB_MONOMIAL_PK = i56;
            this.MQv_GFqn_SIZE = i54 * i51;
            this.ACCESS_last_equations8 = i49 * i56;
            this.NB_BYTES_EQUATION = (i55 + 8) >>> 3;
            int i57 = i56 & 7;
            this.HFENr8 = i57;
            int i58 = (8 - i57) & 7;
            this.HFENr8c = i58;
            this.LOST_BITS = (i11 - 1) * i58;
            this.NB_WORD_MMUL = i44;
            if (i2 != 174) {
                if (i2 != 175) {
                    i36 = 16;
                } else if (i2 != 177) {
                    i17 = 0;
                    i18 = 8;
                } else if (i2 != 178) {
                    i17 = 0;
                    i18 = 31;
                } else if (i2 != 265) {
                    i36 = 42;
                } else if (i2 != 266) {
                    i36 = 47;
                } else if (i2 != 268) {
                    i36 = 25;
                } else if (i2 != 354) {
                    i36 = 99;
                } else if (i2 != 358) {
                    if (i2 != 364) {
                        i17 = 0;
                        i18 = 9;
                    } else if (i2 != 366) {
                        i36 = 29;
                    } else if (i2 != 402) {
                        i36 = 171;
                    } else if (i2 != 537) {
                        i18 = 10;
                        i19 = 2;
                        i17 = 1;
                    } else if (i2 != 544) {
                        i18 = 128;
                        i17 = 1;
                        i19 = 3;
                    } else if (i2 != 270) {
                        i36 = 53;
                    } else {
                        if (i2 == 271) {
                            throw new IllegalArgumentException("error: need to add support for HFEn=" + i2);
                        }
                        i36 = 58;
                    }
                    if (i19 != 0) {
                        i20 = 64 - i17;
                        i21 = 64 - i19;
                    } else {
                        i20 = 0;
                        i21 = 0;
                    }
                    i22 = 64 - (i18 & 63);
                    i23 = i6 & 1;
                    if (i23 == 0) {
                        this.ENABLED_REMOVE_ODD_DEGREE = true;
                        i35 = (1 << i7) + 1;
                        this.HFE_odd_degree = i35;
                        if (i23 == 0) {
                            throw new IllegalArgumentException("HFEDeg is odd, so to remove the leading term would decrease the degree.");
                        }
                        if (i35 <= i6) {
                            throw new IllegalArgumentException("It is useless to remove 0 term.");
                        }
                        if (i35 > 1) {
                            throw new IllegalArgumentException("The case where the term X^3 is removing is not implemented.");
                        }
                        this.NB_COEFS_HFEPOLY = i8 + 2 + (((i7 - 1) * i7) >>> 1) + i7;
                        i24 = 0;
                    } else {
                        i24 = 0;
                        this.ENABLED_REMOVE_ODD_DEGREE = false;
                        this.NB_COEFS_HFEPOLY = i8 + 2 + ((i7 * i37) >>> 1);
                    }
                    if (i10 != 0) {
                        i25 = 1;
                    } else {
                        i25 = i24;
                    }
                    int i59 = i46 + i25;
                    this.NB_WORD_GF2m = i59;
                    int i60 = i52 - i59;
                    if (i10 != 0) {
                        i26 = 1;
                    } else {
                        i26 = i24;
                    }
                    int i61 = i60 + i26;
                    this.NB_WORD_GF2nvm = i61;
                    this.SIZE_SIGN_UNCOMPRESSED = i52 + ((i5 - 1) * i61);
                    if (i <= 128) {
                        this.SIZE_DIGEST = 32;
                        this.SIZE_DIGEST_UINT = 4;
                        this.ShakeBitStrength = 128;
                        this.Sha3BitStrength = 256;
                    } else {
                        if (i <= 192) {
                            this.SIZE_DIGEST = 48;
                            this.SIZE_DIGEST_UINT = 6;
                            this.ShakeBitStrength = 256;
                            i27 = MLKEMEngine.KyberPolyBytes;
                        } else {
                            this.SIZE_DIGEST = 64;
                            this.SIZE_DIGEST_UINT = 8;
                            this.ShakeBitStrength = 256;
                            i27 = 512;
                        }
                        this.Sha3BitStrength = i27;
                    }
                    this.sha3Digest = new SHA3Digest(this.Sha3BitStrength);
                    int i62 = this.NB_COEFS_HFEPOLY + (this.NB_MONOMIAL_VINEGAR - 1) + (i3 * i37);
                    int i63 = this.NB_WORD_GFqn;
                    this.NB_UINT_HFEVPOLY = i62 * i63;
                    this.MLv_GFqn_SIZE = i53 * i63;
                    if (i6 > 34 || (i2 > 196 && i6 < 256)) {
                        if (i6 == 17) {
                            i28 = 4;
                        } else {
                            i28 = 6;
                        }
                        this.II = i28;
                        int i64 = this.II;
                        i29 = 1 << i64;
                        this.POW_II = i29;
                        int i65 = i6 >>> i64;
                        if (i6 % i29 != 0) {
                            i30 = 1;
                        } else {
                            i30 = 0;
                        }
                        int i66 = i65 + i30;
                        this.KP = i66;
                        this.KX = i6 - i66;
                    }
                    if (i19 != 0) {
                        if (i2 == 544 || i18 != 128) {
                            rem544_pentanomial_gf2x = new Rem_GF2n.REM544_PENTANOMIAL_GF2X(i17, i19, i18, i40, i9, i20, i21, i22, jMaskUINT);
                        } else {
                            rem544_pentanomial_gf2x = new Rem_GF2n.REM544_PENTANOMIAL_K3_IS_128_GF2X(i17, i19, i40, i9, i20, i21, jMaskUINT);
                        }
                        this.rem = rem544_pentanomial_gf2x;
                    } else {
                        if (i2 <= 256 && i2 < 289 && i18 > 32 && i18 < 64) {
                            rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM288_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                        } else if (i2 == 354) {
                            rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                        } else if (i2 == 358) {
                            rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED358_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                        } else if (i2 == 402) {
                            rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM402_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                        } else if (i44 == 6) {
                            rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM192_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                        } else if (i44 == 9) {
                            rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM288_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                        } else if (i44 == 12) {
                            rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                        }
                        this.rem = rem192_specialized_trinomial_gf2x;
                    }
                    this.Buffer_NB_WORD_MUL = new Pointer(i44);
                    this.Buffer_NB_WORD_GFqn = new Pointer(this.NB_WORD_GFqn);
                    i31 = 31;
                    this.HFEn_1rightmost = 31;
                    while (true) {
                        i32 = this.HFEn_1rightmost;
                        if ((i43 >>> i32) == 0) {
                            break;
                        } else {
                            this.HFEn_1rightmost = i32 - 1;
                        }
                    }
                    i33 = (i2 + 1) >>> 1;
                    while (true) {
                        this.HFEn1h_rightmost = i31;
                        i34 = this.HFEn1h_rightmost;
                        if ((i33 >>> i34) == 0) {
                            this.HFEn1h_rightmost = i34 - 1;
                            return;
                        }
                        i31 = i34 - 1;
                    }
                } else {
                    i36 = 57;
                }
                i18 = i36;
                i17 = 0;
            } else {
                i17 = 0;
                i18 = 13;
            }
            i19 = 0;
            if (i19 != 0) {
                i20 = 64 - i17;
                i21 = 64 - i19;
            } else {
                i20 = 0;
                i21 = 0;
            }
            i22 = 64 - (i18 & 63);
            i23 = i6 & 1;
            if (i23 == 0) {
                this.ENABLED_REMOVE_ODD_DEGREE = true;
                i35 = (1 << i7) + 1;
                this.HFE_odd_degree = i35;
                if (i23 == 0) {
                    throw new IllegalArgumentException("HFEDeg is odd, so to remove the leading term would decrease the degree.");
                }
                if (i35 <= i6) {
                    throw new IllegalArgumentException("It is useless to remove 0 term.");
                }
                if (i35 > 1) {
                    throw new IllegalArgumentException("The case where the term X^3 is removing is not implemented.");
                }
                this.NB_COEFS_HFEPOLY = i8 + 2 + (((i7 - 1) * i7) >>> 1) + i7;
                i24 = 0;
            } else {
                i24 = 0;
                this.ENABLED_REMOVE_ODD_DEGREE = false;
                this.NB_COEFS_HFEPOLY = i8 + 2 + ((i7 * i37) >>> 1);
            }
            if (i10 != 0) {
                i25 = 1;
            } else {
                i25 = i24;
            }
            int i510 = i46 + i25;
            this.NB_WORD_GF2m = i510;
            int i67 = i52 - i510;
            if (i10 != 0) {
                i26 = 1;
            } else {
                i26 = i24;
            }
            int i68 = i67 + i26;
            this.NB_WORD_GF2nvm = i68;
            this.SIZE_SIGN_UNCOMPRESSED = i52 + ((i5 - 1) * i68);
            if (i <= 128) {
                this.SIZE_DIGEST = 32;
                this.SIZE_DIGEST_UINT = 4;
                this.ShakeBitStrength = 128;
                this.Sha3BitStrength = 256;
            } else {
                if (i <= 192) {
                    this.SIZE_DIGEST = 48;
                    this.SIZE_DIGEST_UINT = 6;
                    this.ShakeBitStrength = 256;
                    i27 = MLKEMEngine.KyberPolyBytes;
                } else {
                    this.SIZE_DIGEST = 64;
                    this.SIZE_DIGEST_UINT = 8;
                    this.ShakeBitStrength = 256;
                    i27 = 512;
                }
                this.Sha3BitStrength = i27;
            }
            this.sha3Digest = new SHA3Digest(this.Sha3BitStrength);
            int i69 = this.NB_COEFS_HFEPOLY + (this.NB_MONOMIAL_VINEGAR - 1) + (i3 * i37);
            int i610 = this.NB_WORD_GFqn;
            this.NB_UINT_HFEVPOLY = i69 * i610;
            this.MLv_GFqn_SIZE = i53 * i610;
            if (i6 > 34) {
                if (i6 == 17) {
                    i28 = 4;
                } else {
                    i28 = 6;
                }
                this.II = i28;
                int i611 = this.II;
                i29 = 1 << i611;
                this.POW_II = i29;
                int i612 = i6 >>> i611;
                if (i6 % i29 != 0) {
                    i30 = 1;
                } else {
                    i30 = 0;
                }
                int i613 = i612 + i30;
                this.KP = i613;
                this.KX = i6 - i613;
            } else {
                if (i6 == 17) {
                    i28 = 4;
                } else {
                    i28 = 6;
                }
                this.II = i28;
                int i614 = this.II;
                i29 = 1 << i614;
                this.POW_II = i29;
                int i615 = i6 >>> i614;
                if (i6 % i29 != 0) {
                    i30 = 1;
                } else {
                    i30 = 0;
                }
                int i616 = i615 + i30;
                this.KP = i616;
                this.KX = i6 - i616;
            }
            if (i19 != 0) {
                if (i2 == 544) {
                    rem544_pentanomial_gf2x = new Rem_GF2n.REM544_PENTANOMIAL_GF2X(i17, i19, i18, i40, i9, i20, i21, i22, jMaskUINT);
                } else {
                    rem544_pentanomial_gf2x = new Rem_GF2n.REM544_PENTANOMIAL_GF2X(i17, i19, i18, i40, i9, i20, i21, i22, jMaskUINT);
                }
                this.rem = rem544_pentanomial_gf2x;
            } else if (i2 <= 256) {
                if (i2 == 354) {
                    rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                } else if (i2 == 358) {
                    rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED358_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                } else if (i2 == 402) {
                    rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM402_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                } else if (i44 == 6) {
                    rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM192_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                } else if (i44 == 9) {
                    rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM288_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                } else if (i44 == 12) {
                    rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                }
                this.rem = rem192_specialized_trinomial_gf2x;
            } else {
                if (i2 == 354) {
                    rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                } else if (i2 == 358) {
                    rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED358_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                } else if (i2 == 402) {
                    rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM402_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                } else if (i44 == 6) {
                    rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM192_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                } else if (i44 == 9) {
                    rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM288_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                } else if (i44 == 12) {
                    rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                }
                this.rem = rem192_specialized_trinomial_gf2x;
            }
            this.Buffer_NB_WORD_MUL = new Pointer(i44);
            this.Buffer_NB_WORD_GFqn = new Pointer(this.NB_WORD_GFqn);
            i31 = 31;
            this.HFEn_1rightmost = 31;
            while (true) {
                i32 = this.HFEn_1rightmost;
                if ((i43 >>> i32) == 0) {
                    break;
                    break;
                }
                this.HFEn_1rightmost = i32 - 1;
            }
            i33 = (i2 + 1) >>> 1;
            while (true) {
                this.HFEn1h_rightmost = i31;
                i34 = this.HFEn1h_rightmost;
                if ((i33 >>> i34) == 0) {
                    this.HFEn1h_rightmost = i34 - 1;
                    return;
                }
                i31 = i34 - 1;
            }
        } else {
            mul6 = new Mul_GF2x.Mul12();
        }
        this.mul = mul6;
        i9 = 64 - i40;
        int i410 = i2 - i4;
        this.HFEm = i410;
        int i411 = i410 >>> 6;
        this.HFEmq = i411;
        i10 = i410 & 63;
        this.HFEmr = i10;
        int i412 = i3 >>> 6;
        this.HFEvq = i412;
        int i413 = i3 & 63;
        this.HFEvr = i413;
        this.NB_WORD_GFqv = i413 != 0 ? i412 + 1 : i412;
        int i414 = i410 >>> 3;
        this.HFEmq8 = i414;
        i11 = i410 & 7;
        this.HFEmr8 = i11;
        if (i11 != 0) {
            i12 = 1;
        } else {
            i12 = 0;
        }
        this.NB_BYTES_GFqm = i12 + i414;
        this.NB_WORD_UNCOMP_EQ = ((((i41 + 1) * i41) >>> 1) * 64) + ((i41 + 1) * i42);
        i13 = i38 & 7;
        this.HFEnvr8 = i13;
        int i511 = i38 >>> 3;
        if (i13 != 0) {
            i14 = 1;
        } else {
            i14 = 0;
        }
        this.NB_BYTES_GFqnv = i511 + i14;
        this.VAL_BITS_M = Math.min(i4 + i3, 8 - i11);
        this.MASK_GF2m = GeMSSUtils.maskUINT(i10);
        jMaskUINT = GeMSSUtils.maskUINT(i40);
        this.MASK_GF2n = jMaskUINT;
        if (i40 != 0) {
            i15 = 1;
        } else {
            i15 = 0;
        }
        int i512 = i15 + i39;
        this.NB_WORD_GFqn = i512;
        this.LTRIANGULAR_N_SIZE = (((i39 * (i39 + 1)) >>> 1) * 64) + (i512 * i40);
        this.MATRIXn_SIZE = i2 * i512;
        if (i42 != 0) {
            i16 = 1;
        } else {
            i16 = 0;
        }
        int i513 = i16 + i41;
        this.NB_WORD_GF2nv = i513;
        this.MATRIXnv_SIZE = i38 * i513;
        this.LTRIANGULAR_NV_SIZE = (((i41 * (i41 + 1)) >>> 1) * 64) + (i42 * i513);
        int i514 = i3 + 1;
        int i515 = ((i3 * i514) >>> 1) + 1;
        this.NB_MONOMIAL_VINEGAR = i515;
        int i516 = (i38 * (i38 + 1)) >>> 1;
        int i517 = i516 + 1;
        this.NB_MONOMIAL_PK = i517;
        this.MQv_GFqn_SIZE = i515 * i512;
        this.ACCESS_last_equations8 = i414 * i517;
        this.NB_BYTES_EQUATION = (i516 + 8) >>> 3;
        int i518 = i517 & 7;
        this.HFENr8 = i518;
        int i519 = (8 - i518) & 7;
        this.HFENr8c = i519;
        this.LOST_BITS = (i11 - 1) * i519;
        this.NB_WORD_MMUL = i44;
        if (i2 != 174) {
            if (i2 != 175) {
                i36 = 16;
            } else if (i2 != 177) {
                i17 = 0;
                i18 = 8;
            } else if (i2 != 178) {
                i17 = 0;
                i18 = 31;
            } else if (i2 != 265) {
                i36 = 42;
            } else if (i2 != 266) {
                i36 = 47;
            } else if (i2 != 268) {
                i36 = 25;
            } else if (i2 != 354) {
                i36 = 99;
            } else if (i2 != 358) {
                if (i2 != 364) {
                    i17 = 0;
                    i18 = 9;
                } else if (i2 != 366) {
                    i36 = 29;
                } else if (i2 != 402) {
                    i36 = 171;
                } else if (i2 != 537) {
                    i18 = 10;
                    i19 = 2;
                    i17 = 1;
                } else if (i2 != 544) {
                    i18 = 128;
                    i17 = 1;
                    i19 = 3;
                } else if (i2 != 270) {
                    i36 = 53;
                } else {
                    if (i2 == 271) {
                        throw new IllegalArgumentException("error: need to add support for HFEn=" + i2);
                    }
                    i36 = 58;
                }
                if (i19 != 0) {
                    i20 = 64 - i17;
                    i21 = 64 - i19;
                } else {
                    i20 = 0;
                    i21 = 0;
                }
                i22 = 64 - (i18 & 63);
                i23 = i6 & 1;
                if (i23 == 0) {
                    this.ENABLED_REMOVE_ODD_DEGREE = true;
                    i35 = (1 << i7) + 1;
                    this.HFE_odd_degree = i35;
                    if (i23 == 0) {
                        throw new IllegalArgumentException("HFEDeg is odd, so to remove the leading term would decrease the degree.");
                    }
                    if (i35 <= i6) {
                        throw new IllegalArgumentException("It is useless to remove 0 term.");
                    }
                    if (i35 > 1) {
                        throw new IllegalArgumentException("The case where the term X^3 is removing is not implemented.");
                    }
                    this.NB_COEFS_HFEPOLY = i8 + 2 + (((i7 - 1) * i7) >>> 1) + i7;
                    i24 = 0;
                } else {
                    i24 = 0;
                    this.ENABLED_REMOVE_ODD_DEGREE = false;
                    this.NB_COEFS_HFEPOLY = i8 + 2 + ((i7 * i37) >>> 1);
                }
                if (i10 != 0) {
                    i25 = 1;
                } else {
                    i25 = i24;
                }
                int i5110 = i411 + i25;
                this.NB_WORD_GF2m = i5110;
                int i617 = i513 - i5110;
                if (i10 != 0) {
                    i26 = 1;
                } else {
                    i26 = i24;
                }
                int i618 = i617 + i26;
                this.NB_WORD_GF2nvm = i618;
                this.SIZE_SIGN_UNCOMPRESSED = i513 + ((i5 - 1) * i618);
                if (i <= 128) {
                    this.SIZE_DIGEST = 32;
                    this.SIZE_DIGEST_UINT = 4;
                    this.ShakeBitStrength = 128;
                    this.Sha3BitStrength = 256;
                } else {
                    if (i <= 192) {
                        this.SIZE_DIGEST = 48;
                        this.SIZE_DIGEST_UINT = 6;
                        this.ShakeBitStrength = 256;
                        i27 = MLKEMEngine.KyberPolyBytes;
                    } else {
                        this.SIZE_DIGEST = 64;
                        this.SIZE_DIGEST_UINT = 8;
                        this.ShakeBitStrength = 256;
                        i27 = 512;
                    }
                    this.Sha3BitStrength = i27;
                }
                this.sha3Digest = new SHA3Digest(this.Sha3BitStrength);
                int i619 = this.NB_COEFS_HFEPOLY + (this.NB_MONOMIAL_VINEGAR - 1) + (i3 * i37);
                int i6110 = this.NB_WORD_GFqn;
                this.NB_UINT_HFEVPOLY = i619 * i6110;
                this.MLv_GFqn_SIZE = i514 * i6110;
                if (i6 > 34) {
                    if (i6 == 17) {
                        i28 = 4;
                    } else {
                        i28 = 6;
                    }
                    this.II = i28;
                    int i6111 = this.II;
                    i29 = 1 << i6111;
                    this.POW_II = i29;
                    int i6112 = i6 >>> i6111;
                    if (i6 % i29 != 0) {
                        i30 = 1;
                    } else {
                        i30 = 0;
                    }
                    int i6113 = i6112 + i30;
                    this.KP = i6113;
                    this.KX = i6 - i6113;
                } else {
                    if (i6 == 17) {
                        i28 = 4;
                    } else {
                        i28 = 6;
                    }
                    this.II = i28;
                    int i6114 = this.II;
                    i29 = 1 << i6114;
                    this.POW_II = i29;
                    int i6115 = i6 >>> i6114;
                    if (i6 % i29 != 0) {
                        i30 = 1;
                    } else {
                        i30 = 0;
                    }
                    int i6116 = i6115 + i30;
                    this.KP = i6116;
                    this.KX = i6 - i6116;
                }
                if (i19 != 0) {
                    if (i2 == 544) {
                        rem544_pentanomial_gf2x = new Rem_GF2n.REM544_PENTANOMIAL_GF2X(i17, i19, i18, i40, i9, i20, i21, i22, jMaskUINT);
                    } else {
                        rem544_pentanomial_gf2x = new Rem_GF2n.REM544_PENTANOMIAL_GF2X(i17, i19, i18, i40, i9, i20, i21, i22, jMaskUINT);
                    }
                    this.rem = rem544_pentanomial_gf2x;
                } else if (i2 <= 256) {
                    if (i2 == 354) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                    } else if (i2 == 358) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED358_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                    } else if (i2 == 402) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM402_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                    } else if (i44 == 6) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM192_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                    } else if (i44 == 9) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM288_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                    } else if (i44 == 12) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                    }
                    this.rem = rem192_specialized_trinomial_gf2x;
                } else {
                    if (i2 == 354) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                    } else if (i2 == 358) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED358_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                    } else if (i2 == 402) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM402_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                    } else if (i44 == 6) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM192_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                    } else if (i44 == 9) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM288_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                    } else if (i44 == 12) {
                        rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
                    }
                    this.rem = rem192_specialized_trinomial_gf2x;
                }
                this.Buffer_NB_WORD_MUL = new Pointer(i44);
                this.Buffer_NB_WORD_GFqn = new Pointer(this.NB_WORD_GFqn);
                i31 = 31;
                this.HFEn_1rightmost = 31;
                while (true) {
                    i32 = this.HFEn_1rightmost;
                    if ((i43 >>> i32) == 0) {
                        break;
                        break;
                    }
                    this.HFEn_1rightmost = i32 - 1;
                }
                i33 = (i2 + 1) >>> 1;
                while (true) {
                    this.HFEn1h_rightmost = i31;
                    i34 = this.HFEn1h_rightmost;
                    if ((i33 >>> i34) == 0) {
                        this.HFEn1h_rightmost = i34 - 1;
                        return;
                    }
                    i31 = i34 - 1;
                }
            } else {
                i36 = 57;
            }
            i18 = i36;
            i17 = 0;
        } else {
            i17 = 0;
            i18 = 13;
        }
        i19 = 0;
        if (i19 != 0) {
            i20 = 64 - i17;
            i21 = 64 - i19;
        } else {
            i20 = 0;
            i21 = 0;
        }
        i22 = 64 - (i18 & 63);
        i23 = i6 & 1;
        if (i23 == 0) {
            this.ENABLED_REMOVE_ODD_DEGREE = true;
            i35 = (1 << i7) + 1;
            this.HFE_odd_degree = i35;
            if (i23 == 0) {
                throw new IllegalArgumentException("HFEDeg is odd, so to remove the leading term would decrease the degree.");
            }
            if (i35 <= i6) {
                throw new IllegalArgumentException("It is useless to remove 0 term.");
            }
            if (i35 > 1) {
                throw new IllegalArgumentException("The case where the term X^3 is removing is not implemented.");
            }
            this.NB_COEFS_HFEPOLY = i8 + 2 + (((i7 - 1) * i7) >>> 1) + i7;
            i24 = 0;
        } else {
            i24 = 0;
            this.ENABLED_REMOVE_ODD_DEGREE = false;
            this.NB_COEFS_HFEPOLY = i8 + 2 + ((i7 * i37) >>> 1);
        }
        if (i10 != 0) {
            i25 = 1;
        } else {
            i25 = i24;
        }
        int i5111 = i411 + i25;
        this.NB_WORD_GF2m = i5111;
        int i6117 = i513 - i5111;
        if (i10 != 0) {
            i26 = 1;
        } else {
            i26 = i24;
        }
        int i6118 = i6117 + i26;
        this.NB_WORD_GF2nvm = i6118;
        this.SIZE_SIGN_UNCOMPRESSED = i513 + ((i5 - 1) * i6118);
        if (i <= 128) {
            this.SIZE_DIGEST = 32;
            this.SIZE_DIGEST_UINT = 4;
            this.ShakeBitStrength = 128;
            this.Sha3BitStrength = 256;
        } else {
            if (i <= 192) {
                this.SIZE_DIGEST = 48;
                this.SIZE_DIGEST_UINT = 6;
                this.ShakeBitStrength = 256;
                i27 = MLKEMEngine.KyberPolyBytes;
            } else {
                this.SIZE_DIGEST = 64;
                this.SIZE_DIGEST_UINT = 8;
                this.ShakeBitStrength = 256;
                i27 = 512;
            }
            this.Sha3BitStrength = i27;
        }
        this.sha3Digest = new SHA3Digest(this.Sha3BitStrength);
        int i6119 = this.NB_COEFS_HFEPOLY + (this.NB_MONOMIAL_VINEGAR - 1) + (i3 * i37);
        int i61110 = this.NB_WORD_GFqn;
        this.NB_UINT_HFEVPOLY = i6119 * i61110;
        this.MLv_GFqn_SIZE = i514 * i61110;
        if (i6 > 34) {
            if (i6 == 17) {
                i28 = 4;
            } else {
                i28 = 6;
            }
            this.II = i28;
            int i61111 = this.II;
            i29 = 1 << i61111;
            this.POW_II = i29;
            int i61112 = i6 >>> i61111;
            if (i6 % i29 != 0) {
                i30 = 1;
            } else {
                i30 = 0;
            }
            int i61113 = i61112 + i30;
            this.KP = i61113;
            this.KX = i6 - i61113;
        } else {
            if (i6 == 17) {
                i28 = 4;
            } else {
                i28 = 6;
            }
            this.II = i28;
            int i61114 = this.II;
            i29 = 1 << i61114;
            this.POW_II = i29;
            int i61115 = i6 >>> i61114;
            if (i6 % i29 != 0) {
                i30 = 1;
            } else {
                i30 = 0;
            }
            int i61116 = i61115 + i30;
            this.KP = i61116;
            this.KX = i6 - i61116;
        }
        if (i19 != 0) {
            if (i2 == 544) {
                rem544_pentanomial_gf2x = new Rem_GF2n.REM544_PENTANOMIAL_GF2X(i17, i19, i18, i40, i9, i20, i21, i22, jMaskUINT);
            } else {
                rem544_pentanomial_gf2x = new Rem_GF2n.REM544_PENTANOMIAL_GF2X(i17, i19, i18, i40, i9, i20, i21, i22, jMaskUINT);
            }
            this.rem = rem544_pentanomial_gf2x;
        } else if (i2 <= 256) {
            if (i2 == 354) {
                rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
            } else if (i2 == 358) {
                rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED358_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
            } else if (i2 == 402) {
                rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM402_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
            } else if (i44 == 6) {
                rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM192_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
            } else if (i44 == 9) {
                rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM288_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
            } else if (i44 == 12) {
                rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
            }
            this.rem = rem192_specialized_trinomial_gf2x;
        } else {
            if (i2 == 354) {
                rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
            } else if (i2 == 358) {
                rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_SPECIALIZED358_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
            } else if (i2 == 402) {
                rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM402_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
            } else if (i44 == 6) {
                rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM192_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
            } else if (i44 == 9) {
                rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM288_SPECIALIZED_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
            } else if (i44 == 12) {
                rem192_specialized_trinomial_gf2x = new Rem_GF2n.REM384_TRINOMIAL_GF2X(i18, i40, i9, i22, jMaskUINT);
            }
            this.rem = rem192_specialized_trinomial_gf2x;
        }
        this.Buffer_NB_WORD_MUL = new Pointer(i44);
        this.Buffer_NB_WORD_GFqn = new Pointer(this.NB_WORD_GFqn);
        i31 = 31;
        this.HFEn_1rightmost = 31;
        while (true) {
            i32 = this.HFEn_1rightmost;
            if ((i43 >>> i32) == 0) {
                break;
                break;
            }
            this.HFEn_1rightmost = i32 - 1;
        }
        i33 = (i2 + 1) >>> 1;
        while (true) {
            this.HFEn1h_rightmost = i31;
            i34 = this.HFEn1h_rightmost;
            if ((i33 >>> i34) == 0) {
                this.HFEn1h_rightmost = i34 - 1;
                return;
            }
            i31 = i34 - 1;
        }
    }

    private void CMP_AND_SWAP_CST_TIME(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        long jCMP_LT_UINT = 0;
        long jORBITS_UINT = 0;
        long j = 0;
        for (int i = this.NB_WORD_GFqn - 1; i > 0; i--) {
            jORBITS_UINT |= GeMSSUtils.ORBITS_UINT(pointer2.get(i) ^ pointer.get(i));
            j += jORBITS_UINT;
        }
        int i2 = 0;
        while (true) {
            int i3 = this.NB_WORD_GFqn;
            if (i2 >= i3) {
                pointer3.setRangeFromXorAndMask_xor(pointer, pointer2, -jCMP_LT_UINT, i3);
                return;
            } else {
                jCMP_LT_UINT |= (-GeMSSUtils.NORBITS_UINT(((long) i2) ^ j)) & GeMSSUtils.CMP_LT_UINT(pointer2.get(i2), pointer.get(i2));
                i2++;
            }
        }
    }

    private void LOOPIR(Pointer pointer, Pointer pointer2, Pointer pointer3, int i, int i2, int i3, int i4, boolean z) {
        for (int i5 = 0; i5 < i; i5++) {
            Pointer pointer4 = new Pointer(pointer3);
            int i6 = 1;
            while (i6 <= i2) {
                LOOPJR(pointer, pointer2, pointer4, 64, i4, i6);
                i6++;
            }
            if (z) {
                LOOPJR(pointer, pointer2, pointer4, i3, i4, i6);
            }
            pointer2.move(i4);
        }
    }

    private void LOOPIR_INIT(Pointer pointer, Pointer pointer2, Pointer pointer3, Pointer pointer4, int i, int i2) {
        while (i < i2) {
            pointer.setRangeClear(0, this.NB_WORD_GFqn);
            pointer2.changeIndex(pointer3);
            LOOPK_COMPLETE(pointer, pointer4, pointer2, 0, this.HFEnvq);
            pointer4.move(this.NB_WORD_GF2nv);
            i++;
        }
    }

    private void LOOPIR_LOOPK_COMPLETE(Pointer pointer, Pointer pointer2, Pointer pointer3, int i, int i2) {
        while (i < i2) {
            LOOPK_COMPLETE(pointer, pointer2, pointer3, 0, this.HFEnvq);
            i++;
        }
    }

    private void LOOPJR(Pointer pointer, Pointer pointer2, Pointer pointer3, int i, int i2, int i3) {
        int iMin = Math.min(i2, i3);
        pointer.set(0L);
        for (int i4 = 0; i4 < i; i4++) {
            pointer.setXor(GeMSSUtils.XORBITS_UINT(pointer2.getDotProduct(0, pointer3, 0, iMin)) << i4);
            pointer3.move(i3);
        }
        pointer.moveIncremental();
    }

    private long LOOPJR_NOCST_64(Pointer pointer, PointerUnion pointerUnion, int i, int i2, long j, int i3, int i4) {
        while (i < i2) {
            if ((1 & j) != 0) {
                pointer.setXorRange(0, pointerUnion, 0, i4);
            }
            pointerUnion.moveNextBytes(i3);
            j >>>= 1;
            i++;
        }
        return j;
    }

    private void LOOPJR_UNROLLED_64(Pointer pointer, PointerUnion pointerUnion, int i, int i2, long j, int i3, int i4) {
        int i5 = i;
        long jLOOPJR_NOCST_64 = j;
        while (i5 < i2 - 3) {
            jLOOPJR_NOCST_64 = LOOPJR_NOCST_64(pointer, pointerUnion, 0, 4, jLOOPJR_NOCST_64, i3, i4);
            i5 += 4;
        }
        LOOPJR_NOCST_64(pointer, pointerUnion, i5, i2, jLOOPJR_NOCST_64, i3, i4);
    }

    private void LOOPKR(Pointer pointer, Pointer pointer2, long j, int i, int i2) {
        while (i < i2) {
            pointer2.setXorRangeAndMaskMove(pointer, this.NB_WORD_GFqn, -(1 & j));
            j >>>= 1;
            i++;
        }
    }

    private void LOOPK_COMPLETE(Pointer pointer, Pointer pointer2, Pointer pointer3, int i, int i2) {
        while (i < i2) {
            LOOPKR(pointer3, pointer, pointer2.get(i), 0, 64);
            i++;
        }
        if (this.HFEnvr != 0) {
            LOOPKR(pointer3, pointer, pointer2.get(i2), 0, this.HFEnvr);
        }
        pointer.move(this.NB_WORD_GFqn);
    }

    private int chooseRootHFE_gf2nx(Pointer pointer, SecretKeyHFE.complete_sparse_monic_gf2nx complete_sparse_monic_gf2nxVar, Pointer pointer2) {
        Pointer pointer3 = new Pointer(this.SIZE_DIGEST_UINT);
        Pointer pointer4 = new Pointer(((this.HFEDeg << 1) - 1) * this.NB_WORD_GFqn);
        Pointer pointer5 = new Pointer((this.HFEDeg + 1) * this.NB_WORD_GFqn);
        Pointer pointer6 = new Pointer(this.NB_WORD_GFqn);
        pointer6.setRangeFromXor(complete_sparse_monic_gf2nxVar.poly, pointer2, this.NB_WORD_GFqn);
        int i = this.HFEDeg;
        if (i <= 34 || (this.HFEn > 196 && i < 256)) {
            frobeniusMap_multisqr_HFE_gf2nx(pointer4, complete_sparse_monic_gf2nxVar, pointer6);
        } else {
            int i2 = 2 << this.HFEDegI;
            pointer4.set(this.NB_WORD_GFqn * i2, 1L);
            divsqr_r_HFE_cstdeg_gf2nx(pointer4, i2, i2, this.HFEDeg, complete_sparse_monic_gf2nxVar, pointer6);
            for_sqr_divsqr(pointer4, this.HFEDegI + 1, this.HFEn, complete_sparse_monic_gf2nxVar, pointer6);
        }
        pointer4.setXor(this.NB_WORD_GFqn, 1L);
        int index = pointer5.getIndex();
        pointer5.copyFrom(complete_sparse_monic_gf2nxVar.poly, this.NB_WORD_GFqn);
        for_copy_move(pointer5, complete_sparse_monic_gf2nxVar);
        pointer5.changeIndex(index);
        pointer5.set(this.HFEDeg * this.NB_WORD_GFqn, 1L);
        pointer5.setXorRange(pointer2, this.NB_WORD_GFqn);
        int iGcd_gf2nx = gcd_gf2nx(pointer5, this.HFEDeg, pointer4, pointer4.getD_for_not0_or_plus(this.NB_WORD_GFqn, this.HFEDeg - 1));
        if (this.buffer != 0) {
            pointer4.swap(pointer5);
        }
        if (pointer4.is0_gf2n(0, this.NB_WORD_GFqn) == 0) {
            return 0;
        }
        convMonic_gf2nx(pointer5, iGcd_gf2nx);
        Pointer pointer7 = new Pointer(this.NB_WORD_GFqn * iGcd_gf2nx);
        findRootsSplit_gf2nx(pointer7, pointer5, iGcd_gf2nx);
        if (iGcd_gf2nx == 1) {
            pointer.copyFrom(pointer7, this.NB_WORD_GFqn);
        } else {
            fast_sort_gf2n(pointer7, iGcd_gf2nx);
            getSHA3Hash(pointer3, 0, this.Sha3BitStrength >>> 3, pointer2.toBytes(this.NB_BYTES_GFqn), 0, this.NB_BYTES_GFqn, new byte[this.Sha3BitStrength >>> 3]);
            int iRemainderUnsigned = (int) remainderUnsigned(pointer3.get(), iGcd_gf2nx);
            int i3 = this.NB_WORD_GFqn;
            pointer.copyFrom(0, pointer7, iRemainderUnsigned * i3, i3);
        }
        return iGcd_gf2nx;
    }

    private void choose_LOOPJR(Pointer pointer, PointerUnion pointerUnion, int i, long j, int i2, int i3) {
        int i4 = this.HFEnvr;
        if (i4 < 8) {
            LOOPJR_NOCST_64(pointer, pointerUnion, i, i4, j, i2, i3);
        } else {
            LOOPJR_UNROLLED_64(pointer, pointerUnion, i, i4, j, i2, i3);
        }
    }

    private long convMQ_last_uncompressL_gf2(Pointer pointer, PointerUnion pointerUnion) {
        PointerUnion pointerUnion2 = new PointerUnion(pointerUnion);
        int i = this.HFEnv - 1;
        int i2 = i >>> 6;
        int i3 = i & 63;
        int iFor_setpk2_end_move_plus = for_setpk2_end_move_plus(pointer, pointerUnion2, i2);
        if (i3 != 0) {
            iFor_setpk2_end_move_plus = setPk2Value(pointer, pointerUnion2, iFor_setpk2_end_move_plus, i2, i3 + 1);
        }
        int i4 = this.HFEnv;
        int i5 = this.LOST_BITS;
        int i6 = i4 - i5;
        int i7 = i6 >>> 6;
        int i8 = i6 & 63;
        if (i8 != 0) {
            int i9 = iFor_setpk2_end_move_plus & 63;
            if (i9 != 0) {
                int i10 = this.NB_MONOMIAL_PK;
                if (((((i10 - i5) + 7) >>> 3) & 7) != 0) {
                    int i11 = (i4 - ((64 - (((i10 - i5) - this.HFEnvr) & 63)) & 63)) >>> 6;
                    pointer.setRangePointerUnion_Check(pointerUnion2, i11, iFor_setpk2_end_move_plus);
                    pointer.set(i11, pointerUnion2.getWithCheck(i11) >>> i9);
                    if (i11 < i7) {
                        int i12 = i11 + 1;
                        long withCheck = pointerUnion2.getWithCheck(i12);
                        pointer.setXor(i11, withCheck << (64 - i9));
                        pointer.set(i12, withCheck >>> i9);
                    } else if (i8 + i9 > 64) {
                        pointer.setXor(i11, pointerUnion2.getWithCheck(i11 + 1) << (64 - i9));
                    }
                } else {
                    pointer.setRangePointerUnion(pointerUnion2, i7, i9);
                    pointer.set(i7, pointerUnion2.get(i7) >>> i9);
                    if (i8 + i9 > 64) {
                        pointer.setXor(i7, pointerUnion2.get(i7 + 1) << (64 - i9));
                    }
                }
            } else if (((((this.NB_MONOMIAL_PK - i5) + 7) >>> 3) & 7) != 0) {
                pointer.setRangePointerUnion(pointerUnion2, i7);
                pointer.set(i7, pointerUnion2.getWithCheck(i7));
            } else {
                i7++;
                pointer.setRangePointerUnion(pointerUnion2, i7);
            }
        } else if (i7 != 0) {
            int i13 = iFor_setpk2_end_move_plus & 63;
            if (i13 == 0) {
                pointer.setRangePointerUnion(pointerUnion2, i7);
            } else if (((((this.NB_MONOMIAL_PK - i5) + 7) >>> 3) & 7) != 0) {
                int i14 = i7 - 1;
                pointer.setRangePointerUnion(pointerUnion2, i14, i13);
                pointer.set(i14, pointerUnion2.get(i14) >>> i13);
                pointer.setXor(i14, pointerUnion2.getWithCheck(i7) << (64 - i13));
            } else {
                pointer.setRangePointerUnion(pointerUnion2, i7, i13);
            }
        }
        return pointerUnion.get() & 1;
    }

    private long convMQ_uncompressL_gf2(Pointer pointer, PointerUnion pointerUnion) {
        PointerUnion pointerUnion2 = new PointerUnion(pointerUnion);
        int iFor_setpk2_end_move_plus = for_setpk2_end_move_plus(pointer, pointerUnion2, this.HFEnvq);
        int i = this.HFEnvr;
        if (i != 0) {
            setPk2Value(pointer, pointerUnion2, iFor_setpk2_end_move_plus, this.HFEnvq, i + 1);
        }
        return pointerUnion.get() & 1;
    }

    private void convMonic_gf2nx(Pointer pointer, int i) {
        Pointer pointer2 = new Pointer(this.NB_WORD_GFqn);
        int index = pointer.getIndex();
        pointer.move(this.NB_WORD_GFqn * i);
        inv_gf2n(pointer2, pointer, 0);
        pointer.set1_gf2n(0, this.NB_WORD_GFqn);
        while (true) {
            i--;
            if (i == -1) {
                pointer.changeIndex(index);
                return;
            } else {
                pointer.move(-this.NB_WORD_GFqn);
                mul_gf2n(pointer, pointer, pointer2);
            }
        }
    }

    private void copy_for_casct(Pointer pointer, Pointer pointer2, Pointer pointer3, Pointer pointer4, Pointer pointer5, int i, int i2) {
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        while (i > 1) {
            pointer4.changeIndex(pointer3, (i2 + i) * this.NB_WORD_GFqn);
            CMP_AND_SWAP_CST_TIME(pointer, pointer4, pointer5);
            i >>>= 1;
        }
    }

    private void copy_move_matrix_move(Pointer pointer, Pointer pointer2, int i) {
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        pointer2.move(this.NB_WORD_GFqn);
        pointer.setXorMatrix(pointer2, this.NB_WORD_GFqn, i);
        pointer2.move(this.NB_WORD_GFqn * (this.HFEv + 1));
    }

    private void div_q_monic_gf2nx(Pointer pointer, int i, Pointer pointer2, int i2) {
        Pointer pointer3 = new Pointer();
        Pointer pointer4 = new Pointer();
        while (i >= i2) {
            int iSearchDegree = pointer.searchDegree(i, i2, this.NB_WORD_GFqn);
            if (iSearchDegree < i2) {
                return;
            }
            pointer3.changeIndex(pointer, this.NB_WORD_GFqn * iSearchDegree);
            int iMax = Math.max(0, (i2 << 1) - iSearchDegree);
            pointer4.changeIndex(pointer, ((iSearchDegree - i2) + iMax) * this.NB_WORD_GFqn);
            for_mul_rem_xor_move(pointer4, pointer3, pointer2, iMax, i2);
            i = iSearchDegree - 1;
        }
    }

    private void div_r_monic_cst_gf2nx(Pointer pointer, int i, Pointer pointer2, int i2) {
        Pointer pointer3 = new Pointer();
        int index = pointer.getIndex();
        pointer.move(this.NB_WORD_GFqn * i);
        while (i >= i2) {
            pointer3.changeIndex(pointer, (-i2) * this.NB_WORD_GFqn);
            for_mul_rem_xor_move(pointer3, pointer, pointer2, 0, i2);
            pointer.move(-this.NB_WORD_GFqn);
            i--;
        }
        pointer.changeIndex(index);
    }

    private int div_r_monic_gf2nx(Pointer pointer, int i, Pointer pointer2, int i2) {
        Pointer pointer3 = new Pointer();
        Pointer pointer4 = new Pointer();
        while (i >= i2) {
            i = pointer.searchDegree(i, i2, this.NB_WORD_GFqn);
            if (i < i2) {
                break;
            }
            pointer3.changeIndex(pointer, this.NB_WORD_GFqn * i);
            pointer4.changeIndex(pointer3, (-i2) * this.NB_WORD_GFqn);
            for_mul_rem_xor_move(pointer4, pointer3, pointer2, 0, i2);
            i--;
        }
        if (i == -1) {
            i++;
        }
        return pointer.searchDegree(i, 1, this.NB_WORD_GFqn);
    }

    private void divsqr_r_HFE_cstdeg_gf2nx(Pointer pointer, int i, int i2, int i3, SecretKeyHFE.complete_sparse_monic_gf2nx complete_sparse_monic_gf2nxVar, Pointer pointer2) {
        Pointer pointer3 = new Pointer(pointer, i * this.NB_WORD_GFqn);
        Pointer pointer4 = new Pointer();
        while (i2 >= i3) {
            pointer4.changeIndex(pointer3, (-this.HFEDeg) * this.NB_WORD_GFqn);
            mul_rem_xorrange(pointer4, pointer3, pointer2);
            for (int i4 = 1; i4 < this.NB_COEFS_HFEPOLY; i4++) {
                pointer4.move(complete_sparse_monic_gf2nxVar.L[i4]);
                mul_rem_xorrange(pointer4, pointer3, complete_sparse_monic_gf2nxVar.poly, this.NB_WORD_GFqn * i4);
            }
            pointer3.move(-this.NB_WORD_GFqn);
            i2--;
        }
    }

    private void dotProduct_gf2n(Pointer pointer, Pointer pointer2, Pointer pointer3, int i) {
        Pointer pointer4 = new Pointer(this.NB_WORD_MUL);
        int index = pointer2.getIndex();
        int index2 = pointer3.getIndex();
        mul_move(pointer4, pointer2, pointer3);
        for_mul_xorrange_move(pointer4, pointer2, pointer3, i - 1);
        rem_gf2n(pointer, 0, pointer4);
        pointer2.changeIndex(index);
        pointer3.changeIndex(index2);
    }

    private void dotproduct_move_move(Pointer pointer, Pointer pointer2, Pointer pointer3, int i) {
        dotProduct_gf2n(pointer, pointer3, pointer2, i);
        pointer.move(this.NB_WORD_GFqn);
        pointer2.move((i + this.HFEv + 1) * this.NB_WORD_GFqn);
    }

    private void evalMQShybrid8_uncomp_nocst_gf2_m(Pointer pointer, Pointer pointer2, PointerUnion pointerUnion, PointerUnion pointerUnion2) {
        PointerUnion pointerUnion3 = new PointerUnion(pointerUnion2);
        evalMQSnocst8_quo_gf2(pointer, pointer2, pointerUnion);
        if (this.HFEmr < 8) {
            pointer.set(this.HFEmq, 0L);
        }
        for (int i = this.HFEmr - this.HFEmr8; i < this.HFEmr; i++) {
            pointer.setXor(this.HFEmq, evalMQnocst_unrolled_no_simd_gf2(pointer2, pointerUnion3) << i);
            pointerUnion3.move(this.NB_WORD_UNCOMP_EQ);
        }
    }

    private void evalMQSnocst8_quo_gf2(Pointer pointer, Pointer pointer2, PointerUnion pointerUnion) {
        int i;
        int i2;
        int i3;
        int i4;
        PointerUnion pointerUnion2;
        int i5;
        int i6 = this.HFEnv;
        int i7 = this.HFEm;
        if ((i7 >>> 3) != 0) {
            i7 = (i7 >>> 3) << 3;
        }
        int i8 = i7;
        int i9 = (i8 & 7) != 0 ? (i8 >>> 3) + 1 : i8 >>> 3;
        int i10 = (i9 >>> 3) + ((i9 & 7) != 0 ? 1 : 0);
        PointerUnion pointerUnion3 = new PointerUnion(pointerUnion);
        System.arraycopy(pointerUnion3.getArray(), 0, pointer.getArray(), pointer.getIndex(), i10);
        pointerUnion3.moveNextBytes(i9);
        int i11 = 0;
        while (true) {
            i = this.HFEnvq;
            if (i11 >= i) {
                break;
            }
            int i12 = i6;
            long j = pointer2.get(i11);
            int i13 = 0;
            while (i13 < 64) {
                if ((j & 1) != 0) {
                    pointer.setXorRange(0, pointerUnion3, 0, i10);
                    pointerUnion3.moveNextBytes(i9);
                    i3 = i11;
                    PointerUnion pointerUnion4 = pointerUnion3;
                    i4 = i10;
                    LOOPJR_UNROLLED_64(pointer, pointerUnion3, i13 + 1, 64, j >>> 1, i9, i10);
                    int i14 = i3 + 1;
                    while (true) {
                        i5 = this.HFEnvq;
                        if (i14 >= i5) {
                            break;
                        }
                        LOOPJR_UNROLLED_64(pointer, pointerUnion4, 0, 64, pointer2.get(i14), i9, i4);
                        i14++;
                    }
                    if (this.HFEnvr != 0) {
                        choose_LOOPJR(pointer, pointerUnion4, 0, pointer2.get(i5), i9, i4);
                    }
                    pointerUnion2 = pointerUnion4;
                } else {
                    i3 = i11;
                    i4 = i10;
                    pointerUnion2 = pointerUnion3;
                    pointerUnion2.moveNextBytes(i12 * i9);
                }
                j >>>= 1;
                i13++;
                i12--;
                pointerUnion3 = pointerUnion2;
                i10 = i4;
                i11 = i3;
            }
            i11++;
            i6 = i12;
            i10 = i10;
        }
        int i15 = i10;
        PointerUnion pointerUnion5 = pointerUnion3;
        if (this.HFEnvr != 0) {
            int i16 = i6;
            long j2 = pointer2.get(i);
            int i17 = 0;
            while (i17 < this.HFEnvr) {
                if ((j2 & 1) != 0) {
                    int i18 = i15;
                    pointer.setXorRange(0, pointerUnion5, 0, i18);
                    pointerUnion5.moveNextBytes(i9);
                    i2 = i18;
                    choose_LOOPJR(pointer, pointerUnion5, i17 + 1, j2 >>> 1, i9, i2);
                } else {
                    i2 = i15;
                    pointerUnion5.moveNextBytes(i16 * i9);
                }
                j2 >>>= 1;
                i17++;
                i16--;
                i15 = i2;
            }
        }
        int i19 = i15;
        int i20 = i8 & 63;
        if (i20 != 0) {
            pointer.setAnd(i19 - 1, (1 << i20) - 1);
        }
    }

    private long evalMQnocst_unrolled_no_simd_gf2(Pointer pointer, PointerUnion pointerUnion) {
        int i;
        PointerUnion pointerUnion2 = new PointerUnion(pointerUnion);
        long j = pointer.get();
        long dotProduct = 0;
        for (int i2 = 0; i2 < 64; i2++) {
            if ((1 & (j >>> i2)) != 0) {
                dotProduct ^= pointerUnion2.get(i2) & j;
            }
        }
        pointerUnion2.move(64);
        int i3 = 1;
        while (true) {
            int i4 = this.NB_WORD_GF2nv;
            if (i3 >= i4) {
                return GeMSSUtils.XORBITS_UINT(dotProduct);
            }
            int i5 = i3 + 1;
            if (i4 != i5 || (i = this.HFEnvr) == 0) {
                i = 64;
            }
            long j2 = pointer.get(i3);
            for (int i6 = 0; i6 < i; i6++) {
                if (((j2 >>> i6) & 1) != 0) {
                    dotProduct ^= pointerUnion2.getDotProduct(0, pointer, 0, i5);
                }
                pointerUnion2.move(i5);
            }
            i3 = i5;
        }
    }

    private void findRootsSplit_gf2nx(Pointer pointer, Pointer pointer2, int i) {
        int i2;
        int iGcd_gf2nx;
        int i3;
        if (i == 1) {
            pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
            return;
        }
        if ((this.HFEn & 1) != 0 && i == 2) {
            findRootsSplit2_HT_gf2nx(pointer, pointer2);
            return;
        }
        int i4 = (i << 1) - 1;
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn * i4);
        Pointer pointer4 = new Pointer(this.NB_WORD_GFqn * i);
        int i5 = i + 1;
        Pointer pointer5 = new Pointer(this.NB_WORD_GFqn * i5);
        Pointer pointer6 = new Pointer(this.NB_WORD_GFqn);
        while (true) {
            pointer3.setRangeClear(0, this.NB_WORD_GFqn * i4);
            pointer4.setRangeClear(0, this.NB_WORD_GFqn * i);
            do {
                pointer4.fillRandom(this.NB_WORD_GFqn, this.random, this.NB_BYTES_GFqn);
                pointer4.setAnd((this.NB_WORD_GFqn << 1) - 1, this.MASK_GF2n);
                i2 = this.NB_WORD_GFqn;
            } while (pointer4.is0_gf2n(i2, i2) != 0);
            pointer5.copyFrom(pointer2, this.NB_WORD_GFqn * i5);
            traceMap_gf2nx(pointer4, pointer3, pointer5, i);
            iGcd_gf2nx = gcd_gf2nx(pointer5, i, pointer4, pointer4.searchDegree(i - 1, 1, this.NB_WORD_GFqn));
            i3 = this.buffer;
            if (iGcd_gf2nx != 0 && iGcd_gf2nx != i) {
                break;
            }
        }
        if (i3 != 0) {
            pointer4.swap(pointer5);
        }
        inv_gf2n(pointer6, pointer5, this.NB_WORD_GFqn * iGcd_gf2nx);
        int i6 = this.NB_WORD_GFqn;
        pointer5.set1_gf2n(iGcd_gf2nx * i6, i6);
        for_mul(pointer5, pointer6, iGcd_gf2nx - 1);
        div_q_monic_gf2nx(pointer2, i, pointer5, iGcd_gf2nx);
        findRootsSplit_gf2nx(pointer, pointer5, iGcd_gf2nx);
        findRootsSplit_gf2nx(new Pointer(pointer, this.NB_WORD_GFqn * iGcd_gf2nx), new Pointer(pointer2, this.NB_WORD_GFqn * iGcd_gf2nx), i - iGcd_gf2nx);
    }

    private void for_and_xor_shift_incre_move(Pointer pointer, int i, int i2) {
        long j = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            pointer.setAnd(j);
            pointer.setXor(1 << i3);
            j = (j << 1) + 1;
            pointer.move(i);
        }
    }

    private void for_casct_move(Pointer pointer, Pointer pointer2, Pointer pointer3, int i, int i2) {
        int i3 = this.NB_WORD_GFqn * i2;
        int i4 = 0;
        while (i4 < i) {
            CMP_AND_SWAP_CST_TIME(pointer, pointer2, pointer3);
            pointer.move(i3);
            pointer2.move(i3);
            i4 += i2;
        }
    }

    private void for_copy_move(Pointer pointer, SecretKeyHFE.complete_sparse_monic_gf2nx complete_sparse_monic_gf2nxVar) {
        for (int i = 1; i < this.NB_COEFS_HFEPOLY; i++) {
            pointer.move(complete_sparse_monic_gf2nxVar.L[i]);
            Pointer pointer2 = complete_sparse_monic_gf2nxVar.poly;
            int i2 = this.NB_WORD_GFqn;
            pointer.copyFrom(0, pointer2, i * i2, i2);
        }
    }

    private void for_mul(Pointer pointer, Pointer pointer2, int i) {
        Pointer pointer3 = new Pointer(pointer, this.NB_WORD_GFqn * i);
        while (i != -1) {
            mul_gf2n(pointer3, pointer3, pointer2);
            pointer3.move(-this.NB_WORD_GFqn);
            i--;
        }
    }

    private void for_mul_rem_xor_move(Pointer pointer, Pointer pointer2, Pointer pointer3, int i, int i2) {
        int i3 = this.NB_WORD_GFqn * i;
        while (i < i2) {
            mul_rem_xorrange(pointer, pointer2, pointer3, i3);
            pointer.move(this.NB_WORD_GFqn);
            i++;
            i3 += this.NB_WORD_GFqn;
        }
    }

    private int for_setPK(byte[] bArr, byte[] bArr2, int i, int i2, int i3) {
        bArr[i] = (byte) (bArr2[i2] & 3);
        int pk = 2;
        for (int i4 = 2; i4 < i3; i4++) {
            int i5 = this.HFEnv;
            pk = setPK(bArr, bArr2, i4, i, i2, pk, i5 - 1, i5 - i4);
        }
        return pk;
    }

    private int for_setpk2_end_move_plus(Pointer pointer, PointerUnion pointerUnion, int i) {
        int i2 = 0;
        int i3 = 1;
        while (i2 < i) {
            int pk2Value = setPk2Value(pointer, pointerUnion, i3, i2, 64);
            setPk2_endValue(pointer, pointerUnion, pk2Value, i2);
            i2++;
            pointerUnion.move(i2);
            pointer.move(i2);
            i3 = pk2Value + (i2 << 6);
        }
        return i3;
    }

    private void for_sqr_divsqr(Pointer pointer, int i, int i2, SecretKeyHFE.complete_sparse_monic_gf2nx complete_sparse_monic_gf2nxVar, Pointer pointer2) {
        while (i < i2) {
            sqr_gf2nx(pointer, this.HFEDeg - 1);
            int i3 = this.HFEDeg;
            divsqr_r_HFE_cstdeg_gf2nx(pointer, (i3 - 1) << 1, (i3 - 1) << 1, i3, complete_sparse_monic_gf2nxVar, pointer2);
            i++;
        }
    }

    private void frobeniusMap_multisqr_HFE_gf2nx(Pointer pointer, SecretKeyHFE.complete_sparse_monic_gf2nx complete_sparse_monic_gf2nxVar, Pointer pointer2) {
        Pointer pointer3 = new Pointer();
        Pointer pointer4 = new Pointer(this.HFEDeg * this.NB_WORD_GFqn);
        Pointer pointer5 = new Pointer();
        Pointer pointer6 = new Pointer(((this.KX * this.HFEDeg) + this.POW_II) * this.NB_WORD_GFqn);
        int i = (this.POW_II * this.KP) - this.HFEDeg;
        Pointer pointer7 = new Pointer(pointer6, this.NB_WORD_GFqn * i);
        pointer7.copyFrom(pointer2, this.NB_WORD_GFqn);
        for_copy_move(pointer7, complete_sparse_monic_gf2nxVar);
        int i2 = i - 1;
        divsqr_r_HFE_cstdeg_gf2nx(pointer6, i2 + this.HFEDeg, i2, 0, complete_sparse_monic_gf2nxVar, pointer2);
        int i3 = this.KP + 1;
        while (true) {
            int i4 = this.HFEDeg;
            if (i3 >= i4) {
                break;
            }
            pointer7.changeIndex(pointer6, i4 * this.NB_WORD_GFqn);
            pointer7.setRangeClear(0, this.POW_II * this.NB_WORD_GFqn);
            int i5 = this.POW_II;
            int i6 = this.NB_WORD_GFqn;
            pointer7.copyFrom(i5 * i6, pointer6, 0, this.HFEDeg * i6);
            pointer6.changeIndex(pointer7);
            int i7 = this.POW_II;
            divsqr_r_HFE_cstdeg_gf2nx(pointer6, this.HFEDeg + (i7 - 1), i7 - 1, 0, complete_sparse_monic_gf2nxVar, pointer2);
            i3++;
        }
        pointer6.indexReset();
        int i8 = (1 << this.HFEDegI) - this.KP;
        int i9 = this.HFEDeg;
        int i10 = this.NB_WORD_GFqn;
        pointer.copyFrom(0, pointer6, i8 * i9 * i10, i9 * i10);
        int i11 = 0;
        while (true) {
            int i12 = this.HFEn;
            int i13 = this.HFEDegI;
            int i14 = this.II;
            if (i11 >= ((i12 - i13) - i14) / i14) {
                for_sqr_divsqr(pointer, 0, (i12 - i13) % i14, complete_sparse_monic_gf2nxVar, pointer2);
                return;
            }
            loop_sqr(pointer4, pointer);
            for (int i15 = 1; i15 < this.II; i15++) {
                loop_sqr(pointer4, pointer4);
            }
            pointer5.changeIndex(pointer4, this.KP * this.NB_WORD_GFqn);
            pointer7.changeIndex(pointer6);
            pointer3.changeIndex(pointer);
            for (int i16 = 0; i16 < this.HFEDeg; i16++) {
                mul_gf2n(pointer3, pointer7, pointer5);
                pointer3.move(this.NB_WORD_GFqn);
                pointer7.move(this.NB_WORD_GFqn);
            }
            int i17 = this.KP;
            while (true) {
                i17++;
                if (i17 >= this.HFEDeg) {
                    break;
                }
                pointer5.move(this.NB_WORD_GFqn);
                pointer3.changeIndex(pointer);
                for (int i18 = 0; i18 < this.HFEDeg; i18++) {
                    mul_rem_xorrange(pointer3, pointer7, pointer5);
                    pointer3.move(this.NB_WORD_GFqn);
                    pointer7.move(this.NB_WORD_GFqn);
                }
            }
            for (int i19 = 0; i19 < this.KP; i19++) {
                int i20 = this.POW_II * i19;
                int i21 = this.NB_WORD_GFqn;
                pointer.setXorRange(i20 * i21, pointer4, i19 * i21, i21);
            }
            i11++;
        }
    }

    private int gcd_gf2nx(Pointer pointer, int i, Pointer pointer2, int i2) {
        int iDiv_r_monic_gf2nx;
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        this.buffer = 0;
        Pointer pointer4 = pointer;
        Pointer pointer5 = pointer2;
        int i3 = i;
        while (i2 != 0) {
            if ((i2 << 1) > i3) {
                iDiv_r_monic_gf2nx = div_r_gf2nx(pointer4, i3, pointer5, i2);
            } else {
                inv_gf2n(pointer3, pointer5, this.NB_WORD_GFqn * i2);
                int i4 = this.NB_WORD_GFqn;
                pointer5.set1_gf2n(i2 * i4, i4);
                for_mul(pointer5, pointer3, i2 - 1);
                iDiv_r_monic_gf2nx = div_r_monic_gf2nx(pointer4, i3, pointer5, i2);
            }
            this.buffer = 1 - this.buffer;
            Pointer pointer6 = pointer4;
            pointer4 = pointer5;
            pointer5 = pointer6;
            int i5 = i2;
            i2 = iDiv_r_monic_gf2nx;
            i3 = i5;
        }
        return i3;
    }

    private void getSHA3Hash(Pointer pointer, int i, int i2, byte[] bArr, int i3, int i4, byte[] bArr2) {
        this.sha3Digest.update(bArr, i3, i4);
        this.sha3Digest.doFinal(bArr2, 0);
        pointer.fill(i, bArr2, 0, i2);
    }

    private void initListDifferences_gf2nx(int[] iArr) {
        iArr[1] = this.NB_WORD_GFqn;
        int arrayL = 2;
        int i = 0;
        while (i < this.HFEDegI) {
            if (!this.ENABLED_REMOVE_ODD_DEGREE || (1 << i) + 1 <= this.HFE_odd_degree) {
                iArr[arrayL] = this.NB_WORD_GFqn;
                arrayL = setArrayL(iArr, arrayL + 1, 0, i);
            } else {
                if (i != 0) {
                    iArr[arrayL] = this.NB_WORD_GFqn << 1;
                    arrayL++;
                }
                arrayL = setArrayL(iArr, arrayL, 1, i);
            }
            i++;
        }
        int i2 = this.HFEDegJ;
        if (i2 != 0) {
            if (!this.ENABLED_REMOVE_ODD_DEGREE || (1 << i) + 1 <= this.HFE_odd_degree) {
                iArr[arrayL] = this.NB_WORD_GFqn;
                setArrayL(iArr, arrayL + 1, 0, i2 - 1);
            } else {
                iArr[arrayL] = this.NB_WORD_GFqn << 1;
                setArrayL(iArr, arrayL + 1, 1, i2 - 1);
            }
        }
    }

    private void inv_gf2n(Pointer pointer, Pointer pointer2, int i) {
        int index = pointer2.getIndex();
        pointer2.move(i);
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        for (int i2 = this.HFEn_1rightmost - 1; i2 != -1; i2--) {
            int i3 = (this.HFEn - 1) >>> (i2 + 1);
            sqr_gf2n(pointer3, pointer);
            for (int i4 = 1; i4 < i3; i4++) {
                sqr_gf2n(pointer3, pointer3);
            }
            mul_gf2n(pointer, pointer, pointer3);
            if ((((this.HFEn - 1) >>> i2) & 1) != 0) {
                sqr_gf2n(pointer3, pointer);
                mul_gf2n(pointer, pointer2, pointer3);
            }
        }
        sqr_gf2n(pointer, pointer);
        pointer2.changeIndex(index);
    }

    private void loop_sqr(Pointer pointer, Pointer pointer2) {
        for (int i = 0; i < this.HFEDeg; i++) {
            int i2 = this.NB_WORD_GFqn;
            sqr_gf2n(pointer, i * i2, pointer2, i2 * i);
        }
    }

    private int loop_xor_loop_move_xorandmask_move(Pointer pointer, Pointer pointer2, Pointer pointer3, Pointer pointer4, int i, int i2, int i3, int i4, int i5) {
        int i6 = 0;
        int i7 = i;
        while (i6 < i3) {
            pointer.setXor(i2, 1 << i6);
            pointer2.changeIndex(pointer);
            pointer3.changeIndex(pointer4);
            for (int i8 = i7; i8 < i4; i8++) {
                pointer2.move(i5);
                pointer3.move((i8 >>> 6) + 1);
                pointer2.setXorRangeAndMask(pointer, i2 + 1, -((pointer3.get() >>> i6) & 1));
            }
            pointer.move(i5);
            pointer4.move(i2 + 1);
            i6++;
            i7++;
        }
        return i7;
    }

    private void mulMatricesLU_gf2(Pointer pointer, Pointer pointer2, Pointer pointer3, FunctionParams functionParams) {
        int i;
        int i2;
        boolean z;
        int index = pointer.getIndex();
        int iOrdinal = functionParams.ordinal();
        if (iOrdinal == 0) {
            int i3 = this.HFEnvq;
            int i4 = this.HFEnvr;
            i = i3;
            i2 = i4;
            z = i4 != 0;
        } else {
            if (iOrdinal != 2) {
                throw new IllegalArgumentException("Invalid parameter for MULMATRICESLU_GF2");
            }
            i = this.HFEnq;
            z = true;
            i2 = this.HFEnr;
        }
        Pointer pointer4 = new Pointer(pointer2);
        int i5 = 1;
        while (i5 <= i) {
            LOOPIR(pointer, pointer4, pointer3, 64, i, i2, i5, z);
            i5++;
        }
        LOOPIR(pointer, pointer4, pointer3, i2, i, i2, i5, z);
        pointer.changeIndex(index);
    }

    private void precSignHFE(SecretKeyHFE secretKeyHFE, Pointer[] pointerArr, byte[] bArr) {
        precSignHFESeed(secretKeyHFE, bArr);
        initListDifferences_gf2nx(secretKeyHFE.F_struct.L);
        Pointer pointer = new Pointer(secretKeyHFE.F_HFEv);
        Pointer pointer2 = new Pointer(this.NB_COEFS_HFEPOLY * this.NB_WORD_GFqn);
        Pointer pointer3 = new Pointer(pointer, this.MQv_GFqn_SIZE);
        pointerArr[0] = pointer3;
        pointer.changeIndex(pointer3, this.MLv_GFqn_SIZE);
        Pointer pointer4 = new Pointer(pointer2, this.NB_WORD_GFqn * 2);
        int i = 0;
        while (true) {
            int i2 = 1;
            if (i >= this.HFEDegI) {
                break;
            }
            if ((1 << i) + 1 <= this.HFE_odd_degree || !this.ENABLED_REMOVE_ODD_DEGREE) {
                i2 = 0;
            }
            int i3 = i - i2;
            pointer4.copyFrom(pointer, this.NB_WORD_GFqn * i3);
            pointer.move(this.NB_WORD_GFqn * i3);
            pointer4.move(i3 * this.NB_WORD_GFqn);
            i++;
            pointerArr[i] = new Pointer(pointer);
            pointer.move(this.MLv_GFqn_SIZE);
            pointer4.move(this.NB_WORD_GFqn);
        }
        int i4 = this.HFEDegJ;
        if (i4 != 0) {
            pointer4.copyFrom(pointer, (i4 - ((1 << i) + 1 > this.HFE_odd_degree ? 1 : 0)) * this.NB_WORD_GFqn);
        }
        secretKeyHFE.F_struct.poly = new Pointer(pointer2);
    }

    private void precSignHFESeed(SecretKeyHFE secretKeyHFE, byte[] bArr) {
        int i = this.NB_UINT_HFEVPOLY + ((this.LTRIANGULAR_NV_SIZE + this.LTRIANGULAR_N_SIZE) << 1);
        secretKeyHFE.sk_uncomp = new Pointer(this.MATRIXnv_SIZE + i + this.MATRIXn_SIZE);
        SHAKEDigest sHAKEDigest = new SHAKEDigest(this.ShakeBitStrength);
        sHAKEDigest.update(bArr, 0, this.SIZE_SEED_SK);
        int i2 = i << 3;
        byte[] bArr2 = new byte[i2];
        sHAKEDigest.doFinal(bArr2, 0, i2);
        secretKeyHFE.sk_uncomp.fill(0, bArr2, 0, i2);
        secretKeyHFE.S = new Pointer(secretKeyHFE.sk_uncomp, i);
        secretKeyHFE.T = new Pointer(secretKeyHFE.S, this.MATRIXnv_SIZE);
        secretKeyHFE.F_HFEv = new Pointer(secretKeyHFE.sk_uncomp);
        cleanMonicHFEv_gf2nx(secretKeyHFE.F_HFEv);
        Pointer pointer = new Pointer(secretKeyHFE.sk_uncomp, this.NB_UINT_HFEVPOLY);
        Pointer pointer2 = new Pointer(pointer, this.LTRIANGULAR_NV_SIZE);
        cleanLowerMatrix(pointer, FunctionParams.NV);
        cleanLowerMatrix(pointer2, FunctionParams.NV);
        mulMatricesLU_gf2(secretKeyHFE.S, pointer, pointer2, FunctionParams.NV);
        pointer.move(this.LTRIANGULAR_NV_SIZE << 1);
        pointer2.changeIndex(pointer, this.LTRIANGULAR_N_SIZE);
        cleanLowerMatrix(pointer, FunctionParams.N);
        cleanLowerMatrix(pointer2, FunctionParams.N);
        mulMatricesLU_gf2(secretKeyHFE.T, pointer, pointer2, FunctionParams.N);
    }

    private void rem_gf2n(Pointer pointer, int i, Pointer pointer2) {
        this.rem.rem_gf2n(pointer.array, i + pointer.getIndex(), pointer2.array);
    }

    private static long remainderUnsigned(long j, long j2) {
        return (j <= 0 || j2 <= 0) ? new BigInteger(1, Pack.longToBigEndian(j)).mod(new BigInteger(1, Pack.longToBigEndian(j2))).longValue() : j % j2;
    }

    private int setArrayL(int[] iArr, int i, int i2, int i3) {
        while (i2 < i3) {
            iArr[i] = this.NB_WORD_GFqn << i2;
            i2++;
            i++;
        }
        return i;
    }

    private int setPK(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, int i5, int i6) {
        while (i5 >= i6) {
            int i7 = (i4 >>> 3) + i2;
            bArr[i7] = (byte) (bArr[i7] ^ (((bArr2[(i >>> 3) + i3] >>> (i & 7)) & 1) << (i4 & 7)));
            i += i5;
            i5--;
            i4++;
        }
        this.buffer = i;
        return i4;
    }

    private int setPk2Value(Pointer pointer, PointerUnion pointerUnion, int i, int i2, int i3) {
        for (int i4 = 1; i4 < i3; i4++) {
            int i5 = i & 63;
            if (i5 != 0) {
                pointer.setRangePointerUnion(pointerUnion, i2, i5);
                pointer.set(i2, pointerUnion.get(i2) >>> i5);
                int i6 = i5 + i4;
                if (i6 > 64) {
                    pointer.setXor(i2, pointerUnion.get(i2 + 1) << (64 - i5));
                }
                if (i6 >= 64) {
                    pointerUnion.moveIncremental();
                }
            } else {
                pointer.setRangePointerUnion(pointerUnion, i2 + 1);
            }
            pointerUnion.move(i2);
            pointer.setAnd(i2, (1 << i4) - 1);
            pointer.move(i2 + 1);
            i += (i2 << 6) + i4;
        }
        return i;
    }

    private void setPk2_endValue(Pointer pointer, PointerUnion pointerUnion, int i, int i2) {
        int i3 = i & 63;
        int i4 = i2 + 1;
        if (i3 != 0) {
            pointer.setRangePointerUnion(pointerUnion, i4, i3);
        } else {
            pointer.setRangePointerUnion(pointerUnion, i4);
        }
    }

    private void special_buffer(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        int i;
        int index = pointer2.getIndex();
        pointer2.move((this.NB_WORD_GFqn * (this.HFEv + 1)) << 1);
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        pointer.move(this.NB_WORD_GFqn);
        Pointer pointer4 = new Pointer(pointer2, this.NB_WORD_GFqn * (this.HFEv + 2));
        int i2 = 2;
        while (i2 < this.SIZE_ROW - 1) {
            copy_move_matrix_move(pointer, pointer4, i2 - 1);
            i2++;
        }
        if (this.ENABLED_REMOVE_ODD_DEGREE) {
            while (i2 < this.SIZE_ROW - 1) {
                copy_move_matrix_move(pointer, pointer4, i2 - 2);
                i2++;
            }
        }
        pointer.set1_gf2n(0, this.NB_WORD_GFqn);
        pointer.setXorMatrix(pointer4, this.NB_WORD_GFqn, this.HFEDegJ);
        for (int i3 = 0; i3 < this.HFEn - 1; i3++) {
            mul_gf2n(pointer, pointer3, pointer2);
            pointer.move(this.NB_WORD_GFqn);
            pointer4.changeIndex(pointer2, this.NB_WORD_GFqn * (this.HFEv + 2));
            int i4 = 2;
            while (i4 < this.HFEDegI) {
                dotproduct_move_move(pointer, pointer4, pointer3, i4);
                i4++;
            }
            if (this.ENABLED_REMOVE_ODD_DEGREE) {
                pointer3.move(this.NB_WORD_GFqn);
                while (i4 < this.SIZE_ROW - 1) {
                    dotproduct_move_move(pointer, pointer4, pointer3, i4 - 1);
                    i4++;
                }
                pointer3.move(-this.NB_WORD_GFqn);
            }
            int i5 = this.HFEDegJ;
            if (i5 == 0) {
                pointer.copyFrom(pointer3, this.NB_WORD_GFqn);
                pointer.move(this.NB_WORD_GFqn);
                i = this.SIZE_ROW;
            } else {
                dotProduct_gf2n(pointer, pointer3, pointer4, i5);
                pointer3.move(this.HFEDegJ * this.NB_WORD_GFqn);
                pointer.setXorRange_SelfMove(pointer3, this.NB_WORD_GFqn);
                i = this.SIZE_ROW - this.HFEDegJ;
            }
            pointer3.move(i * this.NB_WORD_GFqn);
        }
        pointer.indexReset();
        pointer2.changeIndex(index);
        pointer3.indexReset();
    }

    private void sqr_gf2n(Pointer pointer, int i, Pointer pointer2, int i2) {
        this.mul.sqr_gf2x(this.Buffer_NB_WORD_MUL.array, pointer2.array, i2 + pointer2.cp);
        rem_gf2n(pointer, i, this.Buffer_NB_WORD_MUL);
    }

    private void sqr_gf2n(Pointer pointer, Pointer pointer2) {
        this.mul.sqr_gf2x(this.Buffer_NB_WORD_MUL.array, pointer2.array, pointer2.cp);
        this.rem.rem_gf2n(pointer.array, pointer.cp, this.Buffer_NB_WORD_MUL.array);
    }

    private void sqr_gf2nx(Pointer pointer, int i) {
        int i2 = this.NB_WORD_GFqn * i;
        int index = pointer.getIndex();
        pointer.move(i2);
        Pointer pointer2 = new Pointer(pointer, i2);
        for (int i3 = 0; i3 < i; i3++) {
            sqr_gf2n(pointer2, pointer);
            pointer.move(-this.NB_WORD_GFqn);
            pointer2.move(-this.NB_WORD_GFqn);
            pointer2.setRangeClear(0, this.NB_WORD_GFqn);
            pointer2.move(-this.NB_WORD_GFqn);
        }
        sqr_gf2n(pointer, pointer);
        pointer.changeIndex(index);
    }

    private void traceMap_gf2nx(Pointer pointer, Pointer pointer2, Pointer pointer3, int i) {
        int i2;
        int i3 = 1;
        while (true) {
            i2 = 1 << i3;
            if (i2 >= i) {
                break;
            }
            int i4 = this.NB_WORD_GFqn;
            sqr_gf2n(pointer, i4 << i3, pointer, i4 << (i3 - 1));
            i3++;
        }
        if (i3 < this.HFEn) {
            int i5 = this.NB_WORD_GFqn;
            sqr_gf2n(pointer2, i5 << i3, pointer, i5 << (i3 - 1));
            div_r_monic_cst_gf2nx(pointer2, i2, pointer3, i);
            pointer.setXorRange(pointer2, this.NB_WORD_GFqn * i);
            for (int i6 = i3 + 1; i6 < this.HFEn; i6++) {
                int i7 = i - 1;
                sqr_gf2nx(pointer2, i7);
                div_r_monic_cst_gf2nx(pointer2, i7 << 1, pointer3, i);
                pointer.setXorRange(pointer2, this.NB_WORD_GFqn * i);
            }
        }
    }

    private void uncompress_signHFE(Pointer pointer, byte[] bArr) {
        PointerUnion pointerUnion = new PointerUnion(pointer);
        int i = (1 << this.HFEnvr8) - 1;
        pointerUnion.fillBytes(0, bArr, 0, this.NB_BYTES_GFqnv);
        if (this.HFEnvr8 != 0) {
            pointerUnion.setAndByte(this.NB_BYTES_GFqnv - 1, i);
        }
        int i2 = this.HFEnv;
        pointerUnion.moveNextBytes((this.NB_WORD_GF2nv << 3) + (this.HFEmq8 & 7));
        for (int i3 = 1; i3 < this.NB_ITE; i3++) {
            int i4 = i2 & 7;
            int iMin = Math.min(this.HFEDELTA + this.HFEv, (8 - i4) & 7);
            if (i4 != 0) {
                pointerUnion.setXorByte(((bArr[i2 >>> 3] & UByte.MAX_VALUE) >>> i4) << this.HFEmr8);
                int i5 = iMin - this.VAL_BITS_M;
                if (i5 >= 0) {
                    pointerUnion.moveNextByte();
                }
                if (i5 > 0) {
                    int i6 = i2 + this.VAL_BITS_M;
                    pointerUnion.setXorByte((bArr[i6 >>> 3] & UByte.MAX_VALUE) >>> (i6 & 7));
                    i2 = i6 + i5;
                } else {
                    i2 += iMin;
                }
            }
            int i7 = (this.HFEDELTA + this.HFEv) - iMin;
            int i8 = (this.HFEm + iMin) & 7;
            if (i8 != 0) {
                for (int i9 = 0; i9 < ((i7 - 1) >>> 3); i9++) {
                    int i10 = i2 >>> 3;
                    pointerUnion.setXorByte((bArr[i10] & UByte.MAX_VALUE) << i8);
                    pointerUnion.moveNextByte();
                    pointerUnion.setXorByte((bArr[i10] & UByte.MAX_VALUE) >>> (8 - i8));
                    i2 += 8;
                }
                int i11 = i2 >>> 3;
                pointerUnion.setXorByte((bArr[i11] & UByte.MAX_VALUE) << i8);
                pointerUnion.moveNextByte();
                int i12 = ((i7 + 7) & 7) + 1;
                int i13 = 8 - i8;
                if (i12 > i13) {
                    pointerUnion.setByte((bArr[i11] & UByte.MAX_VALUE) >>> i13);
                    pointerUnion.moveNextByte();
                }
                i2 += i12;
            } else {
                for (int i14 = 0; i14 < ((i7 + 7) >>> 3); i14++) {
                    pointerUnion.setByte(bArr[i2 >>> 3]);
                    i2 += 8;
                    pointerUnion.moveNextByte();
                }
                i2 -= (8 - (i7 & 7)) & 7;
            }
            if (this.HFEnvr8 != 0) {
                pointerUnion.setAndByte(-1, i);
            }
            pointerUnion.moveNextBytes(((8 - (this.NB_BYTES_GFqnv & 7)) & 7) + (this.HFEmq8 & 7));
        }
    }

    private void vmpv_xorrange_move(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        vecMatProduct(pointer, pointer2, new Pointer(pointer3, this.NB_WORD_GFqn), FunctionParams.V);
        pointer.setXorRange(pointer3, this.NB_WORD_GFqn);
        pointer3.move(this.MLv_GFqn_SIZE);
    }

    void changeVariablesMQS64_gf2(Pointer pointer, Pointer pointer2) {
        Pointer pointer3 = new Pointer();
        int i = this.HFEnv;
        Pointer pointer4 = new Pointer(i * i * this.NB_WORD_GFqn);
        Pointer pointer5 = new Pointer(pointer, this.NB_WORD_GFqn);
        Pointer pointer6 = new Pointer(pointer4);
        Pointer pointer7 = new Pointer(pointer2);
        int i2 = 0;
        while (true) {
            int i3 = 64;
            if (i2 >= this.HFEnv) {
                break;
            }
            pointer3.changeIndex(pointer5);
            int i4 = 0;
            while (i4 < this.HFEnvq) {
                int i5 = 0;
                while (i5 < i3) {
                    int i6 = i4;
                    int i7 = i5;
                    LOOPKR(pointer3, pointer6, pointer7.get() >>> i5, i7, 64);
                    LOOPK_COMPLETE(pointer6, pointer7, pointer3, 1, this.HFEnvq - i6);
                    i5 = i7 + 1;
                    i3 = i3;
                    i2 = i2;
                    i4 = i6;
                }
                pointer7.moveIncremental();
                i4++;
            }
            int i8 = i2;
            if (this.HFEnvr != 0) {
                for (int i9 = 0; i9 < this.HFEnvr; i9++) {
                    LOOPKR(pointer3, pointer6, pointer7.get() >>> i9, i9, this.HFEnvr);
                    pointer6.move(this.NB_WORD_GFqn);
                }
                pointer7.moveIncremental();
            }
            i2 = i8 + 1;
        }
        int i10 = 64;
        pointer5.changeIndex(pointer4);
        pointer6.changeIndex(pointer, this.NB_WORD_GFqn);
        Pointer pointer8 = new Pointer(pointer2);
        int i11 = 0;
        while (i11 < this.HFEnvq) {
            int i12 = 0;
            while (i12 < i10) {
                pointer7.changeIndex(pointer8);
                int i13 = i12;
                int i14 = i11;
                Pointer pointer9 = pointer8;
                LOOPIR_INIT(pointer6, pointer3, pointer5, pointer7, i13, 64);
                for (int i15 = i14 + 1; i15 < this.HFEnvq; i15++) {
                    LOOPIR_INIT(pointer6, pointer3, pointer5, pointer7, 0, 64);
                }
                int i16 = this.HFEnvr;
                if (i16 != 0) {
                    LOOPIR_INIT(pointer6, pointer3, pointer5, pointer7, 0, i16);
                }
                pointer5.changeIndex(pointer3);
                pointer9.move(this.NB_WORD_GF2nv);
                i12 = i13 + 1;
                pointer8 = pointer9;
                i11 = i14;
                i10 = 64;
            }
            i11++;
            i10 = 64;
        }
        Pointer pointer10 = pointer8;
        if (this.HFEnvr != 0) {
            for (int i17 = 0; i17 < this.HFEnvr; i17++) {
                pointer7.changeIndex(pointer10);
                pointer3.changeIndex(pointer5);
                LOOPIR_INIT(pointer6, pointer3, pointer5, pointer7, i17, this.HFEnvr);
                pointer5.changeIndex(pointer3);
                pointer10.move(this.NB_WORD_GF2nv);
            }
        }
        pointer5.changeIndex(pointer4);
        pointer6.changeIndex(pointer, this.NB_WORD_GFqn);
        pointer7.changeIndex(pointer2);
        for (int i18 = 0; i18 < this.HFEnvq; i18++) {
            int i19 = 0;
            while (i19 < 64) {
                pointer6.move(this.NB_WORD_GFqn);
                pointer5.move(this.HFEnv * this.NB_WORD_GFqn);
                pointer3.changeIndex(pointer5);
                int i20 = i19 + 1;
                LOOPIR_LOOPK_COMPLETE(pointer6, pointer7, pointer3, i20, 64);
                for (int i21 = i18 + 1; i21 < this.HFEnvq; i21++) {
                    LOOPIR_LOOPK_COMPLETE(pointer6, pointer7, pointer3, 0, 64);
                }
                int i22 = this.HFEnvr;
                if (i22 != 0) {
                    LOOPIR_LOOPK_COMPLETE(pointer6, pointer7, pointer3, 0, i22);
                }
                pointer7.move(this.NB_WORD_GF2nv);
                i19 = i20;
            }
        }
        if (this.HFEnvr != 0) {
            int i23 = 0;
            while (i23 < this.HFEnvr - 1) {
                pointer6.move(this.NB_WORD_GFqn);
                pointer5.move(this.HFEnv * this.NB_WORD_GFqn);
                pointer3.changeIndex(pointer5);
                i23++;
                LOOPIR_LOOPK_COMPLETE(pointer6, pointer7, pointer3, i23, this.HFEnvr);
                pointer7.move(this.NB_WORD_GF2nv);
            }
        }
        pointer.indexReset();
        pointer2.indexReset();
    }

    void cleanLowerMatrix(Pointer pointer, FunctionParams functionParams) {
        int i;
        int i2;
        int iOrdinal = functionParams.ordinal();
        if (iOrdinal == 0) {
            i = this.HFEnvq;
            i2 = this.HFEnvr;
        } else {
            if (iOrdinal != 2) {
                throw new IllegalArgumentException("");
            }
            i = this.HFEnq;
            i2 = this.HFEnr;
        }
        Pointer pointer2 = new Pointer(pointer);
        int i3 = 1;
        while (i3 <= i) {
            for_and_xor_shift_incre_move(pointer2, i3, 64);
            pointer2.moveIncremental();
            i3++;
        }
        for_and_xor_shift_incre_move(pointer2, i3, i2);
    }

    void cleanMonicHFEv_gf2nx(Pointer pointer) {
        int i = this.NB_WORD_GFqn - 1;
        while (i < this.NB_UINT_HFEVPOLY) {
            pointer.setAnd(i, this.MASK_GF2n);
            i += this.NB_WORD_GFqn;
        }
    }

    public void compress_signHFE(byte[] bArr, Pointer pointer) {
        int i;
        byte[] bytes = pointer.toBytes(pointer.getLength() << 3);
        System.arraycopy(bytes, 0, bArr, 0, this.NB_BYTES_GFqnv);
        int i2 = this.HFEnv;
        int i3 = (this.NB_WORD_GF2nv << 3) + (this.HFEmq8 & 7);
        for (int i4 = 1; i4 < this.NB_ITE; i4++) {
            int i5 = i2 & 7;
            int iMin = Math.min(this.HFEDELTA + this.HFEv, (8 - i5) & 7);
            if (i5 != 0) {
                int i6 = this.HFEmr8;
                if (i6 != 0) {
                    int i7 = i2 >>> 3;
                    bArr[i7] = (byte) ((((bytes[i3] & UByte.MAX_VALUE) >>> i6) << i5) ^ bArr[i7]);
                    int i8 = this.VAL_BITS_M;
                    int i9 = iMin - i8;
                    if (i9 >= 0) {
                        i3++;
                    }
                    if (i9 > 0) {
                        int i10 = i2 + i8;
                        int i11 = i10 >>> 3;
                        bArr[i11] = (byte) (bArr[i11] ^ ((bytes[i3] & UByte.MAX_VALUE) << (i10 & 7)));
                        i2 = i10 + i9;
                    }
                } else {
                    int i12 = i2 >>> 3;
                    bArr[i12] = (byte) (((bytes[i3] & UByte.MAX_VALUE) << i5) ^ bArr[i12]);
                }
                i2 += iMin;
            }
            int i13 = (this.HFEDELTA + this.HFEv) - iMin;
            int i14 = (this.HFEm + iMin) & 7;
            if (i14 != 0) {
                for (int i15 = 0; i15 < ((i13 - 1) >>> 3); i15++) {
                    int i16 = (bytes[i3] & UByte.MAX_VALUE) >>> i14;
                    i3++;
                    bArr[i2 >>> 3] = (byte) (i16 ^ ((bytes[i3] & UByte.MAX_VALUE) << (8 - i14)));
                    i2 += 8;
                }
                int i17 = i2 >>> 3;
                i = i3 + 1;
                byte b = (byte) ((bytes[i3] & UByte.MAX_VALUE) >>> i14);
                bArr[i17] = b;
                int i18 = ((i13 + 7) & 7) + 1;
                int i19 = 8 - i14;
                if (i18 > i19) {
                    bArr[i17] = (byte) (((byte) ((bytes[i] & UByte.MAX_VALUE) << i19)) ^ b);
                    i = i3 + 2;
                }
                i2 += i18;
            } else {
                int i20 = 0;
                while (i20 < ((i13 + 7) >>> 3)) {
                    bArr[i2 >>> 3] = bytes[i3];
                    i2 += 8;
                    i20++;
                    i3++;
                }
                i2 -= (8 - (i13 & 7)) & 7;
                i = i3;
            }
            i3 = ((8 - (this.NB_BYTES_GFqnv & 7)) & 7) + (this.HFEmq8 & 7) + i;
        }
    }

    void convMQS_one_eq_to_hybrid_rep8_comp_gf2(byte[] bArr, PointerUnion pointerUnion, byte[] bArr2) {
        convMQ_UL_gf2(bArr, bArr2, this.HFEmr8);
        int bytesMove = 0;
        for (int i = 0; i < this.NB_MONOMIAL_PK; i++) {
            bytesMove = pointerUnion.toBytesMove(bArr, bytesMove, this.HFEmq8);
            if (this.HFEmr8 != 0) {
                pointerUnion.moveNextByte();
            }
        }
    }

    void convMQS_one_eq_to_hybrid_rep8_uncomp_gf2(byte[] bArr, PointerUnion pointerUnion, byte[] bArr2) {
        int i = this.HFEmr8 - 1;
        convMQ_UL_gf2(bArr, bArr2, i);
        int i2 = this.ACCESS_last_equations8;
        int i3 = this.NB_BYTES_EQUATION;
        int i4 = i2 + (i * i3);
        int i5 = i * i3;
        int iFor_setPK = for_setPK(bArr, bArr2, i4, i5, this.HFEnv);
        int i6 = this.HFEnv;
        setPK(bArr, bArr2, i6, i4, i5, iFor_setPK, i6 - 1, this.LOST_BITS);
        int i7 = this.buffer;
        long j = 0;
        for (int i8 = this.LOST_BITS - 1; i8 >= 0; i8--) {
            j ^= ((long) ((bArr2[(i7 >>> 3) + i5] >>> (i7 & 7)) & 1)) << ((this.LOST_BITS - 1) - i8);
            i7 += i8;
        }
        int i9 = this.ACCESS_last_equations8 - 1;
        for (int i10 = 0; i10 < this.HFEmr8 - 1; i10++) {
            i9 += this.NB_BYTES_EQUATION;
            bArr[i9] = (byte) (bArr[i9] ^ (((byte) (j >>> (this.HFENr8c * i10))) << this.HFENr8));
        }
        pointerUnion.indexReset();
        int bytesMove = 0;
        for (int i11 = 0; i11 < this.NB_MONOMIAL_PK; i11++) {
            bytesMove = pointerUnion.toBytesMove(bArr, bytesMove, this.HFEmq8);
            pointerUnion.moveNextByte();
        }
    }

    void convMQS_one_to_last_mr8_equations_gf2(byte[] bArr, PointerUnion pointerUnion) {
        int i;
        pointerUnion.moveNextBytes(this.HFEmq8);
        PointerUnion pointerUnion2 = new PointerUnion(pointerUnion);
        int i2 = this.NB_MONOMIAL_PK >>> 3;
        int i3 = 0;
        for (int i4 = 0; i4 < this.HFEmr8; i4++) {
            pointerUnion2.changeIndex(pointerUnion);
            int i5 = 0;
            while (true) {
                if (i5 >= i2) {
                    break;
                }
                int i6 = (pointerUnion2.getByte() >>> i4) & 1;
                pointerUnion2.moveNextBytes(this.NB_BYTES_GFqm);
                for (int i7 = 1; i7 < 8; i7++) {
                    i6 ^= ((pointerUnion2.getByte() >>> i4) & 1) << i7;
                    pointerUnion2.moveNextBytes(this.NB_BYTES_GFqm);
                }
                bArr[i3] = (byte) i6;
                i5++;
                i3++;
            }
            if (this.HFENr8 != 0) {
                long withCheck = (pointerUnion2.getWithCheck() >>> i4) & 1;
                pointerUnion2.moveNextBytes(this.NB_BYTES_GFqm);
                for (i = 1; i < this.HFENr8; i++) {
                    withCheck ^= ((pointerUnion2.getWithCheck() >>> i4) & 1) << i;
                    pointerUnion2.moveNextBytes(this.NB_BYTES_GFqm);
                }
                bArr[i3] = (byte) withCheck;
                i3++;
            }
        }
    }

    void convMQ_UL_gf2(byte[] bArr, byte[] bArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this.ACCESS_last_equations8;
            int i4 = this.NB_BYTES_EQUATION;
            for_setPK(bArr, bArr2, i3 + (i2 * i4), i2 * i4, this.HFEnv + 1);
        }
    }

    public int crypto_sign_open(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        long j;
        int i;
        long j2;
        PointerUnion pointerUnion = new PointerUnion(bArr);
        int i2 = 0;
        long jConvMQ_uncompressL_gf2 = 0;
        if (this.HFENr8 == 0 || this.HFEmr8 <= 1) {
            j = 0;
        } else {
            PointerUnion pointerUnion2 = new PointerUnion(pointerUnion);
            pointerUnion2.moveNextBytes(this.ACCESS_last_equations8 - 1);
            j = 0;
            for (int i3 = 0; i3 < this.HFEmr8 - 1; i3++) {
                pointerUnion2.moveNextBytes(this.NB_BYTES_EQUATION);
                j ^= ((((long) pointerUnion2.getByte()) & 255) >>> this.HFENr8) << (this.HFENr8c * i3);
            }
        }
        int i4 = this.HFEmr8;
        if (i4 == 0) {
            Pointer pointer = new Pointer(this.SIZE_SIGN_UNCOMPRESSED);
            Pointer pointer2 = new Pointer(new Pointer(this.NB_WORD_GF2nv));
            Pointer pointer3 = new Pointer(this.SIZE_DIGEST_UINT);
            pointer.fill(0, bArr3, 0, this.NB_BYTES_GFqnv);
            getSHA3Hash(pointer3, 0, 64, bArr2, 0, bArr2.length, new byte[64]);
            evalMQSnocst8_quo_gf2(pointer2, pointer, pointerUnion);
            return pointer2.isEqual_nocst_gf2(pointer3, this.NB_WORD_GF2m);
        }
        Pointer pointer4 = new Pointer((this.NB_WORD_UNCOMP_EQ * i4) + 1);
        PointerUnion pointerUnion3 = new PointerUnion(pointerUnion);
        while (i2 < this.HFEmr8 - 1) {
            pointerUnion3.setByteIndex(this.ACCESS_last_equations8 + (this.NB_BYTES_EQUATION * i2));
            jConvMQ_uncompressL_gf2 ^= convMQ_uncompressL_gf2(new Pointer(pointer4, (this.NB_WORD_UNCOMP_EQ * i2) + 1), pointerUnion3) << i2;
            i2++;
        }
        pointerUnion3.setByteIndex(this.ACCESS_last_equations8 + (this.NB_BYTES_EQUATION * i2));
        long jConvMQ_last_uncompressL_gf2 = jConvMQ_uncompressL_gf2 ^ (convMQ_last_uncompressL_gf2(new Pointer(pointer4, (this.NB_WORD_UNCOMP_EQ * i2) + 1), pointerUnion3) << i2);
        if (this.HFENr8 != 0) {
            int i5 = this.HFEnvr;
            if (i5 == 0) {
                i = (i2 + 1) * this.NB_WORD_UNCOMP_EQ;
                j2 = j << (64 - this.LOST_BITS);
            } else {
                int i6 = this.LOST_BITS;
                int i7 = i2 + 1;
                if (i5 > i6) {
                    i = i7 * this.NB_WORD_UNCOMP_EQ;
                    j2 = j << (i5 - i6);
                } else if (i5 == i6) {
                    pointer4.set(i7 * this.NB_WORD_UNCOMP_EQ, j);
                } else {
                    pointer4.setXor((this.NB_WORD_UNCOMP_EQ * i7) - 1, j << (64 - (i6 - i5)));
                    pointer4.set(i7 * this.NB_WORD_UNCOMP_EQ, j >>> (this.LOST_BITS - this.HFEnvr));
                }
            }
            pointer4.setXor(i, j2);
        }
        pointer4.set(jConvMQ_last_uncompressL_gf2 << (this.HFEmr - this.HFEmr8));
        return sign_openHFE_huncomp_pk(bArr2, bArr2.length, bArr3, pointerUnion, new PointerUnion(pointer4));
    }

    int div_r_gf2nx(Pointer pointer, int i, Pointer pointer2, int i2) {
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer4 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer5 = new Pointer(pointer);
        inv_gf2n(pointer4, pointer2, this.NB_WORD_GFqn * i2);
        while (i >= i2) {
            i = pointer.searchDegree(i, i2, this.NB_WORD_GFqn);
            if (i < i2) {
                break;
            }
            pointer5.changeIndex((i - i2) * this.NB_WORD_GFqn);
            mul_gf2n(pointer3, pointer, this.NB_WORD_GFqn * i, pointer4);
            for_mul_rem_xor_move(pointer5, pointer3, pointer2, 0, i2);
            i--;
        }
        return pointer.searchDegree(i, 1, this.NB_WORD_GFqn);
    }

    void evalHFEv_gf2nx(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        Pointer pointer4 = new Pointer(this.NB_WORD_MUL);
        Pointer pointer5 = new Pointer(this.NB_WORD_MUL);
        Pointer pointer6 = new Pointer((this.HFEDegI + 1) * this.NB_WORD_GFqn);
        Pointer pointer7 = new Pointer();
        int index = pointer2.getIndex();
        Pointer pointer8 = new Pointer(this.NB_WORD_GFqv);
        Pointer pointer9 = new Pointer(pointer6, this.NB_WORD_GFqn);
        pointer6.copyFrom(pointer3, this.NB_WORD_GFqn);
        pointer6.setAnd(this.NB_WORD_GFqn - 1, this.MASK_GF2n);
        for (int i = 1; i <= this.HFEDegI; i++) {
            sqr_gf2n(pointer9, 0, pointer9, -this.NB_WORD_GFqn);
            pointer9.move(this.NB_WORD_GFqn);
        }
        int i2 = this.NB_WORD_GFqn;
        int i3 = this.NB_WORD_GFqv;
        if (i2 + i3 != this.NB_WORD_GF2nv) {
            i3--;
        }
        int i4 = i3;
        pointer8.setRangeRotate(0, pointer3, i2 - 1, i4, 64 - this.HFEnr);
        int i5 = this.NB_WORD_GFqn;
        if (this.NB_WORD_GFqv + i5 != this.NB_WORD_GF2nv) {
            pointer8.set(i4, pointer3.get((i5 - 1) + i4) >>> this.HFEnr);
        }
        evalMQSv_unrolled_gf2(pointer4, pointer8, pointer2);
        pointer2.move(this.MQv_GFqn_SIZE);
        vmpv_xorrange_move(pointer5, pointer8, pointer2);
        pointer9.changeIndex(pointer6);
        mul_xorrange(pointer4, pointer9, pointer5);
        for (int i6 = 1; i6 < this.HFEDegI; i6++) {
            vmpv_xorrange_move(pointer5, pointer8, pointer2);
            int i7 = this.NB_WORD_GFqn;
            pointer5.setRangeClear(i7, this.NB_WORD_MMUL - i7);
            pointer7.changeIndex(pointer9);
            for_mul_xorrange_move(pointer5, pointer2, pointer7, i6);
            rem_gf2n(pointer5, 0, pointer5);
            mul_xorrange(pointer4, pointer7, pointer5);
        }
        vmpv_xorrange_move(pointer5, pointer8, pointer2);
        pointer7.changeIndex(pointer9);
        if (this.HFEDegJ != 0) {
            int i8 = this.NB_WORD_GFqn;
            pointer5.setRangeClear(i8, this.NB_WORD_MMUL - i8);
            for_mul_xorrange_move(pointer5, pointer2, pointer7, this.HFEDegJ);
            pointer5.setXorRange(pointer7, this.NB_WORD_GFqn);
            rem_gf2n(pointer5, 0, pointer5);
        } else {
            pointer5.setRangeFromXor(pointer5, pointer7, this.NB_WORD_GFqn);
        }
        pointer9.move(this.HFEDegI * this.NB_WORD_GFqn);
        mul_xorrange(pointer4, pointer9, pointer5);
        rem_gf2n(pointer, 0, pointer4);
        pointer2.changeIndex(index);
    }

    void evalMQSv_unrolled_gf2(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        Pointer pointer4 = new Pointer(this.HFEv);
        int i = this.HFEv;
        int i2 = i >>> 6;
        int i3 = i & 63;
        int i4 = this.HFEn;
        int i5 = 0;
        int i6 = (i4 >>> 6) + ((i4 & 63) != 0 ? 1 : 0);
        int index = pointer3.getIndex();
        Pointer pointer5 = new Pointer(i6);
        int i7 = 0;
        int range_xi = 0;
        while (i7 < i2) {
            range_xi = pointer4.setRange_xi(pointer2.get(i7), range_xi, 64);
            i7++;
        }
        if (i3 != 0) {
            pointer4.setRange_xi(pointer2.get(i7), range_xi, i3);
        }
        pointer.copyFrom(pointer3, i6);
        pointer3.move(i6);
        while (i5 < this.HFEv) {
            pointer5.copyFrom(pointer3, i6);
            pointer3.move(i6);
            int i8 = i5 + 1;
            int i9 = i8;
            while (i9 < this.HFEv - 3) {
                pointer5.setXorRangeAndMaskMove(pointer3, i6, pointer4.get(i9));
                pointer5.setXorRangeAndMaskMove(pointer3, i6, pointer4.get(i9 + 1));
                pointer5.setXorRangeAndMaskMove(pointer3, i6, pointer4.get(i9 + 2));
                pointer5.setXorRangeAndMaskMove(pointer3, i6, pointer4.get(i9 + 3));
                i9 += 4;
            }
            while (i9 < this.HFEv) {
                pointer5.setXorRangeAndMaskMove(pointer3, i6, pointer4.get(i9));
                i9++;
            }
            pointer.setXorRangeAndMask(pointer5, i6, pointer4.get(i5));
            i5 = i8;
        }
        pointer3.changeIndex(index);
    }

    void fast_sort_gf2n(Pointer pointer, int i) {
        int i2;
        int i3;
        int i4;
        Pointer pointer2 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer4 = new Pointer();
        Pointer pointer5 = new Pointer();
        int i5 = i - 1;
        int iHighest_One = GeMSSUtils.Highest_One(i5);
        int i6 = iHighest_One;
        while (true) {
            i2 = 0;
            if (i6 <= 1) {
                break;
            }
            int i7 = i6 << 1;
            int i8 = i / i7;
            int iMax = Math.max(0, (i - (i7 * i8)) - i6);
            pointer4.changeIndex(pointer);
            pointer5.changeIndex(pointer, this.NB_WORD_GFqn * i6);
            int i9 = 0;
            while (i9 < i8) {
                for_casct_move(pointer4, pointer5, pointer3, i6, 1);
                pointer4.move(this.NB_WORD_GFqn * i6);
                pointer5.move(this.NB_WORD_GFqn * i6);
                i9++;
                i8 = i8;
            }
            for_casct_move(pointer4, pointer5, pointer3, iMax, 1);
            int i10 = iHighest_One;
            while (i10 > i6) {
                while (i2 < i - i10) {
                    if ((i2 & i6) == 0) {
                        pointer5.changeIndex(pointer, (i2 + i6) * this.NB_WORD_GFqn);
                        i3 = i10;
                        i4 = i2;
                        copy_for_casct(pointer2, pointer5, pointer, pointer4, pointer3, i3, i4);
                        pointer5.copyFrom(pointer2, this.NB_WORD_GFqn);
                    } else {
                        i3 = i10;
                        i4 = i2;
                    }
                    i2 = i4 + 1;
                    i10 = i3;
                    i6 = i6;
                }
                i10 >>>= 1;
            }
            i6 >>>= 1;
        }
        pointer4.changeIndex(pointer);
        pointer5.changeIndex(pointer, this.NB_WORD_GFqn);
        for_casct_move(pointer4, pointer5, pointer3, i5, 2);
        pointer5.changeIndex(pointer, this.NB_WORD_GFqn);
        while (iHighest_One > 1) {
            int i11 = i2;
            while (i11 < i - iHighest_One) {
                copy_for_casct(pointer2, pointer5, pointer, pointer4, pointer3, iHighest_One, i11);
                pointer5.copyFrom(pointer2, this.NB_WORD_GFqn);
                pointer5.move(this.NB_WORD_GFqn << 1);
                i11 += 2;
            }
            iHighest_One >>>= 1;
            i2 = i11;
        }
    }

    void findRootsSplit2_HT_gf2nx(Pointer pointer, Pointer pointer2) {
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer4 = new Pointer(this.NB_WORD_GFqn);
        int index = pointer2.getIndex();
        sqr_gf2n(pointer3, 0, pointer2, this.NB_WORD_GFqn);
        inv_gf2n(pointer, pointer3, 0);
        mul_gf2n(pointer3, pointer2, pointer);
        findRootsSplit_x2_x_c_HT_gf2nx(pointer4, pointer3);
        pointer2.move(this.NB_WORD_GFqn);
        mul_gf2n(pointer, pointer4, pointer2);
        int i = this.NB_WORD_GFqn;
        pointer.setRangeFromXor(i, pointer, 0, pointer2, 0, i);
        pointer2.changeIndex(index);
    }

    void findRootsSplit_x2_x_c_HT_gf2nx(Pointer pointer, Pointer pointer2) {
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        int i = (this.HFEn + 1) >>> 1;
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        int i2 = 1;
        for (int i3 = this.HFEn1h_rightmost; i3 != -1; i3--) {
            int i4 = i2 << 1;
            sqr_gf2n(pointer3, pointer);
            for (int i5 = 1; i5 < i4; i5++) {
                sqr_gf2n(pointer3, pointer3);
            }
            pointer.setXorRange(pointer3, this.NB_WORD_GFqn);
            i2 = i >>> i3;
            if ((i2 & 1) != 0) {
                sqr_gf2n(pointer3, pointer);
                sqr_gf2n(pointer, pointer3);
                pointer.setXorRange(pointer2, this.NB_WORD_GFqn);
            }
        }
    }

    void for_mul_xorrange_move(Pointer pointer, Pointer pointer2, Pointer pointer3, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            this.mul.mul_gf2x_xor(pointer, pointer2, pointer3);
            pointer2.move(this.NB_WORD_GFqn);
            pointer3.move(this.NB_WORD_GFqn);
        }
    }

    void genSecretMQS_gf2_opt(Pointer pointer, Pointer pointer2) {
        Pointer pointer3 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer4 = new Pointer((this.HFEDegI + 1) * (this.HFEv + 1) * this.NB_WORD_GFqn);
        Pointer pointer5 = new Pointer(pointer2, this.MQv_GFqn_SIZE);
        for (int i = 0; i <= this.HFEDegI; i++) {
            for (int i2 = 0; i2 <= this.HFEv; i2++) {
                int i3 = ((this.HFEDegI + 1) * i2) + i;
                int i4 = this.NB_WORD_GFqn;
                pointer4.copyFrom(i3 * i4, pointer5, 0, i4);
                pointer5.move(this.NB_WORD_GFqn);
            }
            pointer5.move(this.NB_WORD_GFqn * i);
        }
        Pointer pointer6 = new Pointer(this.SIZE_ROW * (this.HFEn - 1) * this.NB_WORD_GFqn);
        for (int i5 = 1; i5 < this.HFEn; i5++) {
            pointer6.set(i5 >>> 6, 1 << (i5 & 63));
            for (int i6 = 0; i6 < this.HFEDegI; i6++) {
                sqr_gf2n(pointer6, this.NB_WORD_GFqn, pointer6, 0);
                pointer6.move(this.NB_WORD_GFqn);
            }
            pointer6.move(this.NB_WORD_GFqn);
        }
        pointer6.indexReset();
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        pointer2.move(this.MQv_GFqn_SIZE);
        pointer.move(this.NB_WORD_GFqn);
        Pointer pointer7 = new Pointer(this.HFEDegI * this.HFEn * this.NB_WORD_GFqn);
        special_buffer(pointer7, pointer2, pointer6);
        Pointer pointer8 = new Pointer(pointer7);
        Pointer pointer9 = new Pointer(pointer7);
        pointer.copyFrom(pointer9, this.NB_WORD_GFqn);
        pointer9.move(this.NB_WORD_GFqn);
        pointer.setXorMatrix_NoMove(pointer9, this.NB_WORD_GFqn, this.HFEDegI - 1);
        pointer5.changeIndex(pointer4);
        pointer.setXorMatrix(pointer5, this.NB_WORD_GFqn, this.HFEDegI + 1);
        Pointer pointer10 = new Pointer(pointer6, this.NB_WORD_GFqn);
        int i7 = 1;
        while (i7 < this.HFEn) {
            dotProduct_gf2n(pointer, pointer10, pointer8, this.HFEDegI);
            pointer10.move(this.SIZE_ROW * this.NB_WORD_GFqn);
            pointer.setXorMatrix(pointer9, this.NB_WORD_GFqn, this.HFEDegI);
            i7++;
        }
        while (i7 < this.HFEnv) {
            pointer.copyFrom(pointer5, this.NB_WORD_GFqn);
            pointer5.move(this.NB_WORD_GFqn);
            pointer.setXorMatrix(pointer5, this.NB_WORD_GFqn, this.HFEDegI);
            i7++;
        }
        Pointer pointer11 = new Pointer(pointer6, this.NB_WORD_GFqn);
        Pointer pointer12 = new Pointer(this.NB_WORD_MUL);
        int i8 = 1;
        while (i8 < this.HFEn) {
            pointer8.move(this.HFEDegI * this.NB_WORD_GFqn);
            pointer10.changeIndex(pointer11);
            pointer9.changeIndex(pointer8);
            this.mul.mul_gf2x(this.Buffer_NB_WORD_MUL, pointer4, new Pointer(pointer10, -this.NB_WORD_GFqn));
            int i9 = 1;
            while (i9 <= this.HFEDegI) {
                int i10 = this.NB_WORD_GFqn;
                Pointer pointer13 = pointer10;
                Pointer pointer14 = pointer9;
                pointer3.setRangeFromXor(0, pointer9, 0, pointer4, i9 * i10, i10);
                mul_xorrange(this.Buffer_NB_WORD_MUL, pointer3, pointer13);
                pointer14.move(this.NB_WORD_GFqn);
                pointer13.move(this.NB_WORD_GFqn);
                i9++;
                pointer9 = pointer14;
                pointer10 = pointer13;
                pointer11 = pointer11;
                i8 = i8;
                pointer12 = pointer12;
            }
            Pointer pointer15 = pointer12;
            Pointer pointer16 = pointer11;
            Pointer pointer17 = pointer10;
            Pointer pointer18 = pointer9;
            pointer17.move(this.NB_WORD_GFqn);
            rem_gf2n(pointer, 0, this.Buffer_NB_WORD_MUL);
            pointer.move(this.NB_WORD_GFqn);
            int i11 = i8 + 1;
            int i12 = i11;
            while (i12 < this.HFEn) {
                int index = pointer17.getIndex();
                int index2 = pointer8.getIndex();
                int index3 = pointer16.getIndex();
                int index4 = pointer18.getIndex();
                mul_move(pointer15, pointer17, pointer8);
                for_mul_xorrange_move(pointer15, pointer17, pointer8, this.HFEDegI - 1);
                int i13 = i11;
                Pointer pointer19 = pointer16;
                for_mul_xorrange_move(pointer15, pointer19, pointer18, this.HFEDegI);
                rem_gf2n(pointer, 0, pointer15);
                pointer17.changeIndex(index + (this.SIZE_ROW * this.NB_WORD_GFqn));
                pointer8.changeIndex(index2);
                pointer19.changeIndex(index3);
                pointer18.changeIndex(index4 + (this.HFEDegI * this.NB_WORD_GFqn));
                pointer.move(this.NB_WORD_GFqn);
                i12++;
                pointer3 = pointer3;
                pointer16 = pointer19;
                i11 = i13;
            }
            int i14 = i11;
            Pointer pointer20 = pointer16;
            Pointer pointer21 = pointer3;
            pointer5.changeIndex(pointer4);
            pointer20.move(-this.NB_WORD_GFqn);
            while (i12 < this.HFEnv) {
                pointer5.move((this.HFEDegI + 1) * this.NB_WORD_GFqn);
                dotProduct_gf2n(pointer, pointer20, pointer5, this.HFEDegI + 1);
                pointer.move(this.NB_WORD_GFqn);
                i12++;
            }
            int i15 = this.NB_WORD_GFqn;
            pointer20.move(i15 + (this.SIZE_ROW * i15));
            pointer9 = pointer18;
            pointer10 = pointer17;
            pointer11 = pointer20;
            pointer12 = pointer15;
            i8 = i14;
            pointer3 = pointer21;
        }
        pointer2.move(this.NB_WORD_GFqn - this.MQv_GFqn_SIZE);
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn * (this.NB_MONOMIAL_VINEGAR - 1));
        pointer.indexReset();
        pointer2.indexReset();
    }

    int interpolateHFE_FS_ref(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        Pointer pointer4 = new Pointer(this.NB_WORD_GF2nv);
        Pointer pointer5 = new Pointer();
        Pointer pointer6 = new Pointer();
        Pointer pointer7 = new Pointer(this.HFEnv * this.NB_WORD_GFqn);
        pointer.copyFrom(pointer2, this.NB_WORD_GFqn);
        Pointer pointer8 = new Pointer(pointer3);
        Pointer pointer9 = new Pointer(pointer7);
        for (int i = 0; i < this.HFEnv; i++) {
            evalHFEv_gf2nx(pointer9, pointer2, pointer8);
            pointer9.move(this.NB_WORD_GFqn);
            pointer8.move(this.NB_WORD_GF2nv);
        }
        pointer8.changeIndex(pointer3);
        pointer9.changeIndex(pointer7);
        int i2 = 0;
        while (i2 < this.HFEnv) {
            pointer.move(this.NB_WORD_GFqn);
            pointer9.setXorRange(pointer2, this.NB_WORD_GFqn);
            pointer.copyFrom(pointer9, this.NB_WORD_GFqn);
            pointer5.changeIndex(pointer9);
            pointer6.changeIndex(pointer8);
            int i3 = i2 + 1;
            for (int i4 = i3; i4 < this.HFEnv; i4++) {
                pointer.move(this.NB_WORD_GFqn);
                pointer5.move(this.NB_WORD_GFqn);
                pointer6.move(this.NB_WORD_GF2nv);
                pointer4.setRangeFromXor(pointer8, pointer6, this.NB_WORD_GF2nv);
                evalHFEv_gf2nx(pointer, pointer2, pointer4);
                pointer.setXorRangeXor(0, pointer9, 0, pointer5, 0, this.NB_WORD_GFqn);
            }
            pointer9.move(this.NB_WORD_GFqn);
            pointer8.move(this.NB_WORD_GF2nv);
            i2 = i3;
        }
        pointer.indexReset();
        return 0;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x00da  */
    /* JADX WARN: Code duplicated, block: B:24:0x00ef A[LOOP:2: B:23:0x00ed->B:24:0x00ef, LOOP_END] */
    void invMatrixLU_gf2(Pointer pointer, Pointer pointer2, Pointer pointer3, FunctionParams functionParams) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        Pointer pointer4;
        long j;
        int i6;
        int i7;
        Pointer pointer5;
        int i8;
        Pointer pointer6 = new Pointer(pointer2);
        Pointer pointer7 = new Pointer(pointer2);
        Pointer pointer8 = new Pointer(pointer3);
        int iOrdinal = functionParams.ordinal();
        int i9 = 1;
        if (iOrdinal == 0) {
            i = this.HFEnvq;
            i2 = this.HFEnv - 1;
            i3 = this.NB_WORD_GF2nv;
            i4 = this.HFEnvr;
            i5 = this.LTRIANGULAR_NV_SIZE;
        } else {
            if (iOrdinal != 2) {
                throw new IllegalArgumentException("Invalid Input");
            }
            pointer.setRangeClear(0, this.MATRIXn_SIZE);
            i = this.HFEnq;
            i2 = this.HFEn - 1;
            i3 = this.NB_WORD_GFqn;
            i4 = this.HFEnr;
            i5 = this.LTRIANGULAR_N_SIZE;
        }
        int i10 = i;
        int i11 = i2;
        int i12 = i3;
        int i13 = i4;
        int i14 = i5;
        Pointer pointer9 = new Pointer(pointer);
        Pointer pointer10 = new Pointer(pointer);
        int i15 = 0;
        int iLoop_xor_loop_move_xorandmask_move = 0;
        while (i15 < i10) {
            int i16 = i15;
            int i17 = i12;
            iLoop_xor_loop_move_xorandmask_move = loop_xor_loop_move_xorandmask_move(pointer9, pointer10, pointer6, pointer7, iLoop_xor_loop_move_xorandmask_move, i16, 64, i11, i17);
            pointer7.moveIncremental();
            i15 = i16 + 1;
            pointer10 = pointer10;
            pointer9 = pointer9;
            i13 = i13;
            i9 = i9;
            i14 = i14;
            i10 = i10;
            i12 = i17;
        }
        Pointer pointer11 = pointer10;
        Pointer pointer12 = pointer9;
        int i18 = i14;
        int i19 = i13;
        int i20 = i12;
        int i21 = i9;
        int i22 = i15;
        if (i19 <= i21) {
            pointer4 = pointer12;
            j = 1;
            i6 = i20;
            if (i19 == i21) {
                pointer4.set(i22, 1L);
            }
            pointer8.move(i18);
            i7 = i11;
            while (i7 > 0) {
                pointer8.move((-1) - (i7 >>> 6));
                pointer4.move(-i6);
                pointer5 = pointer11;
                pointer5.changeIndex(pointer);
                for (i8 = 0; i8 < i7; i8++) {
                    pointer5.setXorRangeAndMask(pointer4, i6, -((pointer8.get(i8 >>> 6) >>> (i8 & 63)) & j));
                    pointer5.move(i6);
                }
                i7--;
                pointer11 = pointer5;
            }
        }
        int i23 = i19 - i21;
        j = 1;
        loop_xor_loop_move_xorandmask_move(pointer12, pointer11, pointer6, pointer7, iLoop_xor_loop_move_xorandmask_move, i22, i23, i11, i20);
        pointer4 = pointer12;
        pointer4.setXor(i22, 1 << i23);
        i6 = i20;
        pointer4.move(i6);
        pointer8.move(i18);
        i7 = i11;
        while (i7 > 0) {
            pointer8.move((-1) - (i7 >>> 6));
            pointer4.move(-i6);
            pointer5 = pointer11;
            pointer5.changeIndex(pointer);
            while (i8 < i7) {
                pointer5.setXorRangeAndMask(pointer4, i6, -((pointer8.get(i8 >>> 6) >>> (i8 & 63)) & j));
                pointer5.move(i6);
            }
            i7--;
            pointer11 = pointer5;
        }
    }

    void mul_gf2n(Pointer pointer, Pointer pointer2, int i, Pointer pointer3) {
        int index = pointer2.getIndex();
        pointer2.move(i);
        this.mul.mul_gf2x(this.Buffer_NB_WORD_MUL, pointer2, pointer3);
        pointer2.changeIndex(index);
        rem_gf2n(pointer, 0, this.Buffer_NB_WORD_MUL);
    }

    void mul_gf2n(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        this.mul.mul_gf2x(this.Buffer_NB_WORD_MUL, pointer2, pointer3);
        rem_gf2n(pointer, 0, this.Buffer_NB_WORD_MUL);
    }

    void mul_move(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        this.mul.mul_gf2x(pointer, pointer2, pointer3);
        pointer2.move(this.NB_WORD_GFqn);
        pointer3.move(this.NB_WORD_GFqn);
    }

    public void mul_rem_xorrange(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        this.mul.mul_gf2x(this.Buffer_NB_WORD_MUL, pointer2, pointer3);
        this.rem.rem_gf2n_xor(pointer.array, pointer.cp, this.Buffer_NB_WORD_MUL.array);
    }

    public void mul_rem_xorrange(Pointer pointer, Pointer pointer2, Pointer pointer3, int i) {
        int index = pointer3.getIndex();
        pointer3.move(i);
        this.mul.mul_gf2x(this.Buffer_NB_WORD_MUL, pointer2, pointer3);
        this.rem.rem_gf2n_xor(pointer.array, pointer.cp, this.Buffer_NB_WORD_MUL.array);
        pointer3.changeIndex(index);
    }

    public void mul_xorrange(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        this.mul.mul_gf2x_xor(pointer, pointer2, pointer3);
    }

    public void signHFE_FeistelPatarin(SecureRandom secureRandom, byte[] bArr, byte[] bArr2, int i, int i2, byte[] bArr3) {
        int i3;
        int i4;
        SecretKeyHFE secretKeyHFE;
        Pointer pointer;
        long j;
        PointerUnion pointerUnion;
        Pointer pointer2;
        Pointer pointer3;
        this.random = secureRandom;
        Pointer pointer4 = new Pointer(this.NB_WORD_GFqn);
        Pointer pointer5 = new Pointer(this.SIZE_DIGEST_UINT);
        Pointer pointer6 = new Pointer(new Pointer(this.SIZE_DIGEST_UINT));
        int i5 = this.HFEv;
        int i6 = i5 & 7;
        int i7 = (i5 >>> 3) + (i6 != 0 ? 1 : 0);
        long jMaskUINT = GeMSSUtils.maskUINT(this.HFEvr);
        SecretKeyHFE secretKeyHFE2 = new SecretKeyHFE(this);
        Pointer pointer7 = new Pointer(this.NB_WORD_GFqv);
        Pointer[] pointerArr = new Pointer[this.HFEDegI + 1];
        precSignHFE(secretKeyHFE2, pointerArr, bArr3);
        Pointer pointer8 = new Pointer(secretKeyHFE2.F_struct.poly);
        Pointer pointer9 = new Pointer(pointer5);
        int i8 = this.Sha3BitStrength >>> 3;
        Pointer pointer10 = pointer7;
        SecretKeyHFE secretKeyHFE3 = secretKeyHFE2;
        long j2 = jMaskUINT;
        Pointer pointer11 = pointer6;
        getSHA3Hash(pointer9, 0, i8, bArr2, i, i2, new byte[i8]);
        Pointer pointer12 = new Pointer(this.SIZE_SIGN_UNCOMPRESSED);
        Pointer pointer13 = new Pointer(this.NB_WORD_GF2nv);
        PointerUnion pointerUnion2 = new PointerUnion(pointer13);
        long j3 = 0;
        int i9 = 1;
        while (true) {
            i3 = this.NB_ITE;
            if (i9 > i3) {
                break;
            }
            pointer13.setRangeFromXor(pointer12, pointer9, this.NB_WORD_GF2m);
            if (this.HFEmr8 != 0) {
                pointer13.setAnd(this.NB_WORD_GF2m - 1, this.MASK_GF2m);
                j3 = pointerUnion2.getByte(this.HFEmq8);
            }
            long j4 = j3;
            while (true) {
                if (this.HFEmr8 != 0) {
                    pointerUnion2.fillRandomBytes(this.HFEmq8, secureRandom, (this.NB_BYTES_GFqn - this.NB_BYTES_GFqm) + 1);
                    pointerUnion2.setAndThenXorByte(this.HFEmq8, -(1 << this.HFEmr8), j4);
                } else {
                    int i10 = this.NB_BYTES_GFqm;
                    pointerUnion2.fillRandomBytes(i10, secureRandom, this.NB_BYTES_GFqn - i10);
                }
                if ((this.HFEn & 7) != 0) {
                    i4 = 1;
                    pointer13.setAnd(this.NB_WORD_GFqn - 1, this.MASK_GF2n);
                } else {
                    i4 = 1;
                }
                secretKeyHFE = secretKeyHFE3;
                vecMatProduct(pointer4, pointer13, secretKeyHFE.T, FunctionParams.N);
                pointer = pointer10;
                pointer.fillRandom(0, secureRandom, i7);
                if (i6 != 0) {
                    j = j2;
                    pointer.setAnd(this.NB_WORD_GFqv - i4, j);
                } else {
                    j = j2;
                }
                evalMQSv_unrolled_gf2(pointer8, pointer, secretKeyHFE.F_HFEv);
                int i11 = 0;
                while (i11 <= this.HFEDegI) {
                    PointerUnion pointerUnion3 = pointerUnion2;
                    vecMatProduct(this.Buffer_NB_WORD_GFqn, pointer, new Pointer(pointerArr[i11], this.NB_WORD_GFqn), FunctionParams.V);
                    int i12 = this.NB_WORD_GFqn;
                    int i13 = i11 + 1;
                    pointer8.setRangeFromXor(i12 * (((i11 * i13) >>> 1) + 1), pointerArr[i11], 0, this.Buffer_NB_WORD_GFqn, 0, i12);
                    pointerUnion2 = pointerUnion3;
                    i11 = i13;
                    j = j;
                }
                pointerUnion = pointerUnion2;
                j2 = j;
                if (chooseRootHFE_gf2nx(pointer13, secretKeyHFE.F_struct, pointer4) != 0) {
                    break;
                }
                pointerUnion2 = pointerUnion;
                pointer10 = pointer;
                secretKeyHFE3 = secretKeyHFE;
                i9 = i9;
                pointer9 = pointer9;
            }
            pointer13.setXor(this.NB_WORD_GFqn - 1, pointer.get() << this.HFEnr);
            pointer13.setRangeRotate(this.NB_WORD_GFqn, pointer, 0, this.NB_WORD_GFqv - 1, 64 - this.HFEnr);
            int i14 = this.NB_WORD_GFqn;
            int i15 = this.NB_WORD_GFqv;
            if (i14 + i15 == this.NB_WORD_GF2nv) {
                pointer13.set((i14 + i15) - 1, pointer.get(i15 - 1) >>> (64 - this.HFEnr));
            }
            vecMatProduct(pointer12, pointer13, secretKeyHFE.S, FunctionParams.NV);
            int i16 = this.NB_ITE;
            if (i9 != i16) {
                int i17 = this.NB_WORD_GF2nv;
                int i18 = this.NB_WORD_GF2nvm;
                int i19 = (((i16 - 1) - i9) * i18) + i17;
                pointer12.copyFrom(i19, pointer12, i17 - i18, i18);
                if (this.HFEmr != 0) {
                    pointer12.setAnd(i19, ~this.MASK_GF2m);
                }
                Pointer pointer14 = pointer9;
                byte[] bytes = pointer14.toBytes(this.SIZE_DIGEST);
                getSHA3Hash(pointer11, 0, this.SIZE_DIGEST, bytes, 0, bytes.length, bytes);
                pointer3 = pointer11;
                pointer2 = pointer14;
                pointer3.swap(pointer2);
            } else {
                pointer2 = pointer9;
                pointer3 = pointer11;
            }
            i9++;
            pointer11 = pointer3;
            pointer9 = pointer2;
            secretKeyHFE3 = secretKeyHFE;
            pointerUnion2 = pointerUnion;
            pointer13 = pointer13;
            pointer10 = pointer;
            j2 = j2;
            j3 = j4;
        }
        if (i3 == 1) {
            System.arraycopy(pointer12.toBytes(pointer12.getLength() << 3), 0, bArr, 0, this.NB_BYTES_GFqnv);
        } else {
            compress_signHFE(bArr, pointer12);
        }
    }

    public int sign_openHFE_huncomp_pk(byte[] bArr, int i, byte[] bArr2, PointerUnion pointerUnion, PointerUnion pointerUnion2) {
        Pointer pointer = new Pointer(this.SIZE_SIGN_UNCOMPRESSED);
        Pointer pointer2 = new Pointer(this.NB_WORD_GF2nv);
        Pointer pointer3 = new Pointer(this.NB_WORD_GF2nv);
        Pointer pointer4 = new Pointer(pointer2);
        Pointer pointer5 = new Pointer(pointer3);
        byte[] bArr3 = new byte[64];
        Pointer pointer6 = new Pointer(this.NB_ITE * this.SIZE_DIGEST_UINT);
        long j = pointerUnion2.get();
        pointerUnion2.move(1);
        uncompress_signHFE(pointer, bArr2);
        getSHA3Hash(pointer6, 0, 64, bArr, 0, i, bArr3);
        int i2 = 1;
        while (i2 < this.NB_ITE) {
            int i3 = i2;
            getSHA3Hash(pointer6, i2 * this.SIZE_DIGEST_UINT, 64, bArr3, 0, this.SIZE_DIGEST, bArr3);
            pointer6.setAnd(((this.SIZE_DIGEST_UINT * (i3 - 1)) + this.NB_WORD_GF2m) - 1, this.MASK_GF2m);
            i2 = i3 + 1;
        }
        pointer6.setAnd(((this.SIZE_DIGEST_UINT * (i2 - 1)) + this.NB_WORD_GF2m) - 1, this.MASK_GF2m);
        evalMQShybrid8_uncomp_nocst_gf2_m(pointer4, pointer, pointerUnion, pointerUnion2);
        pointer4.setXor(this.HFEmq, j);
        for (int i4 = this.NB_ITE - 1; i4 > 0; i4--) {
            pointer4.setXorRange(pointer6, this.SIZE_DIGEST_UINT * i4, this.NB_WORD_GF2m);
            int i5 = this.NB_WORD_GF2nv + (((this.NB_ITE - 1) - i4) * this.NB_WORD_GF2nvm);
            pointer4.setAnd(this.NB_WORD_GF2m - 1, this.MASK_GF2m);
            pointer4.setXor(this.NB_WORD_GF2m - 1, pointer.get(i5));
            int i6 = this.NB_WORD_GF2nvm;
            if (i6 != 1) {
                pointer4.copyFrom(this.NB_WORD_GF2m, pointer, i5 + 1, i6 - 1);
            }
            evalMQShybrid8_uncomp_nocst_gf2_m(pointer5, pointer4, pointerUnion, pointerUnion2);
            pointer5.setXor(this.HFEmq, j);
            pointer5.swap(pointer4);
        }
        return pointer4.isEqual_nocst_gf2(pointer6, this.NB_WORD_GF2m);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0055  */
    /* JADX WARN: Code duplicated, block: B:19:0x005d A[LOOP:1: B:17:0x0059->B:19:0x005d, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:23:0x007b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:24:0x007d  */
    /* JADX WARN: Code duplicated, block: B:33:0x0094  */
    /* JADX WARN: Code duplicated, block: B:35:0x0098 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:36:0x0099  */
    /* JADX WARN: Code duplicated, block: B:37:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:39:0x00a6 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:40:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:42:0x00b2 A[LOOP:2: B:41:0x00b0->B:42:0x00b2, LOOP_END] */
    void vecMatProduct(Pointer pointer, Pointer pointer2, Pointer pointer3, FunctionParams functionParams) {
        int i;
        int i2;
        int i3;
        int i4;
        int iOrdinal;
        long j;
        int i5;
        int i6;
        long j2;
        Pointer pointer4 = new Pointer(pointer3);
        int iOrdinal2 = functionParams.ordinal();
        int i7 = 1;
        int i8 = 0;
        if (iOrdinal2 == 0) {
            pointer.setRangeClear(0, this.NB_WORD_GF2nv);
            i = this.HFEnvq;
            i2 = this.NB_WORD_GF2nv;
        } else {
            if (iOrdinal2 != 1) {
                if (iOrdinal2 == 2) {
                    pointer.setRangeClear(0, this.NB_WORD_GFqn);
                    i2 = this.NB_WORD_GFqn;
                    i = this.HFEnq;
                } else {
                    if (iOrdinal2 != 3) {
                        throw new IllegalArgumentException("Invalid input for vecMatProduct");
                    }
                    pointer.setRangeClear(0, this.NB_WORD_GF2m);
                    i = this.HFEnq;
                    i2 = this.NB_WORD_GF2m;
                    i3 = this.NB_WORD_GFqn;
                }
                while (i8 < i) {
                    j2 = pointer2.get(i8);
                    while (i8 < 64) {
                        pointer.setXorRangeAndMask(pointer4, i2, -(j2 & 1));
                        pointer4.move(i3);
                        j2 >>>= 1;
                        i8++;
                        i7 = 1;
                    }
                    i8++;
                    i8 = 0;
                }
                i4 = i7;
                iOrdinal = functionParams.ordinal();
                if (iOrdinal != 0) {
                    if (this.HFEnvr == 0) {
                        return;
                    }
                    j = pointer2.get(this.HFEnvq);
                    i5 = this.HFEnvr;
                } else if (iOrdinal != i4) {
                    if (iOrdinal == 2 && iOrdinal != 3) {
                        throw new IllegalArgumentException("Invalid input for vecMatProduct");
                    }
                    j = pointer2.get(this.HFEnq);
                    i5 = this.HFEnr;
                } else {
                    if (this.HFEvr == 0) {
                        return;
                    }
                    j = pointer2.get(this.HFEvq);
                    i5 = this.HFEvr;
                }
                for (i6 = 0; i6 < i5; i6++) {
                    pointer.setXorRangeAndMask(pointer4, i2, -(j & 1));
                    pointer4.move(i3);
                    j >>>= 1;
                }
                if (functionParams == FunctionParams.M || this.HFEmr == 0) {
                }
                pointer.setAnd(this.NB_WORD_GF2m - 1, this.MASK_GF2m);
                return;
            }
            pointer.setRangeClear(0, this.NB_WORD_GFqn);
            i2 = this.NB_WORD_GFqn;
            i = this.HFEvq;
        }
        i3 = i2;
        while (i8 < i) {
            j2 = pointer2.get(i8);
            while (i8 < 64) {
                pointer.setXorRangeAndMask(pointer4, i2, -(j2 & 1));
                pointer4.move(i3);
                j2 >>>= 1;
                i8++;
                i7 = 1;
            }
            i8++;
            i8 = 0;
        }
        i4 = i7;
        iOrdinal = functionParams.ordinal();
        if (iOrdinal != 0) {
            if (this.HFEnvr == 0) {
                return;
            }
            j = pointer2.get(this.HFEnvq);
            i5 = this.HFEnvr;
        } else if (iOrdinal != i4) {
            if (iOrdinal == 2) {
            }
            j = pointer2.get(this.HFEnq);
            i5 = this.HFEnr;
        } else {
            if (this.HFEvr == 0) {
                return;
            }
            j = pointer2.get(this.HFEvq);
            i5 = this.HFEvr;
        }
        while (i6 < i5) {
            pointer.setXorRangeAndMask(pointer4, i2, -(j & 1));
            pointer4.move(i3);
            j >>>= 1;
        }
        if (functionParams == FunctionParams.M) {
        }
    }
}
