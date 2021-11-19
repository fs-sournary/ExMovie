/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.extension

import android.app.Activity
import com.google.android.play.core.review.ReviewManagerFactory

fun Activity.reviewApp(onComplete: (() -> Unit)? = null, onFailed: (() -> Unit)? = null) {
    val manager = ReviewManagerFactory.create(this)
    manager.requestReviewFlow().addOnCompleteListener {
        if (it.isSuccessful) {
            val flow = manager.launchReviewFlow(this, it.result)
            flow.addOnCompleteListener {
                // This flow has finished. The API doesn't indicate whether the user reviewed or not
                // or even whether the review dialog was shown.
                onComplete?.invoke()
            }
        } else {
            onFailed?.invoke()
        }
    }
}
