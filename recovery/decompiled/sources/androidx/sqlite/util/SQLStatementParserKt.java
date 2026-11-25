package androidx.sqlite.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SQLStatementParser.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u0002\u00a8\u0006\u0006"}, d2 = {"getStatementPrefix", "", "sql", "getStatementPrefixIndex", "", "s", "sqlite"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SQLStatementParserKt {
    public static final String getStatementPrefix(String sql) {
        Intrinsics.checkNotNullParameter(sql, "sql");
        int statementPrefixIndex = getStatementPrefixIndex(sql);
        if (statementPrefixIndex < 0 || statementPrefixIndex > sql.length()) {
            return null;
        }
        String strSubstring = sql.substring(statementPrefixIndex, Math.min(statementPrefixIndex + 3, sql.length()));
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    private static final int getStatementPrefixIndex(String str) {
        int i;
        int length = str.length() - 2;
        if (length < 0) {
            return -1;
        }
        int iIndexOf$default = 0;
        while (iIndexOf$default < length) {
            char cCharAt = str.charAt(iIndexOf$default);
            if (Intrinsics.compare((int) cCharAt, 32) > 0) {
                if (cCharAt == '-') {
                    if (str.charAt(iIndexOf$default + 1) != '-') {
                        return iIndexOf$default;
                    }
                    iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, '\n', iIndexOf$default + 2, false, 4, (Object) null);
                    if (iIndexOf$default < 0) {
                        return -1;
                    }
                } else {
                    if (cCharAt != '/') {
                        return iIndexOf$default;
                    }
                    int iIndexOf$default2 = iIndexOf$default + 1;
                    if (str.charAt(iIndexOf$default2) != '*') {
                        return iIndexOf$default;
                    }
                    do {
                        iIndexOf$default2 = StringsKt.indexOf$default((CharSequence) str, '*', iIndexOf$default2 + 1, false, 4, (Object) null);
                        if (iIndexOf$default2 >= 0) {
                            i = iIndexOf$default2 + 1;
                            if (i >= length) {
                                break;
                            }
                        } else {
                            return -1;
                        }
                    } while (str.charAt(i) != '/');
                    iIndexOf$default = iIndexOf$default2 + 2;
                }
            }
            iIndexOf$default++;
        }
        return -1;
    }
}
