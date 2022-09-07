/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.itlifelang.extrememovie.model.MovieDetail
import com.itlifelang.extrememovie.shared.usecase.library.GetLibraryMoviePagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class LibraryViewModel @Inject constructor(
    getLibraryMoviePagingUseCase: GetLibraryMoviePagingUseCase
) : ViewModel() {
    val libraryMovies: Flow<PagingData<MovieDetail>> =
        getLibraryMoviePagingUseCase().cachedIn(viewModelScope)
}
