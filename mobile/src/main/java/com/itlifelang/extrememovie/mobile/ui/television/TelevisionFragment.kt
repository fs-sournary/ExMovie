/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.television

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.transition.TransitionManager
import com.google.android.material.transition.MaterialContainerTransform
import com.itlifelang.extrememovie.R
import com.itlifelang.extrememovie.databinding.FragmentTelevisionBinding
import com.itlifelang.extrememovie.mobile.ui.BindingFragment
import com.itlifelang.extrememovie.shared.extension.autoClear
import com.itlifelang.extrememovie.shared.extension.flowWithViewLifecycle
import com.itlifelang.extrememovie.shared.util.ConnectivityHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TelevisionFragment : BindingFragment<FragmentTelevisionBinding, TelevisionViewModel>() {
    private var airingTodayTelevisionListAdapter: CategoryTelevisionListAdapter by autoClear()
    private var onTheAirTelevisionListAdapter: CategoryTelevisionListAdapter by autoClear()
    private var popularTelevisionListAdapter: CategoryTelevisionListAdapter by autoClear()
    private var topRatedTelevisionListAdapter: CategoryTelevisionListAdapter by autoClear()
    private var televisionGenreListAdapter: TelevisionGenreListAdapter by autoClear()
    private var genreTelevisionListAdapter: GenreTelevisionListAdapter by autoClear()

    private var connectivityHelper: ConnectivityHelper by autoClear {
        connectivityHelper.unregisterNetworkCallback()
    }

    override val viewModel: TelevisionViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_television

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupConnectivity()
        setupGenreList()
        setupGenreTelevisionList()
        setupAiringTodayTelevisionList()
        setupOnTheAirTelevisionList()
        setupPopularTelevisionList()
        setupTopRatedTelevisionList()
        setupEvents()
    }

    private fun setupConnectivity() {
        connectivityHelper = ConnectivityHelper(
            context = context ?: return,
            onNetworkAvailable = { viewModel.setConnection(true) }
        )
        connectivityHelper.registerNetworkCallback()
    }

    private fun setupGenreList() {
        televisionGenreListAdapter = TelevisionGenreListAdapter(
            lifecycleOwner = viewLifecycleOwner,
            selectedGenre = viewModel.genre,
            click = { _, genre ->
                viewModel.setGenre(genre)
                hideGenreList()
            }
        )
        binding.genreList.adapter = televisionGenreListAdapter
        flowWithViewLifecycle(viewModel.genres) { genres ->
            genres?.let { televisionGenreListAdapter.submitList(it) }
        }
    }

    private fun setupGenreTelevisionList() {
        genreTelevisionListAdapter = GenreTelevisionListAdapter { _, television ->
        }
        binding.genreTelevisionList.adapter = genreTelevisionListAdapter
        flowWithViewLifecycle(viewModel.genreTelevisions) { televisions ->
            televisions?.let { genreTelevisionListAdapter.submitList(it) }
        }
        flowWithViewLifecycle(viewModel.genreTelevisionLoading) {
            if (it) binding.genreTelevisionList.smoothScrollToPosition(0)
        }
    }

    private fun setupAiringTodayTelevisionList() {
        airingTodayTelevisionListAdapter = CategoryTelevisionListAdapter { _, television ->
        }
        binding.airingTodayTelevisionList.adapter = airingTodayTelevisionListAdapter
        flowWithViewLifecycle(viewModel.airingTodayTelevisions) { televisions ->
            televisions?.let { airingTodayTelevisionListAdapter.submitList(it) }
        }
    }

    private fun setupOnTheAirTelevisionList() {
        onTheAirTelevisionListAdapter = CategoryTelevisionListAdapter { _, television ->
        }
        binding.onTheAirTelevisionList.adapter = onTheAirTelevisionListAdapter
        flowWithViewLifecycle(viewModel.onTheAirTelevisions) { televisions ->
            televisions?.let { onTheAirTelevisionListAdapter.submitList(it) }
        }
    }

    private fun setupPopularTelevisionList() {
        popularTelevisionListAdapter = CategoryTelevisionListAdapter { _, television ->
        }
        binding.popularTelevisionList.adapter = popularTelevisionListAdapter
        flowWithViewLifecycle(viewModel.popularTelevisions) { television ->
            television?.let { popularTelevisionListAdapter.submitList(it) }
        }
    }

    private fun setupTopRatedTelevisionList() {
        topRatedTelevisionListAdapter = CategoryTelevisionListAdapter { _, television ->
        }
        binding.topRatedTelevisionList.adapter = topRatedTelevisionListAdapter
        flowWithViewLifecycle(viewModel.topRatedTelevisions) { television ->
            television?.let { topRatedTelevisionListAdapter.submitList(it) }
        }
    }

    private fun setupEvents() {
        binding.genreScrim.setOnClickListener {
            hideGenreList()
        }
        binding.genreTransitionText.setOnClickListener {
            showGenreList()
        }
    }

    private fun hideGenreList() {
        binding.genreScrim.isVisible = false
        val transform = MaterialContainerTransform().apply {
            startView = binding.genreContainer
            endView = binding.genreTransitionText
            scrimColor = Color.TRANSPARENT
            endElevation = resources.getDimension(R.dimen.dp_3)
            addTarget(binding.genreTransitionText)
        }
        TransitionManager.beginDelayedTransition(binding.rootContainer, transform)
        binding.genreContainer.isVisible = false
    }

    private fun showGenreList() {
        binding.genreScrim.isVisible = true
        val transform = MaterialContainerTransform().apply {
            startView = binding.genreTransitionText
            endView = binding.genreContainer
            scrimColor = Color.TRANSPARENT
            endElevation = resources.getDimension(R.dimen.dp_3)
            addTarget(binding.genreContainer)
        }
        TransitionManager.beginDelayedTransition(binding.rootContainer, transform)
        binding.genreContainer.isVisible = true
    }
}
