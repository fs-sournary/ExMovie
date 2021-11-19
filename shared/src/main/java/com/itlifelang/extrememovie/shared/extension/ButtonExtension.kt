/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.extension

import android.content.res.ColorStateList
import androidx.annotation.AttrRes
import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButton

@BindingAdapter("iconTintAttrColor")
fun MaterialButton.setIconTintAttrColor(@AttrRes attrColorId: Int) {
    iconTint = ColorStateList.valueOf(context.themeColor(attrColorId))
}
