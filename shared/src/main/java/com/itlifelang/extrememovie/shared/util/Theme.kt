package com.itlifelang.extrememovie.shared.util

import androidx.appcompat.app.AppCompatDelegate
import com.itlifelang.extrememovie.shared.util.Theme.BATTERY_SAVER
import com.itlifelang.extrememovie.shared.util.Theme.DARK
import com.itlifelang.extrememovie.shared.util.Theme.LIGHT
import com.itlifelang.extrememovie.shared.util.Theme.SYSTEM

enum class Theme(val key: String) {
    LIGHT("light"),
    DARK("dark"),
    SYSTEM("system"),
    BATTERY_SAVER("battery_saver")
}

fun getThemeByKey(key: String): Theme = Theme.values().first { it.key == key }

/**
 * Update the app theme base on user setting
 */
fun updateTheme(theme: Theme) {
    val mode = when (theme) {
        LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
        DARK -> AppCompatDelegate.MODE_NIGHT_YES
        SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        BATTERY_SAVER -> AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
    }
    AppCompatDelegate.setDefaultNightMode(mode)
}
