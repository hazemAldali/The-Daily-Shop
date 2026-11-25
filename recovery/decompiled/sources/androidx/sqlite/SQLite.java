package androidx.sqlite;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"androidx/sqlite/SQLite__SQLiteKt", "androidx/sqlite/SQLite__SQLite_nonWebKt"}, k = 4, mv = {2, 1, 0}, xi = 48)
public final class SQLite {
    public static final int SQLITE_DATA_BLOB = 4;
    public static final int SQLITE_DATA_FLOAT = 2;
    public static final int SQLITE_DATA_INTEGER = 1;
    public static final int SQLITE_DATA_NULL = 5;
    public static final int SQLITE_DATA_TEXT = 3;

    public static final void execSQL(SQLiteConnection sQLiteConnection, String str) throws Exception {
        SQLite__SQLite_nonWebKt.execSQL(sQLiteConnection, str);
    }

    public static final Void throwSQLiteException(int i, String str) {
        return SQLite__SQLiteKt.throwSQLiteException(i, str);
    }
}
