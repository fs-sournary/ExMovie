/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.moviereview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.itlifelang.extrememovie.model.Author
import com.itlifelang.extrememovie.shared.usecase.movie.GetMovieReviewPagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.Lazily
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MovieReviewViewModel @Inject constructor(
    private val getMovieReviewPagingUseCase: GetMovieReviewPagingUseCase
) : ViewModel() {

    private val _movieId = MutableStateFlow<Int?>(null)

    private val movieReviewResult: StateFlow<GetMovieReviewPagingUseCase.Result?> = _movieId.map {
        getMovieReviewPagingUseCase(it ?: return@map null)
    }.stateIn(viewModelScope, Lazily, null)

    val movieReviews: StateFlow<Flow<PagingData<Author>>?> = movieReviewResult.map {
        it?.movieReviews?.cachedIn(viewModelScope)
    }.stateIn(viewModelScope, Lazily, null)

    val reviewCount: StateFlow<Int> = movieReviewResult.flatMapLatest {
        it?.totalResult ?: MutableStateFlow(0)
    }.stateIn(viewModelScope, Lazily, 0)

    fun setMovieId(newMovieId: Int) {
        _movieId.value = newMovieId
    }
}
