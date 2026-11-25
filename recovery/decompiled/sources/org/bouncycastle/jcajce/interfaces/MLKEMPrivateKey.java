package org.bouncycastle.jcajce.interfaces;

import java.security.PrivateKey;

/* JADX INFO: loaded from: classes3.dex */
public interface MLKEMPrivateKey extends PrivateKey, MLKEMKey {
    byte[] getPrivateData();

    MLKEMPublicKey getPublicKey();

    byte[] getSeed();
}
