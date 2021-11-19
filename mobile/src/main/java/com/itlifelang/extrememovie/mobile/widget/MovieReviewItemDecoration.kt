/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.res.getDrawableOrThrow
import androidx.core.view.forEachIndexed
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.State
import com.itlifelang.extrememovie.R

class MovieReviewItemDecoration(
    context: Context
) : RecyclerView.ItemDecoration() {

    private val left = context.resources.getDimensionPixelSize(R.dimen.dp_16)
    private val divider: Drawable

    init {
        val typeArray = context.obtainStyledAttributes(ATTRS)
        divider = typeArray.getDrawableOrThrow(0)
        typeArray.recycle()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
        outRect.bottom = divider.intrinsicHeight
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: State) {
        val childCount = parent.childCount
        val right = parent.width - left
        parent.forEachIndexed { index, view ->
            if (index != childCount - 1) {
                val top = view.bottom
                val bottom = top + divider.intrinsicHeight
                divider.setBounds(left, top, right, bottom)
                divider.draw(c)
            }
        }
    }

    companion object {

        private val ATTRS = intArrayOf(android.R.attr.listDivider)
    }
}
