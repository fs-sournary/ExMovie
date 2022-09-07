/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.extension

import android.view.View
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.itlifelang.extrememovie.shared.util.AutoClearedValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun <T> Fragment.autoClear(destroyViewAction: (() -> Unit)? = null): AutoClearedValue<T> {
    return AutoClearedValue(this, destroyViewAction)
}

fun Fragment.startPostponedTransitionOnPreDraw(view: View) {
    postponeEnterTransition()
    view.doOnPreDraw { startPostponedEnterTransition() }
}

fun Fragment.showKeyBoard(view: View) {
    activity?.window?.let {
        WindowCompat.getInsetsController(it, view).show(WindowInsetsCompat.Type.ime())
    }
}

fun Fragment.dismissKeyBoard(view: View) {
    activity?.window?.let {
        WindowCompat.getInsetsController(it, view).hide(WindowInsetsCompat.Type.ime())
    }
}

inline fun <T> Fragment.flowWithViewLifecycle(
    flow: Flow<T>,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend (T) -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        flow.flowWithLifecycle(viewLifecycleOwner.lifecycle, minActiveState)
            .collectLatest { block(it) }
    }
}
