# تقرير فحص واستعادة APK

تاريخ الفحص: 2026-11-25
الملف: app-release.apk
SHA-256: 1ecb5c388ba7cf8f67fe6aa23c903f15c55d8d818f43f960515005b14a744ab0
حجم APK: 19,587,400 بايت (نحو 19.6 MB).

## نتيجة الفحص
- حزمة Android سليمة بنيوياً ويمكن فتحها كأرشيف ZIP.
- تحتوي AndroidManifest.xml و resources.arsc وملفات الموارد.
- تحتوي classes.dex و classes2.dex و classes3.dex.
- جداول DEX تضم 12,296 تعريف صنف إجمالاً، و523 تعريفاً ضمن com.hazem.dailystore.
- يوجد 3,843 method مرتبطاً بأصناف التطبيق ولها code items داخل DEX.
- هذه النتائج تثبت وجود شيفرة مترجمة فعلية، لكنها لا تعني أن المصدر الأصلي قد استعيد.

## مكونات مهمة ظهرت في DEX
### الواجهة
MainActivity, SplashActivity, SyncDeviceManagementActivity, SyncForegroundService, SyncBootReceiver, TheDailyShopApplication, BackupRestoreWorker.

### قاعدة البيانات والتشفير
AppDatabase, SqlCipherDatabaseRuntime, SqlCipherOpenHelperBase, SqlCipherDatabaseMigrator, SqlCipherLegacyMigrationCoordinator, SqlCipherBackupRestorePipeline, SqlCipherRestoreCoordinator, SqlCipherSameDeviceRestoreCoordinator, DatabaseKeyVault, BackupRestoreManager.

### تسجيل الدخول والصلاحيات
AuthRepository, AuthenticatedUser, SecureSessionStore, PermissionPolicy, ManagedUserSummary.

### البيع والمخزون والحسابات
InvoiceRepository, StoreRepository, CustomerRepository, CustomerPaymentRepository, SupplierRepository, PurchasePaymentRepository, PurchaseReturnRepository, SalesReturnRepository, OperatingExpenseRepository, InventoryAdjustmentRepository, InventoryValuationRepository, FifoCosting, DailyBalanceRepository, CashAccountRepository, ServiceTransferRepository, ServiceTransferReceivableRepository, PartyStatementRepository, SalesProfitRepository.

### المزامنة
SyncProtocolRepository, SyncPairingRelayClient, SyncRelayWebSocketClient, SyncDeviceAuthenticator, SyncDeviceIdentity, SyncDeviceCertificate, SyncDomainApplier, SyncEnvelopeCipher, SyncEnvelopeSecurity, SyncCredentialVault, SyncRelayRegistryClient, SyncRelayRuntime, SyncRelayFrameCodec, SyncRelayProcessor.

## حدود الاستعادة
- لا تتوفر بعد ملفات Java/Kotlin مفككة قابلة للبناء.
- أسماء الأصناف والدوال وحدها لا تكشف أجسام الدوال.
- APK لا يحتوي ملفات Gradle الأصلية أو الاختبارات أو إعدادات CI أو مصدر Cloudflare Worker.
- ناتج JADX سيكون Java/Kotlin تقريبياً، وليس نسخة مطابقة للمصدر الأصلي.

## الخطوات التالية
1. توفير APK الأصلي وملفات الاستعادة الثنائية على GitHub والتحقق من SHA-256.
2. تشغيل JADX موثوق على ملفات DEX الثلاثة.
3. حفظ ناتج التفكيك في مسار مستقل دون الكتابة فوق الملفات الأصلية.
4. إعادة بناء Gradle والاعتماديات تدريجياً.
5. إعادة إنشاء الاختبارات ثم تشغيل البناء والتحقق منه.
6. استعادة Worker من مصدر مستقل أو سجل نشر.
