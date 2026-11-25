package androidx.sqlite;

import android.database.SQLException;
import kotlin.Metadata;

/* JADX INFO: compiled from: SQLite.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00012\b\u0010\t\u001a\u0004\u0018\u00010\n\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"SQLITE_DATA_INTEGER", "", "SQLITE_DATA_FLOAT", "SQLITE_DATA_TEXT", "SQLITE_DATA_BLOB", "SQLITE_DATA_NULL", "throwSQLiteException", "", "errorCode", "errorMsg", "", "sqlite"}, k = 5, mv = {2, 1, 0}, xi = 48, xs = "androidx/sqlite/SQLite")
final /* synthetic */ class SQLite__SQLiteKt {
    public static final Void throwSQLiteException(int i, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("Error code: " + i);
        if (str != null) {
            sb.append(", message: " + str);
        }
        throw new SQLException(sb.toString());
    }
}
