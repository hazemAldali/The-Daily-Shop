package com.hazem.dailystore.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SqlCipherBackupContainer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c0\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J&\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u001e\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012J\u0016\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherBackupContainer;", "", "<init>", "()V", "magic", "", "VERSION", "", "ENVELOPE_BYTES", "HEADER_BYTES", "MAX_DATABASE_BYTES", "", "write", "", "databaseSnapshot", "Ljava/io/File;", "databaseKey", "password", "", "destination", "Ljava/io/OutputStream;", "extract", "source", "Ljava/io/InputStream;", "target", "recoverDatabaseKey", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SqlCipherBackupContainer {
    private static final int ENVELOPE_BYTES = 92;
    private static final int HEADER_BYTES = 24;
    private static final long MAX_DATABASE_BYTES = 536870912;
    private static final int VERSION = 1;
    public static final SqlCipherBackupContainer INSTANCE = new SqlCipherBackupContainer();
    private static final byte[] magic = {84, 68, 83, 66, 75, 85, 80, 49};

    private SqlCipherBackupContainer() {
    }

    public final void write(File databaseSnapshot, byte[] databaseKey, char[] password, OutputStream destination) throws IOException {
        Intrinsics.checkNotNullParameter(databaseSnapshot, "databaseSnapshot");
        Intrinsics.checkNotNullParameter(databaseKey, "databaseKey");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (databaseSnapshot.isFile()) {
            long length = databaseSnapshot.length();
            if (1 <= length && length < 536870913) {
                if (databaseKey.length != 32) {
                    throw new IllegalArgumentException("SQLCipher key must be 256 bits".toString());
                }
                byte[] bArrWrap = BackupKeyEnvelope.INSTANCE.wrap(databaseKey, password);
                if (bArrWrap.length != ENVELOPE_BYTES) {
                    throw new IllegalArgumentException("Unexpected key-envelope size".toString());
                }
                DataOutputStream dataOutputStream = new DataOutputStream(destination);
                dataOutputStream.write(magic);
                dataOutputStream.writeInt(1);
                dataOutputStream.writeInt(bArrWrap.length);
                dataOutputStream.writeLong(databaseSnapshot.length());
                dataOutputStream.write(bArrWrap);
                FileInputStream fileInputStream = new FileInputStream(databaseSnapshot);
                try {
                    ByteStreamsKt.copyTo$default(fileInputStream, dataOutputStream, 0, 2, null);
                    CloseableKt.closeFinally(fileInputStream, null);
                    dataOutputStream.flush();
                    return;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(fileInputStream, th);
                        throw th2;
                    }
                }
            }
        }
        throw new IllegalArgumentException("Invalid SQLCipher database snapshot".toString());
    }

    public final byte[] extract(InputStream source, File target, char[] password) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(password, "password");
        if (target.exists() && !target.isFile()) {
            throw new IllegalArgumentException("Restore target is not a regular file".toString());
        }
        byte[] bArr = null;
        try {
            DataInputStream dataInputStream = new DataInputStream(source);
            byte[] bArr2 = magic;
            byte[] bArr3 = new byte[bArr2.length];
            dataInputStream.readFully(bArr3);
            if (!Arrays.equals(bArr3, bArr2)) {
                throw new IllegalArgumentException("Unknown backup container".toString());
            }
            if (dataInputStream.readInt() != 1) {
                throw new IllegalArgumentException("Unsupported backup container version".toString());
            }
            int i = dataInputStream.readInt();
            if (i != ENVELOPE_BYTES) {
                throw new IllegalArgumentException("Invalid key-envelope length".toString());
            }
            long j = dataInputStream.readLong();
            if (1 > j || j >= 536870913) {
                throw new IllegalArgumentException("Invalid database payload length".toString());
            }
            byte[] bArr4 = new byte[i];
            dataInputStream.readFully(bArr4);
            byte[] bArrUnwrap = BackupKeyEnvelope.INSTANCE.unwrap(bArr4, password);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(target, false);
                try {
                    FileOutputStream fileOutputStream2 = fileOutputStream;
                    byte[] bArr5 = new byte[8192];
                    long j2 = j;
                    while (j2 > 0) {
                        int i2 = dataInputStream.read(bArr5, 0, (int) Math.min(8192, j2));
                        if (i2 < 0) {
                            throw new EOFException("Truncated SQLCipher database payload");
                        }
                        fileOutputStream2.write(bArr5, 0, i2);
                        j2 -= (long) i2;
                    }
                    fileOutputStream2.flush();
                    fileOutputStream2.getFD().sync();
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileOutputStream, null);
                    if (dataInputStream.read() != -1) {
                        throw new IllegalArgumentException("Unexpected trailing data in backup container".toString());
                    }
                    if (target.isFile() && target.length() == j) {
                        return bArrUnwrap;
                    }
                    throw new IllegalArgumentException("Extracted SQLCipher database size mismatch".toString());
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(fileOutputStream, th);
                        throw th2;
                    }
                }
            } catch (Exception e) {
                e = e;
                bArr = bArrUnwrap;
                target.delete();
                byte[] bArr6 = bArr;
                if (bArr6 != null) {
                    ArraysKt.fill$default(bArr6, (byte) 0, 0, 0, 6, (Object) null);
                }
                throw new IllegalArgumentException("Backup container is invalid or cannot be recovered", e);
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public final byte[] recoverDatabaseKey(InputStream source, char[] password) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(password, "password");
        try {
            DataInputStream dataInputStream = new DataInputStream(source);
            byte[] bArr = magic;
            byte[] bArr2 = new byte[bArr.length];
            dataInputStream.readFully(bArr2);
            if (!Arrays.equals(bArr2, bArr)) {
                throw new IllegalArgumentException("Unknown backup container".toString());
            }
            if (dataInputStream.readInt() != 1) {
                throw new IllegalArgumentException("Unsupported backup container version".toString());
            }
            if (dataInputStream.readInt() != ENVELOPE_BYTES) {
                throw new IllegalArgumentException("Invalid key-envelope length".toString());
            }
            long j = dataInputStream.readLong();
            if (1 > j || j >= 536870913) {
                throw new IllegalArgumentException("Invalid database payload length".toString());
            }
            byte[] bArr3 = new byte[ENVELOPE_BYTES];
            dataInputStream.readFully(bArr3);
            byte[] bArrUnwrap = BackupKeyEnvelope.INSTANCE.unwrap(bArr3, password);
            byte[] bArr4 = new byte[8192];
            while (j > 0) {
                int i = dataInputStream.read(bArr4, 0, (int) Math.min(8192, j));
                if (i < 0) {
                    throw new EOFException("Truncated SQLCipher database payload");
                }
                j -= (long) i;
            }
            if (dataInputStream.read() == -1) {
                return bArrUnwrap;
            }
            throw new IllegalArgumentException("Unexpected trailing data in backup container".toString());
        } catch (Exception e) {
            byte[] bArr5 = (byte[]) null;
            if (bArr5 != null) {
                ArraysKt.fill$default(bArr5, (byte) 0, 0, 0, 6, (Object) null);
            }
            throw new IllegalArgumentException("Backup key cannot be recovered from this container", e);
        }
    }
}
