package com.itlifelang.extrememovie.shared.extension

import android.os.Build
import android.view.WindowInsets
import com.itlifelang.extrememovie.shared.model.SystemWindowInsetEdge

@Suppress("DEPRECATION")
fun WindowInsets.getSystemWindowInsetEdges(): SystemWindowInsetEdge {
    return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
        SystemWindowInsetEdge(
            left = systemWindowInsetLeft,
            top = systemWindowInsetTop,
            right = systemWindowInsetRight,
            bottom = systemWindowInsetBottom
        )
    } else {
        val systemWindowInset = getInsets(
            WindowInsets.Type.systemBars() or WindowInsets.Type.ime()
        )
        SystemWindowInsetEdge(
            left = systemWindowInset.left,
            top = systemWindowInset.top,
            right = systemWindowInset.right,
            bottom = systemWindowInset.bottom
        )
    }
}
