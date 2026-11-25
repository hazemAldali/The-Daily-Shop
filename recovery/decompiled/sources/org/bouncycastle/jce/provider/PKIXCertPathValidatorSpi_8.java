package org.bouncycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertPathValidatorSpi;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import okhttp3.Headers$$ExternalSyntheticApiModelOutline0;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.TBSCertificate;
import org.bouncycastle.jcajce.PKIXCertRevocationChecker;
import org.bouncycastle.jcajce.PKIXExtendedBuilderParameters;
import org.bouncycastle.jcajce.PKIXExtendedParameters;
import org.bouncycastle.jcajce.interfaces.BCX509Certificate;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.exception.ExtCertPathValidatorException;
import org.bouncycastle.x509.ExtendedPKIXParameters;

/* JADX INFO: loaded from: classes3.dex */
public class PKIXCertPathValidatorSpi_8 extends CertPathValidatorSpi {
    private final JcaJceHelper helper;
    private final boolean isForCRLCheck;

    public PKIXCertPathValidatorSpi_8() {
        this(false);
    }

    public PKIXCertPathValidatorSpi_8(boolean z) {
        this.helper = new BCJcaJceHelper();
        this.isForCRLCheck = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static void checkCertificate(X509Certificate x509Certificate) throws AnnotatedException {
        if (!(x509Certificate instanceof BCX509Certificate)) {
            try {
                TBSCertificate.getInstance(x509Certificate.getTBSCertificate());
                return;
            } catch (IllegalArgumentException e) {
                throw new AnnotatedException(e.getMessage());
            } catch (CertificateEncodingException e2) {
                throw new AnnotatedException("unable to process TBSCertificate", e2);
            }
        }
        try {
            if (((BCX509Certificate) x509Certificate).getTBSCertificateNative() != null) {
                return;
            }
            e = null;
            throw new AnnotatedException("unable to process TBSCertificate", e);
        } catch (RuntimeException e3) {
            e = e3;
        }
    }

    @Override // java.security.cert.CertPathValidatorSpi
    public PKIXCertPathChecker engineGetRevocationChecker() {
        return new ProvRevocationChecker(this.helper);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v8, types: [org.bouncycastle.asn1.x509.AlgorithmIdentifier] */
    @Override // java.security.cert.CertPathValidatorSpi
    public CertPathValidatorResult engineValidate(CertPath certPath, CertPathParameters certPathParameters) throws CertPathValidatorException, InvalidAlgorithmParameterException {
        PKIXExtendedParameters baseParameters;
        X500Name ca;
        PublicKey cAPublicKey;
        HashSet hashSet;
        int iPrepareNextCertJ;
        ArrayList arrayList;
        int iPrepareNextCertI2;
        HashSet hashSet2;
        if (certPathParameters instanceof PKIXParameters) {
            PKIXExtendedParameters.Builder builder = new PKIXExtendedParameters.Builder((PKIXParameters) certPathParameters);
            if (certPathParameters instanceof ExtendedPKIXParameters) {
                ExtendedPKIXParameters extendedPKIXParameters = (ExtendedPKIXParameters) certPathParameters;
                builder.setUseDeltasEnabled(extendedPKIXParameters.isUseDeltasEnabled());
                builder.setValidityModel(extendedPKIXParameters.getValidityModel());
            }
            baseParameters = builder.build();
        } else if (certPathParameters instanceof PKIXExtendedBuilderParameters) {
            baseParameters = ((PKIXExtendedBuilderParameters) certPathParameters).getBaseParameters();
        } else {
            if (!(certPathParameters instanceof PKIXExtendedParameters)) {
                throw new InvalidAlgorithmParameterException("Parameters must be a " + PKIXParameters.class.getName() + " instance.");
            }
            baseParameters = (PKIXExtendedParameters) certPathParameters;
        }
        if (baseParameters.getTrustAnchors() == null) {
            throw new InvalidAlgorithmParameterException("trustAnchors is null, this is not allowed for certification path validation.");
        }
        List<? extends Certificate> certificates = certPath.getCertificates();
        int size = certificates.size();
        int algorithmIdentifier = -1;
        if (certificates.isEmpty()) {
            throw new CertPathValidatorException("Certification path is empty.", null, certPath, -1);
        }
        Date validityDate = CertPathValidatorUtilities.getValidityDate(baseParameters, new Date());
        Set initialPolicies = baseParameters.getInitialPolicies();
        try {
            TrustAnchor trustAnchorFindTrustAnchor = CertPathValidatorUtilities.findTrustAnchor((X509Certificate) certificates.get(certificates.size() - 1), baseParameters.getTrustAnchors(), baseParameters.getSigProvider());
            if (trustAnchorFindTrustAnchor != null) {
                checkCertificate(trustAnchorFindTrustAnchor.getTrustedCert());
                PKIXExtendedParameters pKIXExtendedParametersBuild = new PKIXExtendedParameters.Builder(baseParameters).setTrustAnchor(trustAnchorFindTrustAnchor).build();
                ArrayList arrayList2 = new ArrayList();
                PKIXCertRevocationChecker provRevocationChecker = null;
                for (PKIXCertPathChecker pKIXCertPathChecker : pKIXExtendedParametersBuild.getCertPathCheckers()) {
                    pKIXCertPathChecker.init(false);
                    if (!Headers$$ExternalSyntheticApiModelOutline0.m2053m((Object) pKIXCertPathChecker)) {
                        arrayList2.add(pKIXCertPathChecker);
                    } else {
                        if (provRevocationChecker != null) {
                            throw new CertPathValidatorException("only one PKIXRevocationChecker allowed");
                        }
                        provRevocationChecker = pKIXCertPathChecker instanceof PKIXCertRevocationChecker ? (PKIXCertRevocationChecker) pKIXCertPathChecker : new WrappedRevocationChecker(pKIXCertPathChecker);
                    }
                }
                if (pKIXExtendedParametersBuild.isRevocationEnabled() && provRevocationChecker == null) {
                    provRevocationChecker = new ProvRevocationChecker(this.helper);
                }
                PKIXCertRevocationChecker pKIXCertRevocationChecker = provRevocationChecker;
                int i = size + 1;
                ArrayList[] arrayListArr = new ArrayList[i];
                for (int i2 = 0; i2 < i; i2++) {
                    arrayListArr[i2] = new ArrayList();
                }
                HashSet hashSet3 = new HashSet();
                hashSet3.add(RFC3280CertPathUtilities.ANY_POLICY);
                PKIXPolicyNode pKIXPolicyNode = new PKIXPolicyNode(new ArrayList(), 0, hashSet3, null, new HashSet(), RFC3280CertPathUtilities.ANY_POLICY, false);
                arrayListArr[0].add(pKIXPolicyNode);
                PKIXNameConstraintValidator pKIXNameConstraintValidator = new PKIXNameConstraintValidator();
                HashSet hashSet4 = new HashSet();
                int i3 = pKIXExtendedParametersBuild.isExplicitPolicyRequired() ? 0 : i;
                int i4 = pKIXExtendedParametersBuild.isAnyPolicyInhibited() ? 0 : i;
                if (pKIXExtendedParametersBuild.isPolicyMappingInhibited()) {
                    i = 0;
                }
                X509Certificate trustedCert = trustAnchorFindTrustAnchor.getTrustedCert();
                try {
                    if (trustedCert != null) {
                        ca = PrincipalUtils.getSubjectPrincipal(trustedCert);
                        cAPublicKey = trustedCert.getPublicKey();
                    } else {
                        ca = PrincipalUtils.getCA(trustAnchorFindTrustAnchor);
                        cAPublicKey = trustAnchorFindTrustAnchor.getCAPublicKey();
                    }
                    try {
                        algorithmIdentifier = CertPathValidatorUtilities.getAlgorithmIdentifier(cAPublicKey);
                        algorithmIdentifier.getAlgorithm();
                        algorithmIdentifier.getParameters();
                        if (pKIXExtendedParametersBuild.getTargetConstraints() != null && !pKIXExtendedParametersBuild.getTargetConstraints().match((Certificate) certificates.get(0))) {
                            throw new ExtCertPathValidatorException("Target certificate in certification path does not match targetConstraints.", null, certPath, 0);
                        }
                        int i5 = 1;
                        int size2 = certificates.size() - 1;
                        int iPrepareNextCertM = size;
                        X509Certificate x509Certificate = null;
                        int i6 = i4;
                        int i7 = i;
                        int i8 = i3;
                        PKIXPolicyNode pKIXPolicyNode2 = pKIXPolicyNode;
                        while (size2 >= 0) {
                            int i9 = size - size2;
                            int i10 = size;
                            X509Certificate x509Certificate2 = (X509Certificate) certificates.get(size2);
                            int i11 = size2 == certificates.size() + (-1) ? i5 : 0;
                            try {
                                checkCertificate(x509Certificate2);
                                int i12 = size2;
                                PKIXExtendedParameters pKIXExtendedParameters = pKIXExtendedParametersBuild;
                                List<? extends Certificate> list = certificates;
                                PKIXNameConstraintValidator pKIXNameConstraintValidator2 = pKIXNameConstraintValidator;
                                Date date = validityDate;
                                Date date2 = validityDate;
                                int i13 = i7;
                                ArrayList[] arrayListArr2 = arrayListArr;
                                PKIXExtendedParameters pKIXExtendedParameters2 = pKIXExtendedParametersBuild;
                                int iPrepareNextCertI1 = i8;
                                ArrayList arrayList3 = arrayList2;
                                boolean z = i11;
                                TrustAnchor trustAnchor = trustAnchorFindTrustAnchor;
                                int i14 = i5;
                                RFC3280CertPathUtilities.processCertA(certPath, pKIXExtendedParameters, date, pKIXCertRevocationChecker, i12, cAPublicKey, z, ca, trustedCert);
                                RFC3280CertPathUtilities.processCertBC(certPath, i12, pKIXNameConstraintValidator2, this.isForCRLCheck);
                                PKIXPolicyNode pKIXPolicyNodeProcessCertE = RFC3280CertPathUtilities.processCertE(certPath, i12, RFC3280CertPathUtilities.processCertD(certPath, i12, hashSet4, pKIXPolicyNode2, arrayListArr2, i6, this.isForCRLCheck));
                                RFC3280CertPathUtilities.processCertF(certPath, i12, pKIXPolicyNodeProcessCertE, iPrepareNextCertI1);
                                if (i9 == i10) {
                                    iPrepareNextCertJ = i6;
                                    arrayListArr = arrayListArr2;
                                    arrayList = arrayList3;
                                    iPrepareNextCertI2 = i13;
                                    pKIXPolicyNode2 = pKIXPolicyNodeProcessCertE;
                                    iPrepareNextCertM = iPrepareNextCertM;
                                } else if (x509Certificate2 == null || x509Certificate2.getVersion() != i14) {
                                    RFC3280CertPathUtilities.prepareNextCertA(certPath, i12);
                                    arrayListArr = arrayListArr2;
                                    PKIXPolicyNode pKIXPolicyNodePrepareCertB = RFC3280CertPathUtilities.prepareCertB(certPath, i12, arrayListArr, pKIXPolicyNodeProcessCertE, i13);
                                    RFC3280CertPathUtilities.prepareNextCertG(certPath, i12, pKIXNameConstraintValidator2);
                                    int iPrepareNextCertH1 = RFC3280CertPathUtilities.prepareNextCertH1(certPath, i12, iPrepareNextCertI1);
                                    int iPrepareNextCertH2 = RFC3280CertPathUtilities.prepareNextCertH2(certPath, i12, i13);
                                    int iPrepareNextCertH3 = RFC3280CertPathUtilities.prepareNextCertH3(certPath, i12, i6);
                                    iPrepareNextCertI1 = RFC3280CertPathUtilities.prepareNextCertI1(certPath, i12, iPrepareNextCertH1);
                                    iPrepareNextCertI2 = RFC3280CertPathUtilities.prepareNextCertI2(certPath, i12, iPrepareNextCertH2);
                                    iPrepareNextCertJ = RFC3280CertPathUtilities.prepareNextCertJ(certPath, i12, iPrepareNextCertH3);
                                    RFC3280CertPathUtilities.prepareNextCertK(certPath, i12);
                                    iPrepareNextCertM = RFC3280CertPathUtilities.prepareNextCertM(certPath, i12, RFC3280CertPathUtilities.prepareNextCertL(certPath, i12, iPrepareNextCertM));
                                    RFC3280CertPathUtilities.prepareNextCertN(certPath, i12);
                                    Set<String> criticalExtensionOIDs = x509Certificate2.getCriticalExtensionOIDs();
                                    if (criticalExtensionOIDs != null) {
                                        hashSet2 = new HashSet(criticalExtensionOIDs);
                                        hashSet2.remove(RFC3280CertPathUtilities.KEY_USAGE);
                                        hashSet2.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                                        hashSet2.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                                        hashSet2.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                                        hashSet2.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                                        hashSet2.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                                        hashSet2.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                                        hashSet2.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                                        hashSet2.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                                        hashSet2.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                                    } else {
                                        hashSet2 = new HashSet();
                                    }
                                    arrayList = arrayList3;
                                    RFC3280CertPathUtilities.prepareNextCertO(certPath, i12, hashSet2, arrayList);
                                    X500Name subjectPrincipal = PrincipalUtils.getSubjectPrincipal(x509Certificate2);
                                    try {
                                        PublicKey nextWorkingKey = CertPathValidatorUtilities.getNextWorkingKey(certPath.getCertificates(), i12, this.helper);
                                        AlgorithmIdentifier algorithmIdentifier2 = CertPathValidatorUtilities.getAlgorithmIdentifier(nextWorkingKey);
                                        algorithmIdentifier2.getAlgorithm();
                                        algorithmIdentifier2.getParameters();
                                        pKIXPolicyNode2 = pKIXPolicyNodePrepareCertB;
                                        ca = subjectPrincipal;
                                        cAPublicKey = nextWorkingKey;
                                        trustedCert = x509Certificate2;
                                    } catch (CertPathValidatorException e) {
                                        throw new CertPathValidatorException("Next working key could not be retrieved.", e, certPath, i12);
                                    }
                                } else {
                                    if (i9 != i14 || !x509Certificate2.equals(trustAnchor.getTrustedCert())) {
                                        throw new CertPathValidatorException("Version 1 certificates can't be used as CA ones.", null, certPath, i12);
                                    }
                                    iPrepareNextCertJ = i6;
                                    arrayListArr = arrayListArr2;
                                    arrayList = arrayList3;
                                    iPrepareNextCertI2 = i13;
                                    pKIXPolicyNode2 = pKIXPolicyNodeProcessCertE;
                                    iPrepareNextCertM = iPrepareNextCertM;
                                }
                                i8 = iPrepareNextCertI1;
                                i6 = iPrepareNextCertJ;
                                arrayList2 = arrayList;
                                i5 = i14;
                                trustAnchorFindTrustAnchor = trustAnchor;
                                validityDate = date2;
                                i7 = iPrepareNextCertI2;
                                pKIXNameConstraintValidator = pKIXNameConstraintValidator2;
                                x509Certificate = x509Certificate2;
                                certificates = list;
                                size = i10;
                                size2 = i12 - 1;
                                pKIXExtendedParametersBuild = pKIXExtendedParameters2;
                            } catch (AnnotatedException e2) {
                                throw new CertPathValidatorException(e2.getMessage(), e2.getUnderlyingException(), certPath, size2);
                            }
                        }
                        PKIXExtendedParameters pKIXExtendedParameters3 = pKIXExtendedParametersBuild;
                        ArrayList arrayList4 = arrayList2;
                        TrustAnchor trustAnchor2 = trustAnchorFindTrustAnchor;
                        X509Certificate x509Certificate3 = x509Certificate;
                        int i15 = size2;
                        int i16 = i15 + 1;
                        int iWrapupCertB = RFC3280CertPathUtilities.wrapupCertB(certPath, i16, RFC3280CertPathUtilities.wrapupCertA(i8, x509Certificate3));
                        Set<String> criticalExtensionOIDs2 = x509Certificate3.getCriticalExtensionOIDs();
                        if (criticalExtensionOIDs2 != null) {
                            hashSet = new HashSet(criticalExtensionOIDs2);
                            hashSet.remove(RFC3280CertPathUtilities.KEY_USAGE);
                            hashSet.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                            hashSet.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                            hashSet.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                            hashSet.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                            hashSet.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                            hashSet.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                            hashSet.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                            hashSet.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                            hashSet.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                            hashSet.remove(RFC3280CertPathUtilities.CRL_DISTRIBUTION_POINTS);
                            hashSet.remove(Extension.extendedKeyUsage.getId());
                        } else {
                            hashSet = new HashSet();
                        }
                        RFC3280CertPathUtilities.wrapupCertF(certPath, i16, arrayList4, hashSet);
                        PKIXPolicyNode pKIXPolicyNodeWrapupCertG = RFC3280CertPathUtilities.wrapupCertG(certPath, pKIXExtendedParameters3, initialPolicies, i16, arrayListArr, pKIXPolicyNode2, hashSet4);
                        if (iWrapupCertB > 0 || pKIXPolicyNodeWrapupCertG != null) {
                            return new PKIXCertPathValidatorResult(trustAnchor2, pKIXPolicyNodeWrapupCertG, x509Certificate3.getPublicKey());
                        }
                        throw new CertPathValidatorException("Path processing failed on policy.", null, certPath, i15);
                    } catch (CertPathValidatorException e3) {
                        throw new ExtCertPathValidatorException("Algorithm identifier of public key of trust anchor could not be read.", e3, certPath, -1);
                    }
                } catch (RuntimeException e4) {
                    throw new ExtCertPathValidatorException("Subject of trust anchor could not be (re)encoded.", e4, certPath, algorithmIdentifier);
                }
            }
            try {
                throw new CertPathValidatorException("Trust anchor for certification path not found.", null, certPath, -1);
            } catch (AnnotatedException e5) {
                e = e5;
            }
        } catch (AnnotatedException e6) {
            e = e6;
        }
        throw new CertPathValidatorException(e.getMessage(), e.getUnderlyingException(), certPath, certificates.size() - 1);
    }
}
