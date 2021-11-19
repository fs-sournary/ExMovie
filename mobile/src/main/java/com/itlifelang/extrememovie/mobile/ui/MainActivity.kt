/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui

import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.itlifelang.extrememovie.R
import com.itlifelang.extrememovie.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private lateinit var binding: ActivityMainBinding
    private val slide = Slide(Gravity.BOTTOM)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        // Setup up bottom navigation with the navigation graph
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        ) as NavHostFragment
        navController = navHostFragment.navController
        binding.navigation.setupWithNavController(navController)
        // Listen when destination changes
        navController.addOnDestinationChangedListener { _, _, arguments ->
            val isShowBottomNavigation = arguments?.getBoolean(
                "showBottomNavigation", false
            ) == true
            if (binding.navigation.isVisible != isShowBottomNavigation) {
                TransitionManager.beginDelayedTransition(binding.navigation, slide)
                binding.navigation.isVisible = isShowBottomNavigation
            }
        }
    }

    fun navigateToSetting() {
        binding.navigation.selectedItemId = R.id.nav_profile
    }

    override fun onNavigateUp(): Boolean = navController.navigateUp()
}
