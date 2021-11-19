/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.extension

import android.content.Context
import android.graphics.Color
import androidx.annotation.AttrRes
import androidx.core.content.res.use
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Context.themeColor(@AttrRes themeAttrId: Int): Int = obtainStyledAttributes(
    intArrayOf(themeAttrId)
).use {
    it.getColor(0, Color.MAGENTA)
}
