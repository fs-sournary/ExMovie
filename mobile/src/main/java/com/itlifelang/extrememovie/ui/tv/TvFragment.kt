package com.itlifelang.extrememovie.ui.tv

import androidx.fragment.app.viewModels
import com.itlifelang.extrememovie.R
import com.itlifelang.extrememovie.databinding.FragmentTvBinding
import com.itlifelang.extrememovie.ui.BindingFragment

class TvFragment : BindingFragment<FragmentTvBinding, TvViewModel>() {

    override val viewModel: TvViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_tv
}
