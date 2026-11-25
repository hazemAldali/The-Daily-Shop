package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.Xof;
import org.bouncycastle.util.Pack;

/* JADX INFO: loaded from: classes3.dex */
public class AsconXof extends AsconBaseDigest implements Xof {
    private final String algorithmName;
    AsconParameters asconParameters;
    private boolean m_squeezing = false;

    public enum AsconParameters {
        AsconXof,
        AsconXofA
    }

    public AsconXof(AsconParameters asconParameters) {
        String str;
        this.asconParameters = asconParameters;
        int iOrdinal = asconParameters.ordinal();
        if (iOrdinal == 0) {
            this.ASCON_PB_ROUNDS = 12;
            str = "Ascon-Xof";
        } else {
            if (iOrdinal != 1) {
                throw new IllegalArgumentException("Invalid parameter settings for Ascon Hash");
            }
            this.ASCON_PB_ROUNDS = 8;
            str = "Ascon-XofA";
        }
        this.algorithmName = str;
        reset();
    }

    @Override // org.bouncycastle.crypto.digests.AsconBaseDigest, org.bouncycastle.crypto.Digest
    public /* bridge */ /* synthetic */ int doFinal(byte[] bArr, int i) {
        return super.doFinal(bArr, i);
    }

    @Override // org.bouncycastle.crypto.Xof
    public int doFinal(byte[] bArr, int i, int i2) {
        int iDoOutput = doOutput(bArr, i, i2);
        reset();
        return iDoOutput;
    }

    @Override // org.bouncycastle.crypto.Xof
    public int doOutput(byte[] bArr, int i, int i2) {
        return hash(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return this.algorithmName;
    }

    @Override // org.bouncycastle.crypto.digests.AsconBaseDigest, org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.digests.AsconBaseDigest, org.bouncycastle.crypto.Digest
    public /* bridge */ /* synthetic */ int getDigestSize() {
        return super.getDigestSize();
    }

    @Override // org.bouncycastle.crypto.digests.AsconBaseDigest
    protected long loadBytes(byte[] bArr, int i) {
        return Pack.bigEndianToLong(bArr, i);
    }

    @Override // org.bouncycastle.crypto.digests.AsconBaseDigest
    protected long loadBytes(byte[] bArr, int i, int i2) {
        return Pack.bigEndianToLong(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.digests.AsconBaseDigest
    protected long pad(int i) {
        return 128 << (56 - (i << 3));
    }

    @Override // org.bouncycastle.crypto.digests.AsconBaseDigest
    protected void padAndAbsorb() {
        this.m_squeezing = true;
        super.padAndAbsorb();
    }

    @Override // org.bouncycastle.crypto.digests.AsconBaseDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        long j;
        super.reset();
        this.m_squeezing = false;
        int iOrdinal = this.asconParameters.ordinal();
        if (iOrdinal == 0) {
            this.x0 = -5368810569253202922L;
            this.x1 = 3121280575360345120L;
            this.x2 = 7395939140700676632L;
            this.x3 = 6533890155656471820L;
            j = 5710016986865767350L;
        } else {
            if (iOrdinal != 1) {
                return;
            }
            this.x0 = 4940560291654768690L;
            this.x1 = -3635129828240960206L;
            this.x2 = -597534922722107095L;
            this.x3 = 2623493988082852443L;
            j = -6283826724160825537L;
        }
        this.x4 = j;
    }

    @Override // org.bouncycastle.crypto.digests.AsconBaseDigest
    protected void setBytes(long j, byte[] bArr, int i) {
        Pack.longToBigEndian(j, bArr, i);
    }

    @Override // org.bouncycastle.crypto.digests.AsconBaseDigest
    protected void setBytes(long j, byte[] bArr, int i, int i2) {
        Pack.longToBigEndian(j, bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.digests.AsconBaseDigest, org.bouncycastle.crypto.Digest
    public void update(byte b) {
        if (this.m_squeezing) {
            throw new IllegalArgumentException("attempt to absorb while squeezing");
        }
        super.update(b);
    }

    @Override // org.bouncycastle.crypto.digests.AsconBaseDigest, org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        if (this.m_squeezing) {
            throw new IllegalArgumentException("attempt to absorb while squeezing");
        }
        super.update(bArr, i, i2);
    }
}
