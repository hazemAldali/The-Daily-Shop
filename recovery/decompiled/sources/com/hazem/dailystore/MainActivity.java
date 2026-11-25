package com.hazem.dailystore;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.DocumentsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import com.google.android.material.card.MaterialCardViewHelper;
import com.hazem.dailystore.data.AppDatabase;
import com.hazem.dailystore.data.AuthRepository;
import com.hazem.dailystore.data.AuthenticatedUser;
import com.hazem.dailystore.data.BackupRestoreManager;
import com.hazem.dailystore.data.CashAccountRepository;
import com.hazem.dailystore.data.CashAccountSummary;
import com.hazem.dailystore.data.CurrencyConverter;
import com.hazem.dailystore.data.CustomerCreditRevaluationRecord;
import com.hazem.dailystore.data.CustomerFinancialSummary;
import com.hazem.dailystore.data.CustomerInvoicePaymentSummary;
import com.hazem.dailystore.data.CustomerPaymentRecord;
import com.hazem.dailystore.data.CustomerPaymentRepository;
import com.hazem.dailystore.data.CustomerPaymentResult;
import com.hazem.dailystore.data.CustomerRepository;
import com.hazem.dailystore.data.CustomerSummary;
import com.hazem.dailystore.data.DailyAccountSummary;
import com.hazem.dailystore.data.DailyBalanceRepository;
import com.hazem.dailystore.data.DailyCashReport;
import com.hazem.dailystore.data.DailyInventoryCapital;
import com.hazem.dailystore.data.ExpenseCategory;
import com.hazem.dailystore.data.InventoryAdjustmentRepository;
import com.hazem.dailystore.data.InventoryAdjustmentResult;
import com.hazem.dailystore.data.InventoryValuationRepository;
import com.hazem.dailystore.data.InvoiceLineInput;
import com.hazem.dailystore.data.InvoiceRepository;
import com.hazem.dailystore.data.InvoiceSaveResult;
import com.hazem.dailystore.data.ManagedUserSummary;
import com.hazem.dailystore.data.OperatingExpense;
import com.hazem.dailystore.data.OperatingExpenseRepository;
import com.hazem.dailystore.data.OperatingExpenseResult;
import com.hazem.dailystore.data.PartyStatement;
import com.hazem.dailystore.data.PartyStatementEntry;
import com.hazem.dailystore.data.PartyStatementExport;
import com.hazem.dailystore.data.PartyStatementRepository;
import com.hazem.dailystore.data.PermissionPolicy;
import com.hazem.dailystore.data.ProductImportRow;
import com.hazem.dailystore.data.PurchaseInvoicePaymentSummary;
import com.hazem.dailystore.data.PurchasePaymentRecord;
import com.hazem.dailystore.data.PurchasePaymentRepository;
import com.hazem.dailystore.data.PurchasePaymentResult;
import com.hazem.dailystore.data.PurchaseReturnInvoiceOption;
import com.hazem.dailystore.data.PurchaseReturnItemInput;
import com.hazem.dailystore.data.PurchaseReturnPreview;
import com.hazem.dailystore.data.PurchaseReturnRepository;
import com.hazem.dailystore.data.PurchaseReturnableItem;
import com.hazem.dailystore.data.RolePermissionSetting;
import com.hazem.dailystore.data.SalesProfitRepository;
import com.hazem.dailystore.data.SalesProfitSummary;
import com.hazem.dailystore.data.SalesRefundAllocationInput;
import com.hazem.dailystore.data.SalesReturnInvoiceOption;
import com.hazem.dailystore.data.SalesReturnItemInput;
import com.hazem.dailystore.data.SalesReturnPreview;
import com.hazem.dailystore.data.SalesReturnRepository;
import com.hazem.dailystore.data.SalesReturnableItem;
import com.hazem.dailystore.data.SecureSessionStore;
import com.hazem.dailystore.data.ServiceDailySummary;
import com.hazem.dailystore.data.ServiceTransferDebtPaymentResult;
import com.hazem.dailystore.data.ServiceTransferReceivableRepository;
import com.hazem.dailystore.data.ServiceTransferReceivableSummary;
import com.hazem.dailystore.data.ServiceTransferRecord;
import com.hazem.dailystore.data.ServiceTransferRepository;
import com.hazem.dailystore.data.ServiceTransferResult;
import com.hazem.dailystore.data.ServiceWalletSummary;
import com.hazem.dailystore.data.SpreadsheetProductImporter;
import com.hazem.dailystore.data.StoreCategory;
import com.hazem.dailystore.data.StoreProduct;
import com.hazem.dailystore.data.StoreRepository;
import com.hazem.dailystore.data.StoreTime;
import com.hazem.dailystore.data.StoreUnit;
import com.hazem.dailystore.data.SupplierFinancialSummary;
import com.hazem.dailystore.data.SupplierRefundAllocationInput;
import com.hazem.dailystore.data.SupplierRepository;
import com.hazem.dailystore.data.SupplierRolloverSummary;
import com.hazem.dailystore.data.SupplierSummary;
import com.hazem.dailystore.data.SyncCredentialVault;
import com.hazem.dailystore.data.SyncProtocolRepository;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import okhttp3.HttpUrl;
import org.bouncycastle.asn1.BERTags;
import org.bouncycastle.jcajce.util.AnnotatedPrivateKey;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u009c\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0006\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00f1\u00012\u00020\u0001:\b\u00ee\u0001\u00ef\u0001\u00f0\u0001\u00f1\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\"\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020\u00052\u0006\u0010?\u001a\u00020\u00052\b\u0010@\u001a\u0004\u0018\u00010AH\u0015J\u0018\u0010B\u001a\u00020=2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020\u000fH\u0002J\u0012\u0010F\u001a\u00020=2\b\u0010G\u001a\u0004\u0018\u00010HH\u0014J(\u0010I\u001a\u00020=2\u0006\u0010>\u001a\u00020\u00052\u0006\u0010C\u001a\u00020D2\u0006\u0010J\u001a\u00020%2\u0006\u0010K\u001a\u00020\u000fH\u0002J\b\u0010L\u001a\u00020=H\u0014J\u0010\u0010M\u001a\u00020=2\u0006\u0010N\u001a\u00020OH\u0016J\u0018\u0010P\u001a\u00020=2\u0006\u0010N\u001a\u00020O2\u0006\u0010Q\u001a\u00020RH\u0002J\b\u0010S\u001a\u00020=H\u0014J\b\u0010T\u001a\u00020=H\u0014J\u0010\u0010U\u001a\u00020=2\u0006\u0010V\u001a\u00020HH\u0014J\b\u0010W\u001a\u00020=H\u0017J\b\u0010X\u001a\u00020=H\u0002J\b\u0010Y\u001a\u00020\u000fH\u0002J\u0014\u0010Z\u001a\u0004\u0018\u00010\u000f2\b\u0010[\u001a\u0004\u0018\u00010AH\u0002J\u0010\u0010\\\u001a\u00020=2\u0006\u0010K\u001a\u00020\u000fH\u0002J\u0012\u0010]\u001a\u00020^2\b\u0010_\u001a\u0004\u0018\u00010HH\u0002J\u0010\u0010`\u001a\u00020^2\u0006\u0010a\u001a\u00020\u000fH\u0002J\u0012\u0010b\u001a\u00020=2\b\u0010c\u001a\u0004\u0018\u00010\u000fH\u0002J\b\u0010d\u001a\u00020=H\u0002J\u001c\u0010e\u001a\u00020=2\u0006\u0010f\u001a\u00020\u000f2\n\b\u0002\u0010g\u001a\u0004\u0018\u00010\u000fH\u0002J\b\u0010h\u001a\u00020=H\u0002J\b\u0010i\u001a\u00020=H\u0002J\b\u0010j\u001a\u00020=H\u0002J\u0018\u0010k\u001a\u00020=2\u0006\u0010l\u001a\u00020m2\u0006\u0010n\u001a\u00020\u000fH\u0002J\u0012\u0010o\u001a\u00020=2\b\b\u0002\u0010p\u001a\u00020^H\u0002J\b\u0010q\u001a\u00020\u0013H\u0002J\b\u0010r\u001a\u00020=H\u0002J\b\u0010s\u001a\u00020=H\u0002J\b\u0010t\u001a\u00020=H\u0002J\u0010\u0010u\u001a\u00020=2\u0006\u0010v\u001a\u00020\fH\u0002J\u0018\u0010w\u001a\u00020=2\u0006\u0010v\u001a\u00020\f2\u0006\u0010x\u001a\u00020yH\u0002J\b\u0010z\u001a\u00020=H\u0002J\u0018\u0010{\u001a\u00020=2\u0006\u0010|\u001a\u00020\u00102\u0006\u0010}\u001a\u00020yH\u0002J\b\u0010~\u001a\u00020=H\u0002J\u0011\u0010\u007f\u001a\u00020=2\u0007\u0010\u0080\u0001\u001a\u00020^H\u0002J\u0012\u0010\u0081\u0001\u001a\u00020=2\u0007\u0010\u0082\u0001\u001a\u00020OH\u0002J\t\u0010\u0083\u0001\u001a\u00020=H\u0002J\t\u0010\u0084\u0001\u001a\u00020=H\u0002J\u0012\u0010\u0085\u0001\u001a\u00020\u000f2\u0007\u0010\u0086\u0001\u001a\u00020\u000fH\u0002J+\u0010\u0087\u0001\u001a\u00020=2\u0006\u0010a\u001a\u00020\u000f2\b\u0010\u0088\u0001\u001a\u00030\u0089\u00012\u000e\u0010\u008a\u0001\u001a\t\u0012\u0004\u0012\u00020=0\u008b\u0001H\u0002J*\u0010\u008c\u0001\u001a\u00020=2\u0006\u0010a\u001a\u00020\u000f2\u0007\u0010\u008d\u0001\u001a\u00020\u000f2\u000e\u0010\u008a\u0001\u001a\t\u0012\u0004\u0012\u00020=0\u008b\u0001H\u0002J\t\u0010\u008e\u0001\u001a\u00020=H\u0002J\u0012\u0010\u008f\u0001\u001a\u00020=2\u0007\u0010\u0090\u0001\u001a\u00020\u000fH\u0002J\t\u0010\u0091\u0001\u001a\u00020=H\u0002J\u001c\u0010\u0092\u0001\u001a\u00020=2\b\u0010\u0093\u0001\u001a\u00030\u0094\u00012\u0007\u0010\u0095\u0001\u001a\u00020\u000fH\u0002J\u0012\u0010\u0096\u0001\u001a\u00020=2\u0007\u0010\u0097\u0001\u001a\u00020\u000fH\u0002J\u001c\u0010\u0098\u0001\u001a\u00020=2\u0007\u0010\u0097\u0001\u001a\u00020\u000f2\b\u0010\u0099\u0001\u001a\u00030\u009a\u0001H\u0002J$\u0010\u009b\u0001\u001a\u00020=2\t\u0010\u0097\u0001\u001a\u0004\u0018\u00010\u000f2\u000e\u0010\u008a\u0001\u001a\t\u0012\u0004\u0012\u00020=0\u008b\u0001H\u0002J\t\u0010\u009c\u0001\u001a\u00020^H\u0002J\u0012\u0010\u009d\u0001\u001a\u00020^2\u0007\u0010\u009e\u0001\u001a\u00020\u000fH\u0002J\t\u0010\u009f\u0001\u001a\u00020=H\u0002J\u0019\u0010\u00a0\u0001\u001a\u00020=2\u000e\u0010\u008a\u0001\u001a\t\u0012\u0004\u0012\u00020=0\u008b\u0001H\u0002J\t\u0010\u00a1\u0001\u001a\u00020=H\u0002J\t\u0010\u00a2\u0001\u001a\u00020=H\u0002J\t\u0010\u00a3\u0001\u001a\u00020=H\u0002J\t\u0010\u00a4\u0001\u001a\u00020=H\u0002J\t\u0010\u00a5\u0001\u001a\u00020=H\u0002J&\u0010\u00a6\u0001\u001a\u00020=2\u0007\u0010\u00a7\u0001\u001a\u00020\u000f2\u0012\b\u0002\u0010\u008a\u0001\u001a\u000b\u0012\u0004\u0012\u00020=\u0018\u00010\u008b\u0001H\u0002J\t\u0010\u00a8\u0001\u001a\u00020=H\u0002J\t\u0010\u00a9\u0001\u001a\u00020=H\u0002J\t\u0010\u00aa\u0001\u001a\u00020=H\u0002J#\u0010\u00ab\u0001\u001a\u00020=2\b\u0010\u00ac\u0001\u001a\u00030\u00ad\u00012\u000e\u0010\u008a\u0001\u001a\t\u0012\u0004\u0012\u00020=0\u008b\u0001H\u0002J\t\u0010\u00ae\u0001\u001a\u00020=H\u0002J\u0012\u0010\u00af\u0001\u001a\u00020\u000f2\u0007\u0010\u00b0\u0001\u001a\u00020\u000fH\u0002J\u001b\u0010\u00b1\u0001\u001a\u00020\u000f2\u0007\u0010\u00b2\u0001\u001a\u00020\u00132\u0007\u0010\u00b3\u0001\u001a\u00020\u000fH\u0002J\u0012\u0010\u00b4\u0001\u001a\u00020\u000f2\u0007\u0010\u0086\u0001\u001a\u00020\u000fH\u0002J\t\u0010\u00b5\u0001\u001a\u00020=H\u0002J \u0010\u00b6\u0001\u001a\u00020=2\u0015\u0010\u00b7\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u00b9\u0001\u0012\u0004\u0012\u00020=0\u00b8\u0001H\u0002JF\u0010\u00ba\u0001\u001a\u00020=2\u0007\u0010v\u001a\u00030\u00b9\u00012\n\b\u0002\u0010\u00bb\u0001\u001a\u00030\u00bc\u00012\t\b\u0002\u0010\u00bd\u0001\u001a\u00020\u00132\u001b\u0010\u00be\u0001\u001a\u0016\u0012\u0005\u0012\u00030\u00bc\u0001\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020=0\u00bf\u0001H\u0002J(\u0010\u00c0\u0001\u001a\u00020:2\u0007\u0010\u00c1\u0001\u001a\u00020\u000f2\t\b\u0002\u0010\u00c2\u0001\u001a\u00020\u000f2\t\b\u0002\u0010\u00b0\u0001\u001a\u00020\u0005H\u0002J\t\u0010\u00c3\u0001\u001a\u00020=H\u0002J\u0011\u0010\u00c4\u0001\u001a\u00020=2\u0006\u0010a\u001a\u00020\u000fH\u0002J\t\u0010\u00c5\u0001\u001a\u00020=H\u0002J\u0011\u0010\u00c6\u0001\u001a\u00020=2\u0006\u0010a\u001a\u00020\u000fH\u0002J\t\u0010\u00c7\u0001\u001a\u00020=H\u0002J\t\u0010\u00c8\u0001\u001a\u00020=H\u0002J\t\u0010\u00c9\u0001\u001a\u00020=H\u0002J\t\u0010\u00ca\u0001\u001a\u00020=H\u0002J\t\u0010\u00cb\u0001\u001a\u00020=H\u0002J\u0012\u0010\u00cc\u0001\u001a\u00020=2\u0007\u0010v\u001a\u00030\u00b9\u0001H\u0002J)\u0010\u00cd\u0001\u001a\u00020=2\n\u0010\u00ce\u0001\u001a\u0005\u0018\u00010\u00b9\u00012\u0012\b\u0002\u0010\u008a\u0001\u001a\u000b\u0012\u0004\u0012\u00020=\u0018\u00010\u008b\u0001H\u0002J\t\u0010\u00cf\u0001\u001a\u00020=H\u0002J\u0012\u0010\u00d0\u0001\u001a\u00020\u000f2\u0007\u0010\u00d1\u0001\u001a\u00020\u0013H\u0002J\u0012\u0010\u00d2\u0001\u001a\u00020\u000f2\u0007\u0010\u00d3\u0001\u001a\u00020\u0013H\u0002J\u0012\u0010\u00d4\u0001\u001a\u00020\u00132\u0007\u0010\u00c2\u0001\u001a\u00020\u000fH\u0002J\u001b\u0010\u00d5\u0001\u001a\u00020\u000f2\u0007\u0010\u00d6\u0001\u001a\u00020\u00132\u0007\u0010\u00d7\u0001\u001a\u00020\u0013H\u0002J\u0012\u0010\u00d8\u0001\u001a\u00020\u00132\u0007\u0010\u00c2\u0001\u001a\u00020\u000fH\u0002J\u0012\u0010\u00d9\u0001\u001a\u00020\u000f2\u0007\u0010\u00d6\u0001\u001a\u00020\u0013H\u0002J\u0012\u0010\u00da\u0001\u001a\u00020\u000f2\u0007\u0010\u00c2\u0001\u001a\u00020yH\u0002J7\u0010\u00db\u0001\u001a\u00020)2\u0007\u0010\u00c2\u0001\u001a\u00020\u000f2\b\u0010\u00dc\u0001\u001a\u00030\u00dd\u00012\u0007\u0010\u00de\u0001\u001a\u00020\u00052\u0007\u0010\u00df\u0001\u001a\u00020^2\u0007\u0010\u00e0\u0001\u001a\u00020\u0005H\u0002J\u0012\u0010\u00e1\u0001\u001a\u00020)2\u0007\u0010\u00c2\u0001\u001a\u00020\u000fH\u0002J#\u0010\u00e2\u0001\u001a\u00030\u00e3\u00012\u0007\u0010\u00c2\u0001\u001a\u00020\u000f2\u000e\u0010\u00e4\u0001\u001a\t\u0012\u0004\u0012\u00020=0\u008b\u0001H\u0002J\u0012\u0010\u00e5\u0001\u001a\u00020\u00052\u0007\u0010\u00c2\u0001\u001a\u00020\u0005H\u0002J\n\u0010\u00e6\u0001\u001a\u00030\u00e7\u0001H\u0002J\n\u0010\u00e8\u0001\u001a\u00030\u00e7\u0001H\u0002J\n\u0010\u00e9\u0001\u001a\u00030\u00ea\u0001H\u0002J\u001c\u0010\u00eb\u0001\u001a\u00030\u00ea\u00012\u0007\u0010\u00ec\u0001\u001a\u00020\u00052\u0007\u0010\u00ed\u0001\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R*\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000ej\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0010`\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010*\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010+R#\u0010,\u001a\n .*\u0004\u0018\u00010-0-8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b1\u00102\u001a\u0004\b/\u00100R\u000e\u00103\u001a\u000204X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000206X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020)X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020)X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020:X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020)X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u00f2\u0001"}, d2 = {"Lcom/hazem/dailystore/MainActivity;", "Landroid/app/Activity;", "<init>", "()V", "green", "", "gold", "backgroundColor", "textColor", "mutedColor", "products", "", "Lcom/hazem/dailystore/MainActivity$Product;", "cart", "Ljava/util/LinkedHashMap;", "", "Lcom/hazem/dailystore/MainActivity$CartLine;", "Lkotlin/collections/LinkedHashMap;", "exchangeRateMilli", "", "searchQuery", "pendingCheckoutDraftId", "currentUserId", "currentUserRole", "activeScreenKey", "activeScreenArgument", "productImportRequestCode", "productImportFolderRequestCode", "backupCreateRequestCode", "backupRestoreRequestCode", "statementExportRequestCode", "deviceSetupRequestCode", "pendingPairingShareText", "pendingStatementExportBytes", "", "pendingStatementExportName", "pendingBackupPassword", "", "backupProgressDialog", "Landroid/app/AlertDialog;", "backupProgressText", "Landroid/widget/TextView;", "backupPreviousOrientation", "Ljava/lang/Integer;", "sessionPrefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "getSessionPrefs", "()Landroid/content/SharedPreferences;", "sessionPrefs$delegate", "Lkotlin/Lazy;", "productGrid", "Landroid/widget/GridLayout;", "cartContainer", "Landroid/widget/LinearLayout;", "totalView", "countView", "searchField", "Landroid/widget/EditText;", "emptyProductsView", "onActivityResult", "", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "importProductFile", "uri", "Landroid/net/Uri;", "fileName", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "startBackupRestoreInBackground", "password", "actorUserId", "onDestroy", "setContentView", "view", "Landroid/view/View;", "assignStableViewIds", "next", "", "onResume", "onPause", "onSaveInstanceState", "outState", "onBackPressed", "persistSessionState", "serializeCart", "incomingPairingShare", "source", "openSyncDeviceManager", "restoreSession", "", "state", "isDraftSalesInvoice", "invoiceId", "restoreCart", "snapshot", "restoreActiveScreen", "markScreen", "key", "argument", "clearSessionState", "showInitialAdminSetupScreen", "showLoginScreen", "showForcedPasswordChange", "user", "Lcom/hazem/dailystore/data/AuthenticatedUser;", "currentPassword", "showPointOfSale", "preserveCart", "cartTotalUsdCents", "showCheckoutDialog", "loadCatalog", "renderProducts", "showQuantityDialog", "product", "addToCart", "quantity", "", "renderCart", "changeCartQuantity", "line", "delta", "showBackupRestoreOptions", "requestBackupPassword", "restore", "showNavigationMenu", "anchor", "showStoreTimeZoneSettings", "showPurchases", "paymentStatusArabic", NotificationCompat.CATEGORY_STATUS, "showEditPurchasePaymentDialog", "payment", "Lcom/hazem/dailystore/data/PurchasePaymentRecord;", "onSaved", "Lkotlin/Function0;", "showPurchasePaymentDialog", "invoiceNumber", "showSuppliers", "showSupplierDetails", "supplierId", "showCustomers", "exportPartyStatement", "statement", "Lcom/hazem/dailystore/data/PartyStatement;", "format", "showCustomerAccount", "customerId", "showCustomerPaymentDialog", "invoice", "Lcom/hazem/dailystore/data/CustomerInvoicePaymentSummary;", "showCustomerEditor", "isManager", "hasPermission", "permissionKey", "showUserManagement", "showCreateUserDialog", "showCashAccounts", "showDailyOperationsDialog", "showDailyCashReport", "showGeneralReports", "showServiceTransfers", "showServiceTransferDebtPaymentDialog", "transferId", "showServiceOpeningDialog", "showServiceRechargeDialog", "showServiceTransferDialog", "showEditCashAccountDialog", "account", "Lcom/hazem/dailystore/data/CashAccountSummary;", "showCreateCashAccountDialog", "cashAccountTypeArabic", "type", "formatAccountBalance", "amountMinor", "currency", "invoiceStatusArabic", "showPurchaseInvoiceEditor", "showPurchaseProductPicker", "onSelected", "Lkotlin/Function1;", "Lcom/hazem/dailystore/data/StoreProduct;", "showPurchaseLineDialog", "initialQuantity", "Ljava/math/BigDecimal;", "initialCost", "onSave", "Lkotlin/Function2;", "fieldForDialog", "hint", "value", "showSalesReturns", "showSalesReturnEditor", "showPurchaseReturns", "showPurchaseReturnEditor", "showExpenses", "showExpenseCategoryDialog", "showExpenseEditor", "showInventoryAdjustments", "showProductManagement", "showOpeningStockDialog", "showProductEditor", "existing", "showAddCategoryDialog", "moneyText", "cents", "formatSypAmount", "amount", "parseSypAmount", "formatDualMoney", "usdCents", "rateMilli", "parseUsdCents", "formatPrice", "formatQuantity", AnnotatedPrivateKey.LABEL, "size", "", "color", "bold", "gravityValue", "sectionHeading", "smallButton", "Landroid/widget/Button;", "action", "dp", "inputBackground", "Landroid/graphics/drawable/GradientDrawable;", "cardBackground", "matchWidth", "Landroid/widget/LinearLayout$LayoutParams;", "fieldParams", "height", "bottom", "Product", "CartLine", "PurchaseLineDraft", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MainActivity extends Activity {
    private static final String SESSION_CART = "pos_cart";
    private static final String SESSION_CHECKOUT_DRAFT = "checkout_draft_id";
    private static final String SESSION_SCREEN = "screen_key";
    private static final String SESSION_SCREEN_ARGUMENT = "screen_argument";
    private static final int STABLE_VIEW_ID_BASE = 2113929216;
    private static final String STATE_CART = "daily_shop.cart";
    private static final String STATE_CHECKOUT_DRAFT = "daily_shop.checkout_draft";
    private static final String STATE_SCREEN = "daily_shop.screen";
    private static final String STATE_SCREEN_ARGUMENT = "daily_shop.screen_argument";
    private static final String STATE_USER_ID = "daily_shop.user_id";
    public static final String TEST_PASSWORD = "1234";
    public static final String TEST_USERNAME = "admin";
    private String activeScreenArgument;
    private Integer backupPreviousOrientation;
    private AlertDialog backupProgressDialog;
    private TextView backupProgressText;
    private LinearLayout cartContainer;
    private TextView countView;
    private String currentUserId;
    private TextView emptyProductsView;
    private long exchangeRateMilli;
    private char[] pendingBackupPassword;
    private String pendingCheckoutDraftId;
    private String pendingPairingShareText;
    private byte[] pendingStatementExportBytes;
    private String pendingStatementExportName;
    private GridLayout productGrid;
    private EditText searchField;
    private TextView totalView;
    private final int green = Color.rgb(24, 75, 55);
    private final int gold = Color.rgb(190, 155, 85);
    private final int backgroundColor = Color.rgb(246, 248, 245);
    private final int textColor = Color.rgb(48, 58, 51);
    private final int mutedColor = Color.rgb(105, 115, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR);
    private final List<Product> products = new ArrayList();
    private final LinkedHashMap<String, CartLine> cart = new LinkedHashMap<>();
    private String searchQuery = "";
    private String currentUserRole = "CASHIER";
    private String activeScreenKey = "POS";
    private final int productImportRequestCode = 7101;
    private final int productImportFolderRequestCode = 7102;
    private final int backupCreateRequestCode = 7110;
    private final int backupRestoreRequestCode = 7111;
    private final int statementExportRequestCode = 7120;
    private final int deviceSetupRequestCode = 7130;

    /* JADX INFO: renamed from: sessionPrefs$delegate, reason: from kotlin metadata */
    private final Lazy sessionPrefs = LazyKt.lazy(new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda28
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return MainActivity.sessionPrefs_delegate$lambda$0(this.f$0);
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestBackupPassword$lambda$112(DialogInterface dialogInterface, int i) {
    }

    private final SharedPreferences getSessionPrefs() {
        return (SharedPreferences) this.sessionPrefs.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SharedPreferences sessionPrefs_delegate$lambda$0(MainActivity mainActivity) {
        return mainActivity.getSharedPreferences("the_daily_shop_session", 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0018\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\nH\u00c6\u0003J\t\u0010 \u001a\u00020\fH\u00c6\u0003JQ\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u00c6\u0001J\u0013\u0010\"\u001a\u00020\n2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020%H\u00d6\u0001J\t\u0010&\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006'"}, d2 = {"Lcom/hazem/dailystore/MainActivity$Product;", "", "id", "", "name", "barcode", "unit", "priceUsdCents", "", "tracksInventory", "", "stock", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JZD)V", "getId", "()Ljava/lang/String;", "getName", "getBarcode", "getUnit", "getPriceUsdCents", "()J", "getTracksInventory", "()Z", "getStock", "()D", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    static final /* data */ class Product {
        private final String barcode;
        private final String id;
        private final String name;
        private final long priceUsdCents;
        private final double stock;
        private final boolean tracksInventory;
        private final String unit;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getBarcode() {
            return this.barcode;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getUnit() {
            return this.unit;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final long getPriceUsdCents() {
            return this.priceUsdCents;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final boolean getTracksInventory() {
            return this.tracksInventory;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final double getStock() {
            return this.stock;
        }

        public final Product copy(String id, String name, String barcode, String unit, long priceUsdCents, boolean tracksInventory, double stock) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(unit, "unit");
            return new Product(id, name, barcode, unit, priceUsdCents, tracksInventory, stock);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Product)) {
                return false;
            }
            Product product = (Product) other;
            return Intrinsics.areEqual(this.id, product.id) && Intrinsics.areEqual(this.name, product.name) && Intrinsics.areEqual(this.barcode, product.barcode) && Intrinsics.areEqual(this.unit, product.unit) && this.priceUsdCents == product.priceUsdCents && this.tracksInventory == product.tracksInventory && Double.compare(this.stock, product.stock) == 0;
        }

        public int hashCode() {
            int iHashCode = ((this.id.hashCode() * 31) + this.name.hashCode()) * 31;
            String str = this.barcode;
            return ((((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.unit.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.priceUsdCents)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.tracksInventory)) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.stock);
        }

        public String toString() {
            return "Product(id=" + this.id + ", name=" + this.name + ", barcode=" + this.barcode + ", unit=" + this.unit + ", priceUsdCents=" + this.priceUsdCents + ", tracksInventory=" + this.tracksInventory + ", stock=" + this.stock + ")";
        }

        public Product(String id, String name, String str, String unit, long j, boolean z, double d) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(unit, "unit");
            this.id = id;
            this.name = name;
            this.barcode = str;
            this.unit = unit;
            this.priceUsdCents = j;
            this.tracksInventory = z;
            this.stock = d;
        }

        public final String getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }

        public final String getBarcode() {
            return this.barcode;
        }

        public final String getUnit() {
            return this.unit;
        }

        public final long getPriceUsdCents() {
            return this.priceUsdCents;
        }

        public final boolean getTracksInventory() {
            return this.tracksInventory;
        }

        public final double getStock() {
            return this.stock;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r\u00a8\u0006\u0018"}, d2 = {"Lcom/hazem/dailystore/MainActivity$CartLine;", "", "product", "Lcom/hazem/dailystore/MainActivity$Product;", "quantity", "", "<init>", "(Lcom/hazem/dailystore/MainActivity$Product;D)V", "getProduct", "()Lcom/hazem/dailystore/MainActivity$Product;", "getQuantity", "()D", "setQuantity", "(D)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    static final /* data */ class CartLine {
        private final Product product;
        private double quantity;

        public static /* synthetic */ CartLine copy$default(CartLine cartLine, Product product, double d, int i, Object obj) {
            if ((i & 1) != 0) {
                product = cartLine.product;
            }
            if ((i & 2) != 0) {
                d = cartLine.quantity;
            }
            return cartLine.copy(product, d);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Product getProduct() {
            return this.product;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final double getQuantity() {
            return this.quantity;
        }

        public final CartLine copy(Product product, double quantity) {
            Intrinsics.checkNotNullParameter(product, "product");
            return new CartLine(product, quantity);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CartLine)) {
                return false;
            }
            CartLine cartLine = (CartLine) other;
            return Intrinsics.areEqual(this.product, cartLine.product) && Double.compare(this.quantity, cartLine.quantity) == 0;
        }

        public int hashCode() {
            return (this.product.hashCode() * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.quantity);
        }

        public String toString() {
            return "CartLine(product=" + this.product + ", quantity=" + this.quantity + ")";
        }

        public CartLine(Product product, double d) {
            Intrinsics.checkNotNullParameter(product, "product");
            this.product = product;
            this.quantity = d;
        }

        public final Product getProduct() {
            return this.product;
        }

        public final double getQuantity() {
            return this.quantity;
        }

        public final void setQuantity(double d) {
            this.quantity = d;
        }
    }

    /* JADX WARN: Code duplicated, block: B:132:0x0280  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.app.Activity
    @Deprecated(message = "Using the document picker keeps compatibility with the app's current Activity-based navigation.")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) throws IOException {
        Object objM381constructorimpl;
        Uri data2;
        String string;
        Uri data3;
        super.onActivityResult(requestCode, resultCode, data);
        int i = 2;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        if (requestCode == this.deviceSetupRequestCode) {
            try {
                Result.Companion companion = Result.INSTANCE;
                MainActivity mainActivity = this;
                objM381constructorimpl = Result.m381constructorimpl(new SyncProtocolRepository(this, objArr2 == true ? 1 : 0, i, objArr == true ? 1 : 0).localDeviceRole());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m387isFailureimpl(objM381constructorimpl)) {
                objM381constructorimpl = null;
            }
            if (objM381constructorimpl != null && restoreSession(null)) {
                new ServiceTransferRepository(this).ensureSystemAccounts();
                showPointOfSale$default(this, false, 1, null);
                return;
            }
            clearSessionState();
            if (new AuthRepository(this).needsInitialAdminSetup()) {
                showInitialAdminSetupScreen();
                return;
            } else {
                showLoginScreen();
                return;
            }
        }
        if (requestCode == this.statementExportRequestCode) {
            byte[] bArr = this.pendingStatementExportBytes;
            String str = this.pendingStatementExportName;
            this.pendingStatementExportBytes = null;
            this.pendingStatementExportName = null;
            Uri data4 = data != null ? data.getData() : null;
            if (resultCode != -1 || data4 == null || bArr == null || str == null) {
                return;
            }
            try {
                OutputStream outputStreamOpenOutputStream = getContentResolver().openOutputStream(data4, "w");
                if (outputStreamOpenOutputStream == null) {
                    throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0641\u062a\u062d \u0645\u0644\u0641 \u0627\u0644\u062a\u0635\u062f\u064a\u0631 \u0644\u0644\u062d\u0641\u0638".toString());
                }
                OutputStream outputStream = outputStreamOpenOutputStream;
                try {
                    outputStream.write(bArr);
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(outputStream, null);
                    Toast.makeText(this, "\u062a\u0645 \u062a\u0635\u062f\u064a\u0631 \u0643\u0634\u0641 \u0627\u0644\u062d\u0633\u0627\u0628 \u0628\u0646\u062c\u0627\u062d: " + str, 1).show();
                    return;
                } catch (Throwable th2) {
                    try {
                        throw th2;
                    } catch (Throwable th3) {
                        CloseableKt.closeFinally(outputStream, th2);
                        throw th3;
                    }
                }
            } catch (Exception e) {
                AlertDialog.Builder title = new AlertDialog.Builder(this).setTitle("\u062a\u0639\u0630\u0631 \u062a\u0635\u062f\u064a\u0631 \u0643\u0634\u0641 \u0627\u0644\u062d\u0633\u0627\u0628");
                String message = e.getMessage();
                if (message == null) {
                    message = "\u062d\u062f\u062b \u062e\u0637\u0623 \u0623\u062b\u0646\u0627\u0621 \u062d\u0641\u0638 \u0627\u0644\u0645\u0644\u0641";
                }
                title.setMessage(message).setPositiveButton("\u062d\u0633\u0646\u0627\u064b", (DialogInterface.OnClickListener) null).show();
                return;
            }
        }
        if (requestCode == this.backupCreateRequestCode || requestCode == this.backupRestoreRequestCode) {
            if (!Intrinsics.areEqual(this.currentUserRole, "ADMIN")) {
                char[] cArr = this.pendingBackupPassword;
                if (cArr != null) {
                    ArraysKt.fill$default(cArr, (char) 0, 0, 0, 6, (Object) null);
                }
                this.pendingBackupPassword = null;
                Toast.makeText(this, "\u0627\u0644\u0646\u0633\u062e \u0627\u0644\u0627\u062d\u062a\u064a\u0627\u0637\u064a \u0648\u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0645\u062a\u0627\u062d\u0627\u0646 \u0644\u0644\u0645\u062f\u064a\u0631 \u0641\u0642\u0637", 1).show();
                return;
            }
            char[] cArr2 = this.pendingBackupPassword;
            this.pendingBackupPassword = null;
            Uri data5 = data != null ? data.getData() : null;
            if (resultCode != -1 || data5 == null || cArr2 == null) {
                if (cArr2 != null) {
                    ArraysKt.fill$default(cArr2, (char) 0, 0, 0, 6, (Object) null);
                    return;
                }
                return;
            }
            String str2 = this.currentUserId;
            String str3 = str2;
            if (str3 == null || StringsKt.isBlank(str3)) {
                ArraysKt.fill$default(cArr2, (char) 0, 0, 0, 6, (Object) null);
                Toast.makeText(this, "\u0627\u0646\u062a\u0647\u062a \u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u062f\u064a\u0631\u061b \u0633\u062c\u0651\u0644 \u0627\u0644\u062f\u062e\u0648\u0644 \u0645\u062c\u062f\u062f\u0627\u064b", 1).show();
                return;
            } else {
                startBackupRestoreInBackground(requestCode, data5, cArr2, str2);
                return;
            }
        }
        if (requestCode != this.productImportFolderRequestCode) {
            if (requestCode != this.productImportRequestCode || resultCode != -1 || data == null || (data2 = data.getData()) == null) {
                return;
            }
            Cursor cursorQuery = getContentResolver().query(data2, new String[]{"_display_name"}, null, null, null);
            if (cursorQuery != null) {
                Cursor cursor = cursorQuery;
                try {
                    Cursor cursor2 = cursor;
                    string = cursor2.moveToFirst() ? cursor2.getString(0) : null;
                    CloseableKt.closeFinally(cursor, null);
                    if (string == null) {
                        string = "products.csv";
                    }
                } catch (Throwable th4) {
                    try {
                        throw th4;
                    } catch (Throwable th5) {
                        CloseableKt.closeFinally(cursor, th4);
                        throw th5;
                    }
                }
            } else {
                string = "products.csv";
            }
            importProductFile(data2, string);
            return;
        }
        if (resultCode != -1 || data == null || (data3 = data.getData()) == null) {
            return;
        }
        try {
            Uri uriBuildChildDocumentsUriUsingTree = DocumentsContract.buildChildDocumentsUriUsingTree(data3, DocumentsContract.getTreeDocumentId(data3));
            final ArrayList arrayList = new ArrayList();
            Cursor cursorQuery2 = getContentResolver().query(uriBuildChildDocumentsUriUsingTree, new String[]{"document_id", "_display_name"}, null, null, null);
            if (cursorQuery2 == null) {
                throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0642\u0631\u0627\u0621\u0629 \u0645\u062d\u062a\u0648\u064a\u0627\u062a \u0627\u0644\u0645\u062c\u0644\u062f \u0627\u0644\u0645\u062d\u062f\u062f".toString());
            }
            Cursor cursor3 = cursorQuery2;
            try {
                Cursor cursor4 = cursor3;
                int columnIndexOrThrow = cursor4.getColumnIndexOrThrow("document_id");
                int columnIndexOrThrow2 = cursor4.getColumnIndexOrThrow("_display_name");
                while (cursor4.moveToNext()) {
                    String string2 = cursor4.getString(columnIndexOrThrow2);
                    if (string2 != null) {
                        Locale ROOT = Locale.ROOT;
                        Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
                        String lowerCase = string2.toLowerCase(ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                        if (StringsKt.endsWith$default(lowerCase, ".csv", false, 2, (Object) null) || StringsKt.endsWith$default(lowerCase, ".xlsx", false, 2, (Object) null)) {
                            String string3 = cursor4.getString(columnIndexOrThrow);
                            if (string3 != null) {
                                arrayList.add(TuplesKt.to(DocumentsContract.buildDocumentUriUsingTree(data3, string3), string2));
                            }
                        }
                    }
                }
                Unit unit2 = Unit.INSTANCE;
                CloseableKt.closeFinally(cursor3, null);
                if (arrayList.isEmpty()) {
                    throw new IllegalStateException("\u0644\u0645 \u0623\u062c\u062f \u0645\u0644\u0641\u0627\u064b \u0628\u0627\u0645\u062a\u062f\u0627\u062f CSV \u0623\u0648 XLSX \u062f\u0627\u062e\u0644 \u0647\u0630\u0627 \u0627\u0644\u0645\u062c\u0644\u062f. \u0627\u062e\u062a\u0631 \u0627\u0644\u0645\u062c\u0644\u062f \u0627\u0644\u0630\u064a \u064a\u062d\u062a\u0648\u064a \u0645\u0644\u0641 \u0627\u0644\u0645\u0646\u062a\u062c\u0627\u062a \u0645\u0628\u0627\u0634\u0631\u0629.".toString());
                }
                ArrayList arrayList2 = arrayList;
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    arrayList3.add((String) ((Pair) it.next()).getSecond());
                }
                new AlertDialog.Builder(this).setTitle("\u0627\u062e\u062a\u0631 \u0645\u0644\u0641 \u0627\u0644\u0645\u0646\u062a\u062c\u0627\u062a").setItems((String[]) arrayList3.toArray(new String[0]), new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda30
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i2) {
                        MainActivity.onActivityResult$lambda$5(arrayList, this, dialogInterface, i2);
                    }
                }).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).show();
            } catch (Throwable th6) {
                try {
                    throw th6;
                } catch (Throwable th7) {
                    CloseableKt.closeFinally(cursor3, th6);
                    throw th7;
                }
            }
        } catch (Exception e2) {
            AlertDialog.Builder title2 = new AlertDialog.Builder(this).setTitle("\u062a\u0639\u0630\u0631 \u0627\u0633\u062a\u0639\u0631\u0627\u0636 \u0627\u0644\u0645\u062c\u0644\u062f");
            String message2 = e2.getMessage();
            if (message2 == null) {
                message2 = "\u0644\u0645 \u0623\u062a\u0645\u0643\u0646 \u0645\u0646 \u0642\u0631\u0627\u0621\u0629 \u0627\u0644\u0645\u0644\u0641\u0627\u062a \u062f\u0627\u062e\u0644 \u0627\u0644\u0645\u062c\u0644\u062f \u0627\u0644\u0645\u062d\u062f\u062f.";
            }
            title2.setMessage(message2).setPositiveButton("\u062d\u0633\u0646\u0627\u064b", (DialogInterface.OnClickListener) null).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onActivityResult$lambda$5(List list, MainActivity mainActivity, DialogInterface dialogInterface, int i) {
        Pair pair = (Pair) list.get(i);
        mainActivity.importProductFile((Uri) pair.getFirst(), (String) pair.getSecond());
    }

    private final void importProductFile(Uri uri, String fileName) {
        String str;
        try {
            InputStream inputStreamOpenInputStream = getContentResolver().openInputStream(uri);
            if (inputStreamOpenInputStream != null) {
                InputStream inputStream = inputStreamOpenInputStream;
                try {
                    final List<ProductImportRow> list = SpreadsheetProductImporter.INSTANCE.parse(fileName, inputStream);
                    CloseableKt.closeFinally(inputStream, null);
                    if (list != null) {
                        final StoreRepository storeRepository = new StoreRepository(this);
                        final long currentExchangeRateMilli = storeRepository.getCurrentExchangeRateMilli();
                        List<ProductImportRow> list2 = list;
                        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                            Iterator<T> it = list2.iterator();
                            while (it.hasNext()) {
                                if (((ProductImportRow) it.next()).getUsesSypPrices()) {
                                    if (currentExchangeRateMilli <= 0) {
                                        throw new IllegalStateException("\u0627\u0644\u0645\u0644\u0641 \u064a\u062d\u062a\u0648\u064a \u0623\u0633\u0639\u0627\u0631\u0627\u064b \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629. \u0633\u062c\u0651\u0644 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0627\u0644\u062d\u0627\u0644\u064a \u0645\u0646 \u0634\u0627\u0634\u0629 \u0625\u062f\u0627\u0631\u0629 \u0627\u0644\u0645\u0646\u062a\u062c\u0627\u062a \u062b\u0645 \u0623\u0639\u062f \u0627\u0644\u0627\u0633\u062a\u064a\u0631\u0627\u062f\u061b \u0644\u0646 \u0623\u062e\u0645\u0651\u0646 \u0633\u0639\u0631 \u0627\u0644\u062a\u062d\u0648\u064a\u0644.".toString());
                                    }
                                    break;
                                }
                            }
                        }
                        String strJoinToString$default = CollectionsKt.joinToString$default(CollectionsKt.take(list, 5), "\n", null, null, 0, null, new Function1() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda156
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return MainActivity.importProductFile$lambda$9((ProductImportRow) obj);
                            }
                        }, 30, null);
                        List<ProductImportRow> list3 = list;
                        if (!(list3 instanceof Collection) || !list3.isEmpty()) {
                            Iterator<T> it2 = list3.iterator();
                            while (it2.hasNext()) {
                                if (((ProductImportRow) it2.next()).getUsesSypPrices()) {
                                    str = "\u0633\u064a\u064f\u062d\u0648\u0651\u0644 \u0633\u0639\u0631 \u0627\u0644\u0634\u0631\u0627\u0621 \u0648\u0627\u0644\u0628\u064a\u0639 \u0645\u0646 \u0627\u0644\u0644\u064a\u0631\u0629 \u0625\u0644\u0649 \u0645\u0631\u062c\u0639 \u0627\u0644\u062f\u0648\u0644\u0627\u0631 \u0628\u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0627\u0644\u0645\u0633\u062c\u0644 \u062d\u0627\u0644\u064a\u0627\u064b: " + MainActivity$$ExternalSyntheticBackport0.m(BigDecimal.valueOf(currentExchangeRateMilli, 3)).toPlainString() + " \u0644.\u0633 \u0644\u0643\u0644 \u062f\u0648\u0644\u0627\u0631.";
                                    new AlertDialog.Builder(this).setTitle("\u0645\u0631\u0627\u062c\u0639\u0629 \u0627\u0633\u062a\u064a\u0631\u0627\u062f \u0627\u0644\u0623\u0635\u0646\u0627\u0641").setMessage("\u0639\u062f\u062f \u0627\u0644\u0623\u0635\u0646\u0627\u0641: " + list.size() + "\n\u0627\u0644\u0645\u0639\u0627\u064a\u0646\u0629:\n" + strJoinToString$default + "\n\n" + str + "\n\n\u0633\u064a\u062a\u0645 \u062d\u0641\u0638 \u062c\u0645\u064a\u0639 \u0627\u0644\u0623\u0635\u0646\u0627\u0641 \u0641\u064a \u0639\u0645\u0644\u064a\u0629 \u0648\u0627\u062d\u062f\u0629 \u0623\u0648 \u0625\u0644\u063a\u0627\u0621 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0643\u0627\u0645\u0644\u0629 \u0639\u0646\u062f \u0648\u062c\u0648\u062f \u062e\u0637\u0623. \u0633\u064a\u0628\u0642\u0649 \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u0635\u0641\u0631\u0627\u064b \u0648\u0627\u0644\u0628\u0627\u0631\u0643\u0648\u062f \u0641\u0627\u0631\u063a\u0627\u064b \u0625\u0630\u0627 \u0644\u0645 \u064a\u0631\u062f \u0641\u064a \u0627\u0644\u0645\u0644\u0641.").setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0627\u0633\u062a\u064a\u0631\u0627\u062f", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda157
                                        @Override // android.content.DialogInterface.OnClickListener
                                        public final void onClick(DialogInterface dialogInterface, int i) {
                                            MainActivity.importProductFile$lambda$11(storeRepository, list, currentExchangeRateMilli, this, dialogInterface, i);
                                        }
                                    }).show();
                                    return;
                                }
                            }
                        }
                        str = "\u0627\u0644\u0623\u0633\u0639\u0627\u0631 \u0645\u062f\u062e\u0644\u0629 \u0628\u0627\u0644\u062f\u0648\u0644\u0627\u0631 \u0648\u0633\u062a\u064f\u062d\u0641\u0638 \u0643\u0645\u0627 \u0647\u064a \u062f\u0648\u0646 \u062a\u062d\u0648\u064a\u0644.";
                        new AlertDialog.Builder(this).setTitle("\u0645\u0631\u0627\u062c\u0639\u0629 \u0627\u0633\u062a\u064a\u0631\u0627\u062f \u0627\u0644\u0623\u0635\u0646\u0627\u0641").setMessage("\u0639\u062f\u062f \u0627\u0644\u0623\u0635\u0646\u0627\u0641: " + list.size() + "\n\u0627\u0644\u0645\u0639\u0627\u064a\u0646\u0629:\n" + strJoinToString$default + "\n\n" + str + "\n\n\u0633\u064a\u062a\u0645 \u062d\u0641\u0638 \u062c\u0645\u064a\u0639 \u0627\u0644\u0623\u0635\u0646\u0627\u0641 \u0641\u064a \u0639\u0645\u0644\u064a\u0629 \u0648\u0627\u062d\u062f\u0629 \u0623\u0648 \u0625\u0644\u063a\u0627\u0621 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0643\u0627\u0645\u0644\u0629 \u0639\u0646\u062f \u0648\u062c\u0648\u062f \u062e\u0637\u0623. \u0633\u064a\u0628\u0642\u0649 \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u0635\u0641\u0631\u0627\u064b \u0648\u0627\u0644\u0628\u0627\u0631\u0643\u0648\u062f \u0641\u0627\u0631\u063a\u0627\u064b \u0625\u0630\u0627 \u0644\u0645 \u064a\u0631\u062f \u0641\u064a \u0627\u0644\u0645\u0644\u0641.").setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0627\u0633\u062a\u064a\u0631\u0627\u062f", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda157
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i) {
                                MainActivity.importProductFile$lambda$11(storeRepository, list, currentExchangeRateMilli, this, dialogInterface, i);
                            }
                        }).show();
                        return;
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(inputStream, th);
                        throw th2;
                    }
                }
            }
            throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0641\u062a\u062d \u0627\u0644\u0645\u0644\u0641 \u0627\u0644\u0645\u062d\u062f\u062f".toString());
        } catch (Exception e) {
            AlertDialog.Builder title = new AlertDialog.Builder(this).setTitle("\u062a\u0639\u0630\u0631 \u0642\u0631\u0627\u0621\u0629 \u0627\u0644\u0645\u0644\u0641");
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0623\u0643\u062f \u0645\u0646 \u0623\u0646 \u0627\u0644\u0645\u0644\u0641 \u0628\u0635\u064a\u063a\u0629 CSV \u0623\u0648 XLSX \u0648\u0623\u0646 \u0627\u0644\u0623\u0639\u0645\u062f\u0629 \u0635\u062d\u064a\u062d\u0629.";
            }
            title.setMessage(message).setPositiveButton("\u062d\u0633\u0646\u0627\u064b", (DialogInterface.OnClickListener) null).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence importProductFile$lambda$9(ProductImportRow it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return "\u2022 " + it.getName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void importProductFile$lambda$11(StoreRepository storeRepository, List list, long j, MainActivity mainActivity, DialogInterface dialogInterface, int i) {
        try {
            String str = mainActivity.currentUserId;
            if (str == null) {
                throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
            }
            Toast.makeText(mainActivity, "\u062a\u0645 \u0627\u0633\u062a\u064a\u0631\u0627\u062f " + storeRepository.importProducts(list, j, str) + " \u0635\u0646\u0641\u0627\u064b \u0628\u0646\u062c\u0627\u062d. \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u0627\u0644\u0627\u0641\u062a\u062a\u0627\u062d\u064a \u0628\u0642\u064a \u0635\u0641\u0631\u0627\u064b \u0628\u0627\u0646\u062a\u0638\u0627\u0631 \u0627\u0644\u062c\u0631\u062f.", 1).show();
            mainActivity.showProductManagement();
        } catch (Exception e) {
            AlertDialog.Builder title = new AlertDialog.Builder(mainActivity).setTitle("\u0644\u0645 \u064a\u062a\u0645 \u0627\u0644\u0627\u0633\u062a\u064a\u0631\u0627\u062f");
            String message = e.getMessage();
            if (message == null) {
                message = "\u062d\u062f\u062b \u062e\u0637\u0623 \u0623\u062b\u0646\u0627\u0621 \u0627\u0644\u0627\u0633\u062a\u064a\u0631\u0627\u062f\u061b \u0644\u0645 \u064a\u062a\u0645 \u062d\u0641\u0638 \u0623\u064a \u0635\u0646\u0641.";
            }
            title.setMessage(message).setPositiveButton("\u062d\u0633\u0646\u0627\u064b", (DialogInterface.OnClickListener) null).show();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws IOException {
        Object objM381constructorimpl;
        String str;
        String str2;
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(this.green);
        getWindow().setNavigationBarColor(this.green);
        getWindow().setSoftInputMode(16);
        getWindow().getDecorView().setLayoutDirection(1);
        this.pendingPairingShareText = incomingPairingShare(getIntent());
        MainActivity mainActivity = this;
        new StoreRepository(mainActivity).ensureBaseSetup();
        AuthRepository authRepository = new AuthRepository(mainActivity);
        if (authRepository.needsInitialAdminSetup() && (str2 = this.pendingPairingShareText) != null && !StringsKt.isBlank(str2)) {
            clearSessionState();
            openSyncDeviceManager("");
            return;
        }
        if (authRepository.needsInitialAdminSetup()) {
            clearSessionState();
            showInitialAdminSetupScreen();
            return;
        }
        new ServiceTransferRepository(mainActivity).ensureSystemAccounts();
        if (restoreSession(savedInstanceState)) {
            Object[] objArr = 0;
            Object[] objArr2 = 0;
            try {
                Result.Companion companion = Result.INSTANCE;
                MainActivity mainActivity2 = this;
                objM381constructorimpl = Result.m381constructorimpl(new SyncProtocolRepository(this, objArr2 == true ? 1 : 0, 2, objArr == true ? 1 : 0).localDeviceRole());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
            }
            if (((String) (Result.m387isFailureimpl(objM381constructorimpl) ? null : objM381constructorimpl)) == null || ((str = this.pendingPairingShareText) != null && !StringsKt.isBlank(str))) {
                String str3 = this.currentUserId;
                if (str3 != null) {
                    openSyncDeviceManager(str3);
                    return;
                } else {
                    showLoginScreen();
                    return;
                }
            }
            restoreActiveScreen();
            return;
        }
        clearSessionState();
        showLoginScreen();
    }

    private final void startBackupRestoreInBackground(int requestCode, final Uri uri, final char[] password, final String actorUserId) {
        if (!BackupRestoreWorker.INSTANCE.getRunning().compareAndSet(false, true)) {
            ArraysKt.fill$default(password, (char) 0, 0, 0, 6, (Object) null);
            Toast.makeText(this, "\u062a\u0648\u062c\u062f \u0639\u0645\u0644\u064a\u0629 \u0646\u0633\u062e \u0623\u0648 \u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0642\u064a\u062f \u0627\u0644\u062a\u0646\u0641\u064a\u0630 \u0628\u0627\u0644\u0641\u0639\u0644", 1).show();
            return;
        }
        final boolean z = requestCode == this.backupRestoreRequestCode;
        MainActivity mainActivity = this;
        TextView textView = new TextView(mainActivity);
        textView.setText(z ? "\u062a\u0647\u064a\u0626\u0629 \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629..." : "\u062a\u0647\u064a\u0626\u0629 \u0627\u0644\u0646\u0633\u062e \u0627\u0644\u0627\u062d\u062a\u064a\u0627\u0637\u064a...");
        textView.setTextSize(14.0f);
        textView.setTextColor(this.textColor);
        textView.setGravity(17);
        textView.setPadding(dp(12), dp(12), dp(12), dp(8));
        ProgressBar progressBar = new ProgressBar(mainActivity);
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        linearLayout.setPadding(dp(22), dp(18), dp(22), dp(18));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dp(44), dp(44));
        layoutParams.gravity = 17;
        Unit unit = Unit.INSTANCE;
        linearLayout.addView(progressBar, layoutParams);
        linearLayout.addView(textView, matchWidth());
        this.backupProgressDialog = new AlertDialog.Builder(mainActivity).setTitle(z ? "\u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a" : "\u0625\u0646\u0634\u0627\u0621 \u0646\u0633\u062e\u0629 \u0627\u062d\u062a\u064a\u0627\u0637\u064a\u0629").setView(linearLayout).setCancelable(false).create();
        this.backupProgressText = textView;
        this.backupPreviousOrientation = Integer.valueOf(getRequestedOrientation());
        setRequestedOrientation(14);
        AlertDialog alertDialog = this.backupProgressDialog;
        if (alertDialog != null) {
            alertDialog.show();
        }
        final Context applicationContext = getApplicationContext();
        final ContentResolver contentResolver = getContentResolver();
        final WeakReference weakReference = new WeakReference(this);
        final Handler handler = new Handler(Looper.getMainLooper());
        final Function1 function1 = new Function1() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda158
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.startBackupRestoreInBackground$lambda$18(handler, weakReference, (String) obj);
            }
        };
        BackupRestoreWorker.INSTANCE.getExecutor().execute(new Runnable() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda159
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.startBackupRestoreInBackground$lambda$25(this.f$0, password, handler, applicationContext, z, contentResolver, uri, actorUserId, function1, weakReference);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit startBackupRestoreInBackground$lambda$18(Handler handler, final WeakReference weakReference, final String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        handler.post(new Runnable() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda111
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.startBackupRestoreInBackground$lambda$18$lambda$17(weakReference, message);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startBackupRestoreInBackground$lambda$18$lambda$17(WeakReference weakReference, String str) {
        TextView textView;
        MainActivity mainActivity = (MainActivity) weakReference.get();
        if (mainActivity == null || mainActivity.isFinishing() || mainActivity.isDestroyed() || (textView = mainActivity.backupProgressText) == null) {
            return;
        }
        textView.setText(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startBackupRestoreInBackground$lambda$25(MainActivity mainActivity, char[] cArr, Handler handler, final Context context, final boolean z, ContentResolver contentResolver, Uri uri, String str, Function1 function1, final WeakReference weakReference) {
        final Object objM381constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            Intrinsics.checkNotNull(context);
            BackupRestoreManager backupRestoreManager = new BackupRestoreManager(context);
            if (z) {
                InputStream inputStreamOpenInputStream = contentResolver.openInputStream(uri);
                if (inputStreamOpenInputStream == null) {
                    throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0641\u062a\u062d \u0645\u0644\u0641 \u0627\u0644\u0646\u0633\u062e\u0629 \u0627\u0644\u0645\u062d\u062f\u062f".toString());
                }
                InputStream inputStream = inputStreamOpenInputStream;
                try {
                    backupRestoreManager.restoreBackup(str, cArr, inputStream, function1);
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(inputStream, null);
                    SecureSessionStore.INSTANCE.clear(context);
                    if (!context.getSharedPreferences("the_daily_shop_session", 0).edit().clear().commit()) {
                        throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0625\u0646\u0647\u0627\u0621 \u0627\u0644\u062c\u0644\u0633\u0629 \u0628\u0639\u062f \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629\u061b \u0633\u062c\u0651\u0644 \u0627\u0644\u062f\u062e\u0648\u0644 \u0645\u062c\u062f\u062f\u0627\u064b \u064a\u062f\u0648\u064a\u0627\u064b".toString());
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(inputStream, th);
                        throw th2;
                    }
                }
            } else {
                OutputStream outputStreamOpenOutputStream = contentResolver.openOutputStream(uri, "w");
                if (outputStreamOpenOutputStream == null) {
                    throw new IllegalStateException("\u062a\u0639\u0630\u0631 \u0641\u062a\u062d \u0645\u0644\u0641 \u0627\u0644\u0646\u0633\u062e\u0629 \u0644\u0644\u062d\u0641\u0638".toString());
                }
                OutputStream outputStream = outputStreamOpenOutputStream;
                try {
                    backupRestoreManager.createBackup(str, cArr, outputStream, function1);
                    Unit unit2 = Unit.INSTANCE;
                    CloseableKt.closeFinally(outputStream, null);
                } catch (Throwable th3) {
                    try {
                        throw th3;
                    } catch (Throwable th4) {
                        CloseableKt.closeFinally(outputStream, th3);
                        throw th4;
                    }
                }
            }
            objM381constructorimpl = Result.m381constructorimpl(Unit.INSTANCE);
        } catch (Throwable th5) {
            Result.Companion companion2 = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th5));
        }
        ArraysKt.fill$default(cArr, (char) 0, 0, 0, 6, (Object) null);
        BackupRestoreWorker.INSTANCE.getRunning().set(false);
        handler.post(new Runnable() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda94
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.startBackupRestoreInBackground$lambda$25$lambda$24(weakReference, objM381constructorimpl, z, context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startBackupRestoreInBackground$lambda$25$lambda$24(WeakReference weakReference, Object obj, boolean z, Context context) {
        String message;
        String message2;
        MainActivity mainActivity = (MainActivity) weakReference.get();
        if (mainActivity == null || mainActivity.isFinishing() || mainActivity.isDestroyed()) {
            if (!Result.m388isSuccessimpl(obj)) {
                Throwable thM384exceptionOrNullimpl = Result.m384exceptionOrNullimpl(obj);
                if (thM384exceptionOrNullimpl == null || (message = thM384exceptionOrNullimpl.getMessage()) == null) {
                    message = "\u062a\u0639\u0630\u0631\u062a \u0639\u0645\u0644\u064a\u0629 \u0627\u0644\u0646\u0633\u062e \u0623\u0648 \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629";
                }
            } else if (z) {
                message = "\u0627\u0643\u062a\u0645\u0644\u062a \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629\u061b \u0627\u0641\u062a\u062d \u0627\u0644\u062a\u0637\u0628\u064a\u0642 \u0648\u0633\u062c\u0651\u0644 \u0627\u0644\u062f\u062e\u0648\u0644 \u0645\u062c\u062f\u062f\u0627\u064b";
            } else {
                message = "\u0627\u0643\u062a\u0645\u0644 \u0625\u0646\u0634\u0627\u0621 \u0627\u0644\u0646\u0633\u062e\u0629 \u0627\u0644\u0627\u062d\u062a\u064a\u0627\u0637\u064a\u0629";
            }
            Toast.makeText(context, message, 1).show();
            return;
        }
        AlertDialog alertDialog = mainActivity.backupProgressDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        mainActivity.backupProgressDialog = null;
        mainActivity.backupProgressText = null;
        Integer num = mainActivity.backupPreviousOrientation;
        if (num != null) {
            mainActivity.setRequestedOrientation(num.intValue());
        }
        mainActivity.backupPreviousOrientation = null;
        if (!Result.m388isSuccessimpl(obj)) {
            AlertDialog.Builder title = new AlertDialog.Builder(mainActivity).setTitle(z ? "\u0644\u0645 \u062a\u062a\u0645 \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629" : "\u062a\u0639\u0630\u0631 \u0625\u0646\u0634\u0627\u0621 \u0627\u0644\u0646\u0633\u062e\u0629 \u0627\u0644\u0627\u062d\u062a\u064a\u0627\u0637\u064a\u0629");
            Throwable thM384exceptionOrNullimpl2 = Result.m384exceptionOrNullimpl(obj);
            if (thM384exceptionOrNullimpl2 == null || (message2 = thM384exceptionOrNullimpl2.getMessage()) == null) {
                message2 = "\u062d\u062f\u062b \u062e\u0637\u0623 \u063a\u064a\u0631 \u0645\u062a\u0648\u0642\u0639. \u0644\u0645 \u064a\u062a\u0645 \u0627\u0639\u062a\u0645\u0627\u062f \u0628\u064a\u0627\u0646\u0627\u062a \u063a\u064a\u0631 \u0645\u062a\u062d\u0642\u0642 \u0645\u0646\u0647\u0627.";
            }
            title.setMessage(message2).setPositiveButton("\u062d\u0633\u0646\u0627\u064b", (DialogInterface.OnClickListener) null).show();
            return;
        }
        if (z) {
            mainActivity.clearSessionState();
            mainActivity.currentUserId = null;
            mainActivity.cart.clear();
            mainActivity.showLoginScreen();
            Toast.makeText(mainActivity, "\u062a\u0645\u062a \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0648\u0627\u0644\u062a\u062d\u0642\u0642 \u0645\u0646 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a. \u0633\u062c\u0651\u0644 \u0627\u0644\u062f\u062e\u0648\u0644 \u0645\u062c\u062f\u062f\u0627\u064b.", 1).show();
            return;
        }
        Toast.makeText(mainActivity, "\u062a\u0645 \u0625\u0646\u0634\u0627\u0621 \u0646\u0633\u062e\u0629 \u0627\u062d\u062a\u064a\u0627\u0637\u064a\u0629 \u0645\u0634\u0641\u0651\u0631\u0629 \u0628\u0646\u062c\u0627\u062d", 1).show();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        AlertDialog alertDialog = this.backupProgressDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        this.backupProgressDialog = null;
        this.backupProgressText = null;
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        assignStableViewIds(view, new int[]{1});
        super.setContentView(view);
    }

    private final void assignStableViewIds(View view, int[] next) {
        int i = next[0];
        next[0] = i + 1;
        view.setId(i + STABLE_VIEW_ID_BASE);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewGroup.getChildAt(i2);
                Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(...)");
                assignStableViewIds(childAt, next);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        String str = this.currentUserId;
        if (str == null || StringsKt.isBlank(str) || !SyncForegroundService.INSTANCE.shouldRun$app_release(this)) {
            return;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            MainActivity mainActivity = this;
            Context applicationContext = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            if (new SyncProtocolRepository(applicationContext, null, 2, 0 == true ? 1 : 0).localDeviceRole() != null) {
                SyncForegroundService.INSTANCE.start(this);
            }
            Result.m381constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m381constructorimpl(ResultKt.createFailure(th));
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        persistSessionState();
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        persistSessionState();
        outState.putString(STATE_SCREEN, this.activeScreenKey);
        outState.putString(STATE_SCREEN_ARGUMENT, this.activeScreenArgument);
        outState.putString(STATE_CART, serializeCart());
        outState.putString(STATE_CHECKOUT_DRAFT, this.pendingCheckoutDraftId);
        super.onSaveInstanceState(outState);
    }

    @Override // android.app.Activity
    @Deprecated(message = "Deprecated in Android API; retained for compatibility with the current Activity navigation")
    public void onBackPressed() {
        String str = this.currentUserId;
        if (str == null || StringsKt.isBlank(str)) {
            super.onBackPressed();
        } else {
            new AlertDialog.Builder(this).setTitle("\u0625\u063a\u0644\u0627\u0642 \u0627\u0644\u062a\u0637\u0628\u064a\u0642").setMessage("\u0633\u064a\u062a\u0645 \u0625\u063a\u0644\u0627\u0642 \u0627\u0644\u062a\u0637\u0628\u064a\u0642 \u0641\u0642\u0637. \u0639\u0646\u062f \u0641\u062a\u062d\u0647 \u0645\u062c\u062f\u062f\u0627\u064b \u0633\u062a\u0639\u0648\u062f \u0625\u0644\u0649 \u0627\u0644\u0635\u0641\u062d\u0629 \u0627\u0644\u062a\u064a \u0643\u0646\u062a \u062a\u0639\u0645\u0644 \u0639\u0644\u064a\u0647\u0627. \u0644\u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062e\u0631\u0648\u062c \u0627\u0633\u062a\u062e\u062f\u0645 \u0627\u0644\u0642\u0627\u0626\u0645\u0629 \u0627\u0644\u062c\u0627\u0646\u0628\u064a\u0629.").setNegativeButton("\u0645\u062a\u0627\u0628\u0639\u0629 \u0627\u0644\u0639\u0645\u0644", (DialogInterface.OnClickListener) null).setPositiveButton("\u0625\u063a\u0644\u0627\u0642 \u0627\u0644\u062a\u0637\u0628\u064a\u0642", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda175
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity.onBackPressed$lambda$27(this.f$0, dialogInterface, i);
                }
            }).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBackPressed$lambda$27(MainActivity mainActivity, DialogInterface dialogInterface, int i) {
        mainActivity.persistSessionState();
        mainActivity.finish();
    }

    private final void persistSessionState() {
        SharedPreferences.Editor editorPutString = getSessionPrefs().edit().putString(SESSION_SCREEN, this.activeScreenKey).putString(SESSION_SCREEN_ARGUMENT, this.activeScreenArgument).putString(SESSION_CART, serializeCart());
        SecureSessionStore.INSTANCE.save(this, this.currentUserId);
        String str = this.pendingCheckoutDraftId;
        if (str != null) {
            editorPutString.putString(SESSION_CHECKOUT_DRAFT, str);
        } else {
            editorPutString.remove(SESSION_CHECKOUT_DRAFT);
        }
        editorPutString.apply();
    }

    private final String serializeCart() {
        JSONArray jSONArray = new JSONArray();
        Collection<CartLine> collectionValues = this.cart.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        for (CartLine cartLine : collectionValues) {
            jSONArray.put(new JSONObject().put("product_id", cartLine.getProduct().getId()).put("quantity", cartLine.getQuantity()));
        }
        String string = jSONArray.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    private final String incomingPairingShare(Intent source) {
        String stringExtra;
        String string;
        if (!Intrinsics.areEqual(source != null ? source.getAction() : null, "android.intent.action.SEND")) {
            return null;
        }
        String type = source.getType();
        if (Intrinsics.areEqual(type != null ? StringsKt.substringBefore$default(type, ';', (String) null, 2, (Object) null) : null, "text/plain") && (stringExtra = source.getStringExtra("android.intent.extra.TEXT")) != null && (string = StringsKt.trim((CharSequence) stringExtra).toString()) != null && string.length() > 0 && string.length() <= 32768) {
            return string;
        }
        return null;
    }

    private final void openSyncDeviceManager(String actorUserId) {
        Intent intent = SyncDeviceManagementActivity.INSTANCE.intent(this, actorUserId);
        String str = this.pendingPairingShareText;
        if (str != null) {
            intent.putExtra(SyncDeviceManagementActivity.EXTRA_INCOMING_SHARE, str);
            this.pendingPairingShareText = null;
        }
        startActivityForResult(intent, this.deviceSetupRequestCode);
    }

    /* JADX WARN: Code duplicated, block: B:38:0x00ad  */
    private final boolean restoreSession(Bundle state) throws IOException {
        AuthenticatedUser authenticatedUserRestoreActiveSession;
        String string;
        String string2;
        String string3;
        String string4;
        MainActivity mainActivity = this;
        String strLoad = SecureSessionStore.INSTANCE.load(mainActivity);
        if (strLoad == null || (authenticatedUserRestoreActiveSession = new AuthRepository(mainActivity).restoreActiveSession(strLoad)) == null || authenticatedUserRestoreActiveSession.getMustChangePassword()) {
            return false;
        }
        this.currentUserId = authenticatedUserRestoreActiveSession.getId();
        this.currentUserRole = authenticatedUserRestoreActiveSession.getRoleKey();
        if ((state == null || (string = state.getString(STATE_SCREEN)) == null) && (string = getSessionPrefs().getString(SESSION_SCREEN, "POS")) == null) {
            string = "POS";
        }
        this.activeScreenKey = string;
        if (state == null || (string2 = state.getString(STATE_SCREEN_ARGUMENT)) == null) {
            string2 = getSessionPrefs().getString(SESSION_SCREEN_ARGUMENT, null);
        }
        this.activeScreenArgument = string2;
        if (state == null || (string3 = state.getString(STATE_CHECKOUT_DRAFT)) == null) {
            string3 = getSessionPrefs().getString(SESSION_CHECKOUT_DRAFT, null);
        }
        this.pendingCheckoutDraftId = string3;
        String str = HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        if (state == null || (string4 = state.getString(STATE_CART)) == null) {
            string4 = getSessionPrefs().getString(SESSION_CART, HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
        }
        String str2 = this.pendingCheckoutDraftId;
        if (str2 != null) {
            Intrinsics.checkNotNull(str2);
            if (isDraftSalesInvoice(str2)) {
                str = string4;
            } else {
                this.pendingCheckoutDraftId = null;
                getSessionPrefs().edit().remove(SESSION_CHECKOUT_DRAFT).putString(SESSION_CART, HttpUrl.PATH_SEGMENT_ENCODE_SET_URI).apply();
            }
        } else {
            str = string4;
        }
        restoreCart(str);
        return true;
    }

    private final boolean isDraftSalesInvoice(String invoiceId) throws IOException {
        Cursor cursorRawQuery = AppDatabase.INSTANCE.getInstance(this).getReadableDatabase().rawQuery("SELECT 1 FROM sales_invoices WHERE id=? AND status='DRAFT' LIMIT 1", new String[]{invoiceId});
        try {
            boolean zMoveToFirst = cursorRawQuery.moveToFirst();
            CloseableKt.closeFinally(cursorRawQuery, null);
            return zMoveToFirst;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    private final void restoreCart(String snapshot) throws IOException {
        this.cart.clear();
        loadCatalog();
        List<Product> list = this.products;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list, 10)), 16));
        for (Object obj : list) {
            linkedHashMap.put(((Product) obj).getId(), obj);
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            MainActivity mainActivity = this;
            if (snapshot == null) {
                snapshot = HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
            }
            JSONArray jSONArray = new JSONArray(snapshot);
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                Product product = (Product) linkedHashMap.get(jSONObject.getString("product_id"));
                if (product != null) {
                    double d = jSONObject.getDouble("quantity");
                    if (d > 0.0d) {
                        this.cart.put(product.getId(), new CartLine(product, d));
                    }
                }
            }
            Result.m381constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m381constructorimpl(ResultKt.createFailure(th));
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final void restoreActiveScreen() throws IOException {
        String str = this.activeScreenKey;
        switch (str.hashCode()) {
            case -1869370773:
                if (str.equals("PURCHASE_EDITOR")) {
                    showPurchaseInvoiceEditor();
                    return;
                }
                break;
            case -1531228153:
                if (str.equals("SUPPLIERS")) {
                    showSuppliers();
                    return;
                }
                break;
            case -1152765157:
                if (str.equals("EXPENSES")) {
                    showExpenses();
                    return;
                }
                break;
            case -1015473865:
                if (str.equals("USER_MANAGEMENT")) {
                    showUserManagement();
                    return;
                }
                break;
            case -719624646:
                if (str.equals("DAILY_REPORT")) {
                    showDailyCashReport();
                    return;
                }
                break;
            case -221134492:
                if (str.equals("PRODUCTS")) {
                    showProductManagement();
                    return;
                }
                break;
            case 103189557:
                if (str.equals("CUSTOMERS")) {
                    showCustomers();
                    return;
                }
                break;
            case 266097522:
                if (str.equals("CASH_ACCOUNTS")) {
                    showCashAccounts();
                    return;
                }
                break;
            case 870514661:
                if (str.equals("PURCHASE_RETURNS")) {
                    showPurchaseReturns();
                    return;
                }
                break;
            case 995076978:
                if (str.equals("PURCHASES")) {
                    showPurchases();
                    return;
                }
                break;
            case 1044742831:
                if (str.equals("SUPPLIER_DETAILS")) {
                    String str2 = this.activeScreenArgument;
                    showSupplierDetails(str2 != null ? str2 : "");
                    return;
                }
                break;
            case 1077989672:
                if (str.equals("GENERAL_REPORTS")) {
                    showGeneralReports();
                    return;
                }
                break;
            case 1282371244:
                if (str.equals("CUSTOMER_ACCOUNT")) {
                    String str3 = this.activeScreenArgument;
                    showCustomerAccount(str3 != null ? str3 : "");
                    return;
                }
                break;
            case 1355033758:
                if (str.equals("SERVICE_TRANSFERS")) {
                    showServiceTransfers();
                    return;
                }
                break;
        }
        showPointOfSale(true);
    }

    static /* synthetic */ void markScreen$default(MainActivity mainActivity, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        mainActivity.markScreen(str, str2);
    }

    private final void markScreen(String key, String argument) {
        this.activeScreenKey = key;
        this.activeScreenArgument = argument;
        getSessionPrefs().edit().putString(SESSION_SCREEN, key).putString(SESSION_SCREEN_ARGUMENT, argument).apply();
    }

    private final void clearSessionState() {
        this.currentUserId = null;
        this.currentUserRole = "CASHIER";
        this.pendingCheckoutDraftId = null;
        this.activeScreenKey = "LOGIN";
        this.activeScreenArgument = null;
        this.cart.clear();
        getSessionPrefs().edit().clear().apply();
        MainActivity mainActivity = this;
        SecureSessionStore.INSTANCE.clear(mainActivity);
        SyncForegroundService.INSTANCE.stop(mainActivity);
    }

    private final void showInitialAdminSetupScreen() {
        markScreen$default(this, "INITIAL_ADMIN_SETUP", null, 2, null);
        MainActivity mainActivity = this;
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        linearLayout.setPadding(dp(26), dp(28), dp(26), dp(28));
        linearLayout.setBackgroundColor(this.backgroundColor);
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.addView(linearLayout);
        TextView textViewLabel = label("THE DAILY SHOP", 26.0f, this.green, true, 17);
        textViewLabel.setLetterSpacing(0.08f);
        TextView textViewLabel2 = label("\u0625\u0639\u062f\u0627\u062f \u0627\u0644\u0645\u062a\u062c\u0631 \u0644\u0623\u0648\u0644 \u0645\u0631\u0629", 21.0f, this.green, true, 17);
        textViewLabel2.setPadding(0, dp(22), 0, dp(8));
        TextView textViewLabel3 = label("\u0623\u0646\u0634\u0626 \u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u062f\u064a\u0631 \u0627\u0644\u062e\u0627\u0635 \u0628\u0643. \u0644\u0646 \u062a\u0643\u0648\u0646 \u0647\u0646\u0627\u0643 \u0643\u0644\u0645\u0629 \u0645\u0631\u0648\u0631 \u0627\u0641\u062a\u0631\u0627\u0636\u064a\u0629.", 14.0f, this.mutedColor, false, 17);
        textViewLabel3.setPadding(0, 0, 0, dp(18));
        final EditText editTextShowInitialAdminSetupScreen$setupField$default = showInitialAdminSetupScreen$setupField$default(this, "\u0627\u0633\u0645 \u0627\u0644\u0645\u062f\u064a\u0631", false, 4, null);
        final EditText editTextShowInitialAdminSetupScreen$setupField$default2 = showInitialAdminSetupScreen$setupField$default(this, "\u0627\u0633\u0645 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 (3 \u0623\u062d\u0631\u0641 \u0639\u0644\u0649 \u0627\u0644\u0623\u0642\u0644)", false, 4, null);
        final EditText editTextShowInitialAdminSetupScreen$setupField = showInitialAdminSetupScreen$setupField(this, "\u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631 (10 \u0623\u062d\u0631\u0641 \u0639\u0644\u0649 \u0627\u0644\u0623\u0642\u0644 \u0648\u062d\u0631\u0641 \u0648\u0631\u0642\u0645)", true);
        final EditText editTextShowInitialAdminSetupScreen$setupField2 = showInitialAdminSetupScreen$setupField(this, "\u062a\u0623\u0643\u064a\u062f \u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631", true);
        Button button = new Button(mainActivity);
        button.setText("\u0625\u0646\u0634\u0627\u0621 \u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u062f\u064a\u0631");
        button.setTextSize(17.0f);
        button.setTextColor(-1);
        button.setBackgroundColor(this.green);
        button.setAllCaps(false);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.showInitialAdminSetupScreen$lambda$38$lambda$37(editTextShowInitialAdminSetupScreen$setupField, editTextShowInitialAdminSetupScreen$setupField2, this, editTextShowInitialAdminSetupScreen$setupField$default2, editTextShowInitialAdminSetupScreen$setupField$default, view);
            }
        });
        linearLayout.addView(textViewLabel, matchWidth());
        linearLayout.addView(textViewLabel2, matchWidth());
        linearLayout.addView(textViewLabel3, matchWidth());
        linearLayout.addView(editTextShowInitialAdminSetupScreen$setupField$default, fieldParams(dp(54), dp(10)));
        linearLayout.addView(editTextShowInitialAdminSetupScreen$setupField$default2, fieldParams(dp(54), dp(12)));
        linearLayout.addView(editTextShowInitialAdminSetupScreen$setupField, fieldParams(dp(54), dp(12)));
        linearLayout.addView(editTextShowInitialAdminSetupScreen$setupField2, fieldParams(dp(54), dp(12)));
        linearLayout.addView(button, fieldParams(dp(54), dp(20)));
        setContentView(scrollView);
    }

    static /* synthetic */ EditText showInitialAdminSetupScreen$setupField$default(MainActivity mainActivity, String str, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return showInitialAdminSetupScreen$setupField(mainActivity, str, z);
    }

    private static final EditText showInitialAdminSetupScreen$setupField(MainActivity mainActivity, String str, boolean z) {
        EditText editText = new EditText(mainActivity);
        editText.setHint(str);
        editText.setTextSize(16.0f);
        editText.setSingleLine(true);
        editText.setGravity(16);
        editText.setPadding(mainActivity.dp(16), 0, mainActivity.dp(16), 0);
        editText.setBackground(mainActivity.inputBackground());
        if (z) {
            editText.setInputType(129);
        }
        return editText;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showInitialAdminSetupScreen$lambda$38$lambda$37(EditText editText, EditText editText2, MainActivity mainActivity, EditText editText3, EditText editText4, View view) {
        String string = editText.getText().toString();
        if (!Intrinsics.areEqual(string, editText2.getText().toString())) {
            editText2.setError("\u0643\u0644\u0645\u062a\u0627 \u0627\u0644\u0645\u0631\u0648\u0631 \u063a\u064a\u0631 \u0645\u062a\u0637\u0627\u0628\u0642\u062a\u064a\u0646");
            return;
        }
        try {
            new AuthRepository(mainActivity).createInitialAdmin(editText3.getText().toString(), editText4.getText().toString(), string);
            mainActivity.clearSessionState();
            new ServiceTransferRepository(mainActivity).ensureSystemAccounts();
            Toast.makeText(mainActivity, "\u062a\u0645 \u0625\u0646\u0634\u0627\u0621 \u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u062f\u064a\u0631. \u0633\u062c\u0651\u0644 \u0627\u0644\u062f\u062e\u0648\u0644 \u0627\u0644\u0622\u0646 \u0644\u0644\u0645\u062a\u0627\u0628\u0639\u0629.", 1).show();
            mainActivity.showLoginScreen();
        } catch (IllegalArgumentException e) {
            String message = e.getMessage();
            editText.setError(message != null ? message : "\u062a\u0639\u0630\u0631 \u0625\u0646\u0634\u0627\u0621 \u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u062f\u064a\u0631");
        } catch (Exception e2) {
            MainActivity mainActivity2 = mainActivity;
            String message2 = e2.getMessage();
            Toast.makeText(mainActivity2, message2 != null ? message2 : "\u062a\u0639\u0630\u0631 \u0625\u0646\u0634\u0627\u0621 \u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u062f\u064a\u0631", 1).show();
        }
    }

    private final void showLoginScreen() {
        markScreen$default(this, "LOGIN", null, 2, null);
        MainActivity mainActivity = this;
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        linearLayout.setPadding(dp(26), dp(24), dp(26), dp(24));
        linearLayout.setBackgroundColor(this.backgroundColor);
        TextView textViewLabel = label("THE DAILY SHOP", 27.0f, this.green, true, 17);
        textViewLabel.setLetterSpacing(0.08f);
        TextView textViewLabel2 = label("\u0625\u062f\u0627\u0631\u0629 \u0645\u062a\u062c\u0631\u0643 \u0628\u0633\u0647\u0648\u0644\u0629 \u0648\u0627\u062d\u062a\u0631\u0627\u0641\u064a\u0629", 15.0f, this.mutedColor, false, 17);
        textViewLabel2.setPadding(0, dp(8), 0, dp(22));
        TextView textViewLabel3 = label("\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501", 15.0f, this.gold, true, 17);
        textViewLabel3.setPadding(0, 0, 0, dp(18));
        TextView textViewLabel4 = label("\u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062f\u062e\u0648\u0644", 22.0f, this.green, true, 17);
        textViewLabel4.setPadding(0, 0, 0, dp(20));
        final EditText editText = new EditText(mainActivity);
        editText.setHint("\u0627\u0633\u0645 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645");
        editText.setTextSize(16.0f);
        editText.setSingleLine(true);
        editText.setGravity(16);
        editText.setPadding(dp(16), 0, dp(16), 0);
        editText.setBackground(inputBackground());
        final EditText editText2 = new EditText(mainActivity);
        editText2.setHint("\u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631");
        editText2.setTextSize(16.0f);
        editText2.setSingleLine(true);
        editText2.setGravity(16);
        editText2.setInputType(129);
        editText2.setPadding(dp(16), 0, dp(16), 0);
        editText2.setBackground(inputBackground());
        final Button button = new Button(mainActivity);
        button.setText("\u062f\u062e\u0648\u0644");
        button.setTextSize(17.0f);
        button.setTextColor(-1);
        button.setBackgroundColor(this.green);
        button.setAllCaps(false);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda70
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IOException {
                MainActivity.showLoginScreen$lambda$44$lambda$43(this.f$0, editText, editText2, button, view);
            }
        });
        TextView textViewLabel5 = label("\u0627\u0644\u0625\u0635\u062f\u0627\u0631 1.0.0", 12.0f, -7829368, false, 17);
        textViewLabel5.setPadding(0, dp(20), 0, 0);
        linearLayout.addView(textViewLabel, matchWidth());
        linearLayout.addView(textViewLabel2, matchWidth());
        linearLayout.addView(textViewLabel3, matchWidth());
        linearLayout.addView(textViewLabel4, matchWidth());
        linearLayout.addView(editText, fieldParams(dp(54), dp(12)));
        linearLayout.addView(editText2, fieldParams(dp(54), dp(18)));
        linearLayout.addView(button, fieldParams(dp(52), 0));
        linearLayout.addView(textViewLabel5, matchWidth());
        setContentView(linearLayout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void showLoginScreen$lambda$44$lambda$43(MainActivity mainActivity, EditText editText, EditText editText2, Button button, View view) throws IOException {
        Object objM381constructorimpl;
        String str;
        MainActivity mainActivity2 = mainActivity;
        AuthenticatedUser authenticatedUserAuthenticate = new AuthRepository(mainActivity2).authenticate(editText.getText().toString(), editText2.getText().toString());
        if (authenticatedUserAuthenticate != null) {
            if (authenticatedUserAuthenticate.getMustChangePassword()) {
                mainActivity.showForcedPasswordChange(authenticatedUserAuthenticate, editText2.getText().toString());
                return;
            }
            mainActivity.currentUserId = authenticatedUserAuthenticate.getId();
            mainActivity.currentUserRole = authenticatedUserAuthenticate.getRoleKey();
            SecureSessionStore.INSTANCE.save(mainActivity2, authenticatedUserAuthenticate.getId());
            mainActivity.getSessionPrefs().edit().putString(SESSION_SCREEN, "POS").putString(SESSION_CART, HttpUrl.PATH_SEGMENT_ENCODE_SET_URI).apply();
            SyncCredentialVault syncCredentialVault = null;
            Object[] objArr = 0;
            try {
                Result.Companion companion = Result.INSTANCE;
                objM381constructorimpl = Result.m381constructorimpl(new SyncProtocolRepository(mainActivity, syncCredentialVault, 2, objArr == true ? 1 : 0).localDeviceRole());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m387isFailureimpl(objM381constructorimpl)) {
                objM381constructorimpl = null;
            }
            if (((String) objM381constructorimpl) == null || ((str = mainActivity.pendingPairingShareText) != null && !StringsKt.isBlank(str))) {
                mainActivity.openSyncDeviceManager(authenticatedUserAuthenticate.getId());
                return;
            } else {
                showPointOfSale$default(mainActivity, false, 1, null);
                return;
            }
        }
        Toast.makeText(mainActivity2, "\u0627\u0633\u0645 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u0623\u0648 \u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631 \u063a\u064a\u0631 \u0635\u062d\u064a\u062d\u0629", 0).show();
    }

    private final void showForcedPasswordChange(final AuthenticatedUser user, final String currentPassword) {
        MainActivity mainActivity = this;
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(22), dp(12), dp(22), dp(4));
        final EditText editText = new EditText(mainActivity);
        editText.setHint("\u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631 \u0627\u0644\u062c\u062f\u064a\u062f\u0629 (10 \u0623\u062d\u0631\u0641 \u0639\u0644\u0649 \u0627\u0644\u0623\u0642\u0644)");
        editText.setInputType(129);
        editText.setSingleLine(true);
        final EditText editText2 = new EditText(mainActivity);
        editText2.setHint("\u062a\u0623\u0643\u064a\u062f \u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631 \u0627\u0644\u062c\u062f\u064a\u062f\u0629");
        editText2.setInputType(129);
        editText2.setSingleLine(true);
        linearLayout.addView(editText, fieldParams(dp(54), dp(8)));
        linearLayout.addView(editText2, fieldParams(dp(54), dp(12)));
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(mainActivity).setTitle("\u062a\u063a\u064a\u064a\u0631 \u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631 \u0625\u0644\u0632\u0627\u0645\u064a").setMessage("\u0644\u0623\u0645\u0627\u0646 \u0627\u0644\u0645\u062a\u062c\u0631\u060c \u064a\u062c\u0628 \u062a\u0639\u064a\u064a\u0646 \u0643\u0644\u0645\u0629 \u0645\u0631\u0648\u0631 \u0642\u0648\u064a\u0629 \u0642\u0628\u0644 \u0627\u0644\u062f\u062e\u0648\u0644.").setView(linearLayout).setCancelable(false).setPositiveButton("\u062d\u0641\u0638 \u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631", (DialogInterface.OnClickListener) null).setNegativeButton("\u062a\u0633\u062c\u064a\u0644 \u062e\u0631\u0648\u062c", (DialogInterface.OnClickListener) null).create();
        alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda60
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                MainActivity.showForcedPasswordChange$lambda$50(alertDialogCreate, this, editText, editText2, user, currentPassword, dialogInterface);
            }
        });
        alertDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showForcedPasswordChange$lambda$50(final AlertDialog alertDialog, final MainActivity mainActivity, final EditText editText, final EditText editText2, final AuthenticatedUser authenticatedUser, final String str, DialogInterface dialogInterface) {
        alertDialog.getButton(-2).setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda18
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.showForcedPasswordChange$lambda$50$lambda$48(this.f$0, alertDialog, view);
            }
        });
        alertDialog.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda19
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.showForcedPasswordChange$lambda$50$lambda$49(editText, editText2, mainActivity, authenticatedUser, str, alertDialog, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showForcedPasswordChange$lambda$50$lambda$48(MainActivity mainActivity, AlertDialog alertDialog, View view) {
        mainActivity.clearSessionState();
        alertDialog.dismiss();
        mainActivity.showLoginScreen();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showForcedPasswordChange$lambda$50$lambda$49(EditText editText, EditText editText2, MainActivity mainActivity, AuthenticatedUser authenticatedUser, String str, AlertDialog alertDialog, View view) {
        String string = editText.getText().toString();
        if (!Intrinsics.areEqual(string, editText2.getText().toString())) {
            editText2.setError("\u0643\u0644\u0645\u062a\u0627 \u0627\u0644\u0645\u0631\u0648\u0631 \u063a\u064a\u0631 \u0645\u062a\u0637\u0627\u0628\u0642\u062a\u064a\u0646");
            return;
        }
        try {
            new AuthRepository(mainActivity).changePassword(authenticatedUser.getId(), str, string);
            mainActivity.currentUserId = authenticatedUser.getId();
            mainActivity.currentUserRole = authenticatedUser.getRoleKey();
            SecureSessionStore.INSTANCE.save(mainActivity, authenticatedUser.getId());
            mainActivity.getSessionPrefs().edit().putString(SESSION_SCREEN, "POS").putString(SESSION_CART, HttpUrl.PATH_SEGMENT_ENCODE_SET_URI).apply();
            alertDialog.dismiss();
            showPointOfSale$default(mainActivity, false, 1, null);
        } catch (IllegalArgumentException e) {
            String message = e.getMessage();
            editText.setError(message != null ? message : "\u062a\u0639\u0630\u0631 \u062a\u063a\u064a\u064a\u0631 \u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631");
        } catch (Exception e2) {
            MainActivity mainActivity2 = mainActivity;
            String message2 = e2.getMessage();
            Toast.makeText(mainActivity2, message2 != null ? message2 : "\u062a\u0639\u0630\u0631 \u062a\u063a\u064a\u064a\u0631 \u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631", 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void showPointOfSale$default(MainActivity mainActivity, boolean z, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            z = false;
        }
        mainActivity.showPointOfSale(z);
    }

    private final void showPointOfSale(boolean preserveCart) throws IOException {
        TextView textView;
        markScreen$default(this, "POS", null, 2, null);
        if (!preserveCart) {
            this.cart.clear();
            getSessionPrefs().edit().putString(SESSION_CART, HttpUrl.PATH_SEGMENT_ENCODE_SET_URI).apply();
            loadCatalog();
        } else if (this.products.isEmpty()) {
            loadCatalog();
        }
        MainActivity mainActivity = this;
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(this.backgroundColor);
        LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        linearLayout2.setPadding(dp(16), dp(10), dp(16), dp(10));
        linearLayout2.setBackgroundColor(-1);
        Button button = new Button(mainActivity);
        button.setText("\u2630");
        button.setTextSize(23.0f);
        button.setTextColor(this.green);
        button.setAllCaps(false);
        button.setBackgroundColor(0);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda50
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.showPointOfSale$lambda$54$lambda$53(this.f$0, view);
            }
        });
        LinearLayout linearLayout3 = new LinearLayout(mainActivity);
        linearLayout3.setOrientation(1);
        linearLayout3.setGravity(17);
        linearLayout3.addView(label("THE DAILY SHOP", 17.0f, this.green, true, 17));
        linearLayout3.addView(label("\u0646\u0638\u0627\u0645 \u0625\u062f\u0627\u0631\u0629 \u0627\u0644\u0645\u062a\u062c\u0631", 11.0f, this.mutedColor, false, 17));
        TextView textViewLabel = label("\u25cf \u062c\u0627\u0647\u0632 \u0644\u0644\u0628\u064a\u0639", 12.0f, this.green, true, 17);
        linearLayout2.addView(button, new LinearLayout.LayoutParams(dp(48), dp(48)));
        linearLayout2.addView(linearLayout3, new LinearLayout.LayoutParams(0, -2, 1.0f));
        linearLayout2.addView(textViewLabel, new LinearLayout.LayoutParams(-2, -2));
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setFillViewport(true);
        LinearLayout linearLayout4 = new LinearLayout(mainActivity);
        linearLayout4.setOrientation(1);
        linearLayout4.setPadding(dp(16), dp(14), dp(16), dp(24));
        TextView textViewLabel2 = label("\u0646\u0642\u0637\u0629 \u0627\u0644\u0628\u064a\u0639", 25.0f, this.green, true, 5);
        textViewLabel2.setPadding(0, 0, 0, dp(4));
        TextView textViewLabel3 = label("\u0627\u0628\u062d\u062b \u0639\u0646 \u0627\u0644\u0645\u0646\u062a\u062c \u0623\u0648 \u0627\u062e\u062a\u0631\u0647 \u0644\u0625\u0636\u0627\u0641\u062a\u0647 \u0625\u0644\u0649 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629", 13.0f, this.mutedColor, false, 5);
        textViewLabel3.setPadding(0, 0, 0, dp(14));
        EditText editText = new EditText(mainActivity);
        editText.setHint("\u2315  \u0627\u0633\u0645 \u0627\u0644\u0645\u0646\u062a\u062c \u0623\u0648 \u0627\u0644\u0628\u0627\u0631\u0643\u0648\u062f");
        editText.setTextSize(15.0f);
        editText.setSingleLine(true);
        editText.setPadding(dp(14), 0, dp(14), 0);
        editText.setBackground(inputBackground());
        this.searchField = editText;
        editText.addTextChangedListener(new TextWatcher() { // from class: com.hazem.dailystore.MainActivity.showPointOfSale.2
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String string;
                MainActivity mainActivity2 = MainActivity.this;
                String string2 = (s == null || (string = s.toString()) == null) ? null : StringsKt.trim((CharSequence) string).toString();
                if (string2 == null) {
                    string2 = "";
                }
                mainActivity2.searchQuery = string2;
                MainActivity.this.renderProducts();
            }
        });
        linearLayout4.addView(textViewLabel2, matchWidth());
        linearLayout4.addView(textViewLabel3, matchWidth());
        EditText editText2 = this.searchField;
        if (editText2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchField");
            editText2 = null;
        }
        linearLayout4.addView(editText2, fieldParams(dp(52), dp(16)));
        linearLayout4.addView(sectionHeading("\u0627\u0644\u0645\u0646\u062a\u062c\u0627\u062a"));
        GridLayout gridLayout = new GridLayout(mainActivity);
        gridLayout.setColumnCount(2);
        this.productGrid = gridLayout;
        TextView textViewLabel4 = label("\u0644\u0627 \u062a\u0648\u062c\u062f \u0645\u0646\u062a\u062c\u0627\u062a \u0645\u0633\u062c\u0644\u0629 \u0628\u0639\u062f\n\u0623\u0636\u0641 \u0627\u0644\u0645\u0646\u062a\u062c\u0627\u062a \u0645\u0646 \u0642\u0633\u0645 \u0625\u062f\u0627\u0631\u0629 \u0627\u0644\u0645\u0646\u062a\u062c\u0627\u062a \u0644\u062a\u0638\u0647\u0631 \u0647\u0646\u0627", 15.0f, this.mutedColor, false, 17);
        textViewLabel4.setPadding(dp(12), dp(30), dp(12), dp(30));
        this.emptyProductsView = textViewLabel4;
        if (textViewLabel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("emptyProductsView");
            textViewLabel4 = null;
        }
        linearLayout4.addView(textViewLabel4, matchWidth());
        GridLayout gridLayout2 = this.productGrid;
        if (gridLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("productGrid");
            gridLayout2 = null;
        }
        linearLayout4.addView(gridLayout2, matchWidth());
        linearLayout4.addView(sectionHeading("\u0633\u0644\u0629 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629"));
        TextView textViewLabel5 = label("0 \u0623\u0635\u0646\u0627\u0641", 12.0f, this.mutedColor, false, 5);
        this.countView = textViewLabel5;
        if (textViewLabel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countView");
            textViewLabel5 = null;
        }
        textViewLabel5.setPadding(0, 0, 0, dp(8));
        TextView textView2 = this.countView;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countView");
            textView2 = null;
        }
        linearLayout4.addView(textView2, matchWidth());
        LinearLayout linearLayout5 = new LinearLayout(mainActivity);
        linearLayout5.setOrientation(1);
        linearLayout5.setBackground(cardBackground());
        linearLayout5.setPadding(dp(12), dp(8), dp(12), dp(8));
        this.cartContainer = linearLayout5;
        linearLayout4.addView(linearLayout5, matchWidth());
        TextView textViewLabel6 = label("\u0627\u0644\u0625\u062c\u0645\u0627\u0644\u064a\n0 \u0644.\u0633", 21.0f, this.green, true, 17);
        this.totalView = textViewLabel6;
        if (textViewLabel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("totalView");
            textViewLabel6 = null;
        }
        textViewLabel6.setBackground(cardBackground());
        TextView textView3 = this.totalView;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("totalView");
            textView3 = null;
        }
        textView3.setPadding(dp(12), dp(14), dp(12), dp(14));
        LinearLayout.LayoutParams layoutParamsMatchWidth = matchWidth();
        layoutParamsMatchWidth.topMargin = dp(12);
        TextView textView4 = this.totalView;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("totalView");
            textView = null;
        } else {
            textView = textView4;
        }
        linearLayout4.addView(textView, layoutParamsMatchWidth);
        Button button2 = new Button(mainActivity);
        button2.setText("\u0645\u062a\u0627\u0628\u0639\u0629 \u0625\u0644\u0649 \u0627\u0644\u062f\u0641\u0639");
        button2.setTextSize(16.0f);
        button2.setAllCaps(false);
        button2.setTextColor(-1);
        button2.setBackgroundColor(this.green);
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda51
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.showCheckoutDialog();
            }
        });
        linearLayout4.addView(button2, fieldParams(dp(54), dp(8)));
        scrollView.addView(linearLayout4);
        linearLayout.addView(linearLayout2, matchWidth());
        linearLayout.addView(scrollView, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        setContentView(linearLayout);
        renderProducts();
        renderCart();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showPointOfSale$lambda$54$lambda$53(MainActivity mainActivity, View view) {
        Intrinsics.checkNotNull(view);
        mainActivity.showNavigationMenu(view);
    }

    private final long cartTotalUsdCents() {
        Collection<CartLine> collectionValues = this.cart.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        long jM = 0;
        for (CartLine cartLine : collectionValues) {
            jM = MainActivity$$ExternalSyntheticBackport0.m(jM, BigDecimal.valueOf(cartLine.getProduct().getPriceUsdCents()).multiply(BigDecimal.valueOf(cartLine.getQuantity())).setScale(0, RoundingMode.HALF_UP).longValueExact());
        }
        return jM;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showCheckoutDialog() {
        if (this.cart.isEmpty()) {
            Toast.makeText(this, "\u0623\u0636\u0641 \u0635\u0646\u0641\u0627\u064b \u0648\u0627\u062d\u062f\u0627\u064b \u0639\u0644\u0649 \u0627\u0644\u0623\u0642\u0644 \u0642\u0628\u0644 \u0645\u062a\u0627\u0628\u0639\u0629 \u0627\u0644\u062f\u0641\u0639", 0).show();
            return;
        }
        MainActivity mainActivity = this;
        final List<CustomerSummary> listListActive = new CustomerRepository(mainActivity).listActive();
        CashAccountRepository cashAccountRepository = new CashAccountRepository(mainActivity);
        CashAccountSummary cashAccountSummaryCurrentDayPrimaryDrawer = cashAccountRepository.currentDayPrimaryDrawer();
        String id = cashAccountSummaryCurrentDayPrimaryDrawer != null ? cashAccountSummaryCurrentDayPrimaryDrawer.getId() : null;
        List<CashAccountSummary> listListActive2 = cashAccountRepository.listActive();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listListActive2) {
            CashAccountSummary cashAccountSummary = (CashAccountSummary) obj;
            if (Intrinsics.areEqual(cashAccountSummary.getAccountType(), "WALLET") || (Intrinsics.areEqual(cashAccountSummary.getAccountType(), "CASH_BOX") && (Intrinsics.areEqual(cashAccountSummary.getCurrencyCode(), "USD") || (Intrinsics.areEqual(cashAccountSummary.getCurrencyCode(), "SYP") && Intrinsics.areEqual(cashAccountSummary.getId(), id))))) {
                arrayList.add(obj);
            }
        }
        final ArrayList arrayList2 = arrayList;
        final long jCartTotalUsdCents = cartTotalUsdCents();
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(18), dp(8), dp(18), dp(4));
        linearLayout.addView(label("\u0625\u062c\u0645\u0627\u0644\u064a \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629", 13.0f, this.mutedColor, false, 5));
        TextView textViewLabel = label(formatDualMoney(jCartTotalUsdCents, this.exchangeRateMilli), 17.0f, this.green, true, 5);
        textViewLabel.setPadding(0, dp(4), 0, dp(12));
        linearLayout.addView(textViewLabel);
        final RadioGroup radioGroup = new RadioGroup(mainActivity);
        radioGroup.setOrientation(0);
        radioGroup.setGravity(17);
        RadioButton radioButton = new RadioButton(mainActivity);
        radioButton.setId(View.generateViewId());
        radioButton.setText("\u0628\u064a\u0639 \u0646\u0642\u062f\u064a");
        ArrayList arrayList3 = arrayList2;
        radioButton.setChecked(!arrayList3.isEmpty());
        radioButton.setEnabled(!arrayList3.isEmpty());
        final RadioButton radioButton2 = new RadioButton(mainActivity);
        radioButton2.setId(View.generateViewId());
        radioButton2.setText("\u0628\u064a\u0639 \u0622\u062c\u0644");
        radioButton2.setEnabled(true);
        radioGroup.addView(radioButton);
        radioGroup.addView(radioButton2);
        if (arrayList2.isEmpty()) {
            radioButton2.setChecked(true);
        }
        linearLayout.addView(label("\u0646\u0648\u0639 \u0627\u0644\u0628\u064a\u0639", 13.0f, this.mutedColor, true, 5));
        linearLayout.addView(radioGroup, fieldParams(dp(48), dp(8)));
        final LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        linearLayout2.setOrientation(1);
        linearLayout2.addView(label("\u0627\u0644\u0632\u0628\u0648\u0646", 12.0f, this.mutedColor, false, 5));
        final Spinner spinner = new Spinner(mainActivity);
        List<CustomerSummary> list = listListActive;
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList4.add(((CustomerSummary) it.next()).getName());
        }
        spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, arrayList4));
        linearLayout2.addView(spinner, fieldParams(dp(48), dp(8)));
        if (listListActive.isEmpty()) {
            TextView textViewLabel2 = label("\u0644\u0627 \u064a\u0648\u062c\u062f \u0632\u0628\u0627\u0626\u0646 \u0646\u0634\u0637\u0648\u0646 \u062d\u0627\u0644\u064a\u0627\u064b. \u0623\u0636\u0641 \u0632\u0628\u0648\u0646\u0627\u064b \u0645\u0646 \u0642\u0633\u0645 \u0627\u0644\u0639\u0645\u0644\u0627\u0621 \u0648\u0627\u0644\u062f\u064a\u0648\u0646 \u0642\u0628\u0644 \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u0622\u062c\u0644.", 11.0f, this.gold, true, 5);
            textViewLabel2.setPadding(0, dp(4), 0, dp(6));
            linearLayout2.addView(textViewLabel2);
        }
        final LinearLayout linearLayout3 = new LinearLayout(mainActivity);
        linearLayout3.setOrientation(1);
        linearLayout3.addView(label("\u0627\u0644\u0635\u0646\u062f\u0648\u0642 \u0623\u0648 \u0627\u0644\u0645\u062d\u0641\u0638\u0629 \u0627\u0644\u062a\u064a \u0633\u062a\u0633\u062a\u0644\u0645 \u0642\u064a\u0645\u0629 \u0627\u0644\u0628\u064a\u0639 \u0643\u0627\u0645\u0644\u0629", 12.0f, this.mutedColor, false, 5));
        final Spinner spinner2 = new Spinner(mainActivity);
        ArrayList<CashAccountSummary> arrayList5 = arrayList2;
        ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList5, 10));
        for (CashAccountSummary cashAccountSummary2 : arrayList5) {
            arrayList6.add(cashAccountSummary2.getName() + " \u2022 " + cashAccountSummary2.getCurrencyCode());
        }
        spinner2.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, arrayList6));
        linearLayout3.addView(spinner2, fieldParams(dp(48), dp(8)));
        final TextView textViewLabel3 = label("", 11.0f, this.mutedColor, false, 5);
        linearLayout3.addView(textViewLabel3);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.hazem.dailystore.MainActivity.showCheckoutDialog.5
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> parent) {
                MainActivity.showCheckoutDialog$refreshMode(radioGroup, radioButton2, linearLayout2, linearLayout3, arrayList2, spinner2, textViewLabel3, this, jCartTotalUsdCents);
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id2) {
                MainActivity.showCheckoutDialog$refreshMode(radioGroup, radioButton2, linearLayout2, linearLayout3, arrayList2, spinner2, textViewLabel3, this, jCartTotalUsdCents);
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda150
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup2, int i) {
                MainActivity.showCheckoutDialog$refreshMode(radioGroup, radioButton2, linearLayout2, linearLayout3, arrayList2, spinner2, textViewLabel3, this, jCartTotalUsdCents);
            }
        });
        linearLayout.addView(linearLayout2, matchWidth());
        linearLayout.addView(linearLayout3, matchWidth());
        showCheckoutDialog$refreshMode(radioGroup, radioButton2, linearLayout2, linearLayout3, arrayList2, spinner2, textViewLabel3, this, jCartTotalUsdCents);
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(mainActivity).setTitle("\u0625\u062a\u0645\u0627\u0645 \u0639\u0645\u0644\u064a\u0629 \u0627\u0644\u0628\u064a\u0639").setView(linearLayout).setNegativeButton("\u0631\u062c\u0648\u0639", (DialogInterface.OnClickListener) null).setPositiveButton("\u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629", (DialogInterface.OnClickListener) null).create();
        alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda151
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                MainActivity.showCheckoutDialog$lambda$84(alertDialogCreate, radioGroup, radioButton2, listListActive, spinner, arrayList2, spinner2, this, dialogInterface);
            }
        });
        alertDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showCheckoutDialog$refreshMode(RadioGroup radioGroup, RadioButton radioButton, LinearLayout linearLayout, LinearLayout linearLayout2, List<CashAccountSummary> list, Spinner spinner, TextView textView, MainActivity mainActivity, long j) {
        String str;
        String str2;
        boolean z = radioGroup.getCheckedRadioButtonId() == radioButton.getId();
        linearLayout.setVisibility(z ? 0 : 8);
        linearLayout2.setVisibility(z ? 8 : 0);
        CashAccountSummary cashAccountSummary = (CashAccountSummary) CollectionsKt.getOrNull(list, spinner.getSelectedItemPosition());
        if (cashAccountSummary != null) {
            if (Intrinsics.areEqual(cashAccountSummary.getCurrencyCode(), "USD")) {
                str = "$" + mainActivity.moneyText(j);
            } else if (mainActivity.exchangeRateMilli > 0) {
                str = mainActivity.formatSypAmount(CurrencyConverter.INSTANCE.usdCentsToSyp(j, mainActivity.exchangeRateMilli)) + " \u0644.\u0633";
            } else {
                str = "\u064a\u0644\u0632\u0645 \u062a\u0633\u062c\u064a\u0644 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0623\u0648\u0644\u0627\u064b";
            }
            str2 = "\u0633\u064a\u064f\u0633\u062c\u0651\u0644 \u0642\u0628\u0636 \u0643\u0627\u0645\u0644 \u0628\u0642\u064a\u0645\u0629 " + str;
        }
        textView.setText(str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showCheckoutDialog$lambda$84(final AlertDialog alertDialog, final RadioGroup radioGroup, final RadioButton radioButton, final List list, final Spinner spinner, final List list2, final Spinner spinner2, final MainActivity mainActivity, DialogInterface dialogInterface) {
        alertDialog.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda161
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.showCheckoutDialog$lambda$84$lambda$83(radioGroup, radioButton, list, spinner, list2, spinner2, mainActivity, alertDialog, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void showCheckoutDialog$lambda$84$lambda$83(RadioGroup radioGroup, RadioButton radioButton, List list, Spinner spinner, List list2, Spinner spinner2, MainActivity mainActivity, AlertDialog alertDialog, View view) {
        String str;
        CashAccountSummary cashAccountSummary;
        boolean zPostCashSale;
        String str2;
        String id;
        try {
            boolean z = radioGroup.getCheckedRadioButtonId() == radioButton.getId();
            if (z && list.isEmpty()) {
                throw new IllegalArgumentException("\u0623\u0636\u0641 \u0632\u0628\u0648\u0646\u0627\u064b \u0646\u0634\u0637\u0627\u064b \u0645\u0646 \u0642\u0633\u0645 \u0627\u0644\u0639\u0645\u0644\u0627\u0621 \u0648\u0627\u0644\u062f\u064a\u0648\u0646 \u0623\u0648\u0644\u0627\u064b".toString());
            }
            if (z) {
                CustomerSummary customerSummary = (CustomerSummary) CollectionsKt.getOrNull(list, spinner.getSelectedItemPosition());
                if (customerSummary == null || (id = customerSummary.getId()) == null) {
                    throw new IllegalStateException("\u0627\u062e\u062a\u0631 \u0627\u0644\u0632\u0628\u0648\u0646 \u0627\u0644\u0630\u064a \u0633\u062a\u0633\u062c\u0644 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0639\u0644\u0649 \u062d\u0633\u0627\u0628\u0647".toString());
                }
                str = id;
            } else {
                str = null;
            }
            if (z) {
                cashAccountSummary = null;
            } else {
                cashAccountSummary = (CashAccountSummary) CollectionsKt.getOrNull(list2, spinner2.getSelectedItemPosition());
                if (cashAccountSummary == null) {
                    throw new IllegalStateException("\u0627\u062e\u062a\u0631 \u0627\u0644\u0635\u0646\u062f\u0648\u0642 \u0623\u0648 \u0627\u0644\u0645\u062d\u0641\u0638\u0629 \u0627\u0644\u062a\u064a \u0633\u062a\u0633\u062a\u0644\u0645 \u0642\u064a\u0645\u0629 \u0627\u0644\u0628\u064a\u0639".toString());
                }
            }
            if (z && mainActivity.exchangeRateMilli <= 0) {
                throw new IllegalArgumentException("\u0633\u062c\u0651\u0644 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0642\u0628\u0644 \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u0622\u062c\u0644".toString());
            }
            if (Intrinsics.areEqual(cashAccountSummary != null ? cashAccountSummary.getCurrencyCode() : null, "SYP") && mainActivity.exchangeRateMilli <= 0) {
                throw new IllegalArgumentException("\u0633\u062c\u0651\u0644 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0642\u0628\u0644 \u0642\u0628\u0636 \u0627\u0644\u0628\u064a\u0639 \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629".toString());
            }
            String str3 = z ? "CREDIT" : "CASH";
            StoreTime storeTime = StoreTime.INSTANCE;
            SQLiteDatabase readableDatabase = AppDatabase.INSTANCE.getInstance(mainActivity).getReadableDatabase();
            Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
            String str4 = StoreTime.today$default(storeTime, readableDatabase, null, 2, null);
            Collection<CartLine> collectionValues = mainActivity.cart.values();
            Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
            Collection<CartLine> collection = collectionValues;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
            for (CartLine cartLine : collection) {
                String id2 = cartLine.getProduct().getId();
                BigDecimal bigDecimalValueOf = BigDecimal.valueOf(cartLine.getQuantity());
                Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "valueOf(...)");
                arrayList.add(new InvoiceLineInput(id2, bigDecimalValueOf, cartLine.getProduct().getPriceUsdCents()));
            }
            ArrayList arrayList2 = arrayList;
            InvoiceRepository invoiceRepository = new InvoiceRepository(mainActivity);
            String str5 = mainActivity.currentUserId;
            if (str5 == null) {
                throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
            }
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            int i = 2;
            InvoiceSaveResult invoiceSaveResultSaveSalesDraft = invoiceRepository.saveSalesDraft(str5, string, mainActivity.pendingCheckoutDraftId, str, str4, 0L, null, arrayList2, str3);
            mainActivity.pendingCheckoutDraftId = invoiceSaveResultSaveSalesDraft.getInvoiceId();
            mainActivity.getSessionPrefs().edit().putString(SESSION_CHECKOUT_DRAFT, mainActivity.pendingCheckoutDraftId).apply();
            if (z) {
                InvoiceRepository invoiceRepository2 = new InvoiceRepository(mainActivity);
                String str6 = mainActivity.currentUserId;
                if (str6 == null) {
                    throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
                }
                String invoiceId = invoiceSaveResultSaveSalesDraft.getInvoiceId();
                String string2 = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                zPostCashSale = invoiceRepository2.postSale(str6, invoiceId, string2, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : null);
            } else {
                InvoiceRepository invoiceRepository3 = new InvoiceRepository(mainActivity);
                String str7 = mainActivity.currentUserId;
                if (str7 != null) {
                    String invoiceId2 = invoiceSaveResultSaveSalesDraft.getInvoiceId();
                    String string3 = UUID.randomUUID().toString();
                    Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
                    Intrinsics.checkNotNull(cashAccountSummary);
                    zPostCashSale = invoiceRepository3.postCashSale(str7, invoiceId2, string3, cashAccountSummary.getId(), (48 & 16) != 0 ? null : null, (48 & 32) != 0 ? null : null);
                } else {
                    throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
                }
            }
            if (!zPostCashSale) {
                throw new IllegalArgumentException("\u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0645\u0633\u062c\u0644\u0629 \u0645\u0633\u0628\u0642\u0627\u064b\u061b \u062d\u062f\u0651\u062b \u0627\u0644\u0634\u0627\u0634\u0629 \u0644\u0645\u0631\u0627\u062c\u0639\u0629 \u062d\u0627\u0644\u062a\u0647\u0627".toString());
            }
            String invoiceNumber = invoiceSaveResultSaveSalesDraft.getInvoiceNumber();
            mainActivity.pendingCheckoutDraftId = null;
            mainActivity.getSessionPrefs().edit().remove(SESSION_CHECKOUT_DRAFT).putString(SESSION_CART, HttpUrl.PATH_SEGMENT_ENCODE_SET_URI).apply();
            mainActivity.cart.clear();
            alertDialog.dismiss();
            MainActivity mainActivity2 = mainActivity;
            if (new SyncProtocolRepository(mainActivity, null, i, 0 == true ? 1 : 0).isConfiguredSecondary()) {
                str2 = "\u062a\u0645 \u062d\u0641\u0638 \u0637\u0644\u0628 \u0627\u0644\u0628\u064a\u0639 \u0645\u062d\u0644\u064a\u0627\u064b \u0628\u0627\u0646\u062a\u0638\u0627\u0631 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u0648\u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a\u061b \u0644\u0645 \u064a\u064f\u0639\u062a\u0645\u062f \u0627\u0644\u0628\u064a\u0639 \u0628\u0639\u062f \u2022 \u0641\u0627\u062a\u0648\u0631\u0629 " + invoiceNumber;
            } else if (z) {
                str2 = "\u062a\u0645 \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u0622\u062c\u0644 \u0639\u0644\u0649 \u062d\u0633\u0627\u0628 \u0627\u0644\u0632\u0628\u0648\u0646 \u2022 \u0641\u0627\u062a\u0648\u0631\u0629 " + invoiceNumber;
            } else {
                str2 = "\u062a\u0645 \u0627\u0644\u0628\u064a\u0639 \u0648\u0642\u0628\u0636 \u0643\u0627\u0645\u0644 \u0627\u0644\u0642\u064a\u0645\u0629 \u0648\u062a\u062d\u062f\u064a\u062b \u0627\u0644\u0635\u0646\u062f\u0648\u0642 \u2022 \u0641\u0627\u062a\u0648\u0631\u0629 " + invoiceNumber;
            }
            Toast.makeText(mainActivity2, str2, 1).show();
            showPointOfSale$default(mainActivity, false, 1, null);
        } catch (Exception e) {
            MainActivity mainActivity3 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u0625\u062a\u0645\u0627\u0645 \u0627\u0644\u0628\u064a\u0639\u061b \u0644\u0645 \u064a\u062a\u0645 \u062a\u0631\u062d\u064a\u0644 \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u0623\u0648 \u0642\u0628\u0636 \u0627\u0644\u0645\u0628\u0644\u063a";
            }
            Toast.makeText(mainActivity3, message, 1).show();
        }
    }

    private final void loadCatalog() throws IOException {
        this.products.clear();
        SQLiteDatabase readableDatabase = AppDatabase.INSTANCE.getInstance(this).getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        Cursor cursorRawQuery = readableDatabase.rawQuery("SELECT p.id, p.name, p.barcode, u.symbol, p.sale_price_usd_cents,\n                      p.track_inventory, COALESCE(SUM(ib.quantity), 0)\n               FROM products p\n               JOIN units u ON u.id = p.unit_id\n               LEFT JOIN inventory_balances ib ON ib.product_id = p.id\n               WHERE p.is_active = 1\n               GROUP BY p.id\n               ORDER BY p.name COLLATE NOCASE", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                List<Product> list = this.products;
                String string = cursor.getString(0);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String string2 = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                String string3 = cursor.isNull(2) ? null : cursor.getString(2);
                String string4 = cursor.getString(3);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                list.add(new Product(string, string2, string3, string4, cursor.getLong(4), cursor.getInt(5) == 1, cursor.getDouble(6)));
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            Cursor cursorRawQuery2 = readableDatabase.rawQuery("SELECT rate_syp_per_usd_milli FROM exchange_rates ORDER BY effective_at DESC, created_at DESC LIMIT 1", (String[]) null);
            try {
                Cursor cursor2 = cursorRawQuery2;
                long j = cursor2.moveToFirst() ? cursor2.getLong(0) : 0L;
                CloseableKt.closeFinally(cursorRawQuery2, null);
                this.exchangeRateMilli = j;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursorRawQuery2, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            try {
                throw th3;
            } catch (Throwable th4) {
                CloseableKt.closeFinally(cursorRawQuery, th3);
                throw th4;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderProducts() {
        String barcode;
        GridLayout gridLayout = this.productGrid;
        if (gridLayout == null) {
            return;
        }
        if (gridLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("productGrid");
            gridLayout = null;
        }
        gridLayout.removeAllViews();
        List<Product> list = this.products;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            Product product = (Product) obj;
            if (StringsKt.isBlank(this.searchQuery) || StringsKt.contains((CharSequence) product.getName(), (CharSequence) this.searchQuery, true) || ((barcode = product.getBarcode()) != null && StringsKt.contains((CharSequence) barcode, (CharSequence) this.searchQuery, true))) {
                arrayList.add(obj);
            }
        }
        ArrayList<Product> arrayList2 = arrayList;
        TextView textView = this.emptyProductsView;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("emptyProductsView");
            textView = null;
        }
        textView.setVisibility(arrayList2.isEmpty() ? 0 : 8);
        GridLayout gridLayout2 = this.productGrid;
        if (gridLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("productGrid");
            gridLayout2 = null;
        }
        gridLayout2.setVisibility(arrayList2.isEmpty() ? 8 : 0);
        for (final Product product2 : arrayList2) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(1);
            linearLayout.setGravity(17);
            linearLayout.setPadding(dp(10), dp(14), dp(10), dp(14));
            linearLayout.setBackground(cardBackground());
            linearLayout.setClickable(true);
            linearLayout.setFocusable(true);
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda89
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.addToCart(product2, 1.0d);
                }
            });
            linearLayout.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda90
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return MainActivity.renderProducts$lambda$93$lambda$90$lambda$89(this.f$0, product2, view);
                }
            });
            linearLayout.addView(label(product2.getName(), 15.0f, this.textColor, true, 17));
            TextView textViewLabel = label(formatPrice(product2.getPriceUsdCents()), 16.0f, this.green, true, 17);
            textViewLabel.setPadding(0, dp(8), 0, dp(4));
            linearLayout.addView(textViewLabel);
            linearLayout.addView(label(product2.getTracksInventory() ? "\u0627\u0644\u0645\u062a\u0648\u0641\u0631: " + formatQuantity(product2.getStock()) + " " + product2.getUnit() : "\u063a\u064a\u0631 \u062e\u0627\u0636\u0639 \u0644\u0644\u0645\u062e\u0632\u0648\u0646", 11.0f, this.mutedColor, false, 17));
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.width = 0;
            layoutParams.height = -2;
            layoutParams.columnSpec = GridLayout.spec(Integer.MIN_VALUE, 1.0f);
            layoutParams.setMargins(dp(4), dp(4), dp(4), dp(4));
            GridLayout gridLayout3 = this.productGrid;
            if (gridLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("productGrid");
                gridLayout3 = null;
            }
            gridLayout3.addView(linearLayout, layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean renderProducts$lambda$93$lambda$90$lambda$89(MainActivity mainActivity, Product product, View view) {
        mainActivity.showQuantityDialog(product);
        return true;
    }

    private final void showQuantityDialog(final Product product) {
        MainActivity mainActivity = this;
        final EditText editText = new EditText(mainActivity);
        editText.setInputType(8194);
        editText.setSingleLine(true);
        editText.setTextSize(20.0f);
        editText.setGravity(17);
        editText.setText("1");
        editText.selectAll();
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(24), dp(8), dp(24), 0);
        linearLayout.addView(label("\u0623\u062f\u062e\u0644 \u0627\u0644\u0643\u0645\u064a\u0629 \u0627\u0644\u062a\u064a \u062a\u0631\u064a\u062f \u0625\u0636\u0627\u0641\u062a\u0647\u0627 \u0645\u0646:", 14.0f, this.mutedColor, false, 17));
        TextView textViewLabel = label(product.getName(), 17.0f, this.green, true, 17);
        textViewLabel.setPadding(0, dp(8), 0, dp(8));
        linearLayout.addView(textViewLabel);
        linearLayout.addView(editText, fieldParams(dp(52), 0));
        new AlertDialog.Builder(mainActivity).setTitle("\u0625\u0636\u0627\u0641\u0629 \u0643\u0645\u064a\u0629").setView(linearLayout).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u0625\u0636\u0627\u0641\u0629", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda71
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.showQuantityDialog$lambda$97(editText, this, product, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showQuantityDialog$lambda$97(EditText editText, MainActivity mainActivity, Product product, DialogInterface dialogInterface, int i) {
        Double doubleOrNull = StringsKt.toDoubleOrNull(editText.getText().toString());
        if (doubleOrNull == null || doubleOrNull.doubleValue() <= 0.0d) {
            Toast.makeText(mainActivity, "\u0623\u062f\u062e\u0644 \u0643\u0645\u064a\u0629 \u0635\u062d\u064a\u062d\u0629 \u0623\u0643\u0628\u0631 \u0645\u0646 \u0635\u0641\u0631", 0).show();
        } else {
            mainActivity.addToCart(product, doubleOrNull.doubleValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addToCart(Product product, double quantity) {
        CartLine cartLine = this.cart.get(product.getId());
        double quantity2 = (cartLine != null ? cartLine.getQuantity() : 0.0d) + quantity;
        if (product.getTracksInventory() && quantity2 > product.getStock() + 1.0E-6d) {
            Toast.makeText(this, "\u0627\u0644\u0643\u0645\u064a\u0629 \u0627\u0644\u0645\u0637\u0644\u0648\u0628\u0629 \u0623\u0643\u0628\u0631 \u0645\u0646 \u0627\u0644\u0645\u062a\u0648\u0641\u0631 (" + formatQuantity(product.getStock()) + ")", 0).show();
            return;
        }
        CartLine cartLine2 = this.cart.get(product.getId());
        if (cartLine2 == null) {
            this.cart.put(product.getId(), new CartLine(product, quantity));
        } else {
            cartLine2.setQuantity(quantity2);
        }
        renderCart();
        Toast.makeText(this, "\u062a\u0645\u062a \u0625\u0636\u0627\u0641\u0629 " + formatQuantity(quantity) + " \u0645\u0646 " + product.getName(), 0).show();
    }

    private final void renderCart() {
        TextView textView;
        LinearLayout linearLayout;
        getSessionPrefs().edit().putString(SESSION_CART, serializeCart()).apply();
        LinearLayout linearLayout2 = this.cartContainer;
        if (linearLayout2 == null) {
            return;
        }
        if (linearLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cartContainer");
            linearLayout2 = null;
        }
        linearLayout2.removeAllViews();
        int i = 8;
        int i2 = 0;
        if (this.cart.isEmpty()) {
            LinearLayout linearLayout3 = this.cartContainer;
            if (linearLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cartContainer");
                linearLayout = null;
            } else {
                linearLayout = linearLayout3;
            }
            TextView textViewLabel = label("\u0627\u0644\u0633\u0644\u0629 \u0641\u0627\u0631\u063a\u0629\n\u0627\u0636\u063a\u0637 \u0639\u0644\u0649 \u0627\u0644\u0645\u0646\u062a\u062c \u0644\u0625\u0636\u0627\u0641\u0629 \u0642\u0637\u0639\u0629\u060c \u0623\u0648 \u0627\u0636\u063a\u0637 \u0645\u0637\u0648\u0644\u0627\u064b \u0644\u0627\u062e\u062a\u064a\u0627\u0631 \u0627\u0644\u0643\u0645\u064a\u0629", 13.0f, this.mutedColor, false, 17);
            textViewLabel.setPadding(dp(8), dp(24), dp(8), dp(24));
            linearLayout.addView(textViewLabel);
        } else {
            Collection<CartLine> collectionValues = this.cart.values();
            Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
            for (final CartLine cartLine : collectionValues) {
                MainActivity mainActivity = this;
                LinearLayout linearLayout4 = new LinearLayout(mainActivity);
                linearLayout4.setOrientation(i2);
                linearLayout4.setGravity(16);
                linearLayout4.setPadding(i2, dp(i), i2, dp(i));
                LinearLayout linearLayout5 = new LinearLayout(mainActivity);
                linearLayout5.setOrientation(1);
                linearLayout5.addView(label(cartLine.getProduct().getName(), 14.0f, this.textColor, true, 5));
                linearLayout5.addView(label(formatQuantity(cartLine.getQuantity()) + " " + cartLine.getProduct().getUnit() + " \u00d7 " + formatPrice(cartLine.getProduct().getPriceUsdCents()), 12.0f, this.mutedColor, false, 5));
                Button buttonSmallButton = smallButton("\u2212", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda133
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivity.renderCart$lambda$106$lambda$101(this.f$0, cartLine);
                    }
                });
                Button buttonSmallButton2 = smallButton("+", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda134
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivity.renderCart$lambda$106$lambda$102(this.f$0, cartLine);
                    }
                });
                Button buttonSmallButton3 = smallButton("\u00d7", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda135
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivity.renderCart$lambda$106$lambda$103(this.f$0, cartLine);
                    }
                });
                linearLayout4.addView(linearLayout5, new LinearLayout.LayoutParams(i2, -2, 1.0f));
                linearLayout4.addView(buttonSmallButton, new LinearLayout.LayoutParams(dp(36), dp(36)));
                TextView textViewLabel2 = label(formatQuantity(cartLine.getQuantity()), 13.0f, this.green, true, 17);
                textViewLabel2.setMinWidth(dp(38));
                linearLayout4.addView(textViewLabel2, new LinearLayout.LayoutParams(dp(42), -2));
                linearLayout4.addView(buttonSmallButton2, new LinearLayout.LayoutParams(dp(36), dp(36)));
                linearLayout4.addView(buttonSmallButton3, new LinearLayout.LayoutParams(dp(36), dp(36)));
                LinearLayout linearLayout6 = this.cartContainer;
                if (linearLayout6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cartContainer");
                    linearLayout6 = null;
                }
                linearLayout6.addView(linearLayout4);
                View view = new View(mainActivity);
                view.setBackgroundColor(Color.rgb(232, 235, 231));
                LinearLayout linearLayout7 = this.cartContainer;
                if (linearLayout7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cartContainer");
                    linearLayout7 = null;
                }
                linearLayout7.addView(view, new LinearLayout.LayoutParams(-1, dp(1)));
                i = 8;
                i2 = 0;
            }
        }
        TextView textView2 = this.countView;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countView");
            textView2 = null;
        }
        textView2.setText(this.cart.size() + " \u0623\u0635\u0646\u0627\u0641");
        Collection<CartLine> collectionValues2 = this.cart.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues2, "<get-values>(...)");
        long jLongValueExact = 0;
        for (CartLine cartLine2 : collectionValues2) {
            jLongValueExact += BigDecimal.valueOf(cartLine2.getProduct().getPriceUsdCents()).multiply(BigDecimal.valueOf(cartLine2.getQuantity())).setScale(0, RoundingMode.HALF_UP).longValueExact();
        }
        TextView textView3 = this.totalView;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("totalView");
            textView = null;
        } else {
            textView = textView3;
        }
        textView.setText("\u0627\u0644\u0625\u062c\u0645\u0627\u0644\u064a\n" + formatPrice(jLongValueExact));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit renderCart$lambda$106$lambda$101(MainActivity mainActivity, CartLine cartLine) {
        Intrinsics.checkNotNull(cartLine);
        mainActivity.changeCartQuantity(cartLine, -1.0d);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit renderCart$lambda$106$lambda$102(MainActivity mainActivity, CartLine cartLine) {
        Intrinsics.checkNotNull(cartLine);
        mainActivity.changeCartQuantity(cartLine, 1.0d);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit renderCart$lambda$106$lambda$103(MainActivity mainActivity, CartLine cartLine) {
        mainActivity.cart.remove(cartLine.getProduct().getId());
        mainActivity.renderCart();
        return Unit.INSTANCE;
    }

    private final void changeCartQuantity(CartLine line, double delta) {
        double quantity = line.getQuantity() + delta;
        if (quantity <= 0.0d) {
            this.cart.remove(line.getProduct().getId());
        } else if (line.getProduct().getTracksInventory() && quantity > line.getProduct().getStock()) {
            Toast.makeText(this, "\u0644\u0627 \u062a\u0648\u062c\u062f \u0643\u0645\u064a\u0629 \u0625\u0636\u0627\u0641\u064a\u0629 \u0643\u0627\u0641\u064a\u0629 \u0641\u064a \u0627\u0644\u0645\u062e\u0632\u0648\u0646", 0).show();
        } else {
            line.setQuantity(quantity);
        }
        renderCart();
    }

    private final void showBackupRestoreOptions() {
        if (!Intrinsics.areEqual(this.currentUserRole, "ADMIN")) {
            Toast.makeText(this, "\u0627\u0644\u0646\u0633\u062e \u0627\u0644\u0627\u062d\u062a\u064a\u0627\u0637\u064a \u0648\u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0645\u062a\u0627\u062d\u0627\u0646 \u0644\u0644\u0645\u062f\u064a\u0631 \u0641\u0642\u0637", 1).show();
        } else {
            new AlertDialog.Builder(this).setTitle("\u0627\u0644\u0646\u0633\u062e \u0627\u0644\u0627\u062d\u062a\u064a\u0627\u0637\u064a \u0648\u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629").setItems(new String[]{"\u0625\u0646\u0634\u0627\u0621 \u0646\u0633\u062e\u0629 \u0627\u062d\u062a\u064a\u0627\u0637\u064a\u0629 \u0645\u0634\u0641\u0651\u0631\u0629", "\u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0646\u0633\u062e\u0629 \u0627\u062d\u062a\u064a\u0627\u0637\u064a\u0629"}, new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda55
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity.showBackupRestoreOptions$lambda$109(this.f$0, dialogInterface, i);
                }
            }).setNegativeButton("\u0625\u063a\u0644\u0627\u0642", (DialogInterface.OnClickListener) null).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showBackupRestoreOptions$lambda$109(final MainActivity mainActivity, DialogInterface dialogInterface, int i) {
        if (i == 0) {
            mainActivity.requestBackupPassword(false);
        } else {
            new AlertDialog.Builder(mainActivity).setTitle("\u062a\u0646\u0628\u064a\u0647 \u0642\u0628\u0644 \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629").setMessage("\u0633\u062a\u0633\u062a\u0628\u062f\u0644 \u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0645\u062a\u062c\u0631 \u0627\u0644\u0645\u0648\u062c\u0648\u062f\u0629 \u0639\u0644\u0649 \u0647\u0630\u0627 \u0627\u0644\u0647\u0627\u062a\u0641 \u0628\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0646\u0633\u062e\u0629 \u0627\u0644\u0645\u062e\u062a\u0627\u0631\u0629. \u0644\u0646 \u064a\u0628\u062f\u0623 \u0627\u0644\u0627\u0633\u062a\u0628\u062f\u0627\u0644 \u0625\u0644\u0627 \u0628\u0639\u062f \u0641\u0643 \u0627\u0644\u062a\u0634\u0641\u064a\u0631 \u0648\u0627\u0644\u062a\u062d\u0642\u0642 \u0645\u0646 \u0633\u0644\u0627\u0645\u0629 \u0642\u0627\u0639\u062f\u0629 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a\u060c \u0645\u0639 \u0627\u0644\u0627\u062d\u062a\u0641\u0627\u0638 \u0628\u0646\u0633\u062e\u0629 \u062a\u0631\u0627\u062c\u0639 \u0645\u0624\u0642\u062a\u0629.").setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u0645\u062a\u0627\u0628\u0639\u0629", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda9
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface2, int i2) {
                    this.f$0.requestBackupPassword(true);
                }
            }).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestBackupPassword(final boolean restore) {
        String str;
        MainActivity mainActivity = this;
        final EditText editText = new EditText(mainActivity);
        editText.setHint("\u0643\u0644\u0645\u0629 \u0645\u0631\u0648\u0631 \u0627\u0644\u0646\u0633\u062e\u0629 (10 \u0623\u062d\u0631\u0641 \u0639\u0644\u0649 \u0627\u0644\u0623\u0642\u0644)");
        editText.setInputType(129);
        editText.setSingleLine(true);
        editText.setPadding(dp(14), dp(10), dp(14), dp(10));
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(20), dp(8), dp(20), dp(4));
        linearLayout.addView(editText, matchWidth());
        if (restore) {
            str = "\u0623\u062f\u062e\u0644 \u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631 \u0627\u0644\u062a\u064a \u0627\u0633\u062a\u064f\u062e\u062f\u0645\u062a \u0639\u0646\u062f \u0625\u0646\u0634\u0627\u0621 \u0647\u0630\u0647 \u0627\u0644\u0646\u0633\u062e\u0629.";
        } else {
            str = "\u0627\u062d\u062a\u0641\u0638 \u0628\u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631 \u0641\u064a \u0645\u0643\u0627\u0646 \u0622\u0645\u0646\u061b \u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0633\u062a\u0639\u0627\u062f\u0629 \u0627\u0644\u0646\u0633\u062e\u0629 \u062f\u0648\u0646\u0647\u0627.";
        }
        linearLayout.addView(label(str, 12.0f, this.mutedColor, false, 5));
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(mainActivity).setTitle(restore ? "\u0643\u0644\u0645\u0629 \u0645\u0631\u0648\u0631 \u0627\u0644\u0646\u0633\u062e\u0629" : "\u062d\u0645\u0627\u064a\u0629 \u0627\u0644\u0646\u0633\u062e\u0629 \u0627\u0644\u0627\u062d\u062a\u064a\u0627\u0637\u064a\u0629").setView(linearLayout).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton(restore ? "\u0627\u062e\u062a\u064a\u0627\u0631 \u0645\u0644\u0641 \u0627\u0644\u0646\u0633\u062e\u0629" : "\u062d\u0641\u0638 \u0627\u0644\u0646\u0633\u062e\u0629", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda137
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.requestBackupPassword$lambda$112(dialogInterface, i);
            }
        }).create();
        alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda138
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                MainActivity.requestBackupPassword$lambda$116(alertDialogCreate, editText, this, restore, dialogInterface);
            }
        });
        alertDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestBackupPassword$lambda$116(final AlertDialog alertDialog, final EditText editText, final MainActivity mainActivity, final boolean z, DialogInterface dialogInterface) {
        alertDialog.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda95
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.requestBackupPassword$lambda$116$lambda$115(editText, mainActivity, z, alertDialog, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestBackupPassword$lambda$116$lambda$115(EditText editText, MainActivity mainActivity, boolean z, AlertDialog alertDialog, View view) {
        Intent intent;
        String string = editText.getText().toString();
        if (string.length() < 10) {
            editText.setError("\u0627\u0633\u062a\u062e\u062f\u0645 10 \u0623\u062d\u0631\u0641 \u0639\u0644\u0649 \u0627\u0644\u0623\u0642\u0644");
            return;
        }
        char[] cArr = mainActivity.pendingBackupPassword;
        if (cArr != null) {
            ArraysKt.fill$default(cArr, (char) 0, 0, 0, 6, (Object) null);
        }
        char[] charArray = string.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "toCharArray(...)");
        mainActivity.pendingBackupPassword = charArray;
        if (z) {
            intent = new Intent("android.intent.action.OPEN_DOCUMENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("application/octet-stream");
        } else {
            intent = new Intent("android.intent.action.CREATE_DOCUMENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("application/octet-stream");
            intent.putExtra("android.intent.extra.TITLE", "The_Daily_Shop_" + new SimpleDateFormat("yyyyMMdd_HHmm", Locale.US).format(new Date()) + ".tdsbackup");
        }
        try {
            mainActivity.startActivityForResult(intent, z ? mainActivity.backupRestoreRequestCode : mainActivity.backupCreateRequestCode);
            alertDialog.dismiss();
        } catch (Exception unused) {
            char[] cArr2 = mainActivity.pendingBackupPassword;
            if (cArr2 != null) {
                ArraysKt.fill$default(cArr2, (char) 0, 0, 0, 6, (Object) null);
            }
            mainActivity.pendingBackupPassword = null;
            Toast.makeText(mainActivity, "\u0644\u0627 \u064a\u062a\u0648\u0641\u0631 \u0645\u062f\u064a\u0631 \u0645\u0644\u0641\u0627\u062a \u0644\u0627\u062e\u062a\u064a\u0627\u0631 \u0645\u0648\u0642\u0639 \u0627\u0644\u0646\u0633\u062e\u0629", 1).show();
        }
    }

    /* JADX WARN: Code duplicated, block: B:78:0x022d A[PHI: r23
      0x022d: PHI (r23v18 java.util.Iterator) = (r23v4 java.util.Iterator), (r23v5 java.util.Iterator), (r23v19 java.util.Iterator) binds: [B:76:0x022a, B:73:0x021f, B:57:0x01e8] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:79:0x0232 A[PHI: r22 r23
      0x0232: PHI (r22v4 java.lang.Object) = 
      (r22v1 java.lang.Object)
      (r22v1 java.lang.Object)
      (r22v1 java.lang.Object)
      (r22v2 java.lang.Object)
      (r22v1 java.lang.Object)
      (r22v1 java.lang.Object)
      (r22v1 java.lang.Object)
      (r22v1 java.lang.Object)
      (r22v1 java.lang.Object)
      (r22v1 java.lang.Object)
      (r22v1 java.lang.Object)
      (r22v1 java.lang.Object)
      (r22v1 java.lang.Object)
      (r22v1 java.lang.Object)
      (r22v1 java.lang.Object)
      (r22v1 java.lang.Object)
     binds: [B:101:?, B:76:0x022a, B:73:0x021f, B:69:0x0210, B:110:?, B:109:?, B:108:?, B:107:?, B:106:?, B:105:?, B:103:?, B:104:?, B:102:?, B:25:0x018b, B:99:?, B:100:?] A[DONT_GENERATE, DONT_INLINE]
      0x0232: PHI (r23v21 java.util.Iterator) = 
      (r23v3 java.util.Iterator)
      (r23v4 java.util.Iterator)
      (r23v5 java.util.Iterator)
      (r23v7 java.util.Iterator)
      (r23v9 java.util.Iterator)
      (r23v11 java.util.Iterator)
      (r23v12 java.util.Iterator)
      (r23v13 java.util.Iterator)
      (r23v14 java.util.Iterator)
      (r23v15 java.util.Iterator)
      (r23v16 java.util.Iterator)
      (r23v17 java.util.Iterator)
      (r23v20 java.util.Iterator)
      (r23v24 java.util.Iterator)
      (r23v25 java.util.Iterator)
      (r23v26 java.util.Iterator)
     binds: [B:101:?, B:76:0x022a, B:73:0x021f, B:69:0x0210, B:110:?, B:109:?, B:108:?, B:107:?, B:106:?, B:105:?, B:103:?, B:104:?, B:102:?, B:25:0x018b, B:99:?, B:100:?] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:81:0x0235  */
    /* JADX WARN: Code duplicated, block: B:97:0x0238 A[SYNTHETIC] */
    /* JADX WARN: Failed to find 'out' block for switch in B:27:0x0199. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v5, types: [T, android.widget.PopupWindow] */
    private final void showNavigationMenu(View anchor) {
        Object objM381constructorimpl;
        PopupWindow popupWindow;
        String str;
        Iterator it;
        Object obj;
        boolean zHasPermission;
        MainActivity mainActivity = this;
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(16), dp(18), dp(16), dp(18));
        linearLayout.setBackgroundColor(-1);
        TextView textViewLabel = label("THE DAILY SHOP", 18.0f, this.green, true, 5);
        textViewLabel.setPadding(0, 0, 0, dp(18));
        linearLayout.addView(textViewLabel);
        List listListOf = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to("\u0646\u0642\u0637\u0629 \u0627\u0644\u0628\u064a\u0639", "\u0648\u0627\u062c\u0647\u0629 \u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u0631\u0626\u064a\u0633\u064a\u0629"), TuplesKt.to("\u0627\u0644\u0645\u0646\u062a\u062c\u0627\u062a \u0648\u0627\u0644\u0645\u062e\u0632\u0648\u0646", "\u0625\u062f\u0627\u0631\u0629 \u0627\u0644\u0645\u0646\u062a\u062c\u0627\u062a \u0648\u0627\u0644\u0643\u0645\u064a\u0627\u062a"), TuplesKt.to("\u062a\u0633\u0648\u064a\u0627\u062a \u0627\u0644\u0645\u062e\u0632\u0648\u0646", "\u062a\u0633\u062c\u064a\u0644 \u0641\u0631\u0648\u0642\u0627\u062a \u0627\u0644\u062c\u0631\u062f \u0628\u0627\u0644\u0632\u064a\u0627\u062f\u0629 \u0623\u0648 \u0627\u0644\u0646\u0642\u0635\u0627\u0646"), TuplesKt.to("\u0645\u0631\u062a\u062c\u0639\u0627\u062a \u0627\u0644\u0645\u0628\u064a\u0639\u0627\u062a", "\u0625\u0631\u062c\u0627\u0639 \u0623\u0635\u0646\u0627\u0641 \u0648\u0631\u062f \u0627\u0644\u0645\u0628\u0627\u0644\u063a \u0644\u0644\u0639\u0645\u0644\u0627\u0621"), TuplesKt.to("\u0645\u0631\u062a\u062c\u0639\u0627\u062a \u0627\u0644\u0645\u0634\u062a\u0631\u064a\u0627\u062a", "\u0625\u0631\u062c\u0627\u0639 \u0627\u0644\u0628\u0636\u0627\u0639\u0629 \u0648\u062a\u0633\u0648\u064a\u0629 \u0645\u0633\u062a\u062d\u0642\u0627\u062a \u0627\u0644\u0645\u0648\u0631\u062f"), TuplesKt.to("\u0627\u0644\u0645\u0635\u0627\u0631\u064a\u0641 \u0627\u0644\u062a\u0634\u063a\u064a\u0644\u064a\u0629", "\u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u0645\u0635\u0631\u0648\u0641\u0627\u062a \u0648\u0631\u0628\u0637\u0647\u0627 \u0628\u0627\u0644\u0635\u0646\u062f\u0648\u0642 \u0648\u0635\u0627\u0641\u064a \u0627\u0644\u0631\u0628\u062d"), TuplesKt.to("\u0627\u0644\u0645\u0634\u062a\u0631\u064a\u0627\u062a", "\u0641\u0648\u0627\u062a\u064a\u0631 \u0627\u0644\u0634\u0631\u0627\u0621 \u0648\u0627\u0644\u0645\u0648\u0631\u062f\u0648\u0646"), TuplesKt.to("\u0627\u0644\u0639\u0645\u0644\u0627\u0621 \u0648\u0627\u0644\u062f\u064a\u0648\u0646", "\u0645\u0644\u0641\u0627\u062a \u0627\u0644\u0639\u0645\u0644\u0627\u0621 \u0648\u0627\u0644\u0623\u0631\u0635\u062f\u0629"), TuplesKt.to("\u0627\u0644\u0635\u0646\u0627\u062f\u064a\u0642 \u0648\u0627\u0644\u0645\u062d\u0627\u0641\u0638", "\u0627\u0644\u062d\u0631\u0643\u0629 \u0627\u0644\u0646\u0642\u062f\u064a\u0629 \u0648\u0627\u0644\u062d\u0633\u0627\u0628\u0627\u062a"), TuplesKt.to("\u062a\u062d\u0648\u064a\u0644 \u0631\u0635\u064a\u062f", "\u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0627\u0644\u0639\u0627\u062f\u064a \u0648\u0643\u0627\u0634 \u0648\u0634\u0627\u0645 \u0643\u0627\u0634"), TuplesKt.to("\u0627\u0644\u062a\u0642\u0627\u0631\u064a\u0631", "\u0627\u0644\u0645\u0628\u064a\u0639\u0627\u062a \u0648\u0627\u0644\u0623\u0631\u0628\u0627\u062d \u0648\u0627\u0644\u0645\u062e\u0632\u0648\u0646"), TuplesKt.to("\u0627\u0644\u0646\u0633\u062e \u0627\u0644\u0627\u062d\u062a\u064a\u0627\u0637\u064a \u0648\u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629", "\u062d\u0641\u0638 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0645\u062a\u062c\u0631 \u0648\u0627\u0633\u062a\u0639\u0627\u062f\u062a\u0647\u0627 \u0628\u0623\u0645\u0627\u0646"), TuplesKt.to("\u0645\u0632\u0627\u0645\u0646\u0629 \u0627\u0644\u0623\u062c\u0647\u0632\u0629", "\u0625\u062f\u0627\u0631\u0629 \u062c\u0630\u0631 \u0627\u0644\u062b\u0642\u0629 \u0648\u0634\u0647\u0627\u062f\u0627\u062a \u0627\u0644\u0623\u062c\u0647\u0632\u0629 \u0648\u0627\u0644\u0627\u062a\u0635\u0627\u0644 \u0627\u0644\u0622\u0645\u0646"), TuplesKt.to("\u0625\u0639\u062f\u0627\u062f\u0627\u062a \u0627\u0644\u0645\u062a\u062c\u0631", "\u0627\u062e\u062a\u064a\u0627\u0631 \u0627\u0644\u0645\u0646\u0637\u0642\u0629 \u0627\u0644\u0632\u0645\u0646\u064a\u0629 \u0627\u0644\u062a\u064a \u062a\u0639\u062a\u0645\u062f\u0647\u0627 \u062c\u0645\u064a\u0639 \u0623\u062c\u0647\u0632\u0629 \u0627\u0644\u0645\u062a\u062c\u0631"), TuplesKt.to("\u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645\u0648\u0646 \u0648\u0627\u0644\u0635\u0644\u0627\u062d\u064a\u0627\u062a", "\u0625\u0636\u0627\u0641\u0629 \u0627\u0644\u0645\u0648\u0638\u0641\u064a\u0646 \u0648\u0627\u0644\u062a\u062d\u0643\u0645 \u0628\u0635\u0644\u0627\u062d\u064a\u0627\u062a\u0647\u0645")});
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Object obj2 = "\u0645\u0631\u062a\u062c\u0639\u0627\u062a \u0627\u0644\u0645\u0634\u062a\u0631\u064a\u0627\u062a";
        SyncProtocolRepository syncProtocolRepository = new SyncProtocolRepository(mainActivity, null, 2, 0 == true ? 1 : 0);
        try {
            Result.Companion companion = Result.INSTANCE;
            MainActivity mainActivity2 = this;
            objM381constructorimpl = Result.m381constructorimpl(syncProtocolRepository.localDeviceRole());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m387isFailureimpl(objM381constructorimpl)) {
            objM381constructorimpl = null;
        }
        String str2 = (String) objM381constructorimpl;
        boolean zAreEqual = Intrinsics.areEqual(str2, "SECONDARY");
        ArrayList<Pair> arrayList = new ArrayList();
        Iterator it2 = listListOf.iterator();
        while (it2.hasNext()) {
            MainActivity mainActivity3 = mainActivity;
            Object next = it2.next();
            Pair pair = (Pair) next;
            if (str2 == null) {
                str = str2;
                zHasPermission = Intrinsics.areEqual(pair.getFirst(), "\u0645\u0632\u0627\u0645\u0646\u0629 \u0627\u0644\u0623\u062c\u0647\u0632\u0629");
            } else {
                str = str2;
                if (!zAreEqual) {
                    String str3 = (String) pair.getFirst();
                    switch (str3.hashCode()) {
                        case -1620452016:
                            it = it2;
                            if (str3.equals("\u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645\u0648\u0646 \u0648\u0627\u0644\u0635\u0644\u0627\u062d\u064a\u0627\u062a")) {
                                zHasPermission = isManager();
                            } else {
                                zHasPermission = true;
                            }
                            break;
                        case -1494544582:
                            it = it2;
                            if (str3.equals("\u0627\u0644\u0645\u0634\u062a\u0631\u064a\u0627\u062a")) {
                                zHasPermission = isManager();
                            } else {
                                zHasPermission = true;
                            }
                            break;
                        case -1249651480:
                            it = it2;
                            obj = obj2;
                            if (str3.equals(obj)) {
                                if (isManager() || !hasPermission(PermissionPolicy.RETURNS_PROCESS)) {
                                    obj2 = obj;
                                    zHasPermission = false;
                                }
                            }
                            obj2 = obj;
                            zHasPermission = true;
                            break;
                        case -1158812380:
                            if (str3.equals("\u0645\u0631\u062a\u062c\u0639\u0627\u062a \u0627\u0644\u0645\u0628\u064a\u0639\u0627\u062a")) {
                                it = it2;
                                obj = obj2;
                                if (isManager()) {
                                }
                                obj2 = obj;
                                zHasPermission = false;
                            } else {
                                it = it2;
                            }
                            zHasPermission = true;
                            break;
                        case -395694874:
                            if (!str3.equals("\u0645\u0632\u0627\u0645\u0646\u0629 \u0627\u0644\u0623\u062c\u0647\u0632\u0629")) {
                                it = it2;
                                zHasPermission = true;
                            }
                            it = it2;
                            zHasPermission = isManager();
                            break;
                        case -333039944:
                            if (str3.equals("\u062a\u0633\u0648\u064a\u0627\u062a \u0627\u0644\u0645\u062e\u0632\u0648\u0646")) {
                                zHasPermission = hasPermission(PermissionPolicy.INVENTORY_ADJUST);
                            } else {
                                it = it2;
                                zHasPermission = true;
                            }
                            break;
                        case -192885658:
                            if (!str3.equals("\u0627\u0644\u0646\u0633\u062e \u0627\u0644\u0627\u062d\u062a\u064a\u0627\u0637\u064a \u0648\u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629")) {
                                it = it2;
                                zHasPermission = true;
                            }
                            it = it2;
                            zHasPermission = isManager();
                            break;
                        case -57351713:
                            if (!str3.equals("\u0627\u0644\u0645\u0646\u062a\u062c\u0627\u062a \u0648\u0627\u0644\u0645\u062e\u0632\u0648\u0646")) {
                                it = it2;
                                zHasPermission = true;
                            }
                            it = it2;
                            zHasPermission = isManager();
                            break;
                        case 812489313:
                            if (!str3.equals("\u0625\u0639\u062f\u0627\u062f\u0627\u062a \u0627\u0644\u0645\u062a\u062c\u0631")) {
                                it = it2;
                                zHasPermission = true;
                            }
                            it = it2;
                            zHasPermission = isManager();
                            break;
                        case 994055226:
                            if (str3.equals("\u0627\u0644\u0645\u0635\u0627\u0631\u064a\u0641 \u0627\u0644\u062a\u0634\u063a\u064a\u0644\u064a\u0629")) {
                                if (isManager() && hasPermission(PermissionPolicy.EXPENSES_MANAGE)) {
                                    it = it2;
                                }
                                it = it2;
                                zHasPermission = false;
                            } else {
                                it = it2;
                            }
                            zHasPermission = true;
                            break;
                        case 1824040998:
                            if (!str3.equals("\u0627\u0644\u062a\u0642\u0627\u0631\u064a\u0631")) {
                                it = it2;
                                zHasPermission = true;
                            }
                            it = it2;
                            zHasPermission = isManager();
                            break;
                        default:
                            it = it2;
                            zHasPermission = true;
                            break;
                    }
                } else {
                    if (Intrinsics.areEqual(pair.getFirst(), "\u0646\u0642\u0637\u0629 \u0627\u0644\u0628\u064a\u0639") || Intrinsics.areEqual(pair.getFirst(), "\u062a\u062d\u0648\u064a\u0644 \u0631\u0635\u064a\u062f") || Intrinsics.areEqual(pair.getFirst(), "\u0645\u0632\u0627\u0645\u0646\u0629 \u0627\u0644\u0623\u062c\u0647\u0632\u0629")) {
                        it = it2;
                        zHasPermission = true;
                    }
                    it = it2;
                    zHasPermission = false;
                }
                if (zHasPermission) {
                    arrayList.add(next);
                }
                mainActivity = mainActivity3;
                it2 = it;
                str2 = str;
            }
            it = it2;
            if (zHasPermission) {
                arrayList.add(next);
            }
            mainActivity = mainActivity3;
            it2 = it;
            str2 = str;
        }
        MainActivity mainActivity4 = mainActivity;
        for (Pair pair2 : arrayList) {
            final String str4 = (String) pair2.component1();
            String str5 = (String) pair2.component2();
            LinearLayout linearLayout2 = new LinearLayout(mainActivity4);
            linearLayout2.setOrientation(1);
            linearLayout2.setPadding(dp(8), dp(10), dp(8), dp(10));
            linearLayout2.addView(label(str4, 15.0f, this.textColor, true, 5));
            linearLayout2.addView(label(str5, 11.0f, this.mutedColor, false, 5));
            linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda77
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws IOException {
                    MainActivity.showNavigationMenu$lambda$124$lambda$123$lambda$122(objectRef, str4, this, view);
                }
            });
            linearLayout.addView(linearLayout2, matchWidth());
        }
        Button button = new Button(mainActivity4);
        button.setText("\u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062e\u0631\u0648\u062c");
        button.setAllCaps(false);
        button.setTextColor(-1);
        button.setBackgroundColor(this.green);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda79
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.showNavigationMenu$lambda$126$lambda$125(objectRef, this, view);
            }
        });
        linearLayout.addView(button, fieldParams(dp(50), 0));
        ScrollView scrollView = new ScrollView(mainActivity4);
        scrollView.setFillViewport(true);
        scrollView.setVerticalScrollBarEnabled(true);
        scrollView.addView(linearLayout, new ViewGroup.LayoutParams(-1, -2));
        ?? popupWindow2 = new PopupWindow((View) scrollView, dp(285), -1, true);
        popupWindow2.setElevation(dp(12));
        popupWindow2.setBackgroundDrawable(cardBackground());
        objectRef.element = popupWindow2;
        if (objectRef.element == 0) {
            Intrinsics.throwUninitializedPropertyAccessException("popup");
            popupWindow = null;
        } else {
            popupWindow = (PopupWindow) objectRef.element;
        }
        popupWindow.showAtLocation(getWindow().getDecorView(), GravityCompat.END, 0, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void showNavigationMenu$lambda$124$lambda$123$lambda$122(Ref.ObjectRef objectRef, String str, MainActivity mainActivity, View view) throws IOException {
        PopupWindow popupWindow;
        if (objectRef.element == 0) {
            Intrinsics.throwUninitializedPropertyAccessException("popup");
            popupWindow = null;
        } else {
            popupWindow = (PopupWindow) objectRef.element;
        }
        popupWindow.dismiss();
        switch (str.hashCode()) {
            case -1620452016:
                if (str.equals("\u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645\u0648\u0646 \u0648\u0627\u0644\u0635\u0644\u0627\u062d\u064a\u0627\u062a")) {
                    mainActivity.showUserManagement();
                    return;
                }
                break;
            case -1494544582:
                if (str.equals("\u0627\u0644\u0645\u0634\u062a\u0631\u064a\u0627\u062a")) {
                    mainActivity.showPurchases();
                    return;
                }
                break;
            case -1249651480:
                if (str.equals("\u0645\u0631\u062a\u062c\u0639\u0627\u062a \u0627\u0644\u0645\u0634\u062a\u0631\u064a\u0627\u062a")) {
                    mainActivity.showPurchaseReturns();
                    return;
                }
                break;
            case -1158812380:
                if (str.equals("\u0645\u0631\u062a\u062c\u0639\u0627\u062a \u0627\u0644\u0645\u0628\u064a\u0639\u0627\u062a")) {
                    mainActivity.showSalesReturns();
                    return;
                }
                break;
            case -395694874:
                if (str.equals("\u0645\u0632\u0627\u0645\u0646\u0629 \u0627\u0644\u0623\u062c\u0647\u0632\u0629")) {
                    String str2 = mainActivity.currentUserId;
                    if (str2 != null) {
                        mainActivity.startActivity(SyncDeviceManagementActivity.INSTANCE.intent(mainActivity, str2));
                        return;
                    }
                    return;
                }
                break;
            case -333039944:
                if (str.equals("\u062a\u0633\u0648\u064a\u0627\u062a \u0627\u0644\u0645\u062e\u0632\u0648\u0646")) {
                    mainActivity.showInventoryAdjustments();
                    return;
                }
                break;
            case -326072541:
                if (str.equals("\u0627\u0644\u0635\u0646\u0627\u062f\u064a\u0642 \u0648\u0627\u0644\u0645\u062d\u0627\u0641\u0638")) {
                    mainActivity.showCashAccounts();
                    return;
                }
                break;
            case -192885658:
                if (str.equals("\u0627\u0644\u0646\u0633\u062e \u0627\u0644\u0627\u062d\u062a\u064a\u0627\u0637\u064a \u0648\u0627\u0644\u0627\u0633\u062a\u0639\u0627\u062f\u0629")) {
                    mainActivity.showBackupRestoreOptions();
                    return;
                }
                break;
            case -57351713:
                if (str.equals("\u0627\u0644\u0645\u0646\u062a\u062c\u0627\u062a \u0648\u0627\u0644\u0645\u062e\u0632\u0648\u0646")) {
                    mainActivity.showProductManagement();
                    return;
                }
                break;
            case 502552179:
                if (str.equals("\u0627\u0644\u0639\u0645\u0644\u0627\u0621 \u0648\u0627\u0644\u062f\u064a\u0648\u0646")) {
                    mainActivity.showCustomers();
                    return;
                }
                break;
            case 812489313:
                if (str.equals("\u0625\u0639\u062f\u0627\u062f\u0627\u062a \u0627\u0644\u0645\u062a\u062c\u0631")) {
                    mainActivity.showStoreTimeZoneSettings();
                    return;
                }
                break;
            case 994055226:
                if (str.equals("\u0627\u0644\u0645\u0635\u0627\u0631\u064a\u0641 \u0627\u0644\u062a\u0634\u063a\u064a\u0644\u064a\u0629")) {
                    mainActivity.showExpenses();
                    return;
                }
                break;
            case 1534073962:
                if (str.equals("\u062a\u062d\u0648\u064a\u0644 \u0631\u0635\u064a\u062f")) {
                    mainActivity.showServiceTransfers();
                    return;
                }
                break;
            case 1609164072:
                if (str.equals("\u0646\u0642\u0637\u0629 \u0627\u0644\u0628\u064a\u0639")) {
                    showPointOfSale$default(mainActivity, false, 1, null);
                    return;
                }
                break;
            case 1824040998:
                if (str.equals("\u0627\u0644\u062a\u0642\u0627\u0631\u064a\u0631")) {
                    mainActivity.showGeneralReports();
                    return;
                }
                break;
        }
        Toast.makeText(mainActivity, "\u0642\u0633\u0645 " + str + " \u0633\u064a\u064f\u0641\u0639\u0651\u0644 \u0641\u064a \u0645\u0631\u062d\u0644\u062a\u0647", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void showNavigationMenu$lambda$126$lambda$125(Ref.ObjectRef objectRef, MainActivity mainActivity, View view) {
        PopupWindow popupWindow;
        if (objectRef.element == 0) {
            Intrinsics.throwUninitializedPropertyAccessException("popup");
            popupWindow = null;
        } else {
            popupWindow = (PopupWindow) objectRef.element;
        }
        popupWindow.dismiss();
        mainActivity.clearSessionState();
        mainActivity.showLoginScreen();
    }

    private final void showStoreTimeZoneSettings() {
        if (!isManager()) {
            Toast.makeText(this, "\u0625\u0639\u062f\u0627\u062f\u0627\u062a \u0627\u0644\u0645\u062a\u062c\u0631 \u0645\u062a\u0627\u062d\u0629 \u0644\u0644\u0645\u062f\u064a\u0631 \u0641\u0642\u0637", 0).show();
            return;
        }
        MainActivity mainActivity = this;
        final StoreRepository storeRepository = new StoreRepository(mainActivity);
        String storeTimeZoneId = storeRepository.getStoreTimeZoneId();
        final AutoCompleteTextView autoCompleteTextView = new AutoCompleteTextView(mainActivity);
        autoCompleteTextView.setText((CharSequence) storeTimeZoneId, false);
        autoCompleteTextView.setHint("\u0645\u062b\u0627\u0644: Asia/Damascus");
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setSingleLine(true);
        autoCompleteTextView.setPadding(dp(12), dp(12), dp(12), dp(12));
        autoCompleteTextView.setAdapter(new ArrayAdapter(mainActivity, android.R.layout.simple_dropdown_item_1line, StoreTime.INSTANCE.availableZoneIds()));
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(20), dp(8), dp(20), dp(4));
        linearLayout.addView(label("\u0627\u0644\u0645\u0646\u0637\u0642\u0629 \u0627\u0644\u0632\u0645\u0646\u064a\u0629 \u0627\u0644\u062d\u0627\u0644\u064a\u0629: " + storeTimeZoneId, 13.0f, this.green, true, 5));
        linearLayout.addView(autoCompleteTextView, fieldParams(dp(54), dp(10)));
        TextView textViewLabel = label("\u0627\u062e\u062a\u0631 \u0645\u0639\u0631\u0651\u0641 \u0627\u0644\u0645\u0646\u0637\u0642\u0629 \u0627\u0644\u0632\u0645\u0646\u064a\u0629 \u0644\u0644\u0645\u062f\u064a\u0646\u0629 \u0627\u0644\u062a\u064a \u064a\u0648\u062c\u062f \u0641\u064a\u0647\u0627 \u0627\u0644\u0645\u062a\u062c\u0631. \u0633\u062a\u0639\u062a\u0645\u062f \u062c\u0645\u064a\u0639 \u0627\u0644\u0623\u062c\u0647\u0632\u0629 \u0627\u0644\u0645\u0631\u062a\u0628\u0637\u0629 \u0647\u0630\u0627 \u0627\u0644\u062a\u0648\u0642\u064a\u062a\u060c \u0628\u0645\u0627 \u0641\u064a \u0630\u0644\u0643 \u0642\u0648\u0627\u0639\u062f \u0627\u0644\u062a\u0648\u0642\u064a\u062a \u0627\u0644\u0635\u064a\u0641\u064a \u0625\u0646 \u0648\u062c\u062f\u062a. \u0644\u0646 \u062a\u062a\u063a\u064a\u0631 \u062a\u0648\u0627\u0631\u064a\u062e \u0627\u0644\u062d\u0631\u0643\u0627\u062a \u0627\u0644\u0633\u0627\u0628\u0642\u0629.", 12.0f, this.mutedColor, false, 5);
        textViewLabel.setPadding(0, dp(8), 0, 0);
        linearLayout.addView(textViewLabel);
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(mainActivity).setTitle("\u0627\u0644\u0645\u0646\u0637\u0642\u0629 \u0627\u0644\u0632\u0645\u0646\u064a\u0629 \u0644\u0644\u0645\u062a\u062c\u0631").setView(linearLayout).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u062d\u0641\u0638 \u0627\u0644\u0625\u0639\u062f\u0627\u062f", (DialogInterface.OnClickListener) null).create();
        alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda172
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                MainActivity.showStoreTimeZoneSettings$lambda$133(alertDialogCreate, autoCompleteTextView, storeRepository, this, dialogInterface);
            }
        });
        alertDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showStoreTimeZoneSettings$lambda$133(final AlertDialog alertDialog, final AutoCompleteTextView autoCompleteTextView, final StoreRepository storeRepository, final MainActivity mainActivity, DialogInterface dialogInterface) {
        alertDialog.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda149
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.showStoreTimeZoneSettings$lambda$133$lambda$132(autoCompleteTextView, storeRepository, mainActivity, alertDialog, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showStoreTimeZoneSettings$lambda$133$lambda$132(AutoCompleteTextView autoCompleteTextView, StoreRepository storeRepository, MainActivity mainActivity, AlertDialog alertDialog, View view) {
        String string = StringsKt.trim((CharSequence) autoCompleteTextView.getText().toString()).toString();
        if (!StoreTime.INSTANCE.isValidZoneId(string)) {
            autoCompleteTextView.setError("\u0627\u062e\u062a\u0631 \u0645\u0646\u0637\u0642\u0629 \u0632\u0645\u0646\u064a\u0629 \u0635\u062d\u064a\u062d\u0629 \u0645\u0646 \u0627\u0644\u0642\u0627\u0626\u0645\u0629");
            return;
        }
        try {
            String str = mainActivity.currentUserId;
            if (str != null) {
                storeRepository.saveStoreTimeZoneId(string, str);
                alertDialog.dismiss();
                Toast.makeText(mainActivity, "\u062a\u0645 \u0627\u0639\u062a\u0645\u0627\u062f \u062a\u0648\u0642\u064a\u062a \u0627\u0644\u0645\u062a\u062c\u0631: " + string, 1).show();
                return;
            }
            throw new IllegalStateException("\u0627\u0646\u062a\u0647\u062a \u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645".toString());
        } catch (Exception e) {
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u062d\u0641\u0638 \u0625\u0639\u062f\u0627\u062f \u0627\u0644\u0645\u0646\u0637\u0642\u0629 \u0627\u0644\u0632\u0645\u0646\u064a\u0629";
            }
            autoCompleteTextView.setError(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0007H\u00c6\u0003J'\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001J\t\u0010\u001d\u001a\u00020\u001eH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013\u00a8\u0006\u001f"}, d2 = {"Lcom/hazem/dailystore/MainActivity$PurchaseLineDraft;", "", "product", "Lcom/hazem/dailystore/data/StoreProduct;", "quantity", "Ljava/math/BigDecimal;", "unitCostUsdCents", "", "<init>", "(Lcom/hazem/dailystore/data/StoreProduct;Ljava/math/BigDecimal;J)V", "getProduct", "()Lcom/hazem/dailystore/data/StoreProduct;", "getQuantity", "()Ljava/math/BigDecimal;", "setQuantity", "(Ljava/math/BigDecimal;)V", "getUnitCostUsdCents", "()J", "setUnitCostUsdCents", "(J)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    static final /* data */ class PurchaseLineDraft {
        private final StoreProduct product;
        private BigDecimal quantity;
        private long unitCostUsdCents;

        public static /* synthetic */ PurchaseLineDraft copy$default(PurchaseLineDraft purchaseLineDraft, StoreProduct storeProduct, BigDecimal bigDecimal, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                storeProduct = purchaseLineDraft.product;
            }
            if ((i & 2) != 0) {
                bigDecimal = purchaseLineDraft.quantity;
            }
            if ((i & 4) != 0) {
                j = purchaseLineDraft.unitCostUsdCents;
            }
            return purchaseLineDraft.copy(storeProduct, bigDecimal, j);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final StoreProduct getProduct() {
            return this.product;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final BigDecimal getQuantity() {
            return this.quantity;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getUnitCostUsdCents() {
            return this.unitCostUsdCents;
        }

        public final PurchaseLineDraft copy(StoreProduct product, BigDecimal quantity, long unitCostUsdCents) {
            Intrinsics.checkNotNullParameter(product, "product");
            Intrinsics.checkNotNullParameter(quantity, "quantity");
            return new PurchaseLineDraft(product, quantity, unitCostUsdCents);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PurchaseLineDraft)) {
                return false;
            }
            PurchaseLineDraft purchaseLineDraft = (PurchaseLineDraft) other;
            return Intrinsics.areEqual(this.product, purchaseLineDraft.product) && Intrinsics.areEqual(this.quantity, purchaseLineDraft.quantity) && this.unitCostUsdCents == purchaseLineDraft.unitCostUsdCents;
        }

        public int hashCode() {
            return (((this.product.hashCode() * 31) + this.quantity.hashCode()) * 31) + MainActivity$$ExternalSyntheticBackport0.m(this.unitCostUsdCents);
        }

        public String toString() {
            return "PurchaseLineDraft(product=" + this.product + ", quantity=" + this.quantity + ", unitCostUsdCents=" + this.unitCostUsdCents + ")";
        }

        public PurchaseLineDraft(StoreProduct product, BigDecimal quantity, long j) {
            Intrinsics.checkNotNullParameter(product, "product");
            Intrinsics.checkNotNullParameter(quantity, "quantity");
            this.product = product;
            this.quantity = quantity;
            this.unitCostUsdCents = j;
        }

        public final StoreProduct getProduct() {
            return this.product;
        }

        public final BigDecimal getQuantity() {
            return this.quantity;
        }

        public final void setQuantity(BigDecimal bigDecimal) {
            Intrinsics.checkNotNullParameter(bigDecimal, "<set-?>");
            this.quantity = bigDecimal;
        }

        public final long getUnitCostUsdCents() {
            return this.unitCostUsdCents;
        }

        public final void setUnitCostUsdCents(long j) {
            this.unitCostUsdCents = j;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void showPurchases() throws IOException {
        char c;
        if (!isManager()) {
            Toast.makeText(this, "\u0625\u062f\u0627\u0631\u0629 \u0627\u0644\u0645\u0634\u062a\u0631\u064a\u0627\u062a \u0645\u062a\u0627\u062d\u0629 \u0644\u0644\u0645\u062f\u064a\u0631 \u0641\u0642\u0637", 0).show();
            return;
        }
        markScreen$default(this, "PURCHASES", null, 2, null);
        MainActivity mainActivity = this;
        PurchasePaymentRepository purchasePaymentRepository = new PurchasePaymentRepository(mainActivity);
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(this.backgroundColor);
        LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        linearLayout2.setPadding(dp(14), dp(10), dp(14), dp(10));
        linearLayout2.setBackgroundColor(-1);
        Button button = new Button(mainActivity);
        button.setText("\u2039");
        button.setTextSize(25.0f);
        button.setAllCaps(false);
        button.setTextColor(this.green);
        button.setBackgroundColor(0);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda109
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IOException {
                MainActivity.showPointOfSale$default(this.f$0, false, 1, null);
            }
        });
        linearLayout2.addView(button, new LinearLayout.LayoutParams(dp(48), dp(48)));
        linearLayout2.addView(label("\u0627\u0644\u0645\u0634\u062a\u0631\u064a\u0627\u062a \u0648\u0627\u0644\u0641\u0648\u0627\u062a\u064a\u0631", 19.0f, this.green, true, 17), new LinearLayout.LayoutParams(0, -2, 1.0f));
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setFillViewport(true);
        LinearLayout linearLayout3 = new LinearLayout(mainActivity);
        linearLayout3.setOrientation(1);
        linearLayout3.setPadding(dp(16), dp(16), dp(16), dp(24));
        linearLayout3.addView(label("\u0625\u062f\u0627\u0631\u0629 \u0627\u0644\u0645\u0634\u062a\u0631\u064a\u0627\u062a", 23.0f, this.green, true, 5));
        TextView textViewLabel = label("\u062a\u0627\u0628\u0639 \u0627\u0644\u0641\u0648\u0627\u062a\u064a\u0631 \u0648\u0627\u0644\u0645\u062f\u0641\u0648\u0639\u0627\u062a \u0648\u0627\u0644\u0645\u0628\u0627\u0644\u063a \u0627\u0644\u0645\u062a\u0628\u0642\u064a\u0629 \u0644\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0645\u0646 \u0645\u0643\u0627\u0646 \u0648\u0627\u062d\u062f.", 13.0f, this.mutedColor, false, 5);
        textViewLabel.setPadding(0, dp(5), 0, dp(14));
        linearLayout3.addView(textViewLabel);
        LinearLayout linearLayout4 = new LinearLayout(mainActivity);
        linearLayout4.setOrientation(0);
        Button button2 = new Button(mainActivity);
        button2.setText("+ \u0641\u0627\u062a\u0648\u0631\u0629 \u0645\u0634\u062a\u0631\u064a\u0627\u062a");
        button2.setAllCaps(false);
        button2.setTextSize(14.0f);
        button2.setTextColor(-1);
        button2.setBackgroundColor(this.green);
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda120
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.showPurchaseInvoiceEditor();
            }
        });
        Button button3 = new Button(mainActivity);
        button3.setText("\u0627\u0644\u0645\u0648\u0631\u062f\u0648\u0646");
        button3.setAllCaps(false);
        button3.setTextSize(14.0f);
        button3.setTextColor(this.green);
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda131
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IOException {
                this.f$0.showSuppliers();
            }
        });
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, dp(50), 1.0f);
        layoutParams.setMarginEnd(dp(6));
        Unit unit = Unit.INSTANCE;
        linearLayout4.addView(button2, layoutParams);
        linearLayout4.addView(button3, new LinearLayout.LayoutParams(0, dp(50), 1.0f));
        linearLayout3.addView(linearLayout4, fieldParams(dp(50), dp(8)));
        linearLayout3.addView(smallButton("\u21a9 \u0645\u0631\u062a\u062c\u0639\u0627\u062a \u0627\u0644\u0645\u0634\u062a\u0631\u064a\u0627\u062a", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda142
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showPurchases$lambda$147(this.f$0);
            }
        }), fieldParams(dp(44), dp(4)));
        List listListPurchaseInvoices$default = PurchasePaymentRepository.listPurchaseInvoices$default(purchasePaymentRepository, null, 1, null);
        List<PurchaseInvoicePaymentSummary> list = listListPurchaseInvoices$default;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            PurchaseInvoicePaymentSummary purchaseInvoicePaymentSummary = (PurchaseInvoicePaymentSummary) obj;
            if (!Intrinsics.areEqual(purchaseInvoicePaymentSummary.getInvoiceStatus(), "DRAFT") && !Intrinsics.areEqual(purchaseInvoicePaymentSummary.getInvoiceStatus(), "CANCELLED")) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        long outstandingSypMinor = 0;
        while (it.hasNext()) {
            outstandingSypMinor += ((PurchaseInvoicePaymentSummary) it.next()).getOutstandingSypMinor();
        }
        Iterator it2 = list.iterator();
        long paidSypMinor = 0;
        while (it2.hasNext()) {
            paidSypMinor += ((PurchaseInvoicePaymentSummary) it2.next()).getPaidSypMinor();
        }
        LinearLayout linearLayout5 = new LinearLayout(mainActivity);
        linearLayout5.setOrientation(1);
        linearLayout5.setPadding(dp(14), dp(12), dp(14), dp(12));
        linearLayout5.setBackground(cardBackground());
        int i = 4;
        linearLayout5.addView(label("\u0645\u0644\u062e\u0635 \u062d\u0633\u0627\u0628\u0627\u062a \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646", 16.0f, this.green, true, 5));
        TextView textViewLabel2 = label("\u0625\u062c\u0645\u0627\u0644\u064a \u0627\u0644\u0645\u062f\u0641\u0648\u0639: " + formatSypAmount(paidSypMinor) + " \u0644.\u0633", 13.0f, this.textColor, false, 5);
        textViewLabel2.setPadding(0, dp(6), 0, dp(3));
        linearLayout5.addView(textViewLabel2);
        int i2 = 3;
        linearLayout5.addView(label("\u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0644\u0644\u0645\u0648\u0631\u062f\u064a\u0646: " + formatSypAmount(outstandingSypMinor) + " \u0644.\u0633", 14.0f, this.green, true, 5));
        linearLayout3.addView(linearLayout5, fieldParams(-2, dp(14)));
        linearLayout3.addView(sectionHeading("\u0633\u062c\u0644 \u0641\u0648\u0627\u062a\u064a\u0631 \u0627\u0644\u0634\u0631\u0627\u0621"));
        LinearLayout linearLayout6 = new LinearLayout(mainActivity);
        linearLayout6.setOrientation(1);
        linearLayout3.addView(linearLayout6, matchWidth());
        scrollView.addView(linearLayout3);
        linearLayout.addView(linearLayout2, matchWidth());
        linearLayout.addView(scrollView, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        setContentView(linearLayout);
        if (listListPurchaseInvoices$default.isEmpty()) {
            TextView textViewLabel3 = label("\u0644\u0627 \u062a\u0648\u062c\u062f \u0641\u0648\u0627\u062a\u064a\u0631 \u0645\u0634\u062a\u0631\u064a\u0627\u062a \u0628\u0639\u062f.", 14.0f, this.mutedColor, false, 17);
            textViewLabel3.setPadding(0, dp(30), 0, dp(30));
            linearLayout6.addView(textViewLabel3);
            return;
        }
        for (final PurchaseInvoicePaymentSummary purchaseInvoicePaymentSummary2 : list) {
            LinearLayout linearLayout7 = new LinearLayout(mainActivity);
            linearLayout7.setOrientation(1);
            linearLayout7.setPadding(dp(14), dp(12), dp(14), dp(12));
            linearLayout7.setBackground(cardBackground());
            linearLayout7.addView(label(purchaseInvoicePaymentSummary2.getInvoiceNumber(), 16.0f, this.green, true, 5));
            TextView textViewLabel4 = label("\u0627\u0644\u062a\u0627\u0631\u064a\u062e: " + purchaseInvoicePaymentSummary2.getDate() + "  \u2022  \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629: " + invoiceStatusArabic(purchaseInvoicePaymentSummary2.getInvoiceStatus()), 12.0f, this.mutedColor, false, 5);
            textViewLabel4.setPadding(0, dp(i), 0, dp(i));
            linearLayout7.addView(textViewLabel4);
            String supplierName = purchaseInvoicePaymentSummary2.getSupplierName();
            if (supplierName == null) {
                supplierName = "\u063a\u064a\u0631 \u0645\u062d\u062f\u062f";
            }
            linearLayout7.addView(label("\u0627\u0644\u0645\u0648\u0631\u062f: " + supplierName, 12.0f, this.mutedColor, false, 5));
            TextView textViewLabel5 = label("\u062d\u0627\u0644\u0629 \u0627\u0644\u062f\u0641\u0639: " + paymentStatusArabic(purchaseInvoicePaymentSummary2.getPaymentStatus()), 13.0f, this.green, true, 5);
            textViewLabel5.setPadding(0, dp(5), 0, dp(i2));
            linearLayout7.addView(textViewLabel5);
            linearLayout7.addView(label("\u0627\u0644\u0625\u062c\u0645\u0627\u0644\u064a \u0627\u0644\u0645\u062a\u0641\u0642 \u0639\u0644\u064a\u0647: " + formatSypAmount(purchaseInvoicePaymentSummary2.getTotalSypMinor()) + " \u0644.\u0633", 13.0f, this.textColor, false, 5));
            linearLayout7.addView(label("\u0627\u0644\u0645\u062f\u0641\u0648\u0639 \u0646\u0642\u062f\u0627\u064b: " + formatSypAmount(purchaseInvoicePaymentSummary2.getPaidSypMinor()) + " \u0644.\u0633", 13.0f, this.textColor, false, 5));
            if (purchaseInvoicePaymentSummary2.getSupplierReturnCreditSypMinor() > 0) {
                linearLayout7.addView(label("\u062a\u062e\u0641\u064a\u0636 \u0645\u0633\u062a\u062d\u0642 \u0627\u0644\u0645\u0648\u0631\u062f \u0628\u0645\u0631\u062a\u062c\u0639\u0627\u062a: " + formatSypAmount(purchaseInvoicePaymentSummary2.getSupplierReturnCreditSypMinor()) + " \u0644.\u0633", 13.0f, this.green, true, 5));
            }
            linearLayout7.addView(label("\u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0627\u0644\u062b\u0627\u0628\u062a: " + formatSypAmount(purchaseInvoicePaymentSummary2.getOutstandingSypMinor()) + " \u0644.\u0633", 14.0f, purchaseInvoicePaymentSummary2.getOutstandingSypMinor() > 0 ? this.green : this.mutedColor, true, 5));
            if (purchaseInvoicePaymentSummary2.getInvoiceRateMilli() <= 0) {
                linearLayout7.addView(label("\u062a\u0646\u0628\u064a\u0647: \u0644\u0627 \u064a\u0648\u062c\u062f \u0633\u0639\u0631 \u0635\u0631\u0641 \u0645\u062d\u0641\u0648\u0638 \u0641\u064a \u0647\u0630\u0647 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0627\u0644\u0642\u062f\u064a\u0645\u0629\u060c \u0644\u0630\u0644\u0643 \u0644\u0627 \u064a\u0645\u0643\u0646 \u062d\u0633\u0627\u0628 \u0631\u0635\u064a\u062f\u0647\u0627 \u0627\u0644\u0633\u0648\u0631\u064a \u062a\u0644\u0642\u0627\u0626\u064a\u0627\u064b.", 11.0f, this.gold, true, 5));
            }
            if (purchaseInvoicePaymentSummary2.getOutstandingSypMinor() <= 0 || purchaseInvoicePaymentSummary2.getInvoiceRateMilli() <= 0 || !(Intrinsics.areEqual(purchaseInvoicePaymentSummary2.getInvoiceStatus(), "POSTED") || Intrinsics.areEqual(purchaseInvoicePaymentSummary2.getInvoiceStatus(), "PARTIALLY_PAID"))) {
                c = ',';
            } else {
                c = ',';
                linearLayout7.addView(smallButton("\uff0b \u062a\u0633\u062c\u064a\u0644 \u062f\u0641\u0639\u0629 \u0644\u0644\u0645\u0648\u0631\u062f", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda153
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivity.showPurchases$lambda$166$lambda$159(this.f$0, purchaseInvoicePaymentSummary2);
                    }
                }), fieldParams(dp(44), dp(i)));
            }
            List<PurchasePaymentRecord> listListPayments = purchasePaymentRepository.listPayments(purchaseInvoicePaymentSummary2.getId());
            if (!listListPayments.isEmpty()) {
                TextView textViewLabel6 = label("\u0633\u062c\u0644 \u0627\u0644\u062f\u0641\u0639\u0627\u062a:", 12.0f, this.mutedColor, true, 5);
                textViewLabel6.setPadding(0, dp(8), 0, dp(i2));
                linearLayout7.addView(textViewLabel6);
                for (final PurchasePaymentRecord purchasePaymentRecord : listListPayments) {
                    LinearLayout linearLayout8 = new LinearLayout(mainActivity);
                    linearLayout8.setOrientation(1);
                    linearLayout8.setPadding(dp(8), dp(6), dp(8), dp(6));
                    String accountBalance = formatAccountBalance(purchasePaymentRecord.getAmountMinor(), purchasePaymentRecord.getCurrencyCode());
                    String accountName = purchasePaymentRecord.getAccountName();
                    StoreTime storeTime = StoreTime.INSTANCE;
                    SQLiteDatabase readableDatabase = AppDatabase.INSTANCE.getInstance(mainActivity).getReadableDatabase();
                    Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
                    linearLayout8.addView(label(accountBalance + " \u2022 " + accountName + " \u2022 " + storeTime.formatTimestamp(readableDatabase, purchasePaymentRecord.getCreatedAt()), 11.0f, this.textColor, false, 5));
                    if (isManager()) {
                        linearLayout8.addView(smallButton("\u062a\u0639\u062f\u064a\u0644 \u0647\u0630\u0647 \u0627\u0644\u062f\u0641\u0639\u0629", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda164
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return MainActivity.showPurchases$lambda$166$lambda$164$lambda$163(this.f$0, purchaseInvoicePaymentSummary2, purchasePaymentRecord);
                            }
                        }), fieldParams(dp(38), 0));
                    }
                    linearLayout7.addView(linearLayout8, matchWidth());
                }
            }
            LinearLayout.LayoutParams layoutParamsMatchWidth = matchWidth();
            layoutParamsMatchWidth.bottomMargin = dp(10);
            Unit unit2 = Unit.INSTANCE;
            linearLayout6.addView(linearLayout7, layoutParamsMatchWidth);
            i2 = 3;
            i = 4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showPurchases$lambda$147(MainActivity mainActivity) throws IOException {
        mainActivity.showPurchaseReturns();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showPurchases$lambda$166$lambda$159(final MainActivity mainActivity, PurchaseInvoicePaymentSummary purchaseInvoicePaymentSummary) throws IOException {
        mainActivity.showPurchasePaymentDialog(purchaseInvoicePaymentSummary.getId(), purchaseInvoicePaymentSummary.getInvoiceNumber(), new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda61
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showPurchases$lambda$166$lambda$159$lambda$158(this.f$0);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showPurchases$lambda$166$lambda$159$lambda$158(MainActivity mainActivity) throws IOException {
        mainActivity.showPurchases();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showPurchases$lambda$166$lambda$164$lambda$163(final MainActivity mainActivity, PurchaseInvoicePaymentSummary purchaseInvoicePaymentSummary, PurchasePaymentRecord purchasePaymentRecord) throws IOException {
        mainActivity.showEditPurchasePaymentDialog(purchaseInvoicePaymentSummary.getId(), purchasePaymentRecord, new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda170
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showPurchases$lambda$166$lambda$164$lambda$163$lambda$162(this.f$0);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showPurchases$lambda$166$lambda$164$lambda$163$lambda$162(MainActivity mainActivity) throws IOException {
        mainActivity.showPurchases();
        return Unit.INSTANCE;
    }

    private final String paymentStatusArabic(String status) {
        switch (status.hashCode()) {
            case -1787006747:
                return !status.equals("UNPAID") ? status : "\u0622\u062c\u0644\u0629 \u2013 \u063a\u064a\u0631 \u0645\u062f\u0641\u0648\u0639\u0629";
            case -1031784143:
                return !status.equals("CANCELLED") ? status : "\u0645\u0644\u063a\u0627\u0629";
            case -313981507:
                return !status.equals("PARTIALLY_PAID") ? status : "\u0645\u062f\u0641\u0648\u0639\u0629 \u062c\u0632\u0626\u064a\u0627\u064b";
            case 2448076:
                return !status.equals("PAID") ? status : "\u0645\u062f\u0641\u0648\u0639\u0629 \u0628\u0627\u0644\u0643\u0627\u0645\u0644";
            case 65307009:
                return !status.equals("DRAFT") ? status : "\u0645\u0633\u0648\u062f\u0629";
            case 707733938:
                return !status.equals("SETTLED_BY_RETURN") ? status : "\u0645\u0633\u0648\u0651\u0627\u0629 \u0628\u0645\u0631\u062a\u062c\u0639 \u0645\u0648\u0631\u062f";
            default:
                return status;
        }
    }

    private final void showEditPurchasePaymentDialog(String invoiceId, final PurchasePaymentRecord payment, final Function0<Unit> onSaved) throws IOException {
        if (!isManager()) {
            Toast.makeText(this, "\u062a\u0639\u062f\u064a\u0644 \u062f\u0641\u0639\u0627\u062a \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0645\u062a\u0627\u062d \u0644\u0644\u0645\u062f\u064a\u0631 \u0641\u0642\u0637", 0).show();
            return;
        }
        MainActivity mainActivity = this;
        SupplierRolloverSummary supplierRolloverSummaryCurrentSupplierRollover = new DailyBalanceRepository(mainActivity).currentSupplierRollover();
        String accountId = supplierRolloverSummaryCurrentSupplierRollover != null ? supplierRolloverSummaryCurrentSupplierRollover.getAccountId() : null;
        List<CashAccountSummary> listListActive = new CashAccountRepository(mainActivity).listActive();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listListActive) {
            CashAccountSummary cashAccountSummary = (CashAccountSummary) obj;
            if (Intrinsics.areEqual(cashAccountSummary.getCurrencyCode(), payment.getCurrencyCode()) && (Intrinsics.areEqual(cashAccountSummary.getAccountType(), "WALLET") || (Intrinsics.areEqual(cashAccountSummary.getAccountType(), "CASH_BOX") && (Intrinsics.areEqual(payment.getCurrencyCode(), "USD") || Intrinsics.areEqual(cashAccountSummary.getId(), accountId) || Intrinsics.areEqual(cashAccountSummary.getId(), payment.getCashAccountId()))))) {
                arrayList.add(obj);
            }
        }
        final ArrayList arrayList2 = arrayList;
        if (arrayList2.isEmpty()) {
            Toast.makeText(mainActivity, "\u0644\u0627 \u064a\u0648\u062c\u062f \u062d\u0633\u0627\u0628 \u0646\u0634\u0637 \u0628\u0639\u0645\u0644\u0629 \u0647\u0630\u0647 \u0627\u0644\u062f\u0641\u0639\u0629", 1).show();
            return;
        }
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(18), dp(8), dp(18), dp(4));
        final Spinner spinner = new Spinner(mainActivity);
        ArrayList<CashAccountSummary> arrayList3 = arrayList2;
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
        for (CashAccountSummary cashAccountSummary2 : arrayList3) {
            arrayList4.add(cashAccountSummary2.getName() + " \u2022 " + cashAccountSummary2.getCurrencyCode());
        }
        spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, arrayList4));
        Iterator it = arrayList2.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            } else if (Intrinsics.areEqual(((CashAccountSummary) it.next()).getId(), payment.getCashAccountId())) {
                break;
            } else {
                i++;
            }
        }
        spinner.setSelection(RangesKt.coerceAtLeast(i, 0));
        final EditText editTextFieldForDialog$default = fieldForDialog$default(this, "\u0627\u0644\u0645\u0628\u0644\u063a", null, 0, 6, null);
        editTextFieldForDialog$default.setText(Intrinsics.areEqual(payment.getCurrencyCode(), "USD") ? moneyText(payment.getAmountMinor()) : formatSypAmount(payment.getAmountMinor()));
        editTextFieldForDialog$default.setInputType(8194);
        final EditText editTextFieldForDialog$default2 = fieldForDialog$default(this, "\u0627\u0644\u0645\u0644\u0627\u062d\u0638\u0629", null, 0, 6, null);
        String note = payment.getNote();
        if (note == null) {
            note = "";
        }
        editTextFieldForDialog$default2.setText(note);
        linearLayout.addView(label("\u062a\u0635\u062d\u064a\u062d \u062f\u0641\u0639\u0629 \u0627\u0644\u0645\u0648\u0631\u062f", 15.0f, this.green, true, 5));
        TextView textViewLabel = label("\u064a\u0645\u0643\u0646 \u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u0645\u0628\u0644\u063a \u0623\u0648 \u0627\u0644\u062d\u0633\u0627\u0628 \u0645\u0646 \u0646\u0641\u0633 \u0627\u0644\u0639\u0645\u0644\u0629 \u0623\u0648 \u0627\u0644\u0645\u0644\u0627\u062d\u0638\u0629. \u0633\u064a\u064f\u0639\u0627\u062f \u0627\u062d\u062a\u0633\u0627\u0628 \u0631\u0635\u064a\u062f \u0627\u0644\u062d\u0633\u0627\u0628 \u0648\u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0639\u0644\u0649 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629\u060c \u0645\u0639 \u0627\u0644\u0627\u062d\u062a\u0641\u0627\u0638 \u0628\u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0627\u0644\u0623\u0635\u0644\u064a.", 11.0f, this.mutedColor, false, 5);
        textViewLabel.setPadding(0, dp(4), 0, dp(8));
        linearLayout.addView(textViewLabel);
        linearLayout.addView(label("\u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a", 12.0f, this.mutedColor, false, 5));
        linearLayout.addView(spinner, fieldParams(dp(48), dp(8)));
        linearLayout.addView(editTextFieldForDialog$default, fieldParams(dp(48), dp(8)));
        linearLayout.addView(editTextFieldForDialog$default2, fieldParams(dp(48), dp(8)));
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(mainActivity).setTitle("\u062a\u0639\u062f\u064a\u0644 \u062f\u0641\u0639\u0629 \u0627\u0644\u0645\u0648\u0631\u062f").setView(linearLayout).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u062d\u0641\u0638 \u0627\u0644\u062a\u0635\u062d\u064a\u062d", (DialogInterface.OnClickListener) null).create();
        alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda74
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                MainActivity.showEditPurchasePaymentDialog$lambda$178$lambda$177(alertDialogCreate, arrayList2, spinner, editTextFieldForDialog$default, payment, this, editTextFieldForDialog$default2, onSaved, dialogInterface);
            }
        });
        alertDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showEditPurchasePaymentDialog$lambda$178$lambda$177(final AlertDialog alertDialog, final List list, final Spinner spinner, final EditText editText, final PurchasePaymentRecord purchasePaymentRecord, final MainActivity mainActivity, final EditText editText2, final Function0 function0, DialogInterface dialogInterface) {
        alertDialog.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda128
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Throwable {
                MainActivity.showEditPurchasePaymentDialog$lambda$178$lambda$177$lambda$176(list, spinner, editText, purchasePaymentRecord, mainActivity, editText2, alertDialog, function0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showEditPurchasePaymentDialog$lambda$178$lambda$177$lambda$176(List list, Spinner spinner, EditText editText, PurchasePaymentRecord purchasePaymentRecord, MainActivity mainActivity, EditText editText2, AlertDialog alertDialog, Function0 function0, View view) throws Throwable {
        try {
            CashAccountSummary cashAccountSummary = (CashAccountSummary) list.get(spinner.getSelectedItemPosition());
            String string = StringsKt.trim((CharSequence) editText.getText().toString()).toString();
            long usdCents = Intrinsics.areEqual(purchasePaymentRecord.getCurrencyCode(), "USD") ? mainActivity.parseUsdCents(string) : mainActivity.parseSypAmount(string);
            if (usdCents <= 0) {
                throw new IllegalArgumentException("\u0623\u062f\u062e\u0644 \u0645\u0628\u0644\u063a\u0627\u064b \u0623\u0643\u0628\u0631 \u0645\u0646 \u0635\u0641\u0631".toString());
            }
            PurchasePaymentRepository purchasePaymentRepository = new PurchasePaymentRepository(mainActivity);
            String str = mainActivity.currentUserRole;
            String str2 = mainActivity.currentUserId;
            if (str2 == null) {
                throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u062f\u064a\u0631 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
            }
            String id = purchasePaymentRecord.getId();
            String id2 = cashAccountSummary.getId();
            String string2 = StringsKt.trim((CharSequence) editText2.getText().toString()).toString();
            if (string2.length() == 0) {
                string2 = null;
            }
            purchasePaymentRepository.editPayment(str, str2, id, id2, usdCents, string2);
            alertDialog.dismiss();
            Toast.makeText(mainActivity, "\u062a\u0645 \u062a\u0635\u062d\u064a\u062d \u0627\u0644\u062f\u0641\u0639\u0629 \u0648\u062a\u062d\u062f\u064a\u062b \u0627\u0644\u062d\u0633\u0627\u0628 \u0648\u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629", 1).show();
            function0.invoke();
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u062f\u0641\u0639\u0629";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    private final void showPurchasePaymentDialog(final String invoiceId, String invoiceNumber, final Function0<Unit> onSaved) throws IOException {
        Object next;
        int i;
        MainActivity mainActivity = this;
        final PurchasePaymentRepository purchasePaymentRepository = new PurchasePaymentRepository(mainActivity);
        SupplierRolloverSummary supplierRolloverSummaryCurrentSupplierRollover = new DailyBalanceRepository(mainActivity).currentSupplierRollover();
        String accountId = supplierRolloverSummaryCurrentSupplierRollover != null ? supplierRolloverSummaryCurrentSupplierRollover.getAccountId() : null;
        List<CashAccountSummary> listListActive = new CashAccountRepository(mainActivity).listActive();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listListActive) {
            CashAccountSummary cashAccountSummary = (CashAccountSummary) obj;
            if (Intrinsics.areEqual(cashAccountSummary.getAccountType(), "WALLET") || (Intrinsics.areEqual(cashAccountSummary.getAccountType(), "CASH_BOX") && (Intrinsics.areEqual(cashAccountSummary.getCurrencyCode(), "USD") || (Intrinsics.areEqual(cashAccountSummary.getCurrencyCode(), "SYP") && Intrinsics.areEqual(cashAccountSummary.getId(), accountId))))) {
                arrayList.add(obj);
            }
        }
        final ArrayList arrayList2 = arrayList;
        if (arrayList2.isEmpty()) {
            new AlertDialog.Builder(mainActivity).setTitle("\u0644\u0627 \u062a\u0648\u062c\u062f \u062d\u0633\u0627\u0628\u0627\u062a \u0645\u0627\u0644\u064a\u0629").setMessage("\u0623\u0646\u0634\u0626 \u0635\u0646\u062f\u0648\u0642\u0627\u064b \u0623\u0648 \u0645\u062d\u0641\u0638\u0629 \u0648\u0623\u062f\u062e\u0644 \u0631\u0635\u064a\u062f\u0647\u0627 \u0627\u0644\u0627\u0641\u062a\u062a\u0627\u062d\u064a \u0642\u0628\u0644 \u062a\u0633\u062c\u064a\u0644 \u062f\u0641\u0639\u0629.").setNegativeButton("\u0644\u0627\u062d\u0642\u0627\u064b", (DialogInterface.OnClickListener) null).setPositiveButton("\u0625\u0636\u0627\u0641\u0629 \u062d\u0633\u0627\u0628", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda91
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) throws IOException {
                    this.f$0.showCashAccounts();
                }
            }).show();
            return;
        }
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(18), dp(8), dp(18), dp(4));
        final Spinner spinner = new Spinner(mainActivity);
        ArrayList<CashAccountSummary> arrayList3 = arrayList2;
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
        for (CashAccountSummary cashAccountSummary2 : arrayList3) {
            arrayList4.add(cashAccountSummary2.getName() + " \u2022 " + cashAccountTypeArabic(cashAccountSummary2.getAccountType()) + " \u2022 " + cashAccountSummary2.getCurrencyCode() + " \u2022 \u0627\u0644\u0631\u0635\u064a\u062f " + formatAccountBalance(cashAccountSummary2.getBalanceMinor(), cashAccountSummary2.getCurrencyCode()));
        }
        spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, arrayList4));
        final EditText editTextFieldForDialog$default = fieldForDialog$default(this, "\u0645\u0628\u0644\u063a \u0627\u0644\u062f\u0641\u0639\u0629", null, 0, 6, null);
        editTextFieldForDialog$default.setInputType(8194);
        final EditText editTextFieldForDialog$default2 = fieldForDialog$default(this, "\u0645\u0644\u0627\u062d\u0638\u0629 (\u0627\u062e\u062a\u064a\u0627\u0631\u064a)", null, 0, 6, null);
        List<PurchasePaymentRecord> listListPayments = purchasePaymentRepository.listPayments(invoiceId);
        String strJoinToString$default = listListPayments.isEmpty() ? "\u0644\u0627 \u062a\u0648\u062c\u062f \u062f\u0641\u0639\u0627\u062a \u0633\u0627\u0628\u0642\u0629." : CollectionsKt.joinToString$default(listListPayments, "\n", null, null, 0, null, new Function1() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda92
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return MainActivity.showPurchasePaymentDialog$lambda$183(this.f$0, (PurchasePaymentRecord) obj2);
            }
        }, 30, null);
        Iterator it = PurchasePaymentRepository.listPurchaseInvoices$default(purchasePaymentRepository, null, 1, null).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((PurchaseInvoicePaymentSummary) next).getId(), invoiceId));
        PurchaseInvoicePaymentSummary purchaseInvoicePaymentSummary = (PurchaseInvoicePaymentSummary) next;
        linearLayout.addView(label("\u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629: " + invoiceNumber, 15.0f, this.green, true, 5));
        if (purchaseInvoicePaymentSummary == null || purchaseInvoicePaymentSummary.getInvoiceRateMilli() <= 0) {
            i = 0;
        } else {
            i = 0;
            TextView textViewLabel = label("\u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0627\u0644\u062b\u0627\u0628\u062a: " + formatSypAmount(purchaseInvoicePaymentSummary.getOutstandingSypMinor()) + " \u0644.\u0633 \u2022 \u0628\u0633\u0639\u0631 \u0635\u0631\u0641 \u064a\u0648\u0645 \u0627\u0644\u0634\u0631\u0627\u0621", 13.0f, this.green, true, 5);
            textViewLabel.setPadding(0, dp(5), 0, dp(5));
            linearLayout.addView(textViewLabel);
        }
        TextView textViewLabel2 = label("\u0627\u062e\u062a\u0631 \u0627\u0644\u0635\u0646\u062f\u0648\u0642 \u0623\u0648 \u0627\u0644\u0645\u062d\u0641\u0638\u0629", 12.0f, this.mutedColor, false, 5);
        textViewLabel2.setPadding(i, dp(10), i, dp(4));
        linearLayout.addView(textViewLabel2);
        linearLayout.addView(spinner, fieldParams(dp(48), dp(8)));
        linearLayout.addView(editTextFieldForDialog$default, fieldParams(dp(48), dp(8)));
        linearLayout.addView(editTextFieldForDialog$default2, fieldParams(dp(48), dp(8)));
        TextView textViewLabel3 = label("\u0627\u0644\u062f\u0641\u0639\u0627\u062a \u0627\u0644\u0633\u0627\u0628\u0642\u0629:\n" + strJoinToString$default, 11.0f, this.mutedColor, false, 5);
        textViewLabel3.setPadding(i, dp(8), i, dp(4));
        linearLayout.addView(textViewLabel3);
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(mainActivity).setTitle("\u062a\u0633\u062c\u064a\u0644 \u062f\u0641\u0639\u0629 \u0644\u0644\u0645\u0648\u0631\u062f").setView(linearLayout).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u062a\u0623\u0643\u064a\u062f \u0627\u0644\u062f\u0641\u0639", (DialogInterface.OnClickListener) null).create();
        alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda93
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                MainActivity.showPurchasePaymentDialog$lambda$191(alertDialogCreate, arrayList2, spinner, editTextFieldForDialog$default, this, purchasePaymentRepository, invoiceId, editTextFieldForDialog$default2, onSaved, dialogInterface);
            }
        });
        alertDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final CharSequence showPurchasePaymentDialog$lambda$183(MainActivity mainActivity, PurchasePaymentRecord it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String accountBalance = mainActivity.formatAccountBalance(it.getAmountMinor(), it.getCurrencyCode());
        String accountName = it.getAccountName();
        StoreTime storeTime = StoreTime.INSTANCE;
        SQLiteDatabase readableDatabase = AppDatabase.INSTANCE.getInstance(mainActivity).getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        return accountBalance + " \u2022 " + accountName + " \u2022 " + storeTime.formatTimestamp(readableDatabase, it.getCreatedAt());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showPurchasePaymentDialog$lambda$191(final AlertDialog alertDialog, final List list, final Spinner spinner, final EditText editText, final MainActivity mainActivity, final PurchasePaymentRepository purchasePaymentRepository, final String str, final EditText editText2, final Function0 function0, DialogInterface dialogInterface) {
        alertDialog.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda107
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Throwable {
                MainActivity.showPurchasePaymentDialog$lambda$191$lambda$190(list, spinner, editText, mainActivity, purchasePaymentRepository, str, editText2, alertDialog, function0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void showPurchasePaymentDialog$lambda$191$lambda$190(List list, Spinner spinner, EditText editText, MainActivity mainActivity, PurchasePaymentRepository purchasePaymentRepository, String str, EditText editText2, AlertDialog alertDialog, Function0 function0, View view) throws Throwable {
        String str2;
        try {
            CashAccountSummary cashAccountSummary = (CashAccountSummary) list.get(spinner.getSelectedItemPosition());
            String string = StringsKt.trim((CharSequence) editText.getText().toString()).toString();
            long usdCents = Intrinsics.areEqual(cashAccountSummary.getCurrencyCode(), "USD") ? mainActivity.parseUsdCents(string) : mainActivity.parseSypAmount(string);
            if (usdCents <= 0) {
                throw new IllegalArgumentException("\u0623\u062f\u062e\u0644 \u0645\u0628\u0644\u063a\u0627\u064b \u0623\u0643\u0628\u0631 \u0645\u0646 \u0635\u0641\u0631".toString());
            }
            String str3 = mainActivity.currentUserId;
            if (str3 == null) {
                throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
            }
            String string2 = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
            String id = cashAccountSummary.getId();
            String string3 = StringsKt.trim((CharSequence) editText2.getText().toString()).toString();
            SyncCredentialVault syncCredentialVault = null;
            Object[] objArr = 0;
            if (string3.length() == 0) {
                string3 = null;
            }
            PurchasePaymentResult purchasePaymentResultAddPayment = purchasePaymentRepository.addPayment(str3, str, string2, id, usdCents, string3);
            alertDialog.dismiss();
            boolean zIsConfiguredSecondary = new SyncProtocolRepository(mainActivity, syncCredentialVault, 2, objArr == true ? 1 : 0).isConfiguredSecondary();
            MainActivity mainActivity2 = mainActivity;
            if (zIsConfiguredSecondary) {
                str2 = "\u062a\u0645 \u062d\u0641\u0638 \u0637\u0644\u0628 \u062f\u0641\u0639\u0629 \u0627\u0644\u0645\u0648\u0631\u062f \u0645\u062d\u0644\u064a\u0627\u064b \u0628\u0627\u0646\u062a\u0638\u0627\u0631 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u0648\u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a";
            } else if (Intrinsics.areEqual(purchasePaymentResultAddPayment.getPaymentStatus(), "PAID")) {
                str2 = "\u062a\u0645 \u062a\u0633\u062f\u064a\u062f \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0628\u0627\u0644\u0643\u0627\u0645\u0644";
            } else {
                str2 = "\u062a\u0645 \u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062f\u0641\u0639\u0629 \u0627\u0644\u062c\u0632\u0626\u064a\u0629 \u0628\u0646\u062c\u0627\u062d";
            }
            Toast.makeText(mainActivity2, str2, 1).show();
            function0.invoke();
        } catch (Exception e) {
            MainActivity mainActivity3 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062f\u0641\u0639\u0629";
            }
            Toast.makeText(mainActivity3, message, 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showSuppliers() throws IOException {
        if (!isManager()) {
            Toast.makeText(this, "\u062d\u0633\u0627\u0628\u0627\u062a \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0645\u062a\u0627\u062d\u0629 \u0644\u0644\u0645\u062f\u064a\u0631 \u0641\u0642\u0637", 0).show();
            return;
        }
        markScreen$default(this, "SUPPLIERS", null, 2, null);
        MainActivity mainActivity = this;
        List<SupplierFinancialSummary> listListSupplierSummaries = new PurchasePaymentRepository(mainActivity).listSupplierSummaries();
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(this.backgroundColor);
        LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        linearLayout2.setPadding(dp(14), dp(10), dp(14), dp(10));
        linearLayout2.setBackgroundColor(-1);
        Button button = new Button(mainActivity);
        button.setText("\u2039");
        button.setTextSize(25.0f);
        button.setAllCaps(false);
        button.setTextColor(this.green);
        button.setBackgroundColor(0);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda144
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IOException {
                this.f$0.showPurchases();
            }
        });
        linearLayout2.addView(button, new LinearLayout.LayoutParams(dp(48), dp(48)));
        linearLayout2.addView(label("\u062d\u0633\u0627\u0628\u0627\u062a \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646", 19.0f, this.green, true, 17), new LinearLayout.LayoutParams(0, -2, 1.0f));
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setFillViewport(true);
        LinearLayout linearLayout3 = new LinearLayout(mainActivity);
        linearLayout3.setOrientation(1);
        linearLayout3.setPadding(dp(16), dp(16), dp(16), dp(24));
        List<SupplierFinancialSummary> list = listListSupplierSummaries;
        Iterator<T> it = list.iterator();
        long outstandingSypMinor = 0;
        while (it.hasNext()) {
            outstandingSypMinor += ((SupplierFinancialSummary) it.next()).getOutstandingSypMinor();
        }
        Iterator<T> it2 = list.iterator();
        MainActivity mainActivity2 = mainActivity;
        long paidSypMinor = 0;
        while (it2.hasNext()) {
            paidSypMinor += ((SupplierFinancialSummary) it2.next()).getPaidSypMinor();
        }
        Iterator<T> it3 = list.iterator();
        long supplierReturnCreditsSypMinor = 0;
        while (it3.hasNext()) {
            supplierReturnCreditsSypMinor += ((SupplierFinancialSummary) it3.next()).getSupplierReturnCreditsSypMinor();
        }
        long j = supplierReturnCreditsSypMinor;
        linearLayout3.addView(label("\u0625\u062c\u0645\u0627\u0644\u064a \u0645\u0627 \u062f\u064f\u0641\u0639 \u0646\u0642\u062f\u0627\u064b: " + formatSypAmount(paidSypMinor) + " \u0644.\u0633", 14.0f, this.textColor, false, 5));
        if (j > 0) {
            linearLayout3.addView(label("\u0625\u062c\u0645\u0627\u0644\u064a \u062a\u062e\u0641\u064a\u0636\u0627\u062a \u0645\u0631\u062a\u062c\u0639\u0627\u062a \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646: " + formatSypAmount(j) + " \u0644.\u0633", 14.0f, this.green, true, 5));
        }
        TextView textViewLabel = label("\u0625\u062c\u0645\u0627\u0644\u064a \u0627\u0644\u0645\u0633\u062a\u062d\u0642 \u0627\u0644\u062b\u0627\u0628\u062a: " + formatSypAmount(outstandingSypMinor) + " \u0644.\u0633", 16.0f, this.green, true, 5);
        textViewLabel.setPadding(0, dp(4), 0, dp(14));
        linearLayout3.addView(textViewLabel);
        Iterator<T> it4 = list.iterator();
        int unpricedInvoiceCount = 0;
        while (it4.hasNext()) {
            unpricedInvoiceCount += ((SupplierFinancialSummary) it4.next()).getUnpricedInvoiceCount();
        }
        if (unpricedInvoiceCount > 0) {
            TextView textViewLabel2 = label("\u062a\u0646\u0628\u064a\u0647: \u062a\u0648\u062c\u062f " + unpricedInvoiceCount + " \u0641\u0627\u062a\u0648\u0631\u0629 \u0642\u062f\u064a\u0645\u0629 \u0628\u0644\u0627 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0645\u062d\u0641\u0648\u0638. \u0644\u0645 \u0646\u064f\u062f\u062e\u0644\u0647\u0627 \u0641\u064a \u0625\u062c\u0645\u0627\u0644\u064a \u0627\u0644\u0644\u064a\u0631\u0629 \u0648\u0644\u0645 \u0646\u064f\u0639\u062f \u062a\u0642\u064a\u064a\u0645\u0647\u0627 \u062a\u0644\u0642\u0627\u0626\u064a\u0627\u064b.", 12.0f, this.gold, true, 5);
            textViewLabel2.setPadding(0, dp(4), 0, dp(12));
            linearLayout3.addView(textViewLabel2);
        }
        if (listListSupplierSummaries.isEmpty()) {
            linearLayout3.addView(label("\u0644\u0627 \u064a\u0648\u062c\u062f \u0645\u0648\u0631\u062f\u0648\u0646 \u0645\u0633\u062c\u0644\u0648\u0646 \u0628\u0639\u062f.", 14.0f, this.mutedColor, false, 17));
        }
        for (final SupplierFinancialSummary supplierFinancialSummary : list) {
            MainActivity mainActivity3 = mainActivity2;
            LinearLayout linearLayout4 = new LinearLayout(mainActivity3);
            linearLayout4.setOrientation(1);
            linearLayout4.setPadding(dp(14), dp(12), dp(14), dp(12));
            linearLayout4.setBackground(cardBackground());
            linearLayout4.setClickable(true);
            linearLayout4.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda145
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws IOException {
                    MainActivity.showSuppliers$lambda$208$lambda$205$lambda$204(this.f$0, supplierFinancialSummary, view);
                }
            });
            linearLayout4.addView(label(supplierFinancialSummary.getName(), 16.0f, this.green, true, 5));
            TextView textViewLabel3 = label("\u0627\u0644\u0641\u0648\u0627\u062a\u064a\u0631 \u0627\u0644\u0645\u0639\u062a\u0645\u062f\u0629: " + formatSypAmount(supplierFinancialSummary.getTotalPurchaseSypMinor()) + " \u0644.\u0633", 12.0f, this.textColor, false, 5);
            textViewLabel3.setPadding(0, dp(5), 0, dp(2));
            linearLayout4.addView(textViewLabel3);
            linearLayout4.addView(label("\u0627\u0644\u0645\u062f\u0641\u0648\u0639 \u0646\u0642\u062f\u0627\u064b: " + formatSypAmount(supplierFinancialSummary.getPaidSypMinor()) + " \u0644.\u0633", 12.0f, this.textColor, false, 5));
            if (supplierFinancialSummary.getSupplierReturnCreditsSypMinor() > 0) {
                linearLayout4.addView(label("\u062a\u062e\u0641\u064a\u0636 \u0627\u0644\u0645\u0631\u062a\u062c\u0639\u0627\u062a: " + formatSypAmount(supplierFinancialSummary.getSupplierReturnCreditsSypMinor()) + " \u0644.\u0633", 12.0f, this.green, true, 5));
            }
            TextView textViewLabel4 = label("\u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0627\u0644\u062b\u0627\u0628\u062a: " + formatSypAmount(supplierFinancialSummary.getOutstandingSypMinor()) + " \u0644.\u0633", 14.0f, this.green, true, 5);
            textViewLabel4.setPadding(0, dp(3), 0, dp(2));
            linearLayout4.addView(textViewLabel4);
            linearLayout4.addView(label("\u0641\u0648\u0627\u062a\u064a\u0631 \u063a\u064a\u0631 \u0645\u0633\u062f\u062f\u0629: " + supplierFinancialSummary.getUnpaidInvoiceCount(), 11.0f, this.mutedColor, false, 5));
            linearLayout3.addView(linearLayout4, fieldParams(-2, dp(10)));
            mainActivity2 = mainActivity3;
        }
        scrollView.addView(linearLayout3);
        linearLayout.addView(linearLayout2, matchWidth());
        linearLayout.addView(scrollView, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        setContentView(linearLayout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showSuppliers$lambda$208$lambda$205$lambda$204(MainActivity mainActivity, SupplierFinancialSummary supplierFinancialSummary, View view) throws IOException {
        mainActivity.showSupplierDetails(supplierFinancialSummary.getId());
    }

    private final void showSupplierDetails(final String supplierId) throws IOException {
        Object next;
        int i;
        String str;
        if (!isManager()) {
            Toast.makeText(this, "\u062d\u0633\u0627\u0628\u0627\u062a \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0645\u062a\u0627\u062d\u0629 \u0644\u0644\u0645\u062f\u064a\u0631 \u0641\u0642\u0637", 0).show();
            return;
        }
        markScreen("SUPPLIER_DETAILS", supplierId);
        MainActivity mainActivity = this;
        PurchasePaymentRepository purchasePaymentRepository = new PurchasePaymentRepository(mainActivity);
        Iterator<T> it = purchasePaymentRepository.listSupplierSummaries().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((SupplierFinancialSummary) next).getId(), supplierId));
        SupplierFinancialSummary supplierFinancialSummary = (SupplierFinancialSummary) next;
        if (supplierFinancialSummary == null) {
            showSuppliers();
            return;
        }
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(this.backgroundColor);
        LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        linearLayout2.setPadding(dp(14), dp(10), dp(14), dp(10));
        linearLayout2.setBackgroundColor(-1);
        Button button = new Button(mainActivity);
        button.setText("\u2039");
        button.setTextSize(25.0f);
        button.setAllCaps(false);
        button.setTextColor(this.green);
        button.setBackgroundColor(0);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda31
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IOException {
                this.f$0.showSuppliers();
            }
        });
        linearLayout2.addView(button, new LinearLayout.LayoutParams(dp(48), dp(48)));
        linearLayout2.addView(label(supplierFinancialSummary.getName(), 18.0f, this.green, true, 17), new LinearLayout.LayoutParams(0, -2, 1.0f));
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setFillViewport(true);
        LinearLayout linearLayout3 = new LinearLayout(mainActivity);
        linearLayout3.setOrientation(1);
        linearLayout3.setPadding(dp(16), dp(14), dp(16), dp(24));
        linearLayout3.addView(label("\u0625\u062c\u0645\u0627\u0644\u064a \u0627\u0644\u0641\u0648\u0627\u062a\u064a\u0631: " + formatSypAmount(supplierFinancialSummary.getTotalPurchaseSypMinor()) + " \u0644.\u0633", 13.0f, this.textColor, false, 5));
        TextView textViewLabel = label("\u0625\u062c\u0645\u0627\u0644\u064a \u0627\u0644\u0645\u062f\u0641\u0648\u0639: " + formatSypAmount(supplierFinancialSummary.getPaidSypMinor()) + " \u0644.\u0633", 13.0f, this.textColor, false, 5);
        textViewLabel.setPadding(0, dp(4), 0, dp(4));
        linearLayout3.addView(textViewLabel);
        TextView textViewLabel2 = label("\u0627\u0644\u0645\u0628\u0644\u063a \u0627\u0644\u0645\u0633\u062a\u062d\u0642 \u0627\u0644\u062b\u0627\u0628\u062a: " + formatSypAmount(supplierFinancialSummary.getOutstandingSypMinor()) + " \u0644.\u0633", 16.0f, this.green, true, 5);
        textViewLabel2.setPadding(0, dp(4), 0, dp(12));
        linearLayout3.addView(textViewLabel2);
        final PartyStatement partyStatementSupplier = new PartyStatementRepository(mainActivity).supplier(supplierId);
        LinearLayout linearLayout4 = new LinearLayout(mainActivity);
        linearLayout4.setOrientation(0);
        Button buttonSmallButton = smallButton("\u062a\u0635\u062f\u064a\u0631 Excel", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda32
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showSupplierDetails$lambda$220(this.f$0, partyStatementSupplier);
            }
        });
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, dp(44), 1.0f);
        layoutParams.setMarginEnd(dp(6));
        Unit unit = Unit.INSTANCE;
        linearLayout4.addView(buttonSmallButton, layoutParams);
        linearLayout4.addView(smallButton("\u062a\u0635\u062f\u064a\u0631 PDF", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda33
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showSupplierDetails$lambda$222(this.f$0, partyStatementSupplier);
            }
        }), new LinearLayout.LayoutParams(0, dp(44), 1.0f));
        linearLayout3.addView(linearLayout4, fieldParams(dp(44), dp(6)));
        linearLayout3.addView(sectionHeading("\u0643\u0634\u0641 \u062d\u0631\u0643\u0629 \u0627\u0644\u0645\u0648\u0631\u062f"));
        TextView textViewLabel3 = label("\u0627\u0644\u0631\u0635\u064a\u062f \u062d\u0633\u0628 \u0627\u0644\u0641\u0648\u0627\u062a\u064a\u0631 \u0648\u0627\u0644\u062f\u0641\u0639\u0627\u062a \u0648\u062a\u062e\u0641\u064a\u0636\u0627\u062a \u0627\u0644\u0645\u0631\u062a\u062c\u0639\u0627\u062a: " + formatSypAmount(partyStatementSupplier.getClosingBalanceSypMinor()) + " \u0644.\u0633", 13.0f, this.green, true, 5);
        textViewLabel3.setPadding(0, dp(4), 0, dp(8));
        linearLayout3.addView(textViewLabel3);
        if (partyStatementSupplier.getUnpricedEntryCount() > 0) {
            i = 8;
            TextView textViewLabel4 = label("\u062a\u0646\u0628\u064a\u0647: \u062a\u0648\u062c\u062f " + partyStatementSupplier.getUnpricedEntryCount() + " \u062d\u0631\u0643\u0629 \u0628\u0644\u0627 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0645\u0648\u062b\u0648\u0642\u061b \u0644\u0627 \u062a\u062f\u062e\u0644 \u0641\u064a \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0645\u062d\u0633\u0648\u0628.", 11.0f, this.gold, true, 5);
            textViewLabel4.setPadding(0, 0, 0, dp(8));
            linearLayout3.addView(textViewLabel4);
        } else {
            i = 8;
        }
        if (partyStatementSupplier.getEntries().isEmpty()) {
            TextView textViewLabel5 = label("\u0644\u0627 \u062a\u0648\u062c\u062f \u062d\u0631\u0643\u0627\u062a \u0645\u0627\u0644\u064a\u0629 \u0645\u0633\u062c\u0644\u0629.", 12.0f, this.mutedColor, false, 17);
            textViewLabel5.setPadding(0, dp(i), 0, dp(i));
            linearLayout3.addView(textViewLabel5);
        }
        for (PartyStatementEntry partyStatementEntry : CollectionsKt.asReversed(partyStatementSupplier.getEntries())) {
            if (partyStatementEntry.getDebitSypMinor() > 0) {
                str = "\u0645\u062f\u064a\u0646: " + formatSypAmount(partyStatementEntry.getDebitSypMinor()) + " \u0644.\u0633";
            } else if (partyStatementEntry.getCreditSypMinor() > 0) {
                str = "\u062f\u0627\u0626\u0646: " + formatSypAmount(partyStatementEntry.getCreditSypMinor()) + " \u0644.\u0633";
            } else {
                str = "\u062d\u0631\u0643\u0629 \u0645\u0627\u0644\u064a\u0629";
            }
            TextView textViewLabel6 = label(partyStatementEntry.getDate() + " \u2022 " + partyStatementEntry.getReference() + " \u2022 " + partyStatementEntry.getDescription() + "\n" + str + " \u2022 \u0627\u0644\u0631\u0635\u064a\u062f: " + formatSypAmount(partyStatementEntry.getBalanceSypMinor()) + " \u0644.\u0633" + (partyStatementEntry.getCashRefundSypMinor() > 0 ? " \u2022 \u0627\u0633\u062a\u0631\u062f\u0627\u062f \u0646\u0642\u062f\u064a: " + formatSypAmount(partyStatementEntry.getCashRefundSypMinor()) + " \u0644.\u0633" : ""), 11.0f, this.textColor, false, 5);
            textViewLabel6.setPadding(dp(8), dp(6), dp(8), dp(6));
            textViewLabel6.setBackground(cardBackground());
            linearLayout3.addView(textViewLabel6);
            purchasePaymentRepository = purchasePaymentRepository;
        }
        final PurchasePaymentRepository purchasePaymentRepository2 = purchasePaymentRepository;
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "ALL";
        LinearLayout linearLayout5 = new LinearLayout(mainActivity);
        linearLayout5.setOrientation(0);
        final LinearLayout linearLayout6 = new LinearLayout(mainActivity);
        linearLayout6.setOrientation(1);
        Iterator it2 = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to("ALL", "\u0627\u0644\u0643\u0644"), TuplesKt.to("UNPAID", "\u0622\u062c\u0644\u0629"), TuplesKt.to("PARTIAL", "\u062c\u0632\u0626\u064a\u0629"), TuplesKt.to("PAID", "\u0645\u062f\u0641\u0648\u0639\u0629")}).iterator();
        while (it2.hasNext()) {
            Pair pair = (Pair) it2.next();
            final String str2 = (String) pair.component1();
            linearLayout5.addView(smallButton((String) pair.component2(), new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda35
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MainActivity.showSupplierDetails$lambda$238$lambda$237(objectRef, str2, linearLayout6, purchasePaymentRepository2, supplierId, this);
                }
            }), new LinearLayout.LayoutParams(0, dp(42), 1.0f));
            it2 = it2;
            linearLayout = linearLayout;
            linearLayout2 = linearLayout2;
        }
        LinearLayout linearLayout7 = linearLayout;
        linearLayout3.addView(linearLayout5, fieldParams(dp(42), dp(8)));
        linearLayout3.addView(linearLayout6, matchWidth());
        showSupplierDetails$refreshInvoices(linearLayout6, purchasePaymentRepository2, supplierId, this, objectRef);
        scrollView.addView(linearLayout3);
        linearLayout7.addView(linearLayout2, matchWidth());
        linearLayout7.addView(scrollView, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        setContentView(linearLayout7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showSupplierDetails$lambda$220(MainActivity mainActivity, PartyStatement partyStatement) {
        mainActivity.exportPartyStatement(partyStatement, "xlsx");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showSupplierDetails$lambda$222(MainActivity mainActivity, PartyStatement partyStatement) {
        mainActivity.exportPartyStatement(partyStatement, "pdf");
        return Unit.INSTANCE;
    }

    private static final void showSupplierDetails$refreshInvoices(LinearLayout linearLayout, PurchasePaymentRepository purchasePaymentRepository, final String str, final MainActivity mainActivity, Ref.ObjectRef<String> objectRef) throws IOException {
        linearLayout.removeAllViews();
        List<PurchaseInvoicePaymentSummary> listListPurchaseInvoices = purchasePaymentRepository.listPurchaseInvoices(str);
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listListPurchaseInvoices.iterator();
        while (true) {
            boolean zAreEqual = true;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            PurchaseInvoicePaymentSummary purchaseInvoicePaymentSummary = (PurchaseInvoicePaymentSummary) next;
            String str2 = objectRef.element;
            int iHashCode = str2.hashCode();
            if (iHashCode != -1787006747) {
                if (iHashCode != -74951327) {
                    if (iHashCode == 2448076 && str2.equals("PAID")) {
                        zAreEqual = Intrinsics.areEqual(purchaseInvoicePaymentSummary.getPaymentStatus(), "PAID");
                    }
                } else if (str2.equals("PARTIAL")) {
                    zAreEqual = Intrinsics.areEqual(purchaseInvoicePaymentSummary.getPaymentStatus(), "PARTIALLY_PAID");
                }
            } else if (str2.equals("UNPAID")) {
                zAreEqual = Intrinsics.areEqual(purchaseInvoicePaymentSummary.getPaymentStatus(), "UNPAID");
            }
            if (zAreEqual) {
                arrayList.add(next);
            }
        }
        ArrayList<PurchaseInvoicePaymentSummary> arrayList2 = arrayList;
        if (arrayList2.isEmpty()) {
            TextView textViewLabel = mainActivity.label("\u0644\u0627 \u062a\u0648\u062c\u062f \u0641\u0648\u0627\u062a\u064a\u0631 \u0636\u0645\u0646 \u0647\u0630\u0627 \u0627\u0644\u062a\u0635\u0646\u064a\u0641.", 13.0f, mainActivity.mutedColor, false, 17);
            textViewLabel.setPadding(0, mainActivity.dp(20), 0, mainActivity.dp(20));
            linearLayout.addView(textViewLabel);
        }
        for (final PurchaseInvoicePaymentSummary purchaseInvoicePaymentSummary2 : arrayList2) {
            LinearLayout linearLayout2 = new LinearLayout(mainActivity);
            linearLayout2.setOrientation(1);
            linearLayout2.setPadding(mainActivity.dp(12), mainActivity.dp(10), mainActivity.dp(12), mainActivity.dp(10));
            linearLayout2.setBackground(mainActivity.cardBackground());
            linearLayout2.addView(mainActivity.label(purchaseInvoicePaymentSummary2.getInvoiceNumber(), 15.0f, mainActivity.green, true, 5));
            TextView textViewLabel2 = mainActivity.label("\u0627\u0644\u062a\u0627\u0631\u064a\u062e: " + purchaseInvoicePaymentSummary2.getDate() + " \u2022 " + mainActivity.paymentStatusArabic(purchaseInvoicePaymentSummary2.getPaymentStatus()), 12.0f, mainActivity.mutedColor, false, 5);
            textViewLabel2.setPadding(0, mainActivity.dp(4), 0, mainActivity.dp(4));
            linearLayout2.addView(textViewLabel2);
            linearLayout2.addView(mainActivity.label("\u0627\u0644\u0625\u062c\u0645\u0627\u0644\u064a \u0627\u0644\u0645\u062a\u0641\u0642 \u0639\u0644\u064a\u0647: " + mainActivity.formatSypAmount(purchaseInvoicePaymentSummary2.getTotalSypMinor()) + " \u0644.\u0633", 12.0f, mainActivity.textColor, false, 5));
            linearLayout2.addView(mainActivity.label("\u0627\u0644\u0645\u062f\u0641\u0648\u0639: " + mainActivity.formatSypAmount(purchaseInvoicePaymentSummary2.getPaidSypMinor()) + " \u0644.\u0633", 12.0f, mainActivity.textColor, false, 5));
            linearLayout2.addView(mainActivity.label("\u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0627\u0644\u062b\u0627\u0628\u062a: " + mainActivity.formatSypAmount(purchaseInvoicePaymentSummary2.getOutstandingSypMinor()) + " \u0644.\u0633", 13.0f, mainActivity.green, true, 5));
            if (purchaseInvoicePaymentSummary2.getInvoiceRateMilli() <= 0) {
                linearLayout2.addView(mainActivity.label("\u0641\u0627\u062a\u0648\u0631\u0629 \u0642\u062f\u064a\u0645\u0629 \u0628\u0644\u0627 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0645\u062d\u0641\u0648\u0638\u061b \u064a\u0644\u0632\u0645 \u0645\u0631\u0627\u062c\u0639\u062a\u0647\u0627 \u064a\u062f\u0648\u064a\u0627\u064b \u0642\u0628\u0644 \u0627\u0644\u062f\u0641\u0639.", 11.0f, mainActivity.gold, true, 5));
            }
            if (purchaseInvoicePaymentSummary2.getOutstandingSypMinor() > 0 && purchaseInvoicePaymentSummary2.getInvoiceRateMilli() > 0 && (Intrinsics.areEqual(purchaseInvoicePaymentSummary2.getInvoiceStatus(), "POSTED") || Intrinsics.areEqual(purchaseInvoicePaymentSummary2.getInvoiceStatus(), "PARTIALLY_PAID"))) {
                linearLayout2.addView(mainActivity.smallButton("\u062a\u0633\u062c\u064a\u0644 \u062f\u0641\u0639\u0629", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivity.showSupplierDetails$refreshInvoices$lambda$236$lambda$235(this.f$0, purchaseInvoicePaymentSummary2, str);
                    }
                }), mainActivity.fieldParams(mainActivity.dp(42), mainActivity.dp(2)));
            }
            linearLayout.addView(linearLayout2, mainActivity.fieldParams(-2, mainActivity.dp(8)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showSupplierDetails$refreshInvoices$lambda$236$lambda$235(final MainActivity mainActivity, PurchaseInvoicePaymentSummary purchaseInvoicePaymentSummary, final String str) throws IOException {
        mainActivity.showPurchasePaymentDialog(purchaseInvoicePaymentSummary.getId(), purchaseInvoicePaymentSummary.getInvoiceNumber(), new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda119
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showSupplierDetails$refreshInvoices$lambda$236$lambda$235$lambda$234(this.f$0, str);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showSupplierDetails$refreshInvoices$lambda$236$lambda$235$lambda$234(MainActivity mainActivity, String str) throws IOException {
        mainActivity.showSupplierDetails(str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit showSupplierDetails$lambda$238$lambda$237(Ref.ObjectRef objectRef, String str, LinearLayout linearLayout, PurchasePaymentRepository purchasePaymentRepository, String str2, MainActivity mainActivity) throws IOException {
        objectRef.element = str;
        showSupplierDetails$refreshInvoices(linearLayout, purchasePaymentRepository, str2, mainActivity, objectRef);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:19:0x0236  */
    public final void showCustomers() {
        String str;
        markScreen$default(this, "CUSTOMERS", null, 2, null);
        MainActivity mainActivity = this;
        final CustomerRepository customerRepository = new CustomerRepository(mainActivity);
        CustomerPaymentRepository customerPaymentRepository = new CustomerPaymentRepository(mainActivity);
        List<CustomerSummary> listListActive = customerRepository.listActive();
        List<CustomerFinancialSummary> listListCustomerSummaries = customerPaymentRepository.listCustomerSummaries();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(listListCustomerSummaries, 10)), 16));
        for (Object obj : listListCustomerSummaries) {
            linkedHashMap.put(((CustomerFinancialSummary) obj).getId(), obj);
        }
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(this.backgroundColor);
        LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        linearLayout2.setPadding(dp(14), dp(10), dp(14), dp(10));
        linearLayout2.setBackgroundColor(-1);
        Button button = new Button(mainActivity);
        button.setText("\u2039");
        button.setTextSize(25.0f);
        button.setAllCaps(false);
        button.setTextColor(this.green);
        button.setBackgroundColor(0);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda39
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IOException {
                MainActivity.showPointOfSale$default(this.f$0, false, 1, null);
            }
        });
        linearLayout2.addView(button, new LinearLayout.LayoutParams(dp(48), dp(48)));
        linearLayout2.addView(label("\u0627\u0644\u0639\u0645\u0644\u0627\u0621 \u0648\u0627\u0644\u062f\u064a\u0648\u0646", 19.0f, this.green, true, 17), new LinearLayout.LayoutParams(0, -2, 1.0f));
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setFillViewport(true);
        LinearLayout linearLayout3 = new LinearLayout(mainActivity);
        linearLayout3.setOrientation(1);
        linearLayout3.setPadding(dp(16), dp(16), dp(16), dp(24));
        linearLayout3.addView(label("\u0645\u0644\u0641\u0627\u062a \u0627\u0644\u0639\u0645\u0644\u0627\u0621", 22.0f, this.green, true, 5));
        TextView textViewLabel = label("\u064a\u0639\u0631\u0636 \u0647\u0630\u0627 \u0627\u0644\u0642\u0633\u0645 \u0641\u0648\u0627\u062a\u064a\u0631 \u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u0622\u062c\u0644 \u0648\u0627\u0644\u062a\u062d\u0635\u064a\u0644\u0627\u062a \u0627\u0644\u0645\u0633\u062c\u0644\u0629 \u0639\u0644\u064a\u0647\u0627 \u0641\u0642\u0637. \u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u0646\u0642\u062f\u064a \u0648\u0627\u0644\u0623\u0631\u0635\u062f\u0629 \u0627\u0644\u0627\u0641\u062a\u062a\u0627\u062d\u064a\u0629 \u063a\u064a\u0631 \u0627\u0644\u0645\u0631\u062a\u0628\u0637\u0629 \u0628\u0641\u0648\u0627\u062a\u064a\u0631 \u0644\u0627 \u062a\u062f\u062e\u0644 \u0641\u064a \u0647\u0630\u0627 \u0627\u0644\u0631\u0635\u064a\u062f.", 12.0f, this.mutedColor, false, 5);
        textViewLabel.setPadding(0, dp(5), 0, dp(12));
        linearLayout3.addView(textViewLabel);
        Button button2 = new Button(mainActivity);
        button2.setText("+ \u0625\u0636\u0627\u0641\u0629 \u0632\u0628\u0648\u0646");
        button2.setAllCaps(false);
        button2.setTextColor(-1);
        button2.setBackgroundColor(this.green);
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda40
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.showCustomers$lambda$249$lambda$248(this.f$0, view);
            }
        });
        linearLayout3.addView(button2, fieldParams(dp(50), dp(12)));
        if (listListActive.isEmpty()) {
            TextView textViewLabel2 = label("\u0644\u0627 \u064a\u0648\u062c\u062f \u0639\u0645\u0644\u0627\u0621 \u0645\u0633\u062c\u0644\u0648\u0646 \u0628\u0639\u062f.", 14.0f, this.mutedColor, false, 17);
            textViewLabel2.setPadding(0, dp(24), 0, dp(24));
            linearLayout3.addView(textViewLabel2);
        }
        for (final CustomerSummary customerSummary : listListActive) {
            CustomerFinancialSummary customerFinancialSummary = (CustomerFinancialSummary) linkedHashMap.get(customerSummary.getId());
            LinearLayout linearLayout4 = new LinearLayout(mainActivity);
            linearLayout4.setOrientation(1);
            linearLayout4.setPadding(dp(14), dp(12), dp(14), dp(12));
            linearLayout4.setBackground(cardBackground());
            linearLayout4.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda41
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MainActivity.showCustomers$lambda$263$lambda$254$lambda$251(this.f$0, customerSummary, view);
                }
            });
            linearLayout4.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda42
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return MainActivity.showCustomers$lambda$263$lambda$254$lambda$253(this.f$0, customerSummary, customerRepository, view);
                }
            });
            linearLayout4.addView(label(customerSummary.getName(), 16.0f, this.green, true, 5));
            String phone = customerSummary.getPhone();
            if (phone == null) {
                phone = "\u0644\u0627 \u064a\u0648\u062c\u062f \u0631\u0642\u0645 \u0647\u0627\u062a\u0641";
            } else {
                if (StringsKt.isBlank(phone)) {
                    phone = null;
                }
                if (phone == null) {
                    phone = "\u0644\u0627 \u064a\u0648\u062c\u062f \u0631\u0642\u0645 \u0647\u0627\u062a\u0641";
                }
            }
            TextView textViewLabel3 = label(phone, 12.0f, this.mutedColor, false, 5);
            textViewLabel3.setPadding(0, dp(4), 0, dp(2));
            linearLayout4.addView(textViewLabel3);
            long outstandingUsdCents = customerFinancialSummary != null ? customerFinancialSummary.getOutstandingUsdCents() : 0L;
            if (outstandingUsdCents > 0) {
                str = "\u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0639\u0644\u0649 \u0627\u0644\u0641\u0648\u0627\u062a\u064a\u0631 \u0627\u0644\u0622\u062c\u0644\u0629: " + moneyText(outstandingUsdCents) + " USD";
            } else {
                str = "\u0644\u0627 \u062a\u0648\u062c\u062f \u062f\u064a\u0648\u0646 \u0622\u062c\u0644\u0629 \u0645\u062a\u0628\u0642\u064a\u0629";
            }
            TextView textViewLabel4 = label(str, 13.0f, outstandingUsdCents > 0 ? this.gold : this.mutedColor, true, 5);
            textViewLabel4.setPadding(0, dp(5), 0, dp(8));
            linearLayout4.addView(textViewLabel4);
            LinearLayout linearLayout5 = new LinearLayout(mainActivity);
            linearLayout5.setOrientation(0);
            linearLayout5.setGravity(16);
            Button buttonSmallButton = smallButton("\u0627\u0644\u062d\u0633\u0627\u0628 \u0648\u0627\u0644\u062a\u062d\u0635\u064a\u0644", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda43
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MainActivity.showCustomers$lambda$263$lambda$259(this.f$0, customerSummary);
                }
            });
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, dp(44), 1.0f);
            layoutParams.setMarginEnd(dp(6));
            Unit unit = Unit.INSTANCE;
            linearLayout5.addView(buttonSmallButton, layoutParams);
            linearLayout5.addView(smallButton("\u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda44
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MainActivity.showCustomers$lambda$263$lambda$262(this.f$0, customerSummary);
                }
            }), new LinearLayout.LayoutParams(0, dp(44), 1.0f));
            linearLayout4.addView(linearLayout5, matchWidth());
            linearLayout3.addView(linearLayout4, fieldParams(-2, dp(9)));
        }
        scrollView.addView(linearLayout3);
        linearLayout.addView(linearLayout2, matchWidth());
        linearLayout.addView(scrollView, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        setContentView(linearLayout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showCustomers$lambda$249$lambda$248(final MainActivity mainActivity, View view) {
        mainActivity.showCustomerEditor(null, new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showCustomers$lambda$249$lambda$248$lambda$247(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showCustomers$lambda$249$lambda$248$lambda$247(MainActivity mainActivity) {
        mainActivity.showCustomers();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showCustomers$lambda$263$lambda$254$lambda$251(MainActivity mainActivity, CustomerSummary customerSummary, View view) {
        mainActivity.showCustomerAccount(customerSummary.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean showCustomers$lambda$263$lambda$254$lambda$253(final MainActivity mainActivity, final CustomerSummary customerSummary, final CustomerRepository customerRepository, View view) {
        new AlertDialog.Builder(mainActivity).setTitle("\u0625\u064a\u0642\u0627\u0641 \u0645\u0644\u0641 \u0627\u0644\u0639\u0645\u064a\u0644").setMessage("\u0633\u064a\u062a\u0645 \u0625\u062e\u0641\u0627\u0621 " + customerSummary.getName() + " \u0645\u0646 \u0627\u0644\u0642\u0648\u0627\u0626\u0645 \u0627\u0644\u062c\u062f\u064a\u062f\u0629 \u0645\u0639 \u0627\u0644\u0627\u062d\u062a\u0641\u0627\u0638 \u0628\u0633\u062c\u0644\u0627\u062a\u0647.").setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u0625\u064a\u0642\u0627\u0641", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda163
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.showCustomers$lambda$263$lambda$254$lambda$253$lambda$252(customerRepository, mainActivity, customerSummary, dialogInterface, i);
            }
        }).show();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showCustomers$lambda$263$lambda$254$lambda$253$lambda$252(CustomerRepository customerRepository, MainActivity mainActivity, CustomerSummary customerSummary, DialogInterface dialogInterface, int i) {
        try {
            String str = mainActivity.currentUserId;
            if (str == null) {
                throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
            }
            customerRepository.setActive(str, customerSummary.getId(), false);
            mainActivity.showCustomers();
        } catch (Exception e) {
            Toast.makeText(mainActivity, e.getMessage(), 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showCustomers$lambda$263$lambda$259(MainActivity mainActivity, CustomerSummary customerSummary) {
        mainActivity.showCustomerAccount(customerSummary.getId());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showCustomers$lambda$263$lambda$262(final MainActivity mainActivity, CustomerSummary customerSummary) {
        mainActivity.showCustomerEditor(customerSummary.getId(), new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showCustomers$lambda$263$lambda$262$lambda$261(this.f$0);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showCustomers$lambda$263$lambda$262$lambda$261(MainActivity mainActivity) {
        mainActivity.showCustomers();
        return Unit.INSTANCE;
    }

    private final void exportPartyStatement(PartyStatement statement, String format) {
        String str;
        byte[] bArrPdf;
        if (!isManager()) {
            Toast.makeText(this, "\u062a\u0635\u062f\u064a\u0631 \u0643\u0634\u0648\u0641 \u0627\u0644\u062d\u0633\u0627\u0628 \u0645\u062a\u0627\u062d \u0644\u0644\u0645\u062f\u064a\u0631 \u0641\u0642\u0637", 0).show();
            return;
        }
        String strTake = StringsKt.take(new Regex("[^\\p{L}\\p{N}_-]+").replace(statement.getPartyName(), "_"), 40);
        if (Intrinsics.areEqual(format, "xlsx")) {
            str = "xlsx";
        } else {
            if (!Intrinsics.areEqual(format, "pdf")) {
                throw new IllegalStateException("\u0635\u064a\u063a\u0629 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629".toString());
            }
            str = "pdf";
        }
        if (Intrinsics.areEqual(format, "xlsx")) {
            bArrPdf = PartyStatementExport.INSTANCE.xlsx(statement);
        } else {
            if (!Intrinsics.areEqual(format, "pdf")) {
                throw new IllegalStateException("\u0635\u064a\u063a\u0629 \u063a\u064a\u0631 \u0645\u062f\u0639\u0648\u0645\u0629".toString());
            }
            bArrPdf = PartyStatementExport.INSTANCE.pdf(statement);
        }
        String str2 = Intrinsics.areEqual(format, "xlsx") ? "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" : "application/pdf";
        String str3 = "\u0643\u0634\u0641_\u062d\u0633\u0627\u0628_" + strTake + "." + str;
        this.pendingStatementExportBytes = bArrPdf;
        this.pendingStatementExportName = str3;
        Intent intent = new Intent("android.intent.action.CREATE_DOCUMENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType(str2);
        intent.putExtra("android.intent.extra.TITLE", str3);
        try {
            startActivityForResult(intent, this.statementExportRequestCode);
        } catch (Exception e) {
            this.pendingStatementExportBytes = null;
            this.pendingStatementExportName = null;
            Toast.makeText(this, "\u062a\u0639\u0630\u0631 \u0641\u062a\u062d \u0646\u0627\u0641\u0630\u0629 \u062d\u0641\u0638 \u0627\u0644\u0645\u0644\u0641: " + e.getMessage(), 1).show();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:107:0x0775  */
    /* JADX WARN: Multi-variable type inference failed */
    private final void showCustomerAccount(String customerId) {
        Object next;
        int i;
        String paymentStatus;
        char c;
        String str;
        int i2;
        char c2;
        StringBuilder sb;
        String str2;
        String str3;
        final String str4 = customerId;
        markScreen("CUSTOMER_ACCOUNT", str4);
        MainActivity mainActivity = this;
        CustomerSummary customerSummary = new CustomerRepository(mainActivity).get(str4);
        if (customerSummary == null) {
            Toast.makeText(mainActivity, "\u0645\u0644\u0641 \u0627\u0644\u0639\u0645\u064a\u0644 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f", 0).show();
            showCustomers();
            return;
        }
        CustomerPaymentRepository customerPaymentRepository = new CustomerPaymentRepository(mainActivity);
        List<CustomerInvoicePaymentSummary> listListInvoices = customerPaymentRepository.listInvoices(str4);
        Iterator<T> it = customerPaymentRepository.listCustomerSummaries().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((CustomerFinancialSummary) next).getId(), str4));
        CustomerFinancialSummary customerFinancialSummary = (CustomerFinancialSummary) next;
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(this.backgroundColor);
        LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        linearLayout2.setPadding(dp(14), dp(10), dp(14), dp(10));
        linearLayout2.setBackgroundColor(-1);
        Button button = new Button(mainActivity);
        button.setText("\u2039");
        button.setTextSize(25.0f);
        button.setAllCaps(false);
        button.setTextColor(this.green);
        button.setBackgroundColor(0);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda173
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.showCustomers();
            }
        });
        linearLayout2.addView(button, new LinearLayout.LayoutParams(dp(48), dp(48)));
        LinearLayout linearLayout3 = linearLayout2;
        linearLayout3.addView(label("\u0643\u0634\u0641 \u062d\u0633\u0627\u0628 \u0627\u0644\u0632\u0628\u0648\u0646", 19.0f, this.green, true, 17), new LinearLayout.LayoutParams(0, -2, 1.0f));
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setFillViewport(true);
        LinearLayout linearLayout4 = new LinearLayout(mainActivity);
        linearLayout4.setOrientation(1);
        linearLayout4.setPadding(dp(16), dp(16), dp(16), dp(24));
        linearLayout4.addView(label(customerSummary.getName(), 22.0f, this.green, true, 5));
        String phone = customerSummary.getPhone();
        if (phone == null) {
            phone = "\u0644\u0627 \u064a\u0648\u062c\u062f \u0631\u0642\u0645 \u0647\u0627\u062a\u0641";
        }
        TextView textViewLabel = label(phone, 12.0f, this.mutedColor, false, 5);
        textViewLabel.setPadding(0, dp(4), 0, dp(10));
        linearLayout4.addView(textViewLabel);
        long outstandingUsdCents = customerFinancialSummary != null ? customerFinancialSummary.getOutstandingUsdCents() : 0L;
        long paidUsdCents = customerFinancialSummary != null ? customerFinancialSummary.getPaidUsdCents() : 0L;
        long totalCreditUsdCents = customerFinancialSummary != null ? customerFinancialSummary.getTotalCreditUsdCents() : 0L;
        LinearLayout linearLayout5 = new LinearLayout(mainActivity);
        linearLayout5.setOrientation(1);
        linearLayout5.setBackground(cardBackground());
        long j = paidUsdCents;
        linearLayout5.setPadding(dp(14), dp(12), dp(14), dp(12));
        String strMoneyText = moneyText(totalCreditUsdCents);
        StringBuilder sb2 = new StringBuilder("\u0625\u062c\u0645\u0627\u0644\u064a \u0641\u0648\u0627\u062a\u064a\u0631 \u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u0622\u062c\u0644: ");
        sb2.append(strMoneyText);
        String str5 = " USD";
        sb2.append(" USD");
        linearLayout5.addView(label(sb2.toString(), 14.0f, this.textColor, false, 5));
        TextView textViewLabel2 = label("\u0625\u062c\u0645\u0627\u0644\u064a \u0627\u0644\u062a\u062d\u0635\u064a\u0644\u0627\u062a: " + moneyText(j) + " USD", 14.0f, this.textColor, false, 5);
        textViewLabel2.setPadding(0, dp(5), 0, dp(5));
        linearLayout5.addView(textViewLabel2);
        TextView textViewLabel3 = label("\u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0627\u0644\u0645\u0631\u062c\u0639\u064a: " + moneyText(outstandingUsdCents) + " USD", 14.0f, this.textColor, false, 5);
        textViewLabel3.setPadding(0, dp(5), 0, dp(3));
        linearLayout5.addView(textViewLabel3);
        String sypAmount = formatSypAmount(customerFinancialSummary != null ? customerFinancialSummary.getOutstandingSypMinor() : 0L);
        StringBuilder sb3 = new StringBuilder("\u0627\u0644\u0645\u0637\u0644\u0648\u0628 \u062d\u0627\u0644\u064a\u0627\u064b: ");
        sb3.append(sypAmount);
        String str6 = " \u0644.\u0633";
        sb3.append(" \u0644.\u0633");
        linearLayout5.addView(label(sb3.toString(), 17.0f, this.green, true, 5));
        linearLayout4.addView(linearLayout5, fieldParams(-2, dp(16)));
        List<ServiceTransferReceivableSummary> listListOpenReceivables$default = ServiceTransferReceivableRepository.listOpenReceivables$default(new ServiceTransferReceivableRepository(mainActivity), str4, 0, 2, null);
        String str7 = "<get-readableDatabase>(...)";
        String str8 = " \u2022 ";
        if (!listListOpenReceivables$default.isEmpty()) {
            linearLayout4.addView(sectionHeading("\u062a\u062d\u0648\u064a\u0644\u0627\u062a \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0622\u062c\u0644\u0629"));
            for (final ServiceTransferReceivableSummary serviceTransferReceivableSummary : listListOpenReceivables$default) {
                String serviceType = serviceTransferReceivableSummary.getServiceType();
                String str9 = Intrinsics.areEqual(serviceType, ServiceTransferRepository.SYRIATEL_BALANCE) ? "\u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0627\u0644\u0639\u0627\u062f\u064a" : Intrinsics.areEqual(serviceType, ServiceTransferRepository.SYRIATEL_CASH) ? "\u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0643\u0627\u0634" : "\u0634\u0627\u0645 \u0643\u0627\u0634";
                LinearLayout linearLayout6 = new LinearLayout(mainActivity);
                linearLayout6.setOrientation(1);
                linearLayout6.setBackground(cardBackground());
                linearLayout6.setPadding(dp(12), dp(10), dp(12), dp(10));
                StoreTime storeTime = StoreTime.INSTANCE;
                SQLiteDatabase readableDatabase = AppDatabase.INSTANCE.getInstance(mainActivity).getReadableDatabase();
                Intrinsics.checkNotNullExpressionValue(readableDatabase, str7);
                linearLayout6.addView(label(str9 + str8 + storeTime.formatTimestamp(readableDatabase, serviceTransferReceivableSummary.getCreatedAt()), 13.0f, this.green, true, 5));
                linearLayout6.addView(label("\u0625\u062c\u0645\u0627\u0644\u064a \u0627\u0644\u062f\u064a\u0646: " + formatSypAmount(serviceTransferReceivableSummary.getChargeMinor()) + " \u0644.\u0633 \u2022 \u0627\u0644\u0645\u0633\u062f\u062f: " + formatSypAmount(serviceTransferReceivableSummary.getPaidMinor()) + " \u0644.\u0633", 12.0f, this.textColor, false, 5));
                String sypAmount2 = formatSypAmount(serviceTransferReceivableSummary.getOutstandingMinor());
                StringBuilder sb4 = new StringBuilder("\u0627\u0644\u0645\u062a\u0628\u0642\u064a: ");
                sb4.append(sypAmount2);
                sb4.append(" \u0644.\u0633");
                linearLayout6.addView(label(sb4.toString(), 15.0f, this.gold, true, 5));
                linearLayout6.addView(smallButton("\u062a\u062d\u0635\u064a\u0644 \u062f\u0641\u0639\u0629", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda174
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivity.showCustomerAccount$lambda$279$lambda$278(this.f$0, serviceTransferReceivableSummary, str4);
                    }
                }), fieldParams(dp(42), dp(6)));
                linearLayout4.addView(linearLayout6, fieldParams(-2, dp(7)));
                str8 = str8;
                str7 = str7;
                linearLayout3 = linearLayout3;
                customerPaymentRepository = customerPaymentRepository;
                listListInvoices = listListInvoices;
            }
        }
        String str10 = str7;
        CustomerPaymentRepository customerPaymentRepository2 = customerPaymentRepository;
        List<CustomerInvoicePaymentSummary> list = listListInvoices;
        LinearLayout linearLayout7 = linearLayout3;
        String str11 = str8;
        final PartyStatement partyStatementCustomer = new PartyStatementRepository(mainActivity).customer(str4);
        LinearLayout linearLayout8 = new LinearLayout(mainActivity);
        linearLayout8.setOrientation(0);
        Button buttonSmallButton = smallButton("\u062a\u0635\u062f\u064a\u0631 Excel", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showCustomerAccount$lambda$281(this.f$0, partyStatementCustomer);
            }
        });
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, dp(44), 1.0f);
        layoutParams.setMarginEnd(dp(6));
        Unit unit = Unit.INSTANCE;
        linearLayout8.addView(buttonSmallButton, layoutParams);
        linearLayout8.addView(smallButton("\u062a\u0635\u062f\u064a\u0631 PDF", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showCustomerAccount$lambda$283(this.f$0, partyStatementCustomer);
            }
        }), new LinearLayout.LayoutParams(0, dp(44), 1.0f));
        linearLayout4.addView(linearLayout8, fieldParams(dp(44), dp(6)));
        linearLayout4.addView(sectionHeading("\u0643\u0634\u0641 \u062d\u0631\u0643\u0629 \u0627\u0644\u062d\u0633\u0627\u0628"));
        TextView textViewLabel4 = label("\u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0645\u0631\u062c\u0639\u064a: " + moneyText(partyStatementCustomer.getClosingBalanceUsdCents()) + " USD \u2022 \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u062a\u0627\u0631\u064a\u062e\u064a \u0627\u0644\u0645\u062d\u0633\u0648\u0628 \u0628\u0627\u0644\u0644\u064a\u0631\u0629: " + formatSypAmount(partyStatementCustomer.getClosingBalanceSypMinor()) + " \u0644.\u0633", 13.0f, this.green, true, 5);
        textViewLabel4.setPadding(0, dp(4), 0, dp(8));
        linearLayout4.addView(textViewLabel4);
        if (partyStatementCustomer.getUnpricedEntryCount() > 0) {
            i = 8;
            TextView textViewLabel5 = label("\u062a\u0646\u0628\u064a\u0647: \u062a\u0648\u062c\u062f " + partyStatementCustomer.getUnpricedEntryCount() + " \u062d\u0631\u0643\u0629 \u0628\u0644\u0627 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0645\u0648\u062b\u0648\u0642\u061b \u0644\u0627 \u062a\u062f\u062e\u0644 \u0641\u064a \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0645\u062d\u0633\u0648\u0628.", 11.0f, this.gold, true, 5);
            textViewLabel5.setPadding(0, 0, 0, dp(8));
            linearLayout4.addView(textViewLabel5);
        } else {
            i = 8;
        }
        if (partyStatementCustomer.getEntries().isEmpty()) {
            TextView textViewLabel6 = label("\u0644\u0627 \u062a\u0648\u062c\u062f \u062d\u0631\u0643\u0627\u062a \u0645\u0627\u0644\u064a\u0629 \u0645\u0633\u062c\u0644\u0629.", 12.0f, this.mutedColor, false, 17);
            textViewLabel6.setPadding(0, dp(i), 0, dp(i));
            linearLayout4.addView(textViewLabel6);
        }
        for (PartyStatementEntry partyStatementEntry : CollectionsKt.asReversed(partyStatementCustomer.getEntries())) {
            if (partyStatementEntry.getDebitUsdCents() > 0) {
                str2 = "\u0645\u062f\u064a\u0646: " + moneyText(partyStatementEntry.getDebitUsdCents()) + " USD";
            } else if (partyStatementEntry.getCreditUsdCents() > 0) {
                str2 = "\u062f\u0627\u0626\u0646: " + moneyText(partyStatementEntry.getCreditUsdCents()) + " USD";
            } else if (partyStatementEntry.getCashRefundUsdCents() > 0) {
                str2 = "\u0631\u062f \u0646\u0642\u062f\u064a: " + moneyText(partyStatementEntry.getCashRefundUsdCents()) + " USD";
            } else if (partyStatementEntry.getCashRefundSypMinor() > 0) {
                str2 = "\u0631\u062f \u0646\u0642\u062f\u064a: " + formatSypAmount(partyStatementEntry.getCashRefundSypMinor()) + str6;
            } else {
                str2 = "\u062d\u0631\u0643\u0629 \u0645\u0627\u0644\u064a\u0629";
            }
            if (partyStatementEntry.getCashRefundUsdCents() > 0 && partyStatementEntry.getCreditUsdCents() > 0) {
                str3 = " \u2022 \u0631\u062f \u0646\u0642\u062f\u064a: " + moneyText(partyStatementEntry.getCashRefundUsdCents()) + " USD";
            } else if (partyStatementEntry.getCashRefundSypMinor() <= 0 || partyStatementEntry.getCreditUsdCents() <= 0) {
                str3 = "";
            } else {
                str3 = " \u2022 \u0631\u062f \u0646\u0642\u062f\u064a: " + formatSypAmount(partyStatementEntry.getCashRefundSypMinor()) + str6;
            }
            TextView textViewLabel7 = label(partyStatementEntry.getDate() + str11 + partyStatementEntry.getReference() + str11 + partyStatementEntry.getDescription() + "\n" + str2 + str3 + " \u2022 \u0627\u0644\u0631\u0635\u064a\u062f: " + moneyText(partyStatementEntry.getBalanceUsdCents()) + " USD", 11.0f, this.textColor, false, 5);
            textViewLabel7.setPadding(dp(i), dp(6), dp(i), dp(6));
            textViewLabel7.setBackground(cardBackground());
            linearLayout4.addView(textViewLabel7);
            str6 = str6;
        }
        String str12 = str6;
        linearLayout4.addView(sectionHeading("\u0641\u0648\u0627\u062a\u064a\u0631 \u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u0622\u062c\u0644"));
        if (list.isEmpty()) {
            TextView textViewLabel8 = label("\u0644\u0627 \u062a\u0648\u062c\u062f \u0641\u0648\u0627\u062a\u064a\u0631 \u0628\u064a\u0639 \u0622\u062c\u0644 \u0644\u0647\u0630\u0627 \u0627\u0644\u0632\u0628\u0648\u0646 \u062d\u062a\u0649 \u0627\u0644\u0622\u0646.", 14.0f, this.mutedColor, false, 17);
            textViewLabel8.setPadding(0, dp(22), 0, dp(22));
            linearLayout4.addView(textViewLabel8);
        }
        for (final CustomerInvoicePaymentSummary customerInvoicePaymentSummary : list) {
            LinearLayout linearLayout9 = new LinearLayout(mainActivity);
            linearLayout9.setOrientation(1);
            linearLayout9.setBackground(cardBackground());
            linearLayout9.setPadding(dp(14), dp(12), dp(14), dp(12));
            switch (customerInvoicePaymentSummary.getPaymentStatus()) {
                case "UNPAID":
                    paymentStatus = "\u063a\u064a\u0631 \u0645\u0633\u062f\u062f\u0629";
                    break;
                case "CANCELLED":
                    paymentStatus = "\u0645\u0644\u063a\u0627\u0629";
                    break;
                case "PARTIALLY_PAID":
                    paymentStatus = "\u0645\u0633\u062f\u062f\u0629 \u062c\u0632\u0626\u064a\u0627\u064b";
                    break;
                case "PAID":
                    paymentStatus = "\u0645\u0633\u062f\u062f\u0629 \u0628\u0627\u0644\u0643\u0627\u0645\u0644";
                    break;
                case "DRAFT":
                    paymentStatus = "\u0645\u0633\u0648\u062f\u0629 \u063a\u064a\u0631 \u0645\u0639\u062a\u0645\u062f\u0629";
                    break;
                default:
                    paymentStatus = customerInvoicePaymentSummary.getPaymentStatus();
                    break;
            }
            linearLayout9.addView(label("\u0641\u0627\u062a\u0648\u0631\u0629 " + customerInvoicePaymentSummary.getInvoiceNumber(), 15.0f, this.green, true, 5));
            TextView textViewLabel9 = label("\u0627\u0644\u062a\u0627\u0631\u064a\u062e: " + customerInvoicePaymentSummary.getDate() + str11 + paymentStatus, 11.0f, this.mutedColor, false, 5);
            textViewLabel9.setPadding(0, dp(4), 0, dp(6));
            linearLayout9.addView(textViewLabel9);
            linearLayout9.addView(label("\u0627\u0644\u0625\u062c\u0645\u0627\u0644\u064a: " + moneyText(customerInvoicePaymentSummary.getTotalUsdCents()) + str5, 13.0f, this.textColor, false, 5));
            TextView textViewLabel10 = label("\u0627\u0644\u0645\u062f\u0641\u0648\u0639: " + moneyText(customerInvoicePaymentSummary.getPaidUsdCents()) + str5, 13.0f, this.textColor, false, 5);
            textViewLabel10.setPadding(0, dp(3), 0, dp(3));
            linearLayout9.addView(textViewLabel10);
            linearLayout9.addView(label("\u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0627\u0644\u0645\u0631\u062c\u0639\u064a: " + moneyText(customerInvoicePaymentSummary.getOutstandingUsdCents()) + str5, 13.0f, this.textColor, false, 5));
            String sypAmount3 = formatSypAmount(customerInvoicePaymentSummary.getOutstandingSypMinor());
            StringBuilder sb5 = new StringBuilder("\u0627\u0644\u0645\u0637\u0644\u0648\u0628 \u062d\u0627\u0644\u064a\u0627\u064b: ");
            sb5.append(sypAmount3);
            String str13 = str12;
            sb5.append(str13);
            linearLayout9.addView(label(sb5.toString(), 15.0f, this.green, true, 5));
            if (customerInvoicePaymentSummary.getOriginalRateMilli() > 0 || customerInvoicePaymentSummary.getOutstandingUsdCents() <= 0) {
                c = 5;
            } else {
                TextView textViewLabel11 = label("\u062a\u0646\u0628\u064a\u0647: \u0641\u0627\u062a\u0648\u0631\u0629 \u0642\u062f\u064a\u0645\u0629 \u0628\u0644\u0627 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0645\u0648\u062b\u0648\u0642\u061b \u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u0642\u064a\u064a\u0645 \u062f\u064a\u0646\u0647\u0627 \u0623\u0648 \u062a\u062d\u0635\u064a\u0644\u0647 \u0622\u0644\u064a\u0627\u064b \u0642\u0628\u0644 \u0645\u0631\u0627\u062c\u0639\u062a\u0647\u0627.", 11.0f, this.gold, true, 5);
                c = 5;
                textViewLabel11.setPadding(0, dp(5), 0, dp(5));
                linearLayout9.addView(textViewLabel11);
            }
            if (customerInvoicePaymentSummary.getOriginalRateMilli() > 0 && customerInvoicePaymentSummary.getOutstandingUsdCents() > 0 && (Intrinsics.areEqual(customerInvoicePaymentSummary.getInvoiceStatus(), "POSTED") || Intrinsics.areEqual(customerInvoicePaymentSummary.getInvoiceStatus(), "PARTIALLY_PAID"))) {
                Button button2 = new Button(mainActivity);
                button2.setText("\u062a\u0633\u062c\u064a\u0644 \u062a\u062d\u0635\u064a\u0644 \u0639\u0644\u0649 \u0647\u0630\u0647 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629");
                button2.setAllCaps(false);
                button2.setTextColor(-1);
                button2.setBackgroundColor(this.green);
                button2.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda4
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.showCustomerPaymentDialog(str4, customerInvoicePaymentSummary);
                    }
                });
                linearLayout9.addView(button2, fieldParams(dp(48), dp(8)));
            }
            CustomerPaymentRepository customerPaymentRepository3 = customerPaymentRepository2;
            List<CustomerCreditRevaluationRecord> listListRevaluations = customerPaymentRepository3.listRevaluations(customerInvoicePaymentSummary.getId());
            if (listListRevaluations.isEmpty()) {
                str = str10;
                i2 = 2;
            } else {
                TextView textViewLabel12 = label("\u062a\u0639\u062f\u064a\u0644\u0627\u062a \u062a\u0642\u064a\u064a\u0645 \u0627\u0644\u062f\u064a\u0646 \u062d\u0633\u0628 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641", 11.0f, this.gold, true, 5);
                char c3 = 6;
                textViewLabel12.setPadding(0, dp(6), 0, dp(3));
                linearLayout9.addView(textViewLabel12);
                for (CustomerCreditRevaluationRecord customerCreditRevaluationRecord : listListRevaluations) {
                    String plainString = MainActivity$$ExternalSyntheticBackport0.m(BigDecimal.valueOf(customerCreditRevaluationRecord.getAppliedRateMilli(), 3)).toPlainString();
                    String sypAmount4 = formatSypAmount(customerCreditRevaluationRecord.getPreviousSypMinor());
                    String sypAmount5 = formatSypAmount(customerCreditRevaluationRecord.getNewSypMinor());
                    StoreTime storeTime2 = StoreTime.INSTANCE;
                    SQLiteDatabase readableDatabase2 = AppDatabase.INSTANCE.getInstance(mainActivity).getReadableDatabase();
                    String str14 = str10;
                    Intrinsics.checkNotNullExpressionValue(readableDatabase2, str14);
                    TextView textViewLabel13 = label(sypAmount4 + " \u2190 " + sypAmount5 + " \u0644.\u0633 \u2022 " + plainString + " \u0644.\u0633/\u062f\u0648\u0644\u0627\u0631 \u2022 " + storeTime2.formatTimestamp(readableDatabase2, customerCreditRevaluationRecord.getCreatedAt()), 10.0f, this.mutedColor, false, 5);
                    textViewLabel13.setPadding(0, dp(2), 0, dp(2));
                    linearLayout9.addView(textViewLabel13);
                    c3 = (char) 6;
                    str10 = str14;
                }
                str = str10;
                i2 = 2;
            }
            List<CustomerPaymentRecord> listListPayments = customerPaymentRepository3.listPayments(customerInvoicePaymentSummary.getId());
            if (listListPayments.isEmpty()) {
                c2 = '\b';
            } else {
                int i3 = i2;
                TextView textViewLabel14 = label("\u0633\u062c\u0644 \u0627\u0644\u062f\u0641\u0639\u0627\u062a", 12.0f, this.gold, true, 5);
                char c4 = '\b';
                textViewLabel14.setPadding(0, dp(8), 0, dp(3));
                linearLayout9.addView(textViewLabel14);
                for (CustomerPaymentRecord customerPaymentRecord : listListPayments) {
                    if (Intrinsics.areEqual(customerPaymentRecord.getCurrencyCode(), "SYP")) {
                        String sypAmount6 = formatSypAmount(customerPaymentRecord.getAmountMinor());
                        sb = new StringBuilder();
                        sb.append(sypAmount6);
                        sb.append(str13);
                    } else {
                        String strMoneyText2 = moneyText(customerPaymentRecord.getAmountMinor());
                        sb = new StringBuilder("$");
                        sb.append(strMoneyText2);
                    }
                    String string = sb.toString();
                    String str15 = Intrinsics.areEqual(customerPaymentRecord.getAccountType(), "WALLET") ? "\u0645\u062d\u0641\u0638\u0629 \u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a\u0629" : "\u062f\u0631\u062c \u0646\u0642\u062f\u064a";
                    String accountName = customerPaymentRecord.getAccountName();
                    StoreTime storeTime3 = StoreTime.INSTANCE;
                    SQLiteDatabase readableDatabase3 = AppDatabase.INSTANCE.getInstance(mainActivity).getReadableDatabase();
                    Intrinsics.checkNotNullExpressionValue(readableDatabase3, str);
                    TextView textViewLabel15 = label(string + str11 + str15 + ": " + accountName + str11 + storeTime3.formatTimestamp(readableDatabase3, customerPaymentRecord.getCreatedAt()), 11.0f, this.mutedColor, false, 5);
                    textViewLabel15.setPadding(0, dp(i3), 0, dp(i3));
                    linearLayout9.addView(textViewLabel15);
                    TextView textViewLabel16 = label("\u062e\u0641\u0636 \u0645\u0631\u062c\u0639 \u0627\u0644\u062f\u064a\u0646: " + moneyText(customerPaymentRecord.getUsdReferenceReductionCents()) + " USD \u2022 \u0633\u0639\u0631 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629: " + MainActivity$$ExternalSyntheticBackport0.m(BigDecimal.valueOf(customerPaymentRecord.getOriginalInvoiceRateMilli(), 3)).toPlainString() + " \u2022 \u0633\u0639\u0631 \u0627\u0644\u062a\u062d\u0635\u064a\u0644: " + MainActivity$$ExternalSyntheticBackport0.m(BigDecimal.valueOf(customerPaymentRecord.getCollectionRateMilli(), 3)).toPlainString() + " \u2022 \u0627\u0644\u0633\u0639\u0631 \u0627\u0644\u0641\u0639\u0627\u0644: " + MainActivity$$ExternalSyntheticBackport0.m(BigDecimal.valueOf(customerPaymentRecord.getEffectiveRateMilli(), 3)).toPlainString(), 10.0f, this.mutedColor, false, 5);
                    textViewLabel16.setPadding(0, dp(1), 0, dp(3));
                    linearLayout9.addView(textViewLabel16);
                    c4 = '\b';
                }
                c2 = c4;
            }
            linearLayout4.addView(linearLayout9, fieldParams(-2, dp(10)));
            str10 = str;
            str12 = str13;
            str5 = str5;
            str4 = customerId;
            customerPaymentRepository2 = customerPaymentRepository3;
        }
        scrollView.addView(linearLayout4);
        linearLayout.addView(linearLayout7, matchWidth());
        linearLayout.addView(scrollView, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        setContentView(linearLayout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showCustomerAccount$lambda$279$lambda$278(final MainActivity mainActivity, ServiceTransferReceivableSummary serviceTransferReceivableSummary, final String str) throws IOException {
        mainActivity.showServiceTransferDebtPaymentDialog(serviceTransferReceivableSummary.getTransferId(), new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showCustomerAccount$lambda$279$lambda$278$lambda$277(this.f$0, str);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showCustomerAccount$lambda$279$lambda$278$lambda$277(MainActivity mainActivity, String str) {
        mainActivity.showCustomerAccount(str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showCustomerAccount$lambda$281(MainActivity mainActivity, PartyStatement partyStatement) {
        mainActivity.exportPartyStatement(partyStatement, "xlsx");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showCustomerAccount$lambda$283(MainActivity mainActivity, PartyStatement partyStatement) {
        mainActivity.exportPartyStatement(partyStatement, "pdf");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showCustomerPaymentDialog(final String customerId, final CustomerInvoicePaymentSummary invoice) {
        String str;
        MainActivity mainActivity = this;
        CashAccountRepository cashAccountRepository = new CashAccountRepository(mainActivity);
        final CashAccountSummary cashAccountSummaryCurrentDayPrimaryDrawer = cashAccountRepository.currentDayPrimaryDrawer();
        List<CashAccountSummary> listListActive = cashAccountRepository.listActive();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listListActive) {
            CashAccountSummary cashAccountSummary = (CashAccountSummary) obj;
            if (Intrinsics.areEqual(cashAccountSummary.getAccountType(), "WALLET") && Intrinsics.areEqual(cashAccountSummary.getCurrencyCode(), "SYP")) {
                arrayList.add(obj);
            }
        }
        final ArrayList arrayList2 = arrayList;
        if (cashAccountSummaryCurrentDayPrimaryDrawer == null && arrayList2.isEmpty()) {
            new AlertDialog.Builder(mainActivity).setTitle("\u0644\u0627 \u064a\u0648\u062c\u062f \u062d\u0633\u0627\u0628 \u0635\u0627\u0644\u062d \u0644\u0644\u062a\u062d\u0635\u064a\u0644").setMessage("\u064a\u062c\u0628 \u0639\u0644\u0649 \u0627\u0644\u0645\u062f\u064a\u0631 \u062a\u0639\u064a\u064a\u0646 \u062f\u0631\u062c \u0646\u0642\u062f\u064a \u0631\u0626\u064a\u0633\u064a \u0644\u0644\u064a\u0648\u0645 \u0623\u0648 \u0625\u0646\u0634\u0627\u0621 \u0645\u062d\u0641\u0638\u0629 \u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a\u0629 \u0646\u0634\u0637\u0629 \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629. \u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0633\u062a\u062e\u062f\u0627\u0645 \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0627\u0644\u0645\u062f\u0648\u0631.").setPositiveButton("\u0641\u062a\u062d \u0627\u0644\u0635\u0646\u0627\u062f\u064a\u0642 \u0648\u0627\u0644\u0645\u062d\u0627\u0641\u0638", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda146
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) throws IOException {
                    this.f$0.showCashAccounts();
                }
            }).show();
            return;
        }
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(18), dp(8), dp(18), dp(4));
        final RadioGroup radioGroup = new RadioGroup(mainActivity);
        radioGroup.setOrientation(1);
        RadioButton radioButton = new RadioButton(mainActivity);
        radioButton.setId(View.generateViewId());
        if (cashAccountSummaryCurrentDayPrimaryDrawer != null) {
            str = "\u0646\u0642\u062f\u0627\u064b \u0625\u0644\u0649 \u062f\u0631\u062c \u0627\u0644\u064a\u0648\u0645 \u0627\u0644\u0631\u0626\u064a\u0633\u064a: " + cashAccountSummaryCurrentDayPrimaryDrawer.getName();
        }
        radioButton.setText(str);
        radioButton.setEnabled(cashAccountSummaryCurrentDayPrimaryDrawer != null);
        radioButton.setTextSize(13.0f);
        final RadioButton radioButton2 = new RadioButton(mainActivity);
        radioButton2.setId(View.generateViewId());
        radioButton2.setText("\u062a\u062d\u0648\u064a\u0644 \u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a \u0625\u0644\u0649 \u0645\u062d\u0641\u0638\u0629");
        radioButton2.setEnabled(true ^ arrayList2.isEmpty());
        radioButton2.setTextSize(13.0f);
        radioGroup.addView(radioButton);
        radioGroup.addView(radioButton2);
        final Spinner spinner = new Spinner(mainActivity);
        ArrayList<CashAccountSummary> arrayList3 = arrayList2;
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
        for (CashAccountSummary cashAccountSummary2 : arrayList3) {
            arrayList4.add(cashAccountSummary2.getName() + " \u2022 \u0627\u0644\u0631\u0635\u064a\u062f " + formatAccountBalance(cashAccountSummary2.getBalanceMinor(), cashAccountSummary2.getCurrencyCode()));
        }
        spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, arrayList4));
        final EditText editTextFieldForDialog = fieldForDialog("\u0645\u0628\u0644\u063a \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629", "", 8194);
        final EditText editTextFieldForDialog$default = fieldForDialog$default(this, "\u0645\u0644\u0627\u062d\u0638\u0629 \u0627\u062e\u062a\u064a\u0627\u0631\u064a\u0629", null, 0, 6, null);
        linearLayout.addView(label("\u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629: " + invoice.getInvoiceNumber(), 15.0f, this.green, true, 5));
        TextView textViewLabel = label("\u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0627\u0644\u0645\u0631\u062c\u0639\u064a: " + moneyText(invoice.getOutstandingUsdCents()) + " USD", 12.0f, this.mutedColor, false, 5);
        textViewLabel.setPadding(0, dp(4), 0, dp(2));
        linearLayout.addView(textViewLabel);
        TextView textViewLabel2 = label("\u0627\u0644\u0645\u0637\u0644\u0648\u0628 \u062d\u0627\u0644\u064a\u0627\u064b: " + formatSypAmount(invoice.getOutstandingSypMinor()) + " \u0644.\u0633", 14.0f, this.green, true, 5);
        textViewLabel2.setPadding(0, dp(2), 0, dp(8));
        linearLayout.addView(textViewLabel2);
        linearLayout.addView(label("\u0637\u0631\u064a\u0642\u0629 \u0627\u0644\u062a\u062d\u0635\u064a\u0644", 12.0f, this.mutedColor, true, 5));
        linearLayout.addView(radioGroup, fieldParams(-2, dp(6)));
        final TextView textViewLabel3 = label("\u0627\u0644\u0645\u062d\u0641\u0638\u0629 \u0627\u0644\u0645\u0633\u062a\u0644\u0645\u0629", 12.0f, this.mutedColor, false, 5);
        linearLayout.addView(textViewLabel3);
        linearLayout.addView(spinner, fieldParams(dp(48), dp(8)));
        linearLayout.addView(editTextFieldForDialog, fieldParams(dp(48), dp(8)));
        linearLayout.addView(editTextFieldForDialog$default, fieldParams(dp(48), dp(8)));
        radioGroup.check(cashAccountSummaryCurrentDayPrimaryDrawer != null ? radioButton.getId() : radioButton2.getId());
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda147
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup2, int i) {
                MainActivity.showCustomerPaymentDialog$updateChannel(radioGroup, radioButton2, textViewLabel3, spinner);
            }
        });
        showCustomerPaymentDialog$updateChannel(radioGroup, radioButton2, textViewLabel3, spinner);
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(mainActivity).setTitle("\u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0646 \u0632\u0628\u0648\u0646").setView(linearLayout).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062a\u062d\u0635\u064a\u0644", (DialogInterface.OnClickListener) null).create();
        alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda148
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                MainActivity.showCustomerPaymentDialog$lambda$317(alertDialogCreate, radioGroup, radioButton2, arrayList2, spinner, cashAccountSummaryCurrentDayPrimaryDrawer, this, editTextFieldForDialog, invoice, editTextFieldForDialog$default, customerId, dialogInterface);
            }
        });
        alertDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showCustomerPaymentDialog$updateChannel(RadioGroup radioGroup, RadioButton radioButton, TextView textView, Spinner spinner) {
        boolean z = radioGroup.getCheckedRadioButtonId() == radioButton.getId();
        textView.setVisibility(z ? 0 : 8);
        spinner.setVisibility(z ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showCustomerPaymentDialog$lambda$317(final AlertDialog alertDialog, final RadioGroup radioGroup, final RadioButton radioButton, final List list, final Spinner spinner, final CashAccountSummary cashAccountSummary, final MainActivity mainActivity, final EditText editText, final CustomerInvoicePaymentSummary customerInvoicePaymentSummary, final EditText editText2, final String str, DialogInterface dialogInterface) {
        alertDialog.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda139
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.showCustomerPaymentDialog$lambda$317$lambda$316(radioGroup, radioButton, list, spinner, cashAccountSummary, mainActivity, editText, customerInvoicePaymentSummary, editText2, alertDialog, str, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showCustomerPaymentDialog$lambda$317$lambda$316(RadioGroup radioGroup, RadioButton radioButton, List list, Spinner spinner, CashAccountSummary cashAccountSummary, MainActivity mainActivity, EditText editText, CustomerInvoicePaymentSummary customerInvoicePaymentSummary, EditText editText2, AlertDialog alertDialog, String str, View view) {
        String id;
        try {
            if (radioGroup.getCheckedRadioButtonId() == radioButton.getId()) {
                CashAccountSummary cashAccountSummary2 = (CashAccountSummary) CollectionsKt.getOrNull(list, spinner.getSelectedItemPosition());
                if (cashAccountSummary2 == null || (id = cashAccountSummary2.getId()) == null) {
                    throw new IllegalStateException("\u0627\u062e\u062a\u0631 \u0627\u0644\u0645\u062d\u0641\u0638\u0629 \u0627\u0644\u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a\u0629 \u0627\u0644\u0645\u0633\u062a\u0644\u0645\u0629".toString());
                }
            } else if (cashAccountSummary == null || (id = cashAccountSummary.getId()) == null) {
                throw new IllegalStateException("\u0644\u0645 \u064a\u062a\u0645 \u062a\u0639\u064a\u064a\u0646 \u062f\u0631\u062c \u0627\u0644\u064a\u0648\u0645 \u0627\u0644\u0631\u0626\u064a\u0633\u064a\u061b \u0644\u0627 \u064a\u0645\u0643\u0646 \u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0627\u0644\u0646\u0642\u062f\u064a".toString());
            }
            String str2 = id;
            long sypAmount = mainActivity.parseSypAmount(editText.getText().toString());
            if (sypAmount <= 0) {
                throw new IllegalArgumentException("\u0623\u062f\u062e\u0644 \u0645\u0628\u0644\u063a \u062a\u062d\u0635\u064a\u0644 \u0623\u0643\u0628\u0631 \u0645\u0646 \u0635\u0641\u0631".toString());
            }
            CustomerPaymentRepository customerPaymentRepository = new CustomerPaymentRepository(mainActivity);
            String str3 = mainActivity.currentUserId;
            if (str3 == null) {
                throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
            }
            String id2 = customerInvoicePaymentSummary.getId();
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            String string2 = StringsKt.trim((CharSequence) editText2.getText().toString()).toString();
            if (string2.length() == 0) {
                string2 = null;
            }
            CustomerPaymentResult customerPaymentResultAddPayment = customerPaymentRepository.addPayment(str3, id2, string, str2, sypAmount, string2);
            alertDialog.dismiss();
            Toast.makeText(mainActivity, customerPaymentResultAddPayment.getOutstandingUsdCents() == 0 ? "\u062a\u0645 \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0648\u0623\u0635\u0628\u062d\u062a \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0645\u0633\u062f\u062f\u0629 \u0628\u0627\u0644\u0643\u0627\u0645\u0644" : "\u062a\u0645 \u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0627\u0644\u0645\u0627\u0644\u064a \u0627\u0644\u062c\u0632\u0626\u064a \u0628\u0646\u062c\u0627\u062d", 1).show();
            mainActivity.showCustomerAccount(str);
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062a\u062d\u0635\u064a\u0644";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    private final void showCustomerEditor(String customerId, final Function0<Unit> onSaved) {
        if (customerId != null && !isManager()) {
            Toast.makeText(this, "\u062a\u0639\u062f\u064a\u0644 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0639\u0645\u0644\u0627\u0621 \u0645\u062a\u0627\u062d \u0644\u0644\u0645\u062f\u064a\u0631 \u0641\u0642\u0637", 0).show();
            return;
        }
        MainActivity mainActivity = this;
        final CustomerRepository customerRepository = new CustomerRepository(mainActivity);
        CustomerSummary customerSummary = customerId != null ? customerRepository.get(customerId) : null;
        if (customerId != null && customerSummary == null) {
            Toast.makeText(mainActivity, "\u0645\u0644\u0641 \u0627\u0644\u0639\u0645\u064a\u0644 \u063a\u064a\u0631 \u0645\u0648\u062c\u0648\u062f", 0).show();
            return;
        }
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(18), dp(8), dp(18), dp(4));
        String name = customerSummary != null ? customerSummary.getName() : null;
        final EditText editTextFieldForDialog$default = fieldForDialog$default(this, "\u0627\u0633\u0645 \u0627\u0644\u0632\u0628\u0648\u0646 *", name == null ? "" : name, 0, 4, null);
        String phone = customerSummary != null ? customerSummary.getPhone() : null;
        if (phone == null) {
            phone = "";
        }
        final EditText editTextFieldForDialog = fieldForDialog("\u0631\u0642\u0645 \u0627\u0644\u0647\u0627\u062a\u0641", phone, 3);
        String address = customerSummary != null ? customerSummary.getAddress() : null;
        final EditText editTextFieldForDialog$default2 = fieldForDialog$default(this, "\u0627\u0644\u0639\u0646\u0648\u0627\u0646", address == null ? "" : address, 0, 4, null);
        String notes = customerSummary != null ? customerSummary.getNotes() : null;
        final EditText editTextFieldForDialog$default3 = fieldForDialog$default(this, "\u0645\u0644\u0627\u062d\u0638\u0627\u062a", notes == null ? "" : notes, 0, 4, null);
        linearLayout.addView(editTextFieldForDialog$default, fieldParams(dp(48), dp(8)));
        linearLayout.addView(editTextFieldForDialog, fieldParams(dp(48), dp(8)));
        linearLayout.addView(editTextFieldForDialog$default2, fieldParams(dp(48), dp(8)));
        linearLayout.addView(editTextFieldForDialog$default3, fieldParams(dp(48), dp(8)));
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(mainActivity).setTitle(customerSummary == null ? "\u0625\u0636\u0627\u0641\u0629 \u0632\u0628\u0648\u0646" : "\u062a\u0639\u062f\u064a\u0644 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0632\u0628\u0648\u0646").setView(linearLayout).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u062d\u0641\u0638", (DialogInterface.OnClickListener) null).create();
        final CustomerSummary customerSummary2 = customerSummary;
        alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda34
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                MainActivity.showCustomerEditor$lambda$322(alertDialogCreate, editTextFieldForDialog$default, customerSummary2, customerRepository, this, editTextFieldForDialog, editTextFieldForDialog$default2, editTextFieldForDialog$default3, onSaved, dialogInterface);
            }
        });
        alertDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showCustomerEditor$lambda$322(final AlertDialog alertDialog, final EditText editText, final CustomerSummary customerSummary, final CustomerRepository customerRepository, final MainActivity mainActivity, final EditText editText2, final EditText editText3, final EditText editText4, final Function0 function0, DialogInterface dialogInterface) {
        alertDialog.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda96
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.showCustomerEditor$lambda$322$lambda$321(editText, customerSummary, customerRepository, mainActivity, editText2, editText3, editText4, alertDialog, function0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showCustomerEditor$lambda$322$lambda$321(EditText editText, CustomerSummary customerSummary, CustomerRepository customerRepository, MainActivity mainActivity, EditText editText2, EditText editText3, EditText editText4, AlertDialog alertDialog, Function0 function0, View view) {
        try {
            String string = StringsKt.trim((CharSequence) editText.getText().toString()).toString();
            if (string.length() <= 0) {
                throw new IllegalArgumentException("\u0627\u0633\u0645 \u0627\u0644\u0632\u0628\u0648\u0646 \u0645\u0637\u0644\u0648\u0628".toString());
            }
            if (customerSummary == null) {
                String str = mainActivity.currentUserId;
                if (str != null) {
                    customerRepository.create(str, string, editText2.getText().toString(), editText3.getText().toString(), editText4.getText().toString());
                } else {
                    throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
                }
            } else {
                String str2 = mainActivity.currentUserId;
                if (str2 != null) {
                    customerRepository.update(str2, customerSummary.getId(), string, editText2.getText().toString(), editText3.getText().toString(), editText4.getText().toString());
                } else {
                    throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
                }
            }
            alertDialog.dismiss();
            Toast.makeText(mainActivity, "\u062a\u0645 \u062d\u0641\u0638 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0632\u0628\u0648\u0646", 0).show();
            function0.invoke();
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u062d\u0641\u0638 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0632\u0628\u0648\u0646";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    private final boolean isManager() {
        String str;
        return (!Intrinsics.areEqual(this.currentUserRole, "ADMIN") || (str = this.currentUserId) == null || StringsKt.isBlank(str)) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean hasPermission(String permissionKey) {
        String str = this.currentUserId;
        if (str == null) {
            return false;
        }
        try {
            PermissionPolicy permissionPolicy = PermissionPolicy.INSTANCE;
            SQLiteDatabase readableDatabase = AppDatabase.INSTANCE.getInstance(this).getReadableDatabase();
            Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
            permissionPolicy.require(readableDatabase, str, permissionKey);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void showUserManagement() {
        String str;
        if (!isManager()) {
            Toast.makeText(this, "\u0625\u062f\u0627\u0631\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645\u064a\u0646 \u0648\u0627\u0644\u0635\u0644\u0627\u062d\u064a\u0627\u062a \u0645\u062a\u0627\u062d\u0629 \u0644\u0644\u0645\u062f\u064a\u0631 \u0641\u0642\u0637", 0).show();
            return;
        }
        final String str2 = this.currentUserId;
        if (str2 == null) {
            return;
        }
        markScreen$default(this, "USER_MANAGEMENT", null, 2, null);
        MainActivity mainActivity = this;
        final AuthRepository authRepository = new AuthRepository(mainActivity);
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(this.backgroundColor);
        LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        linearLayout2.setPadding(dp(14), dp(10), dp(14), dp(10));
        linearLayout2.setBackgroundColor(-1);
        Button button = new Button(mainActivity);
        button.setText("\u2039");
        button.setTextSize(25.0f);
        button.setAllCaps(false);
        button.setTextColor(this.green);
        button.setBackgroundColor(0);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda166
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IOException {
                MainActivity.showPointOfSale$default(this.f$0, false, 1, null);
            }
        });
        linearLayout2.addView(button, new LinearLayout.LayoutParams(dp(48), dp(48)));
        linearLayout2.addView(label("\u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645\u0648\u0646 \u0648\u0627\u0644\u0635\u0644\u0627\u062d\u064a\u0627\u062a", 19.0f, this.green, true, 17), new LinearLayout.LayoutParams(0, -2, 1.0f));
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setFillViewport(true);
        LinearLayout linearLayout3 = new LinearLayout(mainActivity);
        linearLayout3.setOrientation(1);
        linearLayout3.setPadding(dp(16), dp(14), dp(16), dp(24));
        linearLayout3.addView(label("\u0625\u062f\u0627\u0631\u0629 \u062d\u0633\u0627\u0628\u0627\u062a \u0627\u0644\u0645\u062a\u062c\u0631", 21.0f, this.green, true, 5));
        TextView textViewLabel = label("\u0643\u0644 \u0645\u0648\u0638\u0641 \u064a\u0633\u062a\u062e\u062f\u0645 \u062d\u0633\u0627\u0628\u0627\u064b \u0645\u0633\u062a\u0642\u0644\u0627\u064b. \u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631 \u0627\u0644\u0645\u0624\u0642\u062a\u0629 \u064a\u062c\u0628 \u062a\u063a\u064a\u064a\u0631\u0647\u0627 \u0639\u0646\u062f \u0623\u0648\u0644 \u062f\u062e\u0648\u0644\u060c \u0648\u0625\u064a\u0642\u0627\u0641 \u0627\u0644\u062d\u0633\u0627\u0628 \u064a\u0645\u0646\u0639 \u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062f\u062e\u0648\u0644 \u0648\u0627\u0644\u0639\u0645\u0644\u064a\u0627\u062a \u0627\u0644\u062c\u062f\u064a\u062f\u0629.", 12.0f, this.mutedColor, false, 5);
        textViewLabel.setPadding(0, dp(5), 0, dp(12));
        linearLayout3.addView(textViewLabel);
        linearLayout3.addView(smallButton("+ \u0625\u0636\u0627\u0641\u0629 \u0645\u0633\u062a\u062e\u062f\u0645 \u062c\u062f\u064a\u062f", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda167
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showUserManagement$lambda$331(this.f$0);
            }
        }), fieldParams(dp(48), dp(10)));
        for (final ManagedUserSummary managedUserSummary : authRepository.listUsers(str2)) {
            LinearLayout linearLayout4 = new LinearLayout(mainActivity);
            linearLayout4.setOrientation(1);
            linearLayout4.setPadding(dp(13), dp(11), dp(13), dp(11));
            linearLayout4.setBackground(cardBackground());
            linearLayout4.addView(label(managedUserSummary.getDisplayName(), 16.0f, this.green, true, 5));
            TextView textViewLabel2 = label("@" + managedUserSummary.getUsername() + " \u2022 " + (Intrinsics.areEqual(managedUserSummary.getRoleKey(), "ADMIN") ? "\u0645\u062f\u064a\u0631" : "\u0645\u0648\u0638\u0641"), 12.0f, this.mutedColor, false, 5);
            textViewLabel2.setPadding(0, dp(3), 0, dp(3));
            linearLayout4.addView(textViewLabel2);
            if (managedUserSummary.isActive()) {
                str = managedUserSummary.getMustChangePassword() ? "\u0628\u0627\u0646\u062a\u0638\u0627\u0631 \u062a\u063a\u064a\u064a\u0631 \u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631" : "\u0627\u0644\u062d\u0633\u0627\u0628 \u0646\u0634\u0637";
            } else {
                str = "\u0627\u0644\u062d\u0633\u0627\u0628 \u0645\u0648\u0642\u0648\u0641";
            }
            linearLayout4.addView(label(str, 12.0f, managedUserSummary.isActive() ? this.green : this.mutedColor, true, 5));
            if (!Intrinsics.areEqual(managedUserSummary.getId(), str2)) {
                linearLayout4.addView(smallButton(managedUserSummary.isActive() ? "\u0625\u064a\u0642\u0627\u0641 \u0627\u0644\u062d\u0633\u0627\u0628" : "\u0625\u0639\u0627\u062f\u0629 \u062a\u0641\u0639\u064a\u0644 \u0627\u0644\u062d\u0633\u0627\u0628", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda168
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivity.showUserManagement$lambda$336$lambda$335(managedUserSummary, this, authRepository, str2);
                    }
                }), fieldParams(dp(42), dp(7)));
            }
            linearLayout3.addView(linearLayout4, fieldParams(-2, dp(8)));
        }
        linearLayout3.addView(sectionHeading("\u0635\u0644\u0627\u062d\u064a\u0627\u062a \u062f\u0648\u0631 \u0627\u0644\u0645\u0648\u0638\u0641"));
        TextView textViewLabel3 = label("\u062a\u064f\u0637\u0628\u0642 \u0627\u0644\u062a\u063a\u064a\u064a\u0631\u0627\u062a \u0639\u0644\u0649 \u062c\u0645\u064a\u0639 \u0627\u0644\u062d\u0633\u0627\u0628\u0627\u062a \u0630\u0627\u062a \u062f\u0648\u0631 \u00ab\u0645\u0648\u0638\u0641\u00bb. \u0627\u062a\u0631\u0643 \u0627\u0644\u0635\u0644\u0627\u062d\u064a\u0627\u062a \u0627\u0644\u0625\u062f\u0627\u0631\u064a\u0629 \u0645\u063a\u0644\u0642\u0629\u060c \u0648\u0627\u0645\u0646\u062d \u0641\u0642\u0637 \u0645\u0627 \u064a\u062d\u062a\u0627\u062c\u0647 \u0627\u0644\u0645\u0648\u0638\u0641 \u0641\u0639\u0644\u064a\u0627\u064b.", 12.0f, this.mutedColor, false, 5);
        textViewLabel3.setPadding(0, dp(4), 0, dp(8));
        linearLayout3.addView(textViewLabel3);
        PermissionPolicy permissionPolicy = PermissionPolicy.INSTANCE;
        SQLiteDatabase readableDatabase = AppDatabase.INSTANCE.getInstance(mainActivity).getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        for (final RolePermissionSetting rolePermissionSetting : permissionPolicy.listRolePermissions(readableDatabase, "CASHIER")) {
            CheckBox checkBox = new CheckBox(mainActivity);
            checkBox.setText(rolePermissionSetting.getDescription());
            checkBox.setTextSize(14.0f);
            checkBox.setTextColor(this.textColor);
            checkBox.setChecked(rolePermissionSetting.getAllowed());
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda169
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    MainActivity.showUserManagement$lambda$340$lambda$339$lambda$338(this.f$0, str2, rolePermissionSetting, compoundButton, z);
                }
            });
            linearLayout3.addView(checkBox, fieldParams(-2, dp(2)));
        }
        scrollView.addView(linearLayout3);
        linearLayout.addView(linearLayout2, matchWidth());
        linearLayout.addView(scrollView, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        setContentView(linearLayout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showUserManagement$lambda$331(final MainActivity mainActivity) {
        mainActivity.showCreateUserDialog(new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda136
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showUserManagement$lambda$331$lambda$330(this.f$0);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showUserManagement$lambda$331$lambda$330(MainActivity mainActivity) {
        mainActivity.showUserManagement();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showUserManagement$lambda$336$lambda$335(final ManagedUserSummary managedUserSummary, final MainActivity mainActivity, final AuthRepository authRepository, final String str) {
        String str2 = managedUserSummary.isActive() ? "\u0625\u064a\u0642\u0627\u0641" : "\u0625\u0639\u0627\u062f\u0629 \u062a\u0641\u0639\u064a\u0644";
        new AlertDialog.Builder(mainActivity).setTitle(str2.concat(" \u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645")).setMessage("\u0647\u0644 \u062a\u0631\u064a\u062f " + str2 + " \u062d\u0633\u0627\u0628 " + managedUserSummary.getDisplayName() + "\u061f").setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u062a\u0623\u0643\u064a\u062f", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda140
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.showUserManagement$lambda$336$lambda$335$lambda$334(authRepository, str, managedUserSummary, mainActivity, dialogInterface, i);
            }
        }).show();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showUserManagement$lambda$336$lambda$335$lambda$334(AuthRepository authRepository, String str, ManagedUserSummary managedUserSummary, MainActivity mainActivity, DialogInterface dialogInterface, int i) {
        try {
            authRepository.setUserActive(str, managedUserSummary.getId(), !managedUserSummary.isActive());
            Toast.makeText(mainActivity, "\u062a\u0645 \u062a\u062d\u062f\u064a\u062b \u062d\u0627\u0644\u0629 \u0627\u0644\u062d\u0633\u0627\u0628", 0).show();
            mainActivity.showUserManagement();
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u062a\u062d\u062f\u064a\u062b \u0627\u0644\u062d\u0633\u0627\u0628";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void showUserManagement$lambda$340$lambda$339$lambda$338(MainActivity mainActivity, String str, RolePermissionSetting rolePermissionSetting, CompoundButton compoundButton, boolean z) {
        try {
            PermissionPolicy permissionPolicy = PermissionPolicy.INSTANCE;
            SQLiteDatabase writableDatabase = AppDatabase.INSTANCE.getInstance(mainActivity).getWritableDatabase();
            Intrinsics.checkNotNullExpressionValue(writableDatabase, "<get-writableDatabase>(...)");
            permissionPolicy.setRolePermission(writableDatabase, str, "CASHIER", rolePermissionSetting.getKey(), z);
            Toast.makeText(mainActivity, "\u062a\u0645 \u062a\u062d\u062f\u064a\u062b \u0635\u0644\u0627\u062d\u064a\u0629 \u0627\u0644\u0645\u0648\u0638\u0641", 0).show();
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u062a\u063a\u064a\u064a\u0631 \u0627\u0644\u0635\u0644\u0627\u062d\u064a\u0629";
            }
            Toast.makeText(mainActivity2, message, 1).show();
            mainActivity.showUserManagement();
        }
    }

    private final void showCreateUserDialog(final Function0<Unit> onSaved) {
        if (isManager()) {
            MainActivity mainActivity = this;
            LinearLayout linearLayout = new LinearLayout(mainActivity);
            linearLayout.setOrientation(1);
            linearLayout.setPadding(dp(18), dp(8), dp(18), dp(4));
            final EditText editText = new EditText(mainActivity);
            editText.setHint("\u0627\u0633\u0645 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 (3 \u0623\u062d\u0631\u0641 \u0639\u0644\u0649 \u0627\u0644\u0623\u0642\u0644)");
            editText.setSingleLine(true);
            editText.setTextSize(15.0f);
            editText.setBackground(inputBackground());
            final EditText editText2 = new EditText(mainActivity);
            editText2.setHint("\u0627\u0644\u0627\u0633\u0645 \u0627\u0644\u0638\u0627\u0647\u0631 \u0644\u0644\u0645\u0648\u0638\u0641");
            editText2.setSingleLine(true);
            editText2.setTextSize(15.0f);
            editText2.setBackground(inputBackground());
            final EditText editText3 = new EditText(mainActivity);
            editText3.setHint("\u0643\u0644\u0645\u0629 \u0645\u0631\u0648\u0631 \u0645\u0624\u0642\u062a\u0629 \u0642\u0648\u064a\u0629");
            editText3.setSingleLine(true);
            editText3.setTextSize(15.0f);
            editText3.setInputType(129);
            editText3.setBackground(inputBackground());
            final Spinner spinner = new Spinner(mainActivity);
            spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, CollectionsKt.listOf((Object[]) new String[]{"\u0645\u0648\u0638\u0641", "\u0645\u062f\u064a\u0631"})));
            linearLayout.addView(editText, fieldParams(dp(50), dp(7)));
            linearLayout.addView(editText2, fieldParams(dp(50), dp(7)));
            linearLayout.addView(editText3, fieldParams(dp(50), dp(7)));
            linearLayout.addView(label("\u0646\u0648\u0639 \u0627\u0644\u062d\u0633\u0627\u0628", 12.0f, this.mutedColor, false, 5));
            linearLayout.addView(spinner, fieldParams(dp(48), dp(4)));
            final AlertDialog alertDialogCreate = new AlertDialog.Builder(mainActivity).setTitle("\u0625\u0636\u0627\u0641\u0629 \u0645\u0633\u062a\u062e\u062f\u0645").setMessage("\u0633\u064a\u064f\u0637\u0644\u0628 \u0645\u0646 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u062a\u063a\u064a\u064a\u0631 \u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631 \u0627\u0644\u0645\u0624\u0642\u062a\u0629 \u0639\u0646\u062f \u0623\u0648\u0644 \u062a\u0633\u062c\u064a\u0644 \u062f\u062e\u0648\u0644.").setView(linearLayout).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u0625\u0646\u0634\u0627\u0621", (DialogInterface.OnClickListener) null).create();
            alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda38
                @Override // android.content.DialogInterface.OnShowListener
                public final void onShow(DialogInterface dialogInterface) {
                    MainActivity.showCreateUserDialog$lambda$347(alertDialogCreate, this, editText, editText2, editText3, spinner, onSaved, dialogInterface);
                }
            });
            alertDialogCreate.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showCreateUserDialog$lambda$347(final AlertDialog alertDialog, final MainActivity mainActivity, final EditText editText, final EditText editText2, final EditText editText3, final Spinner spinner, final Function0 function0, DialogInterface dialogInterface) {
        alertDialog.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda98
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.showCreateUserDialog$lambda$347$lambda$346(this.f$0, editText, editText2, editText3, spinner, alertDialog, function0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showCreateUserDialog$lambda$347$lambda$346(MainActivity mainActivity, EditText editText, EditText editText2, EditText editText3, Spinner spinner, AlertDialog alertDialog, Function0 function0, View view) {
        try {
            String str = mainActivity.currentUserId;
            if (str == null) {
                throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u062f\u064a\u0631 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
            }
            new AuthRepository(mainActivity).createUser(mainActivity.currentUserRole, str, editText.getText().toString(), editText2.getText().toString(), editText3.getText().toString(), spinner.getSelectedItemPosition() == 1 ? "ADMIN" : "CASHIER");
            alertDialog.dismiss();
            Toast.makeText(mainActivity, "\u062a\u0645 \u0625\u0646\u0634\u0627\u0621 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645. \u064a\u062c\u0628 \u0639\u0644\u064a\u0647 \u062a\u063a\u064a\u064a\u0631 \u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631 \u0639\u0646\u062f \u0623\u0648\u0644 \u062f\u062e\u0648\u0644.", 1).show();
            function0.invoke();
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u0625\u0646\u0634\u0627\u0621 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:63:0x0402  */
    public final void showCashAccounts() throws IOException {
        Object next;
        String str;
        markScreen$default(this, "CASH_ACCOUNTS", null, 2, null);
        MainActivity mainActivity = this;
        final CashAccountRepository cashAccountRepository = new CashAccountRepository(mainActivity);
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(this.backgroundColor);
        LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        linearLayout2.setPadding(dp(14), dp(10), dp(14), dp(10));
        linearLayout2.setBackgroundColor(-1);
        Button button = new Button(mainActivity);
        button.setText("\u2039");
        button.setTextSize(25.0f);
        button.setAllCaps(false);
        button.setTextColor(this.green);
        button.setBackgroundColor(0);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda62
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IOException {
                MainActivity.showPointOfSale$default(this.f$0, false, 1, null);
            }
        });
        linearLayout2.addView(button, new LinearLayout.LayoutParams(dp(48), dp(48)));
        linearLayout2.addView(label("\u0627\u0644\u0635\u0646\u0627\u062f\u064a\u0642 \u0648\u0627\u0644\u0645\u062d\u0627\u0641\u0638", 19.0f, this.green, true, 17), new LinearLayout.LayoutParams(0, -2, 1.0f));
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setFillViewport(true);
        LinearLayout linearLayout3 = new LinearLayout(mainActivity);
        linearLayout3.setOrientation(1);
        linearLayout3.setPadding(dp(16), dp(16), dp(16), dp(24));
        List<ServiceWalletSummary> listListWallets = new ServiceTransferRepository(mainActivity).listWallets();
        linearLayout3.addView(label("\u0627\u0644\u062d\u0633\u0627\u0628\u0627\u062a \u0627\u0644\u0645\u0627\u0644\u064a\u0629", 22.0f, this.green, true, 5));
        TextView textViewLabel = label("\u0627\u0644\u062d\u0633\u0627\u0628\u0627\u062a \u0627\u0644\u0623\u0633\u0627\u0633\u064a\u0629 \u062a\u064f\u0646\u0634\u0623 \u0648\u062a\u064f\u0631\u0628\u0637 \u062a\u0644\u0642\u0627\u0626\u064a\u0627\u064b: \u062f\u0631\u062c \u0627\u0644\u0628\u064a\u0639 \u0627\u0644\u064a\u0648\u0645\u064a\u060c \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0627\u0644\u0645\u062f\u0648\u0631\u060c \u0635\u0646\u062f\u0648\u0642 \u0634\u0631\u0627\u0621 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0627\u062a\u060c \u0648\u0645\u062d\u0641\u0638\u062a\u0627 \u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0648\u0634\u0627\u0645 \u0643\u0627\u0634. \u064a\u0645\u0643\u0646\u0643 \u0625\u0636\u0627\u0641\u0629 \u062d\u0633\u0627\u0628\u0627\u062a \u0623\u062e\u0631\u0649 \u0639\u0646\u062f \u0627\u0644\u062d\u0627\u062c\u0629.", 13.0f, this.mutedColor, false, 5);
        int i = 12;
        textViewLabel.setPadding(0, dp(5), 0, dp(12));
        linearLayout3.addView(textViewLabel);
        if (isManager()) {
            Button button2 = new Button(mainActivity);
            button2.setText("+ \u0625\u0636\u0627\u0641\u0629 \u0635\u0646\u062f\u0648\u0642 \u0623\u0648 \u0645\u062d\u0641\u0638\u0629");
            button2.setAllCaps(false);
            button2.setTextColor(-1);
            button2.setBackgroundColor(this.green);
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda63
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.showCreateCashAccountDialog();
                }
            });
            linearLayout3.addView(button2, fieldParams(dp(50), dp(12)));
        }
        linearLayout3.addView(smallButton("\u0625\u062f\u0627\u0631\u0629 \u0627\u0644\u0623\u0631\u0635\u062f\u0629 \u0648\u0627\u0644\u062a\u062d\u0648\u064a\u0644\u0627\u062a \u0627\u0644\u064a\u0648\u0645\u064a\u0629", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda64
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showCashAccounts$lambda$357(this.f$0);
            }
        }), fieldParams(dp(46), dp(6)));
        linearLayout3.addView(smallButton("\u0643\u0634\u0641 \u0627\u0644\u0645\u0642\u0628\u0648\u0636\u0627\u062a \u0648\u0627\u0644\u062d\u0631\u0643\u0627\u062a \u0627\u0644\u064a\u0648\u0645\u064a\u0629", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda65
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showCashAccounts$lambda$358(this.f$0);
            }
        }), fieldParams(dp(46), dp(10)));
        Iterator it = cashAccountRepository.listAll().iterator();
        while (it.hasNext()) {
            final CashAccountSummary cashAccountSummary = (CashAccountSummary) it.next();
            LinearLayout linearLayout4 = new LinearLayout(mainActivity);
            linearLayout4.setOrientation(1);
            linearLayout4.setPadding(dp(14), dp(i), dp(14), dp(i));
            linearLayout4.setBackground(cardBackground());
            int i2 = i;
            linearLayout4.addView(label(cashAccountSummary.getName(), 16.0f, this.green, true, 5));
            TextView textViewLabel2 = label(cashAccountTypeArabic(cashAccountSummary.getAccountType()) + " \u2022 " + cashAccountSummary.getCurrencyCode(), 12.0f, this.mutedColor, false, 5);
            textViewLabel2.setPadding(0, dp(4), 0, dp(4));
            linearLayout4.addView(textViewLabel2);
            Iterator it2 = it;
            linearLayout4.addView(label("\u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u062d\u0627\u0644\u064a: " + formatAccountBalance(cashAccountSummary.getBalanceMinor(), cashAccountSummary.getCurrencyCode()), 15.0f, this.textColor, true, 5));
            linearLayout4.addView(label(cashAccountSummary.isActive() ? "\u0646\u0634\u0637" : "\u0645\u0648\u0642\u0648\u0641", 11.0f, cashAccountSummary.isActive() ? this.green : this.mutedColor, false, 5));
            CashAccountSummary cashAccountSummaryCurrentDayPrimaryDrawer = cashAccountRepository.currentDayPrimaryDrawer();
            String strRechargeAccountId = new ServiceTransferRepository(mainActivity).rechargeAccountId();
            String strSystemRoleAccountId = cashAccountRepository.systemRoleAccountId("SUPPLIER_ROLLOVER");
            if (Intrinsics.areEqual(cashAccountSummaryCurrentDayPrimaryDrawer != null ? cashAccountSummaryCurrentDayPrimaryDrawer.getId() : null, cashAccountSummary.getId())) {
                TextView textViewLabel3 = label("\u0627\u0644\u062f\u0631\u062c \u0627\u0644\u0646\u0642\u062f\u064a \u0627\u0644\u0631\u0626\u064a\u0633\u064a \u0644\u0644\u064a\u0648\u0645", 12.0f, this.green, true, 5);
                textViewLabel3.setPadding(0, dp(4), 0, dp(2));
                linearLayout4.addView(textViewLabel3);
            }
            if (Intrinsics.areEqual(strRechargeAccountId, cashAccountSummary.getId())) {
                TextView textViewLabel4 = label("\u0635\u0646\u062f\u0648\u0642 \u0634\u0631\u0627\u0621 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0627\u062a \u0627\u0644\u0645\u0648\u062d\u062f", 12.0f, this.green, true, 5);
                textViewLabel4.setPadding(0, dp(4), 0, dp(2));
                linearLayout4.addView(textViewLabel4);
            }
            Iterator<T> it3 = listListWallets.iterator();
            do {
                if (!it3.hasNext()) {
                    next = null;
                    break;
                }
                next = it3.next();
            } while (!Intrinsics.areEqual(((ServiceWalletSummary) next).getAccountId(), cashAccountSummary.getId()));
            ServiceWalletSummary serviceWalletSummary = (ServiceWalletSummary) next;
            if (serviceWalletSummary != null) {
                TextView textViewLabel5 = label(Intrinsics.areEqual(serviceWalletSummary.getServiceKey(), ServiceTransferRepository.SYRIATEL_SHARED) ? "\u0645\u062d\u0641\u0638\u0629 \u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u2022 \u062a\u0634\u063a\u064a\u0644 \u0648\u0642\u0628\u0636 \u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a" : "\u0645\u062d\u0641\u0638\u0629 \u0634\u0627\u0645 \u0643\u0627\u0634 \u2022 \u062a\u0634\u063a\u064a\u0644 \u0648\u0642\u0628\u0636 \u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a", 12.0f, this.green, true, 5);
                textViewLabel5.setPadding(0, dp(4), 0, dp(2));
                linearLayout4.addView(textViewLabel5);
            }
            if (Intrinsics.areEqual(strSystemRoleAccountId, cashAccountSummary.getId())) {
                TextView textViewLabel6 = label("\u0627\u0644\u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0644\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0644\u0628\u0636\u0627\u0639\u0629", 12.0f, this.green, true, 5);
                textViewLabel6.setPadding(0, dp(4), 0, dp(2));
                linearLayout4.addView(textViewLabel6);
            }
            if (isManager() && cashAccountSummary.isActive() && Intrinsics.areEqual(cashAccountSummary.getAccountType(), "CASH_BOX") && Intrinsics.areEqual(cashAccountSummary.getCurrencyCode(), "SYP")) {
                if (Intrinsics.areEqual(cashAccountSummary.getId(), cashAccountSummaryCurrentDayPrimaryDrawer != null ? cashAccountSummaryCurrentDayPrimaryDrawer.getId() : null)) {
                    str = r5;
                } else {
                    str = strSystemRoleAccountId;
                    if (!Intrinsics.areEqual(cashAccountSummary.getId(), str)) {
                        linearLayout4.addView(smallButton("\u062a\u0639\u064a\u064a\u0646 \u0643\u0635\u0646\u062f\u0648\u0642 \u0634\u0631\u0627\u0621 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0627\u062a", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda66
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return MainActivity.showCashAccounts$lambda$371$lambda$367(this.f$0, cashAccountSummary);
                            }
                        }), fieldParams(dp(42), dp(4)));
                    }
                }
            } else {
                str = r5;
            }
            if (isManager() && cashAccountSummary.isActive() && Intrinsics.areEqual(cashAccountSummary.getAccountType(), "CASH_BOX") && Intrinsics.areEqual(cashAccountSummary.getCurrencyCode(), "SYP") && !Intrinsics.areEqual(cashAccountSummary.getId(), strRechargeAccountId) && !Intrinsics.areEqual(cashAccountSummary.getId(), str)) {
                linearLayout4.addView(smallButton("\u062a\u0639\u064a\u064a\u0646 \u0643\u062f\u0631\u062c \u0627\u0644\u064a\u0648\u0645 \u0627\u0644\u0631\u0626\u064a\u0633\u064a", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda68
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivity.showCashAccounts$lambda$371$lambda$368(cashAccountRepository, this, cashAccountSummary);
                    }
                }), fieldParams(dp(42), dp(4)));
            }
            if (isManager()) {
                linearLayout4.addView(smallButton("\u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u062d\u0633\u0627\u0628", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda69
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivity.showCashAccounts$lambda$371$lambda$370(this.f$0, cashAccountSummary);
                    }
                }), fieldParams(dp(42), dp(4)));
            }
            linearLayout3.addView(linearLayout4, fieldParams(-2, dp(8)));
            i = i2;
            it = it2;
        }
        scrollView.addView(linearLayout3);
        linearLayout.addView(linearLayout2, matchWidth());
        linearLayout.addView(scrollView, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        setContentView(linearLayout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showCashAccounts$lambda$357(MainActivity mainActivity) throws IOException {
        mainActivity.showDailyOperationsDialog();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showCashAccounts$lambda$358(MainActivity mainActivity) throws IOException {
        mainActivity.showDailyCashReport();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showCashAccounts$lambda$371$lambda$367(MainActivity mainActivity, CashAccountSummary cashAccountSummary) {
        try {
            ServiceTransferRepository serviceTransferRepository = new ServiceTransferRepository(mainActivity);
            String str = mainActivity.currentUserId;
            if (str == null) {
                throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u062f\u064a\u0631 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
            }
            serviceTransferRepository.setRechargeAccount(str, cashAccountSummary.getId());
            Toast.makeText(mainActivity, "\u062a\u0645 \u062a\u0639\u064a\u064a\u0646 \u0635\u0646\u062f\u0648\u0642 \u0634\u0631\u0627\u0621 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0627\u062a", 1).show();
            mainActivity.showCashAccounts();
            return Unit.INSTANCE;
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u062a\u0639\u064a\u064a\u0646 \u0635\u0646\u062f\u0648\u0642 \u0634\u0631\u0627\u0621 \u0627\u0644\u0631\u0635\u064a\u062f";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showCashAccounts$lambda$371$lambda$368(CashAccountRepository cashAccountRepository, MainActivity mainActivity, CashAccountSummary cashAccountSummary) {
        try {
            String str = mainActivity.currentUserRole;
            String str2 = mainActivity.currentUserId;
            if (str2 != null) {
                cashAccountRepository.setCurrentDayPrimaryDrawer(str, str2, cashAccountSummary.getId());
                Toast.makeText(mainActivity, "\u062a\u0645 \u062a\u0639\u064a\u064a\u0646 " + cashAccountSummary.getName() + " \u0643\u062f\u0631\u062c \u0627\u0644\u064a\u0648\u0645 \u0627\u0644\u0631\u0626\u064a\u0633\u064a. \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0645 \u064a\u062a\u063a\u064a\u0631.", 1).show();
                mainActivity.showCashAccounts();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u062f\u064a\u0631 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u062a\u0639\u064a\u064a\u0646 \u0627\u0644\u062f\u0631\u062c \u0627\u0644\u064a\u0648\u0645\u064a";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showCashAccounts$lambda$371$lambda$370(final MainActivity mainActivity, CashAccountSummary cashAccountSummary) {
        mainActivity.showEditCashAccountDialog(cashAccountSummary, new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda127
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showCashAccounts$lambda$371$lambda$370$lambda$369(this.f$0);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showCashAccounts$lambda$371$lambda$370$lambda$369(MainActivity mainActivity) throws IOException {
        mainActivity.showCashAccounts();
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r0v50, types: [T, android.app.AlertDialog] */
    /* JADX WARN: Type inference failed for: r0v6, types: [T, java.util.List] */
    /* JADX WARN: Type inference failed for: r0v7, types: [T, java.util.List] */
    private final void showDailyOperationsDialog() throws IOException {
        if (!isManager()) {
            Toast.makeText(this, "\u0625\u062f\u0627\u0631\u0629 \u0627\u0644\u0623\u0631\u0635\u062f\u0629 \u0627\u0644\u064a\u0648\u0645\u064a\u0629 \u0645\u062a\u0627\u062d\u0629 \u0644\u0644\u0645\u062f\u064a\u0631 \u0641\u0642\u0637", 0).show();
            return;
        }
        MainActivity mainActivity = this;
        final DailyBalanceRepository dailyBalanceRepository = new DailyBalanceRepository(mainActivity);
        final CashAccountRepository cashAccountRepository = new CashAccountRepository(mainActivity);
        final List<CashAccountSummary> listListActive = cashAccountRepository.listActive();
        final String strSystemRoleAccountId = cashAccountRepository.systemRoleAccountId("SUPPLIER_ROLLOVER");
        List listListOf = CollectionsKt.listOf((Object[]) new String[]{"\u062a\u062d\u062f\u064a\u062f \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0644\u0645\u0648\u0631\u062f\u064a\u0646", "\u062a\u0633\u062c\u064a\u0644 \u0645\u0633\u062d\u0648\u0628\u0627\u062a \u0634\u062e\u0635\u064a\u0629", "\u0645\u0637\u0627\u0628\u0642\u0629 \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0627\u0641\u062a\u062a\u0627\u062d\u064a \u0627\u0644\u064a\u0648\u0645\u064a \u0644\u0645\u062d\u0641\u0638\u0629", "\u062a\u062d\u0648\u064a\u0644 \u0645\u062d\u0641\u0638\u0629 \u0625\u0644\u0649 \u0635\u0646\u062f\u0648\u0642 \u0646\u0642\u062f\u064a \u062a\u062e\u062a\u0627\u0631\u0647", "\u062a\u062d\u0648\u064a\u0644 \u0646\u0642\u062f\u064a\u0629 \u0628\u064a\u0646 \u0635\u0646\u062f\u0648\u0642\u064a\u0646"});
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(18), dp(8), dp(18), dp(4));
        final Spinner spinner = new Spinner(mainActivity);
        spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, listListOf));
        final Spinner spinner2 = new Spinner(mainActivity);
        final TextView textViewLabel = label("", 11.0f, this.mutedColor, false, 5);
        final Spinner spinner3 = new Spinner(mainActivity);
        final TextView textViewLabel2 = label("\u0648\u062c\u0647\u0629 \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0646\u0642\u062f\u064a\u0629", 12.0f, this.mutedColor, false, 5);
        final TextView textViewLabel3 = label("", 11.0f, this.mutedColor, false, 5);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = CollectionsKt.emptyList();
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        objectRef3.element = CollectionsKt.emptyList();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.hazem.dailystore.MainActivity.showDailyOperationsDialog.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> parent) throws IOException {
                MainActivity.showDailyOperationsDialog$refreshAccounts(spinner, objectRef2, spinner2, listListActive, this, textViewLabel, objectRef3, spinner3, dailyBalanceRepository, textViewLabel2, textViewLabel3, cashAccountRepository, strSystemRoleAccountId);
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) throws IOException {
                MainActivity.showDailyOperationsDialog$refreshAccounts(spinner, objectRef2, spinner2, listListActive, this, textViewLabel, objectRef3, spinner3, dailyBalanceRepository, textViewLabel2, textViewLabel3, cashAccountRepository, strSystemRoleAccountId);
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.hazem.dailystore.MainActivity.showDailyOperationsDialog.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> parent) {
            }

            /* JADX WARN: Type inference failed for: r10v5, types: [T, java.util.List] */
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList arrayListListOf;
                if (spinner.getSelectedItemPosition() != 4) {
                    return;
                }
                CashAccountSummary cashAccountSummary = (CashAccountSummary) CollectionsKt.getOrNull(objectRef2.element, position);
                String id2 = cashAccountSummary != null ? cashAccountSummary.getId() : null;
                CashAccountSummary cashAccountSummary2 = (CashAccountSummary) CollectionsKt.getOrNull(objectRef3.element, spinner3.getSelectedItemPosition());
                String id3 = cashAccountSummary2 != null ? cashAccountSummary2.getId() : null;
                List<CashAccountSummary> list = listListActive;
                ArrayList arrayList = new ArrayList();
                for (Object obj : list) {
                    CashAccountSummary cashAccountSummary3 = (CashAccountSummary) obj;
                    if (Intrinsics.areEqual(cashAccountSummary3.getAccountType(), "CASH_BOX") && Intrinsics.areEqual(cashAccountSummary3.getCurrencyCode(), "SYP") && !Intrinsics.areEqual(cashAccountSummary3.getId(), id2)) {
                        arrayList.add(obj);
                    }
                }
                ArrayList arrayList2 = arrayList;
                objectRef3.element = arrayList2;
                Spinner spinner4 = spinner3;
                MainActivity mainActivity2 = this;
                if (arrayList2.isEmpty()) {
                    arrayListListOf = CollectionsKt.listOf("\u0644\u0627 \u062a\u0648\u062c\u062f \u0648\u062c\u0647\u0629 \u0646\u0642\u062f\u064a\u0629 \u0645\u062a\u0627\u062d\u0629");
                } else {
                    ArrayList<CashAccountSummary> arrayList3 = arrayList2;
                    MainActivity mainActivity3 = this;
                    ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
                    for (CashAccountSummary cashAccountSummary4 : arrayList3) {
                        arrayList4.add(cashAccountSummary4.getName() + " \u2022 " + mainActivity3.formatSypAmount(cashAccountSummary4.getBalanceMinor()) + " \u0644.\u0633");
                    }
                    arrayListListOf = arrayList4;
                }
                spinner4.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity2, android.R.layout.simple_spinner_dropdown_item, arrayListListOf));
                if (id3 != null) {
                    Iterator it = arrayList2.iterator();
                    int i = 0;
                    while (true) {
                        if (!it.hasNext()) {
                            i = -1;
                            break;
                        } else if (Intrinsics.areEqual(((CashAccountSummary) it.next()).getId(), id3)) {
                            break;
                        } else {
                            i++;
                        }
                    }
                    if (i >= 0) {
                        spinner3.setSelection(i);
                    }
                }
            }
        });
        final EditText editTextFieldForDialog$default = fieldForDialog$default(this, "\u0627\u0644\u0645\u0628\u0644\u063a", null, 0, 6, null);
        editTextFieldForDialog$default.setInputType(8194);
        final EditText editTextFieldForDialog$default2 = fieldForDialog$default(this, "\u0645\u0631\u062c\u0639 / \u0645\u0644\u0627\u062d\u0638\u0629", null, 0, 6, null);
        linearLayout.addView(label("\u0646\u0648\u0639 \u0627\u0644\u0639\u0645\u0644\u064a\u0629", 12.0f, this.mutedColor, false, 5));
        linearLayout.addView(spinner, fieldParams(dp(48), dp(8)));
        linearLayout.addView(label("\u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a", 12.0f, this.mutedColor, false, 5));
        linearLayout.addView(spinner2, fieldParams(dp(48), dp(8)));
        linearLayout.addView(textViewLabel, fieldParams(-2, dp(2)));
        linearLayout.addView(textViewLabel2, fieldParams(-2, dp(2)));
        linearLayout.addView(spinner3, fieldParams(dp(48), dp(8)));
        linearLayout.addView(textViewLabel3, fieldParams(-2, dp(2)));
        linearLayout.addView(smallButton("\u0625\u0636\u0627\u0641\u0629 \u0635\u0646\u062f\u0648\u0642 \u0623\u0648 \u0645\u062d\u0641\u0638\u0629", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda114
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showDailyOperationsDialog$lambda$385(objectRef, this);
            }
        }), fieldParams(dp(42), dp(4)));
        linearLayout.addView(editTextFieldForDialog$default, fieldParams(dp(48), dp(8)));
        linearLayout.addView(editTextFieldForDialog$default2, fieldParams(dp(48), dp(8)));
        showDailyOperationsDialog$refreshAccounts(spinner, objectRef2, spinner2, listListActive, this, textViewLabel, objectRef3, spinner3, dailyBalanceRepository, textViewLabel2, textViewLabel3, cashAccountRepository, strSystemRoleAccountId);
        final String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        final ?? Create = new AlertDialog.Builder(mainActivity).setTitle("\u0625\u062f\u0627\u0631\u0629 \u0627\u0644\u0623\u0631\u0635\u062f\u0629 \u0627\u0644\u064a\u0648\u0645\u064a\u0629").setView(linearLayout).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u062a\u0646\u0641\u064a\u0630", (DialogInterface.OnClickListener) null).create();
        objectRef.element = Create;
        Create.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda115
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                MainActivity.showDailyOperationsDialog$lambda$392(spinner, Create, objectRef2, spinner2, listListActive, this, textViewLabel, objectRef3, spinner3, dailyBalanceRepository, textViewLabel2, textViewLabel3, cashAccountRepository, strSystemRoleAccountId, editTextFieldForDialog$default, editTextFieldForDialog$default2, string, dialogInterface);
            }
        });
        Create.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [T, java.util.List] */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r10v35 */
    /* JADX WARN: Type inference failed for: r10v36 */
    /* JADX WARN: Type inference failed for: r10v37 */
    /* JADX WARN: Type inference failed for: r10v38 */
    /* JADX WARN: Type inference failed for: r10v39 */
    /* JADX WARN: Type inference failed for: r10v5, types: [T, java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r23v0, types: [android.widget.Spinner] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r6v15 */
    public static final void showDailyOperationsDialog$refreshAccounts(Spinner spinner, Ref.ObjectRef<List<CashAccountSummary>> objectRef, Spinner spinner2, List<CashAccountSummary> list, MainActivity mainActivity, TextView textView, Ref.ObjectRef<List<CashAccountSummary>> objectRef2, Spinner spinner3, DailyBalanceRepository dailyBalanceRepository, TextView textView2, TextView textView3, CashAccountRepository cashAccountRepository, final String str) throws IOException {
        ?? SortedWith;
        String str2;
        String str3;
        ArrayList arrayListListOf;
        CharSequence charSequence;
        ?? EmptyList;
        ArrayList arrayListListOf2;
        int i;
        String str4;
        int iCoerceAtLeast = RangesKt.coerceAtLeast(spinner.getSelectedItemPosition(), 0);
        CashAccountSummary cashAccountSummary = (CashAccountSummary) CollectionsKt.getOrNull(objectRef.element, spinner2.getSelectedItemPosition());
        String id = cashAccountSummary != null ? cashAccountSummary.getId() : null;
        String str5 = "CASH_BOX";
        String str6 = "SYP";
        if (iCoerceAtLeast == 0) {
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                CashAccountSummary cashAccountSummary2 = (CashAccountSummary) next;
                if (Intrinsics.areEqual(cashAccountSummary2.getAccountType(), "CASH_BOX") && Intrinsics.areEqual(cashAccountSummary2.getCurrencyCode(), "SYP")) {
                    String id2 = cashAccountSummary2.getId();
                    CashAccountSummary cashAccountSummaryCurrentDayPrimaryDrawer = cashAccountRepository.currentDayPrimaryDrawer();
                    if (!Intrinsics.areEqual(id2, cashAccountSummaryCurrentDayPrimaryDrawer != null ? cashAccountSummaryCurrentDayPrimaryDrawer.getId() : null) && !Intrinsics.areEqual(cashAccountSummary2.getId(), new ServiceTransferRepository(mainActivity).rechargeAccountId())) {
                        arrayList.add(next);
                    }
                } else {
                    it = it;
                }
                it = it;
            }
            SortedWith = CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.hazem.dailystore.MainActivity$showDailyOperationsDialog$refreshAccounts$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Integer.valueOf(!Intrinsics.areEqual(((CashAccountSummary) t).getId(), str) ? 1 : 0), Integer.valueOf(!Intrinsics.areEqual(((CashAccountSummary) t2).getId(), str) ? 1 : 0));
                }
            });
        } else if (iCoerceAtLeast == 1) {
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : list) {
                CashAccountSummary cashAccountSummary3 = (CashAccountSummary) obj;
                if (Intrinsics.areEqual(cashAccountSummary3.getAccountType(), "CASH_BOX") && Intrinsics.areEqual(cashAccountSummary3.getCurrencyCode(), "SYP")) {
                    String id3 = cashAccountSummary3.getId();
                    SupplierRolloverSummary supplierRolloverSummaryCurrentSupplierRollover = dailyBalanceRepository.currentSupplierRollover();
                    if (Intrinsics.areEqual(id3, supplierRolloverSummaryCurrentSupplierRollover != null ? supplierRolloverSummaryCurrentSupplierRollover.getAccountId() : null)) {
                        arrayList2.add(obj);
                    }
                }
            }
            SortedWith = arrayList2;
        } else if (iCoerceAtLeast == 2) {
            ArrayList arrayList3 = new ArrayList();
            for (Object obj2 : list) {
                if (Intrinsics.areEqual(((CashAccountSummary) obj2).getAccountType(), "WALLET")) {
                    arrayList3.add(obj2);
                }
            }
            SortedWith = arrayList3;
        } else if (iCoerceAtLeast == 3) {
            ArrayList arrayList4 = new ArrayList();
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                Object next2 = it2.next();
                CashAccountSummary cashAccountSummary4 = (CashAccountSummary) next2;
                Iterator it3 = it2;
                if (Intrinsics.areEqual(cashAccountSummary4.getAccountType(), "WALLET") && Intrinsics.areEqual(cashAccountSummary4.getCurrencyCode(), "SYP")) {
                    arrayList4.add(next2);
                }
                it2 = it3;
            }
            SortedWith = arrayList4;
        } else {
            ArrayList arrayList5 = new ArrayList();
            Iterator it4 = list.iterator();
            while (it4.hasNext()) {
                Object next3 = it4.next();
                CashAccountSummary cashAccountSummary5 = (CashAccountSummary) next3;
                Iterator it5 = it4;
                if (Intrinsics.areEqual(cashAccountSummary5.getAccountType(), "CASH_BOX") && Intrinsics.areEqual(cashAccountSummary5.getCurrencyCode(), "SYP") && !Intrinsics.areEqual(cashAccountSummary5.getId(), new ServiceTransferRepository(mainActivity).rechargeAccountId()) && !Intrinsics.areEqual(cashAccountSummary5.getId(), str)) {
                    String id4 = cashAccountSummary5.getId();
                    SupplierRolloverSummary supplierRolloverSummaryCurrentSupplierRollover2 = dailyBalanceRepository.currentSupplierRollover();
                    if (!Intrinsics.areEqual(id4, supplierRolloverSummaryCurrentSupplierRollover2 != null ? supplierRolloverSummaryCurrentSupplierRollover2.getAccountId() : null)) {
                        arrayList5.add(next3);
                    }
                }
                it4 = it5;
            }
            SortedWith = arrayList5;
        }
        objectRef.element = SortedWith;
        spinner2.setTag(SortedWith);
        if (SortedWith.isEmpty()) {
            arrayListListOf = CollectionsKt.listOf("\u0644\u0627 \u064a\u0648\u062c\u062f \u062d\u0633\u0627\u0628 \u0645\u0627\u0644\u064a \u0645\u0646\u0627\u0633\u0628 \u0644\u0647\u0630\u0647 \u0627\u0644\u0639\u0645\u0644\u064a\u0629");
            str2 = "CASH_BOX";
            str3 = "SYP";
        } else {
            Iterable iterable = (Iterable) SortedWith;
            ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            Iterator it6 = iterable.iterator();
            while (it6.hasNext()) {
                CashAccountSummary cashAccountSummary6 = (CashAccountSummary) it6.next();
                Iterator it7 = it6;
                arrayList6.add(cashAccountSummary6.getName() + " \u2022 " + cashAccountSummary6.getCurrencyCode() + " \u2022 " + mainActivity.formatAccountBalance(cashAccountSummary6.getBalanceMinor(), cashAccountSummary6.getCurrencyCode()));
                it6 = it7;
                str5 = str5;
                str6 = str6;
            }
            str2 = str5;
            str3 = str6;
            arrayListListOf = arrayList6;
        }
        MainActivity mainActivity2 = mainActivity;
        spinner2.setAdapter(new ArrayAdapter(mainActivity2, android.R.layout.simple_spinner_dropdown_item, arrayListListOf));
        Collection collection = (Collection) SortedWith;
        spinner2.setEnabled(!collection.isEmpty());
        if (collection.isEmpty()) {
            if (iCoerceAtLeast != 0) {
                if (iCoerceAtLeast != 1) {
                    if (iCoerceAtLeast != 2) {
                        if (iCoerceAtLeast == 3) {
                        }
                    }
                }
            }
        }
        textView.setText(charSequence);
        textView.setVisibility(SortedWith.isEmpty() ? 0 : 8);
        if (id != null) {
            Iterator it8 = SortedWith.iterator();
            int i2 = 0;
            while (true) {
                if (!it8.hasNext()) {
                    i2 = -1;
                    break;
                } else if (Intrinsics.areEqual(((CashAccountSummary) it8.next()).getId(), id)) {
                    break;
                } else {
                    i2++;
                }
            }
            if (i2 >= 0) {
                spinner2.setSelection(i2);
            }
        }
        CashAccountSummary cashAccountSummary7 = (CashAccountSummary) CollectionsKt.getOrNull(objectRef2.element, spinner3.getSelectedItemPosition());
        String id5 = cashAccountSummary7 != null ? cashAccountSummary7.getId() : null;
        String strRechargeAccountId = new ServiceTransferRepository(mainActivity2).rechargeAccountId();
        SupplierRolloverSummary supplierRolloverSummaryCurrentSupplierRollover3 = dailyBalanceRepository.currentSupplierRollover();
        String accountId = supplierRolloverSummaryCurrentSupplierRollover3 != null ? supplierRolloverSummaryCurrentSupplierRollover3.getAccountId() : null;
        CashAccountSummary cashAccountSummary8 = (CashAccountSummary) CollectionsKt.getOrNull(objectRef.element, spinner2.getSelectedItemPosition());
        String id6 = cashAccountSummary8 != null ? cashAccountSummary8.getId() : null;
        if (iCoerceAtLeast == 3) {
            String str7 = str2;
            String str8 = str3;
            ArrayList arrayList7 = new ArrayList();
            for (Object obj3 : list) {
                CashAccountSummary cashAccountSummary9 = (CashAccountSummary) obj3;
                if (Intrinsics.areEqual(cashAccountSummary9.getAccountType(), str7) && Intrinsics.areEqual(cashAccountSummary9.getCurrencyCode(), str8) && !Intrinsics.areEqual(cashAccountSummary9.getId(), strRechargeAccountId) && !Intrinsics.areEqual(cashAccountSummary9.getId(), accountId) && !Intrinsics.areEqual(cashAccountSummary9.getId(), str)) {
                    arrayList7.add(obj3);
                }
            }
            EmptyList = arrayList7;
        } else if (iCoerceAtLeast != 4) {
            EmptyList = CollectionsKt.emptyList();
        } else {
            ArrayList arrayList8 = new ArrayList();
            for (Object obj4 : list) {
                CashAccountSummary cashAccountSummary10 = (CashAccountSummary) obj4;
                String str9 = str2;
                if (Intrinsics.areEqual(cashAccountSummary10.getAccountType(), str9)) {
                    str4 = str3;
                    if (Intrinsics.areEqual(cashAccountSummary10.getCurrencyCode(), str4) && !Intrinsics.areEqual(cashAccountSummary10.getId(), id6)) {
                        arrayList8.add(obj4);
                    }
                } else {
                    str4 = str3;
                }
                str2 = str9;
                str3 = str4;
            }
            EmptyList = arrayList8;
        }
        objectRef2.element = EmptyList;
        if (EmptyList.isEmpty()) {
            arrayListListOf2 = CollectionsKt.listOf("\u0644\u0627 \u062a\u0648\u062c\u062f \u0648\u062c\u0647\u0629 \u0646\u0642\u062f\u064a\u0629 \u0645\u062a\u0627\u062d\u0629");
        } else {
            Iterable<CashAccountSummary> iterable2 = (Iterable) EmptyList;
            ArrayList arrayList9 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
            for (CashAccountSummary cashAccountSummary11 : iterable2) {
                arrayList9.add(cashAccountSummary11.getName() + " \u2022 " + mainActivity.formatAccountBalance(cashAccountSummary11.getBalanceMinor(), cashAccountSummary11.getCurrencyCode()));
            }
            arrayListListOf2 = arrayList9;
        }
        spinner3.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity2, android.R.layout.simple_spinner_dropdown_item, arrayListListOf2));
        spinner3.setEnabled(!((Collection) EmptyList).isEmpty());
        textView2.setText(iCoerceAtLeast == 4 ? "\u0627\u0644\u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0646\u0642\u062f\u064a \u0627\u0644\u0648\u062c\u0647\u0629" : "\u0648\u062c\u0647\u0629 \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0646\u0642\u062f\u064a\u0629");
        textView2.setVisibility((iCoerceAtLeast == 3 || iCoerceAtLeast == 4) ? 0 : 8);
        spinner3.setVisibility((iCoerceAtLeast == 3 || iCoerceAtLeast == 4) ? 0 : 8);
        textView3.setVisibility(((iCoerceAtLeast == 3 || iCoerceAtLeast == 4) && EmptyList.isEmpty()) ? 0 : 8);
        textView3.setText(iCoerceAtLeast == 4 ? "\u0627\u062e\u062a\u0631 \u0635\u0646\u062f\u0648\u0642\u0627\u064b \u0646\u0642\u062f\u064a\u0627\u064b \u0648\u062c\u0647\u0629\u061b \u064a\u0645\u0643\u0646 \u062a\u0645\u0648\u064a\u0644 \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0623\u0648 \u0635\u0646\u062f\u0648\u0642 \u0634\u0631\u0627\u0621 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0627\u062a \u0645\u0646 \u0623\u064a \u0635\u0646\u062f\u0648\u0642 \u0645\u0635\u062f\u0631 \u0645\u062a\u0627\u062d." : "\u0627\u062e\u062a\u0631 \u0635\u0646\u062f\u0648\u0642\u0627\u064b \u0646\u0642\u062f\u064a\u0627\u064b \u0646\u0634\u0637\u0627\u064b \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629\u060c \u0645\u0639 \u0627\u0633\u062a\u0628\u0639\u0627\u062f \u0635\u0646\u062f\u0648\u0642 \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646 \u0648\u0635\u0646\u062f\u0648\u0642 \u0634\u0631\u0627\u0621 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0627\u062a.");
        if (id5 != null) {
            Iterator it9 = EmptyList.iterator();
            int i3 = 0;
            while (true) {
                if (!it9.hasNext()) {
                    i = -1;
                    break;
                } else {
                    if (Intrinsics.areEqual(((CashAccountSummary) it9.next()).getId(), id5)) {
                        i = i3;
                        break;
                    }
                    i3++;
                }
            }
            if (i >= 0) {
                spinner3.setSelection(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit showDailyOperationsDialog$lambda$385(Ref.ObjectRef objectRef, MainActivity mainActivity) {
        AlertDialog alertDialog = (AlertDialog) objectRef.element;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        mainActivity.showCreateCashAccountDialog();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDailyOperationsDialog$lambda$392(final Spinner spinner, final AlertDialog alertDialog, final Ref.ObjectRef objectRef, final Spinner spinner2, final List list, final MainActivity mainActivity, final TextView textView, final Ref.ObjectRef objectRef2, final Spinner spinner3, final DailyBalanceRepository dailyBalanceRepository, final TextView textView2, final TextView textView3, final CashAccountRepository cashAccountRepository, final String str, final EditText editText, final EditText editText2, final String str2, DialogInterface dialogInterface) {
        spinner.post(new Runnable() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda116
            @Override // java.lang.Runnable
            public final void run() throws IOException {
                MainActivity.showDailyOperationsDialog$refreshAccounts(spinner, objectRef, spinner2, list, mainActivity, textView, objectRef2, spinner3, dailyBalanceRepository, textView2, textView3, cashAccountRepository, str);
            }
        });
        alertDialog.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda117
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Throwable {
                MainActivity.showDailyOperationsDialog$lambda$392$lambda$391(objectRef, spinner2, textView, editText, editText2, spinner, mainActivity, dailyBalanceRepository, str2, objectRef2, spinner3, alertDialog, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDailyOperationsDialog$lambda$392$lambda$391(Ref.ObjectRef objectRef, Spinner spinner, TextView textView, EditText editText, EditText editText2, Spinner spinner2, MainActivity mainActivity, DailyBalanceRepository dailyBalanceRepository, String str, Ref.ObjectRef objectRef2, Spinner spinner3, AlertDialog alertDialog, View view) throws Throwable {
        String str2;
        try {
            CashAccountSummary cashAccountSummary = (CashAccountSummary) CollectionsKt.getOrNull((List) objectRef.element, spinner.getSelectedItemPosition());
            if (cashAccountSummary == null) {
                String string = textView.getText().toString();
                if (StringsKt.isBlank(string)) {
                    string = "\u0644\u0627 \u064a\u0648\u062c\u062f \u062d\u0633\u0627\u0628 \u0645\u0627\u0644\u064a \u0645\u0646\u0627\u0633\u0628 \u0644\u0644\u0639\u0645\u0644\u064a\u0629 \u0627\u0644\u0645\u062e\u062a\u0627\u0631\u0629\u061b \u0623\u0646\u0634\u0626 \u0627\u0644\u062d\u0633\u0627\u0628 \u0623\u0648\u0644\u0627\u064b.";
                }
                throw new IllegalStateException(string.toString());
            }
            String string2 = StringsKt.trim((CharSequence) editText.getText().toString()).toString();
            String string3 = StringsKt.trim((CharSequence) editText2.getText().toString()).toString();
            if (string3.length() == 0) {
                string3 = null;
            }
            String str3 = string3;
            int selectedItemPosition = spinner2.getSelectedItemPosition();
            long usdCents = Intrinsics.areEqual(cashAccountSummary.getCurrencyCode(), "USD") ? mainActivity.parseUsdCents(string2) : mainActivity.parseSypAmount(string2);
            String str4 = mainActivity.currentUserId;
            if (str4 == null) {
                throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u062f\u064a\u0631 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
            }
            if (selectedItemPosition == 0) {
                SupplierRolloverSummary supplierRollover = dailyBalanceRepository.setSupplierRollover(str4, cashAccountSummary.getId(), usdCents, (240 & 8) != 0 ? null : str3, (240 & 16) != 0 ? UUID.randomUUID().toString() : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? false : false);
                MainActivity mainActivity2 = mainActivity;
                if (supplierRollover.getPending()) {
                    str2 = "\u062a\u0645 \u062d\u0641\u0638 \u0637\u0644\u0628 \u062a\u062e\u0635\u064a\u0635 \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0645\u062d\u0644\u064a\u0627\u064b\u060c \u0648\u064a\u0646\u062a\u0638\u0631 \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a.";
                } else {
                    str2 = "\u062a\u0645 \u062a\u062d\u062f\u064a\u062f \u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631. \u0627\u0644\u0645\u062a\u0627\u062d: " + mainActivity.formatSypAmount(supplierRollover.getAvailableMinor()) + " \u0644.\u0633";
                }
                Toast.makeText(mainActivity2, str2, 1).show();
            } else if (selectedItemPosition == 1) {
                dailyBalanceRepository.addPersonalWithdrawal(str4, cashAccountSummary.getId(), usdCents, str, str3);
            } else if (selectedItemPosition == 2) {
                dailyBalanceRepository.setWalletOpening(str4, cashAccountSummary.getId(), usdCents, str3);
            } else if (selectedItemPosition == 3) {
                CashAccountSummary cashAccountSummary2 = (CashAccountSummary) CollectionsKt.getOrNull((List) objectRef2.element, spinner3.getSelectedItemPosition());
                if (cashAccountSummary2 == null) {
                    throw new IllegalStateException("\u0627\u062e\u062a\u0631 \u0635\u0646\u062f\u0648\u0642\u0627\u064b \u0646\u0642\u062f\u064a\u0627\u064b \u0643\u0648\u062c\u0647\u0629 \u0644\u0644\u062a\u062d\u0648\u064a\u0644".toString());
                }
                if (Intrinsics.areEqual(cashAccountSummary2.getId(), cashAccountSummary.getId())) {
                    throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u0625\u0644\u0649 \u0627\u0644\u062d\u0633\u0627\u0628 \u0646\u0641\u0633\u0647".toString());
                }
                dailyBalanceRepository.transferWalletToDrawer(str4, cashAccountSummary.getId(), cashAccountSummary2.getId(), usdCents, str, str3, str3);
            } else if (selectedItemPosition == 4) {
                CashAccountSummary cashAccountSummary3 = (CashAccountSummary) CollectionsKt.getOrNull((List) objectRef2.element, spinner3.getSelectedItemPosition());
                if (cashAccountSummary3 == null) {
                    throw new IllegalStateException("\u0627\u062e\u062a\u0631 \u0635\u0646\u062f\u0648\u0642\u0627\u064b \u0646\u0642\u062f\u064a\u0627\u064b \u0643\u0648\u062c\u0647\u0629 \u0644\u0644\u062a\u062d\u0648\u064a\u0644".toString());
                }
                if (Intrinsics.areEqual(cashAccountSummary3.getId(), cashAccountSummary.getId())) {
                    throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u0625\u0644\u0649 \u0627\u0644\u062d\u0633\u0627\u0628 \u0646\u0641\u0633\u0647".toString());
                }
                dailyBalanceRepository.transferCashBetweenBoxes(str4, cashAccountSummary.getId(), cashAccountSummary3.getId(), usdCents, str, str3, str3);
            }
            alertDialog.dismiss();
            Toast.makeText(mainActivity, "\u062a\u0645 \u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u0639\u0645\u0644\u064a\u0629 \u0641\u064a \u0627\u0644\u0633\u062c\u0644 \u0627\u0644\u0645\u0627\u0644\u064a", 1).show();
            mainActivity.showCashAccounts();
        } catch (Exception e) {
            MainActivity mainActivity3 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u062a\u0646\u0641\u064a\u0630 \u0627\u0644\u0639\u0645\u0644\u064a\u0629";
            }
            Toast.makeText(mainActivity3, message, 1).show();
        }
    }

    private final void showDailyCashReport() throws IOException {
        String str;
        markScreen$default(this, "DAILY_REPORT", null, 2, null);
        MainActivity mainActivity = this;
        DailyCashReport dailyCashReportDailyReport$default = DailyBalanceRepository.dailyReport$default(new DailyBalanceRepository(mainActivity), null, 1, null);
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(this.backgroundColor);
        LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        linearLayout2.setPadding(dp(14), dp(10), dp(14), dp(10));
        linearLayout2.setBackgroundColor(-1);
        Button button = new Button(mainActivity);
        button.setText("\u2039");
        button.setTextSize(25.0f);
        button.setAllCaps(false);
        button.setTextColor(this.green);
        button.setBackgroundColor(0);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda78
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IOException {
                this.f$0.showCashAccounts();
            }
        });
        linearLayout2.addView(button, new LinearLayout.LayoutParams(dp(48), dp(48)));
        linearLayout2.addView(label("\u0627\u0644\u0643\u0634\u0641 \u0627\u0644\u0645\u0627\u0644\u064a \u0627\u0644\u064a\u0648\u0645\u064a", 19.0f, this.green, true, 17), new LinearLayout.LayoutParams(0, -2, 1.0f));
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setFillViewport(true);
        LinearLayout linearLayout3 = new LinearLayout(mainActivity);
        linearLayout3.setOrientation(1);
        linearLayout3.setPadding(dp(16), dp(14), dp(16), dp(24));
        linearLayout3.addView(label("\u0627\u0644\u062a\u0627\u0631\u064a\u062e: " + dailyCashReportDailyReport$default.getOperatingDate(), 13.0f, this.mutedColor, false, 5));
        DailyInventoryCapital dailyInventoryCapitalDailyCapital$default = InventoryValuationRepository.dailyCapital$default(new InventoryValuationRepository(mainActivity), null, 1, null);
        long currentExchangeRateMilli = new StoreRepository(mainActivity).getCurrentExchangeRateMilli();
        linearLayout3.addView(sectionHeading("\u0631\u0623\u0633 \u0627\u0644\u0645\u0627\u0644 \u0627\u0644\u0643\u0644\u064a \u0644\u0644\u0645\u062e\u0632\u0648\u0646"));
        TextView textViewLabel = label("\u0627\u0644\u0642\u064a\u0645\u0629 \u0627\u0644\u062f\u0641\u062a\u0631\u064a\u0629 \u0627\u0644\u062d\u0627\u0644\u064a\u0629 \u0648\u0641\u0642 FIFO: " + formatDualMoney(dailyInventoryCapitalDailyCapital$default.getCurrent().getTotalCostUsdCents(), currentExchangeRateMilli), 17.0f, this.green, true, 5);
        textViewLabel.setPadding(0, dp(5), 0, dp(3));
        linearLayout3.addView(textViewLabel);
        String str2 = dailyInventoryCapitalDailyCapital$default.getChangeUsdCents() >= 0 ? "+" : "\u2212";
        long jAbs = dailyInventoryCapitalDailyCapital$default.getChangeUsdCents() == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(dailyInventoryCapitalDailyCapital$default.getChangeUsdCents());
        TextView textViewLabel2 = label(((dailyInventoryCapitalDailyCapital$default.getCurrent().isComplete() && dailyInventoryCapitalDailyCapital$default.getPreviousClose().isComplete()) ? "\u0627\u0644\u062a\u063a\u064a\u0631 \u0639\u0646 \u0625\u063a\u0644\u0627\u0642 \u0627\u0644\u064a\u0648\u0645 \u0627\u0644\u0633\u0627\u0628\u0642: " : "\u0627\u0644\u062a\u063a\u064a\u0631 \u0641\u064a \u0627\u0644\u062c\u0632\u0621 \u0627\u0644\u0645\u0642\u064a\u0645 \u0641\u0642\u0637 (\u0645\u0642\u0627\u0631\u0646\u0629 \u0628\u0625\u063a\u0644\u0627\u0642 \u0623\u0645\u0633): ") + str2 + formatDualMoney(jAbs, currentExchangeRateMilli), 14.0f, dailyInventoryCapitalDailyCapital$default.getChangeUsdCents() >= 0 ? this.green : this.textColor, true, 5);
        textViewLabel2.setPadding(0, dp(2), 0, dp(4));
        linearLayout3.addView(textViewLabel2);
        if (!dailyInventoryCapitalDailyCapital$default.getCurrent().isComplete() || !dailyInventoryCapitalDailyCapital$default.getPreviousClose().isComplete()) {
            TextView textViewLabel3 = label("\u062a\u0646\u0628\u064a\u0647 \u0645\u062d\u0627\u0633\u0628\u064a: \u062a\u0648\u062c\u062f \u0643\u0645\u064a\u0627\u062a \u063a\u064a\u0631 \u0645\u0642\u064a\u0645\u0629 \u0623\u0648 \u0641\u0631\u0648\u0642\u0627\u062a \u0641\u064a \u0633\u062c\u0644 \u0627\u0644\u0645\u062e\u0632\u0648\u0646. \u0627\u0644\u0623\u0635\u0646\u0627\u0641 \u0627\u0644\u0645\u062a\u0623\u062b\u0631\u0629 \u062d\u0627\u0644\u064a\u0627\u064b: " + dailyInventoryCapitalDailyCapital$default.getCurrent().getUnvaluedProductCount() + "\u060c \u0648\u0639\u0646\u062f \u0625\u063a\u0644\u0627\u0642 \u0623\u0645\u0633: " + dailyInventoryCapitalDailyCapital$default.getPreviousClose().getUnvaluedProductCount() + ". \u0642\u064a\u0645\u0629 \u0631\u0623\u0633 \u0627\u0644\u0645\u0627\u0644 \u0627\u0644\u0645\u0639\u0631\u0648\u0636\u0629 \u0647\u064a \u0627\u0644\u062c\u0632\u0621 \u0627\u0644\u0645\u0624\u0643\u062f \u0641\u0642\u0637.", 12.0f, Color.rgb(170, 80, 45), true, 5);
            textViewLabel3.setPadding(0, dp(2), 0, dp(9));
            linearLayout3.addView(textViewLabel3);
        } else {
            TextView textViewLabel4 = label("\u062a\u0645\u062a \u0645\u0637\u0627\u0628\u0642\u0629 \u0631\u0635\u064a\u062f \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u0645\u0639 \u0633\u062c\u0644 \u0627\u0644\u062d\u0631\u0643\u0627\u062a\u060c \u0648\u0627\u0644\u062a\u0642\u064a\u064a\u0645 \u0645\u0628\u0646\u064a \u0639\u0644\u0649 \u0637\u0628\u0642\u0627\u062a \u0627\u0644\u062a\u0643\u0644\u0641\u0629 FIFO \u0648\u0644\u064a\u0633 \u0639\u0644\u0649 \u0633\u0639\u0631 \u0627\u0644\u0628\u064a\u0639 \u0623\u0648 \u0633\u0639\u0631 \u0627\u0644\u0635\u0646\u0641 \u0627\u0644\u062d\u0627\u0644\u064a.", 11.0f, this.mutedColor, false, 5);
            textViewLabel4.setPadding(0, dp(2), 0, dp(9));
            linearLayout3.addView(textViewLabel4);
        }
        List<ServiceDailySummary> listDailySummary = new ServiceTransferRepository(mainActivity).dailySummary(dailyCashReportDailyReport$default.getOperatingDate());
        String str3 = " \u0644.\u0633";
        if (listDailySummary.isEmpty()) {
            str = " \u0644.\u0633";
        } else {
            linearLayout3.addView(sectionHeading("\u062a\u062d\u0648\u064a\u0644\u0627\u062a \u0627\u0644\u0631\u0635\u064a\u062f \u2013 \u062a\u0641\u0635\u064a\u0644 \u0627\u0644\u064a\u0648\u0645"));
            for (ServiceDailySummary serviceDailySummary : listDailySummary) {
                String str4 = str3;
                TextView textViewLabel5 = label(serviceDailySummary.getServiceName() + ": \u0627\u0644\u0645\u0642\u0628\u0648\u0636 " + formatSypAmount(serviceDailySummary.getCustomerReceiptsMinor()) + " \u0644.\u0633 \u2022 \u0627\u0644\u0631\u0628\u062d " + formatSypAmount(serviceDailySummary.getProfitMinor()) + " \u0644.\u0633 \u2022 \u0631\u0633\u0648\u0645 \u0627\u0644\u0634\u0631\u0643\u0629 " + formatSypAmount(serviceDailySummary.getProviderFeeMinor()) + str3, 12.0f, this.textColor, false, 5);
                textViewLabel5.setPadding(0, dp(3), 0, dp(3));
                linearLayout3.addView(textViewLabel5);
                TextView textViewLabel6 = label("\u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0645\u0633\u062a\u0647\u0644\u0643 " + formatSypAmount(serviceDailySummary.getDeliveredMinor()) + " \u0644.\u0633 \u2022 \u0642\u064a\u0645\u0629 \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0645\u0637\u0644\u0648\u0628 \u062a\u0639\u0648\u064a\u0636\u0647 " + formatSypAmount(serviceDailySummary.getRechargeReserveMinor()) + str4, 12.0f, this.green, true, 5);
                textViewLabel6.setPadding(0, dp(2), 0, dp(5));
                linearLayout3.addView(textViewLabel6);
                str3 = str4;
            }
            str = str3;
            TextView textViewLabel7 = label("\u0642\u064a\u0645\u0629 \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0645\u0633\u062a\u0647\u0644\u0643 \u0647\u064a \u0627\u0644\u0645\u0628\u0644\u063a \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0627\u0644\u0644\u0627\u0632\u0645 \u0644\u062a\u0639\u0648\u064a\u0636 \u0627\u0644\u0631\u0635\u064a\u062f. \u0639\u0645\u0648\u0644\u0629 2% \u062a\u064f\u062d\u0633\u0628 \u0641\u0642\u0637 \u0639\u0646\u062f \u062a\u0639\u0628\u0626\u0629 \u0631\u0635\u064a\u062f \u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0623\u0648 \u0634\u0627\u0645 \u0643\u0627\u0634 \u0645\u0646 \u062a\u0627\u062c\u0631 \u062e\u0627\u0631\u062c\u064a\u060c \u0648\u062a\u0638\u0647\u0631 \u062d\u064a\u0646\u0647\u0627 \u0636\u0645\u0646 \u0631\u0633\u0648\u0645 \u0627\u0644\u062a\u0639\u0628\u0626\u0629 \u0627\u0644\u0645\u062f\u0641\u0648\u0639\u0629.", 11.0f, this.mutedColor, false, 5);
            textViewLabel7.setPadding(0, dp(2), 0, dp(8));
            linearLayout3.addView(textViewLabel7);
        }
        boolean zIsEmpty = dailyCashReportDailyReport$default.getElectronicReceiptsByCurrency().isEmpty();
        String str5 = " \u2022 ";
        String strJoinToString$default = zIsEmpty ? "\u0644\u0627 \u062a\u0648\u062c\u062f \u0645\u0642\u0628\u0648\u0636\u0627\u062a \u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a\u0629" : CollectionsKt.joinToString$default(dailyCashReportDailyReport$default.getElectronicReceiptsByCurrency().entrySet(), " \u2022 ", null, null, 0, null, new Function1() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda85
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.showDailyCashReport$lambda$407(this.f$0, (Map.Entry) obj);
            }
        }, 30, null);
        String strJoinToString$default2 = dailyCashReportDailyReport$default.getLiquidatableElectronicReceiptsByCurrency().isEmpty() ? "\u0644\u0627 \u064a\u0648\u062c\u062f \u0645\u0628\u0644\u063a \u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a \u0645\u062a\u0628\u0642\u064d" : CollectionsKt.joinToString$default(dailyCashReportDailyReport$default.getLiquidatableElectronicReceiptsByCurrency().entrySet(), " \u2022 ", null, null, 0, null, new Function1() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda86
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.showDailyCashReport$lambda$408(this.f$0, (Map.Entry) obj);
            }
        }, 30, null);
        String strJoinToString$default3 = dailyCashReportDailyReport$default.getPhysicalCashReceiptsByCurrency().isEmpty() ? "\u0644\u0627 \u062a\u0648\u062c\u062f \u0645\u0642\u0628\u0648\u0636\u0627\u062a \u0646\u0642\u062f\u064a\u0629" : CollectionsKt.joinToString$default(dailyCashReportDailyReport$default.getPhysicalCashReceiptsByCurrency().entrySet(), " \u2022 ", null, null, 0, null, new Function1() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda87
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.showDailyCashReport$lambda$409(this.f$0, (Map.Entry) obj);
            }
        }, 30, null);
        TextView textViewLabel8 = label("\u0625\u062c\u0645\u0627\u0644\u064a \u0627\u0644\u0645\u0642\u0628\u0648\u0636\u0627\u062a \u0627\u0644\u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a\u0629 \u062e\u0644\u0627\u0644 \u0627\u0644\u064a\u0648\u0645: " + strJoinToString$default, 16.0f, this.green, true, 5);
        textViewLabel8.setPadding(0, dp(8), 0, dp(3));
        linearLayout3.addView(textViewLabel8);
        TextView textViewLabel9 = label("\u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0645\u0646 \u0645\u0642\u0628\u0648\u0636\u0627\u062a \u0627\u0644\u064a\u0648\u0645 \u0628\u0639\u062f \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u0625\u0644\u0649 \u0627\u0644\u0643\u0627\u0634: " + strJoinToString$default2, 16.0f, this.green, true, 5);
        textViewLabel9.setPadding(0, dp(2), 0, dp(4));
        linearLayout3.addView(textViewLabel9);
        TextView textViewLabel10 = label("\u0625\u062c\u0645\u0627\u0644\u064a \u0627\u0644\u0645\u0642\u0628\u0648\u0636\u0627\u062a \u0627\u0644\u0646\u0642\u062f\u064a\u0629 \u0627\u0644\u0641\u0639\u0644\u064a\u0629: " + strJoinToString$default3, 15.0f, this.textColor, true, 5);
        int i = 12;
        textViewLabel10.setPadding(0, dp(2), 0, dp(12));
        linearLayout3.addView(textViewLabel10);
        SupplierRolloverSummary supplierRollover = dailyCashReportDailyReport$default.getSupplierRollover();
        if (supplierRollover != null) {
            TextView textViewLabel11 = label("\u0627\u0644\u0646\u0642\u062f \u0627\u0644\u0645\u062f\u0648\u0631 \u0644\u0644\u0645\u0648\u0631\u062f\u064a\u0646: " + formatSypAmount(supplierRollover.getAllocatedMinor()) + " \u0644.\u0633 \u2022 \u062f\u0641\u0639\u0627\u062a \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646: " + formatSypAmount(supplierRollover.getSupplierPaymentsMinor()) + " \u2022 \u0627\u0644\u0645\u0633\u062d\u0648\u0628\u0627\u062a: " + formatSypAmount(supplierRollover.getPersonalWithdrawalsMinor()) + " \u2022 \u0627\u0644\u0645\u062a\u0627\u062d: " + formatSypAmount(supplierRollover.getAvailableMinor()) + str, 14.0f, this.green, true, 5);
            textViewLabel11.setPadding(0, dp(4), 0, dp(12));
            linearLayout3.addView(textViewLabel11);
            Unit unit = Unit.INSTANCE;
            Unit unit2 = Unit.INSTANCE;
        }
        Iterator it = dailyCashReportDailyReport$default.getAccounts().iterator();
        while (it.hasNext()) {
            DailyAccountSummary dailyAccountSummary = (DailyAccountSummary) it.next();
            LinearLayout linearLayout4 = new LinearLayout(mainActivity);
            linearLayout4.setOrientation(1);
            linearLayout4.setPadding(dp(i), dp(10), dp(i), dp(10));
            linearLayout4.setBackground(cardBackground());
            linearLayout4.addView(label(dailyAccountSummary.getAccountName() + str5 + dailyAccountSummary.getCurrencyCode(), 15.0f, this.green, true, 5));
            String accountBalance = formatAccountBalance(dailyAccountSummary.getOpeningMinor(), dailyAccountSummary.getCurrencyCode());
            StringBuilder sb = new StringBuilder("\u0631\u0635\u064a\u062f \u0623\u0648\u0644 \u0627\u0644\u064a\u0648\u0645: ");
            sb.append(accountBalance);
            linearLayout4.addView(label(sb.toString(), 12.0f, this.textColor, false, 5));
            String str6 = Intrinsics.areEqual(dailyAccountSummary.getAccountType(), "WALLET") ? "\u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a" : "\u0646\u0642\u062f\u064a";
            Iterator it2 = it;
            linearLayout4.addView(label("\u0645\u0628\u064a\u0639\u0627\u062a \u0627\u0644\u064a\u0648\u0645 \u2013 \u0642\u0628\u0636 " + str6 + ": " + formatAccountBalance(dailyAccountSummary.getSalesReceiptsMinor(), dailyAccountSummary.getCurrencyCode()), 12.0f, this.textColor, false, 5));
            String str7 = str5;
            linearLayout4.addView(label("\u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0648\u0646 \u2013 \u0642\u0628\u0636 " + str6 + ": " + formatAccountBalance(dailyAccountSummary.getDebtCollectionsMinor(), dailyAccountSummary.getCurrencyCode()), 12.0f, this.textColor, false, 5));
            linearLayout4.addView(label("\u0645\u0642\u0628\u0648\u0636 \u062a\u062d\u0648\u064a\u0644 \u0631\u0635\u064a\u062f \u2013 \u0642\u0628\u0636 " + str6 + ": " + formatAccountBalance(dailyAccountSummary.getServiceTransferReceiptsMinor(), dailyAccountSummary.getCurrencyCode()), 12.0f, this.textColor, false, 5));
            String accountBalance2 = formatAccountBalance(dailyAccountSummary.getSupplierReturnRefundsMinor(), dailyAccountSummary.getCurrencyCode());
            StringBuilder sb2 = new StringBuilder("\u0645\u0628\u0627\u0644\u063a \u0645\u0633\u062a\u0631\u062f\u0629 \u0645\u0646 \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646: ");
            sb2.append(accountBalance2);
            linearLayout4.addView(label(sb2.toString(), 12.0f, this.textColor, false, 5));
            String str8 = Intrinsics.areEqual(dailyAccountSummary.getAccountType(), "WALLET") ? "\u0625\u062c\u0645\u0627\u0644\u064a \u0645\u0642\u0628\u0648\u0636\u0627\u062a \u0627\u0644\u064a\u0648\u0645: " : "\u0625\u062c\u0645\u0627\u0644\u064a \u0627\u0644\u0645\u0642\u0628\u0648\u0636\u0627\u062a \u0627\u0644\u0646\u0642\u062f\u064a\u0629 \u0641\u064a \u0627\u0644\u062d\u0633\u0627\u0628: ";
            linearLayout4.addView(label(str8 + formatAccountBalance(dailyAccountSummary.getElectronicReceiptsMinor(), dailyAccountSummary.getCurrencyCode()), 13.0f, this.green, true, 5));
            if (Intrinsics.areEqual(dailyAccountSummary.getAccountType(), "WALLET")) {
                linearLayout4.addView(label("\u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0645\u0646 \u0645\u0642\u0628\u0648\u0636\u0627\u062a \u0627\u0644\u064a\u0648\u0645 \u0628\u0639\u062f \u0627\u0644\u062a\u062d\u0648\u064a\u0644: " + formatAccountBalance(dailyAccountSummary.getLiquidatableElectronicReceiptsMinor(), dailyAccountSummary.getCurrencyCode()), 13.0f, this.green, true, 5));
            }
            linearLayout4.addView(label("\u0625\u0636\u0627\u0641\u0627\u062a \u0623\u062e\u0631\u0649: " + formatAccountBalance(dailyAccountSummary.getOtherInflowsMinor(), dailyAccountSummary.getCurrencyCode()), 12.0f, this.textColor, false, 5));
            linearLayout4.addView(label("\u062f\u0641\u0639\u0627\u062a \u0627\u0644\u0645\u0648\u0631\u062f\u064a\u0646: " + formatAccountBalance(dailyAccountSummary.getSupplierPaymentsMinor(), dailyAccountSummary.getCurrencyCode()), 12.0f, this.textColor, false, 5));
            linearLayout4.addView(label("\u0627\u0644\u0645\u0635\u0627\u0631\u064a\u0641 \u0627\u0644\u062a\u0634\u063a\u064a\u0644\u064a\u0629: " + formatAccountBalance(dailyAccountSummary.getOperatingExpensesMinor(), dailyAccountSummary.getCurrencyCode()), 12.0f, this.textColor, false, 5));
            linearLayout4.addView(label("\u0645\u0628\u0627\u0644\u063a \u0645\u0631\u062f\u0648\u062f\u0629 \u0644\u0644\u0639\u0645\u0644\u0627\u0621: " + formatAccountBalance(dailyAccountSummary.getSalesReturnRefundsMinor(), dailyAccountSummary.getCurrencyCode()), 12.0f, this.textColor, false, 5));
            linearLayout4.addView(label("\u0627\u0644\u0645\u0633\u062d\u0648\u0628\u0627\u062a \u0627\u0644\u0634\u062e\u0635\u064a\u0629: " + formatAccountBalance(dailyAccountSummary.getPersonalWithdrawalsMinor(), dailyAccountSummary.getCurrencyCode()), 12.0f, this.textColor, false, 5));
            linearLayout4.addView(label("\u062a\u062d\u0648\u064a\u0644\u0627\u062a \u0648\u0627\u0631\u062f\u0629: " + formatAccountBalance(dailyAccountSummary.getTransferInMinor(), dailyAccountSummary.getCurrencyCode()) + " \u2022 \u0635\u0627\u062f\u0631\u0629: " + formatAccountBalance(dailyAccountSummary.getTransferOutMinor(), dailyAccountSummary.getCurrencyCode()), 12.0f, this.textColor, false, 5));
            String accountBalance3 = formatAccountBalance(dailyAccountSummary.getClosingMinor(), dailyAccountSummary.getCurrencyCode());
            StringBuilder sb3 = new StringBuilder("\u0631\u0635\u064a\u062f \u0622\u062e\u0631 \u0627\u0644\u064a\u0648\u0645: ");
            sb3.append(accountBalance3);
            linearLayout4.addView(label(sb3.toString(), 15.0f, this.green, true, 5));
            linearLayout3.addView(linearLayout4, fieldParams(-2, dp(9)));
            it = it2;
            str5 = str7;
            i = 12;
        }
        linearLayout3.addView(sectionHeading("\u062d\u0631\u0643\u0627\u062a \u0627\u0644\u064a\u0648\u0645"));
        List<String> list = new DailyBalanceRepository(mainActivity).todayTransactions();
        if (list.isEmpty()) {
            linearLayout3.addView(label("\u0644\u0627 \u062a\u0648\u062c\u062f \u062d\u0631\u0643\u0627\u062a \u0645\u0633\u062c\u0644\u0629 \u0627\u0644\u064a\u0648\u0645.", 12.0f, this.mutedColor, false, 17));
        } else {
            Iterator<T> it3 = list.iterator();
            while (it3.hasNext()) {
                TextView textViewLabel12 = label((String) it3.next(), 11.0f, this.textColor, false, 5);
                textViewLabel12.setPadding(0, dp(4), 0, dp(4));
                linearLayout3.addView(textViewLabel12);
            }
        }
        scrollView.addView(linearLayout3);
        linearLayout.addView(linearLayout2, matchWidth());
        linearLayout.addView(scrollView, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        setContentView(linearLayout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence showDailyCashReport$lambda$407(MainActivity mainActivity, Map.Entry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return mainActivity.formatAccountBalance(((Number) it.getValue()).longValue(), (String) it.getKey());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence showDailyCashReport$lambda$408(MainActivity mainActivity, Map.Entry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return mainActivity.formatAccountBalance(((Number) it.getValue()).longValue(), (String) it.getKey());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence showDailyCashReport$lambda$409(MainActivity mainActivity, Map.Entry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return mainActivity.formatAccountBalance(((Number) it.getValue()).longValue(), (String) it.getKey());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showGeneralReports() {
        int i;
        int i2;
        String str;
        int i3;
        if (!isManager()) {
            Toast.makeText(this, "\u0627\u0644\u062a\u0642\u0627\u0631\u064a\u0631 \u0627\u0644\u0645\u0627\u0644\u064a\u0629 \u0645\u062a\u0627\u062d\u0629 \u0644\u0644\u0645\u062f\u064a\u0631 \u0641\u0642\u0637", 0).show();
            return;
        }
        markScreen$default(this, "GENERAL_REPORTS", null, 2, null);
        MainActivity mainActivity = this;
        DailyInventoryCapital dailyInventoryCapitalDailyCapital$default = InventoryValuationRepository.dailyCapital$default(new InventoryValuationRepository(mainActivity), null, 1, null);
        long currentExchangeRateMilli = new StoreRepository(mainActivity).getCurrentExchangeRateMilli();
        List<StoreProduct> listListProducts = new StoreRepository(mainActivity).listProducts();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listListProducts) {
            if (((StoreProduct) obj).getTracksInventory()) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = arrayList2;
        if ((arrayList3 instanceof Collection) && arrayList3.isEmpty()) {
            i = 0;
        } else {
            Iterator it = arrayList3.iterator();
            int i4 = 0;
            while (it.hasNext()) {
                if (((StoreProduct) it.next()).getStock() > 1.0E-7d && (i4 = i4 + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
            i = i4;
        }
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(this.backgroundColor);
        LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        linearLayout2.setPadding(dp(14), dp(10), dp(14), dp(10));
        linearLayout2.setBackgroundColor(-1);
        Button button = new Button(mainActivity);
        button.setText("\u2039");
        button.setTextSize(25.0f);
        button.setAllCaps(false);
        button.setTextColor(this.green);
        button.setBackgroundColor(0);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda52
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IOException {
                MainActivity.showPointOfSale$default(this.f$0, false, 1, null);
            }
        });
        linearLayout2.addView(button, new LinearLayout.LayoutParams(dp(48), dp(48)));
        int i5 = i;
        linearLayout2.addView(label("\u0627\u0644\u0643\u0634\u0648\u0641\u0627\u062a \u0627\u0644\u0639\u0627\u0645\u0629", 19.0f, this.green, true, 17), new LinearLayout.LayoutParams(0, -2, 1.0f));
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setFillViewport(true);
        LinearLayout linearLayout3 = new LinearLayout(mainActivity);
        linearLayout3.setOrientation(1);
        linearLayout3.setPadding(dp(16), dp(14), dp(16), dp(24));
        linearLayout3.addView(label("\u0631\u0623\u0633 \u0627\u0644\u0645\u0627\u0644 \u0627\u0644\u0643\u0644\u064a \u0644\u0644\u0645\u062e\u0632\u0648\u0646", 22.0f, this.green, true, 5));
        TextView textViewLabel = label("\u0627\u0644\u062a\u0642\u064a\u064a\u0645 \u0627\u0644\u0645\u062d\u0627\u0633\u0628\u064a \u0644\u0644\u0645\u062e\u0632\u0648\u0646 \u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0648\u0641\u0642 \u0637\u0631\u064a\u0642\u0629 FIFO\u060c \u0639\u0644\u0649 \u0623\u0633\u0627\u0633 \u062a\u0643\u0644\u0641\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 \u0628\u0627\u0644\u062f\u0648\u0644\u0627\u0631 \u0627\u0644\u0645\u0631\u062c\u0639\u064a \u0627\u0644\u062b\u0627\u0628\u062a\u060c \u0648\u0644\u064a\u0633 \u0633\u0639\u0631 \u0627\u0644\u0628\u064a\u0639.", 12.0f, this.mutedColor, false, 5);
        textViewLabel.setPadding(0, dp(4), 0, dp(12));
        linearLayout3.addView(textViewLabel);
        LinearLayout linearLayout4 = new LinearLayout(mainActivity);
        linearLayout4.setOrientation(1);
        linearLayout4.setPadding(dp(14), dp(14), dp(14), dp(14));
        linearLayout4.setBackground(cardBackground());
        linearLayout4.addView(label("\u0642\u064a\u0645\u0629 \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u0627\u0644\u0645\u0642\u064a\u0645 \u062d\u0627\u0644\u064a\u0627\u064b", 13.0f, this.mutedColor, false, 5));
        TextView textViewLabel2 = label(formatDualMoney(dailyInventoryCapitalDailyCapital$default.getCurrent().getTotalCostUsdCents(), currentExchangeRateMilli), 21.0f, this.green, true, 5);
        textViewLabel2.setPadding(0, dp(5), 0, dp(8));
        linearLayout4.addView(textViewLabel2);
        String str2 = dailyInventoryCapitalDailyCapital$default.getChangeUsdCents() >= 0 ? "+" : "\u2212";
        long jAbs = dailyInventoryCapitalDailyCapital$default.getChangeUsdCents() == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(dailyInventoryCapitalDailyCapital$default.getChangeUsdCents());
        linearLayout4.addView(label(((dailyInventoryCapitalDailyCapital$default.getCurrent().isComplete() && dailyInventoryCapitalDailyCapital$default.getPreviousClose().isComplete()) ? "\u0627\u0644\u062a\u063a\u064a\u0631 \u0639\u0646 \u0625\u063a\u0644\u0627\u0642 \u0627\u0644\u064a\u0648\u0645 \u0627\u0644\u0633\u0627\u0628\u0642: " : "\u0627\u0644\u062a\u063a\u064a\u0631 \u0641\u064a \u0627\u0644\u062c\u0632\u0621 \u0627\u0644\u0645\u0642\u064a\u0645 \u0641\u0642\u0637 (\u0645\u0642\u0627\u0631\u0646\u0629 \u0628\u0625\u063a\u0644\u0627\u0642 \u0623\u0645\u0633): ") + str2 + formatDualMoney(jAbs, currentExchangeRateMilli), 14.0f, dailyInventoryCapitalDailyCapital$default.getChangeUsdCents() >= 0 ? this.green : this.textColor, true, 5));
        TextView textViewLabel3 = label("\u0622\u062e\u0631 \u0627\u062d\u062a\u0633\u0627\u0628: " + dailyInventoryCapitalDailyCapital$default.getCurrent().getAsOfLabel(), 10.0f, this.mutedColor, false, 5);
        textViewLabel3.setPadding(0, dp(5), 0, 0);
        linearLayout4.addView(textViewLabel3);
        linearLayout3.addView(linearLayout4, fieldParams(-2, dp(10)));
        if (!dailyInventoryCapitalDailyCapital$default.getCurrent().isComplete() || !dailyInventoryCapitalDailyCapital$default.getPreviousClose().isComplete()) {
            int unvaluedProductCount = dailyInventoryCapitalDailyCapital$default.getCurrent().getUnvaluedProductCount();
            int unvaluedProductCount2 = dailyInventoryCapitalDailyCapital$default.getPreviousClose().getUnvaluedProductCount();
            i2 = 12;
            TextView textViewLabel4 = label("\u062a\u0646\u0628\u064a\u0647: \u0644\u0645 \u0646\u062d\u062a\u0633\u0628 \u0642\u064a\u0645\u0629 \u0627\u0644\u0623\u0635\u0646\u0627\u0641 \u0627\u0644\u062a\u064a \u062a\u062d\u062a\u0648\u064a \u0639\u0644\u0649 \u0643\u0645\u064a\u0627\u062a \u0628\u0644\u0627 \u062a\u0643\u0644\u0641\u0629 \u0645\u0633\u062c\u0644\u0629 \u0623\u0648 \u0641\u0631\u0648\u0642\u0627\u062a \u0641\u064a \u0633\u062c\u0644 \u0627\u0644\u0645\u062e\u0632\u0648\u0646. \u0627\u0644\u0623\u0635\u0646\u0627\u0641 \u0627\u0644\u0645\u062a\u0623\u062b\u0631\u0629 \u062d\u0627\u0644\u064a\u0627\u064b: " + unvaluedProductCount + "\u060c \u0648\u0639\u0646\u062f \u0625\u063a\u0644\u0627\u0642 \u0623\u0645\u0633: " + unvaluedProductCount2 + ". \u0627\u0644\u0631\u0642\u0645 \u0623\u0639\u0644\u0627\u0647 \u0647\u0648 \u0631\u0623\u0633 \u0627\u0644\u0645\u0627\u0644 \u0627\u0644\u0645\u0624\u0643\u062f \u0641\u0642\u0637.", 12.0f, Color.rgb(170, 80, 45), true, 5);
            textViewLabel4.setPadding(0, dp(2), 0, dp(12));
            linearLayout3.addView(textViewLabel4);
        } else {
            TextView textViewLabel5 = label("\u062d\u0627\u0644\u0629 \u0627\u0644\u062a\u0642\u064a\u064a\u0645: \u0645\u0643\u062a\u0645\u0644\u061b \u0627\u0644\u0643\u0645\u064a\u0627\u062a \u0645\u0637\u0627\u0628\u0642\u0629 \u0644\u0633\u062c\u0644 \u0627\u0644\u062d\u0631\u0643\u0627\u062a\u060c \u0648\u062a\u0645 \u0627\u062d\u062a\u0633\u0627\u0628 \u0627\u0644\u0637\u0628\u0642\u0627\u062a \u0627\u0644\u0645\u062a\u0628\u0642\u064a\u0629 \u0648\u0641\u0642 FIFO.", 12.0f, this.green, true, 5);
            textViewLabel5.setPadding(0, dp(2), 0, dp(12));
            linearLayout3.addView(textViewLabel5);
            i2 = 12;
        }
        linearLayout3.addView(sectionHeading("\u0645\u0644\u062e\u0635 \u0631\u0628\u062d \u0627\u0644\u0645\u0628\u064a\u0639\u0627\u062a \u0648\u0641\u0642 \u062a\u0643\u0644\u0641\u0629 FIFO"));
        SalesProfitRepository salesProfitRepository = new SalesProfitRepository(mainActivity);
        SalesProfitSummary salesProfitSummary = SalesProfitRepository.today$default(salesProfitRepository, null, 1, null);
        LinearLayout linearLayout5 = new LinearLayout(mainActivity);
        linearLayout5.setOrientation(1);
        linearLayout5.setPadding(dp(14), dp(i2), dp(14), dp(i2));
        linearLayout5.setBackground(cardBackground());
        linearLayout5.addView(label("\u0646\u062a\u064a\u062c\u0629 \u0627\u0644\u0645\u0628\u064a\u0639\u0627\u062a \u0627\u0644\u0645\u0633\u062c\u0644\u0629 \u0627\u0644\u064a\u0648\u0645", 15.0f, this.green, true, 5));
        linearLayout5.addView(label("\u0639\u062f\u062f \u0627\u0644\u0641\u0648\u0627\u062a\u064a\u0631: " + salesProfitSummary.getInvoiceCount(), 12.0f, this.mutedColor, false, 5));
        linearLayout5.addView(label("\u0625\u062c\u0645\u0627\u0644\u064a \u0627\u0644\u0645\u0628\u064a\u0639\u0627\u062a: " + formatDualMoney(salesProfitSummary.getRevenueUsdCents(), currentExchangeRateMilli), 13.0f, this.textColor, false, 5));
        linearLayout5.addView(label("\u0645\u0631\u062a\u062c\u0639\u0627\u062a \u0627\u0644\u0645\u0628\u064a\u0639\u0627\u062a: " + formatDualMoney(salesProfitSummary.getReturnUsdCents(), currentExchangeRateMilli), 13.0f, this.textColor, false, 5));
        linearLayout5.addView(label("\u0635\u0627\u0641\u064a \u0627\u0644\u0625\u064a\u0631\u0627\u062f \u0628\u0639\u062f \u0627\u0644\u0645\u0631\u062a\u062c\u0639\u0627\u062a: " + formatDualMoney(salesProfitSummary.getNetRevenueUsdCents(), currentExchangeRateMilli), 13.0f, this.textColor, true, 5));
        linearLayout5.addView(label("\u062a\u0643\u0644\u0641\u0629 FIFO \u0627\u0644\u0645\u0628\u0627\u0639\u0629: " + formatDualMoney(salesProfitSummary.getCogsUsdCents(), currentExchangeRateMilli) + " \u2022 \u062a\u0643\u0644\u0641\u0629 \u0627\u0644\u0645\u0631\u062a\u062c\u0639: " + formatDualMoney(salesProfitSummary.getReturnedCogsUsdCents(), currentExchangeRateMilli), 12.0f, this.mutedColor, false, 5));
        linearLayout5.addView(label("\u0627\u0644\u0645\u0635\u0627\u0631\u064a\u0641 \u0627\u0644\u062a\u0634\u063a\u064a\u0644\u064a\u0629 \u0627\u0644\u064a\u0648\u0645: " + moneyText(salesProfitSummary.getOperatingExpensesUsdCents()) + " USD \u2022 \u0639\u062f\u062f\u0647\u0627: " + salesProfitSummary.getOperatingExpenseCount(), 13.0f, this.textColor, false, 5));
        if (salesProfitSummary.isComplete()) {
            str = " USD";
            TextView textViewLabel6 = label("\u0645\u062c\u0645\u0644 \u0627\u0644\u0631\u0628\u062d \u0628\u0639\u062f \u0627\u0644\u0645\u0631\u062a\u062c\u0639\u0627\u062a \u0648\u0642\u0628\u0644 \u0627\u0644\u0645\u0635\u0627\u0631\u064a\u0641: " + formatDualMoney(salesProfitSummary.getGrossProfitUsdCents(), currentExchangeRateMilli), 14.0f, this.mutedColor, true, 5);
            textViewLabel6.setPadding(0, dp(5), 0, 0);
            linearLayout5.addView(textViewLabel6);
            TextView textViewLabel7 = label("\u0635\u0627\u0641\u064a \u0646\u062a\u064a\u062c\u0629 \u0627\u0644\u062a\u0634\u063a\u064a\u0644 \u0628\u0639\u062f \u0627\u0644\u0645\u0635\u0627\u0631\u064a\u0641: " + moneyText(salesProfitSummary.getNetOperatingProfitUsdCents()) + str, 16.0f, this.green, true, 5);
            textViewLabel7.setPadding(0, dp(3), 0, 0);
            linearLayout5.addView(textViewLabel7);
            i3 = 80;
        } else {
            str = " USD";
            i3 = 80;
            TextView textViewLabel8 = label("\u0627\u0644\u0631\u0628\u062d \u063a\u064a\u0631 \u0645\u0643\u062a\u0645\u0644: \u0641\u0648\u0627\u062a\u064a\u0631 \u0628\u0644\u0627 \u062a\u0643\u0644\u0641\u0629 FIFO: " + salesProfitSummary.getUncostedInvoiceCount() + " \u2022 \u0645\u0631\u062a\u062c\u0639\u0627\u062a \u0628\u0644\u0627 \u062a\u0643\u0644\u0641\u0629 \u0645\u0639\u0631\u0648\u0641\u0629: " + salesProfitSummary.getUncostedReturnCount() + ". \u0631\u0627\u062c\u0639\u0647\u0627 \u0642\u0628\u0644 \u0627\u0644\u0627\u0639\u062a\u0645\u0627\u062f.", 12.0f, Color.rgb(170, 80, 45), true, 5);
            textViewLabel8.setPadding(0, dp(5), 0, 0);
            linearLayout5.addView(textViewLabel8);
        }
        SalesProfitSummary salesProfitSummaryAllTime = salesProfitRepository.allTime();
        TextView textViewLabel9 = label("\u0645\u0646\u0630 \u0628\u062f\u0627\u064a\u0629 \u0627\u0644\u062a\u0634\u063a\u064a\u0644 \u2022 \u0627\u0644\u0645\u0628\u064a\u0639\u0627\u062a: " + formatDualMoney(salesProfitSummaryAllTime.getRevenueUsdCents(), currentExchangeRateMilli) + " \u2022 \u0627\u0644\u0645\u0631\u062a\u062c\u0639\u0627\u062a: " + formatDualMoney(salesProfitSummaryAllTime.getReturnUsdCents(), currentExchangeRateMilli), 11.0f, this.mutedColor, false, 5);
        textViewLabel9.setPadding(0, dp(8), 0, 0);
        linearLayout5.addView(textViewLabel9);
        linearLayout5.addView(label("\u0627\u0644\u0645\u0635\u0627\u0631\u064a\u0641 \u0627\u0644\u062a\u0634\u063a\u064a\u0644\u064a\u0629 \u0627\u0644\u062a\u0631\u0627\u0643\u0645\u064a\u0629: " + moneyText(salesProfitSummaryAllTime.getOperatingExpensesUsdCents()) + " USD \u2022 \u0639\u062f\u062f\u0647\u0627: " + salesProfitSummaryAllTime.getOperatingExpenseCount(), 11.0f, this.mutedColor, false, 5));
        if (salesProfitSummaryAllTime.isComplete()) {
            linearLayout5.addView(label("\u0645\u062c\u0645\u0644 \u0627\u0644\u0631\u0628\u062d \u0627\u0644\u062a\u0631\u0627\u0643\u0645\u064a \u0628\u0639\u062f \u0627\u0644\u0645\u0631\u062a\u062c\u0639\u0627\u062a \u0648\u0642\u0628\u0644 \u0627\u0644\u0645\u0635\u0627\u0631\u064a\u0641: " + formatDualMoney(salesProfitSummaryAllTime.getGrossProfitUsdCents(), currentExchangeRateMilli), 12.0f, this.mutedColor, true, 5));
            linearLayout5.addView(label("\u0635\u0627\u0641\u064a \u0646\u062a\u064a\u062c\u0629 \u0627\u0644\u062a\u0634\u063a\u064a\u0644 \u0627\u0644\u062a\u0631\u0627\u0643\u0645\u064a\u0629 \u0628\u0639\u062f \u0627\u0644\u0645\u0635\u0627\u0631\u064a\u0641: " + moneyText(salesProfitSummaryAllTime.getNetOperatingProfitUsdCents()) + str, 14.0f, this.green, true, 5));
        } else {
            linearLayout5.addView(label("\u0627\u0644\u0631\u0628\u062d \u0627\u0644\u062a\u0631\u0627\u0643\u0645\u064a \u063a\u064a\u0631 \u0645\u0643\u062a\u0645\u0644\u061b \u0641\u0648\u0627\u062a\u064a\u0631 \u0628\u0644\u0627 \u062a\u0643\u0644\u0641\u0629: " + salesProfitSummaryAllTime.getUncostedInvoiceCount() + " \u2022 \u0645\u0631\u062a\u062c\u0639\u0627\u062a \u0628\u0644\u0627 \u062a\u0643\u0644\u0641\u0629: " + salesProfitSummaryAllTime.getUncostedReturnCount(), 11.0f, Color.rgb(170, i3, 45), true, 5));
        }
        linearLayout3.addView(linearLayout5, fieldParams(-2, dp(12)));
        TextView textViewLabel10 = label("\u062a\u064f\u062d\u0648\u0651\u0644 \u0627\u0644\u0645\u0635\u0631\u0648\u0641\u0627\u062a \u0625\u0644\u0649 \u0627\u0644\u062f\u0648\u0644\u0627\u0631 \u0628\u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0627\u0644\u0645\u062d\u0641\u0648\u0638 \u0648\u0642\u062a \u062a\u0633\u062c\u064a\u0644 \u0643\u0644 \u0645\u0635\u0631\u0648\u0641. \u0635\u0627\u0641\u064a \u0646\u062a\u064a\u062c\u0629 \u0627\u0644\u062a\u0634\u063a\u064a\u0644 = \u0645\u062c\u0645\u0644 \u0627\u0644\u0631\u0628\u062d \u0628\u0639\u062f \u0645\u0631\u062a\u062c\u0639\u0627\u062a \u0627\u0644\u0645\u0628\u064a\u0639\u0627\u062a \u2212 \u0627\u0644\u0645\u0635\u0631\u0648\u0641\u0627\u062a \u0627\u0644\u0645\u0633\u062c\u0644\u0629\u060c \u0648\u0644\u0627 \u064a\u0634\u0645\u0644 \u0627\u0644\u0636\u0631\u0627\u0626\u0628 \u0623\u0648 \u0627\u0644\u0628\u0646\u0648\u062f \u063a\u064a\u0631 \u0627\u0644\u0645\u0633\u062c\u0644\u0629.", 11.0f, this.mutedColor, false, 5);
        textViewLabel10.setPadding(0, 0, 0, dp(12));
        linearLayout3.addView(textViewLabel10);
        linearLayout3.addView(sectionHeading("\u0645\u0644\u062e\u0635 \u0627\u0644\u0645\u062e\u0632\u0648\u0646"));
        TextView textViewLabel11 = label("\u0639\u062f\u062f \u0627\u0644\u0623\u0635\u0646\u0627\u0641 \u0627\u0644\u062a\u064a \u062a\u062a\u0628\u0639 \u0627\u0644\u0645\u062e\u0632\u0648\u0646: " + arrayList2.size(), 15.0f, this.textColor, true, 5);
        textViewLabel11.setPadding(0, dp(5), 0, dp(3));
        linearLayout3.addView(textViewLabel11);
        linearLayout3.addView(label("\u0639\u062f\u062f \u0627\u0644\u0623\u0635\u0646\u0627\u0641 \u0630\u0627\u062a \u0631\u0635\u064a\u062f \u0645\u0648\u062c\u0628: " + i5, 14.0f, this.textColor, false, 5));
        TextView textViewLabel12 = label("\u0645\u0644\u0627\u062d\u0638\u0629: \u0644\u0627 \u0646\u062c\u0645\u0639 \u0643\u0645\u064a\u0627\u062a \u0627\u0644\u0642\u0637\u0639\u0629 \u0648\u0627\u0644\u0643\u064a\u0644\u0648\u063a\u0631\u0627\u0645 \u0648\u0627\u0644\u0637\u0631\u062f \u0641\u064a \u0631\u0642\u0645 \u0648\u0627\u062d\u062f \u0644\u0627\u062e\u062a\u0644\u0627\u0641 \u0648\u062d\u062f\u0627\u062a \u0627\u0644\u0642\u064a\u0627\u0633. \u0631\u0623\u0633 \u0627\u0644\u0645\u0627\u0644 \u0645\u062d\u0633\u0648\u0628 \u0628\u0627\u0644\u0642\u064a\u0645\u0629 \u0627\u0644\u0645\u0627\u0644\u064a\u0629 \u0627\u0644\u0645\u0648\u062d\u062f\u0629 \u0628\u0627\u0644\u062f\u0648\u0644\u0627\u0631 \u0627\u0644\u0645\u0631\u062c\u0639\u064a.", 11.0f, this.mutedColor, false, 5);
        textViewLabel12.setPadding(0, dp(10), 0, dp(4));
        linearLayout3.addView(textViewLabel12);
        linearLayout3.addView(smallButton("\u0641\u062a\u062d \u0627\u0644\u0643\u0634\u0641 \u0627\u0644\u0645\u0627\u0644\u064a \u0627\u0644\u064a\u0648\u0645\u064a", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda53
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showGeneralReports$lambda$441(this.f$0);
            }
        }), fieldParams(dp(46), dp(12)));
        scrollView.addView(linearLayout3);
        linearLayout.addView(linearLayout2, matchWidth());
        linearLayout.addView(scrollView, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        setContentView(linearLayout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showGeneralReports$lambda$441(MainActivity mainActivity) throws IOException {
        mainActivity.showDailyCashReport();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void showServiceTransfers() throws IOException {
        String str;
        LinearLayout linearLayout;
        String str2;
        LinearLayout linearLayout2;
        LinearLayout linearLayout3;
        String str3;
        String str4;
        String str5;
        markScreen$default(this, "SERVICE_TRANSFERS", null, 2, null);
        MainActivity mainActivity = this;
        ServiceTransferRepository serviceTransferRepository = new ServiceTransferRepository(mainActivity);
        boolean zIsConfiguredSecondary = new SyncProtocolRepository(mainActivity, null, 2, 0 == true ? 1 : 0).isConfiguredSecondary();
        LinearLayout linearLayout4 = new LinearLayout(mainActivity);
        int i = 1;
        linearLayout4.setOrientation(1);
        linearLayout4.setBackgroundColor(this.backgroundColor);
        LinearLayout linearLayout5 = new LinearLayout(mainActivity);
        linearLayout5.setOrientation(0);
        linearLayout5.setGravity(16);
        linearLayout5.setPadding(dp(14), dp(10), dp(14), dp(10));
        linearLayout5.setBackgroundColor(-1);
        Button button = new Button(mainActivity);
        button.setText("\u2039");
        button.setTextSize(25.0f);
        button.setAllCaps(false);
        button.setTextColor(this.green);
        button.setBackgroundColor(0);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda80
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IOException {
                MainActivity.showPointOfSale$default(this.f$0, false, 1, null);
            }
        });
        linearLayout5.addView(button, new LinearLayout.LayoutParams(dp(48), dp(48)));
        linearLayout5.addView(label("\u062a\u062d\u0648\u064a\u0644 \u0631\u0635\u064a\u062f", 19.0f, this.green, true, 17), new LinearLayout.LayoutParams(0, -2, 1.0f));
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setFillViewport(true);
        LinearLayout linearLayout6 = new LinearLayout(mainActivity);
        linearLayout6.setOrientation(1);
        linearLayout6.setPadding(dp(16), dp(14), dp(16), dp(24));
        if (!zIsConfiguredSecondary) {
            int i2 = 12;
            String str6 = " \u0644.\u0633";
            linearLayout = linearLayout6;
            linearLayout.addView(label("\u0645\u062d\u0627\u0641\u0638 \u0627\u0644\u062a\u0634\u063a\u064a\u0644 \u0648\u0627\u0644\u0642\u0628\u0636 \u0627\u0644\u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a \u0644\u0644\u062e\u062f\u0645\u0627\u062a", 21.0f, this.green, true, 5));
            int i3 = 5;
            TextView textViewLabel = label("\u0645\u062d\u0641\u0638\u062a\u0627 \u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0648\u0634\u0627\u0645 \u0643\u0627\u0634 \u0647\u0645\u0627 \u0631\u0635\u064a\u062f \u0627\u0644\u062a\u0634\u063a\u064a\u0644 \u0646\u0641\u0633\u0647 \u0648\u062d\u0633\u0627\u0628 \u0627\u0644\u0642\u0628\u0636 \u0627\u0644\u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a \u0644\u0644\u062e\u062f\u0645\u0629\u061b \u0643\u0644 \u062d\u0631\u0643\u0629 \u0628\u064a\u0639 \u0623\u0648 \u0642\u0628\u0636 \u0623\u0648 \u062a\u062d\u0648\u064a\u0644 \u062f\u0627\u062e\u0644\u064a \u062a\u0638\u0647\u0631 \u0639\u0644\u0649 \u0631\u0635\u064a\u062f \u0627\u0644\u0645\u062d\u0641\u0638\u0629 \u0630\u0627\u062a\u0647\u0627.", 13.0f, this.mutedColor, false, 5);
            textViewLabel.setPadding(0, dp(5), 0, dp(12));
            linearLayout.addView(textViewLabel);
            for (ServiceWalletSummary serviceWalletSummary : serviceTransferRepository.listWallets()) {
                LinearLayout linearLayout7 = new LinearLayout(mainActivity);
                linearLayout7.setOrientation(i);
                linearLayout7.setPadding(dp(14), dp(i2), dp(14), dp(i2));
                linearLayout7.setBackground(cardBackground());
                int i4 = i3;
                linearLayout7.addView(label(serviceWalletSummary.getName(), 16.0f, this.green, true, 5));
                String sypAmount = formatSypAmount(serviceWalletSummary.getBalanceMinor());
                StringBuilder sb = new StringBuilder("\u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0645\u062a\u0627\u062d: ");
                sb.append(sypAmount);
                String str7 = str6;
                sb.append(str7);
                TextView textViewLabel2 = label(sb.toString(), 17.0f, this.textColor, true, 5);
                textViewLabel2.setPadding(0, dp(i4), 0, dp(2));
                linearLayout7.addView(textViewLabel2);
                linearLayout.addView(linearLayout7, fieldParams(-2, dp(8)));
                i3 = i4;
                str6 = str7;
                i2 = 12;
                i = 1;
            }
            str = str6;
            if (isManager()) {
                linearLayout.addView(smallButton("\u0645\u0637\u0627\u0628\u0642\u0629 \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0641\u0639\u0644\u064a \u0644\u0645\u062d\u0641\u0638\u0629 \u0627\u0644\u062a\u0634\u063a\u064a\u0644", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda81
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivity.showServiceTransfers$lambda$452(this.f$0);
                    }
                }), fieldParams(dp(46), dp(7)));
                linearLayout.addView(smallButton("\u0634\u0631\u0627\u0621 / \u0634\u062d\u0646 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0629", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda82
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivity.showServiceTransfers$lambda$453(this.f$0);
                    }
                }), fieldParams(dp(46), dp(12)));
            }
            Button button2 = new Button(mainActivity);
            button2.setText("\uff0b \u062a\u0646\u0641\u064a\u0630 \u062a\u062d\u0648\u064a\u0644 \u0644\u0644\u0632\u0628\u0648\u0646");
            button2.setAllCaps(false);
            button2.setTextColor(-1);
            button2.setBackgroundColor(this.green);
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda83
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws IOException {
                    this.f$0.showServiceTransferDialog();
                }
            });
            linearLayout.addView(button2, fieldParams(dp(52), dp(12)));
        } else {
            str = " \u0644.\u0633";
            linearLayout = linearLayout6;
        }
        String str8 = " \u0644.\u0633 \u2022 \u0631\u0633\u0648\u0645 \u0627\u0644\u0634\u0631\u0643\u0629: ";
        String str9 = "\u0631\u0628\u062d \u0627\u0644\u0645\u062a\u062c\u0631: ";
        if (zIsConfiguredSecondary) {
            str2 = " \u2022 ";
            str9 = str9;
            linearLayout2 = linearLayout4;
            linearLayout3 = linearLayout5;
            str3 = str;
            str4 = " \u0644.\u0633 \u2022 \u0631\u0633\u0648\u0645 \u0627\u0644\u0634\u0631\u0643\u0629: ";
        } else {
            linearLayout.addView(sectionHeading("\u0645\u0644\u062e\u0635 \u0639\u0645\u0644\u064a\u0627\u062a \u0627\u0644\u064a\u0648\u0645 \u0648\u0645\u0628\u0644\u063a \u0625\u0639\u0627\u062f\u0629 \u0627\u0644\u062a\u0639\u0628\u0626\u0629"));
            List<ServiceDailySummary> listDailySummary$default = ServiceTransferRepository.dailySummary$default(serviceTransferRepository, null, 1, null);
            if (listDailySummary$default.isEmpty()) {
                linearLayout.addView(label("\u0644\u0627 \u062a\u0648\u062c\u062f \u0639\u0645\u0644\u064a\u0627\u062a \u062a\u062d\u0648\u064a\u0644 \u0631\u0635\u064a\u062f \u0627\u0644\u064a\u0648\u0645.", 12.0f, this.mutedColor, false, 17));
            }
            for (ServiceDailySummary serviceDailySummary : listDailySummary$default) {
                LinearLayout linearLayout8 = new LinearLayout(mainActivity);
                linearLayout8.setOrientation(1);
                linearLayout8.setPadding(dp(12), dp(10), dp(12), dp(10));
                linearLayout8.setBackground(cardBackground());
                linearLayout8.addView(label(serviceDailySummary.getServiceName() + " \u2022 " + serviceDailySummary.getOperationCount() + " \u0639\u0645\u0644\u064a\u0629", 14.0f, this.green, true, 5));
                String plainString = MainActivity$$ExternalSyntheticBackport0.m(BigDecimal.valueOf(serviceDailySummary.getDeliveredMilliSyp(), 3)).toPlainString();
                StringBuilder sb2 = new StringBuilder("\u0625\u062c\u0645\u0627\u0644\u064a \u0645\u0627 \u0623\u064f\u0631\u0633\u0644 \u0645\u0646 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0629: ");
                sb2.append(plainString);
                String str10 = str;
                sb2.append(str10);
                LinearLayout linearLayout9 = linearLayout4;
                linearLayout8.addView(label(sb2.toString(), 12.0f, this.textColor, false, 5));
                linearLayout8.addView(label("\u0625\u062c\u0645\u0627\u0644\u064a \u0645\u0627 \u0642\u0628\u0636\u0647 \u0627\u0644\u0645\u062a\u062c\u0631 \u0645\u0646 \u0627\u0644\u0632\u0628\u0627\u0626\u0646 \u0646\u0642\u062f\u0627\u064b \u0623\u0648 \u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a\u0627\u064b: " + formatSypAmount(serviceDailySummary.getCustomerReceiptsMinor()) + str10, 12.0f, this.textColor, false, 5));
                if (serviceDailySummary.getCreditChargesMinor() > 0) {
                    linearLayout8.addView(label("\u062f\u064a\u0648\u0646 \u062a\u062d\u0648\u064a\u0644\u0627\u062a \u0622\u062c\u0644\u0629 \u062c\u062f\u064a\u062f\u0629: " + formatSypAmount(serviceDailySummary.getCreditChargesMinor()) + str10, 12.0f, this.gold, true, 5));
                }
                String plainString2 = MainActivity$$ExternalSyntheticBackport0.m(BigDecimal.valueOf(serviceDailySummary.getProfitMilliSyp(), 3)).toPlainString();
                String sypAmount2 = formatSypAmount(serviceDailySummary.getProviderFeeMinor());
                StringBuilder sb3 = new StringBuilder(str9);
                sb3.append(plainString2);
                String str11 = str8;
                sb3.append(str11);
                sb3.append(sypAmount2);
                sb3.append(str10);
                linearLayout8.addView(label(sb3.toString(), 12.0f, this.green, true, 5));
                linearLayout8.addView(label("\u0631\u0633\u0648\u0645 \u0627\u0644\u062a\u0639\u0628\u0626\u0629 \u0627\u0644\u0645\u062f\u0641\u0648\u0639\u0629 \u0641\u0639\u0644\u064a\u0627\u064b \u0627\u0644\u064a\u0648\u0645: " + formatSypAmount(serviceDailySummary.getRechargeFeePaidMinor()) + " \u0644.\u0633 \u2022 \u0642\u064a\u0645\u0629 \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0644\u0627\u0632\u0645 \u062a\u0639\u0648\u064a\u0636\u0647: " + formatSypAmount(serviceDailySummary.getRechargeReserveMinor()) + str10, 12.0f, this.mutedColor, true, 5));
                linearLayout.addView(linearLayout8, fieldParams(-2, dp(7)));
                str = str10;
                str8 = str11;
                linearLayout4 = linearLayout9;
                linearLayout5 = linearLayout5;
            }
            linearLayout2 = linearLayout4;
            linearLayout3 = linearLayout5;
            str3 = str;
            str4 = str8;
        }
        linearLayout.addView(sectionHeading("\u062f\u064a\u0648\u0646 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0622\u062c\u0644\u0629"));
        List listListOpenReceivables$default = ServiceTransferReceivableRepository.listOpenReceivables$default(new ServiceTransferReceivableRepository(mainActivity), null, 0, 3, null);
        if (listListOpenReceivables$default.isEmpty()) {
            linearLayout.addView(label("\u0644\u0627 \u062a\u0648\u062c\u062f \u062f\u064a\u0648\u0646 \u062a\u062d\u0648\u064a\u0644 \u0631\u0635\u064a\u062f \u0645\u0641\u062a\u0648\u062d\u0629.", 12.0f, this.mutedColor, false, 17));
        }
        Iterator it = listListOpenReceivables$default.iterator();
        while (it.hasNext()) {
            final ServiceTransferReceivableSummary serviceTransferReceivableSummary = (ServiceTransferReceivableSummary) it.next();
            String serviceType = serviceTransferReceivableSummary.getServiceType();
            if (Intrinsics.areEqual(serviceType, ServiceTransferRepository.SYRIATEL_BALANCE)) {
                str5 = "\u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0627\u0644\u0639\u0627\u062f\u064a";
            } else {
                str5 = Intrinsics.areEqual(serviceType, ServiceTransferRepository.SYRIATEL_CASH) ? "\u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0643\u0627\u0634" : "\u0634\u0627\u0645 \u0643\u0627\u0634";
            }
            LinearLayout linearLayout10 = new LinearLayout(mainActivity);
            linearLayout10.setOrientation(1);
            linearLayout10.setPadding(dp(12), dp(10), dp(12), dp(10));
            linearLayout10.setBackground(cardBackground());
            linearLayout10.addView(label(serviceTransferReceivableSummary.getCustomerName() + str2 + str5, 14.0f, this.green, true, 5));
            linearLayout10.addView(label("\u0625\u062c\u0645\u0627\u0644\u064a \u0627\u0644\u062f\u064a\u0646: " + formatSypAmount(serviceTransferReceivableSummary.getChargeMinor()) + " \u0644.\u0633 \u2022 \u0627\u0644\u0645\u0633\u062f\u062f: " + formatSypAmount(serviceTransferReceivableSummary.getPaidMinor()) + str3, 12.0f, this.textColor, false, 5));
            String sypAmount3 = formatSypAmount(serviceTransferReceivableSummary.getOutstandingMinor());
            StringBuilder sb4 = new StringBuilder("\u0627\u0644\u0645\u062a\u0628\u0642\u064a: ");
            sb4.append(sypAmount3);
            sb4.append(str3);
            linearLayout10.addView(label(sb4.toString(), 15.0f, this.gold, true, 5));
            linearLayout10.addView(smallButton("\u062a\u062d\u0635\u064a\u0644 \u062f\u0641\u0639\u0629", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda84
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MainActivity.showServiceTransfers$lambda$460$lambda$459(this.f$0, serviceTransferReceivableSummary);
                }
            }), fieldParams(dp(42), dp(6)));
            linearLayout.addView(linearLayout10, fieldParams(-2, dp(7)));
            it = it;
            str4 = str4;
        }
        String str12 = str4;
        if (!zIsConfiguredSecondary) {
            TextView textViewLabel3 = label("\u0627\u0644\u0645\u0642\u0628\u0648\u0636 \u0627\u0644\u0646\u0642\u062f\u064a \u064a\u0630\u0647\u0628 \u0625\u0644\u0649 \u0627\u0644\u062f\u0631\u062c \u0627\u0644\u0631\u0626\u064a\u0633\u064a\u060c \u0648\u0627\u0644\u0645\u0642\u0628\u0648\u0636 \u0627\u0644\u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a \u064a\u0636\u0627\u0641 \u0625\u0644\u0649 \u0645\u062d\u0641\u0638\u0629 \u0627\u0644\u062e\u062f\u0645\u0629 \u0646\u0641\u0633\u0647\u0627 \u0627\u0641\u062a\u0631\u0627\u0636\u064a\u0627\u064b \u0623\u0648 \u0625\u0644\u0649 \u0627\u0644\u0645\u062d\u0641\u0638\u0629 \u0627\u0644\u062a\u064a \u062a\u062e\u062a\u0627\u0631\u0647\u0627. \u0639\u0646\u062f \u062a\u0635\u0631\u064a\u0641 \u0627\u0644\u0645\u062d\u0641\u0638\u0629 \u0625\u0644\u0649 \u0627\u0644\u0646\u0642\u062f\u064a\u0629 \u062a\u0646\u0642\u0635 \u0627\u0644\u0645\u062d\u0641\u0638\u0629 \u0648\u064a\u0632\u064a\u062f \u0627\u0644\u0635\u0646\u062f\u0648\u0642 \u0628\u0627\u0644\u0645\u0628\u0644\u063a \u0646\u0641\u0633\u0647. \u0639\u0645\u0648\u0644\u0629 2% \u062a\u064f\u0633\u062c\u0644 \u0641\u0642\u0637 \u0639\u0646\u062f \u062a\u0639\u0628\u0626\u0629 \u0631\u0635\u064a\u062f \u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0623\u0648 \u0634\u0627\u0645 \u0643\u0627\u0634 \u0645\u0646 \u062a\u0627\u062c\u0631 \u062e\u0627\u0631\u062c\u064a\u060c \u0648\u0644\u0627 \u062a\u064f\u0636\u0627\u0641 \u0625\u0644\u0649 \u062a\u062d\u0648\u064a\u0644\u0627\u062a \u0627\u0644\u0632\u0628\u0627\u0626\u0646.", 11.0f, this.mutedColor, false, 5);
            textViewLabel3.setPadding(0, dp(3), 0, dp(10));
            linearLayout.addView(textViewLabel3);
            linearLayout.addView(sectionHeading("\u0633\u062c\u0644 \u062a\u062d\u0648\u064a\u0644\u0627\u062a \u0627\u0644\u0631\u0635\u064a\u062f"));
            List<ServiceTransferRecord> listListTransfers = serviceTransferRepository.listTransfers(80);
            if (listListTransfers.isEmpty()) {
                TextView textViewLabel4 = label("\u0644\u0627 \u062a\u0648\u062c\u062f \u062a\u062d\u0648\u064a\u0644\u0627\u062a \u0645\u0633\u062c\u0644\u0629 \u062d\u062a\u0649 \u0627\u0644\u0622\u0646.", 13.0f, this.mutedColor, false, 17);
                textViewLabel4.setPadding(0, dp(18), 0, dp(18));
                linearLayout.addView(textViewLabel4);
            }
            for (ServiceTransferRecord serviceTransferRecord : listListTransfers) {
                LinearLayout linearLayout11 = new LinearLayout(mainActivity);
                linearLayout11.setOrientation(1);
                linearLayout11.setPadding(dp(12), dp(10), dp(12), dp(10));
                linearLayout11.setBackground(cardBackground());
                linearLayout11.addView(label(serviceTransferRecord.getServiceName() + str2 + (Intrinsics.areEqual(serviceTransferRecord.getStatus(), "REVERSED") ? "\u0645\u0639\u0643\u0648\u0633\u0629" : "\u0645\u0646\u0641\u0630\u0629"), 15.0f, this.green, true, 5));
                linearLayout11.addView(label("\u0627\u0644\u0648\u0627\u0635\u0644 \u0644\u0644\u0632\u0628\u0648\u0646: " + MainActivity$$ExternalSyntheticBackport0.m(BigDecimal.valueOf(serviceTransferRecord.getDeliveredMilliSyp(), 3)).toPlainString() + " \u0644.\u0633 \u2022 \u0627\u0644\u0645\u062e\u0635\u0648\u0645 \u0645\u0646 \u0627\u0644\u0645\u062d\u0641\u0638\u0629: " + formatSypAmount(serviceTransferRecord.getAmountDebitedMinor()) + str3, 13.0f, this.textColor, false, 5));
                String plainString3 = MainActivity$$ExternalSyntheticBackport0.m(BigDecimal.valueOf(serviceTransferRecord.getProfitMilliSyp(), 3)).toPlainString();
                String sypAmount4 = formatSypAmount(serviceTransferRecord.getProviderFeeMinor());
                StringBuilder sb5 = new StringBuilder(str9);
                sb5.append(plainString3);
                String str13 = str12;
                sb5.append(str13);
                sb5.append(sypAmount4);
                sb5.append(str3);
                linearLayout11.addView(label(sb5.toString(), 12.0f, this.mutedColor, false, 5));
                if (Intrinsics.areEqual(serviceTransferRecord.getPaymentMode(), "CREDIT")) {
                    String customerName = serviceTransferRecord.getCustomerName();
                    if (customerName == null) {
                        customerName = "\u063a\u064a\u0631 \u0645\u0639\u0631\u0648\u0641";
                    }
                    linearLayout11.addView(label("\u0622\u062c\u0644 \u0639\u0644\u0649 \u0627\u0644\u0632\u0628\u0648\u0646: " + customerName, 13.0f, this.gold, true, 5));
                    linearLayout11.addView(label("\u0627\u0644\u0645\u0633\u062f\u062f: " + formatSypAmount(serviceTransferRecord.getPaidMinor()) + " \u0644.\u0633 \u2022 \u0627\u0644\u0645\u062a\u0628\u0642\u064a: " + formatSypAmount(serviceTransferRecord.getOutstandingMinor()) + str3, 12.0f, this.textColor, false, 5));
                } else {
                    linearLayout11.addView(label("\u0627\u0644\u0645\u0642\u0628\u0648\u0636: " + formatSypAmount(serviceTransferRecord.getCustomerChargeMinor()) + " \u0644.\u0633 \u2022 " + serviceTransferRecord.getReceiptAccountName() + (Intrinsics.areEqual(serviceTransferRecord.getReceiptChannel(), "ELECTRONIC") ? " (\u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a)" : " (\u0646\u0642\u062f\u064a)"), 13.0f, this.green, true, 5));
                }
                StoreTime storeTime = StoreTime.INSTANCE;
                SQLiteDatabase readableDatabase = AppDatabase.INSTANCE.getInstance(mainActivity).getReadableDatabase();
                Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
                linearLayout11.addView(label(storeTime.formatTimestamp(readableDatabase, serviceTransferRecord.getCreatedAt()), 10.0f, this.mutedColor, false, 5));
                linearLayout.addView(linearLayout11, fieldParams(-2, dp(8)));
                str12 = str13;
            }
        }
        scrollView.addView(linearLayout);
        LinearLayout linearLayout12 = linearLayout2;
        linearLayout12.addView(linearLayout3, matchWidth());
        linearLayout12.addView(scrollView, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        setContentView(linearLayout12);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showServiceTransfers$lambda$452(MainActivity mainActivity) {
        mainActivity.showServiceOpeningDialog();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showServiceTransfers$lambda$453(MainActivity mainActivity) throws IOException {
        mainActivity.showServiceRechargeDialog();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showServiceTransfers$lambda$460$lambda$459(MainActivity mainActivity, ServiceTransferReceivableSummary serviceTransferReceivableSummary) throws IOException {
        showServiceTransferDebtPaymentDialog$default(mainActivity, serviceTransferReceivableSummary.getTransferId(), null, 2, null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void showServiceTransferDebtPaymentDialog$default(MainActivity mainActivity, String str, Function0 function0, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            function0 = null;
        }
        mainActivity.showServiceTransferDebtPaymentDialog(str, function0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void showServiceTransferDebtPaymentDialog(final String transferId, final Function0<Unit> onSaved) throws IOException {
        Object next;
        MainActivity mainActivity = this;
        final ServiceTransferReceivableRepository serviceTransferReceivableRepository = new ServiceTransferReceivableRepository(mainActivity);
        SyncCredentialVault syncCredentialVault = null;
        Object[] objArr = 0;
        Iterator it = ServiceTransferReceivableRepository.listOpenReceivables$default(serviceTransferReceivableRepository, null, 0, 3, null).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((ServiceTransferReceivableSummary) next).getTransferId(), transferId));
        ServiceTransferReceivableSummary serviceTransferReceivableSummary = (ServiceTransferReceivableSummary) next;
        if (serviceTransferReceivableSummary == null) {
            Toast.makeText(mainActivity, "\u0647\u0630\u0627 \u0627\u0644\u062f\u064a\u0646 \u063a\u064a\u0631 \u0645\u0641\u062a\u0648\u062d \u0623\u0648 \u062a\u0645 \u062a\u0633\u062f\u064a\u062f\u0647.", 0).show();
            if (onSaved != null) {
                onSaved.invoke();
                return;
            } else {
                showServiceTransfers();
                return;
            }
        }
        CashAccountRepository cashAccountRepository = new CashAccountRepository(mainActivity);
        CashAccountSummary cashAccountSummaryCurrentDayPrimaryDrawer = cashAccountRepository.currentDayPrimaryDrawer();
        boolean zIsConfiguredSecondary = new SyncProtocolRepository(mainActivity, syncCredentialVault, 2, objArr == true ? 1 : 0).isConfiguredSecondary();
        List<CashAccountSummary> listListActive = cashAccountRepository.listActive();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listListActive) {
            CashAccountSummary cashAccountSummary = (CashAccountSummary) obj;
            if (Intrinsics.areEqual(cashAccountSummary.getCurrencyCode(), "SYP")) {
                if (!Intrinsics.areEqual(cashAccountSummary.getAccountType(), "WALLET")) {
                    if (Intrinsics.areEqual(cashAccountSummary.getAccountType(), "CASH_BOX")) {
                        if (!zIsConfiguredSecondary) {
                            if (Intrinsics.areEqual(cashAccountSummary.getId(), cashAccountSummaryCurrentDayPrimaryDrawer != null ? cashAccountSummaryCurrentDayPrimaryDrawer.getId() : null)) {
                            }
                        }
                    }
                }
                arrayList.add(obj);
            }
        }
        final ArrayList arrayList2 = arrayList;
        if (arrayList2.isEmpty()) {
            new AlertDialog.Builder(mainActivity).setMessage("\u0639\u064a\u0651\u0646 \u062f\u0631\u062c \u0627\u0644\u064a\u0648\u0645 \u0627\u0644\u0631\u0626\u064a\u0633\u064a \u0623\u0648 \u0623\u0646\u0634\u0626 \u0645\u062d\u0641\u0638\u0629 \u0642\u0628\u0636 \u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a\u0629 \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629 \u0623\u0648\u0644\u0627\u064b.").setPositiveButton("\u062d\u0633\u0646\u0627\u064b", (DialogInterface.OnClickListener) null).show();
            return;
        }
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(18), dp(8), dp(18), dp(4));
        final EditText editTextFieldForDialog$default = fieldForDialog$default(this, "\u0645\u0628\u0644\u063a \u0627\u0644\u062f\u0641\u0639\u0629 \u2022 \u0627\u0644\u0645\u062a\u0628\u0642\u064a " + formatSypAmount(serviceTransferReceivableSummary.getOutstandingMinor()) + " \u0644.\u0633", null, 0, 6, null);
        editTextFieldForDialog$default.setInputType(8194);
        final Spinner spinner = new Spinner(mainActivity);
        ArrayList<CashAccountSummary> arrayList3 = arrayList2;
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
        for (CashAccountSummary cashAccountSummary2 : arrayList3) {
            arrayList4.add(cashAccountSummary2.getName() + (Intrinsics.areEqual(cashAccountSummary2.getAccountType(), "WALLET") ? " \u2022 \u0642\u0628\u0636 \u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a" : " \u2022 \u062f\u0631\u062c \u0627\u0644\u064a\u0648\u0645 \u0627\u0644\u0631\u0626\u064a\u0633\u064a"));
        }
        spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, arrayList4));
        final EditText editTextFieldForDialog$default2 = fieldForDialog$default(this, "\u0645\u0644\u0627\u062d\u0638\u0629 / \u0645\u0631\u062c\u0639 \u0627\u0644\u062a\u062d\u0635\u064a\u0644", null, 0, 6, null);
        linearLayout.addView(editTextFieldForDialog$default, fieldParams(dp(48), dp(8)));
        linearLayout.addView(label("\u0627\u0633\u062a\u0644\u0627\u0645 \u0627\u0644\u062f\u0641\u0639\u0629 \u0641\u064a", 12.0f, this.mutedColor, false, 5));
        linearLayout.addView(spinner, fieldParams(dp(48), dp(8)));
        linearLayout.addView(editTextFieldForDialog$default2, fieldParams(dp(48), dp(8)));
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(mainActivity).setTitle("\u062a\u062d\u0635\u064a\u0644 \u062f\u064a\u0646 \u062a\u062d\u0648\u064a\u0644 \u0627\u0644\u0631\u0635\u064a\u062f").setView(linearLayout).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062a\u062d\u0635\u064a\u0644", (DialogInterface.OnClickListener) null).create();
        alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda45
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                MainActivity.showServiceTransferDebtPaymentDialog$lambda$474(alertDialogCreate, arrayList2, spinner, serviceTransferReceivableRepository, this, transferId, editTextFieldForDialog$default, editTextFieldForDialog$default2, onSaved, dialogInterface);
            }
        });
        alertDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showServiceTransferDebtPaymentDialog$lambda$474(final AlertDialog alertDialog, final List list, final Spinner spinner, final ServiceTransferReceivableRepository serviceTransferReceivableRepository, final MainActivity mainActivity, final String str, final EditText editText, final EditText editText2, final Function0 function0, DialogInterface dialogInterface) {
        alertDialog.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda121
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Throwable {
                MainActivity.showServiceTransferDebtPaymentDialog$lambda$474$lambda$473(list, spinner, serviceTransferReceivableRepository, mainActivity, str, editText, editText2, alertDialog, function0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showServiceTransferDebtPaymentDialog$lambda$474$lambda$473(List list, Spinner spinner, ServiceTransferReceivableRepository serviceTransferReceivableRepository, final MainActivity mainActivity, String str, EditText editText, EditText editText2, AlertDialog alertDialog, final Function0 function0, View view) throws Throwable {
        String str2;
        try {
            CashAccountSummary cashAccountSummary = (CashAccountSummary) CollectionsKt.getOrNull(list, spinner.getSelectedItemPosition());
            if (cashAccountSummary == null) {
                throw new IllegalStateException("\u0627\u062e\u062a\u0631 \u062d\u0633\u0627\u0628 \u0627\u0644\u0642\u0628\u0636".toString());
            }
            String str3 = mainActivity.currentUserId;
            if (str3 == null) {
                throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
            }
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            String id = cashAccountSummary.getId();
            long sypAmount = mainActivity.parseSypAmount(editText.getText().toString());
            String string2 = StringsKt.trim((CharSequence) editText2.getText().toString()).toString();
            if (string2.length() == 0) {
                string2 = null;
            }
            ServiceTransferDebtPaymentResult serviceTransferDebtPaymentResultCollect = serviceTransferReceivableRepository.collect(str3, str, string, id, sypAmount, (BERTags.PRIVATE & 32) != 0 ? null : string2, (BERTags.PRIVATE & 64) != 0 ? null : null, (BERTags.PRIVATE & 128) != 0 ? null : null);
            alertDialog.dismiss();
            AlertDialog.Builder title = new AlertDialog.Builder(mainActivity).setTitle(serviceTransferDebtPaymentResultCollect.getPending() ? "\u062a\u0645 \u062d\u0641\u0638 \u0637\u0644\u0628 \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0644\u0644\u0645\u0632\u0627\u0645\u0646\u0629" : "\u062a\u0645 \u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062a\u062d\u0635\u064a\u0644");
            if (serviceTransferDebtPaymentResultCollect.getPending()) {
                str2 = "\u062d\u064f\u0641\u0638 \u0637\u0644\u0628 \u0627\u0644\u062a\u062d\u0635\u064a\u0644 \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u0648\u0633\u064a\u064f\u0631\u0633\u0644 \u0625\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a. \u0644\u0646 \u064a\u064f\u0631\u062d\u0651\u0644 \u0625\u0644\u0649 \u0627\u0644\u0635\u0646\u062f\u0648\u0642 \u0623\u0648 \u064a\u064f\u0639\u062a\u0645\u062f \u0641\u064a \u0631\u0635\u064a\u062f \u0627\u0644\u062f\u064a\u0646 \u062d\u062a\u0649 \u064a\u0624\u0643\u062f \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u062a\u0637\u0628\u064a\u0642\u0647.";
            } else {
                str2 = "\u0627\u0644\u0645\u0633\u062f\u062f \u062d\u062a\u0649 \u0627\u0644\u0622\u0646: " + mainActivity.formatSypAmount(serviceTransferDebtPaymentResultCollect.getPaidMinor()) + " \u0644.\u0633\n\u0627\u0644\u0645\u062a\u0628\u0642\u064a: " + mainActivity.formatSypAmount(serviceTransferDebtPaymentResultCollect.getOutstandingMinor()) + " \u0644.\u0633";
            }
            title.setMessage(str2).setPositiveButton("\u062d\u0633\u0646\u0627\u064b", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda88
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) throws IOException {
                    MainActivity.showServiceTransferDebtPaymentDialog$lambda$474$lambda$473$lambda$472(function0, mainActivity, dialogInterface, i);
                }
            }).show();
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062a\u062d\u0635\u064a\u0644";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showServiceTransferDebtPaymentDialog$lambda$474$lambda$473$lambda$472(Function0 function0, MainActivity mainActivity, DialogInterface dialogInterface, int i) throws IOException {
        if (function0 != null) {
            function0.invoke();
        } else {
            mainActivity.showServiceTransfers();
        }
    }

    private final void showServiceOpeningDialog() {
        if (isManager()) {
            MainActivity mainActivity = this;
            final ServiceTransferRepository serviceTransferRepository = new ServiceTransferRepository(mainActivity);
            LinearLayout linearLayout = new LinearLayout(mainActivity);
            linearLayout.setOrientation(1);
            linearLayout.setPadding(dp(18), dp(8), dp(18), dp(4));
            final Spinner spinner = new Spinner(mainActivity);
            spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, CollectionsKt.listOf((Object[]) new String[]{"\u0631\u0635\u064a\u062f \u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0627\u0644\u0645\u0634\u062a\u0631\u0643", "\u0631\u0635\u064a\u062f \u0634\u0627\u0645 \u0643\u0627\u0634"})));
            final EditText editTextFieldForDialog$default = fieldForDialog$default(this, "\u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0641\u0639\u0644\u064a \u0627\u0644\u062d\u0627\u0644\u064a \u0644\u0644\u0645\u062d\u0641\u0638\u0629 \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629", null, 0, 6, null);
            editTextFieldForDialog$default.setInputType(8194);
            final EditText editTextFieldForDialog$default2 = fieldForDialog$default(this, "\u0645\u0644\u0627\u062d\u0638\u0629 / \u0645\u0631\u062c\u0639", null, 0, 6, null);
            linearLayout.addView(label("\u0627\u0644\u062e\u062f\u0645\u0629", 12.0f, this.mutedColor, false, 5));
            linearLayout.addView(spinner, fieldParams(dp(48), dp(8)));
            linearLayout.addView(editTextFieldForDialog$default, fieldParams(dp(48), dp(8)));
            linearLayout.addView(editTextFieldForDialog$default2, fieldParams(dp(48), dp(8)));
            final AlertDialog alertDialogCreate = new AlertDialog.Builder(mainActivity).setTitle("\u0645\u0637\u0627\u0628\u0642\u0629 \u0631\u0635\u064a\u062f \u0645\u062d\u0641\u0638\u0629 \u0627\u0644\u062a\u0634\u063a\u064a\u0644").setView(linearLayout).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u062d\u0641\u0638", (DialogInterface.OnClickListener) null).create();
            alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda104
                @Override // android.content.DialogInterface.OnShowListener
                public final void onShow(DialogInterface dialogInterface) {
                    MainActivity.showServiceOpeningDialog$lambda$480(alertDialogCreate, spinner, serviceTransferRepository, this, editTextFieldForDialog$default, editTextFieldForDialog$default2, dialogInterface);
                }
            });
            alertDialogCreate.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showServiceOpeningDialog$lambda$480(final AlertDialog alertDialog, final Spinner spinner, final ServiceTransferRepository serviceTransferRepository, final MainActivity mainActivity, final EditText editText, final EditText editText2, DialogInterface dialogInterface) {
        alertDialog.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda106
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Throwable {
                MainActivity.showServiceOpeningDialog$lambda$480$lambda$479(spinner, serviceTransferRepository, mainActivity, editText, editText2, alertDialog, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showServiceOpeningDialog$lambda$480$lambda$479(Spinner spinner, ServiceTransferRepository serviceTransferRepository, MainActivity mainActivity, EditText editText, EditText editText2, AlertDialog alertDialog, View view) throws Throwable {
        try {
            String str = spinner.getSelectedItemPosition() == 0 ? ServiceTransferRepository.SYRIATEL_SHARED : "SHAM_CASH";
            String str2 = mainActivity.currentUserId;
            if (str2 == null) {
                throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u062f\u064a\u0631 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
            }
            long sypAmount = mainActivity.parseSypAmount(editText.getText().toString());
            String string = StringsKt.trim((CharSequence) editText2.getText().toString()).toString();
            if (string.length() == 0) {
                string = null;
            }
            serviceTransferRepository.setOpeningBalance(str2, str, sypAmount, string);
            alertDialog.dismiss();
            Toast.makeText(mainActivity, "\u062a\u0645\u062a \u0645\u0637\u0627\u0628\u0642\u0629 \u0631\u0635\u064a\u062f \u0627\u0644\u0645\u062d\u0641\u0638\u0629 \u0645\u0639 \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0641\u0639\u0644\u064a", 1).show();
            mainActivity.showServiceTransfers();
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u062d\u0641\u0638 \u0627\u0644\u0631\u0635\u064a\u062f";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    private final void showServiceRechargeDialog() throws IOException {
        if (isManager()) {
            MainActivity mainActivity = this;
            final ServiceTransferRepository serviceTransferRepository = new ServiceTransferRepository(mainActivity);
            String strRechargeAccountId = serviceTransferRepository.rechargeAccountId();
            List<CashAccountSummary> listListActive = new CashAccountRepository(mainActivity).listActive();
            ArrayList arrayList = new ArrayList();
            for (Object obj : listListActive) {
                CashAccountSummary cashAccountSummary = (CashAccountSummary) obj;
                if (Intrinsics.areEqual(cashAccountSummary.getId(), strRechargeAccountId) && Intrinsics.areEqual(cashAccountSummary.getAccountType(), "CASH_BOX") && Intrinsics.areEqual(cashAccountSummary.getCurrencyCode(), "SYP")) {
                    arrayList.add(obj);
                }
            }
            final ArrayList arrayList2 = arrayList;
            if (arrayList2.isEmpty()) {
                new AlertDialog.Builder(mainActivity).setMessage("\u0623\u0646\u0634\u0626 \u0635\u0646\u062f\u0648\u0642\u0627\u064b \u0646\u0642\u062f\u064a\u0627\u064b \u0645\u0633\u062a\u0642\u0644\u0627\u064b \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629 \u0645\u062e\u0635\u0635\u0627\u064b \u0644\u0634\u0631\u0627\u0621 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0627\u062a \u0623\u0648\u0644\u0627\u064b.").setPositiveButton("\u0641\u062a\u062d \u0627\u0644\u0635\u0646\u0627\u062f\u064a\u0642 \u0648\u0627\u0644\u0645\u062d\u0627\u0641\u0638", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda154
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) throws IOException {
                        this.f$0.showCashAccounts();
                    }
                }).show();
                return;
            }
            LinearLayout linearLayout = new LinearLayout(mainActivity);
            linearLayout.setOrientation(1);
            linearLayout.setPadding(dp(18), dp(8), dp(18), dp(4));
            final Spinner spinner = new Spinner(mainActivity);
            spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, CollectionsKt.listOf((Object[]) new String[]{"\u0631\u0635\u064a\u062f \u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0627\u0644\u0645\u0634\u062a\u0631\u0643", "\u0631\u0635\u064a\u062f \u0634\u0627\u0645 \u0643\u0627\u0634"})));
            final Spinner spinner2 = new Spinner(mainActivity);
            ArrayList<CashAccountSummary> arrayList3 = arrayList2;
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
            for (CashAccountSummary cashAccountSummary2 : arrayList3) {
                arrayList4.add(cashAccountSummary2.getName() + " \u2022 \u0635\u0646\u062f\u0648\u0642 \u0634\u0631\u0627\u0621 \u0627\u0644\u0631\u0635\u064a\u062f \u2022 \u0627\u0644\u0645\u062a\u0627\u062d " + formatSypAmount(cashAccountSummary2.getBalanceMinor()) + " \u0644.\u0633");
            }
            spinner2.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, arrayList4));
            final EditText editTextFieldForDialog$default = fieldForDialog$default(this, "\u0642\u064a\u0645\u0629 \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0645\u0631\u0627\u062f \u0634\u0631\u0627\u0624\u0647", null, 0, 6, null);
            editTextFieldForDialog$default.setInputType(8194);
            final TextView textViewLabel = label("\u0639\u0645\u0648\u0644\u0629 \u062a\u0639\u0628\u0626\u0629 2% \u0639\u0646\u062f \u0634\u0631\u0627\u0621 \u0631\u0635\u064a\u062f \u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0623\u0648 \u0634\u0627\u0645 \u0643\u0627\u0634 \u0645\u0646 \u062a\u0627\u062c\u0631 \u062e\u0627\u0631\u062c\u064a \u0641\u0642\u0637\u061b \u0644\u0627 \u0639\u0645\u0648\u0644\u0629 \u0639\u0644\u0649 \u062a\u062d\u0648\u064a\u0644\u0627\u062a \u0627\u0644\u0632\u0628\u0627\u0626\u0646.", 12.0f, this.green, false, 5);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.hazem.dailystore.MainActivity.showServiceRechargeDialog.2
                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onNothingSelected(AdapterView<?> parent) {
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    textViewLabel.setText(position == 0 ? "\u0639\u0645\u0648\u0644\u0629 \u0634\u062d\u0646 \u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644: 2% \u0645\u0646 \u0642\u064a\u0645\u0629 \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0645\u0634\u062a\u064e\u0631\u0649" : "\u0639\u0645\u0648\u0644\u0629 \u0634\u062d\u0646 \u0634\u0627\u0645 \u0643\u0627\u0634: 2% \u0645\u0646 \u0642\u064a\u0645\u0629 \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0645\u0634\u062a\u064e\u0631\u0649");
                }
            });
            final EditText editTextFieldForDialog$default2 = fieldForDialog$default(this, "\u0645\u0631\u062c\u0639 \u0639\u0645\u0644\u064a\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 / \u0645\u0644\u0627\u062d\u0638\u0629", null, 0, 6, null);
            linearLayout.addView(label("\u0645\u062d\u0641\u0638\u0629 \u0627\u0644\u062e\u062f\u0645\u0629", 12.0f, this.mutedColor, false, 5));
            linearLayout.addView(spinner, fieldParams(dp(48), dp(8)));
            linearLayout.addView(label("\u0627\u0644\u062a\u0645\u0648\u064a\u0644 \u0645\u0646 \u0635\u0646\u062f\u0648\u0642 \u0634\u0631\u0627\u0621 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0627\u062a", 12.0f, this.mutedColor, false, 5));
            linearLayout.addView(spinner2, fieldParams(dp(48), dp(8)));
            linearLayout.addView(editTextFieldForDialog$default, fieldParams(dp(48), dp(6)));
            linearLayout.addView(textViewLabel);
            linearLayout.addView(editTextFieldForDialog$default2, fieldParams(dp(48), dp(8)));
            final String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            final AlertDialog alertDialogCreate = new AlertDialog.Builder(mainActivity).setTitle("\u0634\u0631\u0627\u0621 / \u0634\u062d\u0646 \u0631\u0635\u064a\u062f \u0627\u0644\u062e\u062f\u0645\u0629").setView(linearLayout).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u0634\u0631\u0627\u0621 \u0627\u0644\u0631\u0635\u064a\u062f", (DialogInterface.OnClickListener) null).create();
            alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda155
                @Override // android.content.DialogInterface.OnShowListener
                public final void onShow(DialogInterface dialogInterface) {
                    MainActivity.showServiceRechargeDialog$lambda$490(alertDialogCreate, spinner, arrayList2, spinner2, editTextFieldForDialog$default2, serviceTransferRepository, this, editTextFieldForDialog$default, string, dialogInterface);
                }
            });
            alertDialogCreate.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showServiceRechargeDialog$lambda$490(final AlertDialog alertDialog, final Spinner spinner, final List list, final Spinner spinner2, final EditText editText, final ServiceTransferRepository serviceTransferRepository, final MainActivity mainActivity, final EditText editText2, final String str, DialogInterface dialogInterface) {
        alertDialog.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Throwable {
                MainActivity.showServiceRechargeDialog$lambda$490$lambda$489(spinner, list, spinner2, editText, serviceTransferRepository, mainActivity, editText2, str, alertDialog, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showServiceRechargeDialog$lambda$490$lambda$489(Spinner spinner, List list, Spinner spinner2, EditText editText, ServiceTransferRepository serviceTransferRepository, MainActivity mainActivity, EditText editText2, String str, AlertDialog alertDialog, View view) throws Throwable {
        try {
            String str2 = spinner.getSelectedItemPosition() == 0 ? ServiceTransferRepository.SYRIATEL_SHARED : "SHAM_CASH";
            CashAccountSummary cashAccountSummary = (CashAccountSummary) CollectionsKt.getOrNull(list, spinner2.getSelectedItemPosition());
            if (cashAccountSummary == null) {
                throw new IllegalStateException("\u0627\u062e\u062a\u0631 \u0635\u0646\u062f\u0648\u0642 \u0634\u0631\u0627\u0621 \u0627\u0644\u0631\u0635\u064a\u062f".toString());
            }
            String string = StringsKt.trim((CharSequence) editText.getText().toString()).toString();
            if (string.length() == 0) {
                string = null;
            }
            String str3 = string;
            String str4 = mainActivity.currentUserId;
            if (str4 == null) {
                throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u062f\u064a\u0631 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
            }
            long jRecharge = serviceTransferRepository.recharge(str4, str2, cashAccountSummary.getId(), mainActivity.parseSypAmount(editText2.getText().toString()), str, str3, str3);
            alertDialog.dismiss();
            Toast.makeText(mainActivity, "\u062a\u0645 \u0634\u0631\u0627\u0621 \u0627\u0644\u0631\u0635\u064a\u062f. \u0639\u0645\u0648\u0644\u0629 \u0627\u0644\u0634\u062d\u0646: " + mainActivity.formatSypAmount(jRecharge) + " \u0644.\u0633", 1).show();
            mainActivity.showServiceTransfers();
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u0634\u0631\u0627\u0621 \u0627\u0644\u0631\u0635\u064a\u062f";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void showServiceTransferDialog() throws IOException {
        MainActivity mainActivity = this;
        final ServiceTransferRepository serviceTransferRepository = new ServiceTransferRepository(mainActivity);
        final boolean zIsConfiguredSecondary = new SyncProtocolRepository(mainActivity, null, 2, 0 == true ? 1 : 0).isConfiguredSecondary();
        serviceTransferRepository.ensureSystemAccounts();
        CashAccountRepository cashAccountRepository = new CashAccountRepository(mainActivity);
        CashAccountSummary cashAccountSummaryCurrentDayPrimaryDrawer = cashAccountRepository.currentDayPrimaryDrawer();
        List<CashAccountSummary> listListActive = cashAccountRepository.listActive();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listListActive) {
            CashAccountSummary cashAccountSummary = (CashAccountSummary) obj;
            if (Intrinsics.areEqual(cashAccountSummary.getCurrencyCode(), "SYP")) {
                if (!Intrinsics.areEqual(cashAccountSummary.getAccountType(), "WALLET")) {
                    if (Intrinsics.areEqual(cashAccountSummary.getAccountType(), "CASH_BOX")) {
                        if (Intrinsics.areEqual(cashAccountSummary.getId(), cashAccountSummaryCurrentDayPrimaryDrawer != null ? cashAccountSummaryCurrentDayPrimaryDrawer.getId() : null)) {
                        }
                    }
                }
                arrayList.add(obj);
            }
        }
        final ArrayList arrayList2 = arrayList;
        if (arrayList2.isEmpty()) {
            new AlertDialog.Builder(mainActivity).setMessage("\u0639\u064a\u0651\u0646 \u062f\u0631\u062c \u0627\u0644\u064a\u0648\u0645 \u0627\u0644\u0631\u0626\u064a\u0633\u064a \u0623\u0648 \u0623\u0646\u0634\u0626 \u0645\u062d\u0641\u0638\u0629 \u0642\u0628\u0636 \u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a\u0629 \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629 \u0642\u0628\u0644 \u062a\u0646\u0641\u064a\u0630 \u0627\u0644\u062a\u062d\u0648\u064a\u0644.").setPositiveButton("\u0641\u062a\u062d \u0627\u0644\u0635\u0646\u0627\u062f\u064a\u0642 \u0648\u0627\u0644\u0645\u062d\u0627\u0641\u0638", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda48
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) throws IOException {
                    this.f$0.showCashAccounts();
                }
            }).show();
            return;
        }
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(18), dp(8), dp(18), dp(4));
        final Spinner spinner = new Spinner(mainActivity);
        spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, CollectionsKt.listOf((Object[]) new String[]{"\u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0627\u0644\u0639\u0627\u062f\u064a \u2022 \u0631\u0628\u062d 20%", "\u0633\u064a\u0631\u064a\u0627\u062a\u064a\u0644 \u0643\u0627\u0634 \u2022 \u0631\u0628\u062d 10%", "\u0634\u0627\u0645 \u0643\u0627\u0634 \u2022 \u0631\u0628\u062d 5%"})));
        final Spinner spinner2 = new Spinner(mainActivity);
        spinner2.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, zIsConfiguredSecondary ? CollectionsKt.listOf("\u0622\u062c\u0644 \u2022 \u064a\u0633\u062f\u062f \u0644\u0627\u062d\u0642\u0627\u064b") : CollectionsKt.listOf((Object[]) new String[]{"\u0646\u0642\u062f\u064a \u2022 \u0642\u0628\u0636 \u0641\u0648\u0631\u064a", "\u0622\u062c\u0644 \u2022 \u064a\u0633\u062f\u062f \u0644\u0627\u062d\u0642\u0627\u064b"})));
        final ArrayList arrayList3 = new ArrayList();
        Cursor cursorRawQuery = AppDatabase.INSTANCE.getInstance(mainActivity).getReadableDatabase().rawQuery("SELECT id,name FROM customers WHERE is_active=1 ORDER BY name COLLATE NOCASE", (String[]) null);
        try {
            Cursor cursor = cursorRawQuery;
            while (cursor.moveToNext()) {
                arrayList3.add(TuplesKt.to(cursor.getString(0), cursor.getString(1)));
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorRawQuery, null);
            final Spinner spinner3 = new Spinner(mainActivity);
            ArrayList arrayList4 = arrayList3;
            ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList4, 10));
            Iterator it = arrayList4.iterator();
            while (it.hasNext()) {
                arrayList5.add((String) ((Pair) it.next()).getSecond());
            }
            ArrayList arrayListListOf = arrayList5;
            if (arrayListListOf.isEmpty()) {
                arrayListListOf = CollectionsKt.listOf("\u0644\u0627 \u064a\u0648\u062c\u062f \u0639\u0645\u0644\u0627\u0621 \u0646\u0634\u0637\u0648\u0646");
            }
            spinner3.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, arrayListListOf));
            final TextView textViewLabel = label("\u0627\u0644\u0632\u0628\u0648\u0646 \u0627\u0644\u0645\u062f\u064a\u0646", 12.0f, this.mutedColor, false, 5);
            final EditText editTextFieldForDialog$default = fieldForDialog$default(this, "\u0627\u0644\u0645\u0628\u0644\u063a \u0627\u0644\u0645\u062e\u0635\u0648\u0645 \u0645\u0646 \u0645\u062d\u0641\u0638\u062a\u0643", null, 0, 6, null);
            editTextFieldForDialog$default.setInputType(8194);
            final EditText editTextFieldForDialog$default2 = fieldForDialog$default(this, "\u0631\u0633\u0648\u0645 \u0627\u0644\u0634\u0631\u0643\u0629 (\u062a\u064f\u062d\u0633\u0628 \u062a\u0644\u0642\u0627\u0626\u064a\u0627\u064b \u0644\u0633\u064a\u0631\u064a\u062a\u0644 \u0643\u0627\u0634)", null, 0, 6, null);
            editTextFieldForDialog$default2.setInputType(8194);
            editTextFieldForDialog$default2.setText("0");
            final Spinner spinner4 = new Spinner(mainActivity);
            ArrayList<CashAccountSummary> arrayList6 = arrayList2;
            ArrayList arrayList7 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList6, 10));
            for (CashAccountSummary cashAccountSummary2 : arrayList6) {
                arrayList7.add(cashAccountSummary2.getName() + (Intrinsics.areEqual(cashAccountSummary2.getAccountType(), "WALLET") ? " \u2022 \u0642\u0628\u0636 \u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a" : " \u2022 \u0642\u0628\u0636 \u0646\u0642\u062f\u064a"));
            }
            spinner4.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, arrayList7));
            showServiceTransferDialog$selectDefaultServiceWallet(spinner, serviceTransferRepository, arrayList2, spinner4);
            final EditText editTextFieldForDialog$default3 = fieldForDialog$default(this, "\u0631\u0642\u0645 \u0627\u0644\u0632\u0628\u0648\u0646 / \u0645\u0631\u062c\u0639 \u0627\u0644\u062a\u062d\u0648\u064a\u0644 / \u0645\u0644\u0627\u062d\u0638\u0629", null, 0, 6, null);
            final TextView textViewLabel2 = label("\u0623\u062f\u062e\u0644 \u0645\u0628\u0644\u063a \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u0644\u0627\u062d\u062a\u0633\u0627\u0628 \u0631\u0628\u062d \u0627\u0644\u0645\u062a\u062c\u0631 \u0648\u0625\u062c\u0645\u0627\u0644\u064a \u0627\u0644\u0645\u0642\u0628\u0648\u0636.", 13.0f, this.green, true, 5);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.hazem.dailystore.MainActivity.showServiceTransferDialog.3
                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onNothingSelected(AdapterView<?> parent) {
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    MainActivity.showServiceTransferDialog$selectDefaultServiceWallet(spinner, serviceTransferRepository, arrayList2, spinner4);
                    MainActivity.showServiceTransferDialog$updatePreview(spinner, editTextFieldForDialog$default2, this, editTextFieldForDialog$default, textViewLabel2);
                }
            });
            TextWatcher textWatcher = new TextWatcher() { // from class: com.hazem.dailystore.MainActivity$showServiceTransferDialog$watcher$1
                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable s) {
                }

                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    MainActivity.showServiceTransferDialog$updatePreview(spinner, editTextFieldForDialog$default2, this, editTextFieldForDialog$default, textViewLabel2);
                }
            };
            editTextFieldForDialog$default.addTextChangedListener(textWatcher);
            editTextFieldForDialog$default2.addTextChangedListener(textWatcher);
            final TextView textViewLabel3 = label("\u0627\u0633\u062a\u0644\u0627\u0645 \u0627\u0644\u0645\u0628\u0644\u063a \u0645\u0646 \u0627\u0644\u0632\u0628\u0648\u0646 \u0641\u064a", 12.0f, this.mutedColor, false, 5);
            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.hazem.dailystore.MainActivity.showServiceTransferDialog.4
                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onNothingSelected(AdapterView<?> parent) {
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    MainActivity.showServiceTransferDialog$updatePaymentModeVisibility(zIsConfiguredSecondary, spinner2, textViewLabel, spinner3, textViewLabel3, spinner4, arrayList3, this);
                }
            });
            linearLayout.addView(label("\u0646\u0648\u0639 \u0627\u0644\u062e\u062f\u0645\u0629", 12.0f, this.mutedColor, false, 5));
            linearLayout.addView(spinner, fieldParams(dp(48), dp(8)));
            linearLayout.addView(label("\u0637\u0631\u064a\u0642\u0629 \u0627\u0644\u0633\u062f\u0627\u062f", 12.0f, this.mutedColor, false, 5));
            linearLayout.addView(spinner2, fieldParams(dp(48), dp(8)));
            linearLayout.addView(textViewLabel);
            linearLayout.addView(spinner3, fieldParams(dp(48), dp(8)));
            linearLayout.addView(editTextFieldForDialog$default, fieldParams(dp(48), dp(8)));
            linearLayout.addView(editTextFieldForDialog$default2, fieldParams(dp(48), dp(8)));
            linearLayout.addView(textViewLabel2, fieldParams(-2, dp(10)));
            linearLayout.addView(textViewLabel3);
            linearLayout.addView(spinner4, fieldParams(dp(48), dp(8)));
            linearLayout.addView(editTextFieldForDialog$default3, fieldParams(dp(48), dp(8)));
            showServiceTransferDialog$updatePreview(spinner, editTextFieldForDialog$default2, this, editTextFieldForDialog$default, textViewLabel2);
            showServiceTransferDialog$updatePaymentModeVisibility(zIsConfiguredSecondary, spinner2, textViewLabel, spinner3, textViewLabel3, spinner4, arrayList3, this);
            final String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            AlertDialog.Builder title = new AlertDialog.Builder(mainActivity).setTitle("\u062a\u0646\u0641\u064a\u0630 \u062a\u062d\u0648\u064a\u0644 \u0631\u0635\u064a\u062f");
            ScrollView scrollView = new ScrollView(mainActivity);
            scrollView.addView(linearLayout);
            final AlertDialog alertDialogCreate = title.setView(scrollView).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u062a\u0646\u0641\u064a\u0630 \u0627\u0644\u062a\u062d\u0648\u064a\u0644", (DialogInterface.OnClickListener) null).create();
            alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda49
                @Override // android.content.DialogInterface.OnShowListener
                public final void onShow(DialogInterface dialogInterface) {
                    MainActivity.showServiceTransferDialog$lambda$511(alertDialogCreate, spinner, zIsConfiguredSecondary, spinner2, arrayList2, spinner4, arrayList3, spinner3, editTextFieldForDialog$default3, serviceTransferRepository, this, editTextFieldForDialog$default, editTextFieldForDialog$default2, string, dialogInterface);
                }
            });
            alertDialogCreate.show();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorRawQuery, th);
                throw th2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showServiceTransferDialog$selectDefaultServiceWallet(Spinner spinner, ServiceTransferRepository serviceTransferRepository, List<CashAccountSummary> list, Spinner spinner2) {
        String strServiceAccountId = serviceTransferRepository.serviceAccountId(spinner.getSelectedItemPosition() == 2 ? "SHAM_CASH" : ServiceTransferRepository.SYRIATEL_SHARED);
        Iterator<CashAccountSummary> it = list.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            } else if (Intrinsics.areEqual(it.next().getId(), strServiceAccountId)) {
                break;
            } else {
                i++;
            }
        }
        if (i >= 0) {
            spinner2.setSelection(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showServiceTransferDialog$updatePreview(Spinner spinner, EditText editText, MainActivity mainActivity, EditText editText2, TextView textView) {
        long j;
        Long l;
        Object objM381constructorimpl;
        Object objM381constructorimpl2;
        CharSequence charSequence;
        BigDecimal bigDecimalValueOf;
        int selectedItemPosition = spinner.getSelectedItemPosition();
        if (selectedItemPosition != 0) {
            j = selectedItemPosition != 1 ? 5L : 10L;
        } else {
            j = 20;
        }
        editText.setEnabled(spinner.getSelectedItemPosition() == 0);
        int selectedItemPosition2 = spinner.getSelectedItemPosition();
        if (selectedItemPosition2 != 1) {
            l = selectedItemPosition2 != 2 ? null : 0L;
        } else {
            l = 2L;
        }
        if (l != null && !Intrinsics.areEqual(editText.getText().toString(), l.toString())) {
            editText.setText(l.toString());
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(Long.valueOf(mainActivity.parseSypAmount(editText2.getText().toString())));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m387isFailureimpl(objM381constructorimpl)) {
            objM381constructorimpl = null;
        }
        Long l2 = (Long) objM381constructorimpl;
        if (l == null) {
            try {
                Result.Companion companion3 = Result.INSTANCE;
                objM381constructorimpl2 = Result.m381constructorimpl(Long.valueOf(mainActivity.parseSypAmount(editText.getText().toString())));
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                objM381constructorimpl2 = Result.m381constructorimpl(ResultKt.createFailure(th2));
            }
            l = (Long) (Result.m387isFailureimpl(objM381constructorimpl2) ? null : objM381constructorimpl2);
        }
        if (spinner.getSelectedItemPosition() == 0) {
            editText2.setHint("\u0627\u0644\u0645\u0628\u0644\u063a \u0627\u0644\u0645\u062e\u0635\u0648\u0645 \u0645\u0646 \u0645\u062d\u0641\u0638\u062a\u0643");
        } else {
            editText2.setHint("\u0627\u0644\u0645\u0628\u0644\u063a \u0627\u0644\u0648\u0627\u0635\u0644 \u0644\u0644\u0632\u0628\u0648\u0646");
        }
        if (l2 != null && l2.longValue() > 0 && l != null) {
            if (spinner.getSelectedItemPosition() == 0) {
                bigDecimalValueOf = BigDecimal.valueOf(l2.longValue()).multiply(BigDecimal.valueOf(4134L)).divide(BigDecimal.valueOf(4300L), 3, RoundingMode.HALF_UP);
            } else {
                bigDecimalValueOf = BigDecimal.valueOf(l2.longValue());
            }
            BigDecimal bigDecimalDivide = bigDecimalValueOf.multiply(BigDecimal.valueOf(j)).divide(BigDecimal.valueOf(100L), 3, RoundingMode.HALF_UP);
            long jLongValueExact = bigDecimalValueOf.add(bigDecimalDivide).add(BigDecimal.valueOf(l.longValue())).setScale(0, RoundingMode.HALF_UP).longValueExact();
            charSequence = "\u0627\u0644\u0648\u0627\u0635\u0644 \u0644\u0644\u0632\u0628\u0648\u0646: " + MainActivity$$ExternalSyntheticBackport0.m(bigDecimalValueOf).toPlainString() + " \u0644.\u0633\n\u0631\u0628\u062d \u0627\u0644\u0645\u062a\u062c\u0631 (" + j + "%): " + MainActivity$$ExternalSyntheticBackport0.m(bigDecimalDivide).toPlainString() + " \u0644.\u0633\n\u0631\u0633\u0648\u0645 \u0627\u0644\u0634\u0631\u0643\u0629: " + mainActivity.formatSypAmount(l.longValue()) + " \u0644.\u0633\n\u0627\u0644\u0645\u0628\u0644\u063a \u0627\u0644\u0645\u0637\u0644\u0648\u0628 \u0645\u0646 \u0627\u0644\u0632\u0628\u0648\u0646 \u0628\u0639\u062f \u0627\u0644\u062a\u0642\u0631\u064a\u0628: " + mainActivity.formatSypAmount(jLongValueExact) + " \u0644.\u0633";
        }
        textView.setText(charSequence);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showServiceTransferDialog$updatePaymentModeVisibility(boolean z, Spinner spinner, TextView textView, Spinner spinner2, TextView textView2, Spinner spinner3, List<Pair<String, String>> list, MainActivity mainActivity) {
        boolean z2 = z || spinner.getSelectedItemPosition() == 1;
        textView.setVisibility(z2 ? 0 : 8);
        spinner2.setVisibility(z2 ? 0 : 8);
        textView2.setVisibility(z2 ? 8 : 0);
        spinner3.setVisibility(z2 ? 8 : 0);
        if (z2 && list.isEmpty()) {
            Toast.makeText(mainActivity, "\u0623\u0636\u0641 \u0645\u0644\u0641 \u0627\u0644\u0632\u0628\u0648\u0646 \u0623\u0648\u0644\u0627\u064b \u0642\u0628\u0644 \u062a\u0633\u062c\u064a\u0644 \u062a\u062d\u0648\u064a\u0644 \u0622\u062c\u0644.", 1).show();
            spinner.setSelection(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showServiceTransferDialog$lambda$511(final AlertDialog alertDialog, final Spinner spinner, final boolean z, final Spinner spinner2, final List list, final Spinner spinner3, final List list2, final Spinner spinner4, final EditText editText, final ServiceTransferRepository serviceTransferRepository, final MainActivity mainActivity, final EditText editText2, final EditText editText3, final String str, DialogInterface dialogInterface) {
        alertDialog.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda165
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Throwable {
                MainActivity.showServiceTransferDialog$lambda$511$lambda$510(spinner, z, spinner2, list, spinner3, list2, spinner4, editText, serviceTransferRepository, mainActivity, editText2, editText3, str, alertDialog, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:17:0x0028  */
    /* JADX WARN: Code duplicated, block: B:20:0x002d  */
    /* JADX WARN: Code duplicated, block: B:21:0x002f A[Catch: Exception -> 0x01ba, TryCatch #0 {Exception -> 0x01ba, blocks: (B:3:0x0009, B:13:0x001e, B:24:0x003f, B:30:0x005e, B:33:0x0079, B:39:0x008a, B:41:0x0096, B:43:0x009b, B:49:0x00be, B:51:0x00c9, B:54:0x00e8, B:67:0x013e, B:55:0x010f, B:68:0x01a2, B:69:0x01ad, B:40:0x008f, B:27:0x0050, B:28:0x005b, B:21:0x002f, B:70:0x01ae, B:71:0x01b9), top: B:78:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:24:0x003f A[Catch: Exception -> 0x01ba, TryCatch #0 {Exception -> 0x01ba, blocks: (B:3:0x0009, B:13:0x001e, B:24:0x003f, B:30:0x005e, B:33:0x0079, B:39:0x008a, B:41:0x0096, B:43:0x009b, B:49:0x00be, B:51:0x00c9, B:54:0x00e8, B:67:0x013e, B:55:0x010f, B:68:0x01a2, B:69:0x01ad, B:40:0x008f, B:27:0x0050, B:28:0x005b, B:21:0x002f, B:70:0x01ae, B:71:0x01b9), top: B:78:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:26:0x004d  */
    /* JADX WARN: Code duplicated, block: B:27:0x0050 A[Catch: Exception -> 0x01ba, TryCatch #0 {Exception -> 0x01ba, blocks: (B:3:0x0009, B:13:0x001e, B:24:0x003f, B:30:0x005e, B:33:0x0079, B:39:0x008a, B:41:0x0096, B:43:0x009b, B:49:0x00be, B:51:0x00c9, B:54:0x00e8, B:67:0x013e, B:55:0x010f, B:68:0x01a2, B:69:0x01ad, B:40:0x008f, B:27:0x0050, B:28:0x005b, B:21:0x002f, B:70:0x01ae, B:71:0x01b9), top: B:78:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:29:0x005c  */
    /* JADX WARN: Code duplicated, block: B:32:0x0078  */
    /* JADX WARN: Code duplicated, block: B:35:0x0083  */
    /* JADX WARN: Code duplicated, block: B:36:0x0084  */
    /* JADX WARN: Code duplicated, block: B:39:0x008a A[Catch: Exception -> 0x01ba, TryCatch #0 {Exception -> 0x01ba, blocks: (B:3:0x0009, B:13:0x001e, B:24:0x003f, B:30:0x005e, B:33:0x0079, B:39:0x008a, B:41:0x0096, B:43:0x009b, B:49:0x00be, B:51:0x00c9, B:54:0x00e8, B:67:0x013e, B:55:0x010f, B:68:0x01a2, B:69:0x01ad, B:40:0x008f, B:27:0x0050, B:28:0x005b, B:21:0x002f, B:70:0x01ae, B:71:0x01b9), top: B:78:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:40:0x008f A[Catch: Exception -> 0x01ba, TryCatch #0 {Exception -> 0x01ba, blocks: (B:3:0x0009, B:13:0x001e, B:24:0x003f, B:30:0x005e, B:33:0x0079, B:39:0x008a, B:41:0x0096, B:43:0x009b, B:49:0x00be, B:51:0x00c9, B:54:0x00e8, B:67:0x013e, B:55:0x010f, B:68:0x01a2, B:69:0x01ad, B:40:0x008f, B:27:0x0050, B:28:0x005b, B:21:0x002f, B:70:0x01ae, B:71:0x01b9), top: B:78:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:43:0x009b A[Catch: Exception -> 0x01ba, TryCatch #0 {Exception -> 0x01ba, blocks: (B:3:0x0009, B:13:0x001e, B:24:0x003f, B:30:0x005e, B:33:0x0079, B:39:0x008a, B:41:0x0096, B:43:0x009b, B:49:0x00be, B:51:0x00c9, B:54:0x00e8, B:67:0x013e, B:55:0x010f, B:68:0x01a2, B:69:0x01ad, B:40:0x008f, B:27:0x0050, B:28:0x005b, B:21:0x002f, B:70:0x01ae, B:71:0x01b9), top: B:78:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:45:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:46:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:49:0x00be A[Catch: Exception -> 0x01ba, TryCatch #0 {Exception -> 0x01ba, blocks: (B:3:0x0009, B:13:0x001e, B:24:0x003f, B:30:0x005e, B:33:0x0079, B:39:0x008a, B:41:0x0096, B:43:0x009b, B:49:0x00be, B:51:0x00c9, B:54:0x00e8, B:67:0x013e, B:55:0x010f, B:68:0x01a2, B:69:0x01ad, B:40:0x008f, B:27:0x0050, B:28:0x005b, B:21:0x002f, B:70:0x01ae, B:71:0x01b9), top: B:78:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:50:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:54:0x00e8 A[Catch: Exception -> 0x01ba, TRY_ENTER, TryCatch #0 {Exception -> 0x01ba, blocks: (B:3:0x0009, B:13:0x001e, B:24:0x003f, B:30:0x005e, B:33:0x0079, B:39:0x008a, B:41:0x0096, B:43:0x009b, B:49:0x00be, B:51:0x00c9, B:54:0x00e8, B:67:0x013e, B:55:0x010f, B:68:0x01a2, B:69:0x01ad, B:40:0x008f, B:27:0x0050, B:28:0x005b, B:21:0x002f, B:70:0x01ae, B:71:0x01b9), top: B:78:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:55:0x010f A[Catch: Exception -> 0x01ba, TryCatch #0 {Exception -> 0x01ba, blocks: (B:3:0x0009, B:13:0x001e, B:24:0x003f, B:30:0x005e, B:33:0x0079, B:39:0x008a, B:41:0x0096, B:43:0x009b, B:49:0x00be, B:51:0x00c9, B:54:0x00e8, B:67:0x013e, B:55:0x010f, B:68:0x01a2, B:69:0x01ad, B:40:0x008f, B:27:0x0050, B:28:0x005b, B:21:0x002f, B:70:0x01ae, B:71:0x01b9), top: B:78:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:60:0x012d  */
    /* JADX WARN: Code duplicated, block: B:61:0x0130 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:62:0x0132  */
    /* JADX WARN: Code duplicated, block: B:63:0x0135  */
    /* JADX WARN: Code duplicated, block: B:65:0x0139  */
    /* JADX WARN: Code duplicated, block: B:66:0x013c  */
    /* JADX WARN: Code duplicated, block: B:68:0x01a2 A[Catch: Exception -> 0x01ba, TryCatch #0 {Exception -> 0x01ba, blocks: (B:3:0x0009, B:13:0x001e, B:24:0x003f, B:30:0x005e, B:33:0x0079, B:39:0x008a, B:41:0x0096, B:43:0x009b, B:49:0x00be, B:51:0x00c9, B:54:0x00e8, B:67:0x013e, B:55:0x010f, B:68:0x01a2, B:69:0x01ad, B:40:0x008f, B:27:0x0050, B:28:0x005b, B:21:0x002f, B:70:0x01ae, B:71:0x01b9), top: B:78:0x0009 }] */
    /* JADX WARN: Code duplicated, block: B:70:0x01ae A[Catch: Exception -> 0x01ba, TryCatch #0 {Exception -> 0x01ba, blocks: (B:3:0x0009, B:13:0x001e, B:24:0x003f, B:30:0x005e, B:33:0x0079, B:39:0x008a, B:41:0x0096, B:43:0x009b, B:49:0x00be, B:51:0x00c9, B:54:0x00e8, B:67:0x013e, B:55:0x010f, B:68:0x01a2, B:69:0x01ad, B:40:0x008f, B:27:0x0050, B:28:0x005b, B:21:0x002f, B:70:0x01ae, B:71:0x01b9), top: B:78:0x0009 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:54:0x00e8, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:55:0x010f, please report this as an issue */
    public static final void showServiceTransferDialog$lambda$511$lambda$510(Spinner spinner, boolean z, Spinner spinner2, List list, Spinner spinner3, List list2, Spinner spinner4, EditText editText, ServiceTransferRepository serviceTransferRepository, final MainActivity mainActivity, EditText editText2, EditText editText3, String str, AlertDialog alertDialog, View view) throws Throwable {
        String str2;
        String str3;
        boolean z2;
        boolean z3;
        CashAccountSummary cashAccountSummary;
        Pair pair;
        String string;
        String str4;
        String id;
        String str5;
        String str6;
        String str7;
        String str8;
        ServiceTransferResult serviceTransferResultExecuteTransfer;
        String str9;
        String str10;
        String str11;
        Pair pair2;
        try {
            int selectedItemPosition = spinner.getSelectedItemPosition();
            String str12 = "SHAM_CASH";
            if (selectedItemPosition != 0) {
                if (selectedItemPosition != 1) {
                    str3 = "SHAM_CASH";
                } else {
                    str2 = ServiceTransferRepository.SYRIATEL_CASH;
                }
                z2 = false;
                if (!z || spinner2.getSelectedItemPosition() == 1) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3) {
                    cashAccountSummary = null;
                } else {
                    cashAccountSummary = (CashAccountSummary) CollectionsKt.getOrNull(list, spinner3.getSelectedItemPosition());
                    if (cashAccountSummary == null) {
                        throw new IllegalStateException("\u0627\u062e\u062a\u0631 \u062d\u0633\u0627\u0628 \u0627\u0633\u062a\u0644\u0627\u0645 \u0627\u0644\u0645\u0628\u0644\u063a".toString());
                    }
                }
                if (z3) {
                    pair2 = (Pair) CollectionsKt.getOrNull(list2, spinner4.getSelectedItemPosition());
                    if (pair2 != null) {
                        throw new IllegalStateException("\u0627\u062e\u062a\u0631 \u0627\u0644\u0632\u0628\u0648\u0646".toString());
                    }
                    pair = pair2;
                } else {
                    pair = null;
                }
                string = StringsKt.trim((CharSequence) editText.getText().toString()).toString();
                if (string.length() == 0) {
                    string = null;
                }
                str4 = string;
                if (Intrinsics.areEqual(str3, "SHAM_CASH")) {
                    str12 = ServiceTransferRepository.SYRIATEL_SHARED;
                }
                if (z3) {
                    id = serviceTransferRepository.serviceAccountId(str12);
                } else {
                    Intrinsics.checkNotNull(cashAccountSummary);
                    id = cashAccountSummary.getId();
                }
                str5 = id;
                str6 = mainActivity.currentUserId;
                if (str6 != null) {
                    throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
                }
                long sypAmount = mainActivity.parseSypAmount(editText2.getText().toString());
                long sypAmount2 = mainActivity.parseSypAmount(editText3.getText().toString());
                if (z3) {
                    str7 = "CREDIT";
                } else {
                    str7 = "CASH";
                }
                String str13 = str7;
                if (pair != null) {
                    str8 = (String) pair.getFirst();
                } else {
                    str8 = null;
                }
                serviceTransferResultExecuteTransfer = serviceTransferRepository.executeTransfer(str6, str3, sypAmount, sypAmount2, str5, str, (9984 & 64) != 0 ? null : str4, (9984 & 128) != 0 ? null : str4, (9984 & 256) != 0 ? null : null, (9984 & 512) != 0 ? null : null, (9984 & 1024) != 0 ? false : false, (9984 & 2048) != 0 ? "CASH" : str13, (9984 & 4096) != 0 ? null : str8, (9984 & 8192) != 0 ? false : false);
                alertDialog.dismiss();
                if (z3) {
                    Intrinsics.checkNotNull(pair);
                    str9 = "\u0637\u0631\u064a\u0642\u0629 \u0627\u0644\u0633\u062f\u0627\u062f: \u0622\u062c\u0644\n\u0627\u0644\u0632\u0628\u0648\u0646: " + pair.getSecond() + "\n\u0627\u0644\u0645\u0628\u0644\u063a \u0627\u0644\u0645\u0633\u062a\u062d\u0642: " + mainActivity.formatSypAmount(serviceTransferResultExecuteTransfer.getCustomerChargeMinor()) + " \u0644.\u0633";
                } else {
                    str9 = "\u0627\u0644\u0645\u0642\u0628\u0648\u0636 \u0645\u0646 \u0627\u0644\u0632\u0628\u0648\u0646: " + mainActivity.formatSypAmount(serviceTransferResultExecuteTransfer.getCustomerChargeMinor()) + " \u0644.\u0633";
                }
                if (z && z3) {
                    z2 = true;
                }
                if (z2) {
                    str10 = "\u062a\u0645 \u062d\u0641\u0638 \u0637\u0644\u0628 \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u0644\u0644\u0645\u0632\u0627\u0645\u0646\u0629";
                } else if (z3) {
                    str10 = "\u062a\u0645 \u062a\u0646\u0641\u064a\u0630 \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u0648\u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062f\u064a\u0646";
                } else {
                    str10 = "\u062a\u0645 \u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062a\u062d\u0648\u064a\u0644";
                }
                if (z2) {
                    str11 = "\n\n\u0627\u0644\u0637\u0644\u0628 \u0645\u062d\u0641\u0648\u0638 \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u0648\u0633\u064a\u064f\u0631\u0633\u0644 \u0625\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a. \u0644\u0645 \u064a\u064f\u0631\u062d\u0651\u0644 \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u0623\u0648 \u064a\u064f\u0646\u0634\u0623 \u0627\u0644\u062f\u064a\u0646 \u0646\u0647\u0627\u0626\u064a\u0627\u064b \u0628\u0639\u062f\u061b \u064a\u0639\u062a\u0645\u062f \u0630\u0644\u0643 \u0639\u0644\u0649 \u062a\u062d\u0642\u0642 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0645\u0646 \u0627\u0644\u0631\u0635\u064a\u062f \u0648\u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a.";
                } else {
                    str11 = "";
                }
                new AlertDialog.Builder(mainActivity).setTitle(str10).setMessage("\u0627\u0644\u0648\u0627\u0635\u0644 \u0644\u0644\u0632\u0628\u0648\u0646: " + mainActivity.formatSypAmount(serviceTransferResultExecuteTransfer.getDeliveredMinor()) + " \u0644.\u0633\n\u0631\u0628\u062d \u0627\u0644\u0645\u062a\u062c\u0631: " + mainActivity.formatSypAmount(serviceTransferResultExecuteTransfer.getProfitMinor()) + " \u0644.\u0633\n\u0631\u0633\u0648\u0645 \u0627\u0644\u0634\u0631\u0643\u0629: " + mainActivity.formatSypAmount(serviceTransferResultExecuteTransfer.getProviderFeeMinor()) + " \u0644.\u0633\n" + str9 + str11).setPositiveButton("\u062d\u0633\u0646\u0627\u064b", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda72
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) throws IOException {
                        this.f$0.showServiceTransfers();
                    }
                }).show();
            }
            str2 = ServiceTransferRepository.SYRIATEL_BALANCE;
            str3 = str2;
            z2 = false;
            if (z) {
                z3 = true;
            } else {
                z3 = true;
            }
            if (z3) {
                cashAccountSummary = null;
            } else {
                cashAccountSummary = (CashAccountSummary) CollectionsKt.getOrNull(list, spinner3.getSelectedItemPosition());
                if (cashAccountSummary == null) {
                    throw new IllegalStateException("\u0627\u062e\u062a\u0631 \u062d\u0633\u0627\u0628 \u0627\u0633\u062a\u0644\u0627\u0645 \u0627\u0644\u0645\u0628\u0644\u063a".toString());
                }
            }
            if (z3) {
                pair2 = (Pair) CollectionsKt.getOrNull(list2, spinner4.getSelectedItemPosition());
                if (pair2 != null) {
                    throw new IllegalStateException("\u0627\u062e\u062a\u0631 \u0627\u0644\u0632\u0628\u0648\u0646".toString());
                }
                pair = pair2;
            } else {
                pair = null;
            }
            string = StringsKt.trim((CharSequence) editText.getText().toString()).toString();
            if (string.length() == 0) {
                string = null;
            }
            str4 = string;
            if (Intrinsics.areEqual(str3, "SHAM_CASH")) {
                str12 = ServiceTransferRepository.SYRIATEL_SHARED;
            }
            if (z3) {
                id = serviceTransferRepository.serviceAccountId(str12);
            } else {
                Intrinsics.checkNotNull(cashAccountSummary);
                id = cashAccountSummary.getId();
            }
            str5 = id;
            str6 = mainActivity.currentUserId;
            if (str6 != null) {
                throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
            }
            long sypAmount3 = mainActivity.parseSypAmount(editText2.getText().toString());
            long sypAmount4 = mainActivity.parseSypAmount(editText3.getText().toString());
            if (z3) {
                str7 = "CREDIT";
            } else {
                str7 = "CASH";
            }
            String str14 = str7;
            if (pair != null) {
                str8 = (String) pair.getFirst();
            } else {
                str8 = null;
            }
            serviceTransferResultExecuteTransfer = serviceTransferRepository.executeTransfer(str6, str3, sypAmount3, sypAmount4, str5, str, (9984 & 64) != 0 ? null : str4, (9984 & 128) != 0 ? null : str4, (9984 & 256) != 0 ? null : null, (9984 & 512) != 0 ? null : null, (9984 & 1024) != 0 ? false : false, (9984 & 2048) != 0 ? "CASH" : str14, (9984 & 4096) != 0 ? null : str8, (9984 & 8192) != 0 ? false : false);
            alertDialog.dismiss();
            if (z3) {
                Intrinsics.checkNotNull(pair);
                str9 = "\u0637\u0631\u064a\u0642\u0629 \u0627\u0644\u0633\u062f\u0627\u062f: \u0622\u062c\u0644\n\u0627\u0644\u0632\u0628\u0648\u0646: " + pair.getSecond() + "\n\u0627\u0644\u0645\u0628\u0644\u063a \u0627\u0644\u0645\u0633\u062a\u062d\u0642: " + mainActivity.formatSypAmount(serviceTransferResultExecuteTransfer.getCustomerChargeMinor()) + " \u0644.\u0633";
            } else {
                str9 = "\u0627\u0644\u0645\u0642\u0628\u0648\u0636 \u0645\u0646 \u0627\u0644\u0632\u0628\u0648\u0646: " + mainActivity.formatSypAmount(serviceTransferResultExecuteTransfer.getCustomerChargeMinor()) + " \u0644.\u0633";
            }
            if (z) {
                z2 = true;
            }
            if (z2) {
                str10 = "\u062a\u0645 \u062d\u0641\u0638 \u0637\u0644\u0628 \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u0644\u0644\u0645\u0632\u0627\u0645\u0646\u0629";
            } else if (z3) {
                str10 = "\u062a\u0645 \u062a\u0646\u0641\u064a\u0630 \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u0648\u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062f\u064a\u0646";
            } else {
                str10 = "\u062a\u0645 \u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062a\u062d\u0648\u064a\u0644";
            }
            if (z2) {
                str11 = "\n\n\u0627\u0644\u0637\u0644\u0628 \u0645\u062d\u0641\u0648\u0638 \u0639\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u062b\u0627\u0646\u0648\u064a \u0648\u0633\u064a\u064f\u0631\u0633\u0644 \u0625\u0644\u0649 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a. \u0644\u0645 \u064a\u064f\u0631\u062d\u0651\u0644 \u0627\u0644\u062a\u062d\u0648\u064a\u0644 \u0623\u0648 \u064a\u064f\u0646\u0634\u0623 \u0627\u0644\u062f\u064a\u0646 \u0646\u0647\u0627\u0626\u064a\u0627\u064b \u0628\u0639\u062f\u061b \u064a\u0639\u062a\u0645\u062f \u0630\u0644\u0643 \u0639\u0644\u0649 \u062a\u062d\u0642\u0642 \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a \u0645\u0646 \u0627\u0644\u0631\u0635\u064a\u062f \u0648\u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a.";
            } else {
                str11 = "";
            }
            new AlertDialog.Builder(mainActivity).setTitle(str10).setMessage("\u0627\u0644\u0648\u0627\u0635\u0644 \u0644\u0644\u0632\u0628\u0648\u0646: " + mainActivity.formatSypAmount(serviceTransferResultExecuteTransfer.getDeliveredMinor()) + " \u0644.\u0633\n\u0631\u0628\u062d \u0627\u0644\u0645\u062a\u062c\u0631: " + mainActivity.formatSypAmount(serviceTransferResultExecuteTransfer.getProfitMinor()) + " \u0644.\u0633\n\u0631\u0633\u0648\u0645 \u0627\u0644\u0634\u0631\u0643\u0629: " + mainActivity.formatSypAmount(serviceTransferResultExecuteTransfer.getProviderFeeMinor()) + " \u0644.\u0633\n" + str9 + str11).setPositiveButton("\u062d\u0633\u0646\u0627\u064b", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda72
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) throws IOException {
                    this.f$0.showServiceTransfers();
                }
            }).show();
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u062a\u0646\u0641\u064a\u0630 \u0627\u0644\u062a\u062d\u0648\u064a\u0644";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    private final void showEditCashAccountDialog(final CashAccountSummary account, final Function0<Unit> onSaved) {
        if (!isManager()) {
            Toast.makeText(this, "\u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u062d\u0633\u0627\u0628\u0627\u062a \u0627\u0644\u0645\u0627\u0644\u064a\u0629 \u0645\u062a\u0627\u062d \u0644\u0644\u0645\u062f\u064a\u0631 \u0641\u0642\u0637", 0).show();
            return;
        }
        MainActivity mainActivity = this;
        final CashAccountRepository cashAccountRepository = new CashAccountRepository(mainActivity);
        boolean zHasTransactions = cashAccountRepository.hasTransactions(account.getId());
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(18), dp(8), dp(18), dp(4));
        final EditText editTextFieldForDialog$default = fieldForDialog$default(this, "\u0627\u0633\u0645 \u0627\u0644\u062d\u0633\u0627\u0628", null, 0, 6, null);
        editTextFieldForDialog$default.setText(account.getName());
        final Spinner spinner = new Spinner(mainActivity);
        List listListOf = CollectionsKt.listOf((Object[]) new String[]{"\u0635\u0646\u062f\u0648\u0642 \u0646\u0642\u062f\u064a", "\u0645\u062d\u0641\u0638\u0629 \u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a\u0629", "\u062d\u0633\u0627\u0628 \u0628\u0646\u0643\u064a", "\u0623\u062e\u0631\u0649"});
        final List listListOf2 = CollectionsKt.listOf((Object[]) new String[]{"CASH_BOX", "WALLET", "BANK", "OTHER"});
        spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, listListOf));
        spinner.setSelection(RangesKt.coerceAtLeast(listListOf2.indexOf(account.getAccountType()), 0));
        final Spinner spinner2 = new Spinner(mainActivity);
        final List listListOf3 = CollectionsKt.listOf((Object[]) new String[]{"USD", "SYP"});
        spinner2.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, listListOf3));
        spinner2.setSelection(RangesKt.coerceAtLeast(listListOf3.indexOf(account.getCurrencyCode()), 0));
        if (zHasTransactions) {
            spinner.setEnabled(false);
            spinner2.setEnabled(false);
        }
        linearLayout.addView(label("\u0627\u0633\u0645 \u0627\u0644\u062d\u0633\u0627\u0628", 12.0f, this.mutedColor, false, 5));
        linearLayout.addView(editTextFieldForDialog$default, fieldParams(dp(48), dp(8)));
        linearLayout.addView(label("\u0646\u0648\u0639 \u0627\u0644\u062d\u0633\u0627\u0628", 12.0f, this.mutedColor, false, 5));
        linearLayout.addView(spinner, fieldParams(dp(46), dp(6)));
        linearLayout.addView(label("\u0627\u0644\u0639\u0645\u0644\u0629", 12.0f, this.mutedColor, false, 5));
        linearLayout.addView(spinner2, fieldParams(dp(46), dp(6)));
        if (zHasTransactions) {
            TextView textViewLabel = label("\u0644\u0623\u0646 \u0627\u0644\u062d\u0633\u0627\u0628 \u064a\u062d\u062a\u0648\u064a \u062d\u0631\u0643\u0627\u062a \u0645\u0627\u0644\u064a\u0629 \u0633\u0627\u0628\u0642\u0629\u060c \u064a\u0645\u0643\u0646 \u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u0627\u0633\u0645 \u0641\u0642\u0637. \u0646\u0648\u0639 \u0627\u0644\u062d\u0633\u0627\u0628 \u0648\u0627\u0644\u0639\u0645\u0644\u0629 \u0645\u062d\u0645\u064a\u0627\u0646 \u062d\u062a\u0649 \u0644\u0627 \u062a\u062a\u063a\u064a\u0631 \u062f\u0644\u0627\u0644\u0629 \u0627\u0644\u062d\u0631\u0643\u0627\u062a \u0627\u0644\u062a\u0627\u0631\u064a\u062e\u064a\u0629.", 11.0f, this.gold, true, 5);
            textViewLabel.setPadding(0, dp(4), 0, dp(4));
            linearLayout.addView(textViewLabel);
        }
        new AlertDialog.Builder(mainActivity).setTitle("\u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a").setView(linearLayout).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u062d\u0641\u0638 \u0627\u0644\u062a\u0639\u062f\u064a\u0644\u0627\u062a", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda118
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.showEditCashAccountDialog$lambda$515(cashAccountRepository, this, account, editTextFieldForDialog$default, listListOf2, spinner, listListOf3, spinner2, onSaved, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showEditCashAccountDialog$lambda$515(CashAccountRepository cashAccountRepository, MainActivity mainActivity, CashAccountSummary cashAccountSummary, EditText editText, List list, Spinner spinner, List list2, Spinner spinner2, Function0 function0, DialogInterface dialogInterface, int i) {
        try {
            String str = mainActivity.currentUserRole;
            String str2 = mainActivity.currentUserId;
            if (str2 != null) {
                cashAccountRepository.updateDetails(str, str2, cashAccountSummary.getId(), editText.getText().toString(), (String) list.get(spinner.getSelectedItemPosition()), (String) list2.get(spinner2.getSelectedItemPosition()));
                Toast.makeText(mainActivity, "\u062a\u0645 \u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u062d\u0633\u0627\u0628 \u0645\u0639 \u062d\u0641\u0638 \u0633\u062c\u0644 \u0627\u0644\u062a\u062f\u0642\u064a\u0642", 0).show();
                function0.invoke();
                return;
            }
            throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u062f\u064a\u0631 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u062d\u0633\u0627\u0628";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showCreateCashAccountDialog() {
        if (!isManager()) {
            Toast.makeText(this, "\u0625\u0646\u0634\u0627\u0621 \u0627\u0644\u062d\u0633\u0627\u0628\u0627\u062a \u0627\u0644\u0645\u0627\u0644\u064a\u0629 \u0645\u062a\u0627\u062d \u0644\u0644\u0645\u062f\u064a\u0631 \u0641\u0642\u0637", 0).show();
            return;
        }
        MainActivity mainActivity = this;
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(18), dp(8), dp(18), dp(4));
        final EditText editTextFieldForDialog$default = fieldForDialog$default(this, "\u0627\u0633\u0645 \u0627\u0644\u062d\u0633\u0627\u0628", null, 0, 6, null);
        final Spinner spinner = new Spinner(mainActivity);
        spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, CollectionsKt.listOf((Object[]) new String[]{"\u0635\u0646\u062f\u0648\u0642 \u0646\u0642\u062f\u064a", "\u0645\u062d\u0641\u0638\u0629 \u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a\u0629", "\u062d\u0633\u0627\u0628 \u0628\u0646\u0643\u064a", "\u0623\u062e\u0631\u0649"})));
        final Spinner spinner2 = new Spinner(mainActivity);
        final List listListOf = CollectionsKt.listOf((Object[]) new String[]{"USD", "SYP"});
        spinner2.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, listListOf));
        final EditText editTextFieldForDialog$default2 = fieldForDialog$default(this, "\u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u0627\u0641\u062a\u062a\u0627\u062d\u064a", null, 0, 6, null);
        editTextFieldForDialog$default2.setInputType(8194);
        linearLayout.addView(editTextFieldForDialog$default, fieldParams(dp(48), dp(8)));
        linearLayout.addView(label("\u0646\u0648\u0639 \u0627\u0644\u062d\u0633\u0627\u0628", 12.0f, this.mutedColor, false, 5));
        linearLayout.addView(spinner, fieldParams(dp(46), dp(6)));
        linearLayout.addView(label("\u0627\u0644\u0639\u0645\u0644\u0629", 12.0f, this.mutedColor, false, 5));
        linearLayout.addView(spinner2, fieldParams(dp(46), dp(6)));
        linearLayout.addView(editTextFieldForDialog$default2, fieldParams(dp(48), dp(6)));
        new AlertDialog.Builder(mainActivity).setTitle("\u0625\u0636\u0627\u0641\u0629 \u062d\u0633\u0627\u0628 \u0645\u0627\u0644\u064a").setView(linearLayout).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u062d\u0641\u0638", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda57
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.showCreateCashAccountDialog$lambda$517(listListOf, spinner2, this, editTextFieldForDialog$default2, spinner, editTextFieldForDialog$default, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showCreateCashAccountDialog$lambda$517(List list, Spinner spinner, MainActivity mainActivity, EditText editText, Spinner spinner2, EditText editText2, DialogInterface dialogInterface, int i) {
        try {
            String str = (String) list.get(spinner.getSelectedItemPosition());
            long usdCents = Intrinsics.areEqual(str, "USD") ? mainActivity.parseUsdCents(editText.getText().toString()) : mainActivity.parseSypAmount(editText.getText().toString());
            String str2 = (String) CollectionsKt.listOf((Object[]) new String[]{"CASH_BOX", "WALLET", "BANK", "OTHER"}).get(spinner2.getSelectedItemPosition());
            CashAccountRepository cashAccountRepository = new CashAccountRepository(mainActivity);
            String str3 = mainActivity.currentUserId;
            if (str3 != null) {
                cashAccountRepository.create(str3, editText2.getText().toString(), str2, str, usdCents);
                Toast.makeText(mainActivity, "\u062a\u0645 \u0625\u0646\u0634\u0627\u0621 \u0627\u0644\u062d\u0633\u0627\u0628 \u0627\u0644\u0645\u0627\u0644\u064a", 0).show();
                mainActivity.showCashAccounts();
                return;
            }
            throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u062f\u064a\u0631 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u0625\u0646\u0634\u0627\u0621 \u0627\u0644\u062d\u0633\u0627\u0628";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    private final String cashAccountTypeArabic(String type) {
        int iHashCode = type.hashCode();
        if (iHashCode != -1741862919) {
            if (iHashCode != 2031164) {
                if (iHashCode == 807981727 && type.equals("CASH_BOX")) {
                    return "\u0635\u0646\u062f\u0648\u0642 \u0646\u0642\u062f\u064a";
                }
            } else if (type.equals("BANK")) {
                return "\u0628\u0646\u0643";
            }
        } else if (type.equals("WALLET")) {
            return "\u0645\u062d\u0641\u0638\u0629";
        }
        return "\u0623\u062e\u0631\u0649";
    }

    private final String formatAccountBalance(long amountMinor, String currency) {
        if (Intrinsics.areEqual(currency, "USD")) {
            return "$" + moneyText(amountMinor);
        }
        return formatSypAmount(amountMinor) + " \u0644.\u0633";
    }

    private final String invoiceStatusArabic(String status) {
        switch (status.hashCode()) {
            case -1929121473:
                return !status.equals("POSTED") ? status : "\u0645\u0639\u062a\u0645\u062f\u0629";
            case -1031784143:
                return !status.equals("CANCELLED") ? status : "\u0645\u0644\u063a\u0627\u0629";
            case -313981507:
                return !status.equals("PARTIALLY_PAID") ? status : "\u0645\u062f\u0641\u0648\u0639\u0629 \u062c\u0632\u0626\u064a\u0627\u064b";
            case 2448076:
                return !status.equals("PAID") ? status : "\u0645\u062f\u0641\u0648\u0639\u0629";
            case 65307009:
                return !status.equals("DRAFT") ? status : "\u0645\u0633\u0648\u062f\u0629";
            default:
                return status;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v25, types: [T, com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda22] */
    /* JADX WARN: Type inference failed for: r2v10, types: [T, com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda25] */
    public final void showPurchaseInvoiceEditor() {
        markScreen$default(this, "PURCHASE_EDITOR", null, 2, null);
        MainActivity mainActivity = this;
        final StoreRepository storeRepository = new StoreRepository(mainActivity);
        storeRepository.ensureBaseSetup();
        final SupplierRepository supplierRepository = new SupplierRepository(mainActivity);
        final InvoiceRepository invoiceRepository = new InvoiceRepository(mainActivity);
        final ArrayList arrayList = new ArrayList();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        final List mutableList = CollectionsKt.toMutableList((Collection) supplierRepository.listActive());
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(this.backgroundColor);
        LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        linearLayout2.setPadding(dp(14), dp(10), dp(14), dp(10));
        linearLayout2.setBackgroundColor(-1);
        Button button = new Button(mainActivity);
        button.setText("\u2039");
        button.setTextSize(25.0f);
        button.setAllCaps(false);
        button.setTextColor(this.green);
        button.setBackgroundColor(0);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda20
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IOException {
                this.f$0.showPurchases();
            }
        });
        linearLayout2.addView(button, new LinearLayout.LayoutParams(dp(48), dp(48)));
        linearLayout2.addView(label("\u0641\u0627\u062a\u0648\u0631\u0629 \u0645\u0634\u062a\u0631\u064a\u0627\u062a \u062c\u062f\u064a\u062f\u0629", 19.0f, this.green, true, 17), new LinearLayout.LayoutParams(0, -2, 1.0f));
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setFillViewport(true);
        LinearLayout linearLayout3 = new LinearLayout(mainActivity);
        linearLayout3.setOrientation(1);
        linearLayout3.setPadding(dp(16), dp(14), dp(16), dp(28));
        StoreTime storeTime = StoreTime.INSTANCE;
        SQLiteDatabase readableDatabase = AppDatabase.INSTANCE.getInstance(mainActivity).getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        final EditText editTextShowPurchaseInvoiceEditor$field$default = showPurchaseInvoiceEditor$field$default(this, "\u062a\u0627\u0631\u064a\u062e \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629", StoreTime.today$default(storeTime, readableDatabase, null, 2, null), 0, 8, null);
        final Spinner spinner = new Spinner(mainActivity);
        showPurchaseInvoiceEditor$refreshSuppliers$default(mutableList, spinner, this, null, 8, null);
        Button buttonSmallButton = smallButton("+ \u0625\u0636\u0627\u0641\u0629 \u0645\u0648\u0631\u062f", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showPurchaseInvoiceEditor$lambda$529(this.f$0, supplierRepository, mutableList, spinner);
            }
        });
        final EditText editTextShowPurchaseInvoiceEditor$field = showPurchaseInvoiceEditor$field(this, "\u062e\u0635\u0645 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0628\u0627\u0644\u062f\u0648\u0644\u0627\u0631 (\u0645\u062b\u0627\u0644 1.50)", "0", 8194);
        final EditText editTextShowPurchaseInvoiceEditor$field$default2 = showPurchaseInvoiceEditor$field$default(this, "\u0645\u0644\u0627\u062d\u0638\u0627\u062a (\u0627\u062e\u062a\u064a\u0627\u0631\u064a)", null, 0, 12, null);
        final LinearLayout linearLayout4 = new LinearLayout(mainActivity);
        linearLayout4.setOrientation(1);
        linearLayout4.setBackground(cardBackground());
        linearLayout4.setPadding(dp(10), dp(8), dp(10), dp(8));
        final TextView textViewLabel = label("\u0627\u0644\u0625\u062c\u0645\u0627\u0644\u064a: 0.00 USD", 17.0f, this.green, true, 5);
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda22
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        };
        Button button2 = new Button(mainActivity);
        button2.setText("+ \u0627\u062e\u062a\u064a\u0627\u0631 \u0635\u0646\u0641 \u0645\u0646 \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u0623\u0648 \u0627\u0644\u0643\u062a\u0627\u0644\u0648\u062c");
        button2.setAllCaps(false);
        button2.setTextColor(this.green);
        button2.setTextSize(15.0f);
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda24
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.showPurchaseInvoiceEditor$lambda$536$lambda$535(this.f$0, arrayList, objectRef2, view);
            }
        });
        objectRef2.element = new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda25
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showPurchaseInvoiceEditor$lambda$547(linearLayout4, arrayList, this, textViewLabel, storeRepository, objectRef2, editTextShowPurchaseInvoiceEditor$field);
            }
        };
        editTextShowPurchaseInvoiceEditor$field.addTextChangedListener(new TextWatcher() { // from class: com.hazem.dailystore.MainActivity.showPurchaseInvoiceEditor.2
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                objectRef2.element.invoke();
            }
        });
        linearLayout3.addView(label("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629", 20.0f, this.green, true, 5));
        linearLayout3.addView(editTextShowPurchaseInvoiceEditor$field$default, fieldParams(dp(48), dp(8)));
        linearLayout3.addView(label("\u0627\u0644\u0645\u0648\u0631\u062f", 13.0f, this.mutedColor, false, 5));
        linearLayout3.addView(spinner, fieldParams(dp(48), dp(4)));
        linearLayout3.addView(buttonSmallButton, fieldParams(dp(44), dp(12)));
        linearLayout3.addView(sectionHeading("\u0623\u0635\u0646\u0627\u0641 \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629"));
        linearLayout3.addView(button2, fieldParams(dp(50), dp(8)));
        linearLayout3.addView(linearLayout4, fieldParams(-2, dp(12)));
        linearLayout3.addView(editTextShowPurchaseInvoiceEditor$field, fieldParams(dp(48), dp(8)));
        linearLayout3.addView(textViewLabel, fieldParams(-2, dp(12)));
        linearLayout3.addView(editTextShowPurchaseInvoiceEditor$field$default2, fieldParams(dp(48), dp(12)));
        Button button3 = new Button(mainActivity);
        button3.setText("\u062d\u0641\u0638 \u0643\u0645\u0633\u0648\u062f\u0629");
        button3.setAllCaps(false);
        button3.setTextColor(this.green);
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda26
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Throwable {
                MainActivity.showPurchaseInvoiceEditor$lambda$551$lambda$550(booleanRef, spinner, mutableList, invoiceRepository, this, objectRef, editTextShowPurchaseInvoiceEditor$field$default, editTextShowPurchaseInvoiceEditor$field, editTextShowPurchaseInvoiceEditor$field$default2, arrayList, view);
            }
        });
        Button button4 = new Button(mainActivity);
        button4.setText("\u062d\u0641\u0638 \u0648\u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0648\u062a\u062d\u062f\u064a\u062b \u0627\u0644\u0645\u062e\u0632\u0648\u0646");
        button4.setAllCaps(false);
        button4.setTextColor(-1);
        button4.setBackgroundColor(this.green);
        button4.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda27
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Throwable {
                MainActivity.showPurchaseInvoiceEditor$lambda$556$lambda$555(booleanRef, spinner, mutableList, invoiceRepository, this, objectRef, editTextShowPurchaseInvoiceEditor$field$default, editTextShowPurchaseInvoiceEditor$field, editTextShowPurchaseInvoiceEditor$field$default2, arrayList, view);
            }
        });
        linearLayout3.addView(button3, fieldParams(dp(50), dp(8)));
        linearLayout3.addView(button4, fieldParams(dp(54), dp(8)));
        scrollView.addView(linearLayout3);
        linearLayout.addView(linearLayout2, matchWidth());
        linearLayout.addView(scrollView, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        setContentView(linearLayout);
        ((Function0) objectRef2.element).invoke();
    }

    static /* synthetic */ EditText showPurchaseInvoiceEditor$field$default(MainActivity mainActivity, String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str2 = "";
        }
        if ((i2 & 8) != 0) {
            i = 1;
        }
        return showPurchaseInvoiceEditor$field(mainActivity, str, str2, i);
    }

    private static final EditText showPurchaseInvoiceEditor$field(MainActivity mainActivity, String str, String str2, int i) {
        EditText editText = new EditText(mainActivity);
        editText.setHint(str);
        editText.setText(str2);
        editText.setInputType(i);
        editText.setTextSize(14.0f);
        editText.setSingleLine(true);
        editText.setPadding(mainActivity.dp(12), 0, mainActivity.dp(12), 0);
        editText.setBackground(mainActivity.inputBackground());
        return editText;
    }

    static /* synthetic */ void showPurchaseInvoiceEditor$refreshSuppliers$default(List list, Spinner spinner, MainActivity mainActivity, String str, int i, Object obj) {
        if ((i & 8) != 0) {
            str = null;
        }
        showPurchaseInvoiceEditor$refreshSuppliers(list, spinner, mainActivity, str);
    }

    private static final void showPurchaseInvoiceEditor$refreshSuppliers(List<SupplierSummary> list, Spinner spinner, MainActivity mainActivity, String str) {
        List listListOf = CollectionsKt.listOf("\u0628\u062f\u0648\u0646 \u062a\u062d\u062f\u064a\u062f \u0645\u0648\u0631\u062f");
        List<SupplierSummary> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(((SupplierSummary) it.next()).getName());
        }
        spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, CollectionsKt.plus((Collection) listListOf, (Iterable) arrayList)));
        if (str != null) {
            Iterator<SupplierSummary> it2 = list.iterator();
            int i = 0;
            while (true) {
                if (!it2.hasNext()) {
                    i = -1;
                    break;
                } else if (Intrinsics.areEqual(it2.next().getId(), str)) {
                    break;
                } else {
                    i++;
                }
            }
            if (i >= 0) {
                spinner.setSelection(i + 1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showPurchaseInvoiceEditor$lambda$529(final MainActivity mainActivity, final SupplierRepository supplierRepository, final List list, final Spinner spinner) {
        MainActivity mainActivity2 = mainActivity;
        LinearLayout linearLayout = new LinearLayout(mainActivity2);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(mainActivity.dp(18), mainActivity.dp(8), mainActivity.dp(18), 0);
        final EditText editTextShowPurchaseInvoiceEditor$field$default = showPurchaseInvoiceEditor$field$default(mainActivity, "\u0627\u0633\u0645 \u0627\u0644\u0645\u0648\u0631\u062f *", null, 0, 12, null);
        final EditText editTextShowPurchaseInvoiceEditor$field = showPurchaseInvoiceEditor$field(mainActivity, "\u0631\u0642\u0645 \u0627\u0644\u0647\u0627\u062a\u0641 (\u0627\u062e\u062a\u064a\u0627\u0631\u064a)", "", 3);
        final EditText editTextShowPurchaseInvoiceEditor$field$default2 = showPurchaseInvoiceEditor$field$default(mainActivity, "\u0627\u0644\u0639\u0646\u0648\u0627\u0646 (\u0627\u062e\u062a\u064a\u0627\u0631\u064a)", null, 0, 12, null);
        linearLayout.addView(editTextShowPurchaseInvoiceEditor$field$default, mainActivity.fieldParams(mainActivity.dp(48), mainActivity.dp(8)));
        linearLayout.addView(editTextShowPurchaseInvoiceEditor$field, mainActivity.fieldParams(mainActivity.dp(48), mainActivity.dp(8)));
        linearLayout.addView(editTextShowPurchaseInvoiceEditor$field$default2, mainActivity.fieldParams(mainActivity.dp(48), mainActivity.dp(8)));
        new AlertDialog.Builder(mainActivity2).setTitle("\u0625\u0636\u0627\u0641\u0629 \u0645\u0648\u0631\u062f").setView(linearLayout).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u062d\u0641\u0638", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda59
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.showPurchaseInvoiceEditor$lambda$529$lambda$528(supplierRepository, editTextShowPurchaseInvoiceEditor$field$default, editTextShowPurchaseInvoiceEditor$field, editTextShowPurchaseInvoiceEditor$field$default2, list, mainActivity, spinner, dialogInterface, i);
            }
        }).show();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showPurchaseInvoiceEditor$lambda$529$lambda$528(SupplierRepository supplierRepository, EditText editText, EditText editText2, EditText editText3, List list, MainActivity mainActivity, Spinner spinner, DialogInterface dialogInterface, int i) {
        try {
            SupplierSummary supplierSummaryCreate$default = SupplierRepository.create$default(supplierRepository, editText.getText().toString(), editText2.getText().toString(), editText3.getText().toString(), null, 8, null);
            list.add(supplierSummaryCreate$default);
            showPurchaseInvoiceEditor$refreshSuppliers(list, spinner, mainActivity, supplierSummaryCreate$default.getId());
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u062d\u0641\u0638 \u0627\u0644\u0645\u0648\u0631\u062f";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showPurchaseInvoiceEditor$lambda$536$lambda$535(final MainActivity mainActivity, final List list, final Ref.ObjectRef objectRef, View view) {
        mainActivity.showPurchaseProductPicker(new Function1() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda171
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.showPurchaseInvoiceEditor$lambda$536$lambda$535$lambda$534(this.f$0, list, objectRef, (StoreProduct) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showPurchaseInvoiceEditor$lambda$536$lambda$535$lambda$534(MainActivity mainActivity, final List list, final Ref.ObjectRef objectRef, final StoreProduct product) {
        Intrinsics.checkNotNullParameter(product, "product");
        showPurchaseLineDialog$default(mainActivity, product, null, 0L, new Function2() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda46
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return MainActivity.showPurchaseInvoiceEditor$lambda$536$lambda$535$lambda$534$lambda$533(list, product, objectRef, (BigDecimal) obj, ((Long) obj2).longValue());
            }
        }, 6, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showPurchaseInvoiceEditor$lambda$536$lambda$535$lambda$534$lambda$533(List list, StoreProduct storeProduct, Ref.ObjectRef objectRef, BigDecimal qty, long j) {
        Object next;
        Intrinsics.checkNotNullParameter(qty, "qty");
        Iterator it = list.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((PurchaseLineDraft) next).getProduct().getId(), storeProduct.getId()));
        PurchaseLineDraft purchaseLineDraft = (PurchaseLineDraft) next;
        if (purchaseLineDraft == null) {
            list.add(new PurchaseLineDraft(storeProduct, qty, j));
        } else {
            purchaseLineDraft.setQuantity(purchaseLineDraft.getQuantity().add(qty));
            purchaseLineDraft.setUnitCostUsdCents(j);
        }
        ((Function0) objectRef.element).invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showPurchaseInvoiceEditor$lambda$547(LinearLayout linearLayout, final List list, final MainActivity mainActivity, TextView textView, StoreRepository storeRepository, final Ref.ObjectRef objectRef, EditText editText) {
        Object objM381constructorimpl;
        linearLayout.removeAllViews();
        int i = 0;
        if (list.isEmpty()) {
            TextView textViewLabel = mainActivity.label("\u0644\u0645 \u062a\u062a\u0645 \u0625\u0636\u0627\u0641\u0629 \u0623\u0635\u0646\u0627\u0641 \u0628\u0639\u062f.", 13.0f, mainActivity.mutedColor, false, 17);
            textViewLabel.setPadding(0, mainActivity.dp(22), 0, mainActivity.dp(22));
            linearLayout.addView(textViewLabel);
        }
        List<PurchaseLineDraft> list2 = list;
        Iterator it = list2.iterator();
        final int i2 = 0;
        while (it.hasNext()) {
            Object next = it.next();
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            final PurchaseLineDraft purchaseLineDraft = (PurchaseLineDraft) next;
            MainActivity mainActivity2 = mainActivity;
            LinearLayout linearLayout2 = new LinearLayout(mainActivity2);
            linearLayout2.setOrientation(i);
            linearLayout2.setGravity(16);
            linearLayout2.setPadding(i, mainActivity.dp(8), i, mainActivity.dp(8));
            LinearLayout linearLayout3 = new LinearLayout(mainActivity2);
            linearLayout3.setOrientation(1);
            linearLayout3.addView(mainActivity.label(purchaseLineDraft.getProduct().getName(), 14.0f, mainActivity.green, true, 5));
            linearLayout3.addView(mainActivity.label(MainActivity$$ExternalSyntheticBackport0.m(purchaseLineDraft.getQuantity()).toPlainString() + " " + purchaseLineDraft.getProduct().getUnitName() + " \u00d7 " + mainActivity.moneyText(purchaseLineDraft.getUnitCostUsdCents()) + " USD", 12.0f, mainActivity.mutedColor, false, 5));
            linearLayout2.addView(linearLayout3, new LinearLayout.LayoutParams(0, -2, 1.0f));
            linearLayout2.addView(mainActivity.smallButton("\u062a\u0639\u062f\u064a\u0644", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda108
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MainActivity.showPurchaseInvoiceEditor$lambda$547$lambda$544$lambda$541(this.f$0, purchaseLineDraft, objectRef);
                }
            }), new LinearLayout.LayoutParams(mainActivity.dp(68), mainActivity.dp(42)));
            linearLayout2.addView(mainActivity.smallButton("\u062d\u0630\u0641", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda110
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MainActivity.showPurchaseInvoiceEditor$lambda$547$lambda$544$lambda$542(list, i2, objectRef);
                }
            }), new LinearLayout.LayoutParams(mainActivity.dp(58), mainActivity.dp(42)));
            linearLayout.addView(linearLayout2);
            View view = new View(mainActivity2);
            view.setBackgroundColor(Color.rgb(232, 235, 231));
            linearLayout.addView(view, new LinearLayout.LayoutParams(-1, mainActivity.dp(1)));
            i2 = i3;
            it = it;
            i = 0;
        }
        BigDecimal bigDecimalAdd = BigDecimal.ZERO;
        for (PurchaseLineDraft purchaseLineDraft2 : list2) {
            bigDecimalAdd = bigDecimalAdd.add(purchaseLineDraft2.getQuantity().multiply(BigDecimal.valueOf(purchaseLineDraft2.getUnitCostUsdCents())));
        }
        long jLongValueExact = bigDecimalAdd.setScale(0, RoundingMode.HALF_UP).longValueExact();
        try {
            Result.Companion companion = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(Long.valueOf(mainActivity.parseUsdCents(editText.getText().toString())));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m387isFailureimpl(objM381constructorimpl)) {
            objM381constructorimpl = 0L;
        }
        long jCoerceAtLeast = RangesKt.coerceAtLeast(jLongValueExact - ((Number) objM381constructorimpl).longValue(), 0L);
        textView.setText("\u0627\u0644\u0625\u062c\u0645\u0627\u0644\u064a \u0642\u0628\u0644 \u0627\u0644\u062e\u0635\u0645: " + mainActivity.moneyText(jLongValueExact) + " USD\n\u0627\u0644\u0635\u0627\u0641\u064a: " + mainActivity.formatDualMoney(jCoerceAtLeast, storeRepository.getCurrentExchangeRateMilli()));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showPurchaseInvoiceEditor$lambda$547$lambda$544$lambda$541(MainActivity mainActivity, final PurchaseLineDraft purchaseLineDraft, final Ref.ObjectRef objectRef) {
        mainActivity.showPurchaseLineDialog(purchaseLineDraft.getProduct(), purchaseLineDraft.getQuantity(), purchaseLineDraft.getUnitCostUsdCents(), new Function2() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda36
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return MainActivity.showPurchaseInvoiceEditor$lambda$547$lambda$544$lambda$541$lambda$540(purchaseLineDraft, objectRef, (BigDecimal) obj, ((Long) obj2).longValue());
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showPurchaseInvoiceEditor$lambda$547$lambda$544$lambda$541$lambda$540(PurchaseLineDraft purchaseLineDraft, Ref.ObjectRef objectRef, BigDecimal qty, long j) {
        Intrinsics.checkNotNullParameter(qty, "qty");
        purchaseLineDraft.setQuantity(qty);
        purchaseLineDraft.setUnitCostUsdCents(j);
        ((Function0) objectRef.element).invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showPurchaseInvoiceEditor$lambda$547$lambda$544$lambda$542(List list, int i, Ref.ObjectRef objectRef) {
        list.remove(i);
        ((Function0) objectRef.element).invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v9, types: [T, java.lang.String] */
    public static final void showPurchaseInvoiceEditor$lambda$551$lambda$550(Ref.BooleanRef booleanRef, Spinner spinner, List list, InvoiceRepository invoiceRepository, MainActivity mainActivity, Ref.ObjectRef objectRef, EditText editText, EditText editText2, EditText editText3, List list2, View view) throws Throwable {
        boolean z;
        boolean z2;
        SupplierSummary supplierSummary;
        Ref.BooleanRef booleanRef2 = booleanRef;
        if (booleanRef2.element) {
            return;
        }
        try {
            try {
                booleanRef2.element = true;
                String str = null;
                String id = (spinner.getSelectedItemPosition() <= 0 || (supplierSummary = (SupplierSummary) CollectionsKt.getOrNull(list, spinner.getSelectedItemPosition() - 1)) == null) ? null : supplierSummary.getId();
                String str2 = mainActivity.currentUserId;
                if (str2 == null) {
                    throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
                }
                String string = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                String str3 = (String) objectRef.element;
                String string2 = StringsKt.trim((CharSequence) editText.getText().toString()).toString();
                long usdCents = mainActivity.parseUsdCents(editText2.getText().toString());
                String string3 = StringsKt.trim((CharSequence) editText3.getText().toString()).toString();
                if (string3.length() != 0) {
                    str = string3;
                }
                String str4 = str;
                List list3 = list2;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
                Iterator it = list3.iterator();
                while (it.hasNext()) {
                    try {
                        try {
                            PurchaseLineDraft purchaseLineDraft = (PurchaseLineDraft) it.next();
                            Iterator it2 = it;
                            arrayList.add(new InvoiceLineInput(purchaseLineDraft.getProduct().getId(), purchaseLineDraft.getQuantity(), purchaseLineDraft.getUnitCostUsdCents()));
                            it = it2;
                        } catch (Exception e) {
                            e = e;
                            booleanRef2 = booleanRef;
                            MainActivity mainActivity2 = mainActivity;
                            String message = e.getMessage();
                            if (message == null) {
                                message = "\u062a\u0639\u0630\u0631 \u062d\u0641\u0638 \u0627\u0644\u0645\u0633\u0648\u062f\u0629";
                            }
                            Toast.makeText(mainActivity2, message, 1).show();
                            z2 = false;
                            booleanRef2.element = z2;
                        }
                    } catch (Throwable th) {
                        th = th;
                        z = false;
                        booleanRef2 = booleanRef;
                        booleanRef2.element = z;
                        throw th;
                    }
                }
                try {
                    InvoiceSaveResult invoiceSaveResultSavePurchaseDraft = invoiceRepository.savePurchaseDraft(str2, string, str3, id, string2, usdCents, str4, arrayList);
                    objectRef.element = invoiceSaveResultSavePurchaseDraft.getInvoiceId();
                    z2 = false;
                    Toast.makeText(mainActivity, "\u062a\u0645 \u062d\u0641\u0638 \u0627\u0644\u0645\u0633\u0648\u062f\u0629 " + invoiceSaveResultSavePurchaseDraft.getInvoiceNumber(), 0).show();
                    booleanRef2 = booleanRef;
                    booleanRef2.element = z2;
                } catch (Throwable th2) {
                    th = th2;
                    booleanRef2 = booleanRef;
                    z = false;
                    booleanRef2.element = z;
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:57:0x0168  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.String] */
    public static final void showPurchaseInvoiceEditor$lambda$556$lambda$555(Ref.BooleanRef booleanRef, Spinner spinner, List list, InvoiceRepository invoiceRepository, MainActivity mainActivity, Ref.ObjectRef objectRef, EditText editText, EditText editText2, EditText editText3, List list2, View view) throws Throwable {
        boolean z;
        boolean z2;
        String message;
        String str;
        SupplierSummary supplierSummary;
        Ref.BooleanRef booleanRef2 = booleanRef;
        MainActivity mainActivity2 = mainActivity;
        if (booleanRef2.element) {
            return;
        }
        try {
            try {
                booleanRef2.element = true;
                String id = (spinner.getSelectedItemPosition() <= 0 || (supplierSummary = (SupplierSummary) CollectionsKt.getOrNull(list, spinner.getSelectedItemPosition() - 1)) == null) ? null : supplierSummary.getId();
                String str2 = mainActivity2.currentUserId;
                String str3 = "\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629";
                if (str2 == null) {
                    throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
                }
                String string = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                String str4 = (String) objectRef.element;
                String string2 = StringsKt.trim((CharSequence) editText.getText().toString()).toString();
                long usdCents = mainActivity2.parseUsdCents(editText2.getText().toString());
                String string3 = StringsKt.trim((CharSequence) editText3.getText().toString()).toString();
                if (string3.length() == 0) {
                    string3 = null;
                }
                String str5 = string3;
                List list3 = list2;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
                Iterator it = list3.iterator();
                while (it.hasNext()) {
                    try {
                        try {
                            PurchaseLineDraft purchaseLineDraft = (PurchaseLineDraft) it.next();
                            Iterator it2 = it;
                            String str6 = str3;
                            arrayList.add(new InvoiceLineInput(purchaseLineDraft.getProduct().getId(), purchaseLineDraft.getQuantity(), purchaseLineDraft.getUnitCostUsdCents()));
                            it = it2;
                            str3 = str6;
                        } catch (Throwable th) {
                            th = th;
                            z = false;
                            booleanRef2 = booleanRef;
                            booleanRef2.element = z;
                            throw th;
                        }
                    } catch (Exception e) {
                        e = e;
                        booleanRef2 = booleanRef;
                        mainActivity2 = mainActivity;
                        MainActivity mainActivity3 = mainActivity2;
                        message = e.getMessage();
                        if (message == null) {
                            message = "\u062a\u0639\u0630\u0631 \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629";
                        }
                        Toast.makeText(mainActivity3, message, 1).show();
                        z2 = false;
                        booleanRef2.element = z2;
                    }
                }
                String str7 = str3;
                try {
                    InvoiceSaveResult invoiceSaveResultSavePurchaseDraft = invoiceRepository.savePurchaseDraft(str2, string, str4, id, string2, usdCents, str5, arrayList);
                    objectRef.element = invoiceSaveResultSavePurchaseDraft.getInvoiceId();
                    mainActivity2 = mainActivity;
                    try {
                        String str8 = mainActivity2.currentUserId;
                        if (str8 != null) {
                            String invoiceId = invoiceSaveResultSavePurchaseDraft.getInvoiceId();
                            String string4 = UUID.randomUUID().toString();
                            Intrinsics.checkNotNullExpressionValue(string4, "toString(...)");
                            if (!invoiceRepository.postPurchase(str8, invoiceId, string4, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : null)) {
                                throw new IllegalArgumentException("\u062a\u0639\u0630\u0631 \u062d\u0641\u0638 \u0637\u0644\u0628 \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629".toString());
                            }
                            if (new SyncProtocolRepository(mainActivity2, null, 2, 0 == true ? 1 : 0).isConfiguredSecondary()) {
                                str = "\u062a\u0645 \u062d\u0641\u0638 \u0637\u0644\u0628 \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0634\u0631\u0627\u0621 \u0645\u062d\u0644\u064a\u0627\u064b \u0628\u0627\u0646\u062a\u0638\u0627\u0631 \u0627\u0644\u0645\u0632\u0627\u0645\u0646\u0629 \u0648\u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u062c\u0647\u0627\u0632 \u0627\u0644\u0623\u0633\u0627\u0633\u064a\u061b \u0644\u0645 \u064a\u064f\u0631\u062d\u0651\u0644 \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u0628\u0639\u062f";
                            } else {
                                str = "\u062a\u0645 \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0648\u062a\u062d\u062f\u064a\u062b \u0627\u0644\u0645\u062e\u0632\u0648\u0646";
                            }
                            Toast.makeText(mainActivity2, str, 1).show();
                            mainActivity.showPurchases();
                            z2 = false;
                            booleanRef2 = booleanRef;
                        } else {
                            throw new IllegalStateException(str7.toString());
                        }
                    } catch (Exception e2) {
                        e = e2;
                        booleanRef2 = booleanRef;
                        MainActivity mainActivity4 = mainActivity2;
                        message = e.getMessage();
                        if (message == null) {
                            message = "\u062a\u0639\u0630\u0631 \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629";
                        }
                        Toast.makeText(mainActivity4, message, 1).show();
                        z2 = false;
                    }
                    booleanRef2.element = z2;
                } catch (Throwable th2) {
                    th = th2;
                    booleanRef2 = booleanRef;
                    z = false;
                    booleanRef2.element = z;
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private final void showPurchaseProductPicker(final Function1<? super StoreProduct, Unit> onSelected) {
        MainActivity mainActivity = this;
        final StoreRepository storeRepository = new StoreRepository(mainActivity);
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(16), dp(8), dp(16), dp(8));
        EditText editTextFieldForDialog$default = fieldForDialog$default(this, "\u0627\u0628\u062d\u062b \u0628\u0627\u0633\u0645 \u0627\u0644\u0645\u0646\u062a\u062c \u0623\u0648 \u0627\u0644\u0628\u0627\u0631\u0643\u0648\u062f \u0623\u0648 SKU", null, 0, 6, null);
        final LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        linearLayout2.setOrientation(1);
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.addView(linearLayout2);
        Button button = new Button(mainActivity);
        button.setText("+ \u0625\u0636\u0627\u0641\u0629 \u0645\u0646\u062a\u062c \u062c\u062f\u064a\u062f");
        button.setAllCaps(false);
        button.setTextColor(-1);
        button.setBackgroundColor(this.green);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda29
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.showPurchaseProductPicker$lambda$562$lambda$561(this.f$0, onSelected, view);
            }
        });
        linearLayout.addView(editTextFieldForDialog$default, fieldParams(dp(48), dp(8)));
        linearLayout.addView(button, fieldParams(dp(46), dp(8)));
        linearLayout.addView(scrollView, new LinearLayout.LayoutParams(-1, dp(MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION)));
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(mainActivity).setTitle("\u0627\u062e\u062a\u064a\u0627\u0631 \u0635\u0646\u0641").setView(linearLayout).setNegativeButton("\u0625\u063a\u0644\u0627\u0642", (DialogInterface.OnClickListener) null).create();
        editTextFieldForDialog$default.addTextChangedListener(new TextWatcher() { // from class: com.hazem.dailystore.MainActivity.showPurchaseProductPicker.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                LinearLayout linearLayout3 = linearLayout2;
                StoreRepository storeRepository2 = storeRepository;
                MainActivity mainActivity2 = this;
                AlertDialog alertDialog = alertDialogCreate;
                Function1<StoreProduct, Unit> function1 = onSelected;
                String string = s != null ? s.toString() : null;
                if (string == null) {
                    string = "";
                }
                MainActivity.showPurchaseProductPicker$refresh(linearLayout3, storeRepository2, mainActivity2, alertDialog, function1, string);
            }
        });
        showPurchaseProductPicker$refresh(linearLayout2, storeRepository, this, alertDialogCreate, onSelected, "");
        alertDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showPurchaseProductPicker$lambda$562$lambda$561(final MainActivity mainActivity, final Function1 function1, View view) {
        mainActivity.showProductEditor(null, new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda112
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showPurchaseProductPicker$lambda$562$lambda$561$lambda$560(this.f$0, function1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showPurchaseProductPicker$lambda$562$lambda$561$lambda$560(MainActivity mainActivity, Function1 function1) {
        mainActivity.showPurchaseProductPicker(function1);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showPurchaseProductPicker$refresh(LinearLayout linearLayout, StoreRepository storeRepository, MainActivity mainActivity, final AlertDialog alertDialog, final Function1<? super StoreProduct, Unit> function1, String str) {
        String barcode;
        String sku;
        linearLayout.removeAllViews();
        List<StoreProduct> listListProducts = storeRepository.listProducts();
        ArrayList<StoreProduct> arrayList = new ArrayList();
        for (Object obj : listListProducts) {
            StoreProduct storeProduct = (StoreProduct) obj;
            String str2 = str;
            if (StringsKt.isBlank(str2) || StringsKt.contains((CharSequence) storeProduct.getName(), (CharSequence) str2, true) || (((barcode = storeProduct.getBarcode()) != null && StringsKt.contains((CharSequence) barcode, (CharSequence) str2, true)) || ((sku = storeProduct.getSku()) != null && StringsKt.contains((CharSequence) sku, (CharSequence) str2, true)))) {
                arrayList.add(obj);
            }
        }
        for (final StoreProduct storeProduct2 : arrayList) {
            Button buttonSmallButton = mainActivity.smallButton(storeProduct2.getName() + "  \u2022  " + storeProduct2.getUnitName() + "  \u2022  " + mainActivity.moneyText(storeProduct2.getPurchaseCostUsdCents()) + " USD", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda105
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MainActivity.showPurchaseProductPicker$refresh$lambda$565$lambda$564(alertDialog, function1, storeProduct2);
                }
            });
            buttonSmallButton.setGravity(5);
            linearLayout.addView(buttonSmallButton, mainActivity.fieldParams(mainActivity.dp(48), mainActivity.dp(4)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showPurchaseProductPicker$refresh$lambda$565$lambda$564(AlertDialog alertDialog, Function1 function1, StoreProduct storeProduct) {
        alertDialog.dismiss();
        function1.invoke(storeProduct);
        return Unit.INSTANCE;
    }

    static /* synthetic */ void showPurchaseLineDialog$default(MainActivity mainActivity, StoreProduct storeProduct, BigDecimal bigDecimal, long j, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            bigDecimal = BigDecimal.ONE;
        }
        BigDecimal bigDecimal2 = bigDecimal;
        if ((i & 4) != 0) {
            j = storeProduct.getPurchaseCostUsdCents();
        }
        mainActivity.showPurchaseLineDialog(storeProduct, bigDecimal2, j, function2);
    }

    private final void showPurchaseLineDialog(StoreProduct product, BigDecimal initialQuantity, long initialCost, final Function2<? super BigDecimal, ? super Long, Unit> onSave) {
        MainActivity mainActivity = this;
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(20), dp(8), dp(20), 0);
        String str = "\u0627\u0644\u0643\u0645\u064a\u0629 " + product.getUnitName();
        String plainString = MainActivity$$ExternalSyntheticBackport0.m(initialQuantity).toPlainString();
        Intrinsics.checkNotNullExpressionValue(plainString, "toPlainString(...)");
        final EditText editTextFieldForDialog = fieldForDialog(str, plainString, 8194);
        final EditText editTextFieldForDialog2 = fieldForDialog("\u062a\u0643\u0644\u0641\u0629 \u0627\u0644\u0648\u062d\u062f\u0629 \u0628\u0627\u0644\u062f\u0648\u0644\u0627\u0631", moneyText(initialCost), 8194);
        final long currentExchangeRateMilli = new StoreRepository(mainActivity).getCurrentExchangeRateMilli();
        final EditText editTextFieldForDialog3 = fieldForDialog("\u062a\u0643\u0644\u0641\u0629 \u0627\u0644\u0648\u062d\u062f\u0629 \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629", currentExchangeRateMilli > 0 ? formatSypAmount(CurrencyConverter.INSTANCE.usdCentsToSyp(initialCost, currentExchangeRateMilli)) : "", 8194);
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        editTextFieldForDialog2.addTextChangedListener(new TextWatcher() { // from class: com.hazem.dailystore.MainActivity.showPurchaseLineDialog.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Object objM381constructorimpl;
                if (booleanRef.element || currentExchangeRateMilli <= 0) {
                    return;
                }
                MainActivity mainActivity2 = this;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    C00301 c00301 = this;
                    String string = s != null ? s.toString() : null;
                    if (string == null) {
                        string = "";
                    }
                    objM381constructorimpl = Result.m381constructorimpl(Long.valueOf(mainActivity2.parseUsdCents(string)));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
                }
                Long l = (Long) (Result.m387isFailureimpl(objM381constructorimpl) ? null : objM381constructorimpl);
                if (l != null) {
                    long jLongValue = l.longValue();
                    booleanRef.element = true;
                    editTextFieldForDialog3.setText(this.formatSypAmount(CurrencyConverter.INSTANCE.usdCentsToSyp(jLongValue, currentExchangeRateMilli)));
                    booleanRef.element = false;
                }
            }
        });
        editTextFieldForDialog3.addTextChangedListener(new TextWatcher() { // from class: com.hazem.dailystore.MainActivity.showPurchaseLineDialog.2
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Object objM381constructorimpl;
                if (booleanRef.element || currentExchangeRateMilli <= 0) {
                    return;
                }
                MainActivity mainActivity2 = this;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    C00312 c00312 = this;
                    String string = s != null ? s.toString() : null;
                    if (string == null) {
                        string = "";
                    }
                    objM381constructorimpl = Result.m381constructorimpl(Long.valueOf(mainActivity2.parseSypAmount(string)));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
                }
                Long l = (Long) (Result.m387isFailureimpl(objM381constructorimpl) ? null : objM381constructorimpl);
                if (l != null) {
                    long jLongValue = l.longValue();
                    booleanRef.element = true;
                    editTextFieldForDialog2.setText(this.moneyText(CurrencyConverter.INSTANCE.sypToUsdCents(jLongValue, currentExchangeRateMilli)));
                    booleanRef.element = false;
                }
            }
        });
        editTextFieldForDialog3.setEnabled(currentExchangeRateMilli > 0);
        if (currentExchangeRateMilli <= 0) {
            editTextFieldForDialog3.setHint("\u0633\u062c\u0651\u0644 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0623\u0648\u0644\u0627\u064b");
        }
        TextView textViewLabel = label(product.getName(), 17.0f, this.green, true, 17);
        textViewLabel.setPadding(0, 0, 0, dp(8));
        linearLayout.addView(textViewLabel);
        linearLayout.addView(editTextFieldForDialog, fieldParams(dp(48), dp(8)));
        linearLayout.addView(editTextFieldForDialog2, fieldParams(dp(48), dp(8)));
        linearLayout.addView(editTextFieldForDialog3, fieldParams(dp(48), dp(8)));
        new AlertDialog.Builder(mainActivity).setTitle("\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0635\u0646\u0641").setView(linearLayout).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u0625\u0636\u0627\u0641\u0629", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda54
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.showPurchaseLineDialog$lambda$569(editTextFieldForDialog, onSave, this, editTextFieldForDialog2, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showPurchaseLineDialog$lambda$569(EditText editText, Function2 function2, MainActivity mainActivity, EditText editText2, DialogInterface dialogInterface, int i) {
        try {
            BigDecimal bigDecimal = new BigDecimal(StringsKt.replace$default(StringsKt.trim((CharSequence) editText.getText().toString()).toString(), ',', '.', false, 4, (Object) null));
            if (bigDecimal.signum() <= 0) {
                throw new IllegalArgumentException("\u0623\u062f\u062e\u0644 \u0643\u0645\u064a\u0629 \u0623\u0643\u0628\u0631 \u0645\u0646 \u0635\u0641\u0631".toString());
            }
            function2.invoke(bigDecimal, Long.valueOf(mainActivity.parseUsdCents(editText2.getText().toString())));
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u062d\u0642\u0642 \u0645\u0646 \u0627\u0644\u0643\u0645\u064a\u0629 \u0648\u0627\u0644\u062a\u0643\u0644\u0641\u0629";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    static /* synthetic */ EditText fieldForDialog$default(MainActivity mainActivity, String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str2 = "";
        }
        if ((i2 & 4) != 0) {
            i = 1;
        }
        return mainActivity.fieldForDialog(str, str2, i);
    }

    private final EditText fieldForDialog(String hint, String value, int type) {
        EditText editText = new EditText(this);
        editText.setHint(hint);
        editText.setText(value);
        editText.setInputType(type);
        editText.setTextSize(14.0f);
        editText.setSingleLine(true);
        editText.setPadding(dp(12), 0, dp(12), 0);
        editText.setBackground(inputBackground());
        return editText;
    }

    private final void showSalesReturns() throws IOException {
        MainActivity mainActivity = this;
        SalesReturnRepository salesReturnRepository = new SalesReturnRepository(mainActivity);
        markScreen$default(this, "SALES_RETURNS", null, 2, null);
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(this.backgroundColor);
        LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        linearLayout2.setPadding(dp(14), dp(10), dp(14), dp(10));
        linearLayout2.setBackgroundColor(-1);
        Button button = new Button(mainActivity);
        button.setText("\u2039");
        button.setTextSize(25.0f);
        button.setAllCaps(false);
        button.setTextColor(this.green);
        button.setBackgroundColor(0);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda100
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IOException {
                MainActivity.showPointOfSale$default(this.f$0, false, 1, null);
            }
        });
        linearLayout2.addView(button, new LinearLayout.LayoutParams(dp(48), dp(48)));
        linearLayout2.addView(label("\u0645\u0631\u062a\u062c\u0639\u0627\u062a \u0627\u0644\u0645\u0628\u064a\u0639\u0627\u062a", 19.0f, this.green, true, 17), new LinearLayout.LayoutParams(0, -2, 1.0f));
        LinearLayout linearLayout3 = new LinearLayout(mainActivity);
        linearLayout3.setOrientation(1);
        linearLayout3.setPadding(dp(16), dp(16), dp(16), dp(24));
        linearLayout3.addView(label("\u0641\u0648\u0627\u062a\u064a\u0631 \u0642\u0627\u0628\u0644\u0629 \u0644\u0644\u0625\u0631\u062c\u0627\u0639", 21.0f, this.green, true, 5));
        TextView textViewLabel = label("\u064a\u064f\u062e\u0635\u0645 \u0627\u0644\u062f\u064a\u0646 \u0627\u0644\u0645\u0633\u062a\u062d\u0642 \u0623\u0648\u0644\u0627\u064b\u060c \u062b\u0645 \u064a\u064f\u0631\u062f \u0627\u0644\u0645\u062a\u0628\u0642\u064a \u0646\u0642\u062f\u0627\u064b. \u064a\u0645\u0643\u0646\u0643 \u062a\u0648\u0632\u064a\u0639 \u0627\u0644\u0645\u0628\u0644\u063a \u0627\u0644\u0646\u0642\u062f\u064a \u0639\u0644\u0649 \u0623\u0643\u062b\u0631 \u0645\u0646 \u0635\u0646\u062f\u0648\u0642 \u0623\u0648 \u0645\u062d\u0641\u0638\u0629.", 13.0f, this.mutedColor, false, 5);
        textViewLabel.setPadding(0, dp(5), 0, dp(12));
        linearLayout3.addView(textViewLabel);
        List<SalesReturnInvoiceOption> listListReturnableInvoices = salesReturnRepository.listReturnableInvoices();
        if (listListReturnableInvoices.isEmpty()) {
            linearLayout3.addView(label("\u0644\u0627 \u062a\u0648\u062c\u062f \u0641\u0648\u0627\u062a\u064a\u0631 \u0645\u0639\u062a\u0645\u062f\u0629 \u062a\u062d\u062a\u0648\u064a \u0643\u0645\u064a\u0627\u062a \u0645\u062a\u0627\u062d\u0629 \u0644\u0644\u0625\u0631\u062c\u0627\u0639.", 14.0f, this.mutedColor, false, 17));
        } else {
            for (final SalesReturnInvoiceOption salesReturnInvoiceOption : listListReturnableInvoices) {
                LinearLayout linearLayout4 = new LinearLayout(mainActivity);
                linearLayout4.setOrientation(1);
                linearLayout4.setPadding(dp(13), dp(11), dp(13), dp(11));
                linearLayout4.setBackground(cardBackground());
                linearLayout4.addView(label("\u0641\u0627\u062a\u0648\u0631\u0629 " + salesReturnInvoiceOption.getNumber(), 16.0f, this.green, true, 5));
                String date = salesReturnInvoiceOption.getDate();
                String customerName = salesReturnInvoiceOption.getCustomerName();
                if (customerName == null) {
                    customerName = "\u0628\u064a\u0639 \u0646\u0642\u062f\u064a";
                }
                linearLayout4.addView(label(date + " \u2022 " + customerName + " \u2022 " + salesReturnInvoiceOption.getSaleType(), 12.0f, this.mutedColor, false, 5));
                long totalUsdCents = salesReturnInvoiceOption.getTotalUsdCents();
                StringBuilder sb = new StringBuilder("\u0627\u0644\u0625\u062c\u0645\u0627\u0644\u064a: ");
                sb.append(totalUsdCents);
                sb.append(" \u0633\u0646\u062a USD");
                linearLayout4.addView(label(sb.toString(), 13.0f, this.textColor, true, 5));
                linearLayout4.addView(smallButton("\u0625\u0646\u0634\u0627\u0621 \u0645\u0631\u062a\u062c\u0639", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda101
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivity.showSalesReturns$lambda$579$lambda$578(this.f$0, salesReturnInvoiceOption);
                    }
                }), fieldParams(dp(42), dp(7)));
                linearLayout3.addView(linearLayout4, fieldParams(-2, dp(8)));
            }
        }
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setFillViewport(true);
        scrollView.addView(linearLayout3);
        linearLayout.addView(linearLayout2, matchWidth());
        linearLayout.addView(scrollView, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        setContentView(linearLayout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showSalesReturns$lambda$579$lambda$578(MainActivity mainActivity, SalesReturnInvoiceOption salesReturnInvoiceOption) throws IOException {
        mainActivity.showSalesReturnEditor(salesReturnInvoiceOption.getId());
        return Unit.INSTANCE;
    }

    private final void showSalesReturnEditor(final String invoiceId) throws IOException {
        final String str = this.currentUserId;
        if (str == null) {
            return;
        }
        MainActivity mainActivity = this;
        final SalesReturnRepository salesReturnRepository = new SalesReturnRepository(mainActivity);
        List<SalesReturnableItem> listListReturnableItems = salesReturnRepository.listReturnableItems(invoiceId);
        CashAccountRepository cashAccountRepository = new CashAccountRepository(mainActivity);
        String strSystemRoleAccountId = cashAccountRepository.systemRoleAccountId("SUPPLIER_ROLLOVER");
        List<CashAccountSummary> listListActive = cashAccountRepository.listActive();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listListActive) {
            CashAccountSummary cashAccountSummary = (CashAccountSummary) obj;
            if (Intrinsics.areEqual(cashAccountSummary.getCurrencyCode(), "SYP") || Intrinsics.areEqual(cashAccountSummary.getCurrencyCode(), "USD")) {
                if (!Intrinsics.areEqual(cashAccountSummary.getId(), strSystemRoleAccountId)) {
                    arrayList.add(obj);
                }
            }
        }
        final ArrayList arrayList2 = arrayList;
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        int i = 1;
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(16), dp(8), dp(16), dp(8));
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        linearLayout.addView(label("\u0627\u0644\u0643\u0645\u064a\u0627\u062a \u0627\u0644\u0645\u0631\u0627\u062f \u0625\u0631\u062c\u0627\u0639\u0647\u0627", 16.0f, this.green, true, 5));
        for (SalesReturnableItem salesReturnableItem : listListReturnableItems) {
            LinearLayout linearLayout2 = new LinearLayout(mainActivity);
            linearLayout2.setOrientation(i);
            linearLayout2.setPadding(0, dp(4), 0, dp(4));
            linearLayout2.addView(label(salesReturnableItem.getProductName() + " \u2022 \u0645\u0628\u0627\u0639 " + salesReturnableItem.getSoldQuantity().toPlainString() + " \u2022 \u0633\u0628\u0642 \u0625\u0631\u062c\u0627\u0639\u0647 " + salesReturnableItem.getReturnedQuantity().toPlainString() + " \u2022 \u0645\u062a\u0627\u062d " + salesReturnableItem.getAvailableQuantity().toPlainString(), 13.0f, this.textColor, true, 5));
            EditText editTextFieldForDialog$default = fieldForDialog$default(this, "\u0627\u0644\u0643\u0645\u064a\u0629 \u0627\u0644\u0645\u0631\u062a\u062c\u0639\u0629", null, 8194, 2, null);
            linkedHashMap.put(salesReturnableItem.getId(), editTextFieldForDialog$default);
            linearLayout2.addView(editTextFieldForDialog$default, fieldParams(dp(44), dp(4)));
            linearLayout.addView(linearLayout2, matchWidth());
            i = 1;
        }
        final EditText editTextFieldForDialog$default2 = fieldForDialog$default(this, "\u0633\u0628\u0628 \u0627\u0644\u0645\u0631\u062a\u062c\u0639", null, 0, 6, null);
        linearLayout.addView(editTextFieldForDialog$default2, fieldParams(dp(46), dp(8)));
        final TextView textViewLabel = label("\u0627\u0636\u063a\u0637 \u0645\u0639\u0627\u064a\u0646\u0629 \u0644\u062d\u0633\u0627\u0628 \u0642\u064a\u0645\u0629 \u0627\u0644\u0645\u0631\u062a\u062c\u0639 \u0648\u0627\u0644\u062f\u064a\u0646 \u0648\u0631\u062f \u0627\u0644\u0646\u0642\u062f.", 13.0f, this.mutedColor, false, 5);
        linearLayout.addView(textViewLabel, fieldParams(-2, dp(8)));
        linearLayout.addView(label("\u062a\u0648\u0632\u064a\u0639 \u0631\u062f \u0627\u0644\u0645\u0628\u0644\u063a \u0627\u0644\u0646\u0642\u062f\u064a", 15.0f, this.green, true, 5));
        linearLayout.addView(label("\u0623\u0636\u0641 \u062d\u0633\u0627\u0628\u0627\u064b \u0623\u0648 \u0623\u0643\u062b\u0631. \u0627\u0644\u0645\u0628\u0644\u063a \u064a\u064f\u062f\u062e\u0644 \u0628\u0639\u0645\u0644\u0629 \u0627\u0644\u062d\u0633\u0627\u0628: \u0627\u0644\u062f\u0648\u0644\u0627\u0631 \u0644\u0644\u062d\u0633\u0627\u0628 USD \u0648\u0627\u0644\u0644\u064a\u0631\u0629 \u0644\u0644\u062d\u0633\u0627\u0628 SYP.", 12.0f, this.mutedColor, false, 5));
        final LinearLayout linearLayout3 = new LinearLayout(mainActivity);
        linearLayout3.setOrientation(1);
        final ArrayList arrayList3 = new ArrayList();
        linearLayout.addView(linearLayout3, matchWidth());
        linearLayout.addView(smallButton("+ \u0625\u0636\u0627\u0641\u0629 \u062d\u0633\u0627\u0628 \u0644\u0644\u062a\u0648\u0632\u064a\u0639", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda141
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showSalesReturnEditor$lambda$588(arrayList2, this, linearLayout3, arrayList3);
            }
        }), fieldParams(dp(40), dp(5)));
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setFillViewport(true);
        scrollView.addView(linearLayout);
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(mainActivity).setTitle("\u0625\u0646\u0634\u0627\u0621 \u0645\u0631\u062a\u062c\u0639 \u0645\u0628\u064a\u0639\u0627\u062a").setView(scrollView).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setNeutralButton("\u0645\u0639\u0627\u064a\u0646\u0629", (DialogInterface.OnClickListener) null).setPositiveButton("\u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0645\u0631\u062a\u062c\u0639", (DialogInterface.OnClickListener) null).create();
        alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda143
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                MainActivity.showSalesReturnEditor$lambda$600(alertDialogCreate, salesReturnRepository, invoiceId, textViewLabel, arrayList3, this, linkedHashMap, arrayList2, linearLayout3, str, editTextFieldForDialog$default2, dialogInterface);
            }
        });
        alertDialogCreate.show();
    }

    private static final void showSalesReturnEditor$addAllocationRow(List<CashAccountSummary> list, MainActivity mainActivity, LinearLayout linearLayout, List<Pair<Spinner, EditText>> list2) {
        if (list.isEmpty()) {
            Toast.makeText(mainActivity, "\u0644\u0627 \u064a\u0648\u062c\u062f \u062d\u0633\u0627\u0628 \u0646\u0634\u0637 \u0635\u0627\u0644\u062d \u0644\u0631\u062f \u0627\u0644\u0645\u0628\u0644\u063a", 1).show();
            return;
        }
        MainActivity mainActivity2 = mainActivity;
        LinearLayout linearLayout2 = new LinearLayout(mainActivity2);
        linearLayout2.setOrientation(1);
        linearLayout2.setPadding(0, mainActivity.dp(3), 0, mainActivity.dp(3));
        Spinner spinner = new Spinner(mainActivity2);
        List<CashAccountSummary> list3 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
        for (CashAccountSummary cashAccountSummary : list3) {
            arrayList.add(cashAccountSummary.getName() + " \u2022 " + cashAccountSummary.getCurrencyCode() + " \u2022 " + mainActivity.formatAccountBalance(cashAccountSummary.getBalanceMinor(), cashAccountSummary.getCurrencyCode()));
        }
        spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity2, android.R.layout.simple_spinner_dropdown_item, arrayList));
        EditText editTextFieldForDialog$default = fieldForDialog$default(mainActivity, "\u0627\u0644\u0645\u0628\u0644\u063a \u0645\u0646 \u0647\u0630\u0627 \u0627\u0644\u062d\u0633\u0627\u0628", null, 0, 6, null);
        linearLayout2.addView(spinner, mainActivity.fieldParams(mainActivity.dp(44), mainActivity.dp(2)));
        linearLayout2.addView(editTextFieldForDialog$default, mainActivity.fieldParams(mainActivity.dp(44), mainActivity.dp(2)));
        linearLayout.addView(linearLayout2, mainActivity.matchWidth());
        list2.add(TuplesKt.to(spinner, editTextFieldForDialog$default));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showSalesReturnEditor$lambda$588(List list, MainActivity mainActivity, LinearLayout linearLayout, List list2) {
        showSalesReturnEditor$addAllocationRow(list, mainActivity, linearLayout, list2);
        return Unit.INSTANCE;
    }

    private static final List<SalesReturnItemInput> showSalesReturnEditor$selectedItems(LinkedHashMap<String, EditText> linkedHashMap) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, EditText> entry : linkedHashMap.entrySet()) {
            String key = entry.getKey();
            String strReplace$default = StringsKt.replace$default(StringsKt.trim((CharSequence) entry.getValue().getText().toString()).toString(), ',', '.', false, 4, (Object) null);
            SalesReturnItemInput salesReturnItemInput = strReplace$default.length() == 0 ? null : new SalesReturnItemInput(key, new BigDecimal(strReplace$default));
            if (salesReturnItemInput != null) {
                arrayList.add(salesReturnItemInput);
            }
        }
        return arrayList;
    }

    private static final List<SalesRefundAllocationInput> showSalesReturnEditor$selectedAllocations(List<Pair<Spinner, EditText>> list, List<CashAccountSummary> list2) {
        SalesRefundAllocationInput salesRefundAllocationInput;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            Spinner spinner = (Spinner) pair.component1();
            String strReplace$default = StringsKt.replace$default(StringsKt.trim((CharSequence) ((EditText) pair.component2()).getText().toString()).toString(), ',', '.', false, 4, (Object) null);
            if (strReplace$default.length() == 0) {
                salesRefundAllocationInput = null;
            } else {
                CashAccountSummary cashAccountSummary = list2.get(spinner.getSelectedItemPosition());
                salesRefundAllocationInput = new SalesRefundAllocationInput(cashAccountSummary.getId(), new BigDecimal(strReplace$default).multiply(BigDecimal.valueOf(Intrinsics.areEqual(cashAccountSummary.getCurrencyCode(), "USD") ? 100L : 1L)).setScale(0, RoundingMode.UNNECESSARY).longValueExact());
            }
            if (salesRefundAllocationInput != null) {
                arrayList.add(salesRefundAllocationInput);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showSalesReturnEditor$lambda$600(final AlertDialog alertDialog, final SalesReturnRepository salesReturnRepository, final String str, final TextView textView, final List list, final MainActivity mainActivity, final LinkedHashMap linkedHashMap, final List list2, final LinearLayout linearLayout, final String str2, final EditText editText, DialogInterface dialogInterface) {
        alertDialog.getButton(-3).setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda97
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.showSalesReturnEditor$lambda$600$lambda$593(salesReturnRepository, str, textView, list, mainActivity, linkedHashMap, list2, linearLayout, view);
            }
        });
        alertDialog.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda99
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Throwable {
                MainActivity.showSalesReturnEditor$lambda$600$lambda$599(salesReturnRepository, str, str2, mainActivity, editText, alertDialog, linkedHashMap, list, list2, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showSalesReturnEditor$lambda$600$lambda$593(SalesReturnRepository salesReturnRepository, String str, TextView textView, List list, MainActivity mainActivity, LinkedHashMap linkedHashMap, List list2, LinearLayout linearLayout, View view) {
        try {
            List<SalesReturnItemInput> listShowSalesReturnEditor$selectedItems = showSalesReturnEditor$selectedItems(linkedHashMap);
            if (listShowSalesReturnEditor$selectedItems.isEmpty()) {
                throw new IllegalArgumentException("\u0623\u062f\u062e\u0644 \u0643\u0645\u064a\u0629 \u0645\u0631\u062a\u062c\u0639\u0629 \u0644\u0635\u0646\u0641 \u0648\u0627\u062d\u062f \u0639\u0644\u0649 \u0627\u0644\u0623\u0642\u0644".toString());
            }
            SalesReturnPreview salesReturnPreviewPreviewSalesReturn = salesReturnRepository.previewSalesReturn(str, listShowSalesReturnEditor$selectedItems);
            textView.setText("\u0642\u064a\u0645\u0629 \u0627\u0644\u0645\u0631\u062a\u062c\u0639: " + salesReturnPreviewPreviewSalesReturn.getTotalUsdCents() + " \u0633\u0646\u062a USD\n\u062a\u062e\u0641\u064a\u0636 \u0627\u0644\u062f\u064a\u0646: " + salesReturnPreviewPreviewSalesReturn.getCreditReductionUsdCents() + " \u0633\u0646\u062a USD\n\u0627\u0644\u0645\u0628\u0644\u063a \u0627\u0644\u0646\u0642\u062f\u064a \u0627\u0644\u0645\u0637\u0644\u0648\u0628 \u0631\u062f\u0647: " + salesReturnPreviewPreviewSalesReturn.getCashRefundSypMinor() + " \u0644.\u0633");
            if (salesReturnPreviewPreviewSalesReturn.getCashRefundSypMinor() <= 0 || !list.isEmpty()) {
                return;
            }
            showSalesReturnEditor$addAllocationRow(list2, mainActivity, linearLayout, list);
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631\u062a \u0645\u0639\u0627\u064a\u0646\u0629 \u0627\u0644\u0645\u0631\u062a\u062c\u0639";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showSalesReturnEditor$lambda$600$lambda$599(SalesReturnRepository salesReturnRepository, String str, String str2, MainActivity mainActivity, EditText editText, AlertDialog alertDialog, LinkedHashMap linkedHashMap, List list, List list2, View view) throws Throwable {
        try {
            List<SalesReturnItemInput> listShowSalesReturnEditor$selectedItems = showSalesReturnEditor$selectedItems(linkedHashMap);
            if (listShowSalesReturnEditor$selectedItems.isEmpty()) {
                throw new IllegalArgumentException("\u0623\u062f\u062e\u0644 \u0643\u0645\u064a\u0629 \u0645\u0631\u062a\u062c\u0639\u0629 \u0644\u0635\u0646\u0641 \u0648\u0627\u062d\u062f \u0639\u0644\u0649 \u0627\u0644\u0623\u0642\u0644".toString());
            }
            SalesReturnPreview salesReturnPreviewPreviewSalesReturn = salesReturnRepository.previewSalesReturn(str, listShowSalesReturnEditor$selectedItems);
            List<SalesRefundAllocationInput> listShowSalesReturnEditor$selectedAllocations = showSalesReturnEditor$selectedAllocations(list, list2);
            if (salesReturnPreviewPreviewSalesReturn.getCashRefundSypMinor() == 0) {
                if (!listShowSalesReturnEditor$selectedAllocations.isEmpty()) {
                    throw new IllegalArgumentException("\u0644\u0627 \u064a\u0648\u062c\u062f \u0645\u0628\u0644\u063a \u0646\u0642\u062f\u064a \u0645\u0633\u062a\u062d\u0642\u061b \u0627\u062d\u0630\u0641 \u062a\u0648\u0632\u064a\u0639\u0627\u062a \u0631\u062f \u0627\u0644\u0645\u0628\u0644\u063a".toString());
                }
            } else if (listShowSalesReturnEditor$selectedAllocations.isEmpty()) {
                throw new IllegalArgumentException("\u062d\u062f\u062f \u062d\u0633\u0627\u0628\u0627\u064b \u0648\u0645\u0628\u0644\u063a \u0627\u0644\u0631\u062f".toString());
            }
            List<SalesRefundAllocationInput> list3 = listShowSalesReturnEditor$selectedAllocations;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
            Iterator<T> it = list3.iterator();
            while (it.hasNext()) {
                arrayList.add(((SalesRefundAllocationInput) it.next()).getCashAccountId());
            }
            ArrayList arrayList2 = arrayList;
            if (CollectionsKt.distinct(arrayList2).size() != arrayList2.size()) {
                throw new IllegalArgumentException("\u0644\u0627 \u062a\u0643\u0631\u0631 \u0627\u0644\u062d\u0633\u0627\u0628\u061b \u0627\u062c\u0645\u0639 \u0645\u0628\u0644\u063a\u0647 \u0641\u064a \u0633\u0637\u0631 \u0648\u0627\u062d\u062f".toString());
            }
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            StoreTime storeTime = StoreTime.INSTANCE;
            SQLiteDatabase readableDatabase = AppDatabase.INSTANCE.getInstance(mainActivity).getReadableDatabase();
            Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
            Toast.makeText(mainActivity, "\u062a\u0645 \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0645\u0631\u062a\u062c\u0639 " + salesReturnRepository.postSalesReturn(str2, str, string, StoreTime.today$default(storeTime, readableDatabase, null, 2, null), editText.getText().toString(), listShowSalesReturnEditor$selectedItems, listShowSalesReturnEditor$selectedAllocations, (896 & 128) != 0 ? null : null, (896 & 256) != 0 ? false : false, (896 & 512) != 0 ? null : null).getReturnNumber(), 1).show();
            alertDialog.dismiss();
            mainActivity.showSalesReturns();
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0645\u0631\u062a\u062c\u0639";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    private final void showPurchaseReturns() throws IOException {
        if (!isManager()) {
            Toast.makeText(this, "\u0645\u0631\u062a\u062c\u0639\u0627\u062a \u0627\u0644\u0645\u0634\u062a\u0631\u064a\u0627\u062a \u0645\u062a\u0627\u062d\u0629 \u0644\u0644\u0645\u062f\u064a\u0631 \u0641\u0642\u0637", 0).show();
            return;
        }
        markScreen$default(this, "PURCHASE_RETURNS", null, 2, null);
        MainActivity mainActivity = this;
        PurchaseReturnRepository purchaseReturnRepository = new PurchaseReturnRepository(mainActivity);
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(this.backgroundColor);
        LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        linearLayout2.setPadding(dp(14), dp(10), dp(14), dp(10));
        linearLayout2.setBackgroundColor(-1);
        Button button = new Button(mainActivity);
        button.setText("\u2039");
        button.setTextSize(25.0f);
        button.setAllCaps(false);
        button.setTextColor(this.green);
        button.setBackgroundColor(0);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IOException {
                this.f$0.showPurchases();
            }
        });
        linearLayout2.addView(button, new LinearLayout.LayoutParams(dp(48), dp(48)));
        linearLayout2.addView(label("\u0645\u0631\u062a\u062c\u0639\u0627\u062a \u0627\u0644\u0645\u0634\u062a\u0631\u064a\u0627\u062a", 19.0f, this.green, true, 17), new LinearLayout.LayoutParams(0, -2, 1.0f));
        LinearLayout linearLayout3 = new LinearLayout(mainActivity);
        linearLayout3.setOrientation(1);
        linearLayout3.setPadding(dp(16), dp(16), dp(16), dp(24));
        linearLayout3.addView(label("\u0641\u0648\u0627\u062a\u064a\u0631 \u0634\u0631\u0627\u0621 \u0642\u0627\u0628\u0644\u0629 \u0644\u0644\u0625\u0631\u062c\u0627\u0639", 21.0f, this.green, true, 5));
        TextView textViewLabel = label("\u064a\u064f\u062e\u0635\u0645 \u0627\u0644\u0645\u0631\u062a\u062c\u0639 \u0645\u0646 \u0645\u0633\u062a\u062d\u0642 \u0627\u0644\u0645\u0648\u0631\u062f \u0623\u0648\u0644\u0627\u064b. \u0625\u0630\u0627 \u0643\u0627\u0646\u062a \u0627\u0644\u0641\u0627\u062a\u0648\u0631\u0629 \u0645\u0633\u062f\u062f\u0629\u060c \u064a\u064f\u0633\u062c\u0644 \u0627\u0644\u0641\u0631\u0642 \u0643\u0645\u0628\u0644\u063a \u0645\u0633\u062a\u0631\u062f \u064a\u062f\u062e\u0644 \u0625\u0644\u0649 \u0627\u0644\u0635\u0646\u062f\u0648\u0642 \u0623\u0648 \u0627\u0644\u0645\u062d\u0641\u0638\u0629 \u0627\u0644\u0645\u062e\u062a\u0627\u0631\u0629.", 13.0f, this.mutedColor, false, 5);
        textViewLabel.setPadding(0, dp(5), 0, dp(12));
        linearLayout3.addView(textViewLabel);
        List<PurchaseReturnInvoiceOption> listListReturnableInvoices = purchaseReturnRepository.listReturnableInvoices();
        if (listListReturnableInvoices.isEmpty()) {
            linearLayout3.addView(label("\u0644\u0627 \u062a\u0648\u062c\u062f \u0641\u0648\u0627\u062a\u064a\u0631 \u0634\u0631\u0627\u0621 \u0645\u0639\u062a\u0645\u062f\u0629 \u062a\u062d\u062a\u0648\u064a \u0643\u0645\u064a\u0627\u062a \u0645\u062a\u0627\u062d\u0629 \u0645\u0646 \u0637\u0628\u0642\u0627\u062a \u0627\u0644\u0634\u0631\u0627\u0621 \u0627\u0644\u0623\u0635\u0644\u064a\u0629.", 14.0f, this.mutedColor, false, 17));
        } else {
            for (final PurchaseReturnInvoiceOption purchaseReturnInvoiceOption : listListReturnableInvoices) {
                LinearLayout linearLayout4 = new LinearLayout(mainActivity);
                linearLayout4.setOrientation(1);
                linearLayout4.setPadding(dp(13), dp(11), dp(13), dp(11));
                linearLayout4.setBackground(cardBackground());
                linearLayout4.addView(label("\u0641\u0627\u062a\u0648\u0631\u0629 " + purchaseReturnInvoiceOption.getInvoiceNumber(), 16.0f, this.green, true, 5));
                linearLayout4.addView(label(purchaseReturnInvoiceOption.getDate() + " \u2022 " + purchaseReturnInvoiceOption.getSupplierName(), 12.0f, this.mutedColor, false, 5));
                linearLayout4.addView(label("\u0627\u0644\u0625\u062c\u0645\u0627\u0644\u064a: " + moneyText(purchaseReturnInvoiceOption.getTotalUsdCents()) + " USD", 13.0f, this.textColor, true, 5));
                linearLayout4.addView(smallButton("\u0625\u0646\u0634\u0627\u0621 \u0645\u0631\u062a\u062c\u0639", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda23
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivity.showPurchaseReturns$lambda$609$lambda$608(this.f$0, purchaseReturnInvoiceOption);
                    }
                }), fieldParams(dp(42), dp(7)));
                linearLayout3.addView(linearLayout4, fieldParams(-2, dp(8)));
            }
        }
        linearLayout.addView(linearLayout2, matchWidth());
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setFillViewport(true);
        scrollView.addView(linearLayout3);
        linearLayout.addView(scrollView, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        setContentView(linearLayout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showPurchaseReturns$lambda$609$lambda$608(MainActivity mainActivity, PurchaseReturnInvoiceOption purchaseReturnInvoiceOption) throws IOException {
        mainActivity.showPurchaseReturnEditor(purchaseReturnInvoiceOption.getId());
        return Unit.INSTANCE;
    }

    private final void showPurchaseReturnEditor(final String invoiceId) throws IOException {
        Object next;
        final String str = this.currentUserId;
        if (str == null) {
            return;
        }
        MainActivity mainActivity = this;
        final PurchaseReturnRepository purchaseReturnRepository = new PurchaseReturnRepository(mainActivity);
        List<PurchaseReturnableItem> listListReturnableItems = purchaseReturnRepository.listReturnableItems(invoiceId);
        List<CashAccountSummary> listListActive = new CashAccountRepository(mainActivity).listActive();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listListActive) {
            CashAccountSummary cashAccountSummary = (CashAccountSummary) obj;
            if (Intrinsics.areEqual(cashAccountSummary.getCurrencyCode(), "SYP") || Intrinsics.areEqual(cashAccountSummary.getCurrencyCode(), "USD")) {
                arrayList.add(obj);
            }
        }
        final ArrayList arrayList2 = arrayList;
        int i = 1;
        Iterator it = PurchasePaymentRepository.listPurchaseInvoices$default(new PurchasePaymentRepository(mainActivity), null, 1, null).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((PurchaseInvoicePaymentSummary) next).getId(), invoiceId));
        PurchaseInvoicePaymentSummary purchaseInvoicePaymentSummary = (PurchaseInvoicePaymentSummary) next;
        if (purchaseInvoicePaymentSummary != null) {
            purchaseInvoicePaymentSummary.getInvoiceRateMilli();
        }
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(16), dp(8), dp(16), dp(8));
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        linearLayout.addView(label("\u0627\u0644\u0643\u0645\u064a\u0627\u062a \u0627\u0644\u0645\u0631\u0627\u062f \u0625\u0631\u062c\u0627\u0639\u0647\u0627", 16.0f, this.green, true, 5));
        for (PurchaseReturnableItem purchaseReturnableItem : listListReturnableItems) {
            LinearLayout linearLayout2 = new LinearLayout(mainActivity);
            linearLayout2.setOrientation(i);
            linearLayout2.setPadding(0, dp(4), 0, dp(4));
            linearLayout2.addView(label(purchaseReturnableItem.getProductName() + " \u2022 \u0645\u0634\u062a\u0631\u0627\u0629 " + purchaseReturnableItem.getPurchasedQuantity().toPlainString() + " \u2022 \u0633\u0628\u0642 \u0625\u0631\u062c\u0627\u0639\u0647 " + purchaseReturnableItem.getReturnedQuantity().toPlainString() + " \u2022 \u0627\u0644\u0645\u062a\u0627\u062d \u0645\u0646 \u0637\u0628\u0642\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 " + purchaseReturnableItem.getAvailableQuantity().toPlainString(), 13.0f, this.textColor, true, 5));
            EditText editTextFieldForDialog$default = fieldForDialog$default(this, "\u0627\u0644\u0643\u0645\u064a\u0629 \u0627\u0644\u0645\u0631\u062a\u062c\u0639\u0629", null, 8194, 2, null);
            linkedHashMap.put(purchaseReturnableItem.getId(), editTextFieldForDialog$default);
            linearLayout2.addView(editTextFieldForDialog$default, fieldParams(dp(44), dp(4)));
            linearLayout.addView(linearLayout2, matchWidth());
            i = 1;
        }
        StoreTime storeTime = StoreTime.INSTANCE;
        SQLiteDatabase readableDatabase = AppDatabase.INSTANCE.getInstance(mainActivity).getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        final EditText editTextFieldForDialog$default2 = fieldForDialog$default(this, "\u062a\u0627\u0631\u064a\u062e \u0627\u0644\u0645\u0631\u062a\u062c\u0639", StoreTime.today$default(storeTime, readableDatabase, null, 2, null), 0, 4, null);
        final EditText editTextFieldForDialog$default3 = fieldForDialog$default(this, "\u0633\u0628\u0628 \u0627\u0644\u0645\u0631\u062a\u062c\u0639", null, 0, 6, null);
        linearLayout.addView(editTextFieldForDialog$default2, fieldParams(dp(44), dp(6)));
        linearLayout.addView(editTextFieldForDialog$default3, fieldParams(dp(44), dp(6)));
        final TextView textViewLabel = label("\u0627\u0636\u063a\u0637 \u0645\u0639\u0627\u064a\u0646\u0629 \u0644\u062d\u0633\u0627\u0628 \u062a\u062e\u0641\u064a\u0636 \u0645\u0633\u062a\u062d\u0642 \u0627\u0644\u0645\u0648\u0631\u062f \u0648\u0627\u0644\u0645\u0628\u0644\u063a \u0627\u0644\u0645\u0633\u062a\u0631\u062f \u0646\u0642\u062f\u0627\u064b.", 13.0f, this.mutedColor, false, 5);
        linearLayout.addView(textViewLabel, fieldParams(-2, dp(8)));
        linearLayout.addView(label("\u062a\u0648\u0632\u064a\u0639 \u0627\u0644\u0645\u0628\u0644\u063a \u0627\u0644\u0645\u0633\u062a\u0631\u062f \u0645\u0646 \u0627\u0644\u0645\u0648\u0631\u062f", 15.0f, this.green, true, 5));
        linearLayout.addView(label("\u0625\u0630\u0627 \u0628\u0642\u064a \u0645\u0628\u0644\u063a \u0645\u0633\u062a\u062d\u0642 \u0644\u0643 \u0644\u062f\u0649 \u0627\u0644\u0645\u0648\u0631\u062f\u060c \u0627\u062e\u062a\u0631 \u0627\u0644\u062d\u0633\u0627\u0628 \u0623\u0648 \u0627\u0644\u062d\u0633\u0627\u0628\u0627\u062a \u0627\u0644\u062a\u064a \u0633\u062a\u0633\u062a\u0642\u0628\u0644 \u0627\u0644\u0645\u0628\u0644\u063a. \u0623\u062f\u062e\u0644 \u0643\u0644 \u0645\u0628\u0644\u063a \u0628\u0639\u0645\u0644\u0629 \u0627\u0644\u062d\u0633\u0627\u0628.", 12.0f, this.mutedColor, false, 5));
        final LinearLayout linearLayout3 = new LinearLayout(mainActivity);
        linearLayout3.setOrientation(1);
        final ArrayList arrayList3 = new ArrayList();
        linearLayout.addView(linearLayout3, matchWidth());
        linearLayout.addView(smallButton("+ \u0625\u0636\u0627\u0641\u0629 \u062d\u0633\u0627\u0628 \u0644\u0644\u062a\u0648\u0632\u064a\u0639", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda56
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showPurchaseReturnEditor$lambda$620(arrayList2, this, linearLayout3, arrayList3);
            }
        }), fieldParams(dp(40), dp(5)));
        AlertDialog.Builder title = new AlertDialog.Builder(mainActivity).setTitle("\u0625\u0646\u0634\u0627\u0621 \u0645\u0631\u062a\u062c\u0639 \u0645\u0634\u062a\u0631\u064a\u0627\u062a");
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setFillViewport(true);
        scrollView.addView(linearLayout);
        final AlertDialog alertDialogCreate = title.setView(scrollView).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setNeutralButton("\u0645\u0639\u0627\u064a\u0646\u0629", (DialogInterface.OnClickListener) null).setPositiveButton("\u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0645\u0631\u062a\u062c\u0639", (DialogInterface.OnClickListener) null).create();
        alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda67
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                MainActivity.showPurchaseReturnEditor$lambda$634(alertDialogCreate, purchaseReturnRepository, invoiceId, textViewLabel, this, arrayList3, linkedHashMap, arrayList2, linearLayout3, str, editTextFieldForDialog$default2, editTextFieldForDialog$default3, dialogInterface);
            }
        });
        alertDialogCreate.show();
    }

    private static final void showPurchaseReturnEditor$addAllocationRow$619(List<CashAccountSummary> list, MainActivity mainActivity, LinearLayout linearLayout, List<Pair<Spinner, EditText>> list2) {
        if (list.isEmpty()) {
            Toast.makeText(mainActivity, "\u0644\u0627 \u064a\u0648\u062c\u062f \u062d\u0633\u0627\u0628 USD \u0623\u0648 SYP \u0646\u0634\u0637 \u0644\u0627\u0633\u062a\u0642\u0628\u0627\u0644 \u0627\u0644\u0645\u0628\u0644\u063a", 1).show();
            return;
        }
        MainActivity mainActivity2 = mainActivity;
        LinearLayout linearLayout2 = new LinearLayout(mainActivity2);
        linearLayout2.setOrientation(1);
        linearLayout2.setPadding(0, mainActivity.dp(3), 0, mainActivity.dp(3));
        Spinner spinner = new Spinner(mainActivity2);
        List<CashAccountSummary> list3 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
        for (CashAccountSummary cashAccountSummary : list3) {
            arrayList.add(cashAccountSummary.getName() + " \u2022 " + cashAccountSummary.getCurrencyCode() + " \u2022 " + mainActivity.formatAccountBalance(cashAccountSummary.getBalanceMinor(), cashAccountSummary.getCurrencyCode()));
        }
        spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity2, android.R.layout.simple_spinner_dropdown_item, arrayList));
        EditText editTextFieldForDialog$default = fieldForDialog$default(mainActivity, "\u0627\u0644\u0645\u0628\u0644\u063a \u0645\u0646 \u0647\u0630\u0627 \u0627\u0644\u062d\u0633\u0627\u0628", null, 0, 6, null);
        linearLayout2.addView(spinner, mainActivity.fieldParams(mainActivity.dp(44), mainActivity.dp(2)));
        linearLayout2.addView(editTextFieldForDialog$default, mainActivity.fieldParams(mainActivity.dp(44), mainActivity.dp(2)));
        linearLayout.addView(linearLayout2, mainActivity.matchWidth());
        list2.add(TuplesKt.to(spinner, editTextFieldForDialog$default));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showPurchaseReturnEditor$lambda$620(List list, MainActivity mainActivity, LinearLayout linearLayout, List list2) {
        showPurchaseReturnEditor$addAllocationRow$619(list, mainActivity, linearLayout, list2);
        return Unit.INSTANCE;
    }

    private static final List<PurchaseReturnItemInput> showPurchaseReturnEditor$selectedItems$623(LinkedHashMap<String, EditText> linkedHashMap) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, EditText> entry : linkedHashMap.entrySet()) {
            String key = entry.getKey();
            String strReplace$default = StringsKt.replace$default(StringsKt.trim((CharSequence) entry.getValue().getText().toString()).toString(), ',', '.', false, 4, (Object) null);
            PurchaseReturnItemInput purchaseReturnItemInput = strReplace$default.length() == 0 ? null : new PurchaseReturnItemInput(key, new BigDecimal(strReplace$default));
            if (purchaseReturnItemInput != null) {
                arrayList.add(purchaseReturnItemInput);
            }
        }
        return arrayList;
    }

    private static final List<SupplierRefundAllocationInput> showPurchaseReturnEditor$selectedAllocations$625(List<Pair<Spinner, EditText>> list, List<CashAccountSummary> list2) {
        SupplierRefundAllocationInput supplierRefundAllocationInput;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            Spinner spinner = (Spinner) pair.component1();
            String strReplace$default = StringsKt.replace$default(StringsKt.trim((CharSequence) ((EditText) pair.component2()).getText().toString()).toString(), ',', '.', false, 4, (Object) null);
            if (strReplace$default.length() == 0) {
                supplierRefundAllocationInput = null;
            } else {
                CashAccountSummary cashAccountSummary = list2.get(spinner.getSelectedItemPosition());
                supplierRefundAllocationInput = new SupplierRefundAllocationInput(cashAccountSummary.getId(), new BigDecimal(strReplace$default).multiply(BigDecimal.valueOf(Intrinsics.areEqual(cashAccountSummary.getCurrencyCode(), "USD") ? 100L : 1L)).setScale(0, RoundingMode.UNNECESSARY).longValueExact());
            }
            if (supplierRefundAllocationInput != null) {
                arrayList.add(supplierRefundAllocationInput);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showPurchaseReturnEditor$lambda$634(final AlertDialog alertDialog, final PurchaseReturnRepository purchaseReturnRepository, final String str, final TextView textView, final MainActivity mainActivity, final List list, final LinkedHashMap linkedHashMap, final List list2, final LinearLayout linearLayout, final String str2, final EditText editText, final EditText editText2, DialogInterface dialogInterface) {
        alertDialog.getButton(-3).setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda102
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.showPurchaseReturnEditor$lambda$634$lambda$627(purchaseReturnRepository, str, textView, mainActivity, list, linkedHashMap, list2, linearLayout, view);
            }
        });
        alertDialog.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda103
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Throwable {
                MainActivity.showPurchaseReturnEditor$lambda$634$lambda$633(purchaseReturnRepository, str, str2, editText, editText2, mainActivity, alertDialog, linkedHashMap, list, list2, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showPurchaseReturnEditor$lambda$634$lambda$627(PurchaseReturnRepository purchaseReturnRepository, String str, TextView textView, MainActivity mainActivity, List list, LinkedHashMap linkedHashMap, List list2, LinearLayout linearLayout, View view) {
        try {
            List<PurchaseReturnItemInput> listShowPurchaseReturnEditor$selectedItems$623 = showPurchaseReturnEditor$selectedItems$623(linkedHashMap);
            if (listShowPurchaseReturnEditor$selectedItems$623.isEmpty()) {
                throw new IllegalArgumentException("\u0623\u062f\u062e\u0644 \u0643\u0645\u064a\u0629 \u0645\u0631\u062a\u062c\u0639\u0629 \u0644\u0635\u0646\u0641 \u0648\u0627\u062d\u062f \u0639\u0644\u0649 \u0627\u0644\u0623\u0642\u0644".toString());
            }
            PurchaseReturnPreview purchaseReturnPreviewPreviewPurchaseReturn = purchaseReturnRepository.previewPurchaseReturn(str, listShowPurchaseReturnEditor$selectedItems$623);
            textView.setText("\u0642\u064a\u0645\u0629 \u0627\u0644\u0645\u0631\u062a\u062c\u0639: " + mainActivity.moneyText(purchaseReturnPreviewPreviewPurchaseReturn.getTotalUsdCents()) + " USD\n\u062a\u062e\u0641\u064a\u0636 \u0645\u0633\u062a\u062d\u0642 \u0627\u0644\u0645\u0648\u0631\u062f: " + mainActivity.formatSypAmount(purchaseReturnPreviewPreviewPurchaseReturn.getSupplierCreditReductionSypMinor()) + " \u0644.\u0633\n\u0627\u0644\u0645\u0628\u0644\u063a \u0627\u0644\u0645\u0633\u062a\u0631\u062f \u0646\u0642\u062f\u0627\u064b: " + mainActivity.formatSypAmount(purchaseReturnPreviewPreviewPurchaseReturn.getSupplierCashRefundSypMinor()) + " \u0644.\u0633");
            if (purchaseReturnPreviewPreviewPurchaseReturn.getSupplierCashRefundSypMinor() <= 0 || !list.isEmpty()) {
                return;
            }
            showPurchaseReturnEditor$addAllocationRow$619(list2, mainActivity, linearLayout, list);
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631\u062a \u0645\u0639\u0627\u064a\u0646\u0629 \u0627\u0644\u0645\u0631\u062a\u062c\u0639";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showPurchaseReturnEditor$lambda$634$lambda$633(PurchaseReturnRepository purchaseReturnRepository, String str, String str2, EditText editText, EditText editText2, MainActivity mainActivity, AlertDialog alertDialog, LinkedHashMap linkedHashMap, List list, List list2, View view) throws Throwable {
        try {
            List<PurchaseReturnItemInput> listShowPurchaseReturnEditor$selectedItems$623 = showPurchaseReturnEditor$selectedItems$623(linkedHashMap);
            if (listShowPurchaseReturnEditor$selectedItems$623.isEmpty()) {
                throw new IllegalArgumentException("\u0623\u062f\u062e\u0644 \u0643\u0645\u064a\u0629 \u0645\u0631\u062a\u062c\u0639\u0629 \u0644\u0635\u0646\u0641 \u0648\u0627\u062d\u062f \u0639\u0644\u0649 \u0627\u0644\u0623\u0642\u0644".toString());
            }
            PurchaseReturnPreview purchaseReturnPreviewPreviewPurchaseReturn = purchaseReturnRepository.previewPurchaseReturn(str, listShowPurchaseReturnEditor$selectedItems$623);
            List<SupplierRefundAllocationInput> listShowPurchaseReturnEditor$selectedAllocations$625 = showPurchaseReturnEditor$selectedAllocations$625(list, list2);
            if (purchaseReturnPreviewPreviewPurchaseReturn.getSupplierCashRefundSypMinor() == 0) {
                if (!listShowPurchaseReturnEditor$selectedAllocations$625.isEmpty()) {
                    throw new IllegalArgumentException("\u0644\u0627 \u064a\u0648\u062c\u062f \u0645\u0628\u0644\u063a \u0645\u0633\u062a\u0631\u062f \u0646\u0642\u062f\u0627\u064b\u061b \u0627\u062d\u0630\u0641 \u062a\u0648\u0632\u064a\u0639\u0627\u062a \u0627\u0644\u0642\u0628\u0636".toString());
                }
            } else if (listShowPurchaseReturnEditor$selectedAllocations$625.isEmpty()) {
                throw new IllegalArgumentException("\u062d\u062f\u062f \u062d\u0633\u0627\u0628\u0627\u064b \u0648\u0645\u0628\u0644\u063a \u0627\u0644\u0627\u0633\u062a\u0631\u062f\u0627\u062f".toString());
            }
            List<SupplierRefundAllocationInput> list3 = listShowPurchaseReturnEditor$selectedAllocations$625;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
            Iterator<T> it = list3.iterator();
            while (it.hasNext()) {
                arrayList.add(((SupplierRefundAllocationInput) it.next()).getCashAccountId());
            }
            if (CollectionsKt.distinct(arrayList).size() != listShowPurchaseReturnEditor$selectedAllocations$625.size()) {
                throw new IllegalArgumentException("\u0644\u0627 \u062a\u0643\u0631\u0631 \u0627\u0644\u062d\u0633\u0627\u0628\u061b \u0627\u062c\u0645\u0639 \u0645\u0628\u0644\u063a\u0647 \u0641\u064a \u0633\u0637\u0631 \u0648\u0627\u062d\u062f".toString());
            }
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            Toast.makeText(mainActivity, "\u062a\u0645 \u0627\u0639\u062a\u0645\u0627\u062f \u0645\u0631\u062a\u062c\u0639 \u0627\u0644\u0645\u0634\u062a\u0631\u064a\u0627\u062a " + purchaseReturnRepository.postPurchaseReturn(str2, str, string, StringsKt.trim((CharSequence) editText.getText().toString()).toString(), editText2.getText().toString(), listShowPurchaseReturnEditor$selectedItems$623, listShowPurchaseReturnEditor$selectedAllocations$625, (896 & 128) != 0 ? null : null, (896 & 256) != 0 ? false : false, (896 & 512) != 0 ? null : null).getReturnNumber(), 1).show();
            alertDialog.dismiss();
            mainActivity.showPurchaseReturns();
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u0627\u0639\u062a\u0645\u0627\u062f \u0645\u0631\u062a\u062c\u0639 \u0627\u0644\u0645\u0634\u062a\u0631\u064a\u0627\u062a";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0292  */
    /* JADX WARN: Multi-variable type inference failed */
    private final void showExpenses() {
        LinearLayout linearLayout;
        boolean z;
        String str;
        if (!isManager() || !hasPermission(PermissionPolicy.EXPENSES_MANAGE)) {
            Toast.makeText(this, "\u0625\u062f\u0627\u0631\u0629 \u0627\u0644\u0645\u0635\u0627\u0631\u064a\u0641 \u0627\u0644\u062a\u0634\u063a\u064a\u0644\u064a\u0629 \u0645\u062a\u0627\u062d\u0629 \u0644\u0644\u0645\u062f\u064a\u0631 \u0627\u0644\u0645\u062e\u0648\u0651\u0644 \u0641\u0642\u0637", 0).show();
            return;
        }
        markScreen$default(this, "EXPENSES", null, 2, null);
        MainActivity mainActivity = this;
        OperatingExpenseRepository operatingExpenseRepository = new OperatingExpenseRepository(mainActivity, null, 2, 0 == true ? 1 : 0);
        LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        linearLayout2.setOrientation(1);
        linearLayout2.setBackgroundColor(this.backgroundColor);
        LinearLayout linearLayout3 = new LinearLayout(mainActivity);
        linearLayout3.setOrientation(0);
        linearLayout3.setGravity(16);
        linearLayout3.setPadding(dp(14), dp(10), dp(14), dp(10));
        linearLayout3.setBackgroundColor(-1);
        Button button = new Button(mainActivity);
        button.setText("\u2039");
        button.setTextSize(25.0f);
        button.setAllCaps(false);
        button.setTextColor(this.green);
        button.setBackgroundColor(0);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda15
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.showGeneralReports();
            }
        });
        linearLayout3.addView(button, new LinearLayout.LayoutParams(dp(48), dp(48)));
        linearLayout3.addView(label("\u0627\u0644\u0645\u0635\u0627\u0631\u064a\u0641 \u0627\u0644\u062a\u0634\u063a\u064a\u0644\u064a\u0629", 19.0f, this.green, true, 17), new LinearLayout.LayoutParams(0, -2, 1.0f));
        LinearLayout linearLayout4 = new LinearLayout(mainActivity);
        linearLayout4.setOrientation(1);
        linearLayout4.setPadding(dp(16), dp(16), dp(16), dp(24));
        linearLayout4.addView(label("\u0625\u062c\u0645\u0627\u0644\u064a \u0627\u0644\u0645\u0635\u0627\u0631\u064a\u0641 \u0627\u0644\u0645\u0633\u062c\u0644\u0629: " + moneyText(OperatingExpenseRepository.totalUsdCents$default(operatingExpenseRepository, null, null, 3, null)) + " USD", 17.0f, this.green, true, 5));
        TextView textViewLabel = label("\u0643\u0644 \u0645\u0635\u0631\u0648\u0641 \u064a\u064f\u0633\u062c\u0651\u0644 \u0643\u062d\u0631\u0643\u0629 \u062e\u0631\u0648\u062c \u0646\u0642\u062f\u064a\u0629\u060c \u0648\u064a\u064f\u062d\u0641\u0638 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0648\u0642\u062a \u062a\u0633\u062c\u064a\u0644\u0647 \u062d\u062a\u0649 \u0644\u0627 \u062a\u062a\u063a\u064a\u0631 \u0642\u064a\u0645\u062a\u0647 \u0627\u0644\u062a\u0627\u0631\u064a\u062e\u064a\u0629 \u0644\u0627\u062d\u0642\u0627\u064b.", 12.0f, this.mutedColor, false, 5);
        int i = 12;
        textViewLabel.setPadding(0, dp(5), 0, dp(12));
        linearLayout4.addView(textViewLabel);
        LinearLayout linearLayout5 = new LinearLayout(mainActivity);
        linearLayout5.setOrientation(0);
        Button buttonSmallButton = smallButton("\uff0b \u062a\u0633\u062c\u064a\u0644 \u0645\u0635\u0631\u0648\u0641", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showExpenses$lambda$642(this.f$0);
            }
        });
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, dp(46), 1.0f);
        layoutParams.setMarginEnd(dp(6));
        Unit unit = Unit.INSTANCE;
        linearLayout5.addView(buttonSmallButton, layoutParams);
        linearLayout5.addView(smallButton("\uff0b \u062a\u0635\u0646\u064a\u0641 \u062c\u062f\u064a\u062f", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showExpenses$lambda$644(this.f$0);
            }
        }), new LinearLayout.LayoutParams(0, dp(46), 1.0f));
        linearLayout4.addView(linearLayout5, fieldParams(dp(46), dp(10)));
        linearLayout4.addView(sectionHeading("\u0622\u062e\u0631 \u0627\u0644\u0645\u0635\u0627\u0631\u064a\u0641"));
        int i2 = 10;
        List<OperatingExpense> listListExpenses$default = OperatingExpenseRepository.listExpenses$default(operatingExpenseRepository, null, null, 150, 3, null);
        if (listListExpenses$default.isEmpty()) {
            linearLayout = linearLayout4;
            z = true;
            linearLayout.addView(label("\u0644\u0627 \u062a\u0648\u062c\u062f \u0645\u0635\u0627\u0631\u064a\u0641 \u0645\u0633\u062c\u0644\u0629 \u0628\u0639\u062f.", 13.0f, this.mutedColor, false, 17));
        } else {
            linearLayout = linearLayout4;
            z = true;
            for (OperatingExpense operatingExpense : listListExpenses$default) {
                LinearLayout linearLayout6 = new LinearLayout(mainActivity);
                linearLayout6.setOrientation(1);
                linearLayout6.setPadding(dp(i), dp(i2), dp(i), dp(i2));
                linearLayout6.setBackground(cardBackground());
                int i3 = i2;
                int i4 = i;
                linearLayout6.addView(label(operatingExpense.getCategoryName() + " \u2022 " + operatingExpense.getDate(), 15.0f, this.green, true, 5));
                linearLayout6.addView(label("\u0627\u0644\u0645\u0628\u0644\u063a: " + formatAccountBalance(operatingExpense.getAmountMinor(), operatingExpense.getCurrencyCode()) + " \u2022 \u0627\u0644\u062d\u0633\u0627\u0628: " + operatingExpense.getAccountName(), 13.0f, this.textColor, false, 5));
                String strMoneyText = moneyText(operatingExpense.getAmountUsdCents());
                String reference = operatingExpense.getReference();
                if (reference != null) {
                    str = " \u2022 \u0627\u0644\u0645\u0631\u062c\u0639: " + reference;
                    if (str == null) {
                        str = "";
                    }
                } else {
                    str = "";
                }
                linearLayout6.addView(label("\u0627\u0644\u0645\u0643\u0627\u0641\u0626 \u0627\u0644\u0645\u062d\u0627\u0633\u0628\u064a: " + strMoneyText + " USD" + str, 12.0f, this.mutedColor, false, 5));
                String notes = operatingExpense.getNotes();
                if (notes != null && !StringsKt.isBlank(notes)) {
                    linearLayout6.addView(label(operatingExpense.getNotes(), 12.0f, this.mutedColor, false, 5));
                }
                linearLayout.addView(linearLayout6, fieldParams(-2, dp(7)));
                i = i4;
                i2 = i3;
            }
        }
        r3.addView(linearLayout3, matchWidth());
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setFillViewport(z);
        scrollView.addView(linearLayout);
        r3.addView(scrollView, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        setContentView(linearLayout2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showExpenses$lambda$642(MainActivity mainActivity) {
        mainActivity.showExpenseEditor();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showExpenses$lambda$644(MainActivity mainActivity) {
        mainActivity.showExpenseCategoryDialog();
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void showExpenseCategoryDialog() {
        final String str = this.currentUserId;
        if (str == null) {
            return;
        }
        MainActivity mainActivity = this;
        final OperatingExpenseRepository operatingExpenseRepository = new OperatingExpenseRepository(mainActivity, null, 2, 0 == true ? 1 : 0);
        final EditText editTextFieldForDialog$default = fieldForDialog$default(this, "\u0627\u0633\u0645 \u062a\u0635\u0646\u064a\u0641 \u0627\u0644\u0645\u0635\u0631\u0648\u0641", null, 0, 6, null);
        new AlertDialog.Builder(mainActivity).setTitle("\u0625\u0636\u0627\u0641\u0629 \u062a\u0635\u0646\u064a\u0641 \u0645\u0635\u0631\u0648\u0641").setView(editTextFieldForDialog$default).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u062d\u0641\u0638", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda5
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.showExpenseCategoryDialog$lambda$649(operatingExpenseRepository, str, editTextFieldForDialog$default, this, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showExpenseCategoryDialog$lambda$649(OperatingExpenseRepository operatingExpenseRepository, String str, EditText editText, MainActivity mainActivity, DialogInterface dialogInterface, int i) {
        try {
            Toast.makeText(mainActivity, "\u062a\u0645\u062a \u0625\u0636\u0627\u0641\u0629 \u0627\u0644\u062a\u0635\u0646\u064a\u0641 " + operatingExpenseRepository.createCategory(str, editText.getText().toString()).getName(), 0).show();
            mainActivity.showExpenses();
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u062d\u0641\u0638 \u0627\u0644\u062a\u0635\u0646\u064a\u0641";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void showExpenseEditor() {
        final String str = this.currentUserId;
        if (str == null) {
            return;
        }
        MainActivity mainActivity = this;
        final OperatingExpenseRepository operatingExpenseRepository = new OperatingExpenseRepository(mainActivity, null, 2, 0 == true ? 1 : 0);
        final List listListCategories$default = OperatingExpenseRepository.listCategories$default(operatingExpenseRepository, false, 1, null);
        List<CashAccountSummary> listListActive = new CashAccountRepository(mainActivity).listActive();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listListActive) {
            CashAccountSummary cashAccountSummary = (CashAccountSummary) obj;
            if (Intrinsics.areEqual(cashAccountSummary.getCurrencyCode(), "USD") || Intrinsics.areEqual(cashAccountSummary.getCurrencyCode(), "SYP")) {
                arrayList.add(obj);
            }
        }
        final ArrayList arrayList2 = arrayList;
        if (listListCategories$default.isEmpty()) {
            Toast.makeText(mainActivity, "\u0623\u0636\u0641 \u062a\u0635\u0646\u064a\u0641\u0627\u064b \u0644\u0644\u0645\u0635\u0631\u0648\u0641 \u0623\u0648\u0644\u0627\u064b", 1).show();
            return;
        }
        if (arrayList2.isEmpty()) {
            Toast.makeText(mainActivity, "\u0644\u0627 \u064a\u0648\u062c\u062f \u0635\u0646\u062f\u0648\u0642 \u0623\u0648 \u0645\u062d\u0641\u0638\u0629 \u0646\u0634\u0637\u0629 \u0628\u0639\u0645\u0644\u0629 USD \u0623\u0648 SYP", 1).show();
            return;
        }
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(16), dp(8), dp(16), dp(8));
        final Spinner spinner = new Spinner(mainActivity);
        List list = listListCategories$default;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList3.add(((ExpenseCategory) it.next()).getName());
        }
        spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, arrayList3));
        Spinner spinner2 = new Spinner(mainActivity);
        ArrayList<CashAccountSummary> arrayList4 = arrayList2;
        ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList4, 10));
        for (CashAccountSummary cashAccountSummary2 : arrayList4) {
            arrayList5.add(cashAccountSummary2.getName() + " \u2022 " + cashAccountSummary2.getCurrencyCode() + " \u2022 " + formatAccountBalance(cashAccountSummary2.getBalanceMinor(), cashAccountSummary2.getCurrencyCode()));
            spinner2 = spinner2;
        }
        spinner2.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, arrayList5));
        final Spinner spinner3 = spinner2;
        final EditText editTextFieldForDialog$default = fieldForDialog$default(this, "\u0642\u064a\u0645\u0629 \u0627\u0644\u0645\u0635\u0631\u0648\u0641", null, 8194, 2, null);
        StoreTime storeTime = StoreTime.INSTANCE;
        SQLiteDatabase readableDatabase = AppDatabase.INSTANCE.getInstance(mainActivity).getReadableDatabase();
        Intrinsics.checkNotNullExpressionValue(readableDatabase, "<get-readableDatabase>(...)");
        final EditText editTextFieldForDialog$default2 = fieldForDialog$default(this, "\u062a\u0627\u0631\u064a\u062e \u0627\u0644\u0645\u0635\u0631\u0648\u0641", StoreTime.today$default(storeTime, readableDatabase, null, 2, null), 0, 4, null);
        final EditText editTextFieldForDialog$default3 = fieldForDialog$default(this, "\u0631\u0642\u0645 \u0641\u0627\u062a\u0648\u0631\u0629 \u0623\u0648 \u0645\u0631\u062c\u0639 (\u0627\u062e\u062a\u064a\u0627\u0631\u064a)", null, 0, 6, null);
        final EditText editTextFieldForDialog$default4 = fieldForDialog$default(this, "\u0628\u064a\u0627\u0646 \u0627\u0644\u0645\u0635\u0631\u0648\u0641 (\u0627\u062e\u062a\u064a\u0627\u0631\u064a)", null, 0, 6, null);
        linearLayout.addView(label("\u0627\u0644\u062a\u0635\u0646\u064a\u0641", 13.0f, this.textColor, true, 5));
        linearLayout.addView(spinner, fieldParams(dp(44), dp(3)));
        linearLayout.addView(label("\u0627\u0644\u0635\u0646\u062f\u0648\u0642 \u0623\u0648 \u0627\u0644\u0645\u062d\u0641\u0638\u0629", 13.0f, this.textColor, true, 5));
        linearLayout.addView(spinner3, fieldParams(dp(44), dp(3)));
        linearLayout.addView(editTextFieldForDialog$default, fieldParams(dp(46), dp(5)));
        linearLayout.addView(editTextFieldForDialog$default2, fieldParams(dp(44), dp(5)));
        linearLayout.addView(editTextFieldForDialog$default3, fieldParams(dp(44), dp(5)));
        linearLayout.addView(editTextFieldForDialog$default4, fieldParams(dp(44), dp(5)));
        AlertDialog.Builder title = new AlertDialog.Builder(mainActivity).setTitle("\u062a\u0633\u062c\u064a\u0644 \u0645\u0635\u0631\u0648\u0641 \u062a\u0634\u063a\u064a\u0644\u064a");
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setFillViewport(true);
        scrollView.addView(linearLayout);
        final AlertDialog alertDialogCreate = title.setView(scrollView).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u0627\u0639\u062a\u0645\u0627\u062f \u0627\u0644\u0645\u0635\u0631\u0648\u0641", (DialogInterface.OnClickListener) null).create();
        alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda73
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                MainActivity.showExpenseEditor$lambda$658$lambda$657(alertDialogCreate, editTextFieldForDialog$default, arrayList2, spinner3, operatingExpenseRepository, str, listListCategories$default, spinner, editTextFieldForDialog$default2, editTextFieldForDialog$default3, editTextFieldForDialog$default4, this, dialogInterface);
            }
        });
        alertDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showExpenseEditor$lambda$658$lambda$657(final AlertDialog alertDialog, final EditText editText, final List list, final Spinner spinner, final OperatingExpenseRepository operatingExpenseRepository, final String str, final List list2, final Spinner spinner2, final EditText editText2, final EditText editText3, final EditText editText4, final MainActivity mainActivity, DialogInterface dialogInterface) {
        alertDialog.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Throwable {
                MainActivity.showExpenseEditor$lambda$658$lambda$657$lambda$656(editText, list, spinner, operatingExpenseRepository, str, list2, spinner2, editText2, editText3, editText4, mainActivity, alertDialog, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showExpenseEditor$lambda$658$lambda$657$lambda$656(EditText editText, List list, Spinner spinner, OperatingExpenseRepository operatingExpenseRepository, String str, List list2, Spinner spinner2, EditText editText2, EditText editText3, EditText editText4, MainActivity mainActivity, AlertDialog alertDialog, View view) throws Throwable {
        try {
            String strReplace$default = StringsKt.replace$default(StringsKt.trim((CharSequence) editText.getText().toString()).toString(), ',', '.', false, 4, (Object) null);
            if (strReplace$default.length() <= 0) {
                throw new IllegalArgumentException("\u0623\u062f\u062e\u0644 \u0642\u064a\u0645\u0629 \u0627\u0644\u0645\u0635\u0631\u0648\u0641".toString());
            }
            CashAccountSummary cashAccountSummary = (CashAccountSummary) list.get(spinner.getSelectedItemPosition());
            long jLongValueExact = new BigDecimal(strReplace$default).multiply(BigDecimal.valueOf(Intrinsics.areEqual(cashAccountSummary.getCurrencyCode(), "USD") ? 100L : 1L)).setScale(0, RoundingMode.UNNECESSARY).longValueExact();
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            OperatingExpenseResult operatingExpenseResultPostExpense = operatingExpenseRepository.postExpense(str, string, ((ExpenseCategory) list2.get(spinner2.getSelectedItemPosition())).getId(), cashAccountSummary.getId(), jLongValueExact, StringsKt.trim((CharSequence) editText2.getText().toString()).toString(), editText3.getText().toString(), editText4.getText().toString());
            Toast.makeText(mainActivity, "\u062a\u0645 \u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u0645\u0635\u0631\u0648\u0641 \u0628\u0642\u064a\u0645\u0629 " + mainActivity.formatAccountBalance(operatingExpenseResultPostExpense.getAmountMinor(), operatingExpenseResultPostExpense.getCurrencyCode()), 1).show();
            alertDialog.dismiss();
            mainActivity.showExpenses();
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u0645\u0635\u0631\u0648\u0641";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    private final void showInventoryAdjustments() {
        Object next;
        String productId;
        final String str = this.currentUserId;
        if (str == null) {
            return;
        }
        MainActivity mainActivity = this;
        StoreRepository storeRepository = new StoreRepository(mainActivity);
        final InventoryAdjustmentRepository inventoryAdjustmentRepository = new InventoryAdjustmentRepository(mainActivity);
        markScreen$default(this, "INVENTORY_ADJUSTMENTS", null, 2, null);
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(this.backgroundColor);
        LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        linearLayout2.setPadding(dp(14), dp(10), dp(14), dp(10));
        linearLayout2.setBackgroundColor(-1);
        Button button = new Button(mainActivity);
        button.setText("\u2039");
        button.setTextSize(25.0f);
        button.setAllCaps(false);
        button.setTextColor(this.green);
        button.setBackgroundColor(0);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda75
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IOException {
                MainActivity.showPointOfSale$default(this.f$0, false, 1, null);
            }
        });
        linearLayout2.addView(button, new LinearLayout.LayoutParams(dp(48), dp(48)));
        linearLayout2.addView(label("\u062a\u0633\u0648\u064a\u0627\u062a \u0627\u0644\u0645\u062e\u0632\u0648\u0646", 19.0f, this.green, true, 17), new LinearLayout.LayoutParams(0, -2, 1.0f));
        LinearLayout linearLayout3 = new LinearLayout(mainActivity);
        linearLayout3.setOrientation(1);
        linearLayout3.setPadding(dp(16), dp(16), dp(16), dp(24));
        linearLayout3.addView(label("\u062a\u0633\u062c\u064a\u0644 \u0641\u0631\u0642 \u0627\u0644\u062c\u0631\u062f", 21.0f, this.green, true, 5));
        TextView textViewLabel = label("\u0623\u062f\u062e\u0644 \u0627\u0644\u0641\u0631\u0642 \u0645\u0648\u062c\u0628\u0627\u064b \u0644\u0644\u0632\u064a\u0627\u062f\u0629 \u0648\u0633\u0627\u0644\u0628\u0627\u064b \u0644\u0644\u0646\u0642\u0635\u0627\u0646. \u062a\u0643\u0644\u0641\u0629 \u0627\u0644\u0632\u064a\u0627\u062f\u0629 \u0645\u0637\u0644\u0648\u0628\u0629 \u0628\u0627\u0644\u062f\u0648\u0644\u0627\u0631 \u0644\u0644\u0648\u062d\u062f\u0629\u060c \u0623\u0645\u0627 \u0627\u0644\u0646\u0642\u0635 \u0641\u064a\u064f\u062d\u0633\u0628 \u062a\u0644\u0642\u0627\u0626\u064a\u0627\u064b \u0648\u0641\u0642 FIFO.", 13.0f, this.mutedColor, false, 5);
        textViewLabel.setPadding(0, dp(5), 0, dp(12));
        linearLayout3.addView(textViewLabel);
        List<StoreProduct> listListProducts = storeRepository.listProducts();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listListProducts) {
            if (((StoreProduct) obj).getTracksInventory()) {
                arrayList.add(obj);
            }
        }
        final ArrayList arrayList2 = arrayList;
        if (arrayList2.isEmpty()) {
            linearLayout3.addView(label("\u0644\u0627 \u062a\u0648\u062c\u062f \u0623\u0635\u0646\u0627\u0641 \u062a\u062a\u0628\u0639 \u0627\u0644\u0645\u062e\u0632\u0648\u0646.", 14.0f, this.mutedColor, false, 17));
        } else {
            final Spinner spinner = new Spinner(mainActivity);
            ArrayList<StoreProduct> arrayList3 = arrayList2;
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
            for (StoreProduct storeProduct : arrayList3) {
                arrayList4.add(storeProduct.getName() + " \u2022 \u0627\u0644\u0631\u0635\u064a\u062f " + storeProduct.getStock());
            }
            spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, arrayList4));
            final EditText editTextFieldForDialog$default = fieldForDialog$default(this, "\u0641\u0631\u0642 \u0627\u0644\u0643\u0645\u064a\u0629: \u0645\u062b\u0627\u0644 3 \u0623\u0648 -2", null, 12290, 2, null);
            final EditText editTextFieldForDialog$default2 = fieldForDialog$default(this, "\u062a\u0643\u0644\u0641\u0629 \u0634\u0631\u0627\u0621 \u0627\u0644\u0648\u062d\u062f\u0629 \u0628\u0627\u0644\u062f\u0648\u0644\u0627\u0631 (\u0644\u0644\u0632\u064a\u0627\u062f\u0629 \u0641\u0642\u0637)", null, 8194, 2, null);
            final EditText editTextFieldForDialog$default3 = fieldForDialog$default(this, "\u0633\u0628\u0628 \u0627\u0644\u062a\u0633\u0648\u064a\u0629", null, 0, 6, null);
            linearLayout3.addView(label("\u0627\u0644\u0635\u0646\u0641", 13.0f, this.textColor, true, 5));
            linearLayout3.addView(spinner, fieldParams(dp(48), dp(5)));
            linearLayout3.addView(editTextFieldForDialog$default, fieldParams(dp(48), dp(8)));
            linearLayout3.addView(editTextFieldForDialog$default2, fieldParams(dp(48), dp(8)));
            linearLayout3.addView(editTextFieldForDialog$default3, fieldParams(dp(48), dp(8)));
            linearLayout3.addView(smallButton("\u0627\u0639\u062a\u0645\u0627\u062f \u062a\u0633\u0648\u064a\u0629 \u0627\u0644\u0645\u062e\u0632\u0648\u0646", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda76
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MainActivity.showInventoryAdjustments$lambda$668(editTextFieldForDialog$default, editTextFieldForDialog$default2, inventoryAdjustmentRepository, str, arrayList2, spinner, editTextFieldForDialog$default3, this);
                }
            }), fieldParams(dp(48), dp(10)));
        }
        linearLayout3.addView(sectionHeading("\u0622\u062e\u0631 \u062a\u0633\u0648\u064a\u0627\u062a \u0627\u0644\u062c\u0631\u062f"));
        for (InventoryAdjustmentResult inventoryAdjustmentResult : inventoryAdjustmentRepository.listRecent(50)) {
            Iterator it = arrayList2.iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!Intrinsics.areEqual(((StoreProduct) next).getId(), inventoryAdjustmentResult.getProductId()));
            StoreProduct storeProduct2 = (StoreProduct) next;
            if (storeProduct2 == null || (productId = storeProduct2.getName()) == null) {
                productId = inventoryAdjustmentResult.getProductId();
            }
            LinearLayout linearLayout4 = new LinearLayout(mainActivity);
            linearLayout4.setOrientation(1);
            linearLayout4.setPadding(dp(12), dp(10), dp(12), dp(10));
            linearLayout4.setBackground(cardBackground());
            linearLayout4.addView(label(productId, 15.0f, this.green, true, 5));
            String str2 = Intrinsics.areEqual(inventoryAdjustmentResult.getAdjustmentType(), "INCREASE") ? "\u0632\u064a\u0627\u062f\u0629 " : "\u0646\u0642\u0635\u0627\u0646 ";
            linearLayout4.addView(label(str2 + inventoryAdjustmentResult.getQuantity().toPlainString() + " \u2022 " + inventoryAdjustmentResult.getQuantityBefore().toPlainString() + " \u2190 " + inventoryAdjustmentResult.getQuantityAfter().toPlainString(), 13.0f, this.textColor, true, 5));
            String reason = inventoryAdjustmentResult.getReason();
            StringBuilder sb = new StringBuilder("\u0627\u0644\u0633\u0628\u0628: ");
            sb.append(reason);
            linearLayout4.addView(label(sb.toString(), 12.0f, this.mutedColor, false, 5));
            linearLayout3.addView(linearLayout4, fieldParams(-2, dp(7)));
        }
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setFillViewport(true);
        scrollView.addView(linearLayout3);
        linearLayout.addView(linearLayout2, matchWidth());
        linearLayout.addView(scrollView, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        setContentView(linearLayout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showInventoryAdjustments$lambda$668(EditText editText, EditText editText2, InventoryAdjustmentRepository inventoryAdjustmentRepository, String str, List list, Spinner spinner, EditText editText3, MainActivity mainActivity) {
        Long lValueOf;
        try {
            BigDecimal bigDecimal = new BigDecimal(StringsKt.replace$default(StringsKt.trim((CharSequence) editText.getText().toString()).toString(), ',', '.', false, 4, (Object) null));
            String strReplace$default = StringsKt.replace$default(StringsKt.trim((CharSequence) editText2.getText().toString()).toString(), ',', '.', false, 4, (Object) null);
            if (bigDecimal.signum() > 0) {
                lValueOf = Long.valueOf(new BigDecimal(strReplace$default).multiply(BigDecimal.valueOf(100L)).setScale(0, RoundingMode.HALF_UP).longValueExact());
            } else {
                if (strReplace$default.length() != 0) {
                    throw new IllegalArgumentException("\u0644\u0627 \u062a\u062f\u062e\u0644 \u062a\u0643\u0644\u0641\u0629 \u0644\u0644\u0646\u0642\u0635\u061b \u062a\u064f\u062d\u0633\u0628 \u062a\u0644\u0642\u0627\u0626\u064a\u0627\u064b \u0648\u0641\u0642 FIFO".toString());
                }
                lValueOf = null;
            }
            String id = ((StoreProduct) list.get(spinner.getSelectedItemPosition())).getId();
            String string = editText3.getText().toString();
            String string2 = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
            Toast.makeText(mainActivity, "\u062a\u0645\u062a \u0627\u0644\u062a\u0633\u0648\u064a\u0629. \u0627\u0644\u0631\u0635\u064a\u062f \u0627\u0644\u062c\u062f\u064a\u062f: " + inventoryAdjustmentRepository.postAdjustment(str, id, bigDecimal, string, string2, (448 & 32) != 0 ? null : lValueOf, (448 & 64) != 0 ? null : null, (448 & 128) != 0 ? null : null, (448 & 256) != 0 ? false : false).getQuantityAfter(), 1).show();
            mainActivity.showInventoryAdjustments();
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u062a\u0633\u062c\u064a\u0644 \u0627\u0644\u062a\u0633\u0648\u064a\u0629";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
        return Unit.INSTANCE;
    }

    private final void showProductManagement() throws IOException {
        if (!isManager()) {
            Toast.makeText(this, "\u0625\u062f\u0627\u0631\u0629 \u0627\u0644\u0645\u0646\u062a\u062c\u0627\u062a \u0648\u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0645\u062a\u0627\u062d\u0629 \u0644\u0644\u0645\u062f\u064a\u0631 \u0641\u0642\u0637", 1).show();
            showPointOfSale$default(this, false, 1, null);
            return;
        }
        markScreen$default(this, "PRODUCTS", null, 2, null);
        MainActivity mainActivity = this;
        final StoreRepository storeRepository = new StoreRepository(mainActivity);
        storeRepository.ensureBaseSetup();
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(this.backgroundColor);
        LinearLayout linearLayout2 = new LinearLayout(mainActivity);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        linearLayout2.setPadding(dp(14), dp(10), dp(14), dp(10));
        linearLayout2.setBackgroundColor(-1);
        Button button = new Button(mainActivity);
        button.setText("\u2039");
        button.setTextSize(25.0f);
        button.setTextColor(this.green);
        button.setAllCaps(false);
        button.setBackgroundColor(0);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda122
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IOException {
                MainActivity.showPointOfSale$default(this.f$0, false, 1, null);
            }
        });
        linearLayout2.addView(button, new LinearLayout.LayoutParams(dp(48), dp(48)));
        linearLayout2.addView(label("\u0625\u062f\u0627\u0631\u0629 \u0627\u0644\u0645\u0646\u062a\u062c\u0627\u062a", 19.0f, this.green, true, 17), new LinearLayout.LayoutParams(0, -2, 1.0f));
        ScrollView scrollView = new ScrollView(mainActivity);
        scrollView.setFillViewport(true);
        LinearLayout linearLayout3 = new LinearLayout(mainActivity);
        linearLayout3.setOrientation(1);
        linearLayout3.setPadding(dp(16), dp(16), dp(16), dp(24));
        final EditText editText = new EditText(mainActivity);
        editText.setHint("\u0627\u0628\u062d\u062b \u0628\u0627\u0644\u0627\u0633\u0645 \u0623\u0648 \u0627\u0644\u0628\u0627\u0631\u0643\u0648\u062f \u0623\u0648 SKU");
        editText.setTextSize(14.0f);
        editText.setSingleLine(true);
        editText.setPadding(dp(14), 0, dp(14), 0);
        editText.setBackground(inputBackground());
        Button button2 = new Button(mainActivity);
        button2.setText("+ \u0625\u0636\u0627\u0641\u0629 \u0645\u0646\u062a\u062c \u062c\u062f\u064a\u062f");
        button2.setTextSize(16.0f);
        button2.setAllCaps(false);
        button2.setTextColor(-1);
        button2.setBackgroundColor(this.green);
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda123
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.showProductEditor$default(this.f$0, null, null, 2, null);
            }
        });
        Button button3 = new Button(mainActivity);
        button3.setText("\u0625\u0636\u0627\u0641\u0629 \u062a\u0635\u0646\u064a\u0641");
        button3.setTextSize(14.0f);
        button3.setAllCaps(false);
        button3.setTextColor(this.green);
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda124
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.showAddCategoryDialog();
            }
        });
        final LinearLayout linearLayout4 = new LinearLayout(mainActivity);
        linearLayout4.setOrientation(1);
        linearLayout3.addView(label("\u0643\u062a\u0627\u0644\u0648\u062c \u0627\u0644\u0645\u062a\u062c\u0631", 24.0f, this.green, true, 5));
        TextView textViewLabel = label("\u0623\u062f\u062e\u0644 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0645\u0646\u062a\u062c\u0627\u062a \u0645\u0631\u0629 \u0648\u0627\u062d\u062f\u0629 \u0644\u062a\u0638\u0647\u0631 \u0645\u0628\u0627\u0634\u0631\u0629 \u0641\u064a \u0646\u0642\u0637\u0629 \u0627\u0644\u0628\u064a\u0639", 13.0f, this.mutedColor, false, 5);
        textViewLabel.setPadding(0, dp(4), 0, dp(14));
        linearLayout3.addView(textViewLabel);
        TextView textViewLabel2 = label("\u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0627\u0644\u062d\u0627\u0644\u064a (\u0644\u064a\u0631\u0629 \u0633\u0648\u0631\u064a\u0629 \u0644\u0643\u0644 1 \u062f\u0648\u0644\u0627\u0631)", 14.0f, this.green, true, 5);
        long currentExchangeRateMilli = storeRepository.getCurrentExchangeRateMilli();
        final EditText editText2 = new EditText(mainActivity);
        editText2.setHint("\u0645\u062b\u0627\u0644: 15000");
        editText2.setInputType(8194);
        editText2.setSingleLine(true);
        editText2.setTextSize(15.0f);
        editText2.setPadding(dp(12), 0, dp(12), 0);
        editText2.setBackground(inputBackground());
        if (currentExchangeRateMilli > 0) {
            editText2.setText(MainActivity$$ExternalSyntheticBackport0.m(BigDecimal.valueOf(currentExchangeRateMilli, 3)).toPlainString());
        }
        Button button4 = new Button(mainActivity);
        button4.setText("\u062d\u0641\u0638 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641");
        button4.setAllCaps(false);
        button4.setTextColor(-1);
        button4.setBackgroundColor(this.green);
        button4.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda125
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.showProductManagement$lambda$688$lambda$687(editText2, storeRepository, this, view);
            }
        });
        linearLayout3.addView(textViewLabel2, matchWidth());
        linearLayout3.addView(editText2, fieldParams(dp(48), dp(6)));
        linearLayout3.addView(button4, fieldParams(dp(46), dp(8)));
        linearLayout3.addView(button2, fieldParams(dp(52), dp(8)));
        Button button5 = new Button(mainActivity);
        button5.setText("\u0627\u0633\u062a\u064a\u0631\u0627\u062f \u0623\u0635\u0646\u0627\u0641 \u0645\u0646 CSV / Excel");
        button5.setTextSize(14.0f);
        button5.setAllCaps(false);
        button5.setTextColor(this.green);
        button5.setBackgroundColor(-1);
        button5.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda126
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.showProductManagement$lambda$690$lambda$689(this.f$0, view);
            }
        });
        linearLayout3.addView(button5, fieldParams(dp(48), dp(5)));
        linearLayout3.addView(button3, fieldParams(dp(44), dp(14)));
        linearLayout3.addView(editText, fieldParams(dp(50), dp(12)));
        linearLayout3.addView(linearLayout4, matchWidth());
        scrollView.addView(linearLayout3);
        linearLayout.addView(linearLayout2, matchWidth());
        linearLayout.addView(scrollView, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        setContentView(linearLayout);
        editText.addTextChangedListener(new TextWatcher() { // from class: com.hazem.dailystore.MainActivity.showProductManagement.2
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditText editText3 = editText;
                LinearLayout linearLayout5 = linearLayout4;
                StoreRepository storeRepository2 = storeRepository;
                MainActivity mainActivity2 = this;
                String string = s != null ? s.toString() : null;
                if (string == null) {
                    string = "";
                }
                MainActivity.showProductManagement$refresh$704(editText3, linearLayout5, storeRepository2, mainActivity2, string);
            }
        });
        showProductManagement$refresh$704(editText, linearLayout4, storeRepository, this, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showProductManagement$lambda$688$lambda$687(EditText editText, StoreRepository storeRepository, MainActivity mainActivity, View view) {
        try {
            BigDecimal bigDecimal = new BigDecimal(StringsKt.replace$default(StringsKt.trim((CharSequence) editText.getText().toString()).toString(), ',', '.', false, 4, (Object) null));
            String str = mainActivity.currentUserId;
            if (str == null) {
                throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u062f\u064a\u0631 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
            }
            storeRepository.saveExchangeRate(bigDecimal, str);
            Toast.makeText(mainActivity, "\u062a\u0645 \u062a\u062d\u062f\u064a\u062b \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641\u061b \u0628\u0642\u064a\u062a \u0623\u0633\u0639\u0627\u0631 \u0627\u0644\u062f\u0648\u0644\u0627\u0631 \u0627\u0644\u0645\u0631\u062c\u0639\u064a\u0629 \u062b\u0627\u0628\u062a\u0629", 1).show();
            mainActivity.showProductManagement();
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u0623\u062f\u062e\u0644 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0635\u062d\u064a\u062d\u0627\u064b";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showProductManagement$lambda$690$lambda$689(MainActivity mainActivity, View view) {
        if (!mainActivity.isManager()) {
            Toast.makeText(mainActivity, "\u0627\u0633\u062a\u064a\u0631\u0627\u062f \u0627\u0644\u0623\u0635\u0646\u0627\u0641 \u0645\u062a\u0627\u062d \u0644\u0644\u0645\u062f\u064a\u0631 \u0641\u0642\u0637", 1).show();
            return;
        }
        try {
            mainActivity.startActivityForResult(Intent.createChooser(new Intent("android.intent.action.OPEN_DOCUMENT_TREE"), "\u0627\u062e\u062a\u0631 \u0627\u0644\u0645\u062c\u0644\u062f \u0627\u0644\u0630\u064a \u064a\u062d\u062a\u0648\u064a \u0645\u0644\u0641 \u0627\u0644\u0645\u0646\u062a\u062c\u0627\u062a"), mainActivity.productImportFolderRequestCode);
        } catch (Exception unused) {
            Toast.makeText(mainActivity, "\u062a\u0639\u0630\u0631 \u0641\u062a\u062d \u0645\u0646\u062a\u0642\u064a \u0627\u0644\u0645\u062c\u0644\u062f\u0627\u062a \u0639\u0644\u0649 \u0647\u0630\u0627 \u0627\u0644\u062c\u0647\u0627\u0632", 1).show();
        }
    }

    static /* synthetic */ void showProductManagement$refresh$704$default(EditText editText, LinearLayout linearLayout, StoreRepository storeRepository, MainActivity mainActivity, String str, int i, Object obj) {
        if ((i & 16) != 0) {
            str = editText.getText().toString();
        }
        showProductManagement$refresh$704(editText, linearLayout, storeRepository, mainActivity, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showProductManagement$refresh$704(final EditText editText, final LinearLayout linearLayout, final StoreRepository storeRepository, final MainActivity mainActivity, String str) {
        int i;
        String barcode;
        String sku;
        linearLayout.removeAllViews();
        List<StoreProduct> listListProducts = storeRepository.listProducts();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listListProducts.iterator();
        while (true) {
            i = 1;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            StoreProduct storeProduct = (StoreProduct) next;
            String str2 = str;
            if (StringsKt.isBlank(str2) || StringsKt.contains((CharSequence) storeProduct.getName(), (CharSequence) str2, true) || (((barcode = storeProduct.getBarcode()) != null && StringsKt.contains((CharSequence) barcode, (CharSequence) str2, true)) || ((sku = storeProduct.getSku()) != null && StringsKt.contains((CharSequence) sku, (CharSequence) str2, true)))) {
                arrayList.add(next);
            }
        }
        ArrayList<StoreProduct> arrayList2 = arrayList;
        if (arrayList2.isEmpty()) {
            TextView textViewLabel = mainActivity.label("\u0644\u0627 \u062a\u0648\u062c\u062f \u0645\u0646\u062a\u062c\u0627\u062a \u0628\u0639\u062f. \u0627\u0628\u062f\u0623 \u0628\u0625\u0636\u0627\u0641\u0629 \u0623\u0648\u0644 \u0645\u0646\u062a\u062c \u0625\u0644\u0649 \u0645\u062a\u062c\u0631\u0643.", 14.0f, mainActivity.mutedColor, false, 17);
            textViewLabel.setPadding(mainActivity.dp(12), mainActivity.dp(32), mainActivity.dp(12), mainActivity.dp(32));
            linearLayout.addView(textViewLabel);
        }
        for (final StoreProduct storeProduct2 : arrayList2) {
            MainActivity mainActivity2 = mainActivity;
            LinearLayout linearLayout2 = new LinearLayout(mainActivity2);
            linearLayout2.setOrientation(i);
            linearLayout2.setPadding(mainActivity.dp(14), mainActivity.dp(12), mainActivity.dp(14), mainActivity.dp(12));
            linearLayout2.setBackground(mainActivity.cardBackground());
            linearLayout2.addView(mainActivity.label(storeProduct2.getName(), 16.0f, mainActivity.green, true, 5));
            String barcode2 = storeProduct2.getBarcode();
            if (barcode2 == null) {
                barcode2 = "\u2014";
            }
            String sku2 = storeProduct2.getSku();
            TextView textViewLabel2 = mainActivity.label("\u0628\u0627\u0631\u0643\u0648\u062f: " + barcode2 + "   |   SKU: " + (sku2 != null ? sku2 : "\u2014"), 11.0f, mainActivity.mutedColor, false, 5);
            textViewLabel2.setPadding(0, mainActivity.dp(5), 0, mainActivity.dp(3));
            linearLayout2.addView(textViewLabel2);
            String categoryName = storeProduct2.getCategoryName();
            if (categoryName == null) {
                categoryName = "\u063a\u064a\u0631 \u0645\u062d\u062f\u062f";
            }
            linearLayout2.addView(mainActivity.label("\u0627\u0644\u062a\u0635\u0646\u064a\u0641: " + categoryName + "   \u2022   \u0627\u0644\u0648\u062d\u062f\u0629: " + storeProduct2.getUnitName(), 12.0f, mainActivity.mutedColor, false, 5));
            long currentExchangeRateMilli = storeRepository.getCurrentExchangeRateMilli();
            TextView textViewLabel3 = mainActivity.label("\u062a\u0643\u0644\u0641\u0629 \u0627\u0644\u0634\u0631\u0627\u0621: " + mainActivity.formatDualMoney(storeProduct2.getPurchaseCostUsdCents(), currentExchangeRateMilli), 13.0f, mainActivity.textColor, true, 5);
            textViewLabel3.setPadding(0, mainActivity.dp(5), 0, mainActivity.dp(2));
            linearLayout2.addView(textViewLabel3);
            TextView textViewLabel4 = mainActivity.label("\u0633\u0639\u0631 \u0627\u0644\u0645\u0628\u064a\u0639: " + mainActivity.formatDualMoney(storeProduct2.getSalePriceUsdCents(), currentExchangeRateMilli), 13.0f, mainActivity.green, true, 5);
            textViewLabel4.setPadding(0, 0, 0, mainActivity.dp(3));
            linearLayout2.addView(textViewLabel4);
            linearLayout2.addView(mainActivity.label(storeProduct2.getTracksInventory() ? "\u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u0627\u0644\u0645\u062a\u0627\u062d: " + mainActivity.formatQuantity(storeProduct2.getStock()) + " " + storeProduct2.getUnitSymbol() : "\u0644\u0627 \u064a\u062a\u062a\u0628\u0639 \u0627\u0644\u0645\u062e\u0632\u0648\u0646", 12.0f, mainActivity.mutedColor, false, 5));
            LinearLayout linearLayout3 = new LinearLayout(mainActivity2);
            linearLayout3.setOrientation(0);
            linearLayout3.setGravity(16);
            Button buttonSmallButton = mainActivity.smallButton("\u062a\u0639\u062f\u064a\u0644", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda129
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MainActivity.showProductManagement$refresh$704$lambda$703$lambda$698(this.f$0, storeProduct2);
                }
            });
            Button buttonSmallButton2 = mainActivity.smallButton("\u0625\u064a\u0642\u0627\u0641", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda130
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MainActivity.showProductManagement$refresh$704$lambda$703$lambda$700(this.f$0, storeProduct2, storeRepository, editText, linearLayout);
                }
            });
            linearLayout3.addView(buttonSmallButton, new LinearLayout.LayoutParams(0, mainActivity.dp(42), 1.0f));
            if (mainActivity.isManager() && storeProduct2.getTracksInventory() && storeProduct2.getStock() <= 1.0E-7d) {
                if (storeRepository.canRecordOpeningStock(storeProduct2.getId())) {
                    linearLayout3.addView(mainActivity.smallButton("\u062a\u0633\u062c\u064a\u0644 \u0631\u0635\u064a\u062f \u0627\u0644\u062c\u0631\u062f", new Function0() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda132
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivity.showProductManagement$refresh$704$lambda$703$lambda$701(this.f$0, storeProduct2);
                        }
                    }), new LinearLayout.LayoutParams(0, mainActivity.dp(42), 1.0f));
                }
            }
            linearLayout3.addView(buttonSmallButton2, new LinearLayout.LayoutParams(0, mainActivity.dp(42), 1.0f));
            linearLayout2.addView(linearLayout3);
            LinearLayout.LayoutParams layoutParamsMatchWidth = mainActivity.matchWidth();
            layoutParamsMatchWidth.bottomMargin = mainActivity.dp(10);
            linearLayout.addView(linearLayout2, layoutParamsMatchWidth);
            i = 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showProductManagement$refresh$704$lambda$703$lambda$698(MainActivity mainActivity, StoreProduct storeProduct) {
        showProductEditor$default(mainActivity, storeProduct, null, 2, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showProductManagement$refresh$704$lambda$703$lambda$700(final MainActivity mainActivity, final StoreProduct storeProduct, final StoreRepository storeRepository, final EditText editText, final LinearLayout linearLayout) {
        new AlertDialog.Builder(mainActivity).setTitle("\u0625\u064a\u0642\u0627\u0641 \u0627\u0644\u0645\u0646\u062a\u062c").setMessage("\u0633\u064a\u062e\u062a\u0641\u064a " + storeProduct.getName() + " \u0645\u0646 \u0646\u0642\u0637\u0629 \u0627\u0644\u0628\u064a\u0639\u060c \u0645\u0639 \u0627\u0644\u0627\u062d\u062a\u0641\u0627\u0638 \u0628\u0633\u062c\u0644\u0627\u062a\u0647.").setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u0625\u064a\u0642\u0627\u0641", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda152
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.showProductManagement$refresh$704$lambda$703$lambda$700$lambda$699(storeRepository, storeProduct, mainActivity, editText, linearLayout, dialogInterface, i);
            }
        }).show();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showProductManagement$refresh$704$lambda$703$lambda$700$lambda$699(StoreRepository storeRepository, StoreProduct storeProduct, MainActivity mainActivity, EditText editText, LinearLayout linearLayout, DialogInterface dialogInterface, int i) {
        try {
            String id = storeProduct.getId();
            String str = mainActivity.currentUserId;
            if (str == null) {
                throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u062f\u064a\u0631 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
            }
            storeRepository.setProductActive(id, false, str);
            showProductManagement$refresh$704$default(editText, linearLayout, storeRepository, mainActivity, null, 16, null);
            Toast.makeText(mainActivity, "\u062a\u0645 \u0625\u064a\u0642\u0627\u0641 \u0627\u0644\u0645\u0646\u062a\u062c", 0).show();
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u0625\u064a\u0642\u0627\u0641 \u0627\u0644\u0645\u0646\u062a\u062c";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showProductManagement$refresh$704$lambda$703$lambda$701(MainActivity mainActivity, StoreProduct storeProduct) {
        mainActivity.showOpeningStockDialog(storeProduct);
        return Unit.INSTANCE;
    }

    private final void showOpeningStockDialog(final StoreProduct product) {
        if (isManager()) {
            if (product.getPurchaseCostUsdCents() <= 0) {
                new AlertDialog.Builder(this).setTitle("\u062a\u0643\u0644\u0641\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 \u063a\u064a\u0631 \u0645\u062d\u062f\u062f\u0629").setMessage("\u062d\u062f\u0651\u062b \u062a\u0643\u0644\u0641\u0629 \u0634\u0631\u0627\u0621 \u0627\u0644\u0645\u0646\u062a\u062c \u0623\u0648\u0644\u0627\u064b\u061b \u0644\u0627 \u064a\u0645\u0643\u0646 \u0625\u062f\u062e\u0627\u0644 \u0631\u0635\u064a\u062f \u062c\u0631\u062f \u0641\u064a \u0631\u0623\u0633 \u0627\u0644\u0645\u0627\u0644 \u0628\u062a\u0643\u0644\u0641\u0629 \u0645\u062c\u0647\u0648\u0644\u0629.").setPositiveButton("\u062d\u0633\u0646\u0627\u064b", (DialogInterface.OnClickListener) null).show();
                return;
            }
            MainActivity mainActivity = this;
            LinearLayout linearLayout = new LinearLayout(mainActivity);
            linearLayout.setOrientation(1);
            linearLayout.setPadding(dp(18), dp(8), dp(18), dp(4));
            final EditText editText = new EditText(mainActivity);
            editText.setHint("\u0627\u0644\u0643\u0645\u064a\u0629 \u0627\u0644\u0641\u0639\u0644\u064a\u0629 \u0628\u0639\u062f \u0627\u0644\u062c\u0631\u062f");
            editText.setInputType(8194);
            editText.setSingleLine(true);
            editText.setTextSize(15.0f);
            editText.setPadding(dp(12), 0, dp(12), 0);
            editText.setBackground(inputBackground());
            final EditText editText2 = new EditText(mainActivity);
            editText2.setHint("\u0645\u0644\u0627\u062d\u0638\u0629 \u0627\u0644\u062c\u0631\u062f (\u0627\u062e\u062a\u064a\u0627\u0631\u064a)");
            editText2.setSingleLine(true);
            editText2.setTextSize(14.0f);
            editText2.setPadding(dp(12), 0, dp(12), 0);
            editText2.setBackground(inputBackground());
            linearLayout.addView(label("\u0627\u0644\u0635\u0646\u0641: " + product.getName(), 15.0f, this.green, true, 5));
            TextView textViewLabel = label("\u062a\u0643\u0644\u0641\u0629 \u0627\u0644\u0648\u062d\u062f\u0629 \u0627\u0644\u0645\u0639\u062a\u0645\u062f\u0629: " + formatDualMoney(product.getPurchaseCostUsdCents(), new StoreRepository(mainActivity).getCurrentExchangeRateMilli()), 12.0f, this.mutedColor, false, 5);
            textViewLabel.setPadding(0, dp(5), 0, dp(8));
            linearLayout.addView(textViewLabel);
            linearLayout.addView(editText, fieldParams(dp(48), dp(8)));
            linearLayout.addView(editText2, fieldParams(dp(48), dp(5)));
            final AlertDialog alertDialogCreate = new AlertDialog.Builder(mainActivity).setTitle("\u062a\u0633\u062c\u064a\u0644 \u0631\u0635\u064a\u062f \u0627\u0644\u062c\u0631\u062f \u0627\u0644\u0627\u0641\u062a\u062a\u0627\u062d\u064a").setMessage("\u0633\u064a\u064f\u0633\u062c\u0651\u0644 \u0647\u0630\u0627 \u0627\u0644\u0631\u0635\u064a\u062f \u0645\u0631\u0629 \u0648\u0627\u062d\u062f\u0629 \u0641\u0642\u0637\u060c \u0648\u062a\u064f\u062d\u0641\u0638 \u062a\u0643\u0644\u0641\u062a\u0647 \u0627\u0644\u062d\u0627\u0644\u064a\u0629 \u0643\u0637\u0628\u0642\u0629 \u0627\u0641\u062a\u062a\u0627\u062d\u064a\u0629 FIFO. \u0644\u0627 \u062a\u0633\u062a\u062e\u062f\u0645\u0647 \u0644\u062a\u0639\u062f\u064a\u0644 \u0645\u062e\u0632\u0648\u0646 \u0633\u0628\u0642 \u0623\u0646 \u062a\u062d\u0631\u0643.").setView(linearLayout).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u062d\u0641\u0638 \u0627\u0644\u0631\u0635\u064a\u062f", (DialogInterface.OnClickListener) null).create();
            alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda58
                @Override // android.content.DialogInterface.OnShowListener
                public final void onShow(DialogInterface dialogInterface) {
                    MainActivity.showOpeningStockDialog$lambda$711(alertDialogCreate, editText, this, product, editText2, dialogInterface);
                }
            });
            alertDialogCreate.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showOpeningStockDialog$lambda$711(final AlertDialog alertDialog, final EditText editText, final MainActivity mainActivity, final StoreProduct storeProduct, final EditText editText2, DialogInterface dialogInterface) {
        alertDialog.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda113
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.showOpeningStockDialog$lambda$711$lambda$710(editText, mainActivity, storeProduct, editText2, alertDialog, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showOpeningStockDialog$lambda$711$lambda$710(EditText editText, MainActivity mainActivity, StoreProduct storeProduct, EditText editText2, AlertDialog alertDialog, View view) {
        try {
            double dDoubleValue = new BigDecimal(StringsKt.replace$default(StringsKt.trim((CharSequence) editText.getText().toString()).toString(), ',', '.', false, 4, (Object) null)).doubleValue();
            StoreRepository storeRepository = new StoreRepository(mainActivity);
            String id = storeProduct.getId();
            String str = mainActivity.currentUserId;
            if (str == null) {
                throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u062f\u064a\u0631 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
            }
            String string = StringsKt.trim((CharSequence) editText2.getText().toString()).toString();
            if (string.length() == 0) {
                string = null;
            }
            storeRepository.recordOpeningStock(id, dDoubleValue, str, string);
            alertDialog.dismiss();
            Toast.makeText(mainActivity, "\u062a\u0645 \u062a\u0633\u062c\u064a\u0644 \u0631\u0635\u064a\u062f \u0627\u0644\u062c\u0631\u062f \u0648\u062a\u0643\u0644\u0641\u062a\u0647 \u0627\u0644\u0627\u0641\u062a\u062a\u0627\u062d\u064a\u0629", 1).show();
            mainActivity.showProductManagement();
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u062a\u0633\u062c\u064a\u0644 \u0631\u0635\u064a\u062f \u0627\u0644\u062c\u0631\u062f";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void showProductEditor$default(MainActivity mainActivity, StoreProduct storeProduct, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = null;
        }
        mainActivity.showProductEditor(storeProduct, function0);
    }

    /* JADX WARN: Code duplicated, block: B:102:0x0421  */
    /* JADX WARN: Code duplicated, block: B:103:0x0424  */
    /* JADX WARN: Code duplicated, block: B:108:0x01fb A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:109:0x01fd A[EDGE_INSN: B:109:0x01fd->B:74:0x01fd BREAK  A[LOOP:2: B:67:0x01db->B:72:0x01f8], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:110:0x0221 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:40:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:59:0x0150 A[LOOP:0: B:57:0x014a->B:59:0x0150, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:63:0x01b1 A[LOOP:1: B:61:0x01ab->B:63:0x01b1, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:66:0x01d6  */
    /* JADX WARN: Code duplicated, block: B:69:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:72:0x01f8 A[LOOP:2: B:67:0x01db->B:72:0x01f8, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:75:0x01ff  */
    /* JADX WARN: Code duplicated, block: B:79:0x020d  */
    /* JADX WARN: Code duplicated, block: B:82:0x0224 A[LOOP:3: B:77:0x0207->B:82:0x0224, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:85:0x022a  */
    /* JADX WARN: Code duplicated, block: B:86:0x022f  */
    /* JADX WARN: Code duplicated, block: B:88:0x0234  */
    /* JADX WARN: Code duplicated, block: B:91:0x0249  */
    /* JADX WARN: Code duplicated, block: B:94:0x0352  */
    /* JADX WARN: Code duplicated, block: B:95:0x0373  */
    /* JADX WARN: Code duplicated, block: B:98:0x03b0  */
    /* JADX WARN: Code duplicated, block: B:99:0x03ca  */
    /* JADX WARN: Instruction removed from duplicated block: B:59:0x0150, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:94:0x0352, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:99:0x03ca, please report this as an issue */
    private final void showProductEditor(final StoreProduct existing, final Function0<Unit> onSaved) {
        String strMoneyText;
        String sypAmount;
        String strMoneyText2;
        String sypAmount2;
        final EditText editTextShowProductEditor$field$714;
        final Spinner spinner;
        ArrayList arrayList;
        final Spinner spinner2;
        ArrayList arrayList2;
        Iterator<T> it;
        boolean tracksInventory;
        String str;
        String str2;
        Iterator<StoreUnit> it2;
        int i;
        int i2;
        Iterator<StoreCategory> it3;
        int i3;
        int i4;
        if (!isManager()) {
            Toast.makeText(this, "\u0625\u0636\u0627\u0641\u0629 \u0627\u0644\u0645\u0646\u062a\u062c\u0627\u062a \u0648\u062a\u0639\u062f\u064a\u0644\u0647\u0627 \u0645\u062a\u0627\u062d\u0627\u0646 \u0644\u0644\u0645\u062f\u064a\u0631 \u0641\u0642\u0637", 1).show();
            return;
        }
        MainActivity mainActivity = this;
        final StoreRepository storeRepository = new StoreRepository(mainActivity);
        final List<StoreUnit> units = storeRepository.getUnits();
        List<StoreCategory> categories = storeRepository.getCategories();
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(dp(20), dp(8), dp(20), dp(4));
        String name = existing != null ? existing.getName() : null;
        final EditText editTextShowProductEditor$field$714$default = showProductEditor$field$714$default(this, "\u0627\u0633\u0645 \u0627\u0644\u0645\u0646\u062a\u062c *", name == null ? "" : name, 0, 8, null);
        String barcode = existing != null ? existing.getBarcode() : null;
        final EditText editTextShowProductEditor$field$714$default2 = showProductEditor$field$714$default(this, "\u0627\u0644\u0628\u0627\u0631\u0643\u0648\u062f (\u0627\u062e\u062a\u064a\u0627\u0631\u064a)", barcode == null ? "" : barcode, 0, 8, null);
        String sku = existing != null ? existing.getSku() : null;
        final EditText editTextShowProductEditor$field$714$default3 = showProductEditor$field$714$default(this, "\u0631\u0645\u0632 SKU (\u0627\u062e\u062a\u064a\u0627\u0631\u064a)", sku == null ? "" : sku, 0, 8, null);
        long currentExchangeRateMilli = storeRepository.getCurrentExchangeRateMilli();
        if (existing == null || (strMoneyText = moneyText(existing.getPurchaseCostUsdCents())) == null) {
            strMoneyText = "0";
        }
        final EditText editTextShowProductEditor$field$715 = showProductEditor$field$714(this, "\u062a\u0643\u0644\u0641\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 \u0628\u0627\u0644\u062f\u0648\u0644\u0627\u0631", strMoneyText, 8194);
        if (existing == null) {
            sypAmount = "";
        } else {
            sypAmount = currentExchangeRateMilli > 0 ? formatSypAmount(CurrencyConverter.INSTANCE.usdCentsToSyp(existing.getPurchaseCostUsdCents(), currentExchangeRateMilli)) : "";
            if (sypAmount == null) {
                sypAmount = "";
            }
        }
        EditText editTextShowProductEditor$field$716 = showProductEditor$field$714(this, "\u062a\u0643\u0644\u0641\u0629 \u0627\u0644\u0634\u0631\u0627\u0621 \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629", sypAmount, 8194);
        if (existing == null || (strMoneyText2 = moneyText(existing.getSalePriceUsdCents())) == null) {
            strMoneyText2 = "";
        }
        final EditText editTextShowProductEditor$field$717 = showProductEditor$field$714(this, "\u0633\u0639\u0631 \u0627\u0644\u0645\u0628\u064a\u0639 \u0628\u0627\u0644\u062f\u0648\u0644\u0627\u0631 *", strMoneyText2, 8194);
        if (existing != null) {
            sypAmount2 = currentExchangeRateMilli > 0 ? formatSypAmount(CurrencyConverter.INSTANCE.usdCentsToSyp(existing.getSalePriceUsdCents(), currentExchangeRateMilli)) : "";
            if (sypAmount2 == null) {
            }
            EditText editTextShowProductEditor$field$718 = showProductEditor$field$714(this, "\u0633\u0639\u0631 \u0627\u0644\u0645\u0628\u064a\u0639 \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629", sypAmount2, 8194);
            editTextShowProductEditor$field$714 = showProductEditor$field$714(this, "\u0627\u0644\u0643\u0645\u064a\u0629 \u0627\u0644\u0627\u0641\u062a\u062a\u0627\u062d\u064a\u0629", "0", 8194);
            Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            showProductEditor$bindCurrencyPair(currentExchangeRateMilli, booleanRef, this, editTextShowProductEditor$field$715, editTextShowProductEditor$field$716);
            showProductEditor$bindCurrencyPair(currentExchangeRateMilli, booleanRef, this, editTextShowProductEditor$field$717, editTextShowProductEditor$field$718);
            spinner = new Spinner(mainActivity);
            List<StoreUnit> list = units;
            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (StoreUnit storeUnit : list) {
                arrayList.add(storeUnit.getName() + " (" + storeUnit.getSymbol() + ")");
            }
            spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, arrayList));
            spinner2 = new Spinner(mainActivity);
            List listListOf = CollectionsKt.listOf("\u0628\u062f\u0648\u0646 \u062a\u0635\u0646\u064a\u0641");
            List<StoreCategory> list2 = categories;
            arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            it = list2.iterator();
            while (it.hasNext()) {
                arrayList2.add(((StoreCategory) it.next()).getName());
            }
            spinner2.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, CollectionsKt.plus((Collection) listListOf, (Iterable) arrayList2)));
            if (existing != null) {
                it2 = units.iterator();
                i = 0;
                while (true) {
                    i2 = -1;
                    if (it2.hasNext()) {
                        i = -1;
                        break;
                    } else if (Intrinsics.areEqual(it2.next().getName(), existing.getUnitName())) {
                        break;
                    } else {
                        i++;
                    }
                }
                if (i >= 0) {
                    spinner.setSelection(i);
                }
                it3 = categories.iterator();
                i3 = 0;
                while (it3.hasNext()) {
                    if (Intrinsics.areEqual(it3.next().getName(), existing.getCategoryName())) {
                        i2 = i3;
                        break;
                    }
                    i3++;
                }
                tracksInventory = true;
                if (i2 >= 0) {
                    i4 = i2 + 1;
                } else {
                    i4 = 0;
                }
                spinner2.setSelection(i4);
            } else {
                tracksInventory = true;
            }
            final CheckBox checkBox = new CheckBox(mainActivity);
            checkBox.setText("\u064a\u062a\u062a\u0628\u0639 \u0627\u0644\u0645\u062e\u0632\u0648\u0646");
            checkBox.setTextSize(14.0f);
            if (existing != null) {
                tracksInventory = existing.getTracksInventory();
            }
            checkBox.setChecked(tracksInventory);
            checkBox.setTextColor(this.textColor);
            TextView textViewLabel = label("\u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0623\u0633\u0627\u0633\u064a\u0629", 17.0f, this.green, true, 5);
            textViewLabel.setPadding(0, 0, 0, dp(8));
            linearLayout.addView(textViewLabel);
            showProductEditor$addField(linearLayout, this, editTextShowProductEditor$field$714$default);
            showProductEditor$addField(linearLayout, this, editTextShowProductEditor$field$714$default2);
            showProductEditor$addField(linearLayout, this, editTextShowProductEditor$field$714$default3);
            linearLayout.addView(label("\u0648\u062d\u062f\u0629 \u0627\u0644\u0642\u064a\u0627\u0633", 13.0f, this.mutedColor, false, 5));
            linearLayout.addView(spinner, fieldParams(dp(48), dp(8)));
            linearLayout.addView(label("\u0627\u0644\u062a\u0635\u0646\u064a\u0641", 13.0f, this.mutedColor, false, 5));
            linearLayout.addView(spinner2, fieldParams(dp(48), dp(8)));
            TextView textViewLabel2 = label("\u062a\u0643\u0644\u0641\u0629 \u0627\u0644\u0634\u0631\u0627\u0621", 15.0f, this.green, true, 5);
            textViewLabel2.setPadding(0, dp(6), 0, dp(4));
            linearLayout.addView(textViewLabel2);
            showProductEditor$addField(linearLayout, this, editTextShowProductEditor$field$715);
            showProductEditor$addField(linearLayout, this, editTextShowProductEditor$field$716);
            TextView textViewLabel3 = label("\u0633\u0639\u0631 \u0627\u0644\u0645\u0628\u064a\u0639 (\u0627\u0644\u062f\u0648\u0644\u0627\u0631 \u0647\u0648 \u0627\u0644\u0645\u0631\u062c\u0639 \u0627\u0644\u062b\u0627\u0628\u062a)", 15.0f, this.green, true, 5);
            textViewLabel3.setPadding(0, dp(10), 0, dp(4));
            linearLayout.addView(textViewLabel3);
            showProductEditor$addField(linearLayout, this, editTextShowProductEditor$field$717);
            showProductEditor$addField(linearLayout, this, editTextShowProductEditor$field$718);
            if (currentExchangeRateMilli > 0) {
                str = "\u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645: " + MainActivity$$ExternalSyntheticBackport0.m(BigDecimal.valueOf(currentExchangeRateMilli, 3)).toPlainString() + " \u0644.\u0633 \u0644\u0643\u0644 \u062f\u0648\u0644\u0627\u0631. \u062a\u0639\u062f\u064a\u0644 \u0623\u064a \u062d\u0642\u0644 \u064a\u062d\u062f\u0651\u062b \u0627\u0644\u0639\u0645\u0644\u0629 \u0627\u0644\u0645\u0642\u0627\u0628\u0644\u0629 \u062a\u0644\u0642\u0627\u0626\u064a\u0627\u064b.";
            } else {
                str = "\u0644\u0645 \u064a\u064f\u0633\u062c\u0651\u0644 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0628\u0639\u062f. \u0623\u062f\u062e\u0644 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0645\u0646 \u0634\u0627\u0634\u0629 \u0625\u062f\u0627\u0631\u0629 \u0627\u0644\u0645\u0646\u062a\u062c\u0627\u062a \u0644\u062a\u0641\u0639\u064a\u0644 \u062d\u0642\u0648\u0644 \u0627\u0644\u0644\u064a\u0631\u0629.";
            }
            TextView textViewLabel4 = label(str, 11.0f, this.mutedColor, false, 5);
            textViewLabel4.setPadding(0, dp(4), 0, dp(8));
            linearLayout.addView(textViewLabel4);
            linearLayout.addView(checkBox, fieldParams(dp(44), dp(4)));
            if (existing == null) {
                linearLayout.addView(label("\u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u0627\u0644\u0627\u0641\u062a\u062a\u0627\u062d\u064a", 14.0f, this.green, true, 5));
                showProductEditor$addField(linearLayout, this, editTextShowProductEditor$field$714);
            } else {
                TextView textViewLabel5 = label("\u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u0627\u0644\u062d\u0627\u0644\u064a: " + formatQuantity(existing.getStock()) + " " + existing.getUnitSymbol() + "\n\u0644\u0627 \u064a\u062a\u0645 \u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u0645\u0646 \u0634\u0627\u0634\u0629 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0645\u0646\u062a\u062c\u061b \u0627\u0633\u062a\u062e\u062f\u0645 \u062d\u0631\u0643\u0629 \u0645\u062e\u0632\u0648\u0646 \u0644\u0627\u062d\u0642\u0627\u064b.", 12.0f, this.mutedColor, false, 5);
                textViewLabel5.setPadding(0, dp(8), 0, dp(8));
                linearLayout.addView(textViewLabel5);
            }
            ScrollView scrollView = new ScrollView(mainActivity);
            scrollView.addView(linearLayout);
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            if (existing == null) {
                str2 = "\u0625\u0636\u0627\u0641\u0629 \u0645\u0646\u062a\u062c";
            } else {
                str2 = "\u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u0645\u0646\u062a\u062c";
            }
            final AlertDialog alertDialogCreate = builder.setTitle(str2).setView(scrollView).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u062d\u0641\u0638", (DialogInterface.OnClickListener) null).create();
            final List<StoreCategory> list3 = categories;
            alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda37
                @Override // android.content.DialogInterface.OnShowListener
                public final void onShow(DialogInterface dialogInterface) {
                    MainActivity.showProductEditor$lambda$735(alertDialogCreate, editTextShowProductEditor$field$714$default, this, editTextShowProductEditor$field$715, editTextShowProductEditor$field$717, existing, editTextShowProductEditor$field$714, units, spinner, spinner2, list3, storeRepository, editTextShowProductEditor$field$714$default2, editTextShowProductEditor$field$714$default3, checkBox, onSaved, dialogInterface);
                }
            });
            alertDialogCreate.show();
        }
        categories = categories;
        sypAmount2 = "";
        EditText editTextShowProductEditor$field$719 = showProductEditor$field$714(this, "\u0633\u0639\u0631 \u0627\u0644\u0645\u0628\u064a\u0639 \u0628\u0627\u0644\u0644\u064a\u0631\u0629 \u0627\u0644\u0633\u0648\u0631\u064a\u0629", sypAmount2, 8194);
        editTextShowProductEditor$field$714 = showProductEditor$field$714(this, "\u0627\u0644\u0643\u0645\u064a\u0629 \u0627\u0644\u0627\u0641\u062a\u062a\u0627\u062d\u064a\u0629", "0", 8194);
        Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
        showProductEditor$bindCurrencyPair(currentExchangeRateMilli, booleanRef2, this, editTextShowProductEditor$field$715, editTextShowProductEditor$field$716);
        showProductEditor$bindCurrencyPair(currentExchangeRateMilli, booleanRef2, this, editTextShowProductEditor$field$717, editTextShowProductEditor$field$719);
        spinner = new Spinner(mainActivity);
        List<StoreUnit> list4 = units;
        arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list4, 10));
        while (r0.hasNext()) {
            arrayList.add(storeUnit.getName() + " (" + storeUnit.getSymbol() + ")");
        }
        spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, arrayList));
        spinner2 = new Spinner(mainActivity);
        List listListOf2 = CollectionsKt.listOf("\u0628\u062f\u0648\u0646 \u062a\u0635\u0646\u064a\u0641");
        List<StoreCategory> list5 = categories;
        arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list5, 10));
        it = list5.iterator();
        while (it.hasNext()) {
            arrayList2.add(((StoreCategory) it.next()).getName());
        }
        spinner2.setAdapter((SpinnerAdapter) new ArrayAdapter(mainActivity, android.R.layout.simple_spinner_dropdown_item, CollectionsKt.plus((Collection) listListOf2, (Iterable) arrayList2)));
        if (existing != null) {
            it2 = units.iterator();
            i = 0;
            while (true) {
                i2 = -1;
                if (it2.hasNext()) {
                    i = -1;
                    break;
                } else {
                    if (Intrinsics.areEqual(it2.next().getName(), existing.getUnitName())) {
                        break;
                        break;
                    }
                    i++;
                }
            }
            if (i >= 0) {
                spinner.setSelection(i);
            }
            it3 = categories.iterator();
            i3 = 0;
            while (it3.hasNext()) {
                if (Intrinsics.areEqual(it3.next().getName(), existing.getCategoryName())) {
                    i2 = i3;
                    break;
                }
                i3++;
            }
            tracksInventory = true;
            if (i2 >= 0) {
                i4 = i2 + 1;
            } else {
                i4 = 0;
            }
            spinner2.setSelection(i4);
        } else {
            tracksInventory = true;
        }
        final CheckBox checkBox2 = new CheckBox(mainActivity);
        checkBox2.setText("\u064a\u062a\u062a\u0628\u0639 \u0627\u0644\u0645\u062e\u0632\u0648\u0646");
        checkBox2.setTextSize(14.0f);
        if (existing != null) {
            tracksInventory = existing.getTracksInventory();
        }
        checkBox2.setChecked(tracksInventory);
        checkBox2.setTextColor(this.textColor);
        TextView textViewLabel6 = label("\u0627\u0644\u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0623\u0633\u0627\u0633\u064a\u0629", 17.0f, this.green, true, 5);
        textViewLabel6.setPadding(0, 0, 0, dp(8));
        linearLayout.addView(textViewLabel6);
        showProductEditor$addField(linearLayout, this, editTextShowProductEditor$field$714$default);
        showProductEditor$addField(linearLayout, this, editTextShowProductEditor$field$714$default2);
        showProductEditor$addField(linearLayout, this, editTextShowProductEditor$field$714$default3);
        linearLayout.addView(label("\u0648\u062d\u062f\u0629 \u0627\u0644\u0642\u064a\u0627\u0633", 13.0f, this.mutedColor, false, 5));
        linearLayout.addView(spinner, fieldParams(dp(48), dp(8)));
        linearLayout.addView(label("\u0627\u0644\u062a\u0635\u0646\u064a\u0641", 13.0f, this.mutedColor, false, 5));
        linearLayout.addView(spinner2, fieldParams(dp(48), dp(8)));
        TextView textViewLabel7 = label("\u062a\u0643\u0644\u0641\u0629 \u0627\u0644\u0634\u0631\u0627\u0621", 15.0f, this.green, true, 5);
        textViewLabel7.setPadding(0, dp(6), 0, dp(4));
        linearLayout.addView(textViewLabel7);
        showProductEditor$addField(linearLayout, this, editTextShowProductEditor$field$715);
        showProductEditor$addField(linearLayout, this, editTextShowProductEditor$field$716);
        TextView textViewLabel8 = label("\u0633\u0639\u0631 \u0627\u0644\u0645\u0628\u064a\u0639 (\u0627\u0644\u062f\u0648\u0644\u0627\u0631 \u0647\u0648 \u0627\u0644\u0645\u0631\u062c\u0639 \u0627\u0644\u062b\u0627\u0628\u062a)", 15.0f, this.green, true, 5);
        textViewLabel8.setPadding(0, dp(10), 0, dp(4));
        linearLayout.addView(textViewLabel8);
        showProductEditor$addField(linearLayout, this, editTextShowProductEditor$field$717);
        showProductEditor$addField(linearLayout, this, editTextShowProductEditor$field$719);
        if (currentExchangeRateMilli > 0) {
            str = "\u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645: " + MainActivity$$ExternalSyntheticBackport0.m(BigDecimal.valueOf(currentExchangeRateMilli, 3)).toPlainString() + " \u0644.\u0633 \u0644\u0643\u0644 \u062f\u0648\u0644\u0627\u0631. \u062a\u0639\u062f\u064a\u0644 \u0623\u064a \u062d\u0642\u0644 \u064a\u062d\u062f\u0651\u062b \u0627\u0644\u0639\u0645\u0644\u0629 \u0627\u0644\u0645\u0642\u0627\u0628\u0644\u0629 \u062a\u0644\u0642\u0627\u0626\u064a\u0627\u064b.";
        } else {
            str = "\u0644\u0645 \u064a\u064f\u0633\u062c\u0651\u0644 \u0633\u0639\u0631 \u0635\u0631\u0641 \u0628\u0639\u062f. \u0623\u062f\u062e\u0644 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0645\u0646 \u0634\u0627\u0634\u0629 \u0625\u062f\u0627\u0631\u0629 \u0627\u0644\u0645\u0646\u062a\u062c\u0627\u062a \u0644\u062a\u0641\u0639\u064a\u0644 \u062d\u0642\u0648\u0644 \u0627\u0644\u0644\u064a\u0631\u0629.";
        }
        TextView textViewLabel9 = label(str, 11.0f, this.mutedColor, false, 5);
        textViewLabel9.setPadding(0, dp(4), 0, dp(8));
        linearLayout.addView(textViewLabel9);
        linearLayout.addView(checkBox2, fieldParams(dp(44), dp(4)));
        if (existing == null) {
            linearLayout.addView(label("\u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u0627\u0644\u0627\u0641\u062a\u062a\u0627\u062d\u064a", 14.0f, this.green, true, 5));
            showProductEditor$addField(linearLayout, this, editTextShowProductEditor$field$714);
        } else {
            TextView textViewLabel10 = label("\u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u0627\u0644\u062d\u0627\u0644\u064a: " + formatQuantity(existing.getStock()) + " " + existing.getUnitSymbol() + "\n\u0644\u0627 \u064a\u062a\u0645 \u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u0645\u062e\u0632\u0648\u0646 \u0645\u0646 \u0634\u0627\u0634\u0629 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0645\u0646\u062a\u062c\u061b \u0627\u0633\u062a\u062e\u062f\u0645 \u062d\u0631\u0643\u0629 \u0645\u062e\u0632\u0648\u0646 \u0644\u0627\u062d\u0642\u0627\u064b.", 12.0f, this.mutedColor, false, 5);
            textViewLabel10.setPadding(0, dp(8), 0, dp(8));
            linearLayout.addView(textViewLabel10);
        }
        ScrollView scrollView2 = new ScrollView(mainActivity);
        scrollView2.addView(linearLayout);
        AlertDialog.Builder builder2 = new AlertDialog.Builder(mainActivity);
        if (existing == null) {
            str2 = "\u0625\u0636\u0627\u0641\u0629 \u0645\u0646\u062a\u062c";
        } else {
            str2 = "\u062a\u0639\u062f\u064a\u0644 \u0627\u0644\u0645\u0646\u062a\u062c";
        }
        final AlertDialog alertDialogCreate2 = builder2.setTitle(str2).setView(scrollView2).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u062d\u0641\u0638", (DialogInterface.OnClickListener) null).create();
        final List list6 = categories;
        alertDialogCreate2.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda37
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                MainActivity.showProductEditor$lambda$735(alertDialogCreate2, editTextShowProductEditor$field$714$default, this, editTextShowProductEditor$field$715, editTextShowProductEditor$field$717, existing, editTextShowProductEditor$field$714, units, spinner, spinner2, list6, storeRepository, editTextShowProductEditor$field$714$default2, editTextShowProductEditor$field$714$default3, checkBox2, onSaved, dialogInterface);
            }
        });
        alertDialogCreate2.show();
    }

    static /* synthetic */ EditText showProductEditor$field$714$default(MainActivity mainActivity, String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str2 = "";
        }
        if ((i2 & 8) != 0) {
            i = 1;
        }
        return showProductEditor$field$714(mainActivity, str, str2, i);
    }

    private static final EditText showProductEditor$field$714(MainActivity mainActivity, String str, String str2, int i) {
        EditText editText = new EditText(mainActivity);
        editText.setHint(str);
        editText.setText(str2);
        editText.setInputType(i);
        editText.setTextSize(14.0f);
        editText.setSingleLine(true);
        editText.setPadding(mainActivity.dp(12), 0, mainActivity.dp(12), 0);
        editText.setBackground(mainActivity.inputBackground());
        return editText;
    }

    private static final void showProductEditor$addField(LinearLayout linearLayout, MainActivity mainActivity, View view) {
        linearLayout.addView(view, mainActivity.fieldParams(mainActivity.dp(48), mainActivity.dp(9)));
    }

    private static final void showProductEditor$bindCurrencyPair(final long j, final Ref.BooleanRef booleanRef, final MainActivity mainActivity, final EditText editText, final EditText editText2) {
        editText.addTextChangedListener(new TextWatcher() { // from class: com.hazem.dailystore.MainActivity$showProductEditor$bindCurrencyPair$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Object objM381constructorimpl;
                if (booleanRef.element || j <= 0) {
                    return;
                }
                MainActivity mainActivity2 = mainActivity;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    MainActivity$showProductEditor$bindCurrencyPair$1 mainActivity$showProductEditor$bindCurrencyPair$1 = this;
                    String string = s != null ? s.toString() : null;
                    if (string == null) {
                        string = "";
                    }
                    objM381constructorimpl = Result.m381constructorimpl(Long.valueOf(mainActivity2.parseUsdCents(string)));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
                }
                Long l = (Long) (Result.m387isFailureimpl(objM381constructorimpl) ? null : objM381constructorimpl);
                if (l != null) {
                    long jLongValue = l.longValue();
                    booleanRef.element = true;
                    editText2.setText(mainActivity.formatSypAmount(CurrencyConverter.INSTANCE.usdCentsToSyp(jLongValue, j)));
                    booleanRef.element = false;
                }
            }
        });
        editText2.addTextChangedListener(new TextWatcher() { // from class: com.hazem.dailystore.MainActivity$showProductEditor$bindCurrencyPair$2
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Object objM381constructorimpl;
                if (booleanRef.element || j <= 0) {
                    return;
                }
                MainActivity mainActivity2 = mainActivity;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    MainActivity$showProductEditor$bindCurrencyPair$2 mainActivity$showProductEditor$bindCurrencyPair$2 = this;
                    String string = s != null ? s.toString() : null;
                    if (string == null) {
                        string = "";
                    }
                    objM381constructorimpl = Result.m381constructorimpl(Long.valueOf(mainActivity2.parseSypAmount(string)));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM381constructorimpl = Result.m381constructorimpl(ResultKt.createFailure(th));
                }
                Long l = (Long) (Result.m387isFailureimpl(objM381constructorimpl) ? null : objM381constructorimpl);
                if (l != null) {
                    long jLongValue = l.longValue();
                    booleanRef.element = true;
                    editText.setText(mainActivity.moneyText(CurrencyConverter.INSTANCE.sypToUsdCents(jLongValue, j)));
                    booleanRef.element = false;
                }
            }
        });
        editText2.setEnabled(j > 0);
        if (j <= 0) {
            editText2.setHint("\u0633\u062c\u0651\u0644 \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u0623\u0648\u0644\u0627\u064b");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showProductEditor$lambda$735(final AlertDialog alertDialog, final EditText editText, final MainActivity mainActivity, final EditText editText2, final EditText editText3, final StoreProduct storeProduct, final EditText editText4, final List list, final Spinner spinner, final Spinner spinner2, final List list2, final StoreRepository storeRepository, final EditText editText5, final EditText editText6, final CheckBox checkBox, final Function0 function0, DialogInterface dialogInterface) {
        alertDialog.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda47
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.showProductEditor$lambda$735$lambda$734(editText, mainActivity, editText2, editText3, storeProduct, editText4, list, spinner, spinner2, list2, storeRepository, editText5, editText6, checkBox, alertDialog, function0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showProductEditor$lambda$735$lambda$734(EditText editText, MainActivity mainActivity, EditText editText2, EditText editText3, StoreProduct storeProduct, EditText editText4, List list, Spinner spinner, Spinner spinner2, List list2, StoreRepository storeRepository, EditText editText5, EditText editText6, CheckBox checkBox, AlertDialog alertDialog, Function0 function0, View view) {
        StoreCategory storeCategory;
        Double doubleOrNull;
        try {
            String string = StringsKt.trim((CharSequence) editText.getText().toString()).toString();
            long usdCents = mainActivity.parseUsdCents(editText2.getText().toString());
            long usdCents2 = mainActivity.parseUsdCents(editText3.getText().toString());
            double dDoubleValue = (storeProduct != null || (doubleOrNull = StringsKt.toDoubleOrNull(editText4.getText().toString())) == null) ? 0.0d : doubleOrNull.doubleValue();
            if (string.length() <= 0) {
                throw new IllegalArgumentException("\u0627\u0633\u0645 \u0627\u0644\u0645\u0646\u062a\u062c \u0645\u0637\u0644\u0648\u0628".toString());
            }
            if (usdCents2 <= 0) {
                throw new IllegalArgumentException("\u0623\u062f\u062e\u0644 \u0633\u0639\u0631 \u0628\u064a\u0639 \u0635\u062d\u064a\u062d\u0627\u064b \u0628\u0627\u0644\u062f\u0648\u0644\u0627\u0631".toString());
            }
            if (usdCents < 0 || dDoubleValue < 0.0d) {
                throw new IllegalArgumentException("\u062a\u062d\u0642\u0642 \u0645\u0646 \u0627\u0644\u0633\u0639\u0631 \u0648\u0627\u0644\u0643\u0645\u064a\u0629".toString());
            }
            StoreUnit storeUnit = (StoreUnit) CollectionsKt.getOrNull(list, spinner.getSelectedItemPosition());
            if (storeUnit == null) {
                throw new IllegalStateException("\u0627\u062e\u062a\u0631 \u0648\u062d\u062f\u0629 \u0642\u064a\u0627\u0633".toString());
            }
            Long lValueOf = (spinner2.getSelectedItemPosition() <= 0 || (storeCategory = (StoreCategory) CollectionsKt.getOrNull(list2, spinner2.getSelectedItemPosition() - 1)) == null) ? null : Long.valueOf(storeCategory.getId());
            String id = storeProduct != null ? storeProduct.getId() : null;
            String string2 = editText5.getText().toString();
            String string3 = editText6.getText().toString();
            long id2 = storeUnit.getId();
            boolean zIsChecked = checkBox.isChecked();
            String str = mainActivity.currentUserId;
            if (str != null) {
                storeRepository.saveProduct(id, string, string2, string3, lValueOf, id2, usdCents, usdCents2, zIsChecked, dDoubleValue, str);
                alertDialog.dismiss();
                Toast.makeText(mainActivity, "\u062a\u0645 \u062d\u0641\u0638 \u0628\u064a\u0627\u0646\u0627\u062a \u0627\u0644\u0645\u0646\u062a\u062c", 0).show();
                if (function0 != null) {
                    function0.invoke();
                    return;
                } else {
                    mainActivity.showProductManagement();
                    return;
                }
            }
            throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u062f\u064a\u0631 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u062d\u0641\u0638 \u0627\u0644\u0645\u0646\u062a\u062c\u061b \u062a\u062d\u0642\u0642 \u0645\u0646 \u0639\u062f\u0645 \u062a\u0643\u0631\u0627\u0631 \u0627\u0644\u0628\u0627\u0631\u0643\u0648\u062f \u0623\u0648 SKU";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showAddCategoryDialog() {
        if (!isManager()) {
            Toast.makeText(this, "\u0625\u0636\u0627\u0641\u0629 \u0627\u0644\u062a\u0635\u0646\u064a\u0641\u0627\u062a \u0645\u062a\u0627\u062d\u0629 \u0644\u0644\u0645\u062f\u064a\u0631 \u0641\u0642\u0637", 0).show();
            return;
        }
        MainActivity mainActivity = this;
        final EditText editText = new EditText(mainActivity);
        editText.setHint("\u0627\u0633\u0645 \u0627\u0644\u062a\u0635\u0646\u064a\u0641");
        editText.setTextSize(16.0f);
        editText.setSingleLine(true);
        editText.setPadding(dp(14), 0, dp(14), 0);
        new AlertDialog.Builder(mainActivity).setTitle("\u0625\u0636\u0627\u0641\u0629 \u062a\u0635\u0646\u064a\u0641").setView(editText).setNegativeButton("\u0625\u0644\u063a\u0627\u0621", (DialogInterface.OnClickListener) null).setPositiveButton("\u0625\u0636\u0627\u0641\u0629", new DialogInterface.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda160
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.showAddCategoryDialog$lambda$737(this.f$0, editText, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showAddCategoryDialog$lambda$737(MainActivity mainActivity, EditText editText, DialogInterface dialogInterface, int i) {
        try {
            StoreRepository storeRepository = new StoreRepository(mainActivity);
            String string = editText.getText().toString();
            String str = mainActivity.currentUserId;
            if (str == null) {
                throw new IllegalStateException("\u062c\u0644\u0633\u0629 \u0627\u0644\u0645\u062f\u064a\u0631 \u063a\u064a\u0631 \u0635\u0627\u0644\u062d\u0629".toString());
            }
            storeRepository.addCategory(string, str);
            Toast.makeText(mainActivity, "\u062a\u0645\u062a \u0625\u0636\u0627\u0641\u0629 \u0627\u0644\u062a\u0635\u0646\u064a\u0641", 0).show();
            mainActivity.showProductManagement();
        } catch (Exception e) {
            MainActivity mainActivity2 = mainActivity;
            String message = e.getMessage();
            if (message == null) {
                message = "\u062a\u0639\u0630\u0631 \u0625\u0636\u0627\u0641\u0629 \u0627\u0644\u062a\u0635\u0646\u064a\u0641\u061b \u0631\u0628\u0645\u0627 \u0627\u0644\u0627\u0633\u0645 \u0645\u0633\u062a\u062e\u062f\u0645";
            }
            Toast.makeText(mainActivity2, message, 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String moneyText(long cents) {
        String plainString = BigDecimal.valueOf(cents, 2).setScale(2, RoundingMode.HALF_UP).toPlainString();
        Intrinsics.checkNotNullExpressionValue(plainString, "toPlainString(...)");
        return plainString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String formatSypAmount(long amount) {
        String str = new DecimalFormat("#,##0").format(amount);
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long parseSypAmount(String value) {
        BigDecimal bigDecimal = new BigDecimal(StringsKt.replace$default(StringsKt.replace$default(StringsKt.trim((CharSequence) value).toString(), ",", "", false, 4, (Object) null), "\u066c", "", false, 4, (Object) null));
        if (bigDecimal.signum() < 0) {
            throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0625\u062f\u062e\u0627\u0644 \u0645\u0628\u0644\u063a \u0633\u0627\u0644\u0628".toString());
        }
        return bigDecimal.setScale(0, RoundingMode.HALF_UP).longValueExact();
    }

    private final String formatDualMoney(long usdCents, long rateMilli) {
        String str = "$" + moneyText(usdCents);
        if (rateMilli > 0) {
            return str + "  \u2022  " + formatSypAmount(CurrencyConverter.INSTANCE.usdCentsToSyp(usdCents, rateMilli)) + " \u0644.\u0633";
        }
        return str + "  \u2022  \u0633\u0639\u0631 \u0627\u0644\u0635\u0631\u0641 \u063a\u064a\u0631 \u0645\u0633\u062c\u0644";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long parseUsdCents(String value) {
        BigDecimal bigDecimal = new BigDecimal(StringsKt.replace$default(StringsKt.trim((CharSequence) value).toString(), ',', '.', false, 4, (Object) null));
        if (bigDecimal.signum() < 0) {
            throw new IllegalArgumentException("\u0644\u0627 \u064a\u0645\u0643\u0646 \u0625\u062f\u062e\u0627\u0644 \u0633\u0639\u0631 \u0633\u0627\u0644\u0628".toString());
        }
        return bigDecimal.multiply(BigDecimal.valueOf(100L)).setScale(0, RoundingMode.HALF_UP).longValueExact();
    }

    private final String formatPrice(long usdCents) {
        return formatDualMoney(usdCents, this.exchangeRateMilli);
    }

    private final String formatQuantity(double value) {
        String str = new DecimalFormat("#,##0.###").format(value);
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    private final TextView label(String value, float size, int color, boolean bold, int gravityValue) {
        TextView textView = new TextView(this);
        textView.setText(value);
        textView.setTextSize(size);
        textView.setTextColor(color);
        textView.setGravity(gravityValue);
        if (bold) {
            textView.setTypeface(Typeface.DEFAULT_BOLD);
        }
        return textView;
    }

    private final TextView sectionHeading(String value) {
        TextView textViewLabel = label(value, 17.0f, this.green, true, 5);
        textViewLabel.setPadding(0, dp(8), 0, dp(10));
        return textViewLabel;
    }

    private final Button smallButton(String value, final Function0<Unit> action) {
        Button button = new Button(this);
        button.setText(value);
        button.setTextSize(16.0f);
        button.setAllCaps(false);
        button.setMinWidth(0);
        button.setMinimumWidth(0);
        button.setPadding(0, 0, 0, 0);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hazem.dailystore.MainActivity$$ExternalSyntheticLambda162
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                action.invoke();
            }
        });
        return button;
    }

    private final int dp(int value) {
        return (int) (value * getResources().getDisplayMetrics().density);
    }

    private final GradientDrawable inputBackground() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-1);
        gradientDrawable.setCornerRadius(dp(12));
        gradientDrawable.setStroke(dp(1), Color.rgb(220, 225, 220));
        return gradientDrawable;
    }

    private final GradientDrawable cardBackground() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-1);
        gradientDrawable.setCornerRadius(dp(14));
        gradientDrawable.setStroke(dp(1), Color.rgb(232, 235, 231));
        return gradientDrawable;
    }

    private final LinearLayout.LayoutParams matchWidth() {
        return new LinearLayout.LayoutParams(-1, -2);
    }

    private final LinearLayout.LayoutParams fieldParams(int height, int bottom) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, height);
        layoutParams.bottomMargin = bottom;
        return layoutParams;
    }
}
