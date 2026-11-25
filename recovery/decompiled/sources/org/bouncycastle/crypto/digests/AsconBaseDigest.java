package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Longs;

/* JADX INFO: loaded from: classes3.dex */
abstract class AsconBaseDigest implements ExtendedDigest {
    protected long x0;
    protected long x1;
    protected long x2;
    protected long x3;
    protected long x4;
    protected final int CRYPTO_BYTES = 32;
    protected final int ASCON_HASH_RATE = 8;
    protected int ASCON_PB_ROUNDS = 12;
    protected final byte[] m_buf = new byte[8];
    protected int m_bufPos = 0;

    AsconBaseDigest() {
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

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        return hash(bArr, i, 32);
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 32;
    }

    protected int hash(byte[] bArr, int i, int i2) {
        if (i + 32 > bArr.length) {
            throw new OutputLengthException("output buffer is too short");
        }
        padAndAbsorb();
        squeeze(bArr, i, i2);
        return i2;
    }

    protected abstract long loadBytes(byte[] bArr, int i);

    protected abstract long loadBytes(byte[] bArr, int i, int i2);

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

    protected void padAndAbsorb() {
        long jLoadBytes = this.x0 ^ loadBytes(this.m_buf, 0, this.m_bufPos);
        this.x0 = jLoadBytes;
        this.x0 = jLoadBytes ^ pad(this.m_bufPos);
        p(12);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        Arrays.clear(this.m_buf);
        this.m_bufPos = 0;
    }

    protected abstract void setBytes(long j, byte[] bArr, int i);

    protected abstract void setBytes(long j, byte[] bArr, int i, int i2);

    protected void squeeze(byte[] bArr, int i, int i2) {
        int i3 = i;
        int i4 = i2;
        while (i4 > 8) {
            setBytes(this.x0, bArr, i3);
            p(this.ASCON_PB_ROUNDS);
            i3 += 8;
            i4 -= 8;
        }
        setBytes(this.x0, bArr, i3, i4);
        reset();
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        byte[] bArr = this.m_buf;
        int i = this.m_bufPos;
        bArr[i] = b;
        int i2 = i + 1;
        this.m_bufPos = i2;
        if (i2 == 8) {
            this.x0 ^= loadBytes(bArr, 0);
            p(this.ASCON_PB_ROUNDS);
            this.m_bufPos = 0;
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        int i3 = this.m_bufPos;
        int i4 = 8 - i3;
        if (i2 < i4) {
            System.arraycopy(bArr, i, this.m_buf, i3, i2);
            this.m_bufPos += i2;
            return;
        }
        if (i3 > 0) {
            System.arraycopy(bArr, i, this.m_buf, i3, i4);
            this.x0 ^= loadBytes(this.m_buf, 0);
            p(this.ASCON_PB_ROUNDS);
        } else {
            i4 = 0;
        }
        while (true) {
            int i5 = i2 - i4;
            if (i5 < 8) {
                System.arraycopy(bArr, i + i4, this.m_buf, 0, i5);
                this.m_bufPos = i5;
                return;
            } else {
                this.x0 ^= loadBytes(bArr, i + i4);
                p(this.ASCON_PB_ROUNDS);
                i4 += 8;
            }
        }
    }
}
