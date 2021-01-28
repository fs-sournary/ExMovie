package com.itlifelang.extrememovie.ui.movie

import androidx.fragment.app.viewModels
import com.itlifelang.extrememovie.R
import com.itlifelang.extrememovie.databinding.FragmentMovieBinding
import com.itlifelang.extrememovie.ui.BindingFragment

class MovieFragment : BindingFragment<FragmentMovieBinding, MovieViewModel>() {

    override val viewModel: MovieViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_movie
}
