/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.moviereview

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.itlifelang.extrememovie.R
import com.itlifelang.extrememovie.databinding.FragmentMovieReviewBinding
import com.itlifelang.extrememovie.mobile.ui.BindingFragment
import com.itlifelang.extrememovie.mobile.widget.MovieReviewItemDecoration
import com.itlifelang.extrememovie.shared.extension.autoClear
import com.itlifelang.extrememovie.shared.extension.flowWithViewLifecycle
import com.itlifelang.extrememovie.shared.extension.themeColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieReviewFragment : BindingFragment<FragmentMovieReviewBinding, MovieReviewViewModel>() {

    private var bottomSheetBehavior: BottomSheetBehavior<LinearLayout> by autoClear()
    private var movieReviewPagingDataAdapter: MovieReviewPagingDataAdapter by autoClear()
    private val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            if (newState == STATE_HIDDEN) {
                binding.movieReviewList.scrollToPosition(0)
            }
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) = Unit
    }

    override val viewModel: MovieReviewViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_movie_review

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMovieReviews()
        setupEvents()
    }

    private fun setupMovieReviews() {
        // Bottom sheet behavior
        bottomSheetBehavior = BottomSheetBehavior.from(binding.movieReviewContainer)
        bottomSheetBehavior.state = STATE_HIDDEN
        bottomSheetBehavior.addBottomSheetCallback(bottomSheetCallback)
        // Movie review container background
        val corner = resources.getDimension(R.dimen.dp_8)
        val containerElevation = resources.getDimension(R.dimen.dp_8)
        val shapeModel = ShapeAppearanceModel.Builder()
            .setTopLeftCorner(CornerFamily.ROUNDED, corner)
            .setTopRightCorner(CornerFamily.ROUNDED, corner)
            .build()
        binding.movieReviewContainer.background = MaterialShapeDrawable(shapeModel).apply {
            fillColor = ColorStateList.valueOf(requireContext().themeColor(R.attr.colorSurface))
            elevation = containerElevation
            initializeElevationOverlay(requireContext())
        }
        // Movie reviews
        movieReviewPagingDataAdapter = MovieReviewPagingDataAdapter()
        binding.movieReviewList.addItemDecoration(MovieReviewItemDecoration(requireContext()))
        binding.movieReviewList.adapter = movieReviewPagingDataAdapter
        flowWithViewLifecycle(viewModel.movieReviews) {
            flowWithViewLifecycle(it ?: return@flowWithViewLifecycle) { pagingData ->
                movieReviewPagingDataAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupEvents() {
        binding.closeButton.setOnClickListener {
            bottomSheetBehavior.state = STATE_HIDDEN
        }
    }

    fun open() {
        bottomSheetBehavior.state = STATE_EXPANDED
    }

    fun loadMovieReviews(movieId: Int) {
        viewModel.setMovieId(movieId)
    }
}
