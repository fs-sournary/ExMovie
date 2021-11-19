/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.itlifelang.extrememovie.mobile.data.Genre
import com.itlifelang.extrememovie.mobile.data.Movie
import com.itlifelang.extrememovie.mobile.mapper.mapToMobile
import com.itlifelang.extrememovie.mobile.ui.movie.GenreName
import com.itlifelang.extrememovie.shared.usecase.movie.GetGenreMoviePagingUseCase
import com.itlifelang.extrememovie.shared.usecase.movie.GetNowPlayingMoviePagingUseCase
import com.itlifelang.extrememovie.shared.usecase.movie.GetPopularMoviePagingUseCase
import com.itlifelang.extrememovie.shared.usecase.movie.GetTopRatedMoviePagingUseCase
import com.itlifelang.extrememovie.shared.usecase.movie.GetUpComingMoviePagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class MovieListViewModel @Inject constructor(
    getNowPlayingMoviePagingUseCase: GetNowPlayingMoviePagingUseCase,
    getPopularMoviePagingUseCase: GetPopularMoviePagingUseCase,
    getTopRatedMoviePagingUseCase: GetTopRatedMoviePagingUseCase,
    getUpComingMoviePagingUseCase: GetUpComingMoviePagingUseCase,
    getGenreMoviePagingUseCase: GetGenreMoviePagingUseCase
) : ViewModel() {

    private val _genre = MutableStateFlow<Genre?>(null)
    val genre: StateFlow<Genre?> = _genre
    val movies: StateFlow<Flow<PagingData<Movie>>?> = _genre.map {
        val flow = when (it?.name ?: return@map null) {
            GenreName.NowPLaying.name -> getNowPlayingMoviePagingUseCase()
            GenreName.Popular.name -> getPopularMoviePagingUseCase()
            GenreName.TopRated.name -> getTopRatedMoviePagingUseCase()
            GenreName.UpComing.name -> getUpComingMoviePagingUseCase()
            else -> getGenreMoviePagingUseCase(it.id ?: 0)
        }
        flow.map { pagingData -> pagingData.map { movieModel -> movieModel.mapToMobile() } }
            .cachedIn(viewModelScope)
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    fun setGenre(newGenre: Genre) {
        _genre.value = newGenre
    }
}
