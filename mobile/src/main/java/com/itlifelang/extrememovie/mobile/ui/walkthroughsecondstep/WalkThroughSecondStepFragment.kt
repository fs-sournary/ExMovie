/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.walkthroughsecondstep

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.itlifelang.extrememovie.R
import com.itlifelang.extrememovie.databinding.FragmentWalkThroughSecondStepBinding
import com.itlifelang.extrememovie.mobile.ui.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalkThroughSecondStepFragment :
    BindingFragment<FragmentWalkThroughSecondStepBinding, WalkThroughSecondStepViewModel>() {
    override val viewModel: WalkThroughSecondStepViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_walk_through_second_step

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEvents()
    }

    private fun setupEvents() {
        binding.nextButton.setOnClickListener {
            val directions = WalkThroughSecondStepFragmentDirections.navigateToThirdStep()
            navController.navigate(directions)
        }
    }
}
