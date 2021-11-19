/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LauncherActivity : AppCompatActivity() {

    private val viewModel: LauncherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.launcherDestination.collect {
                    when (it) {
                        LauncherNavigationDestination.Main -> {
                            val intent = Intent(
                                this@LauncherActivity,
                                MainActivity::class.java
                            )
                            startActivity(intent)
                        }
                        LauncherNavigationDestination.WalkThrough -> {
                            val intent = Intent(
                                this@LauncherActivity,
                                WalkThroughActivity::class.java
                            )
                            startActivity(intent)
                        }
                    }
                    finish()
                }
            }
        }
    }
}
