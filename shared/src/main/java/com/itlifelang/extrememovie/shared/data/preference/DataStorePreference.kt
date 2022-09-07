/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.itlifelang.extrememovie.model.Theme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStorePreference(private val dataStore: DataStore<Preferences>) : AppPreference {
    override suspend fun saveAccessToken(accessToken: String) {
        dataStore.edit { it[PREF_ACCESS_TOKEN] = accessToken }
    }

    override val accessToken: Flow<String> = dataStore.data.map { it[PREF_ACCESS_TOKEN] ?: "" }

    override suspend fun saveTheme(theme: String) {
        dataStore.edit { it[PREF_THEME] = theme }
    }

    override val theme: Flow<String> = dataStore.data.map {
        it[PREF_THEME] ?: Theme.System.key
    }

    override suspend fun saveLaunchWalkThrough(isLaunch: Boolean) {
        dataStore.edit { it[PREF_IS_LAUNCH_WALK_THROUGH] = isLaunch }
    }

    override val launchWalkThrough: Flow<Boolean> = dataStore.data.map {
        it[PREF_IS_LAUNCH_WALK_THROUGH] ?: false
    }

    override suspend fun saveSendUsageStatics(isSend: Boolean) {
        dataStore.edit { it[PREF_IS_SEND_USAGE_STATICS] = isSend }
    }

    override val enabledSendUsageStatics: Flow<Boolean> = dataStore.data.map {
        it[PREF_IS_SEND_USAGE_STATICS] ?: false
    }

    companion object {
        const val PREF_NAME = "extreme_movie_preference"

        val PREF_ACCESS_TOKEN = stringPreferencesKey("pref_access_token")
        val PREF_THEME = stringPreferencesKey("pref_theme")
        val PREF_IS_LAUNCH_WALK_THROUGH = booleanPreferencesKey("pref_is_launch_walk_through")
        val PREF_IS_SEND_USAGE_STATICS = booleanPreferencesKey("pref_is_send_usage_statics")
    }
}
