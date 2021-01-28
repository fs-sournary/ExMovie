package com.itlifelang.extrememovie.ui.walkthroughsecondstep

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.viewModels
import com.itlifelang.extrememovie.R
import com.itlifelang.extrememovie.databinding.FragmentWalkThroughSecondStepBinding
import com.itlifelang.extrememovie.shared.extension.getSystemWindowInsetEdges
import com.itlifelang.extrememovie.ui.BindingFragment

class WalkThroughSecondStepFragment :
    BindingFragment<FragmentWalkThroughSecondStepBinding, WalkThroughSecondStepViewModel>() {

    override val viewModel: WalkThroughSecondStepViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_walk_through_second_step

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bonusBottomMargin = resources.getDimensionPixelOffset(R.dimen.dp_32)
        view.setOnApplyWindowInsetsListener { _, insets ->
            binding.nextButton.updateLayoutParams<ConstraintLayout.LayoutParams> {
                val bottomMargin = bonusBottomMargin + insets.getSystemWindowInsetEdges().bottom
                setMargins(0, 0, 0, bottomMargin)
            }
            insets
        }
        binding.nextButton.setOnClickListener {
            val directions = WalkThroughSecondStepFragmentDirections.navigateToThirdStep()
            navController.navigate(directions)
        }
    }
}
