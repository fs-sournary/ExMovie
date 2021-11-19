/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.moviesearch

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.itlifelang.extrememovie.mobile.data.HistorySearch
import com.itlifelang.extrememovie.mobile.data.HistorySearch.SearchMovie
import com.itlifelang.extrememovie.mobile.data.HistorySearch.Separator
import com.itlifelang.extrememovie.mobile.data.Movie
import com.itlifelang.extrememovie.mobile.mapper.mapToMobile
import com.itlifelang.extrememovie.mobile.mapper.mapToModel
import com.itlifelang.extrememovie.shared.di.ApplicationScope
import com.itlifelang.extrememovie.shared.usecase.search.GetSearchDatabaseMoviePagingUseCase
import com.itlifelang.extrememovie.shared.usecase.search.GetSearchMoviePagingUseCase
import com.itlifelang.extrememovie.shared.usecase.search.SaveHistorySearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.Lazily
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val getSearchMoviePagingUseCase: GetSearchMoviePagingUseCase,
    private val saveHistorySearchMovieUseCase: SaveHistorySearchMovieUseCase,
    getSearchDatabaseMoviePagingUseCase: GetSearchDatabaseMoviePagingUseCase,
    @ApplicationScope private val applicationScope: CoroutineScope,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiAction = MutableSharedFlow<UiAction>()

    private val searchAction: Flow<UiAction.Search> = _uiAction
        .filterIsInstance<UiAction.Search>()
        .distinctUntilChanged()
        .onStart {
            val query = savedStateHandle.get(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
            emit(UiAction.Search(query))
        }

    private val scrollAction: SharedFlow<UiAction.Scroll> = _uiAction
        .filterIsInstance<UiAction.Scroll>()
        .distinctUntilChanged()
        // This is shared to keep the flow "hot" while caching the last query scrolled,
        // otherwise each flatMapLatest invocation would lose the last query scrolled.
        .shareIn(viewModelScope, Lazily, 1)
        .onSubscription {
            val lastScrolledQuery = savedStateHandle.get(LAST_SCROLLED_QUERY) ?: DEFAULT_QUERY
            emit(UiAction.Scroll(lastScrolledQuery))
        }

    val state: StateFlow<UiState> = searchAction.flatMapLatest { search ->
        combine(scrollAction, searchMovie(query = search.query), ::Pair)
            .filter { search.query != DEFAULT_QUERY }
            .distinctUntilChangedBy { it.second }
            .map { (scroll, pagingData) ->
                Timber.d("Search query: ${search.query}")
                UiState(
                    searchQuery = search.query,
                    pagingData = pagingData,
                    lastScrolledQuery = scroll.currentQuery,
                    // If the search query matches the scroll query, the user has scrolled
                    hasNotScrolledForCurrentSearch = search.query != scroll.currentQuery
                )
            }
    }.stateIn(viewModelScope, Lazily, UiState())

    val accept: (UiAction) -> Unit = { action ->
        viewModelScope.launch { _uiAction.emit(action) }
    }

    private val _historyTimeFormat = MutableSharedFlow<String>()

    val historySearchMovie: Flow<PagingData<HistorySearch>> = getSearchDatabaseMoviePagingUseCase()
        .combine(_historyTimeFormat, ::Pair)
        .map { (pagingData, format) ->
            pagingData.map {
                val insertedTime =
                    SimpleDateFormat(format, Locale.getDefault()).format(Date(it.insertTime))
                SearchMovie(it.movie.mapToMobile(), insertedTime)
            }
        }
        .map {
            it.insertSeparators { before, after -> getHistorySearchMovieSeparators(before, after) }
        }
        .cachedIn(viewModelScope)

    private fun searchMovie(query: String): Flow<PagingData<Movie>> =
        getSearchMoviePagingUseCase(query)
            .map { pagingData ->
                pagingData.map { movie -> movie.mapToMobile() }
            }
            .cachedIn(viewModelScope)

    private fun getHistorySearchMovieSeparators(
        before: SearchMovie?,
        after: SearchMovie?
    ): Separator? = when {
        after == null -> null // We're at the end of the list
        before == null -> Separator(after.insertTime) // We are at the beginning of the list
        else -> if (after.insertTime != before.insertTime) Separator(after.insertTime) else null
    }

    /**
     * This method save a movie into the data. We also need to save its saved time for orders later.
     * @param movie The movie is saved.
     */
    fun insertMovieToDatabase(movie: Movie) {
        val params = Pair(movie.mapToModel(), System.currentTimeMillis())
        applicationScope.launch { saveHistorySearchMovieUseCase(params) }
    }

    override fun onCleared() {
        savedStateHandle[LAST_SEARCH_QUERY] = state.value.searchQuery
        savedStateHandle[LAST_SCROLLED_QUERY] = state.value.lastScrolledQuery
        super.onCleared()
    }
}

private const val DEFAULT_QUERY = ""
private const val LAST_SEARCH_QUERY = "last_search_query"
private const val LAST_SCROLLED_QUERY = "last_scrolled_query"

sealed class UiAction {

    data class Search(val query: String) : UiAction()

    data class Scroll(val currentQuery: String) : UiAction()
}

data class UiState(
    val searchQuery: String = DEFAULT_QUERY,
    val lastScrolledQuery: String = DEFAULT_QUERY,
    val hasNotScrolledForCurrentSearch: Boolean = false,
    val pagingData: PagingData<Movie> = PagingData.empty()
)
