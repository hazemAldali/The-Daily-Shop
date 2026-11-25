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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SqlCipherSameDeviceRestoreJournal.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0000\u0018\u0000 \u001c2\u00020\u0001:\u0003\u001a\u001b\u001cB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J&\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003J\u0016\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010J\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\u0006\u0010\u0014\u001a\u00020\tJ\u0010\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0013H\u0002J\u0010\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherSameDeviceRestoreJournal;", "", "file", "Ljava/io/File;", "<init>", "(Ljava/io/File;)V", "atomic", "Landroid/util/AtomicFile;", "begin", "", "staging", "live", "rollback", "keySnapshot", "advance", "expected", "Lcom/hazem/dailystore/data/SqlCipherSameDeviceRestoreJournal$Phase;", "next", "read", "Lcom/hazem/dailystore/data/SqlCipherSameDeviceRestoreJournal$Record;", "clear", "write", "record", "validateName", "name", "", "Phase", "Record", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SqlCipherSameDeviceRestoreJournal {
    private static final byte[] MAGIC = {84, 68, 83, 83, 68, 82, 74, 49};
    private static final int VERSION = 1;
    private final AtomicFile atomic;

    /* JADX INFO: compiled from: SqlCipherSameDeviceRestoreJournal.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\t"}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherSameDeviceRestoreJournal$Phase;", "", "<init>", "(Ljava/lang/String;I)V", "PREPARED", "OLD_DATABASE_BACKED_UP", "KEY_REPLACED", "DATABASE_SWAPPED", "VERIFIED", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum Phase {
        PREPARED,
        OLD_DATABASE_BACKED_UP,
        KEY_REPLACED,
        DATABASE_SWAPPED,
        VERIFIED;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<Phase> getEntries() {
            return $ENTRIES;
        }
    }

    public SqlCipherSameDeviceRestoreJournal(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        this.atomic = new AtomicFile(file);
    }

    /* JADX INFO: compiled from: SqlCipherSameDeviceRestoreJournal.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u00a2\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0005H\u00c6\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001J\t\u0010\u001d\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e\u00a8\u0006\u001e"}, d2 = {"Lcom/hazem/dailystore/data/SqlCipherSameDeviceRestoreJournal$Record;", "", "phase", "Lcom/hazem/dailystore/data/SqlCipherSameDeviceRestoreJournal$Phase;", "stagingName", "", "liveName", "rollbackName", "keySnapshotName", "<init>", "(Lcom/hazem/dailystore/data/SqlCipherSameDeviceRestoreJournal$Phase;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getPhase", "()Lcom/hazem/dailystore/data/SqlCipherSameDeviceRestoreJournal$Phase;", "getStagingName", "()Ljava/lang/String;", "getLiveName", "getRollbackName", "getKeySnapshotName", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class Record {
        private final String keySnapshotName;
        private final String liveName;
        private final Phase phase;
        private final String rollbackName;
        private final String stagingName;

        public static /* synthetic */ Record copy$default(Record record, Phase phase, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                phase = record.phase;
            }
            if ((i & 2) != 0) {
                str = record.stagingName;
            }
            String str5 = str;
            if ((i & 4) != 0) {
                str2 = record.liveName;
            }
            String str6 = str2;
            if ((i & 8) != 0) {
                str3 = record.rollbackName;
            }
            String str7 = str3;
            if ((i & 16) != 0) {
                str4 = record.keySnapshotName;
            }
            return record.copy(phase, str5, str6, str7, str4);
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
        public final String getLiveName() {
            return this.liveName;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getRollbackName() {
            return this.rollbackName;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getKeySnapshotName() {
            return this.keySnapshotName;
        }

        public final Record copy(Phase phase, String stagingName, String liveName, String rollbackName, String keySnapshotName) {
            Intrinsics.checkNotNullParameter(phase, "phase");
            Intrinsics.checkNotNullParameter(stagingName, "stagingName");
            Intrinsics.checkNotNullParameter(liveName, "liveName");
            Intrinsics.checkNotNullParameter(rollbackName, "rollbackName");
            Intrinsics.checkNotNullParameter(keySnapshotName, "keySnapshotName");
            return new Record(phase, stagingName, liveName, rollbackName, keySnapshotName);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Record)) {
                return false;
            }
            Record record = (Record) other;
            return this.phase == record.phase && Intrinsics.areEqual(this.stagingName, record.stagingName) && Intrinsics.areEqual(this.liveName, record.liveName) && Intrinsics.areEqual(this.rollbackName, record.rollbackName) && Intrinsics.areEqual(this.keySnapshotName, record.keySnapshotName);
        }

        public int hashCode() {
            return (((((((this.phase.hashCode() * 31) + this.stagingName.hashCode()) * 31) + this.liveName.hashCode()) * 31) + this.rollbackName.hashCode()) * 31) + this.keySnapshotName.hashCode();
        }

        public String toString() {
            return "Record(phase=" + this.phase + ", stagingName=" + this.stagingName + ", liveName=" + this.liveName + ", rollbackName=" + this.rollbackName + ", keySnapshotName=" + this.keySnapshotName + ")";
        }

        public Record(Phase phase, String stagingName, String liveName, String rollbackName, String keySnapshotName) {
            Intrinsics.checkNotNullParameter(phase, "phase");
            Intrinsics.checkNotNullParameter(stagingName, "stagingName");
            Intrinsics.checkNotNullParameter(liveName, "liveName");
            Intrinsics.checkNotNullParameter(rollbackName, "rollbackName");
            Intrinsics.checkNotNullParameter(keySnapshotName, "keySnapshotName");
            this.phase = phase;
            this.stagingName = stagingName;
            this.liveName = liveName;
            this.rollbackName = rollbackName;
            this.keySnapshotName = keySnapshotName;
        }

        public final Phase getPhase() {
            return this.phase;
        }

        public final String getStagingName() {
            return this.stagingName;
        }

        public final String getLiveName() {
            return this.liveName;
        }

        public final String getRollbackName() {
            return this.rollbackName;
        }

        public final String getKeySnapshotName() {
            return this.keySnapshotName;
        }
    }

    public final synchronized void begin(File staging, File live, File rollback, File keySnapshot) {
        Intrinsics.checkNotNullParameter(staging, "staging");
        Intrinsics.checkNotNullParameter(live, "live");
        Intrinsics.checkNotNullParameter(rollback, "rollback");
        Intrinsics.checkNotNullParameter(keySnapshot, "keySnapshot");
        if (!this.atomic.getBaseFile().exists()) {
            if (!new File(this.atomic.getBaseFile().getPath() + ".bak").exists()) {
                File parentFile = live.getCanonicalFile().getParentFile();
                if (parentFile == null || !Intrinsics.areEqual(staging.getCanonicalFile().getParentFile(), parentFile) || !Intrinsics.areEqual(rollback.getCanonicalFile().getParentFile(), parentFile) || Intrinsics.areEqual(staging.getCanonicalFile(), live.getCanonicalFile()) || Intrinsics.areEqual(rollback.getCanonicalFile(), live.getCanonicalFile()) || Intrinsics.areEqual(rollback.getCanonicalFile(), staging.getCanonicalFile())) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                String name = keySnapshot.getName();
                Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                if (StringsKt.isBlank(name) || !Intrinsics.areEqual(new File(keySnapshot.getName()).getName(), keySnapshot.getName())) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                Phase phase = Phase.PREPARED;
                String name2 = staging.getName();
                Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
                String name3 = live.getName();
                Intrinsics.checkNotNullExpressionValue(name3, "getName(...)");
                String name4 = rollback.getName();
                Intrinsics.checkNotNullExpressionValue(name4, "getName(...)");
                String name5 = keySnapshot.getName();
                Intrinsics.checkNotNullExpressionValue(name5, "getName(...)");
                write(new Record(phase, name2, name3, name4, name5));
            }
        }
        throw new IllegalStateException("\u064a\u0648\u062c\u062f \u0633\u062c\u0644 \u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0633\u0627\u0628\u0642\u061b \u064a\u062c\u0628 \u0627\u0644\u062a\u0639\u0627\u0641\u064a \u0645\u0646\u0647 \u0623\u0648\u0644\u0627\u064b".toString());
    }

    public final synchronized void advance(Phase expected, Phase next) {
        Intrinsics.checkNotNullParameter(expected, "expected");
        Intrinsics.checkNotNullParameter(next, "next");
        if (next.ordinal() != expected.ordinal() + 1) {
            throw new IllegalArgumentException("Invalid same-device restore phase transition".toString());
        }
        Record record = read();
        if (record == null) {
            throw new IllegalStateException("Same-device restore journal is missing".toString());
        }
        if (record.getPhase() != expected) {
            throw new IllegalStateException("Same-device restore phase changed unexpectedly".toString());
        }
        write(Record.copy$default(record, next, null, null, null, null, 30, null));
    }

    public final synchronized Record read() {
        if (!this.atomic.getBaseFile().exists()) {
            if (!new File(this.atomic.getBaseFile().getPath() + ".bak").exists()) {
                return null;
            }
        }
        try {
            FileInputStream fileInputStreamOpenRead = this.atomic.openRead();
            try {
                FileInputStream fileInputStream = fileInputStreamOpenRead;
                Intrinsics.checkNotNull(fileInputStream);
                byte[] bytes = ByteStreamsKt.readBytes(fileInputStream);
                CloseableKt.closeFinally(fileInputStreamOpenRead, null);
                DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bytes));
                byte[] bArr = MAGIC;
                byte[] bArr2 = new byte[bArr.length];
                dataInputStream.readFully(bArr2);
                if (!Arrays.equals(bArr2, bArr) || dataInputStream.readInt() != 1) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                int i = dataInputStream.readInt();
                if (i < 0 || i >= Phase.values().length) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                ArrayList arrayList = new ArrayList(4);
                for (int i2 = 0; i2 < 4; i2++) {
                    arrayList.add(dataInputStream.readUTF());
                }
                ArrayList arrayList2 = arrayList;
                if (dataInputStream.read() != -1) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    validateName((String) it.next());
                }
                if (CollectionsKt.toSet(arrayList2).size() != arrayList2.size()) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                Phase phase = Phase.values()[i];
                Object obj = arrayList2.get(0);
                Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                String str = (String) obj;
                Object obj2 = arrayList2.get(1);
                Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
                String str2 = (String) obj2;
                Object obj3 = arrayList2.get(2);
                Intrinsics.checkNotNullExpressionValue(obj3, "get(...)");
                String str3 = (String) obj3;
                Object obj4 = arrayList2.get(3);
                Intrinsics.checkNotNullExpressionValue(obj4, "get(...)");
                return new Record(phase, str, str2, str3, (String) obj4);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(fileInputStreamOpenRead, th);
                    throw th2;
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("\u0633\u062c\u0644 \u0627\u0633\u062a\u0639\u0627\u062f\u0629 SQLCipher \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u062a\u0627\u0644\u0641\u061b \u0627\u062d\u062a\u0641\u0638 \u0628\u0643\u0644 \u0627\u0644\u0645\u0644\u0641\u0627\u062a \u0648\u0623\u0648\u0642\u0641 \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629", e);
        }
    }

    public final synchronized void clear() {
        this.atomic.delete();
    }

    private final void write(Record record) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            DataOutputStream dataOutputStream2 = dataOutputStream;
            dataOutputStream2.write(MAGIC);
            dataOutputStream2.writeInt(1);
            dataOutputStream2.writeInt(record.getPhase().ordinal());
            dataOutputStream2.writeUTF(record.getStagingName());
            dataOutputStream2.writeUTF(record.getLiveName());
            dataOutputStream2.writeUTF(record.getRollbackName());
            dataOutputStream2.writeUTF(record.getKeySnapshotName());
            Unit unit = Unit.INSTANCE;
            FileOutputStream fileOutputStreamStartWrite = null;
            CloseableKt.closeFinally(dataOutputStream, null);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                fileOutputStreamStartWrite = this.atomic.startWrite();
                fileOutputStreamStartWrite.write(byteArray);
                fileOutputStreamStartWrite.flush();
                fileOutputStreamStartWrite.getFD().sync();
                this.atomic.finishWrite(fileOutputStreamStartWrite);
            } catch (Exception e) {
                if (fileOutputStreamStartWrite != null) {
                    this.atomic.failWrite(fileOutputStreamStartWrite);
                }
                throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u062d\u0641\u0638 \u0633\u062c\u0644 \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0627\u0644\u0622\u0645\u0646", e);
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

    private final void validateName(String name) {
        String str = name;
        if (StringsKt.isBlank(str) || Intrinsics.areEqual(name, ".") || Intrinsics.areEqual(name, "..") || !Intrinsics.areEqual(new File(name).getName(), name) || StringsKt.contains$default((CharSequence) str, '/', false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, '\\', false, 2, (Object) null)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }
}
