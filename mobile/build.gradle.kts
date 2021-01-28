plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(Version.COMPILE_SDK)
    buildToolsVersion(Version.BUILD_TOOL)
    defaultConfig {
        applicationId = "com.itlifelang.extrememovie"
        minSdkVersion(Version.MIN_SDK)
        targetSdkVersion(Version.TARGET_SDK)
        versionCode(Version.VERSION_CODE)
        versionName(Version.VERSION_NAME)
        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }
    buildTypes {
        release {
            minifyEnabled(true)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            versionNameSuffix("-debug")
        }
    }
    lintOptions {
        isCheckGeneratedSources = true
        isCheckReleaseBuilds = false
    }
    buildFeatures {
        dataBinding = true
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

    implementation(project(":shared"))
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Kotlin
    implementation(Lib.KOTLIN_STDLIB)

    implementation(Lib.CORE_KTX)

    // UI
    implementation(Lib.APP_COMPAT)
    implementation(Lib.ACTIVITY_KTX)
    implementation(Lib.FRAGMENT_KTX)
    implementation(Lib.BROWSER)
    implementation(Lib.CONSTRAINT_LAYOUT)
    implementation(Lib.MATERIAL)

    // Lifecycle
    implementation(Lib.LIFECYCLE_EXTENSIONS)
    implementation(Lib.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Lib.LIFECYCLE_LIVEDATA_KTX)
    implementation(Lib.LIFECYCLE_VIEWMODEL_SAVEDSTATE)

    // Navigation
    implementation(Lib.NAVIGATION_FRAGMENT_KTX)
    implementation(Lib.NAVIGATION_UI_KTX)

    // Paging
    implementation(Lib.PAGING_KTX)

    // WorkManager
    implementation(Lib.WORK_KTX)

    // Coroutine
    implementation(Lib.COROUTINES_CORE)
    implementation(Lib.COROUTINES_ANDROID)

    // Hilt
    implementation(Lib.HILT)
    kapt(Lib.HILT_COMPILER)
    implementation(Lib.HILT_ANDROID_VIEWMODEL)
    kapt(Lib.HILT_ANDROID_COMPILER)

    // Glide
    implementation(Lib.GLIDE_RUNTIME)
    kapt(Lib.GLIDE_COMPILER)

    // Timber
    implementation(Lib.TIMBER)
}
