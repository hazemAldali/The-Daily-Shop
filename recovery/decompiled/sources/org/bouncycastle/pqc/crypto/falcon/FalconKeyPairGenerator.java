package org.bouncycastle.pqc.crypto.falcon;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/* JADX INFO: loaded from: classes3.dex */
public class FalconKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private int logn;
    private FalconNIST nist;
    private int noncelen;
    private FalconKeyGenerationParameters params;
    private int pk_size;
    private SecureRandom random;
    private int sk_size;

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        byte[][] bArrCrypto_sign_keypair = this.nist.crypto_sign_keypair(new byte[this.pk_size], 0, new byte[this.sk_size], 0);
        FalconParameters parameters = this.params.getParameters();
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new FalconPublicKeyParameters(parameters, bArrCrypto_sign_keypair[0]), (AsymmetricKeyParameter) new FalconPrivateKeyParameters(parameters, bArrCrypto_sign_keypair[1], bArrCrypto_sign_keypair[2], bArrCrypto_sign_keypair[3], bArrCrypto_sign_keypair[0]));
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        int i;
        FalconKeyGenerationParameters falconKeyGenerationParameters = (FalconKeyGenerationParameters) keyGenerationParameters;
        this.params = falconKeyGenerationParameters;
        this.random = keyGenerationParameters.getRandom();
        this.logn = falconKeyGenerationParameters.getParameters().getLogN();
        int nonceLength = falconKeyGenerationParameters.getParameters().getNonceLength();
        this.noncelen = nonceLength;
        this.nist = new FalconNIST(this.logn, nonceLength, this.random);
        int i2 = 1 << this.logn;
        if (i2 == 1024) {
            i = 5;
        } else if (i2 == 256 || i2 == 512) {
            i = 6;
        } else {
            i = (i2 == 64 || i2 == 128) ? 7 : 8;
        }
        this.pk_size = ((i2 * 14) / 8) + 1;
        this.sk_size = (((i * 2) * i2) / 8) + 1 + i2;
    }
}
