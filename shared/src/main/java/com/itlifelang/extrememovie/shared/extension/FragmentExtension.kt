package com.itlifelang.extrememovie.shared.extension

import androidx.fragment.app.Fragment
import com.itlifelang.extrememovie.shared.util.AutoClearedValue

fun <T> Fragment.autoClear(): AutoClearedValue<T> = AutoClearedValue(this)
