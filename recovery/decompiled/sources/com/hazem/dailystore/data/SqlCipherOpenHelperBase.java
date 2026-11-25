package com.hazem.dailystore.data;

import android.content.Context;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import net.zetetic.database.DatabaseErrorHandler;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import net.zetetic.database.sqlcipher.SQLiteDatabaseHook;
import net.zetetic.database.sqlcipher.SQLiteOpenHelper;

/* JADX INFO: compiled from: SqlCipherOpenHelperBase.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b \u0018\u0000 \f2\u00020\u0001:\u0001\fB)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0004\b\n\u0010\u000b\u00a8\u0006\r"}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherOpenHelperBase;", "Lnet/zetetic/database/sqlcipher/SQLiteOpenHelper;", "context", "Landroid/content/Context;", "databaseName", "", "databaseKey", "", "databaseVersion", "", "<init>", "(Landroid/content/Context;Ljava/lang/String;[BI)V", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class SqlCipherOpenHelperBase extends SQLiteOpenHelper {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SqlCipherOpenHelperBase(Context context, String databaseName, byte[] bArr, int i) {
        super(context, databaseName, INSTANCE.prepareDatabaseKey(bArr), (SQLiteDatabase.CursorFactory) null, i, 0, (DatabaseErrorHandler) null, (SQLiteDatabaseHook) null, false);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(databaseName, "databaseName");
    }

    /* JADX INFO: compiled from: SqlCipherOpenHelperBase.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0002\u00a8\u0006\u0007"}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherOpenHelperBase$Companion;", "", "<init>", "()V", "prepareDatabaseKey", "", "databaseKey", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final byte[] prepareDatabaseKey(byte[] databaseKey) {
            SqlCipherDatabaseRuntime.INSTANCE.loadNativeLibrary();
            if (databaseKey == null) {
                return null;
            }
            if (databaseKey.length != 32) {
                throw new IllegalArgumentException("SQLCipher database key must be exactly 256 bits".toString());
            }
            char[] cArr = new char[databaseKey.length * 2];
            int length = databaseKey.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                byte b = databaseKey[i];
                int i3 = i2 + 1;
                int i4 = i2 * 2;
                cArr[i4] = "0123456789abcdef".charAt((b & UByte.MAX_VALUE) >>> 4);
                cArr[i4 + 1] = "0123456789abcdef".charAt(b & 15);
                i++;
                i2 = i3;
            }
            byte[] bytes = new String(cArr).getBytes(Charsets.US_ASCII);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            return bytes;
        }
    }
}
