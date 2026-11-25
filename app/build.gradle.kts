plugins {
    id("com.android.application")
}
android {
    namespace = "com.hazem.dailystore"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.hazem.dailystore"
        minSdk = 23
        targetSdk = 35
        versionCode = 1284
        versionName = "1.0.1"
    }
    sourceSets {
        getByName("main") {
            manifest.srcFile("../recovery/decompiled/resources/AndroidManifest.xml")
            java.setSrcDirs(listOf("../recovery/decompiled/sources"))
            java.exclude("**/R.java", "**/BuildConfig.java")
            res.setSrcDirs(listOf("../recovery/decompiled/resources/res"))
            assets.setSrcDirs(listOf("../recovery/decompiled/resources/assets"))
            jniLibs.setSrcDirs(listOf("../recovery/decompiled/resources/lib"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildTypes {
        debug { isMinifyEnabled = false }
        release { isMinifyEnabled = false }
    }
    packaging { jniLibs { useLegacyPackaging = false } }
}
dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.21")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.12.0")
    implementation("net.zetetic:sqlcipher-android:4.7.2")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("org.bouncycastle:bcprov-jdk18on:1.80")
}
