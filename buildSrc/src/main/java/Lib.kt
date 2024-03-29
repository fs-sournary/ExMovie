object Lib {

    const val ANDROID_GRADLE_PLUGIN =
        "com.android.tools.build:gradle:${Version.ANDROID_GRADLE_PLUGIN}"

    const val CORE_KTX = "androidx.core:core-ktx:${Version.CORE_KTX}"

    // UI
    const val SPLASH_SCREEN = "androidx.core:core-splashscreen:${Version.SPLASH_SCREEN}"
    const val ACTIVITY_KTX = "androidx.activity:activity-ktx:${Version.ACTIVITY_KTX}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Version.FRAGMENT_KTX}"
    const val APP_COMPAT = "androidx.appcompat:appcompat: ${Version.APP_COMPAT}"
    const val ARCH_CORE_RUNTIME = "androidx.arch.core:core-runtime:${Version.ARCH_CORE}"
    const val ARCH_CORE_TESTING = "androidx.arch.core:core-testing:${Version.ARCH_CORE}"
    const val BROWSER = "androidx.browser:browser:${Version.BROWSER}"
    const val TIMBER = "com.jakewharton.timber:timber:${Version.TIMBER}"
    const val TRANSITION = "androidx.transition:transition:${Version.TRANSITION}"
    const val PREFERENCE = "androidx.preference:preference-ktx:${Version.PREFERENCE}"
    const val SWIPE_REFRESH_LAYOUT =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Version.SWIPE_REFRESH_LAYOUT}"
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Version.CONSTRAINT_LAYOUT}"
    const val MATERIAL = "com.google.android.material:material:${Version.MATERIAL}"
    const val DATASTORE = "androidx.datastore:datastore-preferences:${Version.DATASTORE}"

    // Lifecycle
    const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.LIFECYCLE}"
    const val LIFECYCLE_VIEWMODEL_KTX =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.LIFECYCLE}"
    const val LIFECYCLE_LIVEDATA_KTX =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Version.LIFECYCLE}"
    const val LIFECYCLE_VIEWMODEL_SAVEDSTATE =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Version.LIFECYCLE}"
    const val LIFECYCLE_COMPILER = "androidx.lifecycle:lifecycle-compiler:${Version.LIFECYCLE}"

    // Navigation
    const val NAVIGATION_FRAGMENT_KTX =
        "androidx.navigation:navigation-fragment-ktx:${Version.NAVIGATION}"
    const val NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:${Version.NAVIGATION}"
    const val NAVIGATION_SAFE_ARGS =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.NAVIGATION}"

    // Room
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Version.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Version.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Version.ROOM}"
    const val ROOM_PAGING = "androidx.room:room-paging:${Version.ROOM_PAGING}"

    // Paging
    const val PAGING_KTX = "androidx.paging:paging-runtime-ktx:${Version.PAGING}"

    // WorkManager
    const val WORK_KTX = "androidx.work:work-runtime-ktx:${Version.WORK}"

    // Hilt
    const val HILT_GRADLE_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:${Version.HILT}"
    const val HILT = "com.google.dagger:hilt-android:${Version.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Version.HILT}"
    const val HILT_ANDROID_VIEWMODEL =
        "androidx.hilt:hilt-lifecycle-viewmodel:${Version.HILT_ANDROID}"
    const val HILT_ANDROID_COMPILER = "androidx.hilt:hilt-compiler:${Version.HILT_ANDROID}"

    // kotlin
    const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.KOTLIN}"
    const val KOTLIN_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KOTLIN}"
    const val KOTLIN_ALLOPEN = "org.jetbrains.kotlin:kotlin-allopen:${Version.KOTLIN}"

    // Coroutines
    const val COROUTINES_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.COROUTINES}"
    const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.COROUTINES}"
    const val COROUTINES_TEST =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.COROUTINES}"

    // Retrofit and net
    const val OKHTTP_LOGGING_INTERCEPTOR =
        "com.squareup.okhttp3:logging-interceptor:${Version.OKHTTP_LOGGING_INTERCEPTOR}"
    const val RETROFIT_RUNTIME = "com.squareup.retrofit2:retrofit:${Version.RETROFIT}"
    const val RETROFIT_GSON = "com.squareup.retrofit2:converter-gson:${Version.RETROFIT}"

    // Glide
    const val GLIDE_RUNTIME = "com.github.bumptech.glide:glide:${Version.GLIDE}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Version.GLIDE}"

    // Firebase
    const val GOOGLE_SERVICE = "com.google.gms:google-services:${Version.GOOGLE_SERVICE}"
    const val FIREBASE_CRASHLYTICS_GRADLE =
        "com.google.firebase:firebase-crashlytics-gradle:${Version.FIREBASE_CRASHLYTICS_GRADLE}"
    const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${Version.FIREBASE_BOM}"
    const val FIREBASE_ANALYTICS_KTX = "com.google.firebase:firebase-analytics-ktx"
    const val FIREBASE_CRASHLYTICS_KTX = "com.google.firebase:firebase-crashlytics-ktx"
    const val FIREBASE_AUTH = "com.google.firebase:firebase-auth-ktx"
    const val FIREBASE_FIRESTORE = "com.google.firebase:firebase-firestore-ktx"
    const val FIREBASE_MESSAGING = "com.google.firebase:firebase-messaging-ktx"

    // Google play
    const val PLAY_CORE_KTX = "com.google.android.play:core-ktx:${Version.PLAY_CORE_KTX}"

    // Testing
    const val JUNIT = "junit:junit:${Version.JUNIT}"
    const val EXT_JUNIT = "androidx.test.ext:junit:${Version.EXT_JUNIT}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Version.ESPRESSO}"
}
