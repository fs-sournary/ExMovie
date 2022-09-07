/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.util

import androidx.appcompat.app.AppCompatDelegate
import com.itlifelang.extrememovie.model.Theme
import com.itlifelang.extrememovie.model.Theme.BatterySaver
import com.itlifelang.extrememovie.model.Theme.Dark
import com.itlifelang.extrememovie.model.Theme.Light
import com.itlifelang.extrememovie.model.Theme.System

/**
 * Update the app theme base on user setting
 */
object ThemeUtil {
    fun getThemeFromPreferenceKey(key: String): Theme = when (key) {
        Light.key -> Light
        Dark.key -> Dark
        System.key -> System
        BatterySaver.key -> BatterySaver
        else -> Light
    }

    fun updateTheme(theme: Theme) {
        val mode = when (theme) {
            is Light -> AppCompatDelegate.MODE_NIGHT_NO
            is Dark -> AppCompatDelegate.MODE_NIGHT_YES
            is System -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            is BatterySaver -> AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}
