/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.itlifelang.extrememovie.model.Cast
import com.itlifelang.extrememovie.model.Credit
import com.itlifelang.extrememovie.model.Crew
import com.itlifelang.extrememovie.model.Genre
import com.itlifelang.extrememovie.model.Movie
import com.itlifelang.extrememovie.model.MovieDetail
import com.itlifelang.extrememovie.model.Video
import com.itlifelang.extrememovie.shared.result.Result
import com.itlifelang.extrememovie.shared.result.data
import com.itlifelang.extrememovie.shared.result.successOr
import com.itlifelang.extrememovie.shared.usecase.library.DeleteLibraryMovieUseCase
import com.itlifelang.extrememovie.shared.usecase.library.GetLibraryMovieUseCase
import com.itlifelang.extrememovie.shared.usecase.library.SaveLibraryMovieUseCase
import com.itlifelang.extrememovie.shared.usecase.movie.GetGenreListUseCase
import com.itlifelang.extrememovie.shared.usecase.movie.GetMovieCreditListUseCase
import com.itlifelang.extrememovie.shared.usecase.movie.GetMovieDetailUseCase
import com.itlifelang.extrememovie.shared.usecase.movie.GetMovieVideoUseCase
import com.itlifelang.extrememovie.shared.usecase.movie.GetSimilarMoviePagingUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.Lazily
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MovieDetailViewModel @AssistedInject constructor(
    @Assisted val movieId: Int,
    getMovieDetailUseCase: GetMovieDetailUseCase,
    getGenreListUseCase: GetGenreListUseCase,
    getSimilarMoviePagingUseCase: GetSimilarMoviePagingUseCase,
    getMovieCreditListUseCase: GetMovieCreditListUseCase,
    getMovieVideoUseCase: GetMovieVideoUseCase,
    private val getLibraryMovieUseCase: GetLibraryMovieUseCase,
    private val saveLibraryMovieUseCase: SaveLibraryMovieUseCase,
    private val deleteLibraryMovieUseCase: DeleteLibraryMovieUseCase
) : ViewModel() {
    private val movieDetailResult: StateFlow<Result<MovieDetail>> =
        getMovieDetailUseCase(movieId).stateIn(viewModelScope, Lazily, Result.Loading)

    val movieDetail: StateFlow<MovieDetail?> = movieDetailResult.map {
        it.data
    }.stateIn(viewModelScope, Lazily, null)

    // Movies that are similar to the movie.
    val similarMovies: Flow<PagingData<Movie>> = getSimilarMoviePagingUseCase(movieId)
        .cachedIn(viewModelScope)

    // Credits that contains all casts and crews belong to the movie.
    private val creditResult: StateFlow<Result<Credit>> =
        getMovieCreditListUseCase(movieId).stateIn(viewModelScope, Lazily, Result.Loading)

    val cast: StateFlow<List<Cast>?> = creditResult.map {
        it.data?.cast
    }.stateIn(viewModelScope, Lazily, null)

    val crew: StateFlow<List<Crew>?> = creditResult.map {
        it.data?.crew
    }.stateIn(viewModelScope, Lazily, null)

    // All genres
    private val genres: StateFlow<List<Genre>> = getGenreListUseCase(Unit).map {
        it.successOr { listOf() }
    }.stateIn(viewModelScope, Lazily, listOf())

    // Genres that the movie belongs to
    val movieGenres: StateFlow<List<Genre>?> = combine(movieDetail, genres) { movieDetail, genres ->
        val movieGenreIds = movieDetail?.genreIds ?: return@combine null
        val result = mutableListOf<Genre>()
        movieGenreIds.forEach { id ->
            val genre = genres.firstOrNull { genre -> id == genre.id }
            genre?.let { result.add(it) }
        }
        result
    }.stateIn(viewModelScope, Lazily, null)

    // Videos that the movie belongs to
    val videos: StateFlow<List<Video>?> = getMovieVideoUseCase(movieId)
        .map { it.data }
        .stateIn(viewModelScope, Lazily, null)

    private val _videoType = MutableStateFlow("")
    val videoType: StateFlow<String> = _videoType

    // Spinner position
    private val _spinnerPosition = MutableStateFlow(0)
    val spinnerPosition: StateFlow<Int> = _spinnerPosition

    // Determine whenever the movie is in the library
    private val _isMovieInLibrary = MutableStateFlow(false)
    val isMovieInLibrary: StateFlow<Boolean> = _isMovieInLibrary

    init {
        checkLibraryMovie()
    }

    private fun checkLibraryMovie() {
        viewModelScope.launch {
            _isMovieInLibrary.value = getLibraryMovieUseCase(movieId) is Result.Success
        }
    }

    fun setSpinnerPosition(newPosition: Int) {
        val type = videos.value?.getOrNull(newPosition)?.type ?: return
        _videoType.value = type
        _spinnerPosition.value = newPosition
    }

    fun saveDeleteLibraryMovie() {
        viewModelScope.launch {
            if (getLibraryMovieUseCase(movieId) is Result.Success) {
                // Movie is currently in the library. Delete it
                deleteLibraryMovie()
            } else {
                // Movie isn't currently in the library. Save it
                saveLibraryMovie()
            }
        }
    }

    private suspend fun saveLibraryMovie() {
        val currentMovieDetail = movieDetail.value ?: return
        val result = saveLibraryMovieUseCase(currentMovieDetail)
        if (result is Result.Success) {
            _isMovieInLibrary.value = true
        }
    }

    private suspend fun deleteLibraryMovie() {
        val currentMovieDetail = movieDetail.value ?: return
        val result = deleteLibraryMovieUseCase(currentMovieDetail)
        if (result is Result.Success) {
            _isMovieInLibrary.value = false
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(movieId: Int): MovieDetailViewModel
    }

    companion object {
        fun provideFactory(factory: Factory, movieId: Int): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return factory.create(movieId) as T
                }
            }
        }
    }
}
