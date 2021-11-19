/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.widget

import android.content.Context
import android.graphics.Color
import android.text.TextPaint
import androidx.core.content.res.ResourcesCompat
import androidx.core.content.withStyledAttributes
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.itlifelang.extrememovie.R
import com.itlifelang.extrememovie.mobile.data.Movie
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * The [ItemDecoration] draws sticker headers marking days in the given list of [Movie]s. It also
 * provide gaps between days.
 */
class DateStickerItemDecoration(context: Context, movies: List<Movie>) : ItemDecoration() {

    private val dateFormatter = SimpleDateFormat("d", Locale.getDefault())
    private val dayFormatter = SimpleDateFormat("eee", Locale.getDefault())

    private var width: Int = 0
    private var paddingTop: Int = 0
    private var dayTextSize: Int = 0

    private val paint: TextPaint = TextPaint(TextPaint.ANTI_ALIAS_FLAG)

    init {
        context.withStyledAttributes(
            resourceId = R.style.Widget_ExtremeMovie_DateStickerDecoration,
            attrs = R.styleable.DateSticker
        ) {
            val fontFamily = getResourceId(R.styleable.DateSticker_android_fontFamily, 0)
            paint.typeface = ResourcesCompat.getFont(context, fontFamily)
            paint.color = getColor(R.styleable.DateSticker_android_textColor, Color.MAGENTA)
            paint.textSize = getDimension(R.styleable.DateSticker_dateTextSize, 0f)
            width = getDimensionPixelOffset(R.styleable.DateSticker_android_width, 0)
            paddingTop = getDimensionPixelOffset(R.styleable.DateSticker_android_paddingTop, 0)
            dayTextSize = getDimensionPixelSize(R.styleable.DateSticker_dayTextSize, 0)
        }
    }
}
