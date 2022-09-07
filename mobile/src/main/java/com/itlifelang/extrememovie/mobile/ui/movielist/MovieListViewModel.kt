/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.itlifelang.extrememovie.mobile.ui.movie.GenreName
import com.itlifelang.extrememovie.model.Movie
import com.itlifelang.extrememovie.shared.usecase.movie.GetGenreMoviePagingUseCase
import com.itlifelang.extrememovie.shared.usecase.movie.GetNowPlayingMoviePagingUseCase
import com.itlifelang.extrememovie.shared.usecase.movie.GetPopularMoviePagingUseCase
import com.itlifelang.extrememovie.shared.usecase.movie.GetTopRatedMoviePagingUseCase
import com.itlifelang.extrememovie.shared.usecase.movie.GetUpComingMoviePagingUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow

class MovieListViewModel @AssistedInject constructor(
    @Assisted val genreName: String,
    @Assisted val genreId: Int,
    getNowPlayingMoviePagingUseCase: GetNowPlayingMoviePagingUseCase,
    getPopularMoviePagingUseCase: GetPopularMoviePagingUseCase,
    getTopRatedMoviePagingUseCase: GetTopRatedMoviePagingUseCase,
    getUpComingMoviePagingUseCase: GetUpComingMoviePagingUseCase,
    getGenreMoviePagingUseCase: GetGenreMoviePagingUseCase
) : ViewModel() {
    val movies: Flow<PagingData<Movie>> = when (genreName) {
        GenreName.NowPLaying.name -> getNowPlayingMoviePagingUseCase()
        GenreName.Popular.name -> getPopularMoviePagingUseCase()
        GenreName.TopRated.name -> getTopRatedMoviePagingUseCase()
        GenreName.UpComing.name -> getUpComingMoviePagingUseCase()
        else -> getGenreMoviePagingUseCase(genreId)
    }.cachedIn(viewModelScope)

    @AssistedFactory
    interface Factory {
        fun create(genreName: String, genreId: Int): MovieListViewModel
    }

    companion object {
        fun provideFactory(
            factory: Factory,
            genreName: String,
            genreId: Int
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return factory.create(genreName, genreId) as T
                }
            }
        }
    }
}
