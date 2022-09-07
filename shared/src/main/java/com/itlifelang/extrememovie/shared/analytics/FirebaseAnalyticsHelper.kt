/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.analytics

import android.app.Activity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.itlifelang.extrememovie.shared.analytics.AnalyticsHelper.Companion.USER_SIGN_IN_PROPERTY
import com.itlifelang.extrememovie.shared.data.preference.AppPreference
import com.itlifelang.extrememovie.shared.data.preference.DataStorePreference
import com.itlifelang.extrememovie.shared.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber

class FirebaseAnalyticsHelper(
    @ApplicationScope private val externalScope: CoroutineScope,
    private val appPreference: AppPreference
) : AnalyticsHelper {
    private val firebaseAnalytics: FirebaseAnalytics = Firebase.analytics

    init {
        externalScope.launch {
            appPreference.enabledSendUsageStatics.collect {
                Timber.d("Send firebase analytics enable: $it")
                firebaseAnalytics.setAnalyticsCollectionEnabled(it)
            }
        }
        // Listen to the walk through, send usage statics state changes.
        externalScope.launch {
            flowOf(
                appPreference.launchWalkThrough.map {
                    DataStorePreference.PREF_IS_LAUNCH_WALK_THROUGH.name to it
                },
                appPreference.enabledSendUsageStatics.map {
                    DataStorePreference.PREF_IS_SEND_USAGE_STATICS.name to it
                }
            ).flattenMerge().collect { (key, value) ->
                val action = if (value) ANALYTICS_ENABLE_ACTION else ANALYTICS_DISABLE_ACTION
                recordUiEvent("Preference: $key", action)
            }
        }
        // Listen to the theme changes.
        externalScope.launch {
            appPreference.theme.collect {
                recordUiEvent("Preference: ${DataStorePreference.PREF_THEME.name}", it)
            }
        }
    }

    override fun recordScreenView(screenName: String, activity: Activity) {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
            param(FirebaseAnalytics.Param.SCREEN_CLASS, activity.localClassName)
        }
    }

    override fun recordUiEvent(itemId: String, action: String) {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
            param(FirebaseAnalytics.Param.ITEM_ID, itemId)
            param(FirebaseAnalytics.Param.CONTENT_TYPE, action)
        }
    }

    override fun setUserSignInProperty(signedIn: Boolean) {
        firebaseAnalytics.setUserProperty(USER_SIGN_IN_PROPERTY, signedIn.toString())
    }

    companion object {
        private const val ANALYTICS_ENABLE_ACTION = "enable"
        private const val ANALYTICS_DISABLE_ACTION = "disable"
    }
}
