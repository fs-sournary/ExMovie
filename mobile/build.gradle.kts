/**
 * Copyright 2021 ItLifeLang LLC
 */

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Version.COMPILE_SDK
    buildToolsVersion = Version.BUILD_TOOL
    defaultConfig {
        applicationId = "com.itlifelang.extrememovie"
        minSdk = Version.MIN_SDK
        targetSdk = Version.TARGET_SDK
        versionCode = Version.VERSION_CODE
        versionName = Version.VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            versionNameSuffix = "-debug"
        }
    }
    lint {
        checkGeneratedSources = true
        checkReleaseBuilds = false
    }
    buildFeatures {
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(project(":shared"))
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Kotlin
    implementation(Lib.KOTLIN_STDLIB)

    implementation(Lib.CORE_KTX)

    // UI
    implementation(Lib.SPLASH_SCREEN)
    implementation(Lib.APP_COMPAT)
    implementation(Lib.ACTIVITY_KTX)
    implementation(Lib.FRAGMENT_KTX)
    implementation(Lib.BROWSER)
    implementation(Lib.CONSTRAINT_LAYOUT)
    implementation(Lib.MATERIAL)
    implementation(Lib.PREFERENCE)

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

    // Glide
    implementation(Lib.GLIDE_RUNTIME)
    kapt(Lib.GLIDE_COMPILER)

    // Timber
    implementation(Lib.TIMBER)

    // Test
    testImplementation(Lib.JUNIT)
    testImplementation(Lib.EXT_JUNIT)
    testImplementation(Lib.ESPRESSO)
}
