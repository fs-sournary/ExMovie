/**
 * Copyright 2021 ItLifeLang LLC
 */

import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}

android {
    compileSdk = Version.COMPILE_SDK
    buildToolsVersion = Version.BUILD_TOOL
    defaultConfig {
        minSdk = Version.MIN_SDK
        targetSdk = Version.TARGET_SDK
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFile("consumer-rules.pro")
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.incremental"] = "true"
            }
        }
        // Build config fields
        val properties = gradleLocalProperties(rootDir)
        buildConfigField("String", "API_KEY", properties["api_key"] as String)
        buildConfigField("String", "BASE_URL", project.properties["base_url"] as String)
        buildConfigField("String", "BASE_IMAGE_URL", project.properties["base_image_url"] as String)
    }
    buildTypes {
        release {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        dataBinding = true
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    api(project(":model"))

    // UI
    implementation(Lib.APP_COMPAT)
    implementation(Lib.ACTIVITY_KTX)
    implementation(Lib.FRAGMENT_KTX)
    implementation(Lib.BROWSER)
    implementation(Lib.CONSTRAINT_LAYOUT)
    implementation(Lib.MATERIAL)
    implementation(Lib.DATASTORE)

    implementation(Lib.TIMBER)

    // Kotlin
    implementation(Lib.KOTLIN_STDLIB)
    implementation(Lib.CORE_KTX)

    // Lifecycle
    implementation(Lib.LIFECYCLE_RUNTIME)
    implementation(Lib.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Lib.LIFECYCLE_LIVEDATA_KTX)
    implementation(Lib.LIFECYCLE_VIEWMODEL_SAVEDSTATE)
    kapt(Lib.LIFECYCLE_COMPILER)

    // Navigation
    implementation(Lib.NAVIGATION_FRAGMENT_KTX)
    implementation(Lib.NAVIGATION_UI_KTX)

    // Room
    implementation(Lib.ROOM_RUNTIME)
    implementation(Lib.ROOM_KTX)
    implementation(Lib.ROOM_PAGING)
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

    // Glide
    implementation(Lib.GLIDE_RUNTIME)
    kapt(Lib.GLIDE_COMPILER)

    // Hilt
    implementation(Lib.HILT)
    kapt(Lib.HILT_COMPILER)

    // WorkManager
    implementation(Lib.WORK_KTX)

    // Google play
    implementation(Lib.PLAY_CORE_KTX)

    // Firebase
    implementation(platform(Lib.FIREBASE_BOM))
    implementation(Lib.FIREBASE_ANALYTICS_KTX)
    implementation(Lib.FIREBASE_CRASHLYTICS_KTX)
    implementation(Lib.FIREBASE_AUTH)
    implementation(Lib.FIREBASE_FIRESTORE)
    implementation(Lib.FIREBASE_MESSAGING)
    implementation(Lib.FIREBASE_ANALYTICS_KTX)
}
