/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter(
    value = [
        "glideImageUrl",
        "glidePlaceHolder",
        "glideErrorImage",
        "centerCrop",
        "centerInside",
        "circleCrop"
    ],
    requireAll = false
)
fun ImageView.setImageUrl(
    url: String?,
    placeHolder: Drawable?,
    errorDrawable: Drawable?,
    isCenterCrop: Boolean,
    isCenterInside: Boolean,
    isCircleCrop: Boolean
) {
    var requestOptions = RequestOptions.placeholderOf(placeHolder).error(errorDrawable)
    if (isCenterCrop) {
        requestOptions = requestOptions.centerCrop()
    }
    if (isCenterInside) {
        requestOptions = requestOptions.centerInside()
    }
    if (isCircleCrop) {
        requestOptions = requestOptions.circleCrop()
    }
    Glide.with(context)
        .load(url)
        .apply(requestOptions)
        .into(this)
}

@BindingAdapter(
    value = ["drawable", "centerCrop", "circleCrop"],
    requireAll = false
)
fun ImageView.setImageDrawable(
    drawable: Drawable,
    isCenterCrop: Boolean,
    isCircleCrop: Boolean
) {
    var requestOptions = RequestOptions()
    if (isCenterCrop) {
        requestOptions = requestOptions.centerCrop()
    }
    if (isCircleCrop) {
        requestOptions = requestOptions.circleCrop()
    }
    Glide.with(context)
        .load(drawable)
        .apply(requestOptions)
        .into(this)
}
