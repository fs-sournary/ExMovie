package com.itlifelang.extrememovie.shared.data.preference

interface AppPreference {

    fun saveAccessToken(accessToken: String)

    fun getAccessToken(): String?
}
