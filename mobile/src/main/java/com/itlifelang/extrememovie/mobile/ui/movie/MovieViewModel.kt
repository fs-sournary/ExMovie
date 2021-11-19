/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itlifelang.extrememovie.mobile.data.Genre
import com.itlifelang.extrememovie.mobile.data.Movie
import com.itlifelang.extrememovie.mobile.mapper.mapToMobile
import com.itlifelang.extrememovie.model.MovieModel
import com.itlifelang.extrememovie.shared.result.Result
import com.itlifelang.extrememovie.shared.result.data
import com.itlifelang.extrememovie.shared.usecase.movie.GetFirstPageGenreMovieUseCase
import com.itlifelang.extrememovie.shared.usecase.movie.GetFirstPageNowPlayingMovieUseCase
import com.itlifelang.extrememovie.shared.usecase.movie.GetFirstPagePopularMovieUseCase
import com.itlifelang.extrememovie.shared.usecase.movie.GetFirstPageTopRatedMovieUseCase
import com.itlifelang.extrememovie.shared.usecase.movie.GetFirstPageUpComingMovieUseCase
import com.itlifelang.extrememovie.shared.usecase.movie.GetGenreListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.Lazily
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class MovieViewModel @Inject constructor(
    getFirstPageNowPlayingMovieUseCase: GetFirstPageNowPlayingMovieUseCase,
    getFirstPageTopRatedMovieUseCase: GetFirstPageTopRatedMovieUseCase,
    getFirstPagePopularMovieUseCase: GetFirstPagePopularMovieUseCase,
    getFirstPageUpComingMovieUseCase: GetFirstPageUpComingMovieUseCase,
    getGenreListUseCase: GetGenreListUseCase,
    getFirstPageGenreMovieUseCase: GetFirstPageGenreMovieUseCase
) : ViewModel() {

    private var shouldScrollGenreMovie: Boolean = false

    // Determine that the user device has network or not.
    // If it doesn't network, we won't start fetch data from server.
    private val _hasConnection = MutableStateFlow(false)
    val hasConnection: StateFlow<Boolean> = _hasConnection

    val nowPlayingMovies: StateFlow<List<Movie>?> = _hasConnection.flatMapLatest { hasConnection ->
        if (hasConnection) {
            getFirstPageNowPlayingMovieUseCase(Unit)
                .map {
                    it.data?.map { movie -> movie.mapToMobile() }
                }
        } else {
            flowOf(null)
        }
    }.stateIn(viewModelScope, Lazily, null)

    val topRatedMovies: StateFlow<List<Movie>?> = _hasConnection.flatMapLatest { hasConnection ->
        if (hasConnection) {
            getFirstPageTopRatedMovieUseCase(Unit)
                .map {
                    it.data?.map { movie -> movie.mapToMobile() }
                }
        } else {
            flowOf(null)
        }
    }.stateIn(viewModelScope, Lazily, null)

    val popularMovies: StateFlow<List<Movie>?> = _hasConnection.flatMapLatest { hasConnection ->
        if (hasConnection) {
            getFirstPagePopularMovieUseCase(Unit)
                .map {
                    it.data?.map { movie -> movie.mapToMobile() }
                }
        } else {
            flowOf(null)
        }
    }.stateIn(viewModelScope, Lazily, null)

    val upComingMovies: StateFlow<List<Movie>?> = _hasConnection.flatMapLatest { hasConnection ->
        if (hasConnection) {
            getFirstPageUpComingMovieUseCase(Unit)
                .map {
                    it.data?.map { movie -> movie.mapToMobile() }
                }
        } else {
            flowOf(null)
        }
    }.stateIn(viewModelScope, Lazily, null)

    // Row 2: Genre and its movies
    val genres: StateFlow<List<Genre>?> = _hasConnection.flatMapLatest { hasConnection ->
        if (hasConnection) {
            getGenreListUseCase(Unit)
                .map {
                    val genres = it.data?.map { genre -> genre.mapToMobile() }
                    setGenre(genres?.getOrNull(0))
                    genres
                }
        } else {
            flowOf(null)
        }
    }.stateIn(viewModelScope, Lazily, null)

    private val _genre = MutableStateFlow<Genre?>(null)
    val genre: StateFlow<Genre?> = _genre
    private val genreMovieResult: StateFlow<Result<List<MovieModel>>> = _genre.flatMapLatest {
        val id = it?.id ?: return@flatMapLatest flowOf(Result.Error(Exception("Genre id is empty")))
        getFirstPageGenreMovieUseCase(id)
    }.stateIn(viewModelScope, Lazily, Result.Loading)
    val genreMovies: StateFlow<List<Movie>?> = genreMovieResult.mapLatest { movieResult ->
        movieResult.data?.map { movie -> movie.mapToMobile() }
    }.stateIn(viewModelScope, Lazily, null)

    fun setGenre(newGenre: Genre?) {
        shouldScrollGenreMovie = true
        _genre.value = newGenre
    }

    fun setConnection(value: Boolean) {
        _hasConnection.value = value
    }

    fun scrollGenreMovie(scrollAction: () -> Unit) {
        if (shouldScrollGenreMovie) {
            scrollAction()
            shouldScrollGenreMovie = false
        }
    }
}
