package androidx.sqlite;

import kotlin.Metadata;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SQLite.nonWeb.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"execSQL", "", "Landroidx/sqlite/SQLiteConnection;", "sql", "", "sqlite"}, k = 5, mv = {2, 1, 0}, xi = 48, xs = "androidx/sqlite/SQLite")
final /* synthetic */ class SQLite__SQLite_nonWebKt {
    public static final void execSQL(SQLiteConnection sQLiteConnection, String sql) throws Exception {
        Intrinsics.checkNotNullParameter(sQLiteConnection, "<this>");
        Intrinsics.checkNotNullParameter(sql, "sql");
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare(sql);
        try {
            sQLiteStatementPrepare.step();
            AutoCloseableKt.closeFinally(sQLiteStatementPrepare, null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AutoCloseableKt.closeFinally(sQLiteStatementPrepare, th);
                throw th2;
            }
        }
    }
}
