/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.library

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.itlifelang.extrememovie.model.Movie
import com.itlifelang.extrememovie.model.MovieDetail
import com.itlifelang.extrememovie.shared.data.db.LibraryMovieDao
import com.itlifelang.extrememovie.shared.data.db.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetLibraryMoviePagingUseCase @Inject constructor(
    private val libraryMovieDao: LibraryMovieDao
) {
    operator fun invoke(): Flow<PagingData<MovieDetail>> {
        return Pager(
            config = PagingConfig(30),
            pagingSourceFactory = { libraryMovieDao.getLibraryMovies() }
        )
            .flow
            .map { pagingData ->
                pagingData.map { it.toModel() }
            }
    }
}
