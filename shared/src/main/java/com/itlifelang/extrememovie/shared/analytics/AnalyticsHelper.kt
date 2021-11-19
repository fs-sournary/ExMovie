/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.analytics

import android.app.Activity

/**
 * Analytics API interface.
 */
interface AnalyticsHelper {

    /**
     * Record the screen with [screenName] that user has opened.
     */
    fun recordScreenView(screenName: String, activity: Activity)

    /**
     * Record the UI [action] with [itemId] such as clicks button...
     */
    fun recordUiEvent(itemId: String, action: String)

    /**
     * Set user sign in property.
     */
    fun setUserSignInProperty(signedIn: Boolean)

    companion object {

        const val APP_TYPE_PROPERTY = "app_type"
        const val STATUS_INSTANT = "instant"
        const val STATUS_INSTALLED = "installed"
        const val SCREEN_CONTENT_TYPE = "screen"
        const val USER_SIGN_IN_PROPERTY = "user_sign_in"
    }
}
