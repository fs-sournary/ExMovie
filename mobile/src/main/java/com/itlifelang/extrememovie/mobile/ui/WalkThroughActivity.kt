/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import com.itlifelang.extrememovie.R
import com.itlifelang.extrememovie.databinding.ActivityWalkThroughBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalkThroughActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityWalkThroughBinding>(
            this,
            R.layout.activity_walk_through
        ).apply {
            lifecycleOwner = this@WalkThroughActivity
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}
