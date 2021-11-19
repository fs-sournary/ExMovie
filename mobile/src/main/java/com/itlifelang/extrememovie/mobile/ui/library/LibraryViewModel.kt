/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.itlifelang.extrememovie.mobile.data.Movie
import com.itlifelang.extrememovie.mobile.mapper.mapToMobile
import com.itlifelang.extrememovie.shared.usecase.library.GetLibraryMoviePagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    getLibraryMoviePagingUseCase: GetLibraryMoviePagingUseCase
) : ViewModel() {

    val libraryMovies: Flow<PagingData<Movie>> = getLibraryMoviePagingUseCase()
        .map {
            it.map { movieModel -> movieModel.mapToMobile() }
        }
        .cachedIn(viewModelScope)
}
