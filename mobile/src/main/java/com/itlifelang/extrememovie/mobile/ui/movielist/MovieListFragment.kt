/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.movielist

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialSharedAxis
import com.itlifelang.extrememovie.R
import com.itlifelang.extrememovie.databinding.FragmentMovieListBinding
import com.itlifelang.extrememovie.mobile.data.Genre
import com.itlifelang.extrememovie.mobile.ui.BindingFragment
import com.itlifelang.extrememovie.shared.extension.autoClear
import com.itlifelang.extrememovie.shared.extension.flowWithViewLifecycle
import com.itlifelang.extrememovie.shared.extension.startPostponedTransitionOnPreDraw
import com.itlifelang.extrememovie.shared.extension.themeColor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MovieListFragment : BindingFragment<FragmentMovieListBinding, MovieListViewModel>() {

    private val safeArgs: MovieListFragmentArgs by navArgs()
    private val genreArgs: Genre by lazy { safeArgs.genre }

    private var movieListPagingDataAdapter: MovieListPagingDataAdapter by autoClear()

    override val viewModel: MovieListViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_movie_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            // Scope the transition to a View in the hierarchy
            drawingViewId = R.id.nav_host_fragment
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startPostponedTransitionOnPreDraw(view)
        viewModel.setGenre(genreArgs)
        setupMovieList()
        setupEvents()
    }

    private fun setupMovieList() {
        movieListPagingDataAdapter = MovieListPagingDataAdapter { view, movie ->
            exitTransition = MaterialElevationScale(false).apply { duration = 300.toLong() }
            reenterTransition = MaterialElevationScale(true).apply { duration = 300.toLong() }
            val movieDetailTransitionName = getString(R.string.movie_detail_transition_name)
            val extras = FragmentNavigatorExtras(view to movieDetailTransitionName)
            val directions = MovieListFragmentDirections.navigateToMovieDetail(movie)
            navController.navigate(directions, extras)
        }
        binding.movieList.adapter = movieListPagingDataAdapter
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            movieListPagingDataAdapter.loadStateFlow.collectLatest {
                binding.progress.isVisible = it.refresh is LoadState.Loading
                binding.retryButton.isVisible = it.refresh is LoadState.Error
                binding.movieList.isVisible = it.refresh is LoadState.NotLoading
            }
        }
        flowWithViewLifecycle(viewModel.movies) {
            flowWithViewLifecycle(it ?: return@flowWithViewLifecycle) { pagingData ->
                movieListPagingDataAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupEvents() {
        binding.toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.search_dest -> {
                    navigateToSearch()
                    true
                }
                else -> false
            }
        }
        binding.retryButton.setOnClickListener {
            movieListPagingDataAdapter.refresh()
        }
    }

    private fun navigateToSearch() {
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
        val directions = MovieListFragmentDirections.navigateToSearch()
        navController.navigate(directions)
    }
}
