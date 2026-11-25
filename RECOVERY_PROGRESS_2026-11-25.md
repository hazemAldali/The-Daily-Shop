# سجل استعادة المشروع — 2026-11-25

## النطاق والحماية
- المشروع: The Daily Shop
- فرع العمل الوحيد: the-daily-shop-development
- يمنع تعديل main أو حذف/استبدال APK الأصلي.
- لا تُرفع مفاتيح التوقيع أو كلمات المرور أو الأسرار إلى المستودع.

## الحالة المنقولة من سجل الفحص
- APK الأصلي محفوظ في مستودع GitLab السابق، ولم يثبت بعد نقله إلى GitHub.
- الحزمة: com.hazem.dailystore
- versionName: 1.0.1، versionCode: 1284
- minSdk: 23، targetSdk: 35، compileSdk: 35
- Android Gradle Plugin: 8.7.3
- Gradle: 8.9
- Kotlin Gradle Plugin: 2.0.21
- Java source/target: 17
- مراجعة Git المضمنة في APK: f558d58b9cd5693b24fec7ef39eac002ffd9bdef
- APK يحتوي 3 ملفات DEX ومكتبات SQLCipher الأصلية لمعالجات ARM وx86.
- SHA-256 للـAPK الأصلي: 1ecb5c388ba7cf8f67fe6aa23c903f15c55d8d818f43f960515005b14a744ab0

## المواد التي سبق استخراجها في GitLab
- class_skeletons.zip: نحو 250 هيكل صنف، دون أجسام الدوال.
- database_sql.txt: مقتطفات SQL وليست مخطط Room/DAO كاملاً.
- arabic_ui_strings.txt: سلاسل واجهة تحتاج تنقية.
- AndroidManifest_recovered.xml: Manifest مفكوك.
- تم فك 628 ملف XML ثنائياً بنجاح.
- أرشيف الموارد المحلي السابق: the-daily-shop-apk-recovery-resources-2026-11-25.zip
- بصمة أرشيف الموارد: e6898f0bb5183e9ad43d2b5a60f9c4018907d3a016a46d07c99de931f12147b1

## ما لم يكتمل
- فك DEX إلى Java/Kotlin شبه مقروء وقراءة أجسام الدوال.
- إعادة بناء مشروع Gradle ومصادر التطبيق والاعتماديات.
- استعادة الاختبارات وإعداد CI وتشغيل بناء فعلي.
- استعادة Cloudflare Worker بشكل مستقل.
- اختبار تسجيل الدخول والاقتران والمبيعات والمزامنة بين الجهازين.

## ملاحظة النقل إلى GitHub
هذا الملف يوثق حالة الاستعادة المنقولة. لا يعني وجوده أن ملفات APK أو DEX أو الموارد الثنائية قد نُقلت إلى GitHub. يجب التحقق من كل ملف بعد رفعه ومطابقة بصمته. لا تعتبر الاستعادة أو البناء أو الاختبارات ناجحة دون أدلة تشغيل فعلية.
