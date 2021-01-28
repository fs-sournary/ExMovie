import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(Version.COMPILE_SDK)
    buildToolsVersion(Version.BUILD_TOOL)
    defaultConfig {
        minSdkVersion(Version.MIN_SDK)
        targetSdkVersion(Version.TARGET_SDK)
        versionCode(Version.VERSION_CODE)
        versionName(Version.VERSION_NAME)
        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
        consumerProguardFile("consumer-rules.pro")
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.incremental"] = "true"
            }
        }
    }
    buildTypes {
        release {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        all {
            buildConfigField("String", "BASE_URL", properties["BASE_URL"] as String)
            buildConfigField("String", "BASE_IMAGE_URL", properties["BASE_IMAGE_URL"] as String)
            buildConfigField(
                type = "String",
                name = "API_KEY",
                value = Properties().apply {
                    load(FileInputStream(File("secret.properties")))
                }["API_KEY"] as String
            )
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":model"))

    // UI
    implementation(Lib.APP_COMPAT)
    implementation(Lib.ACTIVITY_KTX)
    implementation(Lib.FRAGMENT_KTX)
    implementation(Lib.BROWSER)
    implementation(Lib.CONSTRAINT_LAYOUT)
    implementation(Lib.MATERIAL)

    implementation(Lib.TIMBER)

    // Kotlin
    implementation(Lib.KOTLIN_STDLIB)
    implementation(Lib.CORE_KTX)

    // Navigation
    implementation(Lib.NAVIGATION_FRAGMENT_KTX)
    implementation(Lib.NAVIGATION_UI_KTX)

    // Room
    implementation(Lib.ROOM_KTX)
    kapt(Lib.ROOM_COMPILER)

    // Paging
    implementation(Lib.PAGING_KTX)

    // Coroutine
    implementation(Lib.COROUTINES_CORE)
    implementation(Lib.COROUTINES_ANDROID)

    // Retrofit and net
    implementation(Lib.OKHTTP_LOGGING_INTERCEPTOR)
    implementation(Lib.RETROFIT_RUNTIME)
    implementation(Lib.RETROFIT_GSON)

    // Hilt
    implementation(Lib.HILT)
    kapt(Lib.HILT_COMPILER)

    // WorkManager
    implementation(Lib.WORK_KTX)

    // Firebase
    implementation(platform(Lib.FIREBASE_BOM))
    implementation(Lib.FIREBASE_ANALYTICS_KTX)
    implementation(Lib.FIREBASE_CRASHLYTICS_KTX)
    implementation(Lib.FIREBASE_AUTH)
    implementation(Lib.FIREBASE_FIRESTORE)
    implementation(Lib.FIREBASE_MESSAGING)
    implementation(Lib.FIREBASE_ANALYTICS_KTX)
}
