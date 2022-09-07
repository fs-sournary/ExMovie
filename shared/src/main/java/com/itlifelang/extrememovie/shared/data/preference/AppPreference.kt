/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.preference

import kotlinx.coroutines.flow.Flow

interface AppPreference {
    suspend fun saveAccessToken(accessToken: String)
    val accessToken: Flow<String>

    suspend fun saveTheme(theme: String)
    val theme: Flow<String>

    suspend fun saveLaunchWalkThrough(isLaunch: Boolean)
    val launchWalkThrough: Flow<Boolean>

    suspend fun saveSendUsageStatics(isSend: Boolean)
    val enabledSendUsageStatics: Flow<Boolean>
}
