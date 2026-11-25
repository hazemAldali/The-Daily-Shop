package com.hazem.dailystore.data;

import android.content.Context;
import android.database.Cursor;
import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* JADX INFO: compiled from: PartyStatementRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000eJ\u001c\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u000eH\u0002J6\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u000e2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002R\u0018\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/hazem/dailystore/data/PartyStatementRepository;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "appContext", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "db", "Lnet/zetetic/database/sqlcipher/SQLiteDatabase;", "customer", "Lcom/hazem/dailystore/data/PartyStatement;", "customerId", "", "supplier", "supplierId", "refundTotals", "Lkotlin/Pair;", "", "returnId", "build", "id", "name", "type", "source", "", "Lcom/hazem/dailystore/data/PartyStatementEntry;", "unpriced", "", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class PartyStatementRepository {
    private final Context appContext;
    private final SQLiteDatabase db;

    public PartyStatementRepository(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.appContext = context.getApplicationContext();
        SQLiteDatabase readableDatabase = AppDatabase.INSTANCE.getInstance(context).getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        this.db = readableDatabase;
    }

    public final PartyStatement customer(String customerId) {
        Throwable th;
        int i;
        String str;
        String str2;
        String str3;
        Throwable th2;
        String str4;
        String str5;
        String str6;
        String string;
        String str7;
        long j;
        long jSypToUsdCents;
        long jUsdCentsToSyp;
        Cursor cursor;
        String str8 = "USD";
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        if (StringsKt.isBlank(customerId)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        Cursor cursorRawQuery = this.db.rawQuery("SELECT name FROM customers WHERE id=?", new String[]{customerId});
        try {
            Cursor cursor2 = cursorRawQuery;
            if (cursor2.moveToFirst()) {
                int i2 = 0;
                String string2 = cursor2.getString(0);
                CloseableKt.closeFinally(cursorRawQuery, null);
                ArrayList arrayList = new ArrayList();
                Cursor cursorRawQuery2 = this.db.rawQuery("SELECT id,invoice_number,invoice_date,total_usd_cents,\n                      exchange_rate_syp_per_usd_milli,status,created_at\n               FROM sales_invoices\n               WHERE customer_id=? AND sale_type='CREDIT'\n                 AND status IN ('POSTED','PARTIALLY_PAID','PAID')\n               ORDER BY invoice_date,created_at,id", new String[]{customerId});
                try {
                    Cursor cursor3 = cursorRawQuery2;
                    int i3 = 0;
                    while (true) {
                        i = 4;
                        str = "getString(...)";
                        long jUsdCentsToSyp2 = 0;
                        if (!cursor3.moveToNext()) {
                            break;
                        }
                        try {
                            long j2 = cursor3.getLong(4);
                            if (j2 <= 0) {
                                i3++;
                            }
                            String string3 = cursor3.getString(i2);
                            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                            String string4 = cursor3.getString(2);
                            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                            String string5 = cursor3.getString(1);
                            Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                            long j3 = cursor3.getLong(3);
                            if (j2 > 0) {
                                cursor = cursorRawQuery2;
                                try {
                                    jUsdCentsToSyp2 = CurrencyConverter.INSTANCE.usdCentsToSyp(cursor3.getLong(3), j2);
                                } catch (Throwable th3) {
                                    th = th3;
                                    cursorRawQuery2 = cursor;
                                }
                            } else {
                                cursor = cursorRawQuery2;
                            }
                            arrayList.add(new PartyStatementEntry(string3, string4, "SALE_INVOICE", string5, "\u0641\u0627\u062a\u0648\u0631\u0629 \u0628\u064a\u0639 \u0622\u062c\u0644", j3, 0L, jUsdCentsToSyp2, 0L, 0L, 0L, 0L, 0L, cursor3.getString(2) + "|" + cursor3.getString(6), 8000, null));
                            str8 = str8;
                            cursorRawQuery2 = cursor;
                            i2 = 0;
                        } catch (Throwable th4) {
                            th = th4;
                            th = th;
                        }
                        th = th;
                        try {
                            throw th;
                        } catch (Throwable th5) {
                            CloseableKt.closeFinally(cursorRawQuery2, th);
                            throw th5;
                        }
                    }
                    String str9 = str8;
                    Cursor cursor4 = cursorRawQuery2;
                    try {
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(cursorRawQuery2, null);
                        Cursor cursorRawQuery3 = this.db.rawQuery("SELECT p.id,p.invoice_id,p.amount_minor,p.currency_code,\n                      COALESCE(p.exchange_rate_syp_per_usd_milli,t.exchange_rate_syp_per_usd_milli,0),\n                      p.created_at,i.invoice_number,i.exchange_rate_syp_per_usd_milli,\n                      COALESCE(t.operating_date,substr(p.created_at,1,10))\n               FROM invoice_payments p\n               JOIN cash_transactions t ON t.id=p.cash_transaction_id\n               JOIN sales_invoices i ON i.id=p.invoice_id\n               WHERE p.invoice_type='SALE' AND i.customer_id=?\n                 AND i.sale_type='CREDIT'\n               ORDER BY p.created_at,p.id", new String[]{customerId});
                        try {
                            Cursor cursor5 = cursorRawQuery3;
                            while (cursor5.moveToNext()) {
                                long j4 = cursor5.getLong(2);
                                String string6 = cursor5.getString(3);
                                String str10 = str;
                                long j5 = cursor5.getLong(i);
                                long j6 = cursor5.getLong(7);
                                String str11 = str9;
                                if (Intrinsics.areEqual(string6, str11)) {
                                    str7 = string2;
                                    j = j4;
                                } else {
                                    if (Intrinsics.areEqual(string6, "SYP")) {
                                        if (j5 > 0) {
                                            str7 = string2;
                                            jSypToUsdCents = CurrencyConverter.INSTANCE.sypToUsdCents(j4, j5);
                                        } else {
                                            str7 = string2;
                                            if (j6 > 0) {
                                                jSypToUsdCents = CurrencyConverter.INSTANCE.sypToUsdCents(j4, j6);
                                            }
                                        }
                                        j = jSypToUsdCents;
                                    } else {
                                        str7 = string2;
                                    }
                                    j = 0;
                                }
                                if (j5 <= 0) {
                                    j5 = j6;
                                }
                                if (Intrinsics.areEqual(string6, "SYP")) {
                                    jUsdCentsToSyp = j4;
                                } else {
                                    jUsdCentsToSyp = (!Intrinsics.areEqual(string6, str11) || j5 <= 0) ? 0L : CurrencyConverter.INSTANCE.usdCentsToSyp(j4, j5);
                                }
                                if ((j == 0 || jUsdCentsToSyp == 0) && j4 > 0) {
                                    i3++;
                                }
                                String string7 = cursor5.getString(0);
                                Intrinsics.checkNotNullExpressionValue(string7, str10);
                                String string8 = cursor5.getString(5);
                                Intrinsics.checkNotNullExpressionValue(string8, str10);
                                String strTake = StringsKt.take(string8, 10);
                                String string9 = cursor5.getString(6);
                                Intrinsics.checkNotNullExpressionValue(string9, str10);
                                arrayList.add(new PartyStatementEntry(string7, strTake, "CUSTOMER_COLLECTION", string9, "\u062a\u062d\u0635\u064a\u0644 \u0639\u0644\u0649 \u0641\u0627\u062a\u0648\u0631\u0629 \u0622\u062c\u0644\u0629", 0L, j, 0L, jUsdCentsToSyp, 0L, 0L, 0L, 0L, cursor5.getString(8) + "|" + cursor5.getString(5), 7840, null));
                                str = str10;
                                string2 = str7;
                                i = 4;
                                str9 = str11;
                            }
                            String str12 = string2;
                            String str13 = str;
                            Unit unit2 = Unit.INSTANCE;
                            CloseableKt.closeFinally(cursorRawQuery3, null);
                            Cursor cursorRawQuery4 = this.db.rawQuery("SELECT id,return_number,return_date,credit_reduction_usd_cents,\n                      exchange_rate_syp_per_usd_milli,created_at\n               FROM returns\n               WHERE party_type='CUSTOMER' AND party_id=? AND original_invoice_type='SALE'\n                 AND status='POSTED'\n               ORDER BY return_date,created_at,id", new String[]{customerId});
                            try {
                                Cursor cursor6 = cursorRawQuery4;
                                while (cursor6.moveToNext()) {
                                    long j7 = cursor6.getLong(4);
                                    long j8 = cursor6.getLong(3);
                                    if (j8 > 0 && j7 <= 0) {
                                        i3++;
                                    }
                                    String string10 = cursor6.getString(0);
                                    Intrinsics.checkNotNullExpressionValue(string10, str13);
                                    Pair<Long, Long> pairRefundTotals = refundTotals(string10);
                                    if (j8 != 0 || pairRefundTotals.getFirst().longValue() != 0 || pairRefundTotals.getSecond().longValue() != 0) {
                                        String string11 = cursor6.getString(0);
                                        Intrinsics.checkNotNullExpressionValue(string11, str13);
                                        String string12 = cursor6.getString(2);
                                        Intrinsics.checkNotNullExpressionValue(string12, str13);
                                        String string13 = cursor6.getString(1);
                                        Intrinsics.checkNotNullExpressionValue(string13, str13);
                                        long jUsdCentsToSyp3 = j7 > 0 ? CurrencyConverter.INSTANCE.usdCentsToSyp(j8, j7) : 0L;
                                        arrayList.add(new PartyStatementEntry(string11, string12, "SALES_RETURN", string13, "\u062a\u062e\u0641\u064a\u0636 \u062f\u064a\u0646 \u0627\u0644\u0639\u0645\u064a\u0644 \u0628\u0645\u0631\u062a\u062c\u0639", 0L, j8, 0L, jUsdCentsToSyp3, pairRefundTotals.getFirst().longValue(), pairRefundTotals.getSecond().longValue(), 0L, 0L, cursor6.getString(2) + "|" + cursor6.getString(5), 6304, null));
                                    }
                                    i3 = i3;
                                }
                                Unit unit3 = Unit.INSTANCE;
                                CloseableKt.closeFinally(cursorRawQuery4, null);
                                Context appContext = this.appContext;
                                Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
                                String str14 = "\u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0643\u0627\u0634";
                                String str15 = "\u0634\u0627\u0645 \u0643\u0627\u0634";
                                String str16 = "\u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0627\u0644\u0639\u0627\u062f\u064a";
                                if (new SyncProtocolRepository(appContext, null, 2, null).isConfiguredSecondary()) {
                                    Cursor cursorRawQuery5 = this.db.rawQuery("SELECT transfer_id,service_type,charge_minor,operating_date,created_at,reference\n                   FROM service_transfer_receivable_mirrors\n                   WHERE customer_id=? AND status!='REVERSED'\n                   ORDER BY operating_date,created_at,transfer_id", new String[]{customerId});
                                    try {
                                        Cursor cursor7 = cursorRawQuery5;
                                        while (cursor7.moveToNext()) {
                                            String string14 = cursor7.getString(1);
                                            if (Intrinsics.areEqual(string14, ServiceTransferRepository.SYRIATEL_BALANCE)) {
                                                str5 = str14;
                                                str6 = str16;
                                            } else if (Intrinsics.areEqual(string14, ServiceTransferRepository.SYRIATEL_CASH)) {
                                                str6 = str14;
                                                str5 = str6;
                                            } else {
                                                str5 = str14;
                                                str6 = str15;
                                            }
                                            String string15 = cursor7.getString(3);
                                            cursorRawQuery5 = cursorRawQuery5;
                                            String str17 = str15;
                                            try {
                                                String string16 = cursor7.getString(0);
                                                Intrinsics.checkNotNullExpressionValue(string16, str13);
                                                Intrinsics.checkNotNull(string15);
                                                if (cursor7.isNull(5)) {
                                                    try {
                                                        string = cursor7.getString(0);
                                                    } catch (Throwable th6) {
                                                        th2 = th6;
                                                        cursorRawQuery5 = cursorRawQuery5;
                                                        try {
                                                            throw th2;
                                                        } catch (Throwable th7) {
                                                            CloseableKt.closeFinally(cursorRawQuery5, th2);
                                                            throw th7;
                                                        }
                                                    }
                                                } else {
                                                    string = cursor7.getString(5);
                                                }
                                                Intrinsics.checkNotNull(string);
                                                String str18 = str16;
                                                arrayList.add(new PartyStatementEntry(string16, string15, "SERVICE_TRANSFER_CREDIT", string, "\u062a\u062d\u0648\u064a\u0644 \u0631\u0635\u064a\u062f \u0622\u062c\u0644 \u2022 " + str6, 0L, 0L, cursor7.getLong(2), 0L, 0L, 0L, 0L, 0L, string15 + "|" + cursor7.getString(4), 8032, null));
                                                str14 = str5;
                                                str16 = str18;
                                                str15 = str17;
                                            } catch (Throwable th8) {
                                                th = th8;
                                                cursorRawQuery5 = cursorRawQuery5;
                                                th2 = th;
                                                throw th2;
                                            }
                                        }
                                        String str19 = str14;
                                        String str20 = str15;
                                        String str21 = str16;
                                        Unit unit4 = Unit.INSTANCE;
                                        CloseableKt.closeFinally(cursorRawQuery5, null);
                                        Cursor cursorRawQuery6 = this.db.rawQuery("SELECT p.payment_id,p.transfer_id,p.amount_minor,p.operating_date,p.created_at,\n                          r.service_type,COALESCE(r.reference,r.transfer_id)\n                   FROM service_transfer_receivable_payment_mirrors p\n                   JOIN service_transfer_receivable_mirrors r ON r.transfer_id=p.transfer_id\n                   WHERE r.customer_id=?\n                   ORDER BY p.operating_date,p.created_at,p.payment_id", new String[]{customerId});
                                        try {
                                            Cursor cursor8 = cursorRawQuery6;
                                            while (cursor8.moveToNext()) {
                                                String string17 = cursor8.getString(5);
                                                if (Intrinsics.areEqual(string17, ServiceTransferRepository.SYRIATEL_BALANCE)) {
                                                    str4 = str21;
                                                } else {
                                                    str4 = Intrinsics.areEqual(string17, ServiceTransferRepository.SYRIATEL_CASH) ? str19 : str20;
                                                }
                                                String string18 = cursor8.getString(3);
                                                String string19 = cursor8.getString(0);
                                                Intrinsics.checkNotNullExpressionValue(string19, str13);
                                                Intrinsics.checkNotNull(string18);
                                                String string20 = cursor8.getString(6);
                                                Intrinsics.checkNotNullExpressionValue(string20, str13);
                                                arrayList.add(new PartyStatementEntry(string19, string18, "SERVICE_TRANSFER_COLLECTION", string20, "\u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0646 \u062a\u062d\u0648\u064a\u0644 \u0631\u0635\u064a\u062f \u2022 " + str4, 0L, 0L, 0L, cursor8.getLong(2), 0L, 0L, 0L, 0L, string18 + "|" + cursor8.getString(4), 7904, null));
                                            }
                                            Unit unit5 = Unit.INSTANCE;
                                            CloseableKt.closeFinally(cursorRawQuery6, null);
                                        } catch (Throwable th9) {
                                            try {
                                                throw th9;
                                            } catch (Throwable th10) {
                                                CloseableKt.closeFinally(cursorRawQuery6, th9);
                                                throw th10;
                                            }
                                        }
                                    } catch (Throwable th11) {
                                        th = th11;
                                    }
                                } else {
                                    Cursor cursorRawQuery7 = this.db.rawQuery("SELECT t.id,t.service_type,t.customer_charge_minor,\n                          COALESCE(t.operating_date,substr(t.created_at,1,10)),t.created_at,t.reference\n                   FROM service_transfers t\n                   WHERE t.customer_id=? AND t.payment_mode='CREDIT' AND t.status='POSTED'\n                   ORDER BY COALESCE(t.operating_date,substr(t.created_at,1,10)),t.created_at,t.id", new String[]{customerId});
                                    try {
                                        Cursor cursor9 = cursorRawQuery7;
                                        while (cursor9.moveToNext()) {
                                            String string21 = cursor9.getString(1);
                                            if (Intrinsics.areEqual(string21, ServiceTransferRepository.SYRIATEL_BALANCE)) {
                                                str3 = "\u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0627\u0644\u0639\u0627\u062f\u064a";
                                            } else {
                                                str3 = Intrinsics.areEqual(string21, ServiceTransferRepository.SYRIATEL_CASH) ? "\u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0643\u0627\u0634" : "\u0634\u0627\u0645 \u0643\u0627\u0634";
                                            }
                                            String string22 = cursor9.getString(3);
                                            String string23 = cursor9.getString(0);
                                            Intrinsics.checkNotNullExpressionValue(string23, str13);
                                            Intrinsics.checkNotNull(string22);
                                            String string24 = cursor9.isNull(5) ? cursor9.getString(0) : cursor9.getString(5);
                                            Intrinsics.checkNotNull(string24);
                                            arrayList.add(new PartyStatementEntry(string23, string22, "SERVICE_TRANSFER_CREDIT", string24, "\u062a\u062d\u0648\u064a\u0644 \u0631\u0635\u064a\u062f \u0622\u062c\u0644 \u2022 " + str3, 0L, 0L, cursor9.getLong(2), 0L, 0L, 0L, 0L, 0L, string22 + "|" + cursor9.getString(4), 8032, null));
                                        }
                                        Unit unit6 = Unit.INSTANCE;
                                        CloseableKt.closeFinally(cursorRawQuery7, null);
                                        Cursor cursorRawQuery8 = this.db.rawQuery("SELECT p.id,p.transfer_id,p.amount_minor,p.operating_date,p.created_at,\n                          t.service_type,COALESCE(t.reference,t.id)\n                   FROM service_transfer_receivable_payments p\n                   JOIN service_transfers t ON t.id=p.transfer_id\n                   WHERE t.customer_id=?\n                   ORDER BY p.operating_date,p.created_at,p.id", new String[]{customerId});
                                        try {
                                            Cursor cursor10 = cursorRawQuery8;
                                            while (cursor10.moveToNext()) {
                                                String string25 = cursor10.getString(5);
                                                if (Intrinsics.areEqual(string25, ServiceTransferRepository.SYRIATEL_BALANCE)) {
                                                    str2 = "\u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0627\u0644\u0639\u0627\u062f\u064a";
                                                } else {
                                                    str2 = Intrinsics.areEqual(string25, ServiceTransferRepository.SYRIATEL_CASH) ? "\u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0643\u0627\u0634" : "\u0634\u0627\u0645 \u0643\u0627\u0634";
                                                }
                                                String string26 = cursor10.getString(3);
                                                String string27 = cursor10.getString(0);
                                                Intrinsics.checkNotNullExpressionValue(string27, str13);
                                                Intrinsics.checkNotNull(string26);
                                                String string28 = cursor10.getString(6);
                                                Intrinsics.checkNotNullExpressionValue(string28, str13);
                                                arrayList.add(new PartyStatementEntry(string27, string26, "SERVICE_TRANSFER_COLLECTION", string28, "\u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0646 \u062a\u062d\u0648\u064a\u0644 \u0631\u0635\u064a\u062f \u2022 " + str2, 0L, 0L, 0L, cursor10.getLong(2), 0L, 0L, 0L, 0L, string26 + "|" + cursor10.getString(4), 7904, null));
                                            }
                                            Unit unit7 = Unit.INSTANCE;
                                            CloseableKt.closeFinally(cursorRawQuery8, null);
                                        } catch (Throwable th12) {
                                            try {
                                                throw th12;
                                            } catch (Throwable th13) {
                                                CloseableKt.closeFinally(cursorRawQuery8, th12);
                                                throw th13;
                                            }
                                        }
                                    } catch (Throwable th14) {
                                        try {
                                            throw th14;
                                        } catch (Throwable th15) {
                                            CloseableKt.closeFinally(cursorRawQuery7, th14);
                                            throw th15;
                                        }
                                    }
                                }
                                Intrinsics.checkNotNull(str12);
                                return build(customerId, str12, "CUSTOMER", arrayList, i3);
                            } catch (Throwable th16) {
                                try {
                                    throw th16;
                                } catch (Throwable th17) {
                                    CloseableKt.closeFinally(cursorRawQuery4, th16);
                                    throw th17;
                                }
                            }
                        } catch (Throwable th18) {
                            try {
                                throw th18;
                            } catch (Throwable th19) {
                                CloseableKt.closeFinally(cursorRawQuery3, th18);
                                throw th19;
                            }
                        }
                    } catch (Throwable th20) {
                        th = th20;
                        cursorRawQuery2 = cursor4;
                    }
                } catch (Throwable th21) {
                    th = th21;
                }
            } else {
                throw new IllegalArgumentException("\u0627\u0644\u0639\u0645\u064a\u0644 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
            }
        } catch (Throwable th22) {
            try {
                throw th22;
            } catch (Throwable th23) {
                CloseableKt.closeFinally(cursorRawQuery, th22);
                throw th23;
            }
        }
    }

    public final PartyStatement supplier(String supplierId) {
        Throwable th;
        String str;
        long jSypToUsdCents;
        long jUsdCentsToSyp;
        Cursor cursor;
        String str2 = "USD";
        Intrinsics.checkNotNullParameter(supplierId, "supplierId");
        if (StringsKt.isBlank(supplierId)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        Cursor cursorRawQuery = this.db.rawQuery("SELECT name FROM suppliers WHERE id=?", new String[]{supplierId});
        try {
            Cursor cursor2 = cursorRawQuery;
            if (cursor2.moveToFirst()) {
                int i = 0;
                String string = cursor2.getString(0);
                CloseableKt.closeFinally(cursorRawQuery, null);
                ArrayList arrayList = new ArrayList();
                Cursor cursorRawQuery2 = this.db.rawQuery("SELECT id,invoice_number,invoice_date,total_usd_cents,\n                      exchange_rate_syp_per_usd_milli,created_at\n               FROM purchase_invoices\n               WHERE supplier_id=? AND status IN ('POSTED','PARTIALLY_PAID','PAID')\n               ORDER BY invoice_date,created_at,id", new String[]{supplierId});
                try {
                    Cursor cursor3 = cursorRawQuery2;
                    int i2 = 0;
                    while (true) {
                        str = "|";
                        long jUsdCentsToSyp2 = 0;
                        if (!cursor3.moveToNext()) {
                            break;
                        }
                        try {
                            long j = cursor3.getLong(4);
                            if (j <= 0) {
                                i2++;
                            }
                            String string2 = cursor3.getString(i);
                            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                            String string3 = cursor3.getString(2);
                            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                            String string4 = cursor3.getString(1);
                            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                            long j2 = cursor3.getLong(3);
                            if (j > 0) {
                                cursor = cursorRawQuery2;
                                try {
                                    jUsdCentsToSyp2 = CurrencyConverter.INSTANCE.usdCentsToSyp(cursor3.getLong(3), j);
                                } catch (Throwable th2) {
                                    th = th2;
                                    cursorRawQuery2 = cursor;
                                }
                            } else {
                                cursor = cursorRawQuery2;
                            }
                            arrayList.add(new PartyStatementEntry(string2, string3, "PURCHASE_INVOICE", string4, "\u0641\u0627\u062a\u0648\u0631\u0629 \u0634\u0631\u0627\u0621", j2, 0L, jUsdCentsToSyp2, 0L, 0L, 0L, 0L, 0L, cursor3.getString(2) + "|" + cursor3.getString(5), 8000, null));
                            str2 = str2;
                            cursorRawQuery2 = cursor;
                            i = 0;
                        } catch (Throwable th3) {
                            th = th3;
                            th = th;
                        }
                        th = th;
                        try {
                            throw th;
                        } catch (Throwable th4) {
                            CloseableKt.closeFinally(cursorRawQuery2, th);
                            throw th4;
                        }
                    }
                    String str3 = str2;
                    Cursor cursor4 = cursorRawQuery2;
                    try {
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(cursorRawQuery2, null);
                        Cursor cursorRawQuery3 = this.db.rawQuery("SELECT p.id,p.amount_minor,p.currency_code,\n                      COALESCE(p.exchange_rate_syp_per_usd_milli,t.exchange_rate_syp_per_usd_milli,\n                               i.exchange_rate_syp_per_usd_milli,0),\n                      p.created_at,i.invoice_number,\n                      COALESCE(t.operating_date,substr(p.created_at,1,10))\n               FROM invoice_payments p\n               JOIN cash_transactions t ON t.id=p.cash_transaction_id\n               JOIN purchase_invoices i ON i.id=p.invoice_id\n               WHERE p.invoice_type='PURCHASE' AND i.supplier_id=?\n               ORDER BY p.created_at,p.id", new String[]{supplierId});
                        try {
                            Cursor cursor5 = cursorRawQuery3;
                            while (cursor5.moveToNext()) {
                                String str4 = str;
                                long j3 = cursor5.getLong(1);
                                long j4 = cursor5.getLong(3);
                                String string5 = cursor5.getString(2);
                                String str5 = str3;
                                if (Intrinsics.areEqual(string5, str5)) {
                                    jSypToUsdCents = j3;
                                } else {
                                    jSypToUsdCents = (!Intrinsics.areEqual(string5, "SYP") || j4 <= 0) ? 0L : CurrencyConverter.INSTANCE.sypToUsdCents(j3, j4);
                                }
                                if (Intrinsics.areEqual(string5, "SYP")) {
                                    jUsdCentsToSyp = j3;
                                } else {
                                    jUsdCentsToSyp = (!Intrinsics.areEqual(string5, str5) || j4 <= 0) ? 0L : CurrencyConverter.INSTANCE.usdCentsToSyp(j3, j4);
                                }
                                if ((jSypToUsdCents == 0 || jUsdCentsToSyp == 0) && j3 > 0) {
                                    i2++;
                                }
                                String string6 = cursor5.getString(0);
                                Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                                String string7 = cursor5.getString(4);
                                Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                                String strTake = StringsKt.take(string7, 10);
                                String string8 = cursor5.getString(5);
                                Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
                                arrayList.add(new PartyStatementEntry(string6, strTake, "SUPPLIER_PAYMENT", string8, "\u062f\u0641\u0639\u0629 \u0644\u0644\u0645\u0648\u0631\u062f", 0L, jSypToUsdCents, 0L, jUsdCentsToSyp, 0L, 0L, 0L, 0L, cursor5.getString(6) + str4 + cursor5.getString(4), 7840, null));
                                str = str4;
                                str3 = str5;
                            }
                            String str6 = str;
                            Unit unit2 = Unit.INSTANCE;
                            CloseableKt.closeFinally(cursorRawQuery3, null);
                            Cursor cursorRawQuery4 = this.db.rawQuery("SELECT id,return_number,return_date,supplier_credit_reduction_syp_minor,\n                      supplier_cash_refund_syp_minor,exchange_rate_syp_per_usd_milli,created_at\n               FROM returns\n               WHERE party_type='SUPPLIER' AND party_id=? AND original_invoice_type='PURCHASE'\n                 AND status='POSTED'\n               ORDER BY return_date,created_at,id", new String[]{supplierId});
                            try {
                                Cursor cursor6 = cursorRawQuery4;
                                while (cursor6.moveToNext()) {
                                    long j5 = cursor6.getLong(3);
                                    long j6 = cursor6.getLong(5);
                                    long jSypToUsdCents2 = (j5 <= 0 || j6 <= 0) ? 0L : CurrencyConverter.INSTANCE.sypToUsdCents(j5, j6);
                                    if (j5 > 0 && j6 <= 0) {
                                        i2++;
                                    }
                                    String string9 = cursor6.getString(0);
                                    Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
                                    String string10 = cursor6.getString(2);
                                    Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
                                    String string11 = cursor6.getString(1);
                                    Intrinsics.checkNotNullExpressionValue(string11, "getString(...)");
                                    arrayList.add(new PartyStatementEntry(string9, string10, "PURCHASE_RETURN", string11, "\u0645\u0631\u062a\u062c\u0639 \u0645\u0634\u062a\u0631\u064a\u0627\u062a \u0648\u062a\u062e\u0641\u064a\u0636 \u0645\u0633\u062a\u062d\u0642 \u0627\u0644\u0645\u0648\u0631\u062f", 0L, jSypToUsdCents2, 0L, j5, 0L, cursor6.getLong(4), 0L, 0L, cursor6.getString(2) + str6 + cursor6.getString(6), 6816, null));
                                }
                                Unit unit3 = Unit.INSTANCE;
                                CloseableKt.closeFinally(cursorRawQuery4, null);
                                Intrinsics.checkNotNull(string);
                                return build(supplierId, string, "SUPPLIER", arrayList, i2);
                            } catch (Throwable th5) {
                                try {
                                    throw th5;
                                } catch (Throwable th6) {
                                    CloseableKt.closeFinally(cursorRawQuery4, th5);
                                    throw th6;
                                }
                            }
                        } catch (Throwable th7) {
                            try {
                                throw th7;
                            } catch (Throwable th8) {
                                CloseableKt.closeFinally(cursorRawQuery3, th7);
                                throw th8;
                            }
                        }
                    } catch (Throwable th9) {
                        th = th9;
                        cursorRawQuery2 = cursor4;
                    }
                } catch (Throwable th10) {
                    th = th10;
                }
            } else {
                throw new IllegalArgumentException("\u0627\u0644\u0645\u0648\u0631\u062f \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
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

    private final Pair<Long, Long> refundTotals(String returnId) throws IOException {
        Cursor cursorRawQuery = this.db.rawQuery("SELECT amount_minor,currency_code FROM return_refund_allocations\n               WHERE return_id=?", new String[]{returnId});
        try {
            Cursor cursor = cursorRawQuery;
            long jM = 0;
            long jM2 = 0;
            while (cursor.moveToNext()) {
                String string = cursor.getString(1);
                if (Intrinsics.areEqual(string, "USD")) {
                    jM = MainActivity$$ExternalSyntheticBackport0.m(jM, cursor.getLong(0));
                } else if (Intrinsics.areEqual(string, "SYP")) {
                    jM2 = MainActivity$$ExternalSyntheticBackport0.m(jM2, cursor.getLong(0));
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            return TuplesKt.to(Long.valueOf(jM), Long.valueOf(jM2));
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final PartyStatement build(String id, String name, String type, List<PartyStatementEntry> source, int unpriced) {
        final Comparator comparator = new Comparator() { // from class: com.hazem.dailystore.data.PartyStatementRepository$build$$inlined$compareBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(((PartyStatementEntry) t).getSortAt(), ((PartyStatementEntry) t2).getSortAt());
            }
        };
        List<PartyStatementEntry> listSortedWith = CollectionsKt.sortedWith(source, new Comparator() { // from class: com.hazem.dailystore.data.PartyStatementRepository$build$$inlined$thenBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int iCompare = comparator.compare(t, t2);
                return iCompare != 0 ? iCompare : ComparisonsKt.compareValues(((PartyStatementEntry) t).getId(), ((PartyStatementEntry) t2).getId());
            }
        });
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSortedWith, 10));
        long jM = 0;
        long jM2 = 0;
        for (PartyStatementEntry partyStatementEntry : listSortedWith) {
            jM = MainActivity$$ExternalSyntheticBackport0.m(jM, DailyBalanceRepository$$ExternalSyntheticBackport0.m(partyStatementEntry.getDebitUsdCents(), partyStatementEntry.getCreditUsdCents()));
            jM2 = MainActivity$$ExternalSyntheticBackport0.m(jM2, DailyBalanceRepository$$ExternalSyntheticBackport0.m(partyStatementEntry.getDebitSypMinor(), partyStatementEntry.getCreditSypMinor()));
            arrayList.add(partyStatementEntry.copy((10239 & 1) != 0 ? partyStatementEntry.id : null, (10239 & 2) != 0 ? partyStatementEntry.date : null, (10239 & 4) != 0 ? partyStatementEntry.type : null, (10239 & 8) != 0 ? partyStatementEntry.reference : null, (10239 & 16) != 0 ? partyStatementEntry.description : null, (10239 & 32) != 0 ? partyStatementEntry.debitUsdCents : 0L, (10239 & 64) != 0 ? partyStatementEntry.creditUsdCents : 0L, (10239 & 128) != 0 ? partyStatementEntry.debitSypMinor : 0L, (10239 & 256) != 0 ? partyStatementEntry.creditSypMinor : 0L, (10239 & 512) != 0 ? partyStatementEntry.cashRefundUsdCents : 0L, (10239 & 1024) != 0 ? partyStatementEntry.cashRefundSypMinor : 0L, (10239 & 2048) != 0 ? partyStatementEntry.balanceUsdCents : jM, (10239 & 4096) != 0 ? partyStatementEntry.balanceSypMinor : jM2, (10239 & 8192) != 0 ? partyStatementEntry.sortAt : null));
        }
        return new PartyStatement(id, name, type, arrayList, jM, jM2, unpriced);
    }
}
