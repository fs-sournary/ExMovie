/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.extension

import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.forEach
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.databinding.BindingAdapter

@BindingAdapter("visibleWhen")
fun View.setVisibleGone(visible: Boolean) {
    isVisible = visible
}

@BindingAdapter(
    value = [
        "paddingLeftSystemWindowInset",
        "paddingTopSystemWindowInset",
        "paddingRightSystemWindowInset",
        "paddingBottomSystemWindowInset"
    ],
    requireAll = false
)
fun View.applySystemWindowInsetPadding(
    previousApplyLeft: Boolean,
    previousApplyTop: Boolean,
    previousApplyRight: Boolean,
    previousApplyBottom: Boolean,
    applyLeft: Boolean,
    applyTop: Boolean,
    applyRight: Boolean,
    applyBottom: Boolean
) {
    if (previousApplyLeft == applyLeft &&
        previousApplyTop == applyTop &&
        previousApplyRight == applyRight &&
        previousApplyBottom == applyBottom
    ) {
        return
    }
    doOnApplyWindowInset { view, windowInsets, initialPadding, _, _ ->
        val systemWindow = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
        val leftInset = if (applyLeft) systemWindow.left else 0
        val topInset = if (applyTop) systemWindow.top else 0
        val rightInset = if (applyRight) systemWindow.right else 0
        val bottomInset = if (applyBottom) systemWindow.bottom else 0
        view.updatePadding(
            left = initialPadding.left + leftInset,
            top = initialPadding.top + topInset,
            right = initialPadding.right + rightInset,
            bottom = initialPadding.bottom + bottomInset
        )
    }
}

@BindingAdapter(
    value = [
        "marginLeftSystemWindowInset",
        "marginTopSystemWindowInset",
        "marginRightSystemWindowInset",
        "marginBottomSystemWindowInset"
    ],
    requireAll = false
)
fun View.applySystemWindowInsetsMargin(
    previousApplyLeft: Boolean,
    previousApplyTop: Boolean,
    previousApplyRight: Boolean,
    previousApplyBottom: Boolean,
    applyLeft: Boolean,
    applyTop: Boolean,
    applyRight: Boolean,
    applyBottom: Boolean
) {
    if (previousApplyLeft == applyLeft &&
        previousApplyTop == applyTop &&
        previousApplyRight == applyRight &&
        previousApplyBottom == applyBottom
    ) {
        return
    }
    doOnApplyWindowInset { view, windowInsets, _, initialMargin, _ ->
        val systemWindow = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
        val left = if (applyLeft) systemWindow.left else 0
        val top = if (applyTop) systemWindow.top else 0
        val right = if (applyRight) systemWindow.right else 0
        val bottom = if (applyBottom) systemWindow.bottom else 0
        windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
        view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            leftMargin = initialMargin.left + left
            topMargin = initialMargin.top + top
            rightMargin = initialMargin.right + right
            bottomMargin = initialMargin.bottom + bottom
        }
    }
}

fun View.doOnApplyWindowInset(
    block: (View, WindowInsetsCompat, InitialPadding, InitialMargin, Int) -> Unit
) {
    val initialPadding = recordInitialPaddingForView()
    val initialMargin = recordInitialMarginForView()
    val initialHeight = layoutParams.height
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, windowInsets ->
        block(v, windowInsets, initialPadding, initialMargin, initialHeight)
        windowInsets
    }
    requestWindowInsetWhenAttached()
}

fun View.recordInitialPaddingForView(): InitialPadding = InitialPadding(
    paddingLeft, paddingTop, paddingRight, paddingBottom
)

fun View.recordInitialMarginForView(): InitialMargin {
    val lp = layoutParams as? ViewGroup.MarginLayoutParams
        ?: throw IllegalStateException("Invalid view layout params")
    return InitialMargin(lp.leftMargin, lp.topMargin, lp.rightMargin, lp.bottomMargin)
}

fun View.requestWindowInsetWhenAttached() {
    if (isAttachedToWindow) {
        // If the View has already attached, just request insets as normal
        requestApplyInsets()
    } else {
        // If the View hasn't attached, add a listener to request insets when it is attached.
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                // v.removeOnAttachStateChangeListener(this)
                v.requestApplyInsets()
            }

            override fun onViewDetachedFromWindow(v: View?) = Unit
        })
    }
}

@BindingAdapter("dispatchApplyWindowInsetsToChild")
fun View.dispatchApplyWindowInsetsToChild(applyWindowInset: Boolean) {
    if (this is ViewGroup && applyWindowInset) {
        setOnApplyWindowInsetsListener { _, insets ->
            forEach { it.dispatchApplyWindowInsets(insets) }
            insets
        }
    }
}

data class InitialPadding(val left: Int, val top: Int, val right: Int, val bottom: Int)

data class InitialMargin(val left: Int, val top: Int, val right: Int, val bottom: Int)
