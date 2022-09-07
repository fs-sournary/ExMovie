/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.walkthroughfirststep

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.itlifelang.extrememovie.R
import com.itlifelang.extrememovie.databinding.FragmentWalkThroughFirstStepBinding
import com.itlifelang.extrememovie.mobile.ui.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalkThroughFirstStepFragment :
    BindingFragment<FragmentWalkThroughFirstStepBinding, WalkThroughFirstStepViewModel>() {
    override val viewModel: WalkThroughFirstStepViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_walk_through_first_step

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEvents()
    }

    private fun setupEvents() {
        binding.nextButton.setOnClickListener {
            val directions = WalkThroughFirstStepFragmentDirections.navigateToSecondStep()
            navController.navigate(directions)
        }
    }
}
