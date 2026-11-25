package com.hazem.dailystore.data;

import android.content.Context;
import android.database.Cursor;
import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* JADX INFO: compiled from: SalesProfitRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\tJ\u001a\u0010\r\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/hazem/dailystore/data/SalesProfitRepository;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "db", "Lnet/zetetic/database/sqlcipher/SQLiteDatabase;", "today", "Lcom/hazem/dailystore/data/SalesProfitSummary;", "now", "Ljava/util/Date;", "allTime", "summarize", "fromDate", "", "throughDate", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SalesProfitRepository {
    private final SQLiteDatabase db;

    public SalesProfitRepository(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SQLiteDatabase readableDatabase = AppDatabase.INSTANCE.getInstance(context).getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        this.db = readableDatabase;
    }

    public static /* synthetic */ SalesProfitSummary today$default(SalesProfitRepository salesProfitRepository, Date date, int i, Object obj) {
        if ((i & 1) != 0) {
            date = new Date();
        }
        return salesProfitRepository.today(date);
    }

    public final SalesProfitSummary today(Date now) {
        Intrinsics.checkNotNullParameter(now, "now");
        String str = StoreTime.INSTANCE.today(this.db, now);
        return summarize(str, str);
    }

    public final SalesProfitSummary allTime() {
        return summarize(null, null);
    }

    public final SalesProfitSummary summarize(String fromDate, String throughDate) throws IOException {
        String str;
        String[] strArr;
        Throwable th;
        int i = 1;
        int i2 = 0;
        if ((fromDate == null) != (throughDate == null)) {
            throw new IllegalArgumentException("\u064a\u062c\u0628 \u062a\u062d\u062f\u064a\u062f \u062a\u0627\u0631\u064a\u062e\u064a \u0627\u0644\u0628\u062f\u0627\u064a\u0629 \u0648\u0627\u0644\u0646\u0647\u0627\u064a\u0629 \u0645\u0639\u0627\u064b".toString());
        }
        if (fromDate != null) {
            Intrinsics.checkNotNull(throughDate);
            if (fromDate.compareTo(throughDate) > 0) {
                throw new IllegalArgumentException("\u0646\u0637\u0627\u0642 \u0627\u0644\u062a\u0627\u0631\u064a\u062e \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
            }
        }
        StringBuilder sb = new StringBuilder(" WHERE status IN ('POSTED','PARTIALLY_PAID','PAID')");
        ArrayList arrayList = new ArrayList();
        if (fromDate != null) {
            sb.append(" AND posted_operating_date>=? AND posted_operating_date<=?");
            arrayList.add(fromDate);
            Intrinsics.checkNotNull(throughDate);
            arrayList.add(throughDate);
        }
        Cursor cursorRawQuery = this.db.rawQuery("SELECT id,total_usd_cents FROM sales_invoices" + ((Object) sb) + " ORDER BY updated_at,id", (String[]) arrayList.toArray(new String[0]));
        try {
            Cursor cursor = cursorRawQuery;
            int i3 = 0;
            int i4 = 0;
            long jM = 0;
            long jM2 = 0;
            while (cursor.moveToNext()) {
                String string = cursor.getString(i2);
                i3++;
                jM = MainActivity$$ExternalSyntheticBackport0.m(jM, cursor.getLong(i));
                Cursor cursorRawQuery2 = this.db.rawQuery("SELECT cogs_usd_cents FROM sales_items WHERE sales_invoice_id=?", new String[]{string});
                try {
                    Cursor cursor2 = cursorRawQuery2;
                    int i5 = 0;
                    int i6 = 0;
                    while (cursor2.moveToNext()) {
                        i5++;
                        try {
                            if (cursor2.isNull(0)) {
                                i6 = i;
                            } else {
                                Cursor cursor3 = cursorRawQuery2;
                                try {
                                    jM2 = MainActivity$$ExternalSyntheticBackport0.m(jM2, cursor2.getLong(0));
                                    cursorRawQuery2 = cursor3;
                                    i = 1;
                                } catch (Throwable th2) {
                                    th = th2;
                                    cursorRawQuery2 = cursor3;
                                    try {
                                        throw th;
                                    } catch (Throwable th3) {
                                        CloseableKt.closeFinally(cursorRawQuery2, th);
                                        throw th3;
                                    }
                                }
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            th = th;
                            throw th;
                        }
                    }
                    Cursor cursor4 = cursorRawQuery2;
                    try {
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(cursorRawQuery2, null);
                        if (i5 == 0 || i6 != 0) {
                            i4++;
                        }
                        i = 1;
                        i2 = 0;
                    } catch (Throwable th5) {
                        th = th5;
                        cursorRawQuery2 = cursor4;
                        th = th;
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
            }
            Unit unit2 = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            StringBuilder sb2 = new StringBuilder(" WHERE status='POSTED' AND original_invoice_type='SALE'");
            ArrayList arrayList2 = new ArrayList();
            if (fromDate != null) {
                sb2.append(" AND return_date>=? AND return_date<=?");
                arrayList2.add(fromDate);
                Intrinsics.checkNotNull(throughDate);
                str = throughDate;
                arrayList2.add(str);
            } else {
                str = throughDate;
            }
            Cursor cursorRawQuery3 = this.db.rawQuery("SELECT total_usd_cents,returned_cogs_usd_cents FROM returns" + ((Object) sb2) + " ORDER BY return_date,id", (String[]) arrayList2.toArray(new String[0]));
            try {
                Cursor cursor5 = cursorRawQuery3;
                long jM3 = 0;
                long jM4 = 0;
                int i7 = 0;
                int i8 = 0;
                while (cursor5.moveToNext()) {
                    i7++;
                    long j = jM2;
                    jM3 = MainActivity$$ExternalSyntheticBackport0.m(jM3, cursor5.getLong(0));
                    if (cursor5.isNull(1)) {
                        i8++;
                    } else {
                        jM4 = MainActivity$$ExternalSyntheticBackport0.m(jM4, cursor5.getLong(1));
                        jM3 = jM3;
                    }
                    jM2 = j;
                }
                long j2 = jM2;
                Unit unit3 = Unit.INSTANCE;
                CloseableKt.closeFinally(cursorRawQuery3, null);
                String str2 = fromDate == null ? "" : " WHERE expense_date>=? AND expense_date<=?";
                if (fromDate == null) {
                    strArr = null;
                } else {
                    Intrinsics.checkNotNull(throughDate);
                    strArr = new String[]{fromDate, str};
                }
                Cursor cursorRawQuery4 = this.db.rawQuery("SELECT COUNT(*),COALESCE(SUM(amount_usd_cents),0) FROM operating_expenses".concat(str2), strArr);
                try {
                    Cursor cursor6 = cursorRawQuery4;
                    cursor6.moveToFirst();
                    Pair pair = TuplesKt.to(Integer.valueOf(cursor6.getInt(0)), Long.valueOf(cursor6.getLong(1)));
                    CloseableKt.closeFinally(cursorRawQuery4, null);
                    boolean z = i4 == 0 && i8 == 0;
                    return new SalesProfitSummary(fromDate, throughDate, i3, jM, j2, z ? DailyBalanceRepository$$ExternalSyntheticBackport0.m(DailyBalanceRepository$$ExternalSyntheticBackport0.m(jM, jM3), DailyBalanceRepository$$ExternalSyntheticBackport0.m(j2, jM4)) : 0L, i4, z, i7, jM3, jM4, i8, ((Number) pair.getFirst()).intValue(), ((Number) pair.getSecond()).longValue());
                } catch (Throwable th7) {
                    try {
                        throw th7;
                    } catch (Throwable th8) {
                        CloseableKt.closeFinally(cursorRawQuery4, th7);
                        throw th8;
                    }
                }
            } catch (Throwable th9) {
                try {
                    throw th9;
                } catch (Throwable th10) {
                    CloseableKt.closeFinally(cursorRawQuery3, th9);
                    throw th10;
                }
            }
        } catch (Throwable th11) {
            try {
                throw th11;
            } catch (Throwable th12) {
                CloseableKt.closeFinally(cursorRawQuery, th11);
                throw th12;
            }
        }
    }
}
