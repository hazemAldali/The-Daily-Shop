package com.hazem.dailystore.data;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import androidx.core.view.ViewCompat;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PartyStatementExport.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\t\u001a\u00020\nJ&\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u001e\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0002J\u0010\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0016H\u0002J \u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0002J\u0010\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0002J\u0010\u0010 \u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0002J\u0010\u0010!\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\"H\u0002J\u0010\u0010#\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\"H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/hazem/dailystore/data/PartyStatementExport;", "", "<init>", "()V", "headers", "", "", "csv", "", "statement", "Lcom/hazem/dailystore/data/PartyStatement;", "xlsx", "pdf", "pdfLines", "wrapPdfLine", "value", "maxWidth", "", "paint", "Landroid/graphics/Paint;", "xlsxRow", "number", "", "values", "column", "index", "zipEntry", "", "zip", "Ljava/util/zip/ZipOutputStream;", "name", "csvCell", "xmlEscape", "money", "", "syp", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class PartyStatementExport {
    public static final PartyStatementExport INSTANCE = new PartyStatementExport();
    private static final List<String> headers = CollectionsKt.listOf((Object[]) new String[]{"\u0627\u0644\u062a\u0627\u0631\u064a\u062e", "\u0627\u0644\u0646\u0648\u0639", "\u0627\u0644\u0645\u0631\u062c\u0639", "\u0627\u0644\u0628\u064a\u0627\u0646", "\u0645\u062f\u064a\u0646 USD \u0633\u0646\u062a", "\u062f\u0627\u0626\u0646 USD \u0633\u0646\u062a", "\u0645\u062f\u064a\u0646 \u0644\u064a\u0631\u0629", "\u062f\u0627\u0626\u0646 \u0644\u064a\u0631\u0629", "\u0627\u0633\u062a\u0631\u062f\u0627\u062f USD \u0633\u0646\u062a", "\u0627\u0633\u062a\u0631\u062f\u0627\u062f \u0644\u064a\u0631\u0629", "\u0627\u0644\u0631\u0635\u064a\u062f USD \u0633\u0646\u062a", "\u0627\u0644\u0631\u0635\u064a\u062f \u0644\u064a\u0631\u0629"});

    private PartyStatementExport() {
    }

    public final byte[] csv(PartyStatement statement) {
        Intrinsics.checkNotNullParameter(statement, "statement");
        StringBuilder sb = new StringBuilder("\ufeff");
        sb.append(CollectionsKt.joinToString$default(headers, ",", null, null, 0, null, new Function1() { // from class: com.hazem.dailystore.data.PartyStatementExport$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PartyStatementExport.csv$lambda$0((String) obj);
            }
        }, 30, null));
        sb.append("\r\n");
        for (PartyStatementEntry partyStatementEntry : statement.getEntries()) {
            sb.append(CollectionsKt.joinToString$default(CollectionsKt.listOf((Object[]) new String[]{partyStatementEntry.getDate(), partyStatementEntry.getType(), partyStatementEntry.getReference(), partyStatementEntry.getDescription(), String.valueOf(partyStatementEntry.getDebitUsdCents()), String.valueOf(partyStatementEntry.getCreditUsdCents()), String.valueOf(partyStatementEntry.getDebitSypMinor()), String.valueOf(partyStatementEntry.getCreditSypMinor()), String.valueOf(partyStatementEntry.getCashRefundUsdCents()), String.valueOf(partyStatementEntry.getCashRefundSypMinor()), String.valueOf(partyStatementEntry.getBalanceUsdCents()), String.valueOf(partyStatementEntry.getBalanceSypMinor())}), ",", null, null, 0, null, new Function1() { // from class: com.hazem.dailystore.data.PartyStatementExport$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return PartyStatementExport.csv$lambda$2$lambda$1((String) obj);
                }
            }, 30, null));
            sb.append("\r\n");
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        Charset UTF_8 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
        byte[] bytes = string.getBytes(UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        return bytes;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence csv$lambda$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return INSTANCE.csvCell(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence csv$lambda$2$lambda$1(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return INSTANCE.csvCell(it);
    }

    public final byte[] xlsx(PartyStatement statement) {
        Intrinsics.checkNotNullParameter(statement, "statement");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
        try {
            ZipOutputStream zipOutputStream2 = zipOutputStream;
            PartyStatementExport partyStatementExport = INSTANCE;
            partyStatementExport.zipEntry(zipOutputStream2, "[Content_Types].xml", "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Types xmlns=\"http://schemas.openxmlformats.org/package/2006/content-types\">\n<Default Extension=\"rels\" ContentType=\"application/vnd.openxmlformats-package.relationships+xml\"/>\n<Default Extension=\"xml\" ContentType=\"application/xml\"/>\n<Override PartName=\"/xl/workbook.xml\" ContentType=\"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet.main+xml\"/>\n<Override PartName=\"/xl/worksheets/sheet1.xml\" ContentType=\"application/vnd.openxmlformats-officedocument.spreadsheetml.worksheet+xml\"/>\n</Types>");
            partyStatementExport.zipEntry(zipOutputStream2, "_rels/.rels", "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Relationships xmlns=\"http://schemas.openxmlformats.org/package/2006/relationships\">\n<Relationship Id=\"rId1\" Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument\" Target=\"xl/workbook.xml\"/>\n</Relationships>");
            partyStatementExport.zipEntry(zipOutputStream2, "xl/workbook.xml", "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<workbook xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\">\n<sheets><sheet name=\"\u0643\u0634\u0641 \u0627\u0644\u062d\u0633\u0627\u0628\" sheetId=\"1\" r:id=\"rId1\"/></sheets></workbook>");
            partyStatementExport.zipEntry(zipOutputStream2, "xl/_rels/workbook.xml.rels", "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Relationships xmlns=\"http://schemas.openxmlformats.org/package/2006/relationships\">\n<Relationship Id=\"rId1\" Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/worksheet\" Target=\"worksheets/sheet1.xml\"/>\n</Relationships>");
            StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<worksheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\"><sheetViews><sheetView workbookViewId=\"0\" rightToLeft=\"1\"/></sheetViews><sheetData>");
            sb.append(partyStatementExport.xlsxRow(1, headers));
            int i = 0;
            for (Object obj : statement.getEntries()) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                PartyStatementEntry partyStatementEntry = (PartyStatementEntry) obj;
                sb.append(INSTANCE.xlsxRow(i + 2, CollectionsKt.listOf(partyStatementEntry.getDate(), partyStatementEntry.getType(), partyStatementEntry.getReference(), partyStatementEntry.getDescription(), Long.valueOf(partyStatementEntry.getDebitUsdCents()), Long.valueOf(partyStatementEntry.getCreditUsdCents()), Long.valueOf(partyStatementEntry.getDebitSypMinor()), Long.valueOf(partyStatementEntry.getCreditSypMinor()), Long.valueOf(partyStatementEntry.getCashRefundUsdCents()), Long.valueOf(partyStatementEntry.getCashRefundSypMinor()), Long.valueOf(partyStatementEntry.getBalanceUsdCents()), Long.valueOf(partyStatementEntry.getBalanceSypMinor()))));
                i = i2;
            }
            sb.append("</sheetData></worksheet>");
            PartyStatementExport partyStatementExport2 = INSTANCE;
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            partyStatementExport2.zipEntry(zipOutputStream2, "xl/worksheets/sheet1.xml", string);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(zipOutputStream, null);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
            return byteArray;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(zipOutputStream, th);
                throw th2;
            }
        }
    }

    public final byte[] pdf(PartyStatement statement) {
        Intrinsics.checkNotNullParameter(statement, "statement");
        PdfDocument pdfDocument = new PdfDocument();
        int i = 842;
        float f = 842 - 36.0f;
        Paint paint = new Paint(1);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint.setTextSize(10.0f);
        int i2 = 0;
        paint.setTypeface(Typeface.create("sans-serif", 0));
        paint.setTextAlign(Paint.Align.RIGHT);
        List<String> listPdfLines = pdfLines(statement);
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listPdfLines.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, INSTANCE.wrapPdfLine((String) it.next(), f - 36.0f, paint));
        }
        ArrayList arrayList2 = arrayList;
        int iMax = Math.max(1, (arrayList2.size() + 28) / 29);
        Paint paint2 = new Paint(1);
        paint2.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint2.setTextSize(18.0f);
        paint2.setTypeface(Typeface.create("sans-serif", 1));
        paint2.setTextAlign(Paint.Align.RIGHT);
        int i3 = 0;
        while (i3 < iMax) {
            int i4 = i3 + 1;
            PdfDocument.Page pageStartPage = pdfDocument.startPage(new PdfDocument.PageInfo.Builder(i, 595, i4).create());
            Canvas canvas = pageStartPage.getCanvas();
            Paint paint3 = paint2;
            int i5 = iMax;
            int i6 = i2;
            canvas.drawTextRun((CharSequence) "\u0643\u0634\u0641 \u062d\u0633\u0627\u0628 ".concat(Intrinsics.areEqual(statement.getPartyType(), "CUSTOMER") ? "\u0639\u0645\u064a\u0644" : "\u0645\u0648\u0631\u062f"), 0, "\u0643\u0634\u0641 \u062d\u0633\u0627\u0628 ".concat(Intrinsics.areEqual(statement.getPartyType(), "CUSTOMER") ? "\u0639\u0645\u064a\u0644" : "\u0645\u0648\u0631\u062f").length(), 0, "\u0643\u0634\u0641 \u062d\u0633\u0627\u0628 ".concat(Intrinsics.areEqual(statement.getPartyType(), "CUSTOMER") ? "\u0639\u0645\u064a\u0644" : "\u0645\u0648\u0631\u062f").length(), f, 34.0f, true, paint3);
            int i7 = i3 * 29;
            int i8 = i6;
            for (Object obj : arrayList2.subList(i7, Math.min(arrayList2.size(), i7 + 29))) {
                int i9 = i8 + 1;
                if (i8 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                String str = (String) obj;
                canvas.drawTextRun((CharSequence) str, 0, str.length(), 0, str.length(), f, (i8 * 17.0f) + 65.0f, true, paint);
                i8 = i9;
            }
            int length = ("\u0635\u0641\u062d\u0629 " + i4 + " \u0645\u0646 " + i5).length();
            canvas.drawTextRun((CharSequence) ("\u0635\u0641\u062d\u0629 " + i4 + " \u0645\u0646 " + i5), 0, length, 0, ("\u0635\u0641\u062d\u0629 " + i4 + " \u0645\u0646 " + i5).length(), f, 595 - 20.0f, true, paint);
            pdfDocument.finishPage(pageStartPage);
            iMax = i5;
            i3 = i4;
            i2 = i6;
            paint2 = paint3;
            i = 842;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        pdfDocument.writeTo(byteArrayOutputStream);
        pdfDocument.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
        return byteArray;
    }

    public final List<String> pdfLines(PartyStatement statement) {
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(statement, "statement");
        ArrayList arrayList = new ArrayList();
        arrayList.add("\u0627\u0644\u0627\u0633\u0645: " + statement.getPartyName());
        if (Intrinsics.areEqual(statement.getPartyType(), "CUSTOMER")) {
            str = "\u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0645\u0631\u062c\u0639\u064a: " + money(statement.getClosingBalanceUsdCents()) + " USD";
        } else {
            str = "\u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0645\u0633\u062a\u062d\u0642: " + syp(statement.getClosingBalanceSypMinor()) + " \u0644.\u0633";
        }
        arrayList.add(str);
        arrayList.add("\u0627\u0644\u062a\u0627\u0631\u064a\u062e | \u0627\u0644\u0646\u0648\u0639 | \u0627\u0644\u0645\u0631\u062c\u0639 | \u0627\u0644\u0628\u064a\u0627\u0646 | \u0645\u062f\u064a\u0646 | \u062f\u0627\u0626\u0646 | \u0627\u0644\u0631\u0635\u064a\u062f");
        for (PartyStatementEntry partyStatementEntry : statement.getEntries()) {
            String str3 = Intrinsics.areEqual(statement.getPartyType(), "CUSTOMER") ? INSTANCE.money(partyStatementEntry.getDebitUsdCents()) + " USD" : INSTANCE.syp(partyStatementEntry.getDebitSypMinor()) + " \u0644.\u0633";
            String str4 = Intrinsics.areEqual(statement.getPartyType(), "CUSTOMER") ? INSTANCE.money(partyStatementEntry.getCreditUsdCents()) + " USD" : INSTANCE.syp(partyStatementEntry.getCreditSypMinor()) + " \u0644.\u0633";
            String str5 = Intrinsics.areEqual(statement.getPartyType(), "CUSTOMER") ? INSTANCE.money(partyStatementEntry.getBalanceUsdCents()) + " USD" : INSTANCE.syp(partyStatementEntry.getBalanceSypMinor()) + " \u0644.\u0633";
            if (partyStatementEntry.getCashRefundUsdCents() > 0) {
                str2 = " \u2022 \u0631\u062f \u0646\u0642\u062f\u064a " + INSTANCE.money(partyStatementEntry.getCashRefundUsdCents()) + " USD";
            } else if (partyStatementEntry.getCashRefundSypMinor() > 0) {
                str2 = " \u2022 \u0627\u0633\u062a\u0631\u062f\u0627\u062f \u0646\u0642\u062f\u064a " + INSTANCE.syp(partyStatementEntry.getCashRefundSypMinor()) + " \u0644.\u0633";
            } else {
                str2 = "";
            }
            arrayList.add(partyStatementEntry.getDate() + " | " + partyStatementEntry.getType() + " | " + partyStatementEntry.getReference() + " | " + partyStatementEntry.getDescription() + str2 + " | " + str3 + " | " + str4 + " | " + str5);
        }
        if (statement.getUnpricedEntryCount() > 0) {
            arrayList.add("\u062a\u0646\u0628\u064a\u0647: " + statement.getUnpricedEntryCount() + " \u062d\u0631\u0643\u0627\u062a \u0628\u0644\u0627 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0645\u0648\u062b\u0648\u0642 \u0644\u0645 \u062a\u062f\u062e\u0644 \u0641\u064a \u0627\u0644\u0631\u0635\u064a\u062f.");
        }
        return arrayList;
    }

    private final List<String> wrapPdfLine(String value, float maxWidth, Paint paint) {
        if (paint.measureText(value) <= maxWidth) {
            return CollectionsKt.listOf(value);
        }
        ArrayList arrayList = new ArrayList();
        String str = "";
        for (String str2 : new Regex("\\s+").split(value, 0)) {
            String str3 = str;
            String str4 = str3.length() == 0 ? str2 : ((Object) str) + " " + str2;
            if (paint.measureText(str4) <= maxWidth) {
                str = str4;
            } else {
                if (str3.length() > 0) {
                    arrayList.add(str);
                }
                if (paint.measureText(str2) > maxWidth) {
                    String str5 = str2;
                    String strValueOf = "";
                    for (int i = 0; i < str5.length(); i++) {
                        char cCharAt = str5.charAt(i);
                        StringBuilder sb = new StringBuilder();
                        sb.append((Object) strValueOf);
                        sb.append(cCharAt);
                        String string = sb.toString();
                        if (strValueOf.length() <= 0 || paint.measureText(string) <= maxWidth) {
                            strValueOf = string;
                        } else {
                            arrayList.add(strValueOf);
                            strValueOf = String.valueOf(cCharAt);
                        }
                    }
                    str = strValueOf;
                } else {
                    str = str2;
                }
            }
        }
        if (str.length() > 0) {
            arrayList.add(str);
        }
        ArrayList arrayListListOf = arrayList;
        if (arrayListListOf.isEmpty()) {
            arrayListListOf = CollectionsKt.listOf("");
        }
        return arrayListListOf;
    }

    private final String xlsxRow(int number, List<? extends Object> values) {
        List<? extends Object> list = values;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            PartyStatementExport partyStatementExport = INSTANCE;
            String str = partyStatementExport.column(i) + number;
            arrayList.add(obj instanceof Number ? "<c r=\"" + str + "\"><v>" + obj + "</v></c>" : "<c r=\"" + str + "\" t=\"inlineStr\"><is><t xml:space=\"preserve\">" + partyStatementExport.xmlEscape(obj.toString()) + "</t></is></c>");
            i = i2;
        }
        return "<row r=\"" + number + "\">" + CollectionsKt.joinToString$default(arrayList, "", null, null, 0, null, null, 62, null) + "</row>";
    }

    private final String column(int index) {
        int i = index + 1;
        String str = "";
        while (i > 0) {
            int i2 = i - 1;
            str = ((char) ((i2 % 26) + 65)) + str;
            i = i2 / 26;
        }
        return str;
    }

    private final void zipEntry(ZipOutputStream zip, String name, String value) throws IOException {
        zip.putNextEntry(new ZipEntry(name));
        Charset UTF_8 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
        byte[] bytes = value.getBytes(UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        zip.write(bytes);
        zip.closeEntry();
    }

    private final String csvCell(String value) {
        String str = value;
        boolean zMatches = new Regex("-?\\d+(\\.\\d+)?").matches(StringsKt.trim((CharSequence) str).toString());
        if ((StringsKt.startsWith$default(StringsKt.trimStart((CharSequence) str).toString(), "=", false, 2, (Object) null) || StringsKt.startsWith$default(StringsKt.trimStart((CharSequence) str).toString(), "+", false, 2, (Object) null) || StringsKt.startsWith$default(StringsKt.trimStart((CharSequence) str).toString(), "-", false, 2, (Object) null) || StringsKt.startsWith$default(StringsKt.trimStart((CharSequence) str).toString(), "@", false, 2, (Object) null)) && !zMatches) {
            value = "'" + value;
        }
        return "\"" + StringsKt.replace$default(value, "\"", "\"\"", false, 4, (Object) null) + "\"";
    }

    private final String xmlEscape(String value) {
        return StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(value, "&", "&amp;", false, 4, (Object) null), "<", "&lt;", false, 4, (Object) null), ">", "&gt;", false, 4, (Object) null), "\"", "&quot;", false, 4, (Object) null), "'", "&apos;", false, 4, (Object) null);
    }

    private final String money(long value) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(Locale.US, "%.2f", Arrays.copyOf(new Object[]{Double.valueOf(value / 100.0d)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    private final String syp(long value) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(Locale.US, "%,d", Arrays.copyOf(new Object[]{Long.valueOf(value)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}
