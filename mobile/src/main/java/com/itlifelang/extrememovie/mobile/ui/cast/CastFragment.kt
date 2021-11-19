/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.cast

import androidx.fragment.app.viewModels
import com.itlifelang.extrememovie.R
import com.itlifelang.extrememovie.databinding.FragmentCastBinding
import com.itlifelang.extrememovie.mobile.ui.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CastFragment : BindingFragment<FragmentCastBinding, CastViewModel>() {

    override val viewModel: CastViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_cast
}
