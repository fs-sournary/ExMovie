/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.walkthroughthirdstep

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.itlifelang.extrememovie.R
import com.itlifelang.extrememovie.databinding.FragmentWalkThroughThirdStepBinding
import com.itlifelang.extrememovie.mobile.ui.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalkThroughThirdStepFragment :
    BindingFragment<FragmentWalkThroughThirdStepBinding, WalkThroughThirdStepViewModel>() {

    override val viewModel: WalkThroughThirdStepViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_walk_through_third_step

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEvents()
    }

    private fun setupEvents() {
        binding.nextButton.setOnClickListener {
            viewModel.launchWalkThrough()
            val directions = WalkThroughThirdStepFragmentDirections.navigateToMain()
            navController.navigate(directions)
            activity?.finish()
        }
    }
}
