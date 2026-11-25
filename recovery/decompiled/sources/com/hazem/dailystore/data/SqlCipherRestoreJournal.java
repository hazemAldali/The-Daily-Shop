package com.hazem.dailystore.data;

import android.util.AtomicFile;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SqlCipherRestoreJournal.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0000\u0018\u0000 \u001e2\u00020\u0001:\u0003\u001c\u001d\u001eB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0003J\u0016\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eJ\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u0006\u0010\u0012\u001a\u00020\tJ\u0010\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0011H\u0002J$\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0003H\u0002J\u0010\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u0017H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherRestoreJournal;", "", "journalFile", "Ljava/io/File;", "<init>", "(Ljava/io/File;)V", "atomicFile", "Landroid/util/AtomicFile;", "begin", "", "stagingFile", "destinationFile", "advance", "expected", "Lcom/hazem/dailystore/data/SqlCipherRestoreJournal$Phase;", "next", "read", "Lcom/hazem/dailystore/data/SqlCipherRestoreJournal$Record;", "clear", "writeRecord", "record", "validatePair", "Lkotlin/Pair;", "", "staging", "destination", "validateName", "name", "Record", "Phase", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SqlCipherRestoreJournal {
    private static final byte[] MAGIC = {84, 68, 83, 82, 83, 84, 74, 49};
    private static final int VERSION = 1;
    private final AtomicFile atomicFile;

    /* JADX INFO: compiled from: SqlCipherRestoreJournal.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherRestoreJournal$Phase;", "", "<init>", "(Ljava/lang/String;I)V", "STAGED", "KEY_IMPORTED", "DATABASE_COMMITTED", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum Phase {
        STAGED,
        KEY_IMPORTED,
        DATABASE_COMMITTED;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<Phase> getEntries() {
            return $ENTRIES;
        }
    }

    public SqlCipherRestoreJournal(File journalFile) {
        Intrinsics.checkNotNullParameter(journalFile, "journalFile");
        this.atomicFile = new AtomicFile(journalFile);
    }

    /* JADX INFO: compiled from: SqlCipherRestoreJournal.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f\u00a8\u0006\u0018"}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherRestoreJournal$Record;", "", "phase", "Lcom/hazem/dailystore/data/SqlCipherRestoreJournal$Phase;", "stagingName", "", "destinationName", "<init>", "(Lcom/hazem/dailystore/data/SqlCipherRestoreJournal$Phase;Ljava/lang/String;Ljava/lang/String;)V", "getPhase", "()Lcom/hazem/dailystore/data/SqlCipherRestoreJournal$Phase;", "getStagingName", "()Ljava/lang/String;", "getDestinationName", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class Record {
        private final String destinationName;
        private final Phase phase;
        private final String stagingName;

        public static /* synthetic */ Record copy$default(Record record, Phase phase, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                phase = record.phase;
            }
            if ((i & 2) != 0) {
                str = record.stagingName;
            }
            if ((i & 4) != 0) {
                str2 = record.destinationName;
            }
            return record.copy(phase, str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Phase getPhase() {
            return this.phase;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getStagingName() {
            return this.stagingName;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getDestinationName() {
            return this.destinationName;
        }

        public final Record copy(Phase phase, String stagingName, String destinationName) {
            Intrinsics.checkNotNullParameter(phase, "phase");
            Intrinsics.checkNotNullParameter(stagingName, "stagingName");
            Intrinsics.checkNotNullParameter(destinationName, "destinationName");
            return new Record(phase, stagingName, destinationName);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Record)) {
                return false;
            }
            Record record = (Record) other;
            return this.phase == record.phase && Intrinsics.areEqual(this.stagingName, record.stagingName) && Intrinsics.areEqual(this.destinationName, record.destinationName);
        }

        public int hashCode() {
            return (((this.phase.hashCode() * 31) + this.stagingName.hashCode()) * 31) + this.destinationName.hashCode();
        }

        public String toString() {
            return "Record(phase=" + this.phase + ", stagingName=" + this.stagingName + ", destinationName=" + this.destinationName + ")";
        }

        public Record(Phase phase, String stagingName, String destinationName) {
            Intrinsics.checkNotNullParameter(phase, "phase");
            Intrinsics.checkNotNullParameter(stagingName, "stagingName");
            Intrinsics.checkNotNullParameter(destinationName, "destinationName");
            this.phase = phase;
            this.stagingName = stagingName;
            this.destinationName = destinationName;
        }

        public final Phase getPhase() {
            return this.phase;
        }

        public final String getStagingName() {
            return this.stagingName;
        }

        public final String getDestinationName() {
            return this.destinationName;
        }
    }

    public final synchronized void begin(File stagingFile, File destinationFile) {
        Intrinsics.checkNotNullParameter(stagingFile, "stagingFile");
        Intrinsics.checkNotNullParameter(destinationFile, "destinationFile");
        if (!this.atomicFile.getBaseFile().exists()) {
            if (!new File(this.atomicFile.getBaseFile().getPath() + ".bak").exists()) {
                Pair<String, String> pairValidatePair = validatePair(stagingFile, destinationFile);
                writeRecord(new Record(Phase.STAGED, pairValidatePair.getFirst(), pairValidatePair.getSecond()));
            }
        }
        throw new IllegalArgumentException("\u064a\u0648\u062c\u062f \u0633\u062c\u0644 \u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0633\u0627\u0628\u0642\u061b \u064a\u062c\u0628 \u0627\u0644\u062a\u0639\u0627\u0641\u064a \u0645\u0646\u0647 \u0642\u0628\u0644 \u0628\u062f\u0621 \u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u062c\u062f\u064a\u062f\u0629".toString());
    }

    public final synchronized void advance(Phase expected, Phase next) {
        Intrinsics.checkNotNullParameter(expected, "expected");
        Intrinsics.checkNotNullParameter(next, "next");
        if (next.ordinal() != expected.ordinal() + 1) {
            throw new IllegalArgumentException("Invalid restore-journal phase transition".toString());
        }
        Record record = read();
        if (record == null) {
            throw new IllegalStateException("Restore journal is missing");
        }
        if (record.getPhase() != expected) {
            throw new IllegalStateException("Restore journal phase changed unexpectedly".toString());
        }
        writeRecord(Record.copy$default(record, next, null, null, 6, null));
    }

    public final synchronized Record read() {
        if (!this.atomicFile.getBaseFile().exists()) {
            if (!new File(this.atomicFile.getBaseFile().getPath() + ".bak").exists()) {
                return null;
            }
        }
        try {
            FileInputStream fileInputStreamOpenRead = this.atomicFile.openRead();
            try {
                FileInputStream fileInputStream = fileInputStreamOpenRead;
                Intrinsics.checkNotNull(fileInputStream);
                byte[] bytes = ByteStreamsKt.readBytes(fileInputStream);
                CloseableKt.closeFinally(fileInputStreamOpenRead, null);
                DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bytes));
                byte[] bArr = MAGIC;
                byte[] bArr2 = new byte[bArr.length];
                dataInputStream.readFully(bArr2);
                if (!Arrays.equals(bArr2, bArr)) {
                    throw new IllegalArgumentException("Invalid SQLCipher restore journal".toString());
                }
                if (dataInputStream.readInt() != 1) {
                    throw new IllegalArgumentException("Unsupported SQLCipher restore journal version".toString());
                }
                int i = dataInputStream.readInt();
                if (i < 0 || i >= Phase.values().length) {
                    throw new IllegalArgumentException("Invalid SQLCipher restore phase".toString());
                }
                String utf = dataInputStream.readUTF();
                String utf2 = dataInputStream.readUTF();
                if (dataInputStream.read() != -1) {
                    throw new IllegalArgumentException("Unexpected trailing restore-journal data".toString());
                }
                Intrinsics.checkNotNull(utf);
                validateName(utf);
                Intrinsics.checkNotNull(utf2);
                validateName(utf2);
                if (Intrinsics.areEqual(utf, utf2)) {
                    throw new IllegalArgumentException("Restore journal paths must be distinct".toString());
                }
                return new Record(Phase.values()[i], utf, utf2);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(fileInputStreamOpenRead, th);
                    throw th2;
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("\u0633\u062c\u0644 \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u062a\u0627\u0644\u0641\u061b \u0623\u0648\u0642\u0641 \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0648\u0627\u062d\u062a\u0641\u0638 \u0628\u0645\u0644\u0641\u0627\u062a \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u062f\u0648\u0646 \u062d\u0630\u0641\u0647\u0627", e);
        }
    }

    public final synchronized void clear() {
        this.atomicFile.delete();
    }

    private final void writeRecord(Record record) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            DataOutputStream dataOutputStream2 = dataOutputStream;
            dataOutputStream2.write(MAGIC);
            dataOutputStream2.writeInt(1);
            dataOutputStream2.writeInt(record.getPhase().ordinal());
            dataOutputStream2.writeUTF(record.getStagingName());
            dataOutputStream2.writeUTF(record.getDestinationName());
            Unit unit = Unit.INSTANCE;
            FileOutputStream fileOutputStreamStartWrite = null;
            CloseableKt.closeFinally(dataOutputStream, null);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                fileOutputStreamStartWrite = this.atomicFile.startWrite();
                fileOutputStreamStartWrite.write(byteArray);
                fileOutputStreamStartWrite.flush();
                fileOutputStreamStartWrite.getFD().sync();
                this.atomicFile.finishWrite(fileOutputStreamStartWrite);
            } catch (Exception e) {
                if (fileOutputStreamStartWrite != null) {
                    this.atomicFile.failWrite(fileOutputStreamStartWrite);
                }
                throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u062d\u0641\u0638 \u062a\u0642\u062f\u0645 \u0639\u0645\u0644\u064a\u0629 \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0628\u0623\u0645\u0627\u0646", e);
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(dataOutputStream, th);
                throw th2;
            }
        }
    }

    private final Pair<String, String> validatePair(File staging, File destination) {
        File parentFile = staging.getCanonicalFile().getParentFile();
        File parentFile2 = destination.getCanonicalFile().getParentFile();
        if (parentFile == null || !Intrinsics.areEqual(parentFile, parentFile2)) {
            throw new IllegalArgumentException("\u064a\u062c\u0628 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0645\u0644\u0641 \u0627\u0644\u062a\u062c\u0647\u064a\u0632 \u0648\u0627\u0644\u0648\u062c\u0647\u0629 \u0641\u064a \u0627\u0644\u0645\u062c\u0644\u062f \u0646\u0641\u0633\u0647".toString());
        }
        String name = staging.getName();
        String name2 = destination.getName();
        Intrinsics.checkNotNull(name);
        validateName(name);
        Intrinsics.checkNotNull(name2);
        validateName(name2);
        if (Intrinsics.areEqual(name, name2)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        return TuplesKt.to(name, name2);
    }

    private final void validateName(String name) {
        String str = name;
        if (StringsKt.isBlank(str) || Intrinsics.areEqual(name, ".") || Intrinsics.areEqual(name, "..") || !Intrinsics.areEqual(new File(name).getName(), name) || StringsKt.contains$default((CharSequence) str, '/', false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, '\\', false, 2, (Object) null)) {
            throw new IllegalArgumentException("Invalid restore-journal file name".toString());
        }
    }
}
