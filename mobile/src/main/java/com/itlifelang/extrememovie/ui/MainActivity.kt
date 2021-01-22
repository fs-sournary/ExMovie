package com.itlifelang.extrememovie.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.itlifelang.extrememovie.R
import com.itlifelang.extrememovie.databinding.ActivityMainBinding
import com.itlifelang.extrememovie.util.EdgeToEdge

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        EdgeToEdge.setupRoot(binding.root, window)
    }

    private fun setupBottomNavigation() {

    }
}

