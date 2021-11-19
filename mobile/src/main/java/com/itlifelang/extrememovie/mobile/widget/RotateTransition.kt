/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.widget

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewGroup
import androidx.transition.Transition
import androidx.transition.TransitionValues

/**
 * The [Transition] animates the rotation of a [View].
 */
class RotateTransition : Transition() {

    override fun getTransitionProperties(): Array<String> = arrayOf(ROTATION_PROPERTY)

    override fun captureStartValues(transitionValues: TransitionValues) {
        captureValues(transitionValues)
    }

    override fun captureEndValues(transitionValues: TransitionValues) {
        captureValues(transitionValues)
    }

    private fun captureValues(transitionValues: TransitionValues) {
        val view = transitionValues.view
        if (view.width <= 0 || view.height <= 0) return
        transitionValues.values[ROTATION_PROPERTY] = view.rotation
    }

    override fun createAnimator(
        sceneRoot: ViewGroup,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator? {
        if (startValues == null || endValues == null) return null
        val startRotation = startValues.values[ROTATION_PROPERTY] as Float
        val endRotation = endValues.values[ROTATION_PROPERTY] as Float
        if (startRotation == endRotation) return null
        val view = endValues.view
        view.pivotX = view.width / 2f
        view.pivotY = view.height / 2f
        return ObjectAnimator.ofFloat(view, View.ROTATION, startRotation, endRotation)
    }

    companion object {

        private const val ROTATION_PROPERTY = "extrememovie:rotate:rotation"
    }
}
