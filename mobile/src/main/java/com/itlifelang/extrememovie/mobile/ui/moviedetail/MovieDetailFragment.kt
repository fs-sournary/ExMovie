/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.moviedetail

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.chip.Chip
import com.google.android.material.transition.MaterialElevationScale
import com.itlifelang.extrememovie.R
import com.itlifelang.extrememovie.databinding.FragmentMovieDetailBinding
import com.itlifelang.extrememovie.mobile.ui.BindingFragment
import com.itlifelang.extrememovie.mobile.ui.moviereview.MovieReviewFragment
import com.itlifelang.extrememovie.model.Genre
import com.itlifelang.extrememovie.model.Video
import com.itlifelang.extrememovie.shared.extension.autoClear
import com.itlifelang.extrememovie.shared.extension.flowWithViewLifecycle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.abs

@AndroidEntryPoint
class MovieDetailFragment : BindingFragment<FragmentMovieDetailBinding, MovieDetailViewModel>() {
    private val safeArgs: MovieDetailFragmentArgs by navArgs()

    private var similarMovieListAdapter: SimilarMovieListAdapter by autoClear()
    private var castListAdapter: CastListAdapter by autoClear()
    private var crewListAdapter: CrewListAdapter by autoClear()
    private var videoQualitySpinner: ArrayAdapter<String> by autoClear()
    private lateinit var movieReviewFragment: MovieReviewFragment

    @Inject
    lateinit var viewModelFactory: MovieDetailViewModel.Factory

    override val viewModel: MovieDetailViewModel by viewModels {
        MovieDetailViewModel.provideFactory(viewModelFactory, safeArgs.movieId)
    }

    override val layoutId: Int = R.layout.fragment_movie_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialElevationScale(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSimilarMovieList()
        setupMovieCreditList()
        setupVideoQualitySpinner()
        setupReviewList()
        setupEvent()
    }

    private fun setupSimilarMovieList() {
        similarMovieListAdapter = SimilarMovieListAdapter { view, movie ->
            val movieDetailTransitionName = getString(R.string.movie_detail_transition_name)
            val extras = FragmentNavigatorExtras(view to movieDetailTransitionName)
            val directions = MovieDetailFragmentDirections.navigateToMovieDetail(movie.id ?: 0)
            navController.navigate(directions, extras)
        }
        binding.similarMovieList.adapter = similarMovieListAdapter
        flowWithViewLifecycle(similarMovieListAdapter.loadStateFlow) {
            binding.similarMovieTitleText.isVisible = it.refresh is LoadState.NotLoading &&
                    similarMovieListAdapter.itemCount != 0
        }
        flowWithViewLifecycle(viewModel.similarMovies) {
            similarMovieListAdapter.submitData(it)
        }
    }

    private fun setupMovieCreditList() {
        castListAdapter = CastListAdapter { view, cast ->
            // Todo: Cast item click
        }
        crewListAdapter = CrewListAdapter { view, crew ->
            // Todo: Crew item click
        }
        val adapter = ConcatAdapter(castListAdapter, crewListAdapter)
        binding.creditMovieList.adapter = adapter
        flowWithViewLifecycle(viewModel.cast) { casts ->
            casts?.let { castListAdapter.submitList(it) }
        }
        flowWithViewLifecycle(viewModel.crew) { crews ->
            crews?.let { crewListAdapter.submitList(it) }
        }
    }

    private fun setupVideoQualitySpinner() {
        videoQualitySpinner =
            ArrayAdapter(requireContext(), R.layout.item_video_quality_spinner, R.id.text)
        videoQualitySpinner.setDropDownViewResource(R.layout.item_dropdown_video_quality_spinner)
        binding.videoQualitySpinner.adapter = videoQualitySpinner
        flowWithViewLifecycle(viewModel.videos) { videos ->
            videos?.let { handleVideos(it) }
        }
        flowWithViewLifecycle(viewModel.movieGenres) { genres ->
            genres?.let { setupGenres(it) }
        }
        flowWithViewLifecycle(viewModel.spinnerPosition) {
            binding.videoQualitySpinner.setSelection(it)
        }
    }

    private fun setupReviewList() {
        movieReviewFragment = childFragmentManager.findFragmentById(
            R.id.movie_review_fragment
        ) as MovieReviewFragment
    }

    private fun handleVideos(videos: List<Video>?) {
        viewModel.setSpinnerPosition(0)
        if (videos.isNullOrEmpty()) return
        val text = videos.map { it.size?.toString() ?: "Unknown" }
        videoQualitySpinner.addAll(text)
    }

    private fun setupEvent() {
        // Appbar event
        val appbarOffsetListener = AppBarLayout.OnOffsetChangedListener { appBar, verticalOffset ->
            val fraction = abs(verticalOffset).toFloat() / appBar.totalScrollRange
            val expandedAlpha = 1 - fraction
            binding.collapsingToolbar.title = when (expandedAlpha) {
                0f -> viewModel.movieDetail.value?.title
                else -> null
            }
            binding.backDropImage.alpha = expandedAlpha
            binding.titleText.alpha = expandedAlpha
        }
        binding.appbar.addOnOffsetChangedListener(appbarOffsetListener)
        // Toolbar event
        binding.toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.cast_action -> {
                }
                R.id.share_action -> shareMovie()
            }
            true
        }
        // Spinner event
        val videoQualityItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, v: View?, p: Int, id: Long) {
                viewModel.setSpinnerPosition(p)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        binding.videoQualitySpinner.onItemSelectedListener = videoQualityItemSelectedListener
        // Click events
        binding.addLibraryButton.setOnClickListener {
            viewModel.saveDeleteLibraryMovie()
        }
        binding.reviewButton.setOnClickListener {
            movieReviewFragment.loadMovieReviews(safeArgs.movieId)
            movieReviewFragment.open()
        }
    }

    private fun shareMovie() {
//        val title = safeArgs.movie.title ?: return
//        val intent = Intent().apply {
//            action = Intent.ACTION_SEND
//            putExtra(Intent.EXTRA_TEXT, title)
//            putExtra(Intent.EXTRA_TITLE, "Share movie")
//            type = "text/plain"
//        }
//        val sharedIntent = Intent.createChooser(intent, null)
//        startActivity(sharedIntent)
    }

    private fun setupGenres(genres: List<Genre>?) {
        if (genres.isNullOrEmpty()) return
        binding.genreChipGroup.removeAllViews()
        genres.forEach {
            val chip = layoutInflater.inflate(
                R.layout.layout_movie_genre, binding.genreChipGroup, false
            ) as Chip
            chip.text = it.name
            chip.transitionName = "movie_genre_${it.id}"
            chip.setOnClickListener { view ->
                val movieDetailTransitionName = getString(R.string.movie_list_transition_name)
                val extras = FragmentNavigatorExtras(view to movieDetailTransitionName)
                val directions = MovieDetailFragmentDirections.navigateToMovieList(
                    name = it.name ?: "",
                    id = it.id ?: 0
                )
                navController.navigate(directions, extras)
            }
            binding.genreChipGroup.addView(chip)
        }
    }
}
