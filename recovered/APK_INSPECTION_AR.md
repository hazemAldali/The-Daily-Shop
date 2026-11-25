# تقرير فحص ملف APK — المتجر اليومي

تاريخ الفحص: 2026-11-25

## الملف
- المسار في المستودع: `app-release.apk`
- الفرع: `the-daily-shop-development`
- الحجم: 19,587,400 بايت (نحو 19.6 MB)
- SHA-256: `1ecb5c388ba7cf8f67fe6aa23c903f15c55d8d818f43f960515005b14a744ab0`
- بنية APK: ZIP صالح، 885 مدخلاً.
- يحتوي على `classes.dex` و`classes2.dex` و`classes3.dex`، و`AndroidManifest.xml`، و`resources.arsc`.
- يحتوي على مكتبات SQLCipher الأصلية لمعالجات ARM وx86.

## بيانات البناء المضمنة
- إصدار Android Gradle Plugin: 8.7.3
- Gradle: 8.9
- Kotlin Gradle Plugin: 2.0.21
- Java source/target: 17
- إصدار التطبيق الظاهر في DEX: 1.0.1
- مراجعة Git المضمنة في `META-INF/version-control-info.textproto`:
  `f558d58b9cd5693b24fec7ef39eac002ffd9bdef`

## مؤشرات الكود الموجودة داخل DEX
ظهرت أسماء أصناف وملفات مرتبطة بوظائف التطبيق، ومنها:
- `AuthRepository`
- `AppDatabase`
- `SqlCipherOpenHelperBase`
- `SqlCipherDatabaseMigrator`
- `SqlCipherBackupRestorePipeline`
- `SqlCipherLegacyMigrationCoordinator`
- `SqlCipherRestoreCoordinator`
- `SyncPairingRelayClient`
- `SyncRelayProcessor`
- `SyncRelayRegistryClient`
- `SyncRelayWebSocketClient`

هذه مؤشرات على أن APK يحتوي على تنفيذ مترجم فعلي لهذه المكونات، وليس أسماء الواجهات فقط.

## حدود الاستعادة
- APK لا يحتوي عادةً على ملفات Kotlin الأصلية أو ملفات Gradle أو ملفات الاختبارات بصيغتها المصدرية.
- يمكن استخراج الموارد والـManifest، ويمكن تفكيك DEX إلى شيفرة Java تقريبية باستخدام أداة مناسبة؛ وقد تختلف عن المصدر الأصلي وتفقد بعض الأسماء والتعليقات والبنية.
- لم تُجرَ بعد عملية decompilation كاملة، ولم يُختبر بناء APK جديد من هذا الملف.
- ملف APK وحده لا يعيد ملفات Worker/Cloudflare أو إعدادات CI أو مفاتيح التوقيع.

## الخطوة التالية
استخدام APK مع ملفات الاستعادة الأخرى لاستخراج الموارد والـManifest ومعلومات الأصناف، ثم إعادة بناء مشروع Android تدريجياً. يجب إبقاء أي ملفات مصدر مستعادة في فرع التطوير فقط، وعدم الادعاء بأن المشروع قابل للبناء حتى تنجح عملية بناء واختبارات فعلية.
