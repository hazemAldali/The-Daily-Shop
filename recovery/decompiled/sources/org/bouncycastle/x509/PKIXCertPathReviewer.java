package org.bouncycastle.x509;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidatorException;
import java.security.cert.Certificate;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXParameters;
import java.security.cert.PolicyNode;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.security.auth.x500.X500Principal;
import kotlin.UByte;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1IA5String;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.x509.AccessDescription;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.AuthorityInformationAccess;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.DistributionPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.GeneralSubtree;
import org.bouncycastle.asn1.x509.IssuingDistributionPoint;
import org.bouncycastle.asn1.x509.NameConstraints;
import org.bouncycastle.asn1.x509.PolicyInformation;
import org.bouncycastle.asn1.x509.qualified.MonetaryValue;
import org.bouncycastle.asn1.x509.qualified.QCStatement;
import org.bouncycastle.i18n.ErrorBundle;
import org.bouncycastle.i18n.LocaleString;
import org.bouncycastle.i18n.filter.TrustedInput;
import org.bouncycastle.i18n.filter.UntrustedInput;
import org.bouncycastle.i18n.filter.UntrustedUrlInput;
import org.bouncycastle.jce.provider.AnnotatedException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.provider.PKIXNameConstraintValidator;
import org.bouncycastle.jce.provider.PKIXNameConstraintValidatorException;
import org.bouncycastle.jce.provider.PKIXPolicyNode;
import org.bouncycastle.jce.provider.RFC3280CertPathUtilities;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class PKIXCertPathReviewer extends CertPathValidatorUtilities {
    private static final int NAME_CHECK_MAX = 1024;
    private static final String RESOURCE_NAME = "org.bouncycastle.x509.CertPathReviewerMessages";
    protected CertPath certPath;
    protected List certs;
    protected Date currentDate;
    protected List[] errors;
    private boolean initialized;
    protected int n;
    protected List[] notifications;
    protected PKIXParameters pkixParams;
    protected PolicyNode policyTree;
    protected PublicKey subjectPublicKey;
    protected TrustAnchor trustAnchor;
    protected Date validDate;
    private static final String QC_STATEMENT = Extension.qCStatements.getId();
    private static final String CRL_DIST_POINTS = Extension.cRLDistributionPoints.getId();
    private static final String AUTH_INFO_ACCESS = Extension.authorityInfoAccess.getId();

    public PKIXCertPathReviewer() {
    }

    public PKIXCertPathReviewer(CertPath certPath, PKIXParameters pKIXParameters) throws CertPathReviewerException {
        init(certPath, pKIXParameters);
    }

    private String IPtoString(byte[] bArr) {
        try {
            return InetAddress.getByAddress(bArr).getHostAddress();
        } catch (Exception unused) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i != bArr.length; i++) {
                stringBuffer.append(Integer.toHexString(bArr[i] & UByte.MAX_VALUE));
                stringBuffer.append(' ');
            }
            return stringBuffer.toString();
        }
    }

    private void checkCriticalExtensions() {
        List<PKIXCertPathChecker> certPathCheckers = this.pkixParams.getCertPathCheckers();
        Iterator<PKIXCertPathChecker> it = certPathCheckers.iterator();
        while (it.hasNext()) {
            try {
                try {
                    it.next().init(false);
                } catch (CertPathValidatorException e) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certPathCheckerError", new Object[]{e.getMessage(), e, e.getClass().getName()}), e);
                }
            } catch (CertPathReviewerException e2) {
                addError(e2.getErrorMessage(), e2.getIndex());
                return;
            }
        }
        for (int size = this.certs.size() - 1; size >= 0; size--) {
            X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
            Set<String> criticalExtensionOIDs = x509Certificate.getCriticalExtensionOIDs();
            if (criticalExtensionOIDs != null && !criticalExtensionOIDs.isEmpty()) {
                criticalExtensionOIDs.remove(KEY_USAGE);
                criticalExtensionOIDs.remove(CERTIFICATE_POLICIES);
                criticalExtensionOIDs.remove(POLICY_MAPPINGS);
                criticalExtensionOIDs.remove(INHIBIT_ANY_POLICY);
                criticalExtensionOIDs.remove(ISSUING_DISTRIBUTION_POINT);
                criticalExtensionOIDs.remove(DELTA_CRL_INDICATOR);
                criticalExtensionOIDs.remove(POLICY_CONSTRAINTS);
                criticalExtensionOIDs.remove(BASIC_CONSTRAINTS);
                criticalExtensionOIDs.remove(SUBJECT_ALTERNATIVE_NAME);
                criticalExtensionOIDs.remove(NAME_CONSTRAINTS);
                if (size == 0) {
                    criticalExtensionOIDs.remove(Extension.extendedKeyUsage.getId());
                }
                String str = QC_STATEMENT;
                if (criticalExtensionOIDs.contains(str) && processQcStatements(x509Certificate, size)) {
                    criticalExtensionOIDs.remove(str);
                }
                Iterator<PKIXCertPathChecker> it2 = certPathCheckers.iterator();
                while (it2.hasNext()) {
                    try {
                        it2.next().check(x509Certificate, criticalExtensionOIDs);
                    } catch (CertPathValidatorException e3) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.criticalExtensionError", new Object[]{e3.getMessage(), e3, e3.getClass().getName()}), e3.getCause(), this.certPath, size);
                    }
                }
                if (!criticalExtensionOIDs.isEmpty()) {
                    Iterator<String> it3 = criticalExtensionOIDs.iterator();
                    while (it3.hasNext()) {
                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.unknownCriticalExt", new Object[]{new ASN1ObjectIdentifier(it3.next())}), size);
                    }
                }
            }
        }
    }

    private void checkNameConstraints() {
        PKIXNameConstraintValidator pKIXNameConstraintValidator = new PKIXNameConstraintValidator();
        try {
            for (int size = this.certs.size() - 1; size > 0; size--) {
                X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
                if (!isSelfIssued(x509Certificate)) {
                    X500Principal subjectPrincipal = getSubjectPrincipal(x509Certificate);
                    try {
                        ASN1Sequence aSN1Sequence = (ASN1Sequence) new ASN1InputStream(new ByteArrayInputStream(subjectPrincipal.getEncoded())).readObject();
                        try {
                            pKIXNameConstraintValidator.checkPermittedDN(aSN1Sequence);
                            try {
                                pKIXNameConstraintValidator.checkExcludedDN(aSN1Sequence);
                                try {
                                    ASN1Sequence aSN1Sequence2 = (ASN1Sequence) getExtensionValue(x509Certificate, SUBJECT_ALTERNATIVE_NAME);
                                    if (aSN1Sequence2 != null) {
                                        if (aSN1Sequence2.size() > 1024) {
                                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.subjAltNameExtError"), this.certPath, size);
                                        }
                                        for (int i = 0; i < aSN1Sequence2.size(); i++) {
                                            GeneralName generalName = GeneralName.getInstance(aSN1Sequence2.getObjectAt(i));
                                            try {
                                                pKIXNameConstraintValidator.checkPermitted(generalName);
                                                pKIXNameConstraintValidator.checkExcluded(generalName);
                                            } catch (PKIXNameConstraintValidatorException e) {
                                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notPermittedEmail", new Object[]{new UntrustedInput(generalName)}), e, this.certPath, size);
                                            }
                                        }
                                    }
                                } catch (AnnotatedException e2) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.subjAltNameExtError"), e2, this.certPath, size);
                                }
                            } catch (PKIXNameConstraintValidatorException e3) {
                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.excludedDN", new Object[]{new UntrustedInput(subjectPrincipal.getName())}), e3, this.certPath, size);
                            }
                        } catch (PKIXNameConstraintValidatorException e4) {
                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notPermittedDN", new Object[]{new UntrustedInput(subjectPrincipal.getName())}), e4, this.certPath, size);
                        }
                    } catch (IOException e5) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ncSubjectNameError", new Object[]{new UntrustedInput(subjectPrincipal)}), e5, this.certPath, size);
                    }
                }
                try {
                    ASN1Sequence aSN1Sequence3 = (ASN1Sequence) getExtensionValue(x509Certificate, NAME_CONSTRAINTS);
                    if (aSN1Sequence3 != null) {
                        NameConstraints nameConstraints = NameConstraints.getInstance(aSN1Sequence3);
                        GeneralSubtree[] permittedSubtrees = nameConstraints.getPermittedSubtrees();
                        if (permittedSubtrees != null) {
                            pKIXNameConstraintValidator.intersectPermittedSubtree(permittedSubtrees);
                        }
                        GeneralSubtree[] excludedSubtrees = nameConstraints.getExcludedSubtrees();
                        if (excludedSubtrees != null) {
                            for (int i2 = 0; i2 != excludedSubtrees.length; i2++) {
                                pKIXNameConstraintValidator.addExcludedSubtree(excludedSubtrees[i2]);
                            }
                        }
                    }
                } catch (AnnotatedException e6) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ncExtError"), e6, this.certPath, size);
                }
            }
        } catch (CertPathReviewerException e7) {
            addError(e7.getErrorMessage(), e7.getIndex());
        }
    }

    private void checkPathLength() {
        BasicConstraints basicConstraints;
        ASN1Integer pathLenConstraintInteger;
        int iMin = this.n;
        int i = 0;
        for (int size = this.certs.size() - 1; size > 0; size--) {
            X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
            if (!isSelfIssued(x509Certificate)) {
                if (iMin <= 0) {
                    addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.pathLengthExtended"));
                }
                iMin--;
                i++;
            }
            try {
                basicConstraints = BasicConstraints.getInstance(getExtensionValue(x509Certificate, BASIC_CONSTRAINTS));
            } catch (AnnotatedException unused) {
                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.processLengthConstError"), size);
                basicConstraints = null;
            }
            if (basicConstraints != null && basicConstraints.isCA() && (pathLenConstraintInteger = basicConstraints.getPathLenConstraintInteger()) != null) {
                iMin = Math.min(iMin, pathLenConstraintInteger.intPositiveValueExact());
            }
        }
        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.totalPathLength", new Object[]{Integers.valueOf(i)}));
    }

    /* JADX WARN: Code duplicated, block: B:100:0x021f A[Catch: CertPathReviewerException -> 0x05f7, TryCatch #7 {CertPathReviewerException -> 0x05f7, blocks: (B:17:0x006f, B:21:0x007f, B:23:0x008c, B:27:0x009c, B:28:0x00a7, B:30:0x00ad, B:32:0x00ce, B:33:0x00d6, B:35:0x00dc, B:37:0x00e1, B:38:0x00ed, B:42:0x00f9, B:45:0x0100, B:46:0x0109, B:48:0x010f, B:50:0x0119, B:53:0x0120, B:55:0x0124, B:95:0x0210, B:97:0x0216, B:98:0x0219, B:100:0x021f, B:102:0x022b, B:105:0x0233, B:106:0x0236, B:107:0x0239, B:109:0x023f, B:110:0x0248, B:112:0x024e, B:120:0x0271, B:121:0x027d, B:122:0x027e, B:124:0x0282, B:126:0x028a, B:127:0x028e, B:129:0x0294, B:132:0x02b6, B:134:0x02c0, B:135:0x02c5, B:136:0x02d1, B:137:0x02d2, B:138:0x02de, B:140:0x02e1, B:141:0x02ee, B:143:0x02f4, B:145:0x031a, B:147:0x0332, B:146:0x0329, B:148:0x0339, B:149:0x033f, B:151:0x0345, B:153:0x034d, B:164:0x0377, B:157:0x0355, B:158:0x0361, B:160:0x0363, B:161:0x0372, B:167:0x0380, B:178:0x039f, B:180:0x03a9, B:181:0x03ad, B:183:0x03b3, B:188:0x03c3, B:191:0x03d0, B:194:0x03dd, B:196:0x03e7, B:207:0x0425, B:199:0x03ef, B:200:0x03fd, B:201:0x03fe, B:202:0x040c, B:204:0x040e, B:205:0x041c, B:59:0x0133, B:60:0x0137, B:62:0x013d, B:64:0x0153, B:66:0x015d, B:67:0x0162, B:69:0x0168, B:70:0x0176, B:72:0x017c, B:74:0x0188, B:78:0x0195, B:79:0x019b, B:81:0x01a1, B:86:0x01ba, B:75:0x018b, B:77:0x018f, B:90:0x01f3, B:93:0x0203, B:94:0x020f, B:209:0x0434, B:210:0x0441, B:211:0x0442, B:215:0x0453, B:217:0x045d, B:218:0x0462, B:220:0x0468, B:223:0x0476, B:230:0x048b, B:308:0x05dd, B:309:0x05e9, B:233:0x0496, B:234:0x04a2, B:235:0x04a3, B:237:0x04a9, B:239:0x04b1, B:241:0x04b7, B:243:0x04c1, B:244:0x04c4, B:246:0x04ca, B:248:0x04da, B:249:0x04de, B:251:0x04e4, B:252:0x04ec, B:253:0x04ef, B:254:0x04f2, B:255:0x04f6, B:257:0x04fc, B:259:0x050c, B:261:0x0514, B:262:0x0517, B:264:0x051d, B:266:0x0529, B:267:0x052d, B:268:0x0530, B:269:0x0533, B:270:0x053f, B:272:0x0544, B:274:0x054e, B:275:0x0551, B:277:0x0557, B:279:0x0567, B:280:0x056b, B:282:0x0571, B:284:0x0581, B:285:0x0585, B:286:0x0588, B:287:0x058b, B:288:0x0591, B:290:0x0597, B:292:0x05a9, B:295:0x05b3, B:297:0x05b9, B:298:0x05bc, B:300:0x05c2, B:302:0x05ce, B:303:0x05d2, B:304:0x05d5, B:310:0x05ea, B:311:0x05f6), top: B:327:0x006f, inners: #0, #1, #2, #3, #5, #6, #8, #10 }] */
    /* JADX WARN: Code duplicated, block: B:102:0x022b A[Catch: CertPathReviewerException -> 0x05f7, TryCatch #7 {CertPathReviewerException -> 0x05f7, blocks: (B:17:0x006f, B:21:0x007f, B:23:0x008c, B:27:0x009c, B:28:0x00a7, B:30:0x00ad, B:32:0x00ce, B:33:0x00d6, B:35:0x00dc, B:37:0x00e1, B:38:0x00ed, B:42:0x00f9, B:45:0x0100, B:46:0x0109, B:48:0x010f, B:50:0x0119, B:53:0x0120, B:55:0x0124, B:95:0x0210, B:97:0x0216, B:98:0x0219, B:100:0x021f, B:102:0x022b, B:105:0x0233, B:106:0x0236, B:107:0x0239, B:109:0x023f, B:110:0x0248, B:112:0x024e, B:120:0x0271, B:121:0x027d, B:122:0x027e, B:124:0x0282, B:126:0x028a, B:127:0x028e, B:129:0x0294, B:132:0x02b6, B:134:0x02c0, B:135:0x02c5, B:136:0x02d1, B:137:0x02d2, B:138:0x02de, B:140:0x02e1, B:141:0x02ee, B:143:0x02f4, B:145:0x031a, B:147:0x0332, B:146:0x0329, B:148:0x0339, B:149:0x033f, B:151:0x0345, B:153:0x034d, B:164:0x0377, B:157:0x0355, B:158:0x0361, B:160:0x0363, B:161:0x0372, B:167:0x0380, B:178:0x039f, B:180:0x03a9, B:181:0x03ad, B:183:0x03b3, B:188:0x03c3, B:191:0x03d0, B:194:0x03dd, B:196:0x03e7, B:207:0x0425, B:199:0x03ef, B:200:0x03fd, B:201:0x03fe, B:202:0x040c, B:204:0x040e, B:205:0x041c, B:59:0x0133, B:60:0x0137, B:62:0x013d, B:64:0x0153, B:66:0x015d, B:67:0x0162, B:69:0x0168, B:70:0x0176, B:72:0x017c, B:74:0x0188, B:78:0x0195, B:79:0x019b, B:81:0x01a1, B:86:0x01ba, B:75:0x018b, B:77:0x018f, B:90:0x01f3, B:93:0x0203, B:94:0x020f, B:209:0x0434, B:210:0x0441, B:211:0x0442, B:215:0x0453, B:217:0x045d, B:218:0x0462, B:220:0x0468, B:223:0x0476, B:230:0x048b, B:308:0x05dd, B:309:0x05e9, B:233:0x0496, B:234:0x04a2, B:235:0x04a3, B:237:0x04a9, B:239:0x04b1, B:241:0x04b7, B:243:0x04c1, B:244:0x04c4, B:246:0x04ca, B:248:0x04da, B:249:0x04de, B:251:0x04e4, B:252:0x04ec, B:253:0x04ef, B:254:0x04f2, B:255:0x04f6, B:257:0x04fc, B:259:0x050c, B:261:0x0514, B:262:0x0517, B:264:0x051d, B:266:0x0529, B:267:0x052d, B:268:0x0530, B:269:0x0533, B:270:0x053f, B:272:0x0544, B:274:0x054e, B:275:0x0551, B:277:0x0557, B:279:0x0567, B:280:0x056b, B:282:0x0571, B:284:0x0581, B:285:0x0585, B:286:0x0588, B:287:0x058b, B:288:0x0591, B:290:0x0597, B:292:0x05a9, B:295:0x05b3, B:297:0x05b9, B:298:0x05bc, B:300:0x05c2, B:302:0x05ce, B:303:0x05d2, B:304:0x05d5, B:310:0x05ea, B:311:0x05f6), top: B:327:0x006f, inners: #0, #1, #2, #3, #5, #6, #8, #10 }] */
    /* JADX WARN: Code duplicated, block: B:109:0x023f A[Catch: CertPathReviewerException -> 0x05f7, TryCatch #7 {CertPathReviewerException -> 0x05f7, blocks: (B:17:0x006f, B:21:0x007f, B:23:0x008c, B:27:0x009c, B:28:0x00a7, B:30:0x00ad, B:32:0x00ce, B:33:0x00d6, B:35:0x00dc, B:37:0x00e1, B:38:0x00ed, B:42:0x00f9, B:45:0x0100, B:46:0x0109, B:48:0x010f, B:50:0x0119, B:53:0x0120, B:55:0x0124, B:95:0x0210, B:97:0x0216, B:98:0x0219, B:100:0x021f, B:102:0x022b, B:105:0x0233, B:106:0x0236, B:107:0x0239, B:109:0x023f, B:110:0x0248, B:112:0x024e, B:120:0x0271, B:121:0x027d, B:122:0x027e, B:124:0x0282, B:126:0x028a, B:127:0x028e, B:129:0x0294, B:132:0x02b6, B:134:0x02c0, B:135:0x02c5, B:136:0x02d1, B:137:0x02d2, B:138:0x02de, B:140:0x02e1, B:141:0x02ee, B:143:0x02f4, B:145:0x031a, B:147:0x0332, B:146:0x0329, B:148:0x0339, B:149:0x033f, B:151:0x0345, B:153:0x034d, B:164:0x0377, B:157:0x0355, B:158:0x0361, B:160:0x0363, B:161:0x0372, B:167:0x0380, B:178:0x039f, B:180:0x03a9, B:181:0x03ad, B:183:0x03b3, B:188:0x03c3, B:191:0x03d0, B:194:0x03dd, B:196:0x03e7, B:207:0x0425, B:199:0x03ef, B:200:0x03fd, B:201:0x03fe, B:202:0x040c, B:204:0x040e, B:205:0x041c, B:59:0x0133, B:60:0x0137, B:62:0x013d, B:64:0x0153, B:66:0x015d, B:67:0x0162, B:69:0x0168, B:70:0x0176, B:72:0x017c, B:74:0x0188, B:78:0x0195, B:79:0x019b, B:81:0x01a1, B:86:0x01ba, B:75:0x018b, B:77:0x018f, B:90:0x01f3, B:93:0x0203, B:94:0x020f, B:209:0x0434, B:210:0x0441, B:211:0x0442, B:215:0x0453, B:217:0x045d, B:218:0x0462, B:220:0x0468, B:223:0x0476, B:230:0x048b, B:308:0x05dd, B:309:0x05e9, B:233:0x0496, B:234:0x04a2, B:235:0x04a3, B:237:0x04a9, B:239:0x04b1, B:241:0x04b7, B:243:0x04c1, B:244:0x04c4, B:246:0x04ca, B:248:0x04da, B:249:0x04de, B:251:0x04e4, B:252:0x04ec, B:253:0x04ef, B:254:0x04f2, B:255:0x04f6, B:257:0x04fc, B:259:0x050c, B:261:0x0514, B:262:0x0517, B:264:0x051d, B:266:0x0529, B:267:0x052d, B:268:0x0530, B:269:0x0533, B:270:0x053f, B:272:0x0544, B:274:0x054e, B:275:0x0551, B:277:0x0557, B:279:0x0567, B:280:0x056b, B:282:0x0571, B:284:0x0581, B:285:0x0585, B:286:0x0588, B:287:0x058b, B:288:0x0591, B:290:0x0597, B:292:0x05a9, B:295:0x05b3, B:297:0x05b9, B:298:0x05bc, B:300:0x05c2, B:302:0x05ce, B:303:0x05d2, B:304:0x05d5, B:310:0x05ea, B:311:0x05f6), top: B:327:0x006f, inners: #0, #1, #2, #3, #5, #6, #8, #10 }] */
    /* JADX WARN: Code duplicated, block: B:112:0x024e A[Catch: CertPathReviewerException -> 0x05f7, LOOP:6: B:110:0x0248->B:112:0x024e, LOOP_END, TryCatch #7 {CertPathReviewerException -> 0x05f7, blocks: (B:17:0x006f, B:21:0x007f, B:23:0x008c, B:27:0x009c, B:28:0x00a7, B:30:0x00ad, B:32:0x00ce, B:33:0x00d6, B:35:0x00dc, B:37:0x00e1, B:38:0x00ed, B:42:0x00f9, B:45:0x0100, B:46:0x0109, B:48:0x010f, B:50:0x0119, B:53:0x0120, B:55:0x0124, B:95:0x0210, B:97:0x0216, B:98:0x0219, B:100:0x021f, B:102:0x022b, B:105:0x0233, B:106:0x0236, B:107:0x0239, B:109:0x023f, B:110:0x0248, B:112:0x024e, B:120:0x0271, B:121:0x027d, B:122:0x027e, B:124:0x0282, B:126:0x028a, B:127:0x028e, B:129:0x0294, B:132:0x02b6, B:134:0x02c0, B:135:0x02c5, B:136:0x02d1, B:137:0x02d2, B:138:0x02de, B:140:0x02e1, B:141:0x02ee, B:143:0x02f4, B:145:0x031a, B:147:0x0332, B:146:0x0329, B:148:0x0339, B:149:0x033f, B:151:0x0345, B:153:0x034d, B:164:0x0377, B:157:0x0355, B:158:0x0361, B:160:0x0363, B:161:0x0372, B:167:0x0380, B:178:0x039f, B:180:0x03a9, B:181:0x03ad, B:183:0x03b3, B:188:0x03c3, B:191:0x03d0, B:194:0x03dd, B:196:0x03e7, B:207:0x0425, B:199:0x03ef, B:200:0x03fd, B:201:0x03fe, B:202:0x040c, B:204:0x040e, B:205:0x041c, B:59:0x0133, B:60:0x0137, B:62:0x013d, B:64:0x0153, B:66:0x015d, B:67:0x0162, B:69:0x0168, B:70:0x0176, B:72:0x017c, B:74:0x0188, B:78:0x0195, B:79:0x019b, B:81:0x01a1, B:86:0x01ba, B:75:0x018b, B:77:0x018f, B:90:0x01f3, B:93:0x0203, B:94:0x020f, B:209:0x0434, B:210:0x0441, B:211:0x0442, B:215:0x0453, B:217:0x045d, B:218:0x0462, B:220:0x0468, B:223:0x0476, B:230:0x048b, B:308:0x05dd, B:309:0x05e9, B:233:0x0496, B:234:0x04a2, B:235:0x04a3, B:237:0x04a9, B:239:0x04b1, B:241:0x04b7, B:243:0x04c1, B:244:0x04c4, B:246:0x04ca, B:248:0x04da, B:249:0x04de, B:251:0x04e4, B:252:0x04ec, B:253:0x04ef, B:254:0x04f2, B:255:0x04f6, B:257:0x04fc, B:259:0x050c, B:261:0x0514, B:262:0x0517, B:264:0x051d, B:266:0x0529, B:267:0x052d, B:268:0x0530, B:269:0x0533, B:270:0x053f, B:272:0x0544, B:274:0x054e, B:275:0x0551, B:277:0x0557, B:279:0x0567, B:280:0x056b, B:282:0x0571, B:284:0x0581, B:285:0x0585, B:286:0x0588, B:287:0x058b, B:288:0x0591, B:290:0x0597, B:292:0x05a9, B:295:0x05b3, B:297:0x05b9, B:298:0x05bc, B:300:0x05c2, B:302:0x05ce, B:303:0x05d2, B:304:0x05d5, B:310:0x05ea, B:311:0x05f6), top: B:327:0x006f, inners: #0, #1, #2, #3, #5, #6, #8, #10 }] */
    /* JADX WARN: Code duplicated, block: B:271:0x0540  */
    /* JADX WARN: Code duplicated, block: B:353:0x0232 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:354:0x0233 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:355:0x0233 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:97:0x0216 A[Catch: CertPathReviewerException -> 0x05f7, TryCatch #7 {CertPathReviewerException -> 0x05f7, blocks: (B:17:0x006f, B:21:0x007f, B:23:0x008c, B:27:0x009c, B:28:0x00a7, B:30:0x00ad, B:32:0x00ce, B:33:0x00d6, B:35:0x00dc, B:37:0x00e1, B:38:0x00ed, B:42:0x00f9, B:45:0x0100, B:46:0x0109, B:48:0x010f, B:50:0x0119, B:53:0x0120, B:55:0x0124, B:95:0x0210, B:97:0x0216, B:98:0x0219, B:100:0x021f, B:102:0x022b, B:105:0x0233, B:106:0x0236, B:107:0x0239, B:109:0x023f, B:110:0x0248, B:112:0x024e, B:120:0x0271, B:121:0x027d, B:122:0x027e, B:124:0x0282, B:126:0x028a, B:127:0x028e, B:129:0x0294, B:132:0x02b6, B:134:0x02c0, B:135:0x02c5, B:136:0x02d1, B:137:0x02d2, B:138:0x02de, B:140:0x02e1, B:141:0x02ee, B:143:0x02f4, B:145:0x031a, B:147:0x0332, B:146:0x0329, B:148:0x0339, B:149:0x033f, B:151:0x0345, B:153:0x034d, B:164:0x0377, B:157:0x0355, B:158:0x0361, B:160:0x0363, B:161:0x0372, B:167:0x0380, B:178:0x039f, B:180:0x03a9, B:181:0x03ad, B:183:0x03b3, B:188:0x03c3, B:191:0x03d0, B:194:0x03dd, B:196:0x03e7, B:207:0x0425, B:199:0x03ef, B:200:0x03fd, B:201:0x03fe, B:202:0x040c, B:204:0x040e, B:205:0x041c, B:59:0x0133, B:60:0x0137, B:62:0x013d, B:64:0x0153, B:66:0x015d, B:67:0x0162, B:69:0x0168, B:70:0x0176, B:72:0x017c, B:74:0x0188, B:78:0x0195, B:79:0x019b, B:81:0x01a1, B:86:0x01ba, B:75:0x018b, B:77:0x018f, B:90:0x01f3, B:93:0x0203, B:94:0x020f, B:209:0x0434, B:210:0x0441, B:211:0x0442, B:215:0x0453, B:217:0x045d, B:218:0x0462, B:220:0x0468, B:223:0x0476, B:230:0x048b, B:308:0x05dd, B:309:0x05e9, B:233:0x0496, B:234:0x04a2, B:235:0x04a3, B:237:0x04a9, B:239:0x04b1, B:241:0x04b7, B:243:0x04c1, B:244:0x04c4, B:246:0x04ca, B:248:0x04da, B:249:0x04de, B:251:0x04e4, B:252:0x04ec, B:253:0x04ef, B:254:0x04f2, B:255:0x04f6, B:257:0x04fc, B:259:0x050c, B:261:0x0514, B:262:0x0517, B:264:0x051d, B:266:0x0529, B:267:0x052d, B:268:0x0530, B:269:0x0533, B:270:0x053f, B:272:0x0544, B:274:0x054e, B:275:0x0551, B:277:0x0557, B:279:0x0567, B:280:0x056b, B:282:0x0571, B:284:0x0581, B:285:0x0585, B:286:0x0588, B:287:0x058b, B:288:0x0591, B:290:0x0597, B:292:0x05a9, B:295:0x05b3, B:297:0x05b9, B:298:0x05bc, B:300:0x05c2, B:302:0x05ce, B:303:0x05d2, B:304:0x05d5, B:310:0x05ea, B:311:0x05f6), top: B:327:0x006f, inners: #0, #1, #2, #3, #5, #6, #8, #10 }] */
    private void checkPolicy() {
        int i;
        int i2;
        int i3;
        PKIXPolicyNode pKIXPolicyNodeRemovePolicyNode;
        String str;
        int i4;
        int i5;
        HashSet hashSet;
        String str2;
        int i6;
        int iIntValueExact;
        int iIntValueExact2;
        String str3;
        HashSet hashSet2;
        String id;
        int i7;
        Set<String> criticalExtensionOIDs;
        boolean zContains;
        ArrayList arrayList;
        int i8;
        ArrayList arrayList2;
        int i9;
        PKIXPolicyNode pKIXPolicyNode;
        PKIXPolicyNode pKIXPolicyNodeRemovePolicyNode2;
        String str4 = "CertPathReviewer.policyExtError";
        Set<String> initialPolicies = this.pkixParams.getInitialPolicies();
        int i10 = this.n + 1;
        ArrayList[] arrayListArr = new ArrayList[i10];
        for (int i11 = 0; i11 < i10; i11++) {
            arrayListArr[i11] = new ArrayList();
        }
        HashSet hashSet3 = new HashSet();
        hashSet3.add(RFC3280CertPathUtilities.ANY_POLICY);
        PKIXPolicyNode pKIXPolicyNode2 = new PKIXPolicyNode(new ArrayList(), 0, hashSet3, null, new HashSet(), RFC3280CertPathUtilities.ANY_POLICY, false);
        arrayListArr[0].add(pKIXPolicyNode2);
        if (this.pkixParams.isExplicitPolicyRequired()) {
            i2 = 0;
            i = 1;
        } else {
            i = 1;
            i2 = this.n + 1;
        }
        int i12 = this.pkixParams.isAnyPolicyInhibited() ? 0 : this.n + i;
        int i13 = this.pkixParams.isPolicyMappingInhibited() ? 0 : this.n + i;
        try {
            int size = this.certs.size() - i;
            PKIXPolicyNode pKIXPolicyNodePrepareNextCertB2 = pKIXPolicyNode2;
            X509Certificate x509Certificate = null;
            HashSet hashSet4 = null;
            while (size >= 0) {
                int i14 = this.n - size;
                X509Certificate x509Certificate2 = (X509Certificate) this.certs.get(size);
                int i15 = i10;
                try {
                    ASN1Sequence aSN1Sequence = (ASN1Sequence) getExtensionValue(x509Certificate2, CERTIFICATE_POLICIES);
                    if (aSN1Sequence == null || pKIXPolicyNodePrepareNextCertB2 == null) {
                        str = str4;
                        i4 = i12;
                        i5 = i13;
                        pKIXPolicyNodePrepareNextCertB2 = pKIXPolicyNodePrepareNextCertB2;
                    } else {
                        Enumeration objects = aSN1Sequence.getObjects();
                        HashSet hashSet5 = new HashSet();
                        while (objects.hasMoreElements()) {
                            PolicyInformation policyInformation = PolicyInformation.getInstance(objects.nextElement());
                            PKIXPolicyNode pKIXPolicyNode3 = pKIXPolicyNodePrepareNextCertB2;
                            ASN1ObjectIdentifier policyIdentifier = policyInformation.getPolicyIdentifier();
                            String str5 = str4;
                            hashSet5.add(policyIdentifier.getId());
                            if (!RFC3280CertPathUtilities.ANY_POLICY.equals(policyIdentifier.getId())) {
                                try {
                                    Set qualifierSet = getQualifierSet(policyInformation.getPolicyQualifiers());
                                    if (!processCertD1i(i14, arrayListArr, policyIdentifier, qualifierSet)) {
                                        processCertD1ii(i14, arrayListArr, policyIdentifier, qualifierSet);
                                    }
                                } catch (CertPathValidatorException e) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyQualifierError"), e, this.certPath, size);
                                }
                            }
                            pKIXPolicyNodePrepareNextCertB2 = pKIXPolicyNode3;
                            str4 = str5;
                        }
                        str = str4;
                        PKIXPolicyNode pKIXPolicyNode4 = pKIXPolicyNodePrepareNextCertB2;
                        if (hashSet4 == null || hashSet4.contains(RFC3280CertPathUtilities.ANY_POLICY)) {
                            hashSet2 = hashSet5;
                        } else {
                            hashSet2 = new HashSet();
                            for (Object obj : hashSet4) {
                                if (hashSet5.contains(obj)) {
                                    hashSet2.add(obj);
                                }
                            }
                        }
                        if (i12 > 0 || (i14 < this.n && isSelfIssued(x509Certificate2))) {
                            Enumeration objects2 = aSN1Sequence.getObjects();
                            while (true) {
                                if (!objects2.hasMoreElements()) {
                                    i4 = i12;
                                    i5 = i13;
                                    break;
                                }
                                PolicyInformation policyInformation2 = PolicyInformation.getInstance(objects2.nextElement());
                                if (RFC3280CertPathUtilities.ANY_POLICY.equals(policyInformation2.getPolicyIdentifier().getId())) {
                                    try {
                                        Set qualifierSet2 = getQualifierSet(policyInformation2.getPolicyQualifiers());
                                        ArrayList arrayList3 = arrayListArr[i14 - 1];
                                        for (int i16 = 0; i16 < arrayList3.size(); i16++) {
                                            PKIXPolicyNode pKIXPolicyNode5 = (PKIXPolicyNode) arrayList3.get(i16);
                                            for (Object obj2 : pKIXPolicyNode5.getExpectedPolicies()) {
                                                arrayList3 = arrayList3;
                                                i12 = i12;
                                                if (obj2 instanceof String) {
                                                    id = (String) obj2;
                                                } else if (obj2 instanceof ASN1ObjectIdentifier) {
                                                    id = ((ASN1ObjectIdentifier) obj2).getId();
                                                }
                                                Iterator children = pKIXPolicyNode5.getChildren();
                                                boolean z = false;
                                                while (children.hasNext()) {
                                                    Iterator it = children;
                                                    if (id.equals(((PKIXPolicyNode) children.next()).getValidPolicy())) {
                                                        z = true;
                                                    }
                                                    children = it;
                                                }
                                                if (!z) {
                                                    HashSet hashSet6 = new HashSet();
                                                    hashSet6.add(id);
                                                    PKIXPolicyNode pKIXPolicyNode6 = new PKIXPolicyNode(new ArrayList(), i14, hashSet6, pKIXPolicyNode5, qualifierSet2, id, false);
                                                    pKIXPolicyNode5.addChild(pKIXPolicyNode6);
                                                    arrayListArr[i14].add(pKIXPolicyNode6);
                                                }
                                                i13 = i13;
                                            }
                                        }
                                        i4 = i12;
                                        i5 = i13;
                                        break;
                                    } catch (CertPathValidatorException e2) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyQualifierError"), e2, this.certPath, size);
                                    }
                                }
                            }
                            pKIXPolicyNodePrepareNextCertB2 = pKIXPolicyNode4;
                            for (i7 = i14 - 1; i7 >= 0; i7--) {
                                arrayList2 = arrayListArr[i7];
                                for (i9 = 0; i9 < arrayList2.size(); i9++) {
                                    pKIXPolicyNode = (PKIXPolicyNode) arrayList2.get(i9);
                                    if (!pKIXPolicyNode.hasChildren()) {
                                        pKIXPolicyNodeRemovePolicyNode2 = removePolicyNode(pKIXPolicyNodePrepareNextCertB2, arrayListArr, pKIXPolicyNode);
                                        pKIXPolicyNodePrepareNextCertB2 = pKIXPolicyNodeRemovePolicyNode2;
                                        if (pKIXPolicyNodeRemovePolicyNode2 == null) {
                                            break;
                                        }
                                    }
                                }
                            }
                            criticalExtensionOIDs = x509Certificate2.getCriticalExtensionOIDs();
                            if (criticalExtensionOIDs != null) {
                                zContains = criticalExtensionOIDs.contains(CERTIFICATE_POLICIES);
                                arrayList = arrayListArr[i14];
                                for (i8 = 0; i8 < arrayList.size(); i8++) {
                                    ((PKIXPolicyNode) arrayList.get(i8)).setCritical(zContains);
                                }
                            }
                            hashSet4 = hashSet2;
                        } else {
                            i4 = i12;
                            i5 = i13;
                            pKIXPolicyNodePrepareNextCertB2 = pKIXPolicyNode4;
                            while (i7 >= 0) {
                                arrayList2 = arrayListArr[i7];
                                while (i9 < arrayList2.size()) {
                                    pKIXPolicyNode = (PKIXPolicyNode) arrayList2.get(i9);
                                    if (!pKIXPolicyNode.hasChildren()) {
                                        pKIXPolicyNodeRemovePolicyNode2 = removePolicyNode(pKIXPolicyNodePrepareNextCertB2, arrayListArr, pKIXPolicyNode);
                                        pKIXPolicyNodePrepareNextCertB2 = pKIXPolicyNodeRemovePolicyNode2;
                                        if (pKIXPolicyNodeRemovePolicyNode2 == null) {
                                            break;
                                            break;
                                        }
                                    }
                                }
                            }
                            criticalExtensionOIDs = x509Certificate2.getCriticalExtensionOIDs();
                            if (criticalExtensionOIDs != null) {
                                zContains = criticalExtensionOIDs.contains(CERTIFICATE_POLICIES);
                                arrayList = arrayListArr[i14];
                                while (i8 < arrayList.size()) {
                                    ((PKIXPolicyNode) arrayList.get(i8)).setCritical(zContains);
                                }
                            }
                            hashSet4 = hashSet2;
                        }
                    }
                    if (aSN1Sequence == null) {
                        pKIXPolicyNodePrepareNextCertB2 = null;
                    }
                    if (i2 <= 0 && pKIXPolicyNodePrepareNextCertB2 == null) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noValidPolicyTree"));
                    }
                    if (i14 != this.n) {
                        try {
                            ASN1Primitive extensionValue = getExtensionValue(x509Certificate2, POLICY_MAPPINGS);
                            if (extensionValue != null) {
                                ASN1Sequence aSN1Sequence2 = (ASN1Sequence) extensionValue;
                                int i17 = 0;
                                while (i17 < aSN1Sequence2.size()) {
                                    ASN1Sequence aSN1Sequence3 = (ASN1Sequence) aSN1Sequence2.getObjectAt(i17);
                                    ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) aSN1Sequence3.getObjectAt(0);
                                    ASN1ObjectIdentifier aSN1ObjectIdentifier2 = (ASN1ObjectIdentifier) aSN1Sequence3.getObjectAt(1);
                                    boolean zEquals = RFC3280CertPathUtilities.ANY_POLICY.equals(aSN1ObjectIdentifier.getId());
                                    ASN1Sequence aSN1Sequence4 = aSN1Sequence2;
                                    if (zEquals) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.invalidPolicyMapping"), this.certPath, size);
                                    }
                                    if (RFC3280CertPathUtilities.ANY_POLICY.equals(aSN1ObjectIdentifier2.getId())) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.invalidPolicyMapping"), this.certPath, size);
                                    }
                                    i17++;
                                    aSN1Sequence2 = aSN1Sequence4;
                                }
                            }
                            if (extensionValue != null) {
                                ASN1Sequence aSN1Sequence5 = (ASN1Sequence) extensionValue;
                                HashMap map = new HashMap();
                                HashSet<String> hashSet7 = new HashSet();
                                int i18 = 0;
                                while (i18 < aSN1Sequence5.size()) {
                                    ASN1Sequence aSN1Sequence6 = (ASN1Sequence) aSN1Sequence5.getObjectAt(i18);
                                    ASN1Sequence aSN1Sequence7 = aSN1Sequence5;
                                    String id2 = ((ASN1ObjectIdentifier) aSN1Sequence6.getObjectAt(0)).getId();
                                    HashSet hashSet8 = hashSet4;
                                    String id3 = ((ASN1ObjectIdentifier) aSN1Sequence6.getObjectAt(1)).getId();
                                    if (map.containsKey(id2)) {
                                        ((Set) map.get(id2)).add(id3);
                                    } else {
                                        HashSet hashSet9 = new HashSet();
                                        hashSet9.add(id3);
                                        map.put(id2, hashSet9);
                                        hashSet7.add(id2);
                                    }
                                    i18++;
                                    aSN1Sequence5 = aSN1Sequence7;
                                    hashSet4 = hashSet8;
                                }
                                hashSet = hashSet4;
                                for (String str6 : hashSet7) {
                                    if (i5 > 0) {
                                        try {
                                            prepareNextCertB1(i14, arrayListArr, str6, map, x509Certificate2);
                                            str3 = str;
                                        } catch (CertPathValidatorException e3) {
                                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyQualifierError"), e3, this.certPath, size);
                                        } catch (AnnotatedException e4) {
                                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, str), e4, this.certPath, size);
                                        }
                                    } else {
                                        str3 = str;
                                        if (i5 <= 0) {
                                            pKIXPolicyNodePrepareNextCertB2 = prepareNextCertB2(i14, arrayListArr, str6, pKIXPolicyNodePrepareNextCertB2);
                                        }
                                    }
                                    str = str3;
                                }
                            } else {
                                hashSet = hashSet4;
                            }
                            str2 = str;
                            if (isSelfIssued(x509Certificate2)) {
                                i6 = i4;
                                i13 = i5;
                            } else {
                                if (i2 != 0) {
                                    i2--;
                                }
                                i13 = i5 != 0 ? i5 - 1 : i5;
                                i6 = i4 != 0 ? i4 - 1 : i4;
                            }
                            try {
                                ASN1Sequence aSN1Sequence8 = (ASN1Sequence) getExtensionValue(x509Certificate2, POLICY_CONSTRAINTS);
                                if (aSN1Sequence8 != null) {
                                    Enumeration objects3 = aSN1Sequence8.getObjects();
                                    while (objects3.hasMoreElements()) {
                                        ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) objects3.nextElement();
                                        int tagNo = aSN1TaggedObject.getTagNo();
                                        if (tagNo == 0) {
                                            int iIntValueExact3 = ASN1Integer.getInstance(aSN1TaggedObject, false).intValueExact();
                                            if (iIntValueExact3 < i2) {
                                                i2 = iIntValueExact3;
                                            }
                                        } else if (tagNo == 1 && (iIntValueExact2 = ASN1Integer.getInstance(aSN1TaggedObject, false).intValueExact()) < i13) {
                                            i13 = iIntValueExact2;
                                        }
                                    }
                                }
                                try {
                                    ASN1Integer aSN1Integer = (ASN1Integer) getExtensionValue(x509Certificate2, INHIBIT_ANY_POLICY);
                                    if (aSN1Integer != null && (iIntValueExact = aSN1Integer.intValueExact()) < i6) {
                                        i6 = iIntValueExact;
                                    }
                                } catch (AnnotatedException unused) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyInhibitExtError"), this.certPath, size);
                                }
                            } catch (AnnotatedException unused2) {
                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyConstExtError"), this.certPath, size);
                            }
                        } catch (AnnotatedException e5) {
                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyMapExtError"), e5, this.certPath, size);
                        }
                    } else {
                        hashSet = hashSet4;
                        str2 = str;
                        i6 = i4;
                        i13 = i5;
                    }
                    size--;
                    x509Certificate = x509Certificate2;
                    str4 = str2;
                    hashSet4 = hashSet;
                    i10 = i15;
                    i12 = i6;
                    initialPolicies = initialPolicies;
                } catch (AnnotatedException e6) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, str4), e6, this.certPath, size);
                }
            }
            Set<String> set = initialPolicies;
            int i19 = i10;
            PKIXPolicyNode pKIXPolicyNode7 = pKIXPolicyNodePrepareNextCertB2;
            if (!isSelfIssued(x509Certificate) && i2 > 0) {
                i2--;
            }
            try {
                ASN1Sequence aSN1Sequence9 = (ASN1Sequence) getExtensionValue(x509Certificate, POLICY_CONSTRAINTS);
                if (aSN1Sequence9 != null) {
                    Enumeration objects4 = aSN1Sequence9.getObjects();
                    int i20 = i2;
                    while (objects4.hasMoreElements()) {
                        ASN1TaggedObject aSN1TaggedObject2 = (ASN1TaggedObject) objects4.nextElement();
                        if (aSN1TaggedObject2.getTagNo() == 0 && ASN1Integer.getInstance(aSN1TaggedObject2, false).intValueExact() == 0) {
                            i20 = 0;
                        }
                    }
                    i3 = 0;
                    i2 = i20;
                } else {
                    i3 = 0;
                }
                if (pKIXPolicyNode7 == null) {
                    if (this.pkixParams.isExplicitPolicyRequired()) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.explicitPolicy"), this.certPath, size);
                    }
                    pKIXPolicyNodeRemovePolicyNode = null;
                } else if (!isAnyPolicy(set)) {
                    HashSet<PKIXPolicyNode> hashSet10 = new HashSet();
                    for (int i21 = i3; i21 < i19; i21++) {
                        ArrayList arrayList4 = arrayListArr[i21];
                        for (int i22 = i3; i22 < arrayList4.size(); i22++) {
                            PKIXPolicyNode pKIXPolicyNode8 = (PKIXPolicyNode) arrayList4.get(i22);
                            if (RFC3280CertPathUtilities.ANY_POLICY.equals(pKIXPolicyNode8.getValidPolicy())) {
                                Iterator children2 = pKIXPolicyNode8.getChildren();
                                while (children2.hasNext()) {
                                    PKIXPolicyNode pKIXPolicyNode9 = (PKIXPolicyNode) children2.next();
                                    if (!RFC3280CertPathUtilities.ANY_POLICY.equals(pKIXPolicyNode9.getValidPolicy())) {
                                        hashSet10.add(pKIXPolicyNode9);
                                    }
                                }
                            }
                        }
                    }
                    pKIXPolicyNodeRemovePolicyNode = pKIXPolicyNode7;
                    for (PKIXPolicyNode pKIXPolicyNode10 : hashSet10) {
                        Set<String> set2 = set;
                        if (!set2.contains(pKIXPolicyNode10.getValidPolicy())) {
                            pKIXPolicyNodeRemovePolicyNode = removePolicyNode(pKIXPolicyNodeRemovePolicyNode, arrayListArr, pKIXPolicyNode10);
                        }
                        set = set2;
                    }
                    if (pKIXPolicyNodeRemovePolicyNode != null) {
                        for (int i23 = this.n - 1; i23 >= 0; i23--) {
                            ArrayList arrayList5 = arrayListArr[i23];
                            for (int i24 = i3; i24 < arrayList5.size(); i24++) {
                                PKIXPolicyNode pKIXPolicyNode11 = (PKIXPolicyNode) arrayList5.get(i24);
                                if (!pKIXPolicyNode11.hasChildren()) {
                                    pKIXPolicyNodeRemovePolicyNode = removePolicyNode(pKIXPolicyNodeRemovePolicyNode, arrayListArr, pKIXPolicyNode11);
                                }
                            }
                        }
                    }
                } else if (!this.pkixParams.isExplicitPolicyRequired()) {
                    pKIXPolicyNodeRemovePolicyNode = pKIXPolicyNode7;
                } else {
                    if (hashSet4.isEmpty()) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.explicitPolicy"), this.certPath, size);
                    }
                    HashSet hashSet11 = new HashSet();
                    for (int i25 = i3; i25 < i19; i25++) {
                        ArrayList arrayList6 = arrayListArr[i25];
                        for (int i26 = i3; i26 < arrayList6.size(); i26++) {
                            PKIXPolicyNode pKIXPolicyNode12 = (PKIXPolicyNode) arrayList6.get(i26);
                            if (RFC3280CertPathUtilities.ANY_POLICY.equals(pKIXPolicyNode12.getValidPolicy())) {
                                Iterator children3 = pKIXPolicyNode12.getChildren();
                                while (children3.hasNext()) {
                                    hashSet11.add(children3.next());
                                }
                            }
                        }
                    }
                    Iterator it2 = hashSet11.iterator();
                    while (it2.hasNext()) {
                        hashSet4.contains(((PKIXPolicyNode) it2.next()).getValidPolicy());
                    }
                    if (pKIXPolicyNode7 != null) {
                        pKIXPolicyNodeRemovePolicyNode = pKIXPolicyNode7;
                        for (int i27 = this.n - 1; i27 >= 0; i27--) {
                            ArrayList arrayList7 = arrayListArr[i27];
                            for (int i28 = i3; i28 < arrayList7.size(); i28++) {
                                PKIXPolicyNode pKIXPolicyNode13 = (PKIXPolicyNode) arrayList7.get(i28);
                                if (!pKIXPolicyNode13.hasChildren()) {
                                    pKIXPolicyNodeRemovePolicyNode = removePolicyNode(pKIXPolicyNodeRemovePolicyNode, arrayListArr, pKIXPolicyNode13);
                                }
                            }
                        }
                    } else {
                        pKIXPolicyNodeRemovePolicyNode = pKIXPolicyNode7;
                    }
                }
                if (i2 <= 0 && pKIXPolicyNodeRemovePolicyNode == null) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.invalidPolicy"));
                }
            } catch (AnnotatedException unused3) {
                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyConstExtError"), this.certPath, size);
            }
        } catch (CertPathReviewerException e7) {
            addError(e7.getErrorMessage(), e7.getIndex());
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x02c9 A[LOOP:1: B:98:0x02c3->B:100:0x02c9, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:104:0x02ec A[LOOP:2: B:102:0x02e6->B:104:0x02ec, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:114:0x0337  */
    /* JADX WARN: Code duplicated, block: B:119:0x036a  */
    /* JADX WARN: Code duplicated, block: B:122:0x0371  */
    /* JADX WARN: Code duplicated, block: B:124:0x0375  */
    /* JADX WARN: Code duplicated, block: B:126:0x037c  */
    /* JADX WARN: Code duplicated, block: B:127:0x0385  */
    /* JADX WARN: Code duplicated, block: B:130:0x0392 A[Catch: AnnotatedException -> 0x03ac, TryCatch #3 {AnnotatedException -> 0x03ac, blocks: (B:128:0x0386, B:130:0x0392, B:132:0x0398, B:133:0x03a1), top: B:156:0x0386 }] */
    /* JADX WARN: Code duplicated, block: B:132:0x0398 A[Catch: AnnotatedException -> 0x03ac, TryCatch #3 {AnnotatedException -> 0x03ac, blocks: (B:128:0x0386, B:130:0x0392, B:132:0x0398, B:133:0x03a1), top: B:156:0x0386 }] */
    /* JADX WARN: Code duplicated, block: B:133:0x03a1 A[Catch: AnnotatedException -> 0x03ac, TRY_LEAVE, TryCatch #3 {AnnotatedException -> 0x03ac, blocks: (B:128:0x0386, B:130:0x0392, B:132:0x0398, B:133:0x03a1), top: B:156:0x0386 }] */
    /* JADX WARN: Code duplicated, block: B:138:0x03bc  */
    /* JADX WARN: Code duplicated, block: B:142:0x03c4  */
    /* JADX WARN: Code duplicated, block: B:143:0x03cf  */
    /* JADX WARN: Code duplicated, block: B:168:0x0188 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:170:0x01b8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:177:0x0287 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:31:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:33:0x00fd A[Catch: IllegalArgumentException -> 0x010c, TRY_ENTER, TryCatch #16 {IllegalArgumentException -> 0x010c, blocks: (B:33:0x00fd, B:34:0x0102), top: B:173:0x00fb }] */
    /* JADX WARN: Code duplicated, block: B:34:0x0102 A[Catch: IllegalArgumentException -> 0x010c, TRY_LEAVE, TryCatch #16 {IllegalArgumentException -> 0x010c, blocks: (B:33:0x00fd, B:34:0x0102), top: B:173:0x00fb }] */
    /* JADX WARN: Code duplicated, block: B:45:0x013e  */
    /* JADX WARN: Code duplicated, block: B:47:0x0141  */
    /* JADX WARN: Code duplicated, block: B:49:0x0147  */
    /* JADX WARN: Code duplicated, block: B:50:0x014c  */
    /* JADX WARN: Code duplicated, block: B:54:0x0166  */
    /* JADX WARN: Code duplicated, block: B:57:0x0176  */
    /* JADX WARN: Code duplicated, block: B:64:0x01b2  */
    /* JADX WARN: Code duplicated, block: B:70:0x01ec  */
    /* JADX WARN: Code duplicated, block: B:76:0x021d  */
    /* JADX WARN: Code duplicated, block: B:87:0x028f A[Catch: AnnotatedException -> 0x0294, TRY_LEAVE, TryCatch #18 {AnnotatedException -> 0x0294, blocks: (B:85:0x0287, B:87:0x028f), top: B:177:0x0287 }] */
    /* JADX WARN: Code duplicated, block: B:90:0x029e  */
    /* JADX WARN: Code duplicated, block: B:93:0x02a7 A[Catch: AnnotatedException -> 0x02ac, TRY_LEAVE, TryCatch #4 {AnnotatedException -> 0x02ac, blocks: (B:91:0x029f, B:93:0x02a7), top: B:158:0x029f }] */
    /* JADX WARN: Code duplicated, block: B:96:0x02b6  */
    private void checkSignatures() {
        TrustAnchor trustAnchor;
        TrustAnchor trustAnchor2;
        X500Principal x500Principal;
        X509Certificate trustedCert;
        PublicKey cAPublicKey;
        X509Certificate x509Certificate;
        X500Principal subjectX500Principal;
        PublicKey nextWorkingKey;
        int size;
        int i;
        X509Certificate x509Certificate2;
        ErrorBundle errorBundle;
        ErrorBundle errorBundle2;
        CRLDistPoint cRLDistPoint;
        AuthorityInformationAccess authorityInformationAccess;
        Iterator it;
        Iterator it2;
        X509Certificate x509Certificate3;
        int i2;
        int i3;
        PublicKey publicKey;
        X500Principal x500Principal2;
        TrustAnchor trustAnchor3;
        int i4;
        ASN1Primitive extensionValue;
        ASN1Primitive extensionValue2;
        char c;
        int i5;
        char c2;
        boolean[] keyUsage;
        BasicConstraints basicConstraints;
        ErrorBundle errorBundle3;
        byte[] extensionValue3;
        AuthorityKeyIdentifier authorityKeyIdentifier;
        GeneralNames authorityCertIssuer;
        GeneralName generalName;
        BigInteger authorityCertSerialNumber;
        X509Certificate trustedCert2;
        boolean[] keyUsage2;
        char c3 = 2;
        int i6 = 1;
        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certPathValidDate", new Object[]{new TrustedInput(this.validDate), new TrustedInput(this.currentDate)}));
        try {
            List list = this.certs;
            X509Certificate x509Certificate4 = (X509Certificate) list.get(list.size() - 1);
            Collection trustAnchors = getTrustAnchors(x509Certificate4, this.pkixParams.getTrustAnchors());
            if (trustAnchors.size() <= 1) {
                if (trustAnchors.isEmpty()) {
                    addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noTrustAnchorFound", new Object[]{new UntrustedInput(x509Certificate4.getIssuerX500Principal()), Integers.valueOf(this.pkixParams.getTrustAnchors().size())}));
                } else {
                    trustAnchor = (TrustAnchor) trustAnchors.iterator().next();
                    try {
                        try {
                            CertPathValidatorUtilities.verifyX509Certificate(x509Certificate4, trustAnchor.getTrustedCert() != null ? trustAnchor.getTrustedCert().getPublicKey() : trustAnchor.getCAPublicKey(), this.pkixParams.getSigProvider());
                        } catch (SignatureException unused) {
                            addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustButInvalidCert"));
                        } catch (Exception unused2) {
                        }
                    } catch (CertPathReviewerException e) {
                        e = e;
                        addError(e.getErrorMessage());
                    } catch (Throwable th) {
                        th = th;
                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.unknown", new Object[]{new UntrustedInput(th.getMessage()), new UntrustedInput(th)}));
                    }
                }
                trustAnchor2 = trustAnchor;
                if (trustAnchor2 != null) {
                    trustedCert2 = trustAnchor2.getTrustedCert();
                    try {
                        if (trustedCert2 != null) {
                            x500Principal = getSubjectPrincipal(trustedCert2);
                        } else {
                            x500Principal = new X500Principal(trustAnchor2.getCAName());
                        }
                    } catch (IllegalArgumentException unused3) {
                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustDNInvalid", new Object[]{new UntrustedInput(trustAnchor2.getCAName())}));
                        x500Principal = null;
                    }
                    if (trustedCert2 != null && (keyUsage2 = trustedCert2.getKeyUsage()) != null && (keyUsage2.length <= 5 || !keyUsage2[5])) {
                        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustKeyUsage"));
                    }
                } else {
                    x500Principal = null;
                }
                if (trustAnchor2 != null) {
                    trustedCert = trustAnchor2.getTrustedCert();
                    if (trustedCert != null) {
                        cAPublicKey = trustedCert.getPublicKey();
                    } else {
                        cAPublicKey = trustAnchor2.getCAPublicKey();
                    }
                    try {
                        AlgorithmIdentifier algorithmIdentifier = getAlgorithmIdentifier(cAPublicKey);
                        algorithmIdentifier.getAlgorithm();
                        algorithmIdentifier.getParameters();
                    } catch (CertPathValidatorException unused4) {
                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustPubKeyError"));
                    }
                } else {
                    trustedCert = null;
                    cAPublicKey = null;
                }
                x509Certificate = trustedCert;
                subjectX500Principal = x500Principal;
                nextWorkingKey = cAPublicKey;
                size = this.certs.size() - 1;
                while (size >= 0) {
                    i = this.n - size;
                    x509Certificate2 = (X509Certificate) this.certs.get(size);
                    if (nextWorkingKey != null) {
                        try {
                            CertPathValidatorUtilities.verifyX509Certificate(x509Certificate2, nextWorkingKey, this.pkixParams.getSigProvider());
                        } catch (GeneralSecurityException e2) {
                            String message = e2.getMessage();
                            String name = e2.getClass().getName();
                            Object[] objArr = new Object[3];
                            objArr[0] = message;
                            objArr[i6] = e2;
                            objArr[c3] = name;
                            errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.signatureNotVerified", objArr);
                            addError(errorBundle, size);
                        }
                    } else if (isSelfIssued(x509Certificate2)) {
                        try {
                            CertPathValidatorUtilities.verifyX509Certificate(x509Certificate2, x509Certificate2.getPublicKey(), this.pkixParams.getSigProvider());
                            addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.rootKeyIsValidButNotATrustAnchor"), size);
                        } catch (GeneralSecurityException e3) {
                            String message2 = e3.getMessage();
                            String name2 = e3.getClass().getName();
                            Object[] objArr2 = new Object[3];
                            objArr2[0] = message2;
                            objArr2[i6] = e3;
                            objArr2[c3] = name2;
                            errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.signatureNotVerified", objArr2);
                            addError(errorBundle, size);
                        }
                    } else {
                        errorBundle3 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.NoIssuerPublicKey");
                        extensionValue3 = x509Certificate2.getExtensionValue(Extension.authorityKeyIdentifier.getId());
                        if (extensionValue3 != null && (authorityCertIssuer = (authorityKeyIdentifier = AuthorityKeyIdentifier.getInstance(DEROctetString.getInstance(extensionValue3).getOctets())).getAuthorityCertIssuer()) != null) {
                            generalName = authorityCertIssuer.getNames()[0];
                            authorityCertSerialNumber = authorityKeyIdentifier.getAuthorityCertSerialNumber();
                            if (authorityCertSerialNumber != null) {
                                LocaleString localeString = new LocaleString(RESOURCE_NAME, "missingIssuer");
                                LocaleString localeString2 = new LocaleString(RESOURCE_NAME, "missingSerial");
                                Object[] objArr3 = new Object[7];
                                objArr3[0] = localeString;
                                objArr3[i6] = " \"";
                                objArr3[2] = generalName;
                                objArr3[3] = "\" ";
                                objArr3[4] = localeString2;
                                objArr3[5] = " ";
                                objArr3[6] = authorityCertSerialNumber;
                                errorBundle3.setExtraArguments(objArr3);
                            }
                        }
                        addError(errorBundle3, size);
                    }
                    try {
                        x509Certificate2.checkValidity(this.validDate);
                    } catch (CertificateExpiredException unused5) {
                        Object[] objArr4 = new Object[i6];
                        objArr4[0] = new TrustedInput(x509Certificate2.getNotAfter());
                        errorBundle2 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certificateExpired", objArr4);
                        addError(errorBundle2, size);
                    } catch (CertificateNotYetValidException unused6) {
                        Object[] objArr5 = new Object[i6];
                        objArr5[0] = new TrustedInput(x509Certificate2.getNotBefore());
                        errorBundle2 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certificateNotYetValid", objArr5);
                        addError(errorBundle2, size);
                    }
                    if (this.pkixParams.isRevocationEnabled()) {
                        try {
                            extensionValue2 = getExtensionValue(x509Certificate2, CRL_DIST_POINTS);
                            if (extensionValue2 != null) {
                                cRLDistPoint = CRLDistPoint.getInstance(extensionValue2);
                            } else {
                                cRLDistPoint = null;
                            }
                        } catch (AnnotatedException unused7) {
                            addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlDistPtExtError"), size);
                        }
                        try {
                            extensionValue = getExtensionValue(x509Certificate2, AUTH_INFO_ACCESS);
                            if (extensionValue != null) {
                                authorityInformationAccess = AuthorityInformationAccess.getInstance(extensionValue);
                            } else {
                                authorityInformationAccess = null;
                            }
                        } catch (AnnotatedException unused8) {
                            addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlAuthInfoAccError"), size);
                        }
                        Vector cRLDistUrls = getCRLDistUrls(cRLDistPoint);
                        Vector oCSPUrls = getOCSPUrls(authorityInformationAccess);
                        it = cRLDistUrls.iterator();
                        while (it.hasNext()) {
                            Object[] objArr6 = new Object[i6];
                            objArr6[0] = new UntrustedUrlInput(it.next());
                            addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlDistPoint", objArr6), size);
                        }
                        it2 = oCSPUrls.iterator();
                        while (it2.hasNext()) {
                            Object[] objArr7 = new Object[i6];
                            objArr7[0] = new UntrustedUrlInput(it2.next());
                            addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ocspLocation", objArr7), size);
                        }
                        try {
                            x509Certificate3 = x509Certificate2;
                            i2 = i;
                            int i7 = size;
                            X509Certificate x509Certificate5 = x509Certificate;
                            publicKey = nextWorkingKey;
                            x500Principal2 = subjectX500Principal;
                            i3 = i7;
                            trustAnchor3 = trustAnchor2;
                            try {
                                checkRevocation(this.pkixParams, x509Certificate2, this.validDate, x509Certificate5, nextWorkingKey, cRLDistUrls, oCSPUrls, i3);
                                i4 = i3;
                            } catch (CertPathReviewerException e4) {
                                e = e4;
                                i4 = i3;
                                addError(e.getErrorMessage(), i4);
                            }
                        } catch (CertPathReviewerException e5) {
                            e = e5;
                            x509Certificate3 = x509Certificate2;
                            i2 = i;
                            i3 = size;
                            publicKey = nextWorkingKey;
                            x500Principal2 = subjectX500Principal;
                            trustAnchor3 = trustAnchor2;
                        }
                    } else {
                        x509Certificate3 = x509Certificate2;
                        i2 = i;
                        i4 = size;
                        publicKey = nextWorkingKey;
                        x500Principal2 = subjectX500Principal;
                        trustAnchor3 = trustAnchor2;
                    }
                    if (x500Principal2 != null || x509Certificate3.getIssuerX500Principal().equals(x500Principal2)) {
                        c = 2;
                    } else {
                        c = 2;
                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certWrongIssuer", new Object[]{x500Principal2.getName(), x509Certificate3.getIssuerX500Principal().getName()}), i4);
                    }
                    try {
                        try {
                            if (i2 != this.n) {
                                if (x509Certificate3 != null) {
                                    i5 = 1;
                                    if (x509Certificate3.getVersion() == 1) {
                                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noCACert"), i4);
                                    }
                                } else {
                                    i5 = 1;
                                }
                                try {
                                    basicConstraints = BasicConstraints.getInstance(getExtensionValue(x509Certificate3, BASIC_CONSTRAINTS));
                                    if (basicConstraints != null) {
                                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noBasicConstraints"), i4);
                                    } else if (!basicConstraints.isCA()) {
                                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noCACert"), i4);
                                    }
                                } catch (AnnotatedException unused9) {
                                    addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.errorProcesingBC"), i4);
                                }
                                keyUsage = x509Certificate3.getKeyUsage();
                                if (keyUsage != null) {
                                    c2 = 5;
                                    if (keyUsage.length > 5 || !keyUsage[5]) {
                                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noCertSign"), i4);
                                    }
                                }
                                subjectX500Principal = x509Certificate3.getSubjectX500Principal();
                                nextWorkingKey = getNextWorkingKey(this.certs, i4);
                                AlgorithmIdentifier algorithmIdentifier2 = getAlgorithmIdentifier(nextWorkingKey);
                                algorithmIdentifier2.getAlgorithm();
                                algorithmIdentifier2.getParameters();
                                i6 = i5;
                                trustAnchor2 = trustAnchor3;
                                x509Certificate = x509Certificate3;
                                size = i4 - 1;
                                c3 = c;
                            } else {
                                i5 = 1;
                            }
                            AlgorithmIdentifier algorithmIdentifier3 = getAlgorithmIdentifier(nextWorkingKey);
                            algorithmIdentifier3.getAlgorithm();
                            algorithmIdentifier3.getParameters();
                        } catch (CertPathValidatorException unused10) {
                            addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.pubKeyError"), i4);
                        }
                        nextWorkingKey = getNextWorkingKey(this.certs, i4);
                    } catch (CertPathValidatorException unused11) {
                        nextWorkingKey = publicKey;
                    }
                    c2 = 5;
                    subjectX500Principal = x509Certificate3.getSubjectX500Principal();
                    i6 = i5;
                    trustAnchor2 = trustAnchor3;
                    x509Certificate = x509Certificate3;
                    size = i4 - 1;
                    c3 = c;
                }
                this.trustAnchor = trustAnchor2;
                this.subjectPublicKey = nextWorkingKey;
            }
            addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.conflictingTrustAnchors", new Object[]{Integers.valueOf(trustAnchors.size()), new UntrustedInput(x509Certificate4.getIssuerX500Principal())}));
            trustAnchor = null;
        } catch (CertPathReviewerException e6) {
            e = e6;
            trustAnchor = null;
        } catch (Throwable th2) {
            th = th2;
            trustAnchor = null;
        }
        trustAnchor2 = trustAnchor;
        if (trustAnchor2 != null) {
            trustedCert2 = trustAnchor2.getTrustedCert();
            if (trustedCert2 != null) {
                x500Principal = getSubjectPrincipal(trustedCert2);
            } else {
                x500Principal = new X500Principal(trustAnchor2.getCAName());
            }
            if (trustedCert2 != null) {
                addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustKeyUsage"));
            }
        } else {
            x500Principal = null;
        }
        if (trustAnchor2 != null) {
            trustedCert = trustAnchor2.getTrustedCert();
            if (trustedCert != null) {
                cAPublicKey = trustedCert.getPublicKey();
            } else {
                cAPublicKey = trustAnchor2.getCAPublicKey();
            }
            AlgorithmIdentifier algorithmIdentifier4 = getAlgorithmIdentifier(cAPublicKey);
            algorithmIdentifier4.getAlgorithm();
            algorithmIdentifier4.getParameters();
        } else {
            trustedCert = null;
            cAPublicKey = null;
        }
        x509Certificate = trustedCert;
        subjectX500Principal = x500Principal;
        nextWorkingKey = cAPublicKey;
        size = this.certs.size() - 1;
        while (size >= 0) {
            i = this.n - size;
            x509Certificate2 = (X509Certificate) this.certs.get(size);
            if (nextWorkingKey != null) {
                CertPathValidatorUtilities.verifyX509Certificate(x509Certificate2, nextWorkingKey, this.pkixParams.getSigProvider());
            } else if (isSelfIssued(x509Certificate2)) {
                CertPathValidatorUtilities.verifyX509Certificate(x509Certificate2, x509Certificate2.getPublicKey(), this.pkixParams.getSigProvider());
                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.rootKeyIsValidButNotATrustAnchor"), size);
            } else {
                errorBundle3 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.NoIssuerPublicKey");
                extensionValue3 = x509Certificate2.getExtensionValue(Extension.authorityKeyIdentifier.getId());
                if (extensionValue3 != null) {
                    generalName = authorityCertIssuer.getNames()[0];
                    authorityCertSerialNumber = authorityKeyIdentifier.getAuthorityCertSerialNumber();
                    if (authorityCertSerialNumber != null) {
                        LocaleString localeString3 = new LocaleString(RESOURCE_NAME, "missingIssuer");
                        LocaleString localeString4 = new LocaleString(RESOURCE_NAME, "missingSerial");
                        Object[] objArr8 = new Object[7];
                        objArr8[0] = localeString3;
                        objArr8[i6] = " \"";
                        objArr8[2] = generalName;
                        objArr8[3] = "\" ";
                        objArr8[4] = localeString4;
                        objArr8[5] = " ";
                        objArr8[6] = authorityCertSerialNumber;
                        errorBundle3.setExtraArguments(objArr8);
                    }
                }
                addError(errorBundle3, size);
            }
            x509Certificate2.checkValidity(this.validDate);
            if (this.pkixParams.isRevocationEnabled()) {
                extensionValue2 = getExtensionValue(x509Certificate2, CRL_DIST_POINTS);
                if (extensionValue2 != null) {
                    cRLDistPoint = CRLDistPoint.getInstance(extensionValue2);
                } else {
                    cRLDistPoint = null;
                }
                extensionValue = getExtensionValue(x509Certificate2, AUTH_INFO_ACCESS);
                if (extensionValue != null) {
                    authorityInformationAccess = AuthorityInformationAccess.getInstance(extensionValue);
                } else {
                    authorityInformationAccess = null;
                }
                Vector cRLDistUrls2 = getCRLDistUrls(cRLDistPoint);
                Vector oCSPUrls2 = getOCSPUrls(authorityInformationAccess);
                it = cRLDistUrls2.iterator();
                while (it.hasNext()) {
                    Object[] objArr9 = new Object[i6];
                    objArr9[0] = new UntrustedUrlInput(it.next());
                    addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlDistPoint", objArr9), size);
                }
                it2 = oCSPUrls2.iterator();
                while (it2.hasNext()) {
                    Object[] objArr10 = new Object[i6];
                    objArr10[0] = new UntrustedUrlInput(it2.next());
                    addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ocspLocation", objArr10), size);
                }
                x509Certificate3 = x509Certificate2;
                i2 = i;
                int i8 = size;
                X509Certificate x509Certificate6 = x509Certificate;
                publicKey = nextWorkingKey;
                x500Principal2 = subjectX500Principal;
                i3 = i8;
                trustAnchor3 = trustAnchor2;
                checkRevocation(this.pkixParams, x509Certificate2, this.validDate, x509Certificate6, nextWorkingKey, cRLDistUrls2, oCSPUrls2, i3);
                i4 = i3;
            } else {
                x509Certificate3 = x509Certificate2;
                i2 = i;
                i4 = size;
                publicKey = nextWorkingKey;
                x500Principal2 = subjectX500Principal;
                trustAnchor3 = trustAnchor2;
            }
            if (x500Principal2 != null) {
                c = 2;
            } else {
                c = 2;
            }
            if (i2 != this.n) {
                if (x509Certificate3 != null) {
                    i5 = 1;
                    if (x509Certificate3.getVersion() == 1) {
                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noCACert"), i4);
                    }
                } else {
                    i5 = 1;
                }
                basicConstraints = BasicConstraints.getInstance(getExtensionValue(x509Certificate3, BASIC_CONSTRAINTS));
                if (basicConstraints != null) {
                    addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noBasicConstraints"), i4);
                } else if (!basicConstraints.isCA()) {
                    addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noCACert"), i4);
                }
                keyUsage = x509Certificate3.getKeyUsage();
                if (keyUsage != null) {
                    c2 = 5;
                    if (keyUsage.length > 5) {
                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noCertSign"), i4);
                    } else {
                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noCertSign"), i4);
                    }
                }
                subjectX500Principal = x509Certificate3.getSubjectX500Principal();
                nextWorkingKey = getNextWorkingKey(this.certs, i4);
                AlgorithmIdentifier algorithmIdentifier5 = getAlgorithmIdentifier(nextWorkingKey);
                algorithmIdentifier5.getAlgorithm();
                algorithmIdentifier5.getParameters();
                i6 = i5;
                trustAnchor2 = trustAnchor3;
                x509Certificate = x509Certificate3;
                size = i4 - 1;
                c3 = c;
            } else {
                i5 = 1;
            }
            c2 = 5;
            subjectX500Principal = x509Certificate3.getSubjectX500Principal();
            nextWorkingKey = getNextWorkingKey(this.certs, i4);
            AlgorithmIdentifier algorithmIdentifier6 = getAlgorithmIdentifier(nextWorkingKey);
            algorithmIdentifier6.getAlgorithm();
            algorithmIdentifier6.getParameters();
            i6 = i5;
            trustAnchor2 = trustAnchor3;
            x509Certificate = x509Certificate3;
            size = i4 - 1;
            c3 = c;
        }
        this.trustAnchor = trustAnchor2;
        this.subjectPublicKey = nextWorkingKey;
    }

    private X509CRL getCRL(String str) throws CertPathReviewerException {
        try {
            URL url = new URL(str);
            if (!url.getProtocol().equals("http") && !url.getProtocol().equals("https")) {
                return null;
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                return (X509CRL) CertificateFactory.getInstance("X.509", BouncyCastleProvider.PROVIDER_NAME).generateCRL(httpURLConnection.getInputStream());
            }
            throw new Exception(httpURLConnection.getResponseMessage());
        } catch (Exception e) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.loadCrlDistPointError", new Object[]{new UntrustedInput(str), e.getMessage(), e, e.getClass().getName()}));
        }
    }

    private boolean processQcStatements(X509Certificate x509Certificate, int i) {
        ErrorBundle errorBundle;
        try {
            ASN1Sequence aSN1Sequence = (ASN1Sequence) getExtensionValue(x509Certificate, QC_STATEMENT);
            boolean z = false;
            for (int i2 = 0; i2 < aSN1Sequence.size(); i2++) {
                QCStatement qCStatement = QCStatement.getInstance(aSN1Sequence.getObjectAt(i2));
                if (QCStatement.id_etsi_qcs_QcCompliance.equals((ASN1Primitive) qCStatement.getStatementId())) {
                    errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcEuCompliance");
                } else {
                    if (!QCStatement.id_qcs_pkixQCSyntax_v1.equals((ASN1Primitive) qCStatement.getStatementId())) {
                        if (QCStatement.id_etsi_qcs_QcSSCD.equals((ASN1Primitive) qCStatement.getStatementId())) {
                            errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcSSCD");
                        } else if (QCStatement.id_etsi_qcs_LimiteValue.equals((ASN1Primitive) qCStatement.getStatementId())) {
                            MonetaryValue monetaryValue = MonetaryValue.getInstance(qCStatement.getStatementInfo());
                            monetaryValue.getCurrency();
                            double dDoubleValue = monetaryValue.getAmount().doubleValue() * Math.pow(10.0d, monetaryValue.getExponent().doubleValue());
                            addNotification(monetaryValue.getCurrency().isAlphabetic() ? new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcLimitValueAlpha", new Object[]{monetaryValue.getCurrency().getAlphabetic(), new TrustedInput(new Double(dDoubleValue)), monetaryValue}) : new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcLimitValueNum", new Object[]{Integers.valueOf(monetaryValue.getCurrency().getNumeric()), new TrustedInput(new Double(dDoubleValue)), monetaryValue}), i);
                        } else {
                            addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcUnknownStatement", new Object[]{qCStatement.getStatementId(), new UntrustedInput(qCStatement)}), i);
                            z = true;
                        }
                    }
                }
                addNotification(errorBundle, i);
            }
            return !z;
        } catch (AnnotatedException unused) {
            addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcStatementExtError"), i);
            return false;
        }
    }

    protected void addError(ErrorBundle errorBundle) {
        this.errors[0].add(errorBundle);
    }

    protected void addError(ErrorBundle errorBundle, int i) {
        if (i < -1 || i >= this.n) {
            throw new IndexOutOfBoundsException();
        }
        this.errors[i + 1].add(errorBundle);
    }

    protected void addNotification(ErrorBundle errorBundle) {
        this.notifications[0].add(errorBundle);
    }

    protected void addNotification(ErrorBundle errorBundle, int i) {
        if (i < -1 || i >= this.n) {
            throw new IndexOutOfBoundsException();
        }
        this.notifications[i + 1].add(errorBundle);
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0256  */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    protected void checkCRLs(PKIXParameters pKIXParameters, X509Certificate x509Certificate, Date date, X509Certificate x509Certificate2, PublicKey publicKey, Vector vector, int i) throws CertPathReviewerException {
        Iterator it;
        X509CRL x509crl;
        X509CRL x509crl2;
        boolean z;
        String str;
        boolean[] keyUsage;
        X509CRL x509crl3;
        Iterator it2;
        boolean z2;
        X509CRLStoreSelector x509CRLStoreSelector = new X509CRLStoreSelector();
        try {
            x509CRLStoreSelector.addIssuerName(getEncodedIssuerPrincipal(x509Certificate).getEncoded());
            x509CRLStoreSelector.setCertificateChecking(x509Certificate);
            try {
                Set setFindCRLs = PKIXCRLUtil.findCRLs(x509CRLStoreSelector, pKIXParameters);
                it = setFindCRLs.iterator();
                if (setFindCRLs.isEmpty()) {
                    Iterator it3 = PKIXCRLUtil.findCRLs(new X509CRLStoreSelector(), pKIXParameters).iterator();
                    ArrayList arrayList = new ArrayList();
                    while (it3.hasNext()) {
                        arrayList.add(((X509CRL) it3.next()).getIssuerX500Principal());
                    }
                    addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noCrlInCertstore", new Object[]{new UntrustedInput(x509CRLStoreSelector.getIssuerNames()), new UntrustedInput(arrayList), Integers.valueOf(arrayList.size())}), i);
                }
                while (true) {
                    if (!it.hasNext()) {
                        x509crl2 = x509crl;
                        z = false;
                        break;
                    }
                    x509crl = (X509CRL) it.next();
                    Date thisUpdate = x509crl.getThisUpdate();
                    Date nextUpdate = x509crl.getNextUpdate();
                    Object[] objArr = {new TrustedInput(thisUpdate), new TrustedInput(nextUpdate)};
                    if (nextUpdate == null || date.before(nextUpdate)) {
                        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.localValidCRL", objArr), i);
                        x509crl2 = x509crl;
                        z = true;
                        break;
                    }
                    addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.localInvalidCRL", objArr), i);
                }
            } catch (AnnotatedException e) {
                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlExtractionError", new Object[]{e.getCause().getMessage(), e.getCause(), e.getCause().getClass().getName()}), i);
                it = new ArrayList().iterator();
            }
            x509crl = null;
            if (!z) {
                X500Principal issuerX500Principal = x509Certificate.getIssuerX500Principal();
                Iterator it4 = vector.iterator();
                boolean z3 = z;
                while (true) {
                    if (!it4.hasNext()) {
                        z = z3;
                        break;
                    }
                    try {
                        String str2 = (String) it4.next();
                        X509CRL crl = getCRL(str2);
                        if (crl != null) {
                            X500Principal issuerX500Principal2 = crl.getIssuerX500Principal();
                            if (issuerX500Principal.equals(issuerX500Principal2)) {
                                x509crl3 = x509crl2;
                                issuerX500Principal = issuerX500Principal;
                                it2 = it4;
                                z2 = z3;
                                Date thisUpdate2 = crl.getThisUpdate();
                                Date nextUpdate2 = crl.getNextUpdate();
                                try {
                                    Object[] objArr2 = {new TrustedInput(thisUpdate2), new TrustedInput(nextUpdate2), new UntrustedUrlInput(str2)};
                                    if (nextUpdate2 != null && !date.before(nextUpdate2)) {
                                        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.onlineInvalidCRL", objArr2), i);
                                    }
                                    try {
                                        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.onlineValidCRL", objArr2), i);
                                        x509crl2 = crl;
                                        z = true;
                                        break;
                                    } catch (CertPathReviewerException e2) {
                                        e = e2;
                                        z2 = true;
                                        addNotification(e.getErrorMessage(), i);
                                        it4 = it2;
                                        x509crl2 = x509crl3;
                                        z3 = z2;
                                        issuerX500Principal = issuerX500Principal;
                                    }
                                } catch (CertPathReviewerException e3) {
                                    e = e3;
                                }
                            } else {
                                x509crl3 = x509crl2;
                                it2 = it4;
                                try {
                                    z2 = z3;
                                    try {
                                        issuerX500Principal = issuerX500Principal;
                                        try {
                                            addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.onlineCRLWrongCA", new Object[]{new UntrustedInput(issuerX500Principal2.getName()), new UntrustedInput(issuerX500Principal.getName()), new UntrustedUrlInput(str2)}), i);
                                        } catch (CertPathReviewerException e4) {
                                            e = e4;
                                            addNotification(e.getErrorMessage(), i);
                                        }
                                    } catch (CertPathReviewerException e5) {
                                        e = e5;
                                        issuerX500Principal = issuerX500Principal;
                                    }
                                } catch (CertPathReviewerException e6) {
                                    e = e6;
                                    z2 = z3;
                                    addNotification(e.getErrorMessage(), i);
                                    it4 = it2;
                                    x509crl2 = x509crl3;
                                    z3 = z2;
                                    issuerX500Principal = issuerX500Principal;
                                }
                            }
                            it4 = it2;
                            x509crl2 = x509crl3;
                            z3 = z2;
                            issuerX500Principal = issuerX500Principal;
                        } else {
                            x509crl3 = x509crl2;
                            issuerX500Principal = issuerX500Principal;
                            it2 = it4;
                            z2 = z3;
                        }
                    } catch (CertPathReviewerException e7) {
                        e = e7;
                        x509crl3 = x509crl2;
                        it2 = it4;
                    }
                    it4 = it2;
                    x509crl2 = x509crl3;
                    z3 = z2;
                    issuerX500Principal = issuerX500Principal;
                }
            }
            if (x509crl2 != null) {
                if (x509Certificate2 != null && (keyUsage = x509Certificate2.getKeyUsage()) != null && (keyUsage.length <= 6 || !keyUsage[6])) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noCrlSigningPermited"));
                }
                if (publicKey == null) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlNoIssuerPublicKey"));
                }
                try {
                    x509crl2.verify(publicKey, BouncyCastleProvider.PROVIDER_NAME);
                    X509CRLEntry revokedCertificate = x509crl2.getRevokedCertificate(x509Certificate.getSerialNumber());
                    if (revokedCertificate != null) {
                        if (revokedCertificate.hasExtensions()) {
                            try {
                                ASN1Enumerated aSN1Enumerated = ASN1Enumerated.getInstance(getExtensionValue(revokedCertificate, Extension.reasonCode.getId()));
                                if (aSN1Enumerated != null) {
                                    str = crlReasons[aSN1Enumerated.intValueExact()];
                                } else {
                                    str = null;
                                }
                            } catch (AnnotatedException e8) {
                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlReasonExtError"), e8);
                            }
                        } else {
                            str = null;
                        }
                        if (str == null) {
                            str = crlReasons[7];
                        }
                        LocaleString localeString = new LocaleString(RESOURCE_NAME, str);
                        if (!date.before(revokedCertificate.getRevocationDate())) {
                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certRevoked", new Object[]{new TrustedInput(revokedCertificate.getRevocationDate()), localeString}));
                        }
                        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.revokedAfterValidation", new Object[]{new TrustedInput(revokedCertificate.getRevocationDate()), localeString}), i);
                    } else {
                        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notRevoked"), i);
                    }
                    Date nextUpdate3 = x509crl2.getNextUpdate();
                    if (nextUpdate3 != null && !date.before(nextUpdate3)) {
                        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlUpdateAvailable", new Object[]{new TrustedInput(nextUpdate3)}), i);
                    }
                    try {
                        ASN1Primitive extensionValue = getExtensionValue(x509crl2, ISSUING_DISTRIBUTION_POINT);
                        try {
                            ASN1Primitive extensionValue2 = getExtensionValue(x509crl2, DELTA_CRL_INDICATOR);
                            if (extensionValue2 != null) {
                                X509CRLStoreSelector x509CRLStoreSelector2 = new X509CRLStoreSelector();
                                try {
                                    x509CRLStoreSelector2.addIssuerName(getIssuerPrincipal(x509crl2).getEncoded());
                                    x509CRLStoreSelector2.setMinCRLNumber(((ASN1Integer) extensionValue2).getPositiveValue());
                                    try {
                                        x509CRLStoreSelector2.setMaxCRLNumber(((ASN1Integer) getExtensionValue(x509crl2, CRL_NUMBER)).getPositiveValue().subtract(BigInteger.valueOf(1L)));
                                        try {
                                            Iterator it5 = PKIXCRLUtil.findCRLs(x509CRLStoreSelector2, pKIXParameters).iterator();
                                            do {
                                                if (!it5.hasNext()) {
                                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noBaseCRL"));
                                                }
                                                try {
                                                } catch (AnnotatedException e9) {
                                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.distrPtExtError"), e9);
                                                }
                                            } while (!Objects.areEqual(extensionValue, getExtensionValue((X509CRL) it5.next(), ISSUING_DISTRIBUTION_POINT)));
                                        } catch (AnnotatedException e10) {
                                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlExtractionError"), e10);
                                        }
                                    } catch (AnnotatedException e11) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlNbrExtError"), e11);
                                    }
                                } catch (IOException e12) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlIssuerException"), e12);
                                }
                            }
                            if (extensionValue != null) {
                                IssuingDistributionPoint issuingDistributionPoint = IssuingDistributionPoint.getInstance(extensionValue);
                                try {
                                    BasicConstraints basicConstraints = BasicConstraints.getInstance(getExtensionValue(x509Certificate, BASIC_CONSTRAINTS));
                                    if (issuingDistributionPoint.onlyContainsUserCerts() && basicConstraints != null && basicConstraints.isCA()) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlOnlyUserCert"));
                                    }
                                    if (issuingDistributionPoint.onlyContainsCACerts() && (basicConstraints == null || !basicConstraints.isCA())) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlOnlyCaCert"));
                                    }
                                    if (issuingDistributionPoint.onlyContainsAttributeCerts()) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlOnlyAttrCert"));
                                    }
                                } catch (AnnotatedException e13) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlBCExtError"), e13);
                                }
                            }
                        } catch (AnnotatedException unused) {
                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.deltaCrlExtError"));
                        }
                    } catch (AnnotatedException unused2) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.distrPtExtError"));
                    }
                } catch (Exception e14) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlVerifyFailed"), e14);
                }
            }
            if (!z) {
                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noValidCrlFound"));
            }
        } catch (IOException e15) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlIssuerException"), e15);
        }
    }

    protected void checkRevocation(PKIXParameters pKIXParameters, X509Certificate x509Certificate, Date date, X509Certificate x509Certificate2, PublicKey publicKey, Vector vector, Vector vector2, int i) throws CertPathReviewerException {
        checkCRLs(pKIXParameters, x509Certificate, date, x509Certificate2, publicKey, vector, i);
    }

    protected void doChecks() {
        if (!this.initialized) {
            throw new IllegalStateException("Object not initialized. Call init() first.");
        }
        if (this.notifications != null) {
            return;
        }
        int i = this.n;
        this.notifications = new List[i + 1];
        this.errors = new List[i + 1];
        int i2 = 0;
        while (true) {
            List[] listArr = this.notifications;
            if (i2 >= listArr.length) {
                checkSignatures();
                checkNameConstraints();
                checkPathLength();
                checkPolicy();
                checkCriticalExtensions();
                return;
            }
            listArr[i2] = new ArrayList();
            this.errors[i2] = new ArrayList();
            i2++;
        }
    }

    protected Vector getCRLDistUrls(CRLDistPoint cRLDistPoint) {
        Vector vector = new Vector();
        if (cRLDistPoint != null) {
            for (DistributionPoint distributionPoint : cRLDistPoint.getDistributionPoints()) {
                DistributionPointName distributionPoint2 = distributionPoint.getDistributionPoint();
                if (distributionPoint2.getType() == 0) {
                    GeneralName[] names = GeneralNames.getInstance(distributionPoint2.getName()).getNames();
                    for (int i = 0; i < names.length; i++) {
                        if (names[i].getTagNo() == 6) {
                            vector.add(((ASN1IA5String) names[i].getName()).getString());
                        }
                    }
                }
            }
        }
        return vector;
    }

    public CertPath getCertPath() {
        return this.certPath;
    }

    public int getCertPathSize() {
        return this.n;
    }

    public List getErrors(int i) {
        doChecks();
        return this.errors[i + 1];
    }

    public List[] getErrors() {
        doChecks();
        return this.errors;
    }

    public List getNotifications(int i) {
        doChecks();
        return this.notifications[i + 1];
    }

    public List[] getNotifications() {
        doChecks();
        return this.notifications;
    }

    protected Vector getOCSPUrls(AuthorityInformationAccess authorityInformationAccess) {
        Vector vector = new Vector();
        if (authorityInformationAccess != null) {
            AccessDescription[] accessDescriptions = authorityInformationAccess.getAccessDescriptions();
            for (int i = 0; i < accessDescriptions.length; i++) {
                if (accessDescriptions[i].getAccessMethod().equals((ASN1Primitive) AccessDescription.id_ad_ocsp)) {
                    GeneralName accessLocation = accessDescriptions[i].getAccessLocation();
                    if (accessLocation.getTagNo() == 6) {
                        vector.add(((ASN1IA5String) accessLocation.getName()).getString());
                    }
                }
            }
        }
        return vector;
    }

    public PolicyNode getPolicyTree() {
        doChecks();
        return this.policyTree;
    }

    public PublicKey getSubjectPublicKey() {
        doChecks();
        return this.subjectPublicKey;
    }

    public TrustAnchor getTrustAnchor() {
        doChecks();
        return this.trustAnchor;
    }

    protected Collection getTrustAnchors(X509Certificate x509Certificate, Set set) throws CertPathReviewerException {
        ArrayList arrayList = new ArrayList();
        Iterator it = set.iterator();
        X509CertSelector x509CertSelector = new X509CertSelector();
        try {
            x509CertSelector.setSubject(getEncodedIssuerPrincipal(x509Certificate).getEncoded());
            byte[] extensionValue = x509Certificate.getExtensionValue(Extension.authorityKeyIdentifier.getId());
            if (extensionValue != null) {
                AuthorityKeyIdentifier authorityKeyIdentifier = AuthorityKeyIdentifier.getInstance(ASN1Primitive.fromByteArray(((ASN1OctetString) ASN1Primitive.fromByteArray(extensionValue)).getOctets()));
                if (authorityKeyIdentifier.getAuthorityCertSerialNumber() != null) {
                    x509CertSelector.setSerialNumber(authorityKeyIdentifier.getAuthorityCertSerialNumber());
                } else {
                    byte[] keyIdentifier = authorityKeyIdentifier.getKeyIdentifier();
                    if (keyIdentifier != null) {
                        x509CertSelector.setSubjectKeyIdentifier(new DEROctetString(keyIdentifier).getEncoded());
                    }
                }
            }
            while (it.hasNext()) {
                TrustAnchor trustAnchor = (TrustAnchor) it.next();
                if (trustAnchor.getTrustedCert() != null) {
                    if (x509CertSelector.match(trustAnchor.getTrustedCert())) {
                        arrayList.add(trustAnchor);
                    }
                } else if (trustAnchor.getCAName() != null && trustAnchor.getCAPublicKey() != null && getEncodedIssuerPrincipal(x509Certificate).equals(new X500Principal(trustAnchor.getCAName()))) {
                    arrayList.add(trustAnchor);
                }
            }
            return arrayList;
        } catch (IOException unused) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustAnchorIssuerError"));
        }
    }

    public void init(CertPath certPath, PKIXParameters pKIXParameters) throws CertPathReviewerException {
        if (this.initialized) {
            throw new IllegalStateException("object is already initialized!");
        }
        this.initialized = true;
        if (certPath == null) {
            throw new NullPointerException("certPath was null");
        }
        List<? extends Certificate> certificates = certPath.getCertificates();
        if (certificates.size() != 1) {
            HashSet hashSet = new HashSet();
            Iterator<TrustAnchor> it = pKIXParameters.getTrustAnchors().iterator();
            while (it.hasNext()) {
                hashSet.add(it.next().getTrustedCert());
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i != certificates.size(); i++) {
                if (!hashSet.contains(certificates.get(i))) {
                    arrayList.add(certificates.get(i));
                }
            }
            try {
                this.certPath = CertificateFactory.getInstance("X.509", BouncyCastleProvider.PROVIDER_NAME).generateCertPath(arrayList);
                this.certs = arrayList;
            } catch (GeneralSecurityException unused) {
                throw new IllegalStateException("unable to rebuild certpath");
            }
        } else {
            this.certPath = certPath;
            this.certs = certPath.getCertificates();
        }
        this.n = this.certs.size();
        if (this.certs.isEmpty()) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.emptyCertPath"));
        }
        this.pkixParams = (PKIXParameters) pKIXParameters.clone();
        Date date = new Date();
        this.currentDate = date;
        this.validDate = getValidityDate(this.pkixParams, date);
        this.notifications = null;
        this.errors = null;
        this.trustAnchor = null;
        this.subjectPublicKey = null;
        this.policyTree = null;
    }

    public boolean isValidCertPath() {
        doChecks();
        int i = 0;
        while (true) {
            List[] listArr = this.errors;
            if (i >= listArr.length) {
                return true;
            }
            if (!listArr[i].isEmpty()) {
                return false;
            }
            i++;
        }
    }
}
