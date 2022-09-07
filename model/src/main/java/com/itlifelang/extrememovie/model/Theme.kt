/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.model

sealed class Theme(val key: String) {

    object Light : Theme("light")

    object Dark : Theme("dark")

    object System : Theme("system")

    object BatterySaver : Theme("battery_saver")
}
