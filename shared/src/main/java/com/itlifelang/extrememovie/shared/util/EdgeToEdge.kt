package com.itlifelang.extrememovie.shared.util

import android.os.Build
import android.view.View
import android.view.Window

/**
 * An object class that help to implement edge-to-edge display.
 */
object EdgeToEdge {

    /**
     * Implement edge-to-edge display in the root of an Activity.
     */
    @Suppress("DEPRECATION")
    fun setupRoot(rootView: View, window: Window) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            // Laid out as if the navigation bar was hidden
            rootView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                // Laid out fullscreen, behind status bar
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                // Laid out at the most extreme scenario of any other flags
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        } else {
            // Tell the window that we want to handle/fit any system windows
            window.setDecorFitsSystemWindows(false)
        }
    }
}
