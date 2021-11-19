/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.moviesearch

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.itlifelang.extrememovie.R
import com.itlifelang.extrememovie.databinding.FragmentMovieSearchBinding
import com.itlifelang.extrememovie.mobile.data.Movie
import com.itlifelang.extrememovie.mobile.ui.BindingFragment
import com.itlifelang.extrememovie.shared.extension.autoClear
import com.itlifelang.extrememovie.shared.extension.dismissKeyBoard
import com.itlifelang.extrememovie.shared.extension.flowWithViewLifecycle
import com.itlifelang.extrememovie.shared.extension.showKeyBoard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
import timber.log.Timber

@AndroidEntryPoint
class MovieSearchFragment : BindingFragment<FragmentMovieSearchBinding, MovieSearchViewModel>() {

    private val historyTimeFormat: String by lazy {
        getString(R.string.search_movie_saved_movie_time_format)
    }

    private val recognizerLResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        val result = it.data
            ?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            ?.getOrNull(0)
            ?: return@registerForActivityResult
        binding.searchEditText.setText(result)
        binding.searchEditText.setSelection(binding.searchEditText.length())
    }

    private var searchTextChangeListener: TextWatcher by autoClear {
        binding.searchEditText.removeTextChangedListener(searchTextChangeListener)
    }

    private var searchMoviePagingDataAdapter: SearchMoviePagingDataAdapter by autoClear()
    private var historySearchMoviePagingDataAdapter: HistorySearchMoviePagingDataAdapter by autoClear()

    override val viewModel: MovieSearchViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_movie_search

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearch()
        setupSearchMovieList()
        setupHistorySearchMovieList()
    }

    private fun setupSearch() {
        binding.toolbar.setNavigationOnClickListener {
            dismissKeyBoard(binding.searchEditText)
            navController.popBackStack()
        }
        searchTextChangeListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.historySearchMovieList.isVisible = s.isNullOrEmpty()
                binding.searchMovieList.isVisible = s.isNullOrEmpty().not()
                s?.trim()?.let {
                    // This makes sure when the user backs to movie search. The search list doesn't
                    // scroll to top.
                    if (viewModel.state.value.searchQuery != it.toString()) {
                        binding.searchMovieList.scrollToPosition(0)
                    }
                    viewModel.accept(UiAction.Search(it.toString()))
                }
            }

            override fun afterTextChanged(s: Editable?) = Unit
        }
        binding.searchEditText.addTextChangedListener(searchTextChangeListener)
        binding.searchEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                showKeyBoard(binding.searchEditText)
            }
        }
        binding.retryButton.setOnClickListener {
            dismissKeyBoard(binding.searchEditText)
            searchMoviePagingDataAdapter.refresh()
        }
        binding.micImageButton.setOnClickListener {
            dismissKeyBoard(binding.searchEditText)
            openRecognizeSpeech()
        }
    }

    private fun setupSearchMovieList() {
        searchMoviePagingDataAdapter = SearchMoviePagingDataAdapter { view, movie ->
            dismissKeyBoard(binding.searchEditText)
            viewModel.insertMovieToDatabase(movie)
            navigateToMovieDetail(view, movie)
        }
        binding.searchMovieList.adapter = searchMoviePagingDataAdapter.withLoadStateHeaderAndFooter(
            header = SearchMovieLoadStateAdapter { searchMoviePagingDataAdapter.retry() },
            footer = SearchMovieLoadStateAdapter { searchMoviePagingDataAdapter.retry() }
        )
        binding.searchMovieList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy != 0) viewModel.accept(UiAction.Scroll(viewModel.state.value.searchQuery))
            }
        })
        val notLoading = searchMoviePagingDataAdapter.loadStateFlow
            .distinctUntilChangedBy { it.refresh }
            .map { it.refresh is LoadState.NotLoading }
        val hasNotScrolledForCurrentSearch = viewModel.state
            .map { it.hasNotScrolledForCurrentSearch }
            .distinctUntilChanged()
        val shouldScrollToTop = combine(
            notLoading, hasNotScrolledForCurrentSearch, Boolean::and
        ).distinctUntilChanged()
        val pagingData = viewModel.state.map { it.pagingData }.distinctUntilChanged()
        flowWithViewLifecycle(
            combine(shouldScrollToTop, pagingData, ::Pair).distinctUntilChangedBy { it.second }
        ) { (shouldScroll, pagingData) ->
            searchMoviePagingDataAdapter.submitData(pagingData)
            if (shouldScroll) binding.searchMovieList.scrollToPosition(0)
        }
        flowWithViewLifecycle(searchMoviePagingDataAdapter.loadStateFlow) {
            val hasSearchQuery = binding.searchEditText.text.toString().isEmpty().not()
            val isEmptyList = it.refresh is LoadState.NotLoading &&
                    searchMoviePagingDataAdapter.itemCount == 0 &&
                    hasSearchQuery
            binding.emptySearchMovieText.isVisible = isEmptyList
            binding.searchMovieList.isVisible = isEmptyList.not()
            binding.progress.isVisible = it.source.refresh is LoadState.Loading && hasSearchQuery
            binding.retryButton.isVisible = it.source.refresh is LoadState.Error && hasSearchQuery
        }
    }

    private fun setupHistorySearchMovieList() {
        historySearchMoviePagingDataAdapter = HistorySearchMoviePagingDataAdapter { view, movie ->
            dismissKeyBoard(binding.searchEditText)
            navigateToMovieDetail(view, movie)
        }
        binding.historySearchMovieList.adapter = historySearchMoviePagingDataAdapter
        flowWithViewLifecycle(viewModel.historySearchMovie) {
            historySearchMoviePagingDataAdapter.submitData(it)
        }
        flowWithViewLifecycle(historySearchMoviePagingDataAdapter.loadStateFlow) {
            binding.historySearchMovieList.isVisible = it.refresh is LoadState.NotLoading &&
                    binding.searchEditText.text.isNullOrEmpty()
        }
    }

    private fun navigateToMovieDetail(view: View, movie: Movie) {
        val movieDetailTransitionName = getString(R.string.movie_detail_transition_name)
        val extras = FragmentNavigatorExtras(view to movieDetailTransitionName)
        val directions = MovieSearchFragmentDirections.navigateToMovieDetail(movie)
        navController.navigate(directions, extras)
    }

    private fun openRecognizeSpeech() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            recognizerLResult.launch(intent)
        }
    }
}
