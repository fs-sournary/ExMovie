package com.itlifelang.extrememovie.shared.data.preference

import android.content.Context
import androidx.core.content.edit

class DefaultAppPreference(context: Context) : AppPreference {

    private val preference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    override fun saveAccessToken(accessToken: String) {
        preference.edit { putString(ACCESS_TOKEN_KEY, accessToken) }
    }

    override fun getAccessToken(): String? = preference.getString(ACCESS_TOKEN_KEY, STRING_DEF)

    companion object {

        private const val STRING_DEF = ""

        private const val PREF_NAME = "extreme_movie_preference"
        private const val ACCESS_TOKEN_KEY = "access_token"
    }
}
