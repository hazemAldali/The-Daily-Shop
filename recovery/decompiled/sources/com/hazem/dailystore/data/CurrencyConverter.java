package com.hazem.dailystore.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import kotlin.Metadata;

/* JADX INFO: compiled from: CurrencyConverter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007J\u0016\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/hazem/dailystore/data/CurrencyConverter;", "", "<init>", "()V", "conversionScale", "Ljava/math/BigDecimal;", "usdCentsToSyp", "", "usdCents", "rateSypPerUsdMilli", "sypToUsdCents", "sypAmount", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CurrencyConverter {
    public static final CurrencyConverter INSTANCE = new CurrencyConverter();
    private static final BigDecimal conversionScale = new BigDecimal("100000");

    private CurrencyConverter() {
    }

    public final long usdCentsToSyp(long usdCents, long rateSypPerUsdMilli) {
        if (usdCents < 0) {
            throw new IllegalArgumentException("USD amount cannot be negative".toString());
        }
        if (rateSypPerUsdMilli <= 0) {
            throw new IllegalArgumentException("Exchange rate must be positive".toString());
        }
        return BigDecimal.valueOf(usdCents).multiply(BigDecimal.valueOf(rateSypPerUsdMilli)).divide(conversionScale, 0, RoundingMode.HALF_UP).longValueExact();
    }

    public final long sypToUsdCents(long sypAmount, long rateSypPerUsdMilli) {
        if (sypAmount < 0) {
            throw new IllegalArgumentException("SYP amount cannot be negative".toString());
        }
        if (rateSypPerUsdMilli <= 0) {
            throw new IllegalArgumentException("Exchange rate must be positive".toString());
        }
        return BigDecimal.valueOf(sypAmount).multiply(conversionScale).divide(BigDecimal.valueOf(rateSypPerUsdMilli), 0, RoundingMode.HALF_UP).longValueExact();
    }
}
