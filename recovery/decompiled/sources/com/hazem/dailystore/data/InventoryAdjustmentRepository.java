package com.hazem.dailystore.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import androidx.core.app.NotificationCompat;
import com.hazem.dailystore.MainActivity$$ExternalSyntheticBackport0;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import org.json.JSONObject;

/* JADX INFO: compiled from: InventoryAdjustmentRepository.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005Ja\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0017\u001a\u00020\u0018\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001dJ$\u0010\u001e\u001a\u00020\u000b2\n\u0010\u001f\u001a\u00060 j\u0002`!2\u0006\u0010\"\u001a\u00020\r2\u0006\u0010#\u001a\u00020\u0018H\u0002J\u0010\u0010$\u001a\u00020\r2\u0006\u0010%\u001a\u00020\rH\u0002J\b\u0010&\u001a\u00020\rH\u0002R\u0018\u0010\u0002\u001a\n \u0006*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006'"}, d2 = {"Lcom/hazem/dailystore/data/InventoryAdjustmentRepository;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "database", "Lcom/hazem/dailystore/data/AppDatabase;", "postAdjustment", "Lcom/hazem/dailystore/data/InventoryAdjustmentResult;", "actorUserId", "", "productId", "quantityDelta", "Ljava/math/BigDecimal;", "reason", "operationId", "unitCostUsdCents", "", "warehouseId", "occurredAt", "syncOrigin", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/math/BigDecimal;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Z)Lcom/hazem/dailystore/data/InventoryAdjustmentResult;", "listRecent", "", "limit", "", "loadResult", "db", "Lnet/zetetic/database/sqlcipher/SQLiteDatabase;", "Lcom/hazem/dailystore/data/AppSqliteDatabase;", "id", "replayed", "hash", "value", "timestamp", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class InventoryAdjustmentRepository {
    private final Context context;
    private final AppDatabase database;

    public InventoryAdjustmentRepository(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context.getApplicationContext();
        this.database = AppDatabase.INSTANCE.getInstance(context);
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0226 A[Catch: all -> 0x0373, TRY_ENTER, TryCatch #15 {all -> 0x0373, blocks: (B:43:0x013b, B:60:0x0170, B:70:0x0199, B:72:0x01a6, B:80:0x01d5, B:102:0x022c, B:107:0x024d, B:101:0x0226, B:55:0x0158, B:59:0x016c), top: B:446:0x013b }] */
    /* JADX WARN: Code duplicated, block: B:104:0x0249 A[Catch: all -> 0x0152, TRY_ENTER, TRY_LEAVE, TryCatch #33 {all -> 0x0152, blocks: (B:45:0x0142, B:83:0x01dc, B:85:0x01e4, B:90:0x01fc, B:93:0x0205, B:95:0x020d, B:104:0x0249), top: B:479:0x0142 }] */
    /* JADX WARN: Code duplicated, block: B:106:0x024c  */
    /* JADX WARN: Code duplicated, block: B:113:0x02a9 A[Catch: all -> 0x0371, TRY_ENTER, TRY_LEAVE, TryCatch #14 {all -> 0x0371, blocks: (B:113:0x02a9, B:122:0x02fc, B:138:0x032f, B:139:0x0332, B:140:0x0333, B:141:0x0343, B:147:0x034d, B:148:0x0350, B:160:0x036d, B:161:0x0370, B:157:0x036a, B:135:0x032c, B:144:0x034a), top: B:445:0x0140, inners: #9, #12, #29 }] */
    /* JADX WARN: Code duplicated, block: B:117:0x02db  */
    /* JADX WARN: Code duplicated, block: B:119:0x02de  */
    /* JADX WARN: Code duplicated, block: B:140:0x0333 A[Catch: all -> 0x0371, TryCatch #14 {all -> 0x0371, blocks: (B:113:0x02a9, B:122:0x02fc, B:138:0x032f, B:139:0x0332, B:140:0x0333, B:141:0x0343, B:147:0x034d, B:148:0x0350, B:160:0x036d, B:161:0x0370, B:157:0x036a, B:135:0x032c, B:144:0x034a), top: B:445:0x0140, inners: #9, #12, #29 }] */
    /* JADX WARN: Code duplicated, block: B:149:0x0351  */
    /* JADX WARN: Code duplicated, block: B:226:0x048f  */
    /* JADX WARN: Code duplicated, block: B:230:0x049f A[Catch: all -> 0x0997, TRY_LEAVE, TryCatch #38 {all -> 0x0997, blocks: (B:228:0x0496, B:230:0x049f), top: B:488:0x0496 }] */
    /* JADX WARN: Code duplicated, block: B:237:0x04c1 A[Catch: all -> 0x09ed, TRY_LEAVE, TryCatch #11 {all -> 0x09ed, blocks: (B:170:0x0394, B:182:0x03d5, B:207:0x0442, B:216:0x046b, B:232:0x04a8, B:235:0x04bc, B:237:0x04c1, B:247:0x04e7, B:249:0x04f4, B:267:0x0574, B:227:0x0490, B:231:0x04a4), top: B:439:0x0394 }] */
    /* JADX WARN: Code duplicated, block: B:240:0x04d4  */
    /* JADX WARN: Code duplicated, block: B:245:0x04e3 A[Catch: all -> 0x0966, TRY_ENTER, TRY_LEAVE, TryCatch #4 {all -> 0x0966, blocks: (B:238:0x04cb, B:245:0x04e3), top: B:425:0x04cb }] */
    /* JADX WARN: Code duplicated, block: B:249:0x04f4 A[Catch: all -> 0x09ed, TRY_LEAVE, TryCatch #11 {all -> 0x09ed, blocks: (B:170:0x0394, B:182:0x03d5, B:207:0x0442, B:216:0x046b, B:232:0x04a8, B:235:0x04bc, B:237:0x04c1, B:247:0x04e7, B:249:0x04f4, B:267:0x0574, B:227:0x0490, B:231:0x04a4), top: B:439:0x0394 }] */
    /* JADX WARN: Code duplicated, block: B:251:0x050c A[Catch: all -> 0x043b, TRY_ENTER, TryCatch #20 {all -> 0x043b, blocks: (B:185:0x03dc, B:187:0x03e6, B:189:0x03f2, B:191:0x03fa, B:193:0x0401, B:200:0x0422, B:201:0x042d, B:202:0x042e, B:203:0x043a, B:219:0x047f, B:251:0x050c, B:264:0x0527), top: B:456:0x03da }] */
    /* JADX WARN: Code duplicated, block: B:252:0x0511  */
    /* JADX WARN: Code duplicated, block: B:254:0x0515  */
    /* JADX WARN: Code duplicated, block: B:256:0x0518  */
    /* JADX WARN: Code duplicated, block: B:259:0x051e  */
    /* JADX WARN: Code duplicated, block: B:261:0x0521  */
    /* JADX WARN: Code duplicated, block: B:264:0x0527 A[Catch: all -> 0x043b, TRY_ENTER, TRY_LEAVE, TryCatch #20 {all -> 0x043b, blocks: (B:185:0x03dc, B:187:0x03e6, B:189:0x03f2, B:191:0x03fa, B:193:0x0401, B:200:0x0422, B:201:0x042d, B:202:0x042e, B:203:0x043a, B:219:0x047f, B:251:0x050c, B:264:0x0527), top: B:456:0x03da }] */
    /* JADX WARN: Code duplicated, block: B:266:0x0570  */
    /* JADX WARN: Code duplicated, block: B:288:0x0682  */
    /* JADX WARN: Code duplicated, block: B:293:0x06c9 A[Catch: all -> 0x09fe, TryCatch #22 {all -> 0x09fe, blocks: (B:291:0x06c3, B:293:0x06c9, B:297:0x06fb, B:299:0x0752, B:303:0x0769, B:305:0x0770, B:307:0x084c, B:309:0x08a5, B:311:0x08aa, B:313:0x08b2, B:315:0x08bb, B:317:0x08ed, B:304:0x076d, B:300:0x075a, B:296:0x06f9, B:324:0x0934, B:325:0x093f, B:330:0x0949, B:331:0x0965, B:337:0x096c, B:338:0x096f, B:339:0x0970, B:340:0x097c, B:346:0x0983, B:347:0x0986, B:359:0x099d, B:360:0x09a0, B:361:0x09a1, B:362:0x09ad, B:378:0x09df, B:379:0x09e2, B:385:0x09e9, B:386:0x09ec, B:396:0x09fa, B:397:0x09fd, B:382:0x09e6, B:334:0x0969, B:393:0x09f7, B:233:0x04b4, B:343:0x0980, B:356:0x099a, B:375:0x09dc), top: B:459:0x00f9, inners: #6, #8, #17, #21, #25, #41, #42 }] */
    /* JADX WARN: Code duplicated, block: B:294:0x06f3  */
    /* JADX WARN: Code duplicated, block: B:296:0x06f9 A[Catch: all -> 0x09fe, TryCatch #22 {all -> 0x09fe, blocks: (B:291:0x06c3, B:293:0x06c9, B:297:0x06fb, B:299:0x0752, B:303:0x0769, B:305:0x0770, B:307:0x084c, B:309:0x08a5, B:311:0x08aa, B:313:0x08b2, B:315:0x08bb, B:317:0x08ed, B:304:0x076d, B:300:0x075a, B:296:0x06f9, B:324:0x0934, B:325:0x093f, B:330:0x0949, B:331:0x0965, B:337:0x096c, B:338:0x096f, B:339:0x0970, B:340:0x097c, B:346:0x0983, B:347:0x0986, B:359:0x099d, B:360:0x09a0, B:361:0x09a1, B:362:0x09ad, B:378:0x09df, B:379:0x09e2, B:385:0x09e9, B:386:0x09ec, B:396:0x09fa, B:397:0x09fd, B:382:0x09e6, B:334:0x0969, B:393:0x09f7, B:233:0x04b4, B:343:0x0980, B:356:0x099a, B:375:0x09dc), top: B:459:0x00f9, inners: #6, #8, #17, #21, #25, #41, #42 }] */
    /* JADX WARN: Code duplicated, block: B:299:0x0752 A[Catch: all -> 0x09fe, TryCatch #22 {all -> 0x09fe, blocks: (B:291:0x06c3, B:293:0x06c9, B:297:0x06fb, B:299:0x0752, B:303:0x0769, B:305:0x0770, B:307:0x084c, B:309:0x08a5, B:311:0x08aa, B:313:0x08b2, B:315:0x08bb, B:317:0x08ed, B:304:0x076d, B:300:0x075a, B:296:0x06f9, B:324:0x0934, B:325:0x093f, B:330:0x0949, B:331:0x0965, B:337:0x096c, B:338:0x096f, B:339:0x0970, B:340:0x097c, B:346:0x0983, B:347:0x0986, B:359:0x099d, B:360:0x09a0, B:361:0x09a1, B:362:0x09ad, B:378:0x09df, B:379:0x09e2, B:385:0x09e9, B:386:0x09ec, B:396:0x09fa, B:397:0x09fd, B:382:0x09e6, B:334:0x0969, B:393:0x09f7, B:233:0x04b4, B:343:0x0980, B:356:0x099a, B:375:0x09dc), top: B:459:0x00f9, inners: #6, #8, #17, #21, #25, #41, #42 }] */
    /* JADX WARN: Code duplicated, block: B:300:0x075a A[Catch: all -> 0x09fe, TRY_LEAVE, TryCatch #22 {all -> 0x09fe, blocks: (B:291:0x06c3, B:293:0x06c9, B:297:0x06fb, B:299:0x0752, B:303:0x0769, B:305:0x0770, B:307:0x084c, B:309:0x08a5, B:311:0x08aa, B:313:0x08b2, B:315:0x08bb, B:317:0x08ed, B:304:0x076d, B:300:0x075a, B:296:0x06f9, B:324:0x0934, B:325:0x093f, B:330:0x0949, B:331:0x0965, B:337:0x096c, B:338:0x096f, B:339:0x0970, B:340:0x097c, B:346:0x0983, B:347:0x0986, B:359:0x099d, B:360:0x09a0, B:361:0x09a1, B:362:0x09ad, B:378:0x09df, B:379:0x09e2, B:385:0x09e9, B:386:0x09ec, B:396:0x09fa, B:397:0x09fd, B:382:0x09e6, B:334:0x0969, B:393:0x09f7, B:233:0x04b4, B:343:0x0980, B:356:0x099a, B:375:0x09dc), top: B:459:0x00f9, inners: #6, #8, #17, #21, #25, #41, #42 }] */
    /* JADX WARN: Code duplicated, block: B:303:0x0769 A[Catch: all -> 0x09fe, TRY_ENTER, TryCatch #22 {all -> 0x09fe, blocks: (B:291:0x06c3, B:293:0x06c9, B:297:0x06fb, B:299:0x0752, B:303:0x0769, B:305:0x0770, B:307:0x084c, B:309:0x08a5, B:311:0x08aa, B:313:0x08b2, B:315:0x08bb, B:317:0x08ed, B:304:0x076d, B:300:0x075a, B:296:0x06f9, B:324:0x0934, B:325:0x093f, B:330:0x0949, B:331:0x0965, B:337:0x096c, B:338:0x096f, B:339:0x0970, B:340:0x097c, B:346:0x0983, B:347:0x0986, B:359:0x099d, B:360:0x09a0, B:361:0x09a1, B:362:0x09ad, B:378:0x09df, B:379:0x09e2, B:385:0x09e9, B:386:0x09ec, B:396:0x09fa, B:397:0x09fd, B:382:0x09e6, B:334:0x0969, B:393:0x09f7, B:233:0x04b4, B:343:0x0980, B:356:0x099a, B:375:0x09dc), top: B:459:0x00f9, inners: #6, #8, #17, #21, #25, #41, #42 }] */
    /* JADX WARN: Code duplicated, block: B:304:0x076d A[Catch: all -> 0x09fe, TryCatch #22 {all -> 0x09fe, blocks: (B:291:0x06c3, B:293:0x06c9, B:297:0x06fb, B:299:0x0752, B:303:0x0769, B:305:0x0770, B:307:0x084c, B:309:0x08a5, B:311:0x08aa, B:313:0x08b2, B:315:0x08bb, B:317:0x08ed, B:304:0x076d, B:300:0x075a, B:296:0x06f9, B:324:0x0934, B:325:0x093f, B:330:0x0949, B:331:0x0965, B:337:0x096c, B:338:0x096f, B:339:0x0970, B:340:0x097c, B:346:0x0983, B:347:0x0986, B:359:0x099d, B:360:0x09a0, B:361:0x09a1, B:362:0x09ad, B:378:0x09df, B:379:0x09e2, B:385:0x09e9, B:386:0x09ec, B:396:0x09fa, B:397:0x09fd, B:382:0x09e6, B:334:0x0969, B:393:0x09f7, B:233:0x04b4, B:343:0x0980, B:356:0x099a, B:375:0x09dc), top: B:459:0x00f9, inners: #6, #8, #17, #21, #25, #41, #42 }] */
    /* JADX WARN: Code duplicated, block: B:307:0x084c A[Catch: all -> 0x09fe, TryCatch #22 {all -> 0x09fe, blocks: (B:291:0x06c3, B:293:0x06c9, B:297:0x06fb, B:299:0x0752, B:303:0x0769, B:305:0x0770, B:307:0x084c, B:309:0x08a5, B:311:0x08aa, B:313:0x08b2, B:315:0x08bb, B:317:0x08ed, B:304:0x076d, B:300:0x075a, B:296:0x06f9, B:324:0x0934, B:325:0x093f, B:330:0x0949, B:331:0x0965, B:337:0x096c, B:338:0x096f, B:339:0x0970, B:340:0x097c, B:346:0x0983, B:347:0x0986, B:359:0x099d, B:360:0x09a0, B:361:0x09a1, B:362:0x09ad, B:378:0x09df, B:379:0x09e2, B:385:0x09e9, B:386:0x09ec, B:396:0x09fa, B:397:0x09fd, B:382:0x09e6, B:334:0x0969, B:393:0x09f7, B:233:0x04b4, B:343:0x0980, B:356:0x099a, B:375:0x09dc), top: B:459:0x00f9, inners: #6, #8, #17, #21, #25, #41, #42 }] */
    /* JADX WARN: Code duplicated, block: B:309:0x08a5 A[Catch: all -> 0x09fe, TryCatch #22 {all -> 0x09fe, blocks: (B:291:0x06c3, B:293:0x06c9, B:297:0x06fb, B:299:0x0752, B:303:0x0769, B:305:0x0770, B:307:0x084c, B:309:0x08a5, B:311:0x08aa, B:313:0x08b2, B:315:0x08bb, B:317:0x08ed, B:304:0x076d, B:300:0x075a, B:296:0x06f9, B:324:0x0934, B:325:0x093f, B:330:0x0949, B:331:0x0965, B:337:0x096c, B:338:0x096f, B:339:0x0970, B:340:0x097c, B:346:0x0983, B:347:0x0986, B:359:0x099d, B:360:0x09a0, B:361:0x09a1, B:362:0x09ad, B:378:0x09df, B:379:0x09e2, B:385:0x09e9, B:386:0x09ec, B:396:0x09fa, B:397:0x09fd, B:382:0x09e6, B:334:0x0969, B:393:0x09f7, B:233:0x04b4, B:343:0x0980, B:356:0x099a, B:375:0x09dc), top: B:459:0x00f9, inners: #6, #8, #17, #21, #25, #41, #42 }] */
    /* JADX WARN: Code duplicated, block: B:310:0x08a8  */
    /* JADX WARN: Code duplicated, block: B:313:0x08b2 A[Catch: all -> 0x09fe, TryCatch #22 {all -> 0x09fe, blocks: (B:291:0x06c3, B:293:0x06c9, B:297:0x06fb, B:299:0x0752, B:303:0x0769, B:305:0x0770, B:307:0x084c, B:309:0x08a5, B:311:0x08aa, B:313:0x08b2, B:315:0x08bb, B:317:0x08ed, B:304:0x076d, B:300:0x075a, B:296:0x06f9, B:324:0x0934, B:325:0x093f, B:330:0x0949, B:331:0x0965, B:337:0x096c, B:338:0x096f, B:339:0x0970, B:340:0x097c, B:346:0x0983, B:347:0x0986, B:359:0x099d, B:360:0x09a0, B:361:0x09a1, B:362:0x09ad, B:378:0x09df, B:379:0x09e2, B:385:0x09e9, B:386:0x09ec, B:396:0x09fa, B:397:0x09fd, B:382:0x09e6, B:334:0x0969, B:393:0x09f7, B:233:0x04b4, B:343:0x0980, B:356:0x099a, B:375:0x09dc), top: B:459:0x00f9, inners: #6, #8, #17, #21, #25, #41, #42 }] */
    /* JADX WARN: Code duplicated, block: B:314:0x08b7  */
    /* JADX WARN: Code duplicated, block: B:316:0x08e4  */
    /* JADX WARN: Code duplicated, block: B:324:0x0934 A[Catch: all -> 0x09fe, TRY_ENTER, TryCatch #22 {all -> 0x09fe, blocks: (B:291:0x06c3, B:293:0x06c9, B:297:0x06fb, B:299:0x0752, B:303:0x0769, B:305:0x0770, B:307:0x084c, B:309:0x08a5, B:311:0x08aa, B:313:0x08b2, B:315:0x08bb, B:317:0x08ed, B:304:0x076d, B:300:0x075a, B:296:0x06f9, B:324:0x0934, B:325:0x093f, B:330:0x0949, B:331:0x0965, B:337:0x096c, B:338:0x096f, B:339:0x0970, B:340:0x097c, B:346:0x0983, B:347:0x0986, B:359:0x099d, B:360:0x09a0, B:361:0x09a1, B:362:0x09ad, B:378:0x09df, B:379:0x09e2, B:385:0x09e9, B:386:0x09ec, B:396:0x09fa, B:397:0x09fd, B:382:0x09e6, B:334:0x0969, B:393:0x09f7, B:233:0x04b4, B:343:0x0980, B:356:0x099a, B:375:0x09dc), top: B:459:0x00f9, inners: #6, #8, #17, #21, #25, #41, #42 }] */
    /* JADX WARN: Code duplicated, block: B:330:0x0949 A[Catch: all -> 0x09fe, TryCatch #22 {all -> 0x09fe, blocks: (B:291:0x06c3, B:293:0x06c9, B:297:0x06fb, B:299:0x0752, B:303:0x0769, B:305:0x0770, B:307:0x084c, B:309:0x08a5, B:311:0x08aa, B:313:0x08b2, B:315:0x08bb, B:317:0x08ed, B:304:0x076d, B:300:0x075a, B:296:0x06f9, B:324:0x0934, B:325:0x093f, B:330:0x0949, B:331:0x0965, B:337:0x096c, B:338:0x096f, B:339:0x0970, B:340:0x097c, B:346:0x0983, B:347:0x0986, B:359:0x099d, B:360:0x09a0, B:361:0x09a1, B:362:0x09ad, B:378:0x09df, B:379:0x09e2, B:385:0x09e9, B:386:0x09ec, B:396:0x09fa, B:397:0x09fd, B:382:0x09e6, B:334:0x0969, B:393:0x09f7, B:233:0x04b4, B:343:0x0980, B:356:0x099a, B:375:0x09dc), top: B:459:0x00f9, inners: #6, #8, #17, #21, #25, #41, #42 }] */
    /* JADX WARN: Code duplicated, block: B:339:0x0970 A[Catch: all -> 0x09fe, TryCatch #22 {all -> 0x09fe, blocks: (B:291:0x06c3, B:293:0x06c9, B:297:0x06fb, B:299:0x0752, B:303:0x0769, B:305:0x0770, B:307:0x084c, B:309:0x08a5, B:311:0x08aa, B:313:0x08b2, B:315:0x08bb, B:317:0x08ed, B:304:0x076d, B:300:0x075a, B:296:0x06f9, B:324:0x0934, B:325:0x093f, B:330:0x0949, B:331:0x0965, B:337:0x096c, B:338:0x096f, B:339:0x0970, B:340:0x097c, B:346:0x0983, B:347:0x0986, B:359:0x099d, B:360:0x09a0, B:361:0x09a1, B:362:0x09ad, B:378:0x09df, B:379:0x09e2, B:385:0x09e9, B:386:0x09ec, B:396:0x09fa, B:397:0x09fd, B:382:0x09e6, B:334:0x0969, B:393:0x09f7, B:233:0x04b4, B:343:0x0980, B:356:0x099a, B:375:0x09dc), top: B:459:0x00f9, inners: #6, #8, #17, #21, #25, #41, #42 }] */
    /* JADX WARN: Code duplicated, block: B:348:0x0987  */
    /* JADX WARN: Code duplicated, block: B:437:0x05e8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:454:0x062c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:469:0x01bb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:54:0x0157  */
    /* JADX WARN: Code duplicated, block: B:58:0x0167 A[Catch: all -> 0x0364, TRY_LEAVE, TryCatch #3 {all -> 0x0364, blocks: (B:56:0x015e, B:58:0x0167), top: B:423:0x015e }] */
    /* JADX WARN: Code duplicated, block: B:63:0x0183  */
    /* JADX WARN: Code duplicated, block: B:68:0x0195 A[Catch: all -> 0x0344, TRY_ENTER, TRY_LEAVE, TryCatch #26 {all -> 0x0344, blocks: (B:61:0x017a, B:68:0x0195), top: B:465:0x017a }] */
    /* JADX WARN: Code duplicated, block: B:72:0x01a6 A[Catch: all -> 0x0373, TRY_LEAVE, TryCatch #15 {all -> 0x0373, blocks: (B:43:0x013b, B:60:0x0170, B:70:0x0199, B:72:0x01a6, B:80:0x01d5, B:102:0x022c, B:107:0x024d, B:101:0x0226, B:55:0x0158, B:59:0x016c), top: B:446:0x013b }] */
    /* JADX WARN: Code duplicated, block: B:79:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:82:0x01da  */
    /* JADX WARN: Code duplicated, block: B:88:0x01f9  */
    /* JADX WARN: Code duplicated, block: B:90:0x01fc A[Catch: all -> 0x0152, TryCatch #33 {all -> 0x0152, blocks: (B:45:0x0142, B:83:0x01dc, B:85:0x01e4, B:90:0x01fc, B:93:0x0205, B:95:0x020d, B:104:0x0249), top: B:479:0x0142 }] */
    /* JADX WARN: Code duplicated, block: B:91:0x0201  */
    /* JADX WARN: Instruction removed from duplicated block: B:330:0x0949, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v7, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r13v13 */
    /* JADX WARN: Type inference failed for: r13v14 */
    /* JADX WARN: Type inference failed for: r13v15, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r13v30 */
    /* JADX WARN: Type inference failed for: r13v31 */
    /* JADX WARN: Type inference failed for: r13v5, types: [com.hazem.dailystore.data.PermissionPolicy] */
    /* JADX WARN: Type inference failed for: r15v0, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v2 */
    /* JADX WARN: Type inference failed for: r15v20 */
    /* JADX WARN: Type inference failed for: r15v21 */
    /* JADX WARN: Type inference failed for: r15v25 */
    /* JADX WARN: Type inference failed for: r15v26 */
    /* JADX WARN: Type inference failed for: r15v27 */
    /* JADX WARN: Type inference failed for: r15v3, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r15v35 */
    /* JADX WARN: Type inference failed for: r15v4 */
    /* JADX WARN: Type inference failed for: r15v40 */
    /* JADX WARN: Type inference failed for: r15v41 */
    /* JADX WARN: Type inference failed for: r15v45 */
    /* JADX WARN: Type inference failed for: r15v47 */
    /* JADX WARN: Type inference failed for: r15v48, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r15v49 */
    /* JADX WARN: Type inference failed for: r15v8 */
    /* JADX WARN: Type inference failed for: r15v9 */
    /* JADX WARN: Type inference failed for: r27v0 */
    /* JADX WARN: Type inference failed for: r27v1 */
    /* JADX WARN: Type inference failed for: r27v10, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r27v9 */
    /* JADX WARN: Type inference failed for: r2v19, types: [com.hazem.dailystore.data.PermissionPolicy] */
    /* JADX WARN: Type inference failed for: r2v34, types: [com.hazem.dailystore.data.SyncProtocolRepository] */
    /* JADX WARN: Type inference failed for: r2v58, types: [com.hazem.dailystore.data.FifoCosting] */
    /* JADX WARN: Type inference failed for: r2v70, types: [com.hazem.dailystore.data.SyncProtocolRepository] */
    /* JADX WARN: Type inference failed for: r3v17, types: [com.hazem.dailystore.data.StoreTime] */
    /* JADX WARN: Type inference failed for: r3v51, types: [android.content.ContentValues] */
    /* JADX WARN: Type inference failed for: r3v52, types: [net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r4v6, types: [org.json.JSONObject] */
    /* JADX WARN: Type inference failed for: r56v0 */
    /* JADX WARN: Type inference failed for: r56v1 */
    /* JADX WARN: Type inference failed for: r56v2 */
    /* JADX WARN: Type inference failed for: r56v3 */
    /* JADX WARN: Type inference failed for: r56v4 */
    /* JADX WARN: Type inference failed for: r56v5 */
    /* JADX WARN: Type inference failed for: r60v0, types: [com.hazem.dailystore.data.InventoryAdjustmentRepository] */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r9v2, types: [java.lang.Object, net.zetetic.database.sqlcipher.SQLiteDatabase] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final InventoryAdjustmentResult postAdjustment(String actorUserId, String productId, BigDecimal quantityDelta, String reason, String operationId, Long unitCostUsdCents, String warehouseId, String occurredAt, boolean syncOrigin) throws Throwable {
        ?? r15;
        Throwable th;
        Throwable th2;
        String string;
        String str;
        Cursor cursorRawQuery;
        Throwable th3;
        Cursor cursor;
        BigDecimal bigDecimalValueOf;
        BigDecimal bigDecimal;
        BigDecimal bigDecimalAdd;
        Cursor cursorRawQuery2;
        Throwable th4;
        Cursor cursor2;
        JSONObject jSONObject;
        Throwable th5;
        String strTimestamp;
        String strOperatingDate;
        Object obj;
        Long lValueOf;
        String str2;
        String strOptString;
        String strOptString2;
        Cursor cursorRawQuery3;
        Throwable th6;
        Cursor cursor3;
        Throwable th7;
        Triple triple;
        Throwable th8;
        Throwable th9;
        Cursor cursorRawQuery4;
        Throwable th10;
        Cursor cursor4;
        String str3;
        Cursor cursorRawQuery5;
        boolean zMoveToFirst;
        Cursor cursorRawQuery6;
        Throwable th11;
        Cursor cursor5;
        BigDecimal bigDecimalValueOf2;
        BigDecimal bigDecimal2;
        BigDecimal bigDecimalAdd2;
        String string2;
        String string3;
        String strTimestamp2;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        ?? r13;
        Long lConsumeForSale;
        String str10;
        String str11;
        ?? r56;
        String str12;
        ?? r57;
        String str13;
        ?? r16;
        int iUpdate;
        String str14;
        Object obj2;
        ContentValues contentValues;
        String str15;
        String str16;
        Long l;
        String str17;
        String str18;
        Long l2;
        Object obj3;
        Object obj4;
        InventoryAdjustmentResult inventoryAdjustmentResultLoadResult;
        ?? productId2 = productId;
        String str19 = warehouseId;
        Intrinsics.checkNotNullParameter(actorUserId, "actorUserId");
        Intrinsics.checkNotNullParameter(productId2, "productId");
        Intrinsics.checkNotNullParameter(quantityDelta, "quantityDelta");
        Intrinsics.checkNotNullParameter(reason, "reason");
        Intrinsics.checkNotNullParameter(operationId, "operationId");
        if (StringsKt.isBlank(actorUserId) || StringsKt.isBlank((CharSequence) productId2) || StringsKt.isBlank(operationId)) {
            throw new IllegalArgumentException("\u0628\u064a\u0627\u0646\u0627\u062a \u062a\u0633\u0648\u064a\u0629 \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u063a\u064a\u0631 \u0645\u0643\u062a\u0645\u0644\u0629".toString());
        }
        if (quantityDelta.signum() == 0) {
            throw new IllegalArgumentException("\u0641\u0631\u0642 \u0627\u0644\u0643\u0645\u064a\u0629 \u064a\u062c\u0628 \u0623\u0644\u0627 \u064a\u0633\u0627\u0648\u064a \u0635\u0641\u0631\u0627\u064b".toString());
        }
        String string4 = StringsKt.trim((CharSequence) reason).toString();
        if (string4.length() <= 0) {
            throw new IllegalArgumentException("\u0633\u0628\u0628 \u0627\u0644\u062a\u0633\u0648\u064a\u0629 \u0645\u0637\u0644\u0648\u0628".toString());
        }
        boolean z = quantityDelta.signum() > 0;
        if (z) {
            if (unitCostUsdCents == null || unitCostUsdCents.longValue() <= 0) {
                throw new IllegalArgumentException("\u064a\u062c\u0628 \u062a\u062d\u062f\u064a\u062f \u062a\u0643\u0644\u0641\u0629 \u0634\u0631\u0627\u0621 \u0645\u0648\u062c\u0628\u0629 \u0644\u0644\u0643\u0645\u064a\u0629 \u0627\u0644\u0645\u0636\u0627\u0641\u0629 \u062d\u062a\u0649 \u0644\u0627 \u064a\u062a\u0634\u0648\u0647 \u0631\u0623\u0633 \u0645\u0627\u0644 \u0627\u0644\u0645\u062e\u0632\u0648\u0646".toString());
            }
        } else if (unitCostUsdCents != null) {
            throw new IllegalArgumentException("\u062a\u0643\u0644\u0641\u0629 \u0627\u0644\u0643\u0645\u064a\u0629 \u0627\u0644\u0645\u0646\u0642\u0648\u0635\u0629 \u062a\u064f\u062d\u0633\u0628 \u062a\u0644\u0642\u0627\u0626\u064a\u0627\u064b \u0648\u0641\u0642 FIFO \u0648\u0644\u0627 \u062a\u064f\u062f\u062e\u0644 \u064a\u062f\u0648\u064a\u0627\u064b".toString());
        }
        BigDecimal bigDecimalAbs = quantityDelta.abs();
        String strHash = hash(productId2 + "|" + str19 + "|" + MainActivity$$ExternalSyntheticBackport0.m(quantityDelta).toPlainString() + "|" + (unitCostUsdCents == null ? "" : unitCostUsdCents) + "|" + string4);
        productId2 = this.database.getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(productId2, "<get-writableDatabase>(...)");
        productId2.beginTransaction();
        try {
            try {
                Cursor cursorRawQuery7 = productId2.rawQuery("SELECT state_value FROM sync_protocol_state WHERE state_key='local_device_role'", null);
                try {
                    Cursor cursor6 = cursorRawQuery7;
                    if (cursor6.moveToFirst()) {
                        try {
                            string = cursor6.getString(0);
                        } catch (Throwable th12) {
                            th2 = th12;
                            try {
                                throw th2;
                            } catch (Throwable th13) {
                                CloseableKt.closeFinally(cursorRawQuery7, th2);
                                throw th13;
                            }
                        }
                    } else {
                        string = null;
                    }
                    CloseableKt.closeFinally(cursorRawQuery7, null);
                    if (Intrinsics.areEqual(string, "SECONDARY")) {
                        try {
                            PermissionPolicy.INSTANCE.requireAssigned(productId2, actorUserId, PermissionPolicy.INVENTORY_ADJUST);
                            try {
                                if (str19 == null) {
                                    cursorRawQuery3 = productId2.rawQuery("SELECT id FROM warehouses WHERE is_active=1 ORDER BY is_default DESC,created_at,id LIMIT 1", null);
                                    cursor3 = cursorRawQuery3;
                                    if (!cursor3.moveToFirst()) {
                                        throw new IllegalArgumentException("\u0644\u0627 \u064a\u0648\u062c\u062f \u0645\u0633\u062a\u0648\u062f\u0639 \u0646\u0634\u0637".toString());
                                    }
                                    String string5 = cursor3.getString(0);
                                    CloseableKt.closeFinally(cursorRawQuery3, null);
                                    str = string5;
                                    cursorRawQuery = productId2.rawQuery("SELECT quantity FROM inventory_balances WHERE product_id=? AND warehouse_id=?", new String[]{productId2, str});
                                    cursor = cursorRawQuery;
                                    if (cursor.moveToFirst()) {
                                        bigDecimalValueOf = BigDecimal.valueOf(cursor.getDouble(0));
                                    } else {
                                        bigDecimalValueOf = BigDecimal.ZERO;
                                    }
                                    bigDecimal = bigDecimalValueOf;
                                    CloseableKt.closeFinally(cursorRawQuery, null);
                                    bigDecimalAdd = bigDecimal.add(quantityDelta);
                                    if (bigDecimalAdd.signum() >= 0) {
                                        throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u062a\u0624\u062f\u064a \u0627\u0644\u062a\u0633\u0648\u064a\u0629 \u0625\u0644\u0649 \u0645\u062e\u0632\u0648\u0646 \u0633\u0627\u0644\u0628 \u0639\u0644\u0649 \u0627\u0644\u0646\u0633\u062e\u0629 \u0627\u0644\u0645\u062d\u0644\u064a\u0629".toString());
                                    }
                                    cursorRawQuery2 = productId2.rawQuery("SELECT payload_json FROM sync_operation_envelopes WHERE operation_id=?", new String[]{operationId});
                                    cursor2 = cursorRawQuery2;
                                    if (cursor2.moveToFirst()) {
                                        jSONObject = new JSONObject(cursor2.getString(0));
                                        th5 = null;
                                    } else {
                                        th5 = null;
                                        jSONObject = null;
                                    }
                                    CloseableKt.closeFinally(cursorRawQuery2, th5);
                                    if (occurredAt == null) {
                                        if (jSONObject != null) {
                                            strTimestamp = null;
                                        } else {
                                            strTimestamp = null;
                                        }
                                        if (strTimestamp == null) {
                                            strTimestamp = timestamp();
                                        }
                                    } else {
                                        strTimestamp = occurredAt;
                                    }
                                    if (jSONObject != null) {
                                        strOperatingDate = StoreTime.INSTANCE.operatingDate(productId2, strTimestamp);
                                    } else {
                                        strOperatingDate = StoreTime.INSTANCE.operatingDate(productId2, strTimestamp);
                                    }
                                    JSONObject jSONObjectPut = new JSONObject().put("product_id", productId2).put("warehouse_id", str).put("quantity_delta", MainActivity$$ExternalSyntheticBackport0.m(quantityDelta).toPlainString());
                                    if (unitCostUsdCents == null) {
                                        obj = JSONObject.NULL;
                                    } else {
                                        obj = unitCostUsdCents;
                                    }
                                    JSONObject jSONObjectPut2 = jSONObjectPut.put("unit_cost_usd_cents", obj).put("reason", string4).put("occurred_at", strTimestamp).put("operating_date", strOperatingDate);
                                    String str20 = "pending-adjustment-" + operationId;
                                    int i = 2;
                                    Context context = this.context;
                                    Intrinsics.checkNotNullExpressionValue(context, "context");
                                    ?? syncProtocolRepository = new SyncProtocolRepository(context, null, i, false ? 1 : 0);
                                    String string6 = jSONObjectPut2.toString();
                                    Intrinsics.checkNotNullExpressionValue(string6, "toString(...)");
                                    lValueOf = null;
                                    productId2 = productId2;
                                    syncProtocolRepository.enqueueSecondaryCommand(productId2, actorUserId, operationId, "POST_INVENTORY_ADJUSTMENT", "INVENTORY_ADJUSTMENT", str20, string6);
                                    productId2.setTransactionSuccessful();
                                    if (z) {
                                        Intrinsics.checkNotNull(unitCostUsdCents);
                                        lValueOf = Long.valueOf(BigDecimal.valueOf(unitCostUsdCents.longValue()).multiply(bigDecimalAbs).setScale(0, RoundingMode.HALF_UP).longValueExact());
                                    }
                                    String str21 = "pending-movement-" + operationId;
                                    Intrinsics.checkNotNull(str);
                                    if (z) {
                                        str2 = "INCREASE";
                                    } else {
                                        str2 = "DECREASE";
                                    }
                                    String str22 = str2;
                                    Intrinsics.checkNotNull(bigDecimalAbs);
                                    Intrinsics.checkNotNull(bigDecimal);
                                    Intrinsics.checkNotNull(bigDecimalAdd);
                                    InventoryAdjustmentResult inventoryAdjustmentResult = new InventoryAdjustmentResult(str20, str21, productId, str, str22, bigDecimalAbs, bigDecimal, bigDecimalAdd, unitCostUsdCents, lValueOf, string4, false);
                                    productId2.endTransaction();
                                    Result.Companion companion = Result.INSTANCE;
                                    SyncRelaySession.INSTANCE.flushPending();
                                    Result.m381constructorimpl(Unit.INSTANCE);
                                    return inventoryAdjustmentResult;
                                    th6 = th;
                                    throw th6;
                                }
                                try {
                                    if (StringsKt.isBlank(str19)) {
                                        str19 = null;
                                    }
                                    if (str19 == null) {
                                        cursorRawQuery3 = productId2.rawQuery("SELECT id FROM warehouses WHERE is_active=1 ORDER BY is_default DESC,created_at,id LIMIT 1", null);
                                        try {
                                            cursor3 = cursorRawQuery3;
                                            if (!cursor3.moveToFirst()) {
                                                String string7 = cursor3.getString(0);
                                                CloseableKt.closeFinally(cursorRawQuery3, null);
                                                str = string7;
                                            } else {
                                                try {
                                                    throw new IllegalArgumentException("\u0644\u0627 \u064a\u0648\u062c\u062f \u0645\u0633\u062a\u0648\u062f\u0639 \u0646\u0634\u0637".toString());
                                                } catch (Throwable th14) {
                                                    th = th14;
                                                }
                                            }
                                        } catch (Throwable th15) {
                                            th = th15;
                                        }
                                        th6 = th;
                                        try {
                                            throw th6;
                                        } catch (Throwable th16) {
                                            CloseableKt.closeFinally(cursorRawQuery3, th6);
                                            throw th16;
                                        }
                                    }
                                    str = str19;
                                    cursorRawQuery = productId2.rawQuery("SELECT quantity FROM inventory_balances WHERE product_id=? AND warehouse_id=?", new String[]{productId2, str});
                                    try {
                                        cursor = cursorRawQuery;
                                        if (cursor.moveToFirst()) {
                                            try {
                                                bigDecimalValueOf = BigDecimal.valueOf(cursor.getDouble(0));
                                            } catch (Throwable th17) {
                                                th3 = th17;
                                                try {
                                                    throw th3;
                                                } catch (Throwable th18) {
                                                    CloseableKt.closeFinally(cursorRawQuery, th3);
                                                    throw th18;
                                                }
                                            }
                                        } else {
                                            bigDecimalValueOf = BigDecimal.ZERO;
                                        }
                                        bigDecimal = bigDecimalValueOf;
                                        CloseableKt.closeFinally(cursorRawQuery, null);
                                        bigDecimalAdd = bigDecimal.add(quantityDelta);
                                        if (bigDecimalAdd.signum() >= 0) {
                                            throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u062a\u0624\u062f\u064a \u0627\u0644\u062a\u0633\u0648\u064a\u0629 \u0625\u0644\u0649 \u0645\u062e\u0632\u0648\u0646 \u0633\u0627\u0644\u0628 \u0639\u0644\u0649 \u0627\u0644\u0646\u0633\u062e\u0629 \u0627\u0644\u0645\u062d\u0644\u064a\u0629".toString());
                                        }
                                        cursorRawQuery2 = productId2.rawQuery("SELECT payload_json FROM sync_operation_envelopes WHERE operation_id=?", new String[]{operationId});
                                        try {
                                            cursor2 = cursorRawQuery2;
                                            if (cursor2.moveToFirst()) {
                                                try {
                                                    jSONObject = new JSONObject(cursor2.getString(0));
                                                    th5 = null;
                                                } catch (Throwable th19) {
                                                    th4 = th19;
                                                    try {
                                                        throw th4;
                                                    } catch (Throwable th20) {
                                                        CloseableKt.closeFinally(cursorRawQuery2, th4);
                                                        throw th20;
                                                    }
                                                }
                                            } else {
                                                th5 = null;
                                                jSONObject = null;
                                            }
                                            CloseableKt.closeFinally(cursorRawQuery2, th5);
                                            if (occurredAt == null) {
                                                if (jSONObject != null || (strOptString2 = jSONObject.optString("occurred_at")) == null) {
                                                    strTimestamp = null;
                                                } else if (new Regex("\\d{4}-\\d{2}-\\d{2}T.*").matches(strOptString2)) {
                                                    strTimestamp = strOptString2;
                                                } else {
                                                    strTimestamp = null;
                                                }
                                                if (strTimestamp == null) {
                                                    strTimestamp = timestamp();
                                                }
                                            } else {
                                                strTimestamp = occurredAt;
                                            }
                                            if (jSONObject != null || (strOptString = jSONObject.optString("operating_date")) == null) {
                                                strOperatingDate = StoreTime.INSTANCE.operatingDate(productId2, strTimestamp);
                                            } else {
                                                strOperatingDate = new Regex("\\d{4}-\\d{2}-\\d{2}").matches(strOptString) ? strOptString : null;
                                                if (strOperatingDate == null) {
                                                    strOperatingDate = StoreTime.INSTANCE.operatingDate(productId2, strTimestamp);
                                                }
                                            }
                                            JSONObject jSONObjectPut3 = new JSONObject().put("product_id", productId2).put("warehouse_id", str).put("quantity_delta", MainActivity$$ExternalSyntheticBackport0.m(quantityDelta).toPlainString());
                                            if (unitCostUsdCents == null) {
                                                obj = JSONObject.NULL;
                                            } else {
                                                obj = unitCostUsdCents;
                                            }
                                            JSONObject jSONObjectPut4 = jSONObjectPut3.put("unit_cost_usd_cents", obj).put("reason", string4).put("occurred_at", strTimestamp).put("operating_date", strOperatingDate);
                                            String str23 = "pending-adjustment-" + operationId;
                                            int i2 = 2;
                                            try {
                                                Context context2 = this.context;
                                                Intrinsics.checkNotNullExpressionValue(context2, "context");
                                                ?? syncProtocolRepository2 = new SyncProtocolRepository(context2, null, i2, false ? 1 : 0);
                                                String string8 = jSONObjectPut4.toString();
                                                Intrinsics.checkNotNullExpressionValue(string8, "toString(...)");
                                                lValueOf = null;
                                                productId2 = productId2;
                                                try {
                                                    syncProtocolRepository2.enqueueSecondaryCommand(productId2, actorUserId, operationId, "POST_INVENTORY_ADJUSTMENT", "INVENTORY_ADJUSTMENT", str23, string8);
                                                    productId2.setTransactionSuccessful();
                                                    if (z) {
                                                        Intrinsics.checkNotNull(unitCostUsdCents);
                                                        lValueOf = Long.valueOf(BigDecimal.valueOf(unitCostUsdCents.longValue()).multiply(bigDecimalAbs).setScale(0, RoundingMode.HALF_UP).longValueExact());
                                                    }
                                                    String str24 = "pending-movement-" + operationId;
                                                    Intrinsics.checkNotNull(str);
                                                    if (z) {
                                                        str2 = "INCREASE";
                                                    } else {
                                                        str2 = "DECREASE";
                                                    }
                                                    String str25 = str2;
                                                    Intrinsics.checkNotNull(bigDecimalAbs);
                                                    Intrinsics.checkNotNull(bigDecimal);
                                                    Intrinsics.checkNotNull(bigDecimalAdd);
                                                    InventoryAdjustmentResult inventoryAdjustmentResult2 = new InventoryAdjustmentResult(str23, str24, productId, str, str25, bigDecimalAbs, bigDecimal, bigDecimalAdd, unitCostUsdCents, lValueOf, string4, false);
                                                    productId2.endTransaction();
                                                    try {
                                                        Result.Companion companion2 = Result.INSTANCE;
                                                        SyncRelaySession.INSTANCE.flushPending();
                                                        Result.m381constructorimpl(Unit.INSTANCE);
                                                    } catch (Throwable th21) {
                                                        Result.Companion companion3 = Result.INSTANCE;
                                                        Result.m381constructorimpl(ResultKt.createFailure(th21));
                                                    }
                                                    return inventoryAdjustmentResult2;
                                                } catch (Throwable th22) {
                                                    th = th22;
                                                    th = th;
                                                    r15 = productId2;
                                                    r15.endTransaction();
                                                    Result.Companion companion4 = Result.INSTANCE;
                                                    SyncRelaySession.INSTANCE.flushPending();
                                                    Result.m381constructorimpl(Unit.INSTANCE);
                                                    throw th;
                                                }
                                            } catch (Throwable th23) {
                                                th = th23;
                                                productId2 = productId2;
                                            }
                                        } catch (Throwable th24) {
                                            th4 = th24;
                                        }
                                    } catch (Throwable th25) {
                                        th3 = th25;
                                    }
                                } catch (Throwable th26) {
                                    th = th26;
                                    r15 = productId2;
                                }
                            } catch (Throwable th27) {
                                th = th27;
                                th = th;
                                r15 = productId2;
                                r15.endTransaction();
                                Result.Companion companion5 = Result.INSTANCE;
                                SyncRelaySession.INSTANCE.flushPending();
                                Result.m381constructorimpl(Unit.INSTANCE);
                                throw th;
                            }
                        } catch (Throwable th28) {
                            th = th28;
                            productId2 = productId2;
                        }
                    } else {
                        try {
                            productId2 = productId2;
                            try {
                                PermissionPolicy.INSTANCE.require(productId2, actorUserId, PermissionPolicy.INVENTORY_ADJUST);
                                Cursor cursorRawQuery8 = productId2.rawQuery("SELECT operation_type,payload_hash,result_reference FROM operation_registry WHERE operation_id=?", new String[]{operationId});
                                try {
                                    Cursor cursor7 = cursorRawQuery8;
                                    if (cursor7.moveToFirst()) {
                                        try {
                                            triple = new Triple(cursor7.getString(0), cursor7.getString(1), cursor7.isNull(2) ? null : cursor7.getString(2));
                                            th8 = null;
                                        } catch (Throwable th29) {
                                            th7 = th29;
                                            try {
                                                throw th7;
                                            } catch (Throwable th30) {
                                                CloseableKt.closeFinally(cursorRawQuery8, th7);
                                                throw th30;
                                            }
                                        }
                                    } else {
                                        th8 = null;
                                        triple = null;
                                    }
                                    CloseableKt.closeFinally(cursorRawQuery8, th8);
                                    try {
                                        if (triple != null) {
                                            if (!Intrinsics.areEqual(triple.getFirst(), "POST_INVENTORY_ADJUSTMENT") || !Intrinsics.areEqual(triple.getSecond(), strHash)) {
                                                throw new IllegalArgumentException("\u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0645\u0633\u062a\u062e\u062f\u0645 \u0644\u062a\u0633\u0648\u064a\u0629 \u0645\u062e\u062a\u0644\u0641\u0629 \u0623\u0648 \u0628\u064a\u0627\u0646\u0627\u062a \u0645\u062e\u062a\u0644\u0641\u0629".toString());
                                            }
                                            String str26 = (String) triple.getThird();
                                            if (str26 == null || (inventoryAdjustmentResultLoadResult = loadResult(productId2, str26, true)) == null) {
                                                throw new IllegalStateException("\u0633\u062c\u0644 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0644\u0627 \u064a\u062d\u062a\u0648\u064a \u0645\u0631\u062c\u0639 \u0627\u0644\u062a\u0633\u0648\u064a\u0629".toString());
                                            }
                                            productId2.setTransactionSuccessful();
                                            productId2.endTransaction();
                                            try {
                                                Result.Companion companion6 = Result.INSTANCE;
                                                SyncRelaySession.INSTANCE.flushPending();
                                                Result.m381constructorimpl(Unit.INSTANCE);
                                            } catch (Throwable th31) {
                                                Result.Companion companion7 = Result.INSTANCE;
                                                Result.m381constructorimpl(ResultKt.createFailure(th31));
                                            }
                                            return inventoryAdjustmentResultLoadResult;
                                        }
                                        Cursor cursorRawQuery9 = productId2.rawQuery("SELECT p.track_inventory,p.is_active,u.decimal_places\n                   FROM products p JOIN units u ON u.id=p.unit_id WHERE p.id=?", new String[]{productId});
                                        try {
                                            Cursor cursor8 = cursorRawQuery9;
                                            try {
                                                if (!cursor8.moveToFirst()) {
                                                    throw new IllegalArgumentException("\u0627\u0644\u0635\u0646\u0641 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f".toString());
                                                }
                                                if (cursor8.getInt(0) != 1) {
                                                    throw new IllegalArgumentException("\u0647\u0630\u0627 \u0627\u0644\u0635\u0646\u0641 \u0644\u0627 \u064a\u062a\u062a\u0628\u0639 \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u0648\u0644\u0627 \u064a\u0642\u0628\u0644 \u062a\u0633\u0648\u064a\u0629 \u0643\u0645\u064a\u0629".toString());
                                                }
                                                if (cursor8.getInt(1) != 1) {
                                                    throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u0633\u0648\u064a\u0629 \u0635\u0646\u0641 \u0645\u0648\u0642\u0648\u0641".toString());
                                                }
                                                int i3 = cursor8.getInt(2);
                                                CloseableKt.closeFinally(cursorRawQuery9, null);
                                                if (RangesKt.coerceAtLeast(MainActivity$$ExternalSyntheticBackport0.m(bigDecimalAbs).scale(), 0) > i3) {
                                                    throw new IllegalArgumentException("\u062f\u0642\u0629 \u0627\u0644\u0643\u0645\u064a\u0629 \u062a\u062a\u062c\u0627\u0648\u0632 \u062f\u0642\u0629 \u0648\u062d\u062f\u0629 \u0642\u064a\u0627\u0633 \u0627\u0644\u0635\u0646\u0641".toString());
                                                }
                                                if (str19 != null) {
                                                    if (StringsKt.isBlank(str19)) {
                                                        str19 = null;
                                                    }
                                                    if (str19 == null) {
                                                        cursorRawQuery4 = productId2.rawQuery("SELECT id FROM warehouses WHERE is_active=1 ORDER BY is_default DESC,created_at,id LIMIT 1", null);
                                                        cursor4 = cursorRawQuery4;
                                                        if (!cursor4.moveToFirst()) {
                                                            throw new IllegalArgumentException("\u0644\u0627 \u064a\u0648\u062c\u062f \u0645\u0633\u062a\u0648\u062f\u0639 \u0646\u0634\u0637 \u0644\u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062a\u0633\u0648\u064a\u0629".toString());
                                                        }
                                                        String string9 = cursor4.getString(0);
                                                        CloseableKt.closeFinally(cursorRawQuery4, null);
                                                        str3 = string9;
                                                        th10 = th;
                                                        throw th10;
                                                    }
                                                    str3 = str19;
                                                    cursorRawQuery5 = productId2.rawQuery("SELECT 1 FROM warehouses WHERE id=? AND is_active=1", new String[]{str3});
                                                    zMoveToFirst = cursorRawQuery5.moveToFirst();
                                                    CloseableKt.closeFinally(cursorRawQuery5, null);
                                                    if (zMoveToFirst) {
                                                        throw new IllegalArgumentException("\u0627\u0644\u0645\u0633\u062a\u0648\u062f\u0639 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u0645\u0648\u0642\u0648\u0641".toString());
                                                    }
                                                    cursorRawQuery6 = productId2.rawQuery("SELECT quantity FROM inventory_balances WHERE product_id=? AND warehouse_id=?", new String[]{productId2, str3});
                                                    cursor5 = cursorRawQuery6;
                                                    if (cursor5.moveToFirst()) {
                                                        bigDecimalValueOf2 = BigDecimal.valueOf(cursor5.getDouble(0));
                                                    } else {
                                                        bigDecimalValueOf2 = BigDecimal.ZERO;
                                                    }
                                                    bigDecimal2 = bigDecimalValueOf2;
                                                    CloseableKt.closeFinally(cursorRawQuery6, null);
                                                    bigDecimalAdd2 = bigDecimal2.add(quantityDelta);
                                                    if (bigDecimalAdd2.signum() >= 0) {
                                                        throw new IllegalArgumentException(("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u062a\u0624\u062f\u064a \u0627\u0644\u062a\u0633\u0648\u064a\u0629 \u0625\u0644\u0649 \u0645\u062e\u0632\u0648\u0646 \u0633\u0627\u0644\u0628. \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u062d\u0627\u0644\u064a: " + bigDecimal2).toString());
                                                    }
                                                    string2 = UUID.randomUUID().toString();
                                                    Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                                                    string3 = UUID.randomUUID().toString();
                                                    Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
                                                    if (occurredAt == null) {
                                                        strTimestamp2 = timestamp();
                                                    } else {
                                                        strTimestamp2 = occurredAt;
                                                    }
                                                    if (z) {
                                                        str4 = "INCREASE";
                                                    } else {
                                                        str4 = "DECREASE";
                                                    }
                                                    str5 = str4;
                                                    if (z) {
                                                        str6 = "ADJUSTMENT_IN";
                                                    } else {
                                                        str6 = "ADJUSTMENT_OUT";
                                                    }
                                                    String str27 = str6;
                                                    if (z) {
                                                        Intrinsics.checkNotNull(unitCostUsdCents);
                                                        str7 = strTimestamp2;
                                                        str9 = "toString(...)";
                                                        str8 = null;
                                                        r13 = productId2;
                                                        lConsumeForSale = Long.valueOf(BigDecimal.valueOf(unitCostUsdCents.longValue()).multiply(bigDecimalAbs).setScale(0, RoundingMode.HALF_UP).longValueExact());
                                                    } else {
                                                        String str28 = strTimestamp2;
                                                        ?? r2 = FifoCosting.INSTANCE;
                                                        Intrinsics.checkNotNull(str3);
                                                        str7 = str28;
                                                        str8 = null;
                                                        str9 = "toString(...)";
                                                        r13 = productId2;
                                                        lConsumeForSale = r2.consumeForSale(productId2, string2, null, productId, str3, bigDecimalAbs.doubleValue(), str7, false);
                                                        r13 = r13;
                                                    }
                                                    ?? contentValues2 = new ContentValues();
                                                    contentValues2.put("id", string3);
                                                    contentValues2.put("product_id", productId2);
                                                    str10 = str3;
                                                    contentValues2.put("warehouse_id", str10);
                                                    contentValues2.put("movement_type", str27);
                                                    contentValues2.put("quantity_delta", Double.valueOf(quantityDelta.doubleValue()));
                                                    String str29 = str8;
                                                    contentValues2.put(r31, Long.valueOf(unitCostUsdCents != null ? unitCostUsdCents.longValue() : 0L));
                                                    contentValues2.put("source_type", "INVENTORY_ADJUSTMENT");
                                                    contentValues2.put("source_id", string2);
                                                    contentValues2.put("operation_id", operationId);
                                                    contentValues2.put("note", string4);
                                                    contentValues2.put("created_by", actorUserId);
                                                    str11 = str7;
                                                    contentValues2.put("created_at", str11);
                                                    Unit unit = Unit.INSTANCE;
                                                    r13.insertOrThrow("inventory_movements", str29, contentValues2);
                                                    if (z) {
                                                        FifoCosting fifoCosting = FifoCosting.INSTANCE;
                                                        Intrinsics.checkNotNull(str10);
                                                        double dDoubleValue = r7.doubleValue();
                                                        Intrinsics.checkNotNull(unitCostUsdCents);
                                                        str12 = str11;
                                                        r56 = r13;
                                                        fifoCosting.addLayer(r13, string3, productId, str10, dDoubleValue, unitCostUsdCents.longValue(), "INVENTORY_ADJUSTMENT", string2, str12, (512 & 512) != 0 ? null : null);
                                                        r57 = r56;
                                                    } else {
                                                        str12 = str11;
                                                        r57 = r13;
                                                    }
                                                    ContentValues contentValues3 = new ContentValues();
                                                    contentValues3.put("quantity", Double.valueOf(bigDecimalAdd2.doubleValue()));
                                                    str13 = str12;
                                                    contentValues3.put("updated_at", str13);
                                                    r16 = r57;
                                                    iUpdate = r16.update("inventory_balances", contentValues3, "product_id=? AND warehouse_id=?", new String[]{productId, str10});
                                                    if (iUpdate == 0) {
                                                        ContentValues contentValues4 = new ContentValues();
                                                        str14 = "product_id";
                                                        contentValues4.put(str14, productId);
                                                        contentValues4.put("warehouse_id", str10);
                                                        contentValues4.put("quantity", Double.valueOf(bigDecimalAdd2.doubleValue()));
                                                        contentValues4.put("updated_at", str13);
                                                        Unit unit2 = Unit.INSTANCE;
                                                        obj2 = null;
                                                        Long.valueOf(r16.insertOrThrow("inventory_balances", null, contentValues4));
                                                    } else {
                                                        str14 = r7;
                                                        obj2 = null;
                                                        if (iUpdate == 1) {
                                                            throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062d\u062f\u064a\u062b \u0631\u0635\u064a\u062f \u0627\u0644\u0645\u062e\u0632\u0648\u0646".toString());
                                                        }
                                                        Unit unit3 = Unit.INSTANCE;
                                                    }
                                                    contentValues = new ContentValues();
                                                    contentValues.put("id", string2);
                                                    contentValues.put("operation_id", operationId);
                                                    contentValues.put(str14, productId);
                                                    contentValues.put("warehouse_id", str10);
                                                    contentValues.put("inventory_movement_id", string3);
                                                    contentValues.put("adjustment_type", str5);
                                                    contentValues.put("quantity", Double.valueOf(r7.doubleValue()));
                                                    contentValues.put("quantity_before", Double.valueOf(bigDecimal2.doubleValue()));
                                                    contentValues.put("quantity_after", Double.valueOf(bigDecimalAdd2.doubleValue()));
                                                    if (unitCostUsdCents == null) {
                                                        str15 = "unit_cost_usd_cents";
                                                        contentValues.putNull(str15);
                                                    } else {
                                                        str15 = "unit_cost_usd_cents";
                                                        contentValues.put(str15, unitCostUsdCents);
                                                    }
                                                    str16 = str15;
                                                    l = lConsumeForSale;
                                                    if (l == null) {
                                                        contentValues.putNull("cost_usd_cents");
                                                    } else {
                                                        contentValues.put("cost_usd_cents", l);
                                                    }
                                                    contentValues.put("reason", string4);
                                                    contentValues.put("created_by", actorUserId);
                                                    contentValues.put("created_at", str13);
                                                    Unit unit4 = Unit.INSTANCE;
                                                    r16.insertOrThrow("inventory_adjustments", null, contentValues);
                                                    ContentValues contentValues5 = new ContentValues();
                                                    contentValues5.put("operation_id", operationId);
                                                    contentValues5.put("operation_type", "POST_INVENTORY_ADJUSTMENT");
                                                    contentValues5.put("payload_hash", strHash);
                                                    contentValues5.put(NotificationCompat.CATEGORY_STATUS, "COMPLETED");
                                                    contentValues5.put("result_reference", string2);
                                                    contentValues5.put("created_at", str13);
                                                    contentValues5.put("completed_at", str13);
                                                    Unit unit5 = Unit.INSTANCE;
                                                    r16.insertOrThrow("operation_registry", null, contentValues5);
                                                    ContentValues contentValues6 = new ContentValues();
                                                    contentValues6.put("id", UUID.randomUUID().toString());
                                                    contentValues6.put("user_id", actorUserId);
                                                    contentValues6.put("action", "INVENTORY_ADJUSTMENT_POSTED");
                                                    contentValues6.put("entity_type", "INVENTORY_ADJUSTMENT");
                                                    contentValues6.put("entity_id", string2);
                                                    contentValues6.put("after_json", new JSONObject().put(str14, productId).put("warehouse_id", str10).put("type", str5).put("quantity", r7.toPlainString()).put("quantity_before", bigDecimal2.toPlainString()).put("quantity_after", bigDecimalAdd2.toPlainString()).put("cost_usd_cents", l).put("reason", string4).toString());
                                                    contentValues6.put("created_at", str13);
                                                    Unit unit6 = Unit.INSTANCE;
                                                    r16.insertOrThrow("audit_log", null, contentValues6);
                                                    if (syncOrigin) {
                                                        str17 = string2;
                                                        str18 = string3;
                                                        l2 = l;
                                                    } else {
                                                        Context context3 = this.context;
                                                        Intrinsics.checkNotNullExpressionValue(context3, "context");
                                                        ?? syncProtocolRepository3 = new SyncProtocolRepository(context3, null, 2, false ? 1 : 0);
                                                        str17 = string2;
                                                        str18 = string3;
                                                        JSONObject jSONObjectPut5 = new JSONObject().put("adjustment_id", string2).put("movement_id", str18).put(str14, productId).put("warehouse_id", str10).put("adjustment_type", str5).put("quantity", bigDecimalAbs.toPlainString()).put("quantity_before", bigDecimal2.toPlainString()).put("quantity_after", bigDecimalAdd2.toPlainString());
                                                        l2 = l;
                                                        if (unitCostUsdCents == null) {
                                                            obj3 = JSONObject.NULL;
                                                        } else {
                                                            obj3 = unitCostUsdCents;
                                                        }
                                                        JSONObject jSONObjectPut6 = jSONObjectPut5.put(str16, obj3);
                                                        if (l2 == null) {
                                                            obj4 = JSONObject.NULL;
                                                        } else {
                                                            obj4 = l2;
                                                        }
                                                        String string10 = jSONObjectPut6.put("cost_usd_cents", obj4).put("reason", string4).put("created_at", str13).toString();
                                                        Intrinsics.checkNotNullExpressionValue(string10, str9);
                                                        syncProtocolRepository3.enqueueLocalOperation(r16, actorUserId, operationId, "POST_INVENTORY_ADJUSTMENT", "INVENTORY_ADJUSTMENT", str17, "CREATE", string10);
                                                    }
                                                    r16.setTransactionSuccessful();
                                                    Intrinsics.checkNotNull(str10);
                                                    Intrinsics.checkNotNull(r7);
                                                    Intrinsics.checkNotNull(bigDecimal2);
                                                    Intrinsics.checkNotNull(bigDecimalAdd2);
                                                    InventoryAdjustmentResult inventoryAdjustmentResult3 = new InventoryAdjustmentResult(str17, str18, productId, str10, str5, r7, bigDecimal2, bigDecimalAdd2, unitCostUsdCents, l2, string4, false);
                                                    r16.endTransaction();
                                                    Result.Companion companion8 = Result.INSTANCE;
                                                    SyncRelaySession.INSTANCE.flushPending();
                                                    Result.m381constructorimpl(Unit.INSTANCE);
                                                    return inventoryAdjustmentResult3;
                                                }
                                                cursorRawQuery4 = productId2.rawQuery("SELECT id FROM warehouses WHERE is_active=1 ORDER BY is_default DESC,created_at,id LIMIT 1", null);
                                                try {
                                                    cursor4 = cursorRawQuery4;
                                                    if (!cursor4.moveToFirst()) {
                                                        String string11 = cursor4.getString(0);
                                                        CloseableKt.closeFinally(cursorRawQuery4, null);
                                                        str3 = string11;
                                                        cursorRawQuery5 = productId2.rawQuery("SELECT 1 FROM warehouses WHERE id=? AND is_active=1", new String[]{str3});
                                                        try {
                                                            zMoveToFirst = cursorRawQuery5.moveToFirst();
                                                            CloseableKt.closeFinally(cursorRawQuery5, null);
                                                            if (zMoveToFirst) {
                                                                throw new IllegalArgumentException("\u0627\u0644\u0645\u0633\u062a\u0648\u062f\u0639 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f \u0623\u0648 \u0645\u0648\u0642\u0648\u0641".toString());
                                                            }
                                                            cursorRawQuery6 = productId2.rawQuery("SELECT quantity FROM inventory_balances WHERE product_id=? AND warehouse_id=?", new String[]{productId2, str3});
                                                            try {
                                                                cursor5 = cursorRawQuery6;
                                                                if (cursor5.moveToFirst()) {
                                                                    try {
                                                                        bigDecimalValueOf2 = BigDecimal.valueOf(cursor5.getDouble(0));
                                                                    } catch (Throwable th32) {
                                                                        th11 = th32;
                                                                        try {
                                                                            throw th11;
                                                                        } catch (Throwable th33) {
                                                                            CloseableKt.closeFinally(cursorRawQuery6, th11);
                                                                            throw th33;
                                                                        }
                                                                    }
                                                                } else {
                                                                    bigDecimalValueOf2 = BigDecimal.ZERO;
                                                                }
                                                                bigDecimal2 = bigDecimalValueOf2;
                                                                CloseableKt.closeFinally(cursorRawQuery6, null);
                                                                bigDecimalAdd2 = bigDecimal2.add(quantityDelta);
                                                                if (bigDecimalAdd2.signum() >= 0) {
                                                                    throw new IllegalArgumentException(("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u062a\u0624\u062f\u064a \u0627\u0644\u062a\u0633\u0648\u064a\u0629 \u0625\u0644\u0649 \u0645\u062e\u0632\u0648\u0646 \u0633\u0627\u0644\u0628. \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u062d\u0627\u0644\u064a: " + bigDecimal2).toString());
                                                                }
                                                                string2 = UUID.randomUUID().toString();
                                                                Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                                                                string3 = UUID.randomUUID().toString();
                                                                Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
                                                                if (occurredAt == null) {
                                                                    strTimestamp2 = timestamp();
                                                                } else {
                                                                    strTimestamp2 = occurredAt;
                                                                }
                                                                if (z) {
                                                                    str4 = "INCREASE";
                                                                } else {
                                                                    str4 = "DECREASE";
                                                                }
                                                                str5 = str4;
                                                                if (z) {
                                                                    str6 = "ADJUSTMENT_IN";
                                                                } else {
                                                                    str6 = "ADJUSTMENT_OUT";
                                                                }
                                                                String str210 = str6;
                                                                if (z) {
                                                                    Intrinsics.checkNotNull(unitCostUsdCents);
                                                                    str7 = strTimestamp2;
                                                                    str9 = "toString(...)";
                                                                    str8 = null;
                                                                    r13 = productId2;
                                                                    lConsumeForSale = Long.valueOf(BigDecimal.valueOf(unitCostUsdCents.longValue()).multiply(bigDecimalAbs).setScale(0, RoundingMode.HALF_UP).longValueExact());
                                                                } else {
                                                                    String str211 = strTimestamp2;
                                                                    ?? r3 = FifoCosting.INSTANCE;
                                                                    Intrinsics.checkNotNull(str3);
                                                                    str7 = str211;
                                                                    str8 = null;
                                                                    str9 = "toString(...)";
                                                                    r13 = productId2;
                                                                    try {
                                                                        lConsumeForSale = r3.consumeForSale(productId2, string2, null, productId, str3, bigDecimalAbs.doubleValue(), str7, false);
                                                                        r13 = r13;
                                                                    } catch (Throwable th34) {
                                                                        th = th34;
                                                                        productId2 = r13;
                                                                    }
                                                                }
                                                                ?? contentValues7 = new ContentValues();
                                                                contentValues7.put("id", string3);
                                                                contentValues7.put("product_id", productId2);
                                                                str10 = str3;
                                                                contentValues7.put("warehouse_id", str10);
                                                                contentValues7.put("movement_type", str210);
                                                                contentValues7.put("quantity_delta", Double.valueOf(quantityDelta.doubleValue()));
                                                                String str212 = str8;
                                                                if (unitCostUsdCents != null) {
                                                                    try {
                                                                    } catch (Throwable th35) {
                                                                        th = th35;
                                                                        r15 = r13;
                                                                    }
                                                                }
                                                                contentValues7.put(r31, Long.valueOf(unitCostUsdCents != null ? unitCostUsdCents.longValue() : 0L));
                                                                contentValues7.put("source_type", "INVENTORY_ADJUSTMENT");
                                                                contentValues7.put("source_id", string2);
                                                                contentValues7.put("operation_id", operationId);
                                                                contentValues7.put("note", string4);
                                                                contentValues7.put("created_by", actorUserId);
                                                                str11 = str7;
                                                                contentValues7.put("created_at", str11);
                                                                Unit unit7 = Unit.INSTANCE;
                                                                r13.insertOrThrow("inventory_movements", str212, contentValues7);
                                                                if (z) {
                                                                    try {
                                                                        FifoCosting fifoCosting2 = FifoCosting.INSTANCE;
                                                                        Intrinsics.checkNotNull(str10);
                                                                        double dDoubleValue2 = r7.doubleValue();
                                                                        Intrinsics.checkNotNull(unitCostUsdCents);
                                                                        str12 = str11;
                                                                        r56 = r13;
                                                                        try {
                                                                            fifoCosting2.addLayer(r13, string3, productId, str10, dDoubleValue2, unitCostUsdCents.longValue(), "INVENTORY_ADJUSTMENT", string2, str12, (512 & 512) != 0 ? null : null);
                                                                            r57 = r56;
                                                                        } catch (Throwable th36) {
                                                                            th = th36;
                                                                            th = th;
                                                                            r15 = r56;
                                                                            r15.endTransaction();
                                                                            Result.Companion companion9 = Result.INSTANCE;
                                                                            SyncRelaySession.INSTANCE.flushPending();
                                                                            Result.m381constructorimpl(Unit.INSTANCE);
                                                                            throw th;
                                                                        }
                                                                    } catch (Throwable th37) {
                                                                        th = th37;
                                                                        r56 = r13;
                                                                    }
                                                                } else {
                                                                    str12 = str11;
                                                                    r57 = r13;
                                                                }
                                                                try {
                                                                    ContentValues contentValues8 = new ContentValues();
                                                                    contentValues8.put("quantity", Double.valueOf(bigDecimalAdd2.doubleValue()));
                                                                    str13 = str12;
                                                                    contentValues8.put("updated_at", str13);
                                                                    r16 = r57;
                                                                    iUpdate = r16.update("inventory_balances", contentValues8, "product_id=? AND warehouse_id=?", new String[]{productId, str10});
                                                                    if (iUpdate == 0) {
                                                                        ContentValues contentValues9 = new ContentValues();
                                                                        str14 = "product_id";
                                                                        contentValues9.put(str14, productId);
                                                                        contentValues9.put("warehouse_id", str10);
                                                                        contentValues9.put("quantity", Double.valueOf(bigDecimalAdd2.doubleValue()));
                                                                        contentValues9.put("updated_at", str13);
                                                                        Unit unit8 = Unit.INSTANCE;
                                                                        obj2 = null;
                                                                        Long.valueOf(r16.insertOrThrow("inventory_balances", null, contentValues9));
                                                                    } else {
                                                                        str14 = r7;
                                                                        obj2 = null;
                                                                        if (iUpdate == 1) {
                                                                            throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062a\u062d\u062f\u064a\u062b \u0631\u0635\u064a\u062f \u0627\u0644\u0645\u062e\u0632\u0648\u0646".toString());
                                                                        }
                                                                        Unit unit9 = Unit.INSTANCE;
                                                                    }
                                                                    contentValues = new ContentValues();
                                                                    contentValues.put("id", string2);
                                                                    contentValues.put("operation_id", operationId);
                                                                    contentValues.put(str14, productId);
                                                                    contentValues.put("warehouse_id", str10);
                                                                    contentValues.put("inventory_movement_id", string3);
                                                                    contentValues.put("adjustment_type", str5);
                                                                    contentValues.put("quantity", Double.valueOf(r7.doubleValue()));
                                                                    contentValues.put("quantity_before", Double.valueOf(bigDecimal2.doubleValue()));
                                                                    contentValues.put("quantity_after", Double.valueOf(bigDecimalAdd2.doubleValue()));
                                                                    if (unitCostUsdCents == null) {
                                                                        str15 = "unit_cost_usd_cents";
                                                                        contentValues.putNull(str15);
                                                                    } else {
                                                                        str15 = "unit_cost_usd_cents";
                                                                        contentValues.put(str15, unitCostUsdCents);
                                                                    }
                                                                    str16 = str15;
                                                                    l = lConsumeForSale;
                                                                    if (l == null) {
                                                                        contentValues.putNull("cost_usd_cents");
                                                                    } else {
                                                                        contentValues.put("cost_usd_cents", l);
                                                                    }
                                                                    contentValues.put("reason", string4);
                                                                    contentValues.put("created_by", actorUserId);
                                                                    contentValues.put("created_at", str13);
                                                                    Unit unit10 = Unit.INSTANCE;
                                                                    r16.insertOrThrow("inventory_adjustments", null, contentValues);
                                                                    ContentValues contentValues10 = new ContentValues();
                                                                    contentValues10.put("operation_id", operationId);
                                                                    contentValues10.put("operation_type", "POST_INVENTORY_ADJUSTMENT");
                                                                    contentValues10.put("payload_hash", strHash);
                                                                    contentValues10.put(NotificationCompat.CATEGORY_STATUS, "COMPLETED");
                                                                    contentValues10.put("result_reference", string2);
                                                                    contentValues10.put("created_at", str13);
                                                                    contentValues10.put("completed_at", str13);
                                                                    Unit unit11 = Unit.INSTANCE;
                                                                    r16.insertOrThrow("operation_registry", null, contentValues10);
                                                                    ContentValues contentValues11 = new ContentValues();
                                                                    contentValues11.put("id", UUID.randomUUID().toString());
                                                                    contentValues11.put("user_id", actorUserId);
                                                                    contentValues11.put("action", "INVENTORY_ADJUSTMENT_POSTED");
                                                                    contentValues11.put("entity_type", "INVENTORY_ADJUSTMENT");
                                                                    contentValues11.put("entity_id", string2);
                                                                    contentValues11.put("after_json", new JSONObject().put(str14, productId).put("warehouse_id", str10).put("type", str5).put("quantity", r7.toPlainString()).put("quantity_before", bigDecimal2.toPlainString()).put("quantity_after", bigDecimalAdd2.toPlainString()).put("cost_usd_cents", l).put("reason", string4).toString());
                                                                    contentValues11.put("created_at", str13);
                                                                    Unit unit12 = Unit.INSTANCE;
                                                                    r16.insertOrThrow("audit_log", null, contentValues11);
                                                                    if (syncOrigin) {
                                                                        Context context4 = this.context;
                                                                        Intrinsics.checkNotNullExpressionValue(context4, "context");
                                                                        ?? syncProtocolRepository4 = new SyncProtocolRepository(context4, null, 2, false ? 1 : 0);
                                                                        str17 = string2;
                                                                        str18 = string3;
                                                                        JSONObject jSONObjectPut7 = new JSONObject().put("adjustment_id", string2).put("movement_id", str18).put(str14, productId).put("warehouse_id", str10).put("adjustment_type", str5).put("quantity", bigDecimalAbs.toPlainString()).put("quantity_before", bigDecimal2.toPlainString()).put("quantity_after", bigDecimalAdd2.toPlainString());
                                                                        l2 = l;
                                                                        if (unitCostUsdCents == null) {
                                                                            obj3 = JSONObject.NULL;
                                                                        } else {
                                                                            obj3 = unitCostUsdCents;
                                                                        }
                                                                        JSONObject jSONObjectPut8 = jSONObjectPut7.put(str16, obj3);
                                                                        if (l2 == null) {
                                                                            obj4 = JSONObject.NULL;
                                                                        } else {
                                                                            obj4 = l2;
                                                                        }
                                                                        String string12 = jSONObjectPut8.put("cost_usd_cents", obj4).put("reason", string4).put("created_at", str13).toString();
                                                                        Intrinsics.checkNotNullExpressionValue(string12, str9);
                                                                        syncProtocolRepository4.enqueueLocalOperation(r16, actorUserId, operationId, "POST_INVENTORY_ADJUSTMENT", "INVENTORY_ADJUSTMENT", str17, "CREATE", string12);
                                                                    } else {
                                                                        str17 = string2;
                                                                        str18 = string3;
                                                                        l2 = l;
                                                                    }
                                                                    r16.setTransactionSuccessful();
                                                                    Intrinsics.checkNotNull(str10);
                                                                    Intrinsics.checkNotNull(r7);
                                                                    Intrinsics.checkNotNull(bigDecimal2);
                                                                    Intrinsics.checkNotNull(bigDecimalAdd2);
                                                                    InventoryAdjustmentResult inventoryAdjustmentResult4 = new InventoryAdjustmentResult(str17, str18, productId, str10, str5, r7, bigDecimal2, bigDecimalAdd2, unitCostUsdCents, l2, string4, false);
                                                                    r16.endTransaction();
                                                                    try {
                                                                        Result.Companion companion10 = Result.INSTANCE;
                                                                        SyncRelaySession.INSTANCE.flushPending();
                                                                        Result.m381constructorimpl(Unit.INSTANCE);
                                                                    } catch (Throwable th38) {
                                                                        Result.Companion companion11 = Result.INSTANCE;
                                                                        Result.m381constructorimpl(ResultKt.createFailure(th38));
                                                                    }
                                                                    return inventoryAdjustmentResult4;
                                                                } catch (Throwable th39) {
                                                                    th = th39;
                                                                    productId2 = r57;
                                                                }
                                                            } catch (Throwable th40) {
                                                                th11 = th40;
                                                            }
                                                        } catch (Throwable th41) {
                                                            productId2 = productId2;
                                                            try {
                                                                throw th41;
                                                            } catch (Throwable th42) {
                                                                CloseableKt.closeFinally(cursorRawQuery5, th41);
                                                                throw th42;
                                                            }
                                                        }
                                                    } else {
                                                        try {
                                                            throw new IllegalArgumentException("\u0644\u0627 \u064a\u0648\u062c\u062f \u0645\u0633\u062a\u0648\u062f\u0639 \u0646\u0634\u0637 \u0644\u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062a\u0633\u0648\u064a\u0629".toString());
                                                        } catch (Throwable th43) {
                                                            th = th43;
                                                        }
                                                    }
                                                } catch (Throwable th44) {
                                                    th = th44;
                                                }
                                                th10 = th;
                                                try {
                                                    throw th10;
                                                } catch (Throwable th45) {
                                                    CloseableKt.closeFinally(cursorRawQuery4, th10);
                                                    throw th45;
                                                }
                                            } catch (Throwable th46) {
                                                th = th46;
                                                th9 = th;
                                                throw th9;
                                            }
                                        } catch (Throwable th47) {
                                            th = th47;
                                            productId2 = productId2;
                                        }
                                        th9 = th;
                                        try {
                                            throw th9;
                                        } catch (Throwable th48) {
                                            CloseableKt.closeFinally(cursorRawQuery9, th9);
                                            throw th48;
                                        }
                                    } catch (Throwable th49) {
                                        th = th49;
                                        r15 = productId2;
                                    }
                                } catch (Throwable th50) {
                                    th7 = th50;
                                }
                            } catch (Throwable th51) {
                                th = th51;
                                productId2 = productId2;
                            }
                        } catch (Throwable th52) {
                            th = th52;
                            productId2 = productId2;
                        }
                        th = th;
                        r15 = productId2;
                    }
                } catch (Throwable th53) {
                    th2 = th53;
                }
            } catch (Throwable th54) {
                th = th54;
            }
        } catch (Throwable th55) {
            th = th55;
            productId2 = productId2;
        }
        r15.endTransaction();
        try {
            Result.Companion companion12 = Result.INSTANCE;
            SyncRelaySession.INSTANCE.flushPending();
            Result.m381constructorimpl(Unit.INSTANCE);
            throw th;
        } catch (Throwable th56) {
            Result.Companion companion13 = Result.INSTANCE;
            Result.m381constructorimpl(ResultKt.createFailure(th56));
            throw th;
        }
    }

    public static /* synthetic */ List listRecent$default(InventoryAdjustmentRepository inventoryAdjustmentRepository, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 100;
        }
        return inventoryAdjustmentRepository.listRecent(i);
    }

    public final List<InventoryAdjustmentResult> listRecent(int limit) throws IOException {
        int i = 1;
        if (1 > limit || limit >= 501) {
            throw new IllegalArgumentException("\u0639\u062f\u062f \u0627\u0644\u0633\u062c\u0644\u0627\u062a \u0627\u0644\u0645\u0637\u0644\u0648\u0628 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d".toString());
        }
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = this.database.getReadableDatabase().rawQuery("SELECT id,inventory_movement_id,product_id,warehouse_id,adjustment_type,\n                      quantity,quantity_before,quantity_after,unit_cost_usd_cents,\n                      cost_usd_cents,reason\n               FROM inventory_adjustments ORDER BY created_at DESC,id DESC LIMIT ?", new String[]{String.valueOf(limit)});
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                String string = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String string2 = cursor.getString(i);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                String string3 = cursor.getString(2);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                String string4 = cursor.getString(3);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                String string5 = cursor.getString(4);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                BigDecimal bigDecimalValueOf = BigDecimal.valueOf(cursor.getDouble(5));
                Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "valueOf(...)");
                BigDecimal bigDecimalValueOf2 = BigDecimal.valueOf(cursor.getDouble(6));
                Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf2, "valueOf(...)");
                BigDecimal bigDecimalValueOf3 = BigDecimal.valueOf(cursor.getDouble(7));
                Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf3, "valueOf(...)");
                Long lValueOf = cursor.isNull(8) ? null : Long.valueOf(cursor.getLong(8));
                Long lValueOf2 = cursor.isNull(9) ? null : Long.valueOf(cursor.getLong(9));
                String string6 = cursor.getString(10);
                Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                arrayList.add(new InventoryAdjustmentResult(string, string2, string3, string4, string5, bigDecimalValueOf, bigDecimalValueOf2, bigDecimalValueOf3, lValueOf, lValueOf2, string6, false));
                i = 1;
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            return arrayList;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final InventoryAdjustmentResult loadResult(SQLiteDatabase db, String id, boolean replayed) throws IOException {
        Cursor cursorRawQuery = db.rawQuery("SELECT id,inventory_movement_id,product_id,warehouse_id,adjustment_type,\n                      quantity,quantity_before,quantity_after,unit_cost_usd_cents,\n                      cost_usd_cents,reason\n               FROM inventory_adjustments WHERE id=?", new String[]{id});
        try {
            Cursor cursor = cursorRawQuery;
            if (!cursor.moveToFirst()) {
                throw new IllegalArgumentException("\u062a\u0633\u0648\u064a\u0629 \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u0627\u0644\u0633\u0627\u0628\u0642\u0629 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f\u0629".toString());
            }
            String string = cursor.getString(0);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String string2 = cursor.getString(1);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            String string3 = cursor.getString(2);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
            String string4 = cursor.getString(3);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
            String string5 = cursor.getString(4);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
            BigDecimal bigDecimalValueOf = BigDecimal.valueOf(cursor.getDouble(5));
            Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "valueOf(...)");
            BigDecimal bigDecimalValueOf2 = BigDecimal.valueOf(cursor.getDouble(6));
            Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf2, "valueOf(...)");
            BigDecimal bigDecimalValueOf3 = BigDecimal.valueOf(cursor.getDouble(7));
            Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf3, "valueOf(...)");
            Long lValueOf = cursor.isNull(8) ? null : Long.valueOf(cursor.getLong(8));
            Long lValueOf2 = cursor.isNull(9) ? null : Long.valueOf(cursor.getLong(9));
            String string6 = cursor.getString(10);
            Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
            InventoryAdjustmentResult inventoryAdjustmentResult = new InventoryAdjustmentResult(string, string2, string3, string4, string5, bigDecimalValueOf, bigDecimalValueOf2, bigDecimalValueOf3, lValueOf, lValueOf2, string6, replayed);
            CloseableKt.closeFinally(cursorRawQuery, null);
            return inventoryAdjustmentResult;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final String hash(String value) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = value.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes);
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
        return ArraysKt.joinToString$default(bArrDigest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.hazem.dailystore.data.InventoryAdjustmentRepository$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return InventoryAdjustmentRepository.hash$lambda$41(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence hash$lambda$41(byte b) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    private final String timestamp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String str = simpleDateFormat.format(new Date());
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}
