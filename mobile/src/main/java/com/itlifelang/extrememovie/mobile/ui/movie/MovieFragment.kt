/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialSharedAxis
import com.itlifelang.extrememovie.R
import com.itlifelang.extrememovie.databinding.FragmentMovieBinding
import com.itlifelang.extrememovie.mobile.ui.BindingFragment
import com.itlifelang.extrememovie.mobile.ui.MainActivity
import com.itlifelang.extrememovie.mobile.ui.movie.GenreName.NowPLaying
import com.itlifelang.extrememovie.mobile.ui.movie.GenreName.Popular
import com.itlifelang.extrememovie.mobile.ui.movie.GenreName.TopRated
import com.itlifelang.extrememovie.mobile.ui.movie.GenreName.UpComing
import com.itlifelang.extrememovie.model.Genre
import com.itlifelang.extrememovie.model.Movie
import com.itlifelang.extrememovie.shared.extension.autoClear
import com.itlifelang.extrememovie.shared.extension.flowWithViewLifecycle
import com.itlifelang.extrememovie.shared.util.ConnectivityHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : BindingFragment<FragmentMovieBinding, MovieViewModel>() {
    private var introMovieListAdapter: IntroMovieListAdapter by autoClear()
    private var nowPlayingListAdapter: CategoryMovieListAdapter by autoClear()
    private var topRatedListAdapter: CategoryMovieListAdapter by autoClear()
    private var popularListAdapter: CategoryMovieListAdapter by autoClear()
    private var upComingListAdapter: CategoryMovieListAdapter by autoClear()
    private var genreMovieListAdapter: GenreMovieListAdapter by autoClear()

    private var connectivityHelper: ConnectivityHelper by autoClear {
        connectivityHelper.unregisterNetworkCallback()
    }

    override val viewModel: MovieViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_movie

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupConnectivity()
        setupIntroMovieList()
        setupGenreList()
        setupGenreMovieList()
        setupNowPlayingMovieList()
        setupTopRatedMovieList()
        setupPopularMovieList()
        setupUpComingMovieList()
        setupEvents()
    }

    private fun setupConnectivity() {
        connectivityHelper = ConnectivityHelper(
            context = context ?: return,
            onNetworkAvailable = { viewModel.setConnection(true) },
            onNetworkLost = {}
        )
        connectivityHelper.registerNetworkCallback()
    }

    private fun setupIntroMovieList() {
        introMovieListAdapter = IntroMovieListAdapter(
            firstIntroClick = {
                // Todo: first item clicks
            },
            secondIntroClick = {
                // Todo: Second item clicks
            }
        )
        binding.introMovieList.adapter = introMovieListAdapter
        introMovieListAdapter.submitList(listOf(Any(), Any()))
    }

    private fun setupGenreList() {
        flowWithViewLifecycle(viewModel.genres) {
            binding.genreChipGroup.setGenres(
                inflater = layoutInflater,
                items = it,
                click = { genre -> viewModel.setGenre(genre) }
            )
        }
    }

    private fun setupGenreMovieList() {
        genreMovieListAdapter = GenreMovieListAdapter(
            click = { _, movie -> navigateToMovieDetail(movie) },
            viewMoreAction = { viewModel.genre.value?.apply { navigateToMovieList(this) } }
        )
        binding.genreMovieList.adapter = genreMovieListAdapter
        flowWithViewLifecycle(viewModel.genreMovies) {
            it ?: return@flowWithViewLifecycle
            genreMovieListAdapter.hasExtraRow = true
            genreMovieListAdapter.submitList(it) {
                viewModel.scrollGenreMovie { binding.genreMovieList.scrollToPosition(0) }
            }
        }
    }

    private fun setupNowPlayingMovieList() {
        nowPlayingListAdapter = CategoryMovieListAdapter(
            prefixTransitionName = getString(R.string.movie_now_playing_transition_name),
            itemClick = { _, movie -> navigateToMovieDetail(movie) },
            viewMore = { navigateToMovieList(Genre(name = NowPLaying.name)) }
        )
        binding.nowPlayingMovieList.adapter = nowPlayingListAdapter
        flowWithViewLifecycle(viewModel.nowPlayingMovies) {
            it ?: return@flowWithViewLifecycle
            nowPlayingListAdapter.hasExtraRow = true
            nowPlayingListAdapter.submitList(it)
        }
    }

    private fun setupTopRatedMovieList() {
        topRatedListAdapter = CategoryMovieListAdapter(
            prefixTransitionName = getString(R.string.movie_top_rated_transition_name),
            itemClick = { _, movie -> navigateToMovieDetail(movie) },
            viewMore = { navigateToMovieList(Genre(name = TopRated.name)) }
        )
        binding.topRatedMovieList.adapter = topRatedListAdapter
        flowWithViewLifecycle(viewModel.topRatedMovies) {
            it ?: return@flowWithViewLifecycle
            topRatedListAdapter.hasExtraRow = true
            topRatedListAdapter.submitList(it)
        }
    }

    private fun setupPopularMovieList() {
        popularListAdapter = CategoryMovieListAdapter(
            prefixTransitionName = getString(R.string.movie_popular_transition_name),
            itemClick = { _, movie -> navigateToMovieDetail(movie) },
            viewMore = { navigateToMovieList(Genre(name = Popular.name)) }
        )
        binding.popularMovieList.adapter = popularListAdapter
        flowWithViewLifecycle(viewModel.popularMovies) {
            it ?: return@flowWithViewLifecycle
            popularListAdapter.hasExtraRow = true
            popularListAdapter.submitList(it)
        }
    }

    private fun setupUpComingMovieList() {
        upComingListAdapter = CategoryMovieListAdapter(
            prefixTransitionName = getString(R.string.movie_up_coming_transition_name),
            itemClick = { _, movie -> navigateToMovieDetail(movie) },
            viewMore = { navigateToMovieList(Genre(name = UpComing.name)) }
        )
        binding.upComingMovieList.adapter = upComingListAdapter
        flowWithViewLifecycle(viewModel.upComingMovies) {
            it ?: return@flowWithViewLifecycle
            upComingListAdapter.hasExtraRow = true
            upComingListAdapter.submitList(it)
        }
    }

    private fun navigateToMovieDetail(movie: Movie) {
        exitTransition = MaterialElevationScale(false)
        reenterTransition = MaterialElevationScale(true)
        val directions = MovieFragmentDirections.navigateToMovieDetail(movie.id ?: 0)
        navController.navigate(directions)
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
        binding.settingAction.setOnClickListener {
            (requireActivity() as? MainActivity)?.navigateToSetting()
        }
    }

    private fun navigateToSearch() {
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
        val directions = MovieFragmentDirections.navigateToSearch()
        navController.navigate(directions)
    }

    private fun navigateToMovieList(genre: Genre) {
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
        val direction = MovieFragmentDirections.navigateToMovieList(genre.name ?: "", genre.id ?: 0)
        navController.navigate(direction)
    }
}
