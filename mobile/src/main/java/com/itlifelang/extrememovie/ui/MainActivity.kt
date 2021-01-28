package com.itlifelang.extrememovie.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.itlifelang.extrememovie.R
import com.itlifelang.extrememovie.databinding.ActivityMainBinding
import com.itlifelang.extrememovie.shared.extension.setupWithNavController
import com.itlifelang.extrememovie.shared.util.EdgeToEdge

class MainActivity : AppCompatActivity() {

    private var currentNavController: LiveData<NavController>? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        EdgeToEdge.setupRoot(binding.root, window)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val navGraphIds = listOf(
            R.navigation.nav_movie,
            R.navigation.nav_tv,
            R.navigation.nav_profile
        )
        val navController = binding.navigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment,
            intent = intent
        )
        navController.observe(this) {

        }
        currentNavController = navController
    }
}

