package kotlin.collections;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UArraySorting.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007\u001a'\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003\u00a2\u0006\u0004\b\n\u0010\u000b\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003\u00a2\u0006\u0004\b\r\u0010\u000e\u001a'\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0010\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0013\u001a'\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0015\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003\u00a2\u0006\u0004\b\u0017\u0010\u0018\u001a'\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003\u00a2\u0006\u0004\b\u0019\u0010\u001a\u001a'\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001\u00a2\u0006\u0004\b\u001e\u0010\u000b\u001a'\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001\u00a2\u0006\u0004\b\u001f\u0010\u0010\u001a'\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001\u00a2\u0006\u0004\b \u0010\u0015\u001a'\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001\u00a2\u0006\u0004\b!\u0010\u001a\u00a8\u0006\""}, d2 = {"partition", "", "array", "Lkotlin/UByteArray;", "left", "right", "partition-4UcCI2c", "([BII)I", "quickSort", "", "quickSort-4UcCI2c", "([BII)V", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "quickSort-Aa5vz7o", "([SII)V", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "quickSort-oBK06Vg", "([III)V", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "quickSort--nroSd4", "([JII)V", "sortArray", "fromIndex", "toIndex", "sortArray-4UcCI2c", "sortArray-Aa5vz7o", "sortArray-oBK06Vg", "sortArray--nroSd4", "kotlin-stdlib"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class UArraySortingKt {
    /* JADX INFO: renamed from: partition-4UcCI2c, reason: not valid java name */
    private static final int m843partition4UcCI2c(byte[] bArr, int i, int i2) {
        int i3;
        byte bM459getw2LRezQ = UByteArray.m459getw2LRezQ(bArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                int iM459getw2LRezQ = UByteArray.m459getw2LRezQ(bArr, i) & UByte.MAX_VALUE;
                i3 = bM459getw2LRezQ & UByte.MAX_VALUE;
                if (Intrinsics.compare(iM459getw2LRezQ, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UByteArray.m459getw2LRezQ(bArr, i2) & UByte.MAX_VALUE, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                byte bM459getw2LRezQ2 = UByteArray.m459getw2LRezQ(bArr, i);
                UByteArray.m464setVurrAj0(bArr, i, UByteArray.m459getw2LRezQ(bArr, i2));
                UByteArray.m464setVurrAj0(bArr, i2, bM459getw2LRezQ2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* JADX INFO: renamed from: quickSort-4UcCI2c, reason: not valid java name */
    private static final void m847quickSort4UcCI2c(byte[] bArr, int i, int i2) {
        int iM843partition4UcCI2c = m843partition4UcCI2c(bArr, i, i2);
        int i3 = iM843partition4UcCI2c - 1;
        if (i < i3) {
            m847quickSort4UcCI2c(bArr, i, i3);
        }
        if (iM843partition4UcCI2c < i2) {
            m847quickSort4UcCI2c(bArr, iM843partition4UcCI2c, i2);
        }
    }

    /* JADX INFO: renamed from: partition-Aa5vz7o, reason: not valid java name */
    private static final int m844partitionAa5vz7o(short[] sArr, int i, int i2) {
        int i3;
        short sM722getMh2AYeg = UShortArray.m722getMh2AYeg(sArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                i3 = sM722getMh2AYeg & 65535;
                if (Intrinsics.compare(UShortArray.m722getMh2AYeg(sArr, i) & 65535, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UShortArray.m722getMh2AYeg(sArr, i2) & 65535, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                short sM722getMh2AYeg2 = UShortArray.m722getMh2AYeg(sArr, i);
                UShortArray.m727set01HTLdE(sArr, i, UShortArray.m722getMh2AYeg(sArr, i2));
                UShortArray.m727set01HTLdE(sArr, i2, sM722getMh2AYeg2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* JADX INFO: renamed from: quickSort-Aa5vz7o, reason: not valid java name */
    private static final void m848quickSortAa5vz7o(short[] sArr, int i, int i2) {
        int iM844partitionAa5vz7o = m844partitionAa5vz7o(sArr, i, i2);
        int i3 = iM844partitionAa5vz7o - 1;
        if (i < i3) {
            m848quickSortAa5vz7o(sArr, i, i3);
        }
        if (iM844partitionAa5vz7o < i2) {
            m848quickSortAa5vz7o(sArr, iM844partitionAa5vz7o, i2);
        }
    }

    /* JADX INFO: renamed from: partition-oBK06Vg, reason: not valid java name */
    private static final int m845partitionoBK06Vg(int[] iArr, int i, int i2) {
        int iM538getpVg5ArA = UIntArray.m538getpVg5ArA(iArr, (i + i2) / 2);
        while (i <= i2) {
            while (Integer.compare(UIntArray.m538getpVg5ArA(iArr, i) ^ Integer.MIN_VALUE, iM538getpVg5ArA ^ Integer.MIN_VALUE) < 0) {
                i++;
            }
            while (Integer.compare(UIntArray.m538getpVg5ArA(iArr, i2) ^ Integer.MIN_VALUE, iM538getpVg5ArA ^ Integer.MIN_VALUE) > 0) {
                i2--;
            }
            if (i <= i2) {
                int iM538getpVg5ArA2 = UIntArray.m538getpVg5ArA(iArr, i);
                UIntArray.m543setVXSXFK8(iArr, i, UIntArray.m538getpVg5ArA(iArr, i2));
                UIntArray.m543setVXSXFK8(iArr, i2, iM538getpVg5ArA2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* JADX INFO: renamed from: quickSort-oBK06Vg, reason: not valid java name */
    private static final void m849quickSortoBK06Vg(int[] iArr, int i, int i2) {
        int iM845partitionoBK06Vg = m845partitionoBK06Vg(iArr, i, i2);
        int i3 = iM845partitionoBK06Vg - 1;
        if (i < i3) {
            m849quickSortoBK06Vg(iArr, i, i3);
        }
        if (iM845partitionoBK06Vg < i2) {
            m849quickSortoBK06Vg(iArr, iM845partitionoBK06Vg, i2);
        }
    }

    /* JADX INFO: renamed from: partition--nroSd4, reason: not valid java name */
    private static final int m842partitionnroSd4(long[] jArr, int i, int i2) {
        long jM617getsVKNKU = ULongArray.m617getsVKNKU(jArr, (i + i2) / 2);
        while (i <= i2) {
            while (Long.compare(ULongArray.m617getsVKNKU(jArr, i) ^ Long.MIN_VALUE, jM617getsVKNKU ^ Long.MIN_VALUE) < 0) {
                i++;
            }
            while (Long.compare(ULongArray.m617getsVKNKU(jArr, i2) ^ Long.MIN_VALUE, jM617getsVKNKU ^ Long.MIN_VALUE) > 0) {
                i2--;
            }
            if (i <= i2) {
                long jM617getsVKNKU2 = ULongArray.m617getsVKNKU(jArr, i);
                ULongArray.m622setk8EXiF4(jArr, i, ULongArray.m617getsVKNKU(jArr, i2));
                ULongArray.m622setk8EXiF4(jArr, i2, jM617getsVKNKU2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* JADX INFO: renamed from: quickSort--nroSd4, reason: not valid java name */
    private static final void m846quickSortnroSd4(long[] jArr, int i, int i2) {
        int iM842partitionnroSd4 = m842partitionnroSd4(jArr, i, i2);
        int i3 = iM842partitionnroSd4 - 1;
        if (i < i3) {
            m846quickSortnroSd4(jArr, i, i3);
        }
        if (iM842partitionnroSd4 < i2) {
            m846quickSortnroSd4(jArr, iM842partitionnroSd4, i2);
        }
    }

    /* JADX INFO: renamed from: sortArray-4UcCI2c, reason: not valid java name */
    public static final void m851sortArray4UcCI2c(byte[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m847quickSort4UcCI2c(array, i, i2 - 1);
    }

    /* JADX INFO: renamed from: sortArray-Aa5vz7o, reason: not valid java name */
    public static final void m852sortArrayAa5vz7o(short[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m848quickSortAa5vz7o(array, i, i2 - 1);
    }

    /* JADX INFO: renamed from: sortArray-oBK06Vg, reason: not valid java name */
    public static final void m853sortArrayoBK06Vg(int[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m849quickSortoBK06Vg(array, i, i2 - 1);
    }

    /* JADX INFO: renamed from: sortArray--nroSd4, reason: not valid java name */
    public static final void m850sortArraynroSd4(long[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        m846quickSortnroSd4(array, i, i2 - 1);
    }
}
