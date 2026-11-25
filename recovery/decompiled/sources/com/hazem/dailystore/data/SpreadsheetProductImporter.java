package com.hazem.dailystore.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/* JADX INFO: compiled from: SpreadsheetProductImporter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0002J\u001c\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00050\u00052\u0006\u0010\r\u001a\u00020\bH\u0002J\u001c\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00050\u00052\u0006\u0010\t\u001a\u00020\nH\u0002J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u001d\u0010\u0013\u001a\n \u0015*\u0004\u0018\u00010\u00140\u00142\u0006\u0010\u0016\u001a\u00020\bH\u0002\u00a2\u0006\u0002\u0010\u0017J\"\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0012\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00050\u0005H\u0002J\u0010\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\bH\u0002J\u0010\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\bH\u0002J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0018\u0010!\u001a\u00020\"2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0018\u0010#\u001a\u00020\"2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020 H\u0002\u00a8\u0006$"}, d2 = {"Lcom/hazem/dailystore/data/SpreadsheetProductImporter;", "", "<init>", "()V", "parse", "", "Lcom/hazem/dailystore/data/ProductImportRow;", "fileName", "", "input", "Ljava/io/InputStream;", "detectDelimiter", "", "source", "parseCsv", "parseXlsx", "parseSharedStrings", "bytes", "", "parseXml", "Lorg/w3c/dom/Document;", "kotlin.jvm.PlatformType", "xml", "(Ljava/lang/String;)Lorg/w3c/dom/Document;", "mapRows", "table", "normalizeHeader", "value", "normalizeDigits", "decimal", "Ljava/math/BigDecimal;", "row", "", "money", "", "usdCents", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SpreadsheetProductImporter {
    public static final SpreadsheetProductImporter INSTANCE = new SpreadsheetProductImporter();

    private SpreadsheetProductImporter() {
    }

    public final List<ProductImportRow> parse(String fileName, InputStream input) throws IOException {
        List<List<String>> csv;
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(input, "input");
        Locale ROOT = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
        String lowerCase = fileName.toLowerCase(ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        if (StringsKt.endsWith$default(lowerCase, ".xlsx", false, 2, (Object) null)) {
            csv = parseXlsx(input);
        } else {
            Locale ROOT2 = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue(ROOT2, "ROOT");
            String lowerCase2 = fileName.toLowerCase(ROOT2);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
            if (!StringsKt.endsWith$default(lowerCase2, ".csv", false, 2, (Object) null)) {
                throw new IllegalArgumentException("\u0627\u0644\u0635\u064a\u063a\u0629 \u0627\u0644\u0645\u062f\u0639\u0648\u0645\u0629 \u0647\u064a CSV \u0623\u0648 Excel \u0628\u0635\u064a\u063a\u0629 XLSX".toString());
            }
            csv = parseCsv(StringsKt.removePrefix(new String(ByteStreamsKt.readBytes(input), Charsets.UTF_8), (CharSequence) "\ufeff"));
        }
        if (csv.size() < 2) {
            throw new IllegalArgumentException("\u0627\u0644\u0645\u0644\u0641 \u0644\u0627 \u064a\u062d\u062a\u0648\u064a \u0639\u0644\u0649 \u0635\u0641\u0648\u0641 \u0623\u0635\u0646\u0627\u0641".toString());
        }
        return mapRows(csv);
    }

    private final char detectDelimiter(String source) {
        Object next;
        String next2;
        int i;
        List listListOf = CollectionsKt.listOf((Object[]) new Character[]{',', ';', '\t'});
        Iterator<String> it = StringsKt.lineSequence(source).iterator();
        do {
            next = null;
            if (!it.hasNext()) {
                next2 = null;
                break;
            }
            next2 = it.next();
        } while (StringsKt.isBlank(next2));
        String str = next2;
        if (str == null) {
            str = "";
        }
        List list = listListOf;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list, 10)), 16));
        for (Object obj : list) {
            ((Character) obj).charValue();
            linkedHashMap.put(obj, 0);
        }
        Map mutableMap = MapsKt.toMutableMap(linkedHashMap);
        int i2 = 0;
        boolean z = false;
        while (i2 < str.length()) {
            char cCharAt = str.charAt(i2);
            if (cCharAt == '\"' && z && (i = i2 + 1) < str.length() && str.charAt(i) == '\"') {
                i2 = i;
            } else if (cCharAt == '\"') {
                z = !z;
            } else if (!z && listListOf.contains(Character.valueOf(cCharAt))) {
                Character chValueOf = Character.valueOf(cCharAt);
                Integer num = (Integer) mutableMap.get(Character.valueOf(cCharAt));
                mutableMap.put(chValueOf, Integer.valueOf((num != null ? num.intValue() : 0) + 1));
            }
            i2++;
        }
        Iterator it2 = list.iterator();
        if (it2.hasNext()) {
            next = it2.next();
            if (it2.hasNext()) {
                Character ch = (Character) next;
                ch.charValue();
                Integer num2 = (Integer) mutableMap.get(ch);
                int iIntValue = num2 != null ? num2.intValue() : 0;
                do {
                    Object next3 = it2.next();
                    Character ch2 = (Character) next3;
                    ch2.charValue();
                    Integer num3 = (Integer) mutableMap.get(ch2);
                    int iIntValue2 = num3 != null ? num3.intValue() : 0;
                    if (iIntValue < iIntValue2) {
                        next = next3;
                        iIntValue = iIntValue2;
                    }
                } while (it2.hasNext());
            }
        }
        Character ch3 = (Character) next;
        if (ch3 != null) {
            return ch3.charValue();
        }
        return ',';
    }

    private final List<List<String>> parseCsv(String source) {
        int i;
        int i2;
        char cDetectDelimiter = detectDelimiter(source);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int i3 = 0;
        boolean z = false;
        while (i3 < source.length()) {
            char cCharAt = source.charAt(i3);
            if (cCharAt == '\"' && z && (i2 = i3 + 1) < source.length() && source.charAt(i2) == '\"') {
                sb.append(Typography.quote);
                i3 = i2;
            } else if (cCharAt == '\"') {
                z = !z;
            } else if (cCharAt == cDetectDelimiter && !z) {
                String string = sb.toString();
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                arrayList2.add(string);
                sb.setLength(0);
            } else if ((cCharAt == '\n' || cCharAt == '\r') && !z) {
                if (cCharAt == '\r' && (i = i3 + 1) < source.length() && source.charAt(i) == '\n') {
                    i3 = i;
                }
                String string2 = sb.toString();
                Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                arrayList2.add(string2);
                sb.setLength(0);
                ArrayList arrayList3 = arrayList2;
                if (!(arrayList3 instanceof Collection) || !arrayList3.isEmpty()) {
                    Iterator it = arrayList3.iterator();
                    while (it.hasNext()) {
                        if (!StringsKt.isBlank((String) it.next())) {
                            arrayList.add(arrayList2);
                            break;
                        }
                    }
                }
                arrayList2 = new ArrayList();
            } else {
                sb.append(cCharAt);
            }
            i3++;
        }
        if (z) {
            throw new IllegalArgumentException("\u064a\u0648\u062c\u062f \u0627\u0642\u062a\u0628\u0627\u0633 \u063a\u064a\u0631 \u0645\u063a\u0644\u0642 \u0641\u064a \u0645\u0644\u0641 CSV".toString());
        }
        String string3 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
        arrayList2.add(string3);
        ArrayList arrayList4 = arrayList2;
        if (!(arrayList4 instanceof Collection) || !arrayList4.isEmpty()) {
            Iterator it2 = arrayList4.iterator();
            while (it2.hasNext()) {
                if (!StringsKt.isBlank((String) it2.next())) {
                    arrayList.add(arrayList2);
                    break;
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:53:0x014d A[PHI: r2
      0x014d: PHI (r2v26 java.lang.String) = (r2v17 java.lang.String), (r2v22 java.lang.String), (r2v30 java.lang.String) binds: [B:66:0x017c, B:60:0x0168, B:51:0x014a] A[DONT_GENERATE, DONT_INLINE]] */
    private final List<List<String>> parseXlsx(InputStream input) throws IOException {
        int i;
        int i2;
        String textContent;
        String textContent2;
        Integer intOrNull;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ZipInputStream zipInputStream = new ZipInputStream(input);
        try {
            ZipInputStream zipInputStream2 = zipInputStream;
            for (ZipEntry nextEntry = zipInputStream2.getNextEntry(); nextEntry != null; nextEntry = zipInputStream2.getNextEntry()) {
                if (!nextEntry.isDirectory() && (Intrinsics.areEqual(nextEntry.getName(), "xl/sharedStrings.xml") || Intrinsics.areEqual(nextEntry.getName(), "xl/worksheets/sheet1.xml"))) {
                    linkedHashMap.put(nextEntry.getName(), ByteStreamsKt.readBytes(zipInputStream2));
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(zipInputStream, null);
            byte[] bArr = (byte[]) linkedHashMap.get("xl/worksheets/sheet1.xml");
            if (bArr == null) {
                throw new IllegalStateException("\u0644\u0645 \u0623\u062c\u062f \u0648\u0631\u0642\u0629 \u0627\u0644\u0639\u0645\u0644 \u0627\u0644\u0623\u0648\u0644\u0649 \u062f\u0627\u062e\u0644 \u0645\u0644\u0641 Excel".toString());
            }
            byte[] bArr2 = (byte[]) linkedHashMap.get("xl/sharedStrings.xml");
            List<String> sharedStrings = bArr2 != null ? parseSharedStrings(bArr2) : null;
            if (sharedStrings == null) {
                sharedStrings = CollectionsKt.emptyList();
            }
            NodeList elementsByTagName = parseXml(new String(bArr, Charsets.UTF_8)).getElementsByTagName("row");
            ArrayList arrayList = new ArrayList();
            int length = elementsByTagName.getLength();
            int i3 = 0;
            int i4 = 0;
            while (i4 < length) {
                Node nodeItem = elementsByTagName.item(i4);
                Intrinsics.checkNotNull(nodeItem, "null cannot be cast to non-null type org.w3c.dom.Element");
                NodeList elementsByTagName2 = ((Element) nodeItem).getElementsByTagName("c");
                SortedMap sortedMapSortedMapOf = MapsKt.sortedMapOf(new Pair[i3]);
                int length2 = elementsByTagName2.getLength();
                int i5 = i3;
                while (true) {
                    String str = "";
                    if (i5 >= length2) {
                        break;
                    }
                    Node nodeItem2 = elementsByTagName2.item(i5);
                    Intrinsics.checkNotNull(nodeItem2, "null cannot be cast to non-null type org.w3c.dom.Element");
                    Element element = (Element) nodeItem2;
                    String attribute = element.getAttribute("r");
                    Intrinsics.checkNotNull(attribute);
                    int length3 = attribute.length();
                    while (true) {
                        if (i3 >= length3) {
                            i2 = 0;
                            break;
                        }
                        if (!Character.isLetter(attribute.charAt(i3))) {
                            i2 = 0;
                            attribute = attribute.substring(0, i3);
                            Intrinsics.checkNotNullExpressionValue(attribute, "substring(...)");
                            break;
                        }
                        i3++;
                    }
                    String str2 = attribute;
                    Integer numValueOf = Integer.valueOf(i2);
                    for (int i6 = 0; i6 < str2.length(); i6++) {
                        numValueOf = Integer.valueOf((numValueOf.intValue() * 26) + (Character.toUpperCase(str2.charAt(i6)) - '@'));
                    }
                    int iIntValue = numValueOf.intValue() - 1;
                    if (iIntValue >= 0) {
                        String attribute2 = element.getAttribute("t");
                        if (Intrinsics.areEqual(attribute2, "s")) {
                            Node nodeItem3 = element.getElementsByTagName("v").item(0);
                            textContent = (nodeItem3 == null || (textContent2 = nodeItem3.getTextContent()) == null || (intOrNull = StringsKt.toIntOrNull(textContent2)) == null) ? null : (String) CollectionsKt.getOrNull(sharedStrings, intOrNull.intValue());
                            if (textContent != null) {
                                str = textContent;
                            }
                        } else if (Intrinsics.areEqual(attribute2, "inlineStr")) {
                            Node nodeItem4 = element.getElementsByTagName("t").item(0);
                            textContent = nodeItem4 != null ? nodeItem4.getTextContent() : null;
                            if (textContent != null) {
                                str = textContent;
                            }
                        } else {
                            Node nodeItem5 = element.getElementsByTagName("v").item(0);
                            textContent = nodeItem5 != null ? nodeItem5.getTextContent() : null;
                            if (textContent != null) {
                                str = textContent;
                            }
                        }
                        sortedMapSortedMapOf.put(Integer.valueOf(iIntValue), str);
                    }
                    i5++;
                    elementsByTagName = elementsByTagName;
                    i3 = 0;
                }
                NodeList nodeList = elementsByTagName;
                if (sortedMapSortedMapOf.isEmpty()) {
                    i = 0;
                } else {
                    Integer num = (Integer) sortedMapSortedMapOf.lastKey();
                    Intrinsics.checkNotNull(num);
                    i = 0;
                    IntRange intRange = new IntRange(0, num.intValue());
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
                    Iterator<Integer> it = intRange.iterator();
                    while (it.hasNext()) {
                        String str3 = (String) sortedMapSortedMapOf.get(Integer.valueOf(((IntIterator) it).nextInt()));
                        if (str3 == null) {
                            str3 = "";
                        }
                        arrayList2.add(str3);
                    }
                    arrayList.add(arrayList2);
                }
                i4++;
                i3 = i;
                elementsByTagName = nodeList;
            }
            return arrayList;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(zipInputStream, th);
                throw th2;
            }
        }
    }

    private final List<String> parseSharedStrings(byte[] bytes) {
        NodeList elementsByTagName = parseXml(new String(bytes, Charsets.UTF_8)).getElementsByTagName("si");
        IntRange intRangeUntil = RangesKt.until(0, elementsByTagName.getLength());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
        Iterator<Integer> it = intRangeUntil.iterator();
        while (it.hasNext()) {
            Node nodeItem = elementsByTagName.item(((IntIterator) it).nextInt());
            Intrinsics.checkNotNull(nodeItem, "null cannot be cast to non-null type org.w3c.dom.Element");
            final NodeList elementsByTagName2 = ((Element) nodeItem).getElementsByTagName("t");
            arrayList.add(CollectionsKt.joinToString$default(RangesKt.until(0, elementsByTagName2.getLength()), "", null, null, 0, null, new Function1() { // from class: com.hazem.dailystore.data.SpreadsheetProductImporter$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SpreadsheetProductImporter.parseSharedStrings$lambda$14$lambda$13(elementsByTagName2, ((Integer) obj).intValue());
                }
            }, 30, null));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence parseSharedStrings$lambda$14$lambda$13(NodeList nodeList, int i) {
        String textContent = nodeList.item(i).getTextContent();
        if (textContent == null) {
            textContent = "";
        }
        return textContent;
    }

    private final Document parseXml(String xml) throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
        documentBuilderFactoryNewInstance.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        documentBuilderFactoryNewInstance.setFeature("http://xml.org/sax/features/external-general-entities", false);
        documentBuilderFactoryNewInstance.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        documentBuilderFactoryNewInstance.setXIncludeAware(false);
        documentBuilderFactoryNewInstance.setExpandEntityReferences(false);
        return documentBuilderFactoryNewInstance.newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final List<ProductImportRow> mapRows(List<? extends List<String>> table) {
        boolean z;
        boolean z2;
        Iterable iterable = (Iterable) CollectionsKt.first((List) table);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(normalizeHeader((String) it.next()));
        }
        ArrayList arrayList2 = arrayList;
        int iMapRows$column = mapRows$column(arrayList2, "\u0627\u0633\u0645 \u0627\u0644\u0645\u0646\u062a\u062c", "\u0627\u0633\u0645 \u0627\u0644\u0635\u0646\u0641", "\u0627\u0633\u0645 \u0627\u0644\u0645\u0627\u062f\u0629", "\u0627\u0644\u0627\u0633\u0645", "product name", "name");
        if (iMapRows$column < 0) {
            throw new IllegalArgumentException("\u0644\u0645 \u0623\u062c\u062f \u0639\u0645\u0648\u062f \u0627\u0633\u0645 \u0627\u0644\u0645\u0646\u062a\u062c \u0641\u064a \u0627\u0644\u0635\u0641 \u0627\u0644\u0623\u0648\u0644".toString());
        }
        int iMapRows$column2 = mapRows$column(arrayList2, "\u0627\u0644\u062a\u0635\u0646\u064a\u0641", "\u0627\u0633\u0645 \u0627\u0644\u062a\u0635\u0646\u064a\u0641", "\u0627\u0644\u0641\u0626\u0629", "category");
        int iMapRows$column3 = mapRows$column(arrayList2, "\u0648\u062d\u062f\u0629 \u0627\u0644\u0642\u064a\u0627\u0633", "\u0627\u0644\u0648\u062d\u062f\u0629", "\u0648\u062d\u062f\u0629", "unit");
        int iMapRows$column4 = mapRows$column(arrayList2, "\u062a\u0643\u0644\u0641\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 \u0644 \u0633", "\u062a\u0643\u0644\u0641\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629", "purchase cost syp", "cost syp");
        int iMapRows$column5 = mapRows$column(arrayList2, "\u0633\u0639\u0631 \u0627\u0644\u0628\u064a\u0639 \u0644 \u0633", "\u0633\u0639\u0631 \u0627\u0644\u0645\u0628\u064a\u0639 \u0644 \u0633", "\u0633\u0639\u0631 \u0627\u0644\u0628\u064a\u0639 \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629", "\u0633\u0639\u0631 \u0627\u0644\u0645\u0628\u064a\u0639 \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629", "sale price syp", "selling price syp");
        int iMapRows$column6 = mapRows$column(arrayList2, "\u062a\u0643\u0644\u0641\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 \u0628\u0627\u0644\u062f\u0648\u0644\u0627\u0631", "purchase cost usd", "cost usd");
        int iMapRows$column7 = mapRows$column(arrayList2, "\u0633\u0639\u0631 \u0627\u0644\u0628\u064a\u0639 \u0628\u0627\u0644\u062f\u0648\u0644\u0627\u0631", "\u0633\u0639\u0631 \u0627\u0644\u0645\u0628\u064a\u0639 \u0628\u0627\u0644\u062f\u0648\u0644\u0627\u0631", "sale price usd", "selling price usd");
        int iMapRows$column8 = mapRows$column(arrayList2, "sku", "\u0631\u0645\u0632 sku");
        int iMapRows$column9 = mapRows$column(arrayList2, "\u0627\u0644\u0628\u0627\u0631\u0643\u0648\u062f", "barcode", "\u0628\u0627\u0631\u0643\u0648\u062f");
        int iMapRows$column10 = mapRows$column(arrayList2, "\u062a\u062a\u0628\u0639 \u0627\u0644\u0645\u062e\u0632\u0648\u0646", "track inventory", "track_inventory");
        if (iMapRows$column5 < 0 && iMapRows$column7 < 0) {
            throw new IllegalArgumentException("\u0644\u0645 \u0623\u062c\u062f \u0639\u0645\u0648\u062f \u0633\u0639\u0631 \u0627\u0644\u0628\u064a\u0639 \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0623\u0648 \u0627\u0644\u062f\u0648\u0644\u0627\u0631".toString());
        }
        ArrayList arrayList3 = new ArrayList();
        int size = table.size();
        int i = 1;
        while (i < size) {
            List<String> list = table.get(i);
            String strMapRows$value = mapRows$value(list, iMapRows$column);
            if (strMapRows$value == null) {
                iMapRows$column10 = iMapRows$column10;
            } else {
                int i2 = i + 1;
                String strMapRows$value2 = mapRows$value(list, iMapRows$column2);
                String strMapRows$value3 = mapRows$value(list, iMapRows$column3);
                String strMapRows$value4 = mapRows$value(list, iMapRows$column4);
                Long lValueOf = strMapRows$value4 != null ? Long.valueOf(INSTANCE.money(strMapRows$value4, i2)) : null;
                String strMapRows$value5 = mapRows$value(list, iMapRows$column5);
                Long lValueOf2 = strMapRows$value5 != null ? Long.valueOf(INSTANCE.money(strMapRows$value5, i2)) : null;
                String strMapRows$value6 = mapRows$value(list, iMapRows$column6);
                Long lValueOf3 = strMapRows$value6 != null ? Long.valueOf(INSTANCE.usdCents(strMapRows$value6, i2)) : null;
                String strMapRows$value7 = mapRows$value(list, iMapRows$column7);
                Long lValueOf4 = strMapRows$value7 != null ? Long.valueOf(INSTANCE.usdCents(strMapRows$value7, i2)) : null;
                String strMapRows$value8 = mapRows$value(list, iMapRows$column8);
                String strMapRows$value9 = mapRows$value(list, iMapRows$column9);
                String strMapRows$value10 = mapRows$value(list, iMapRows$column10);
                if (strMapRows$value10 != null) {
                    String string = StringsKt.trim((CharSequence) strMapRows$value10).toString();
                    Locale ROOT = Locale.ROOT;
                    Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
                    String lowerCase = string.toLowerCase(ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                    switch (lowerCase.hashCode()) {
                        case 48:
                            if (!lowerCase.equals("0")) {
                                throw new IllegalStateException(("\u0642\u064a\u0645\u0629 \u062a\u062a\u0628\u0639 \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u063a\u064a\u0631 \u0645\u0641\u0647\u0648\u0645\u0629 \u0641\u064a \u0627\u0644\u0635\u0641 " + i2 + ": " + strMapRows$value10).toString());
                            }
                            z2 = false;
                            z = z2;
                            break;
                        case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                            if (!lowerCase.equals("1")) {
                                throw new IllegalStateException(("\u0642\u064a\u0645\u0629 \u062a\u062a\u0628\u0639 \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u063a\u064a\u0631 \u0645\u0641\u0647\u0648\u0645\u0629 \u0641\u064a \u0627\u0644\u0635\u0641 " + i2 + ": " + strMapRows$value10).toString());
                            }
                            z2 = true;
                            z = z2;
                            break;
                        case 3521:
                            if (!lowerCase.equals("no")) {
                                throw new IllegalStateException(("\u0642\u064a\u0645\u0629 \u062a\u062a\u0628\u0639 \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u063a\u064a\u0631 \u0645\u0641\u0647\u0648\u0645\u0629 \u0641\u064a \u0627\u0644\u0635\u0641 " + i2 + ": " + strMapRows$value10).toString());
                            }
                            z2 = false;
                            z = z2;
                            break;
                        case 51299:
                            if (!lowerCase.equals("\u0644\u0627")) {
                                throw new IllegalStateException(("\u0642\u064a\u0645\u0629 \u062a\u062a\u0628\u0639 \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u063a\u064a\u0631 \u0645\u0641\u0647\u0648\u0645\u0629 \u0641\u064a \u0627\u0644\u0635\u0641 " + i2 + ": " + strMapRows$value10).toString());
                            }
                            z2 = false;
                            z = z2;
                            break;
                        case 119527:
                            if (!lowerCase.equals("yes")) {
                                throw new IllegalStateException(("\u0642\u064a\u0645\u0629 \u062a\u062a\u0628\u0639 \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u063a\u064a\u0631 \u0645\u0641\u0647\u0648\u0645\u0629 \u0641\u064a \u0627\u0644\u0635\u0641 " + i2 + ": " + strMapRows$value10).toString());
                            }
                            z2 = true;
                            z = z2;
                            break;
                        case 1594354:
                            if (!lowerCase.equals("\u0646\u0639\u0645")) {
                                throw new IllegalStateException(("\u0642\u064a\u0645\u0629 \u062a\u062a\u0628\u0639 \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u063a\u064a\u0631 \u0645\u0641\u0647\u0648\u0645\u0629 \u0641\u064a \u0627\u0644\u0635\u0641 " + i2 + ": " + strMapRows$value10).toString());
                            }
                            z2 = true;
                            z = z2;
                            break;
                        case 3569038:
                            if (!lowerCase.equals("true")) {
                                throw new IllegalStateException(("\u0642\u064a\u0645\u0629 \u062a\u062a\u0628\u0639 \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u063a\u064a\u0631 \u0645\u0641\u0647\u0648\u0645\u0629 \u0641\u064a \u0627\u0644\u0635\u0641 " + i2 + ": " + strMapRows$value10).toString());
                            }
                            z2 = true;
                            z = z2;
                            break;
                        case 97196323:
                            if (!lowerCase.equals("false")) {
                                throw new IllegalStateException(("\u0642\u064a\u0645\u0629 \u062a\u062a\u0628\u0639 \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u063a\u064a\u0631 \u0645\u0641\u0647\u0648\u0645\u0629 \u0641\u064a \u0627\u0644\u0635\u0641 " + i2 + ": " + strMapRows$value10).toString());
                            }
                            z2 = false;
                            z = z2;
                            break;
                        default:
                            throw new IllegalStateException(("\u0642\u064a\u0645\u0629 \u062a\u062a\u0628\u0639 \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u063a\u064a\u0631 \u0645\u0641\u0647\u0648\u0645\u0629 \u0641\u064a \u0627\u0644\u0635\u0641 " + i2 + ": " + strMapRows$value10).toString());
                    }
                } else {
                    z = true;
                }
                arrayList3.add(new ProductImportRow(i2, strMapRows$value, strMapRows$value2, strMapRows$value3, lValueOf, lValueOf2, lValueOf3, lValueOf4, strMapRows$value8, strMapRows$value9, z));
            }
            i++;
            iMapRows$column = iMapRows$column;
            iMapRows$column2 = iMapRows$column2;
            iMapRows$column10 = iMapRows$column10;
        }
        if (arrayList3.isEmpty()) {
            throw new IllegalArgumentException("\u0644\u0645 \u0623\u062c\u062f \u0623\u0635\u0646\u0627\u0641\u0627\u064b \u0635\u0627\u0644\u062d\u0629 \u0644\u0644\u0627\u0633\u062a\u064a\u0631\u0627\u062f".toString());
        }
        return arrayList3;
    }

    private static final String mapRows$value(List<String> list, int i) {
        String str;
        String string;
        if (i >= 0 && (str = (String) CollectionsKt.getOrNull(list, i)) != null && (string = StringsKt.trim((CharSequence) str).toString()) != null && string.length() > 0) {
            return string;
        }
        return null;
    }

    private final String normalizeHeader(String value) {
        String string = StringsKt.trim((CharSequence) value).toString();
        Locale ROOT = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
        String lowerCase = string.toLowerCase(ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return StringsKt.trim((CharSequence) new Regex("\\s+").replace(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(lowerCase, "(", " ", false, 4, (Object) null), ")", " ", false, 4, (Object) null), ".", " ", false, 4, (Object) null), "\u0640", "", false, 4, (Object) null), "\u0623", "\u0627", false, 4, (Object) null), "\u0625", "\u0627", false, 4, (Object) null), "\u0622", "\u0627", false, 4, (Object) null), "\u0649", "\u064a", false, 4, (Object) null), " ")).toString();
    }

    private final String normalizeDigits(String value) {
        int i;
        StringBuilder sb = new StringBuilder();
        String str = value;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            if ('\u0660' > cCharAt || cCharAt >= '\u066a') {
                if ('\u06f0' <= cCharAt && cCharAt < '\u06fa') {
                    i = cCharAt - '\u06c0';
                }
                sb.append(cCharAt);
            } else {
                i = cCharAt - '\u0630';
            }
            cCharAt = (char) i;
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    private final BigDecimal decimal(String value, int row) {
        try {
            return new BigDecimal(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(normalizeDigits(value), "\u066c", "", false, 4, (Object) null), "\u066b", ".", false, 4, (Object) null), " ", "", false, 4, (Object) null), ",", "", false, 4, (Object) null));
        } catch (Exception unused) {
            throw new IllegalStateException(("\u0642\u064a\u0645\u0629 \u0631\u0642\u0645\u064a\u0629 \u063a\u064a\u0631 \u0635\u062d\u064a\u062d\u0629 \u0641\u064a \u0627\u0644\u0635\u0641 " + row + ": " + value).toString());
        }
    }

    private final long money(String value, int row) {
        long jLongValueExact = decimal(value, row).setScale(0, RoundingMode.HALF_UP).longValueExact();
        if (jLongValueExact >= 0) {
            return jLongValueExact;
        }
        throw new IllegalArgumentException(("\u0627\u0644\u0633\u0639\u0631 \u0633\u0627\u0644\u0628 \u0641\u064a \u0627\u0644\u0635\u0641 " + row).toString());
    }

    private final long usdCents(String value, int row) {
        long jLongValueExact = decimal(value, row).multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP).longValueExact();
        if (jLongValueExact >= 0) {
            return jLongValueExact;
        }
        throw new IllegalArgumentException(("\u0627\u0644\u0633\u0639\u0631 \u0633\u0627\u0644\u0628 \u0641\u064a \u0627\u0644\u0635\u0641 " + row).toString());
    }

    private static final int mapRows$column(List<String> list, String... strArr) {
        int i = 0;
        for (String str : list) {
            SpreadsheetProductImporter spreadsheetProductImporter = INSTANCE;
            ArrayList arrayList = new ArrayList(strArr.length);
            for (String str2 : strArr) {
                arrayList.add(spreadsheetProductImporter.normalizeHeader(str2));
            }
            if (arrayList.contains(str)) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
