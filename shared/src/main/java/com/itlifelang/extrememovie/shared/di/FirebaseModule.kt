/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.di

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.itlifelang.extrememovie.shared.analytics.AnalyticsHelper
import com.itlifelang.extrememovie.shared.analytics.FirebaseAnalyticsHelper
import com.itlifelang.extrememovie.shared.data.preference.AppPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope

@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebaseAnalytics(): FirebaseAnalytics = Firebase.analytics

    @Singleton
    @Provides
    fun provideAnalyticsHelper(
        @ApplicationScope externalScope: CoroutineScope,
        appPreference: AppPreference
    ): AnalyticsHelper = FirebaseAnalyticsHelper(externalScope, appPreference)
}
