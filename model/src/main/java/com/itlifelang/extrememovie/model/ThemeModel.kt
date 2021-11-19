/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.model

sealed class ThemeModel(val key: String) {

    object Light : ThemeModel("light")

    object Dark : ThemeModel("dark")

    object System : ThemeModel("system")

    object BatterySaver : ThemeModel("battery_saver")
}
