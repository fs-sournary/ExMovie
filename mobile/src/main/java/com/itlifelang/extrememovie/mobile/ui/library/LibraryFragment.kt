/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.library

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialSharedAxis
import com.itlifelang.extrememovie.R
import com.itlifelang.extrememovie.databinding.FragmentLibraryBinding
import com.itlifelang.extrememovie.mobile.ui.BindingFragment
import com.itlifelang.extrememovie.shared.extension.autoClear
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LibraryFragment : BindingFragment<FragmentLibraryBinding, LibraryViewModel>() {

    private var libraryMoviePagingDataAdapter: LibraryMoviePagingDataAdapter by autoClear()

    override val viewModel: LibraryViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_library

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLibraryMovieList()
        setupEvents()
    }

    private fun setupLibraryMovieList() {
        libraryMoviePagingDataAdapter = LibraryMoviePagingDataAdapter { _, movie ->
            exitTransition = MaterialElevationScale(false)
            reenterTransition = MaterialElevationScale(true)
            val directions = LibraryFragmentDirections.navigateToMovieDetail(movie)
            navController.navigate(directions)
        }
        binding.movieList.adapter = libraryMoviePagingDataAdapter
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            libraryMoviePagingDataAdapter.loadStateFlow.collectLatest {
                binding.movieList.isVisible = it.refresh is LoadState.NotLoading
                binding.emptyMovieText.isVisible = it.refresh is LoadState.NotLoading &&
                    libraryMoviePagingDataAdapter.itemCount == 0
            }
        }
        viewModel.libraryMovies.asLiveData().observe(viewLifecycleOwner) {
            libraryMoviePagingDataAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun setupEvents() {
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.search_dest -> {
                    navigateToSearch()
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateToSearch() {
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
        val directions = LibraryFragmentDirections.navigateToSearch()
        navController.navigate(directions)
    }
}
