package com.itlifelang.extrememovie.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.itlifelang.extrememovie.R
import com.itlifelang.extrememovie.databinding.ActivityWalkThroughBinding
import com.itlifelang.extrememovie.shared.util.EdgeToEdge

class WalkThroughActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityWalkThroughBinding>(
            this,
            R.layout.activity_walk_through
        )
        binding.lifecycleOwner = this
        EdgeToEdge.setupRoot(binding.root, window)
    }
}
