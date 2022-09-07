/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.crew

import androidx.fragment.app.viewModels
import com.itlifelang.extrememovie.R
import com.itlifelang.extrememovie.databinding.FragmentCrewBinding
import com.itlifelang.extrememovie.mobile.ui.BindingFragment

class CrewFragment : BindingFragment<FragmentCrewBinding, CrewViewModel>() {
    override val viewModel: CrewViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_crew
}
