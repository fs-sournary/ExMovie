package com.itlifelang.extrememovie.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.itlifelang.extrememovie.shared.util.EdgeToEdge
import kotlinx.coroutines.delay

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EdgeToEdge.setupRoot(findViewById(android.R.id.content), window)
        lifecycleScope.launchWhenCreated {
            delay(SPLASH_DURATION)
            startActivity(Intent(this@LauncherActivity, WalkThroughActivity::class.java))
            finish()
        }
    }

    companion object {

        private const val SPLASH_DURATION = 1000L
    }
}
