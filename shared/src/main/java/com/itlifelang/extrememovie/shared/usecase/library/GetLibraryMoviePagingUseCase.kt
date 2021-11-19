/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.library

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.itlifelang.extrememovie.model.MovieModel
import com.itlifelang.extrememovie.shared.data.db.LibraryMovieDao
import com.itlifelang.extrememovie.shared.mapper.mapToModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetLibraryMoviePagingUseCase @Inject constructor(private val libraryMovieDao: LibraryMovieDao) {

    operator fun invoke(): Flow<PagingData<MovieModel>> = Pager(
        config = PagingConfig(30),
        pagingSourceFactory = { libraryMovieDao.getLibraryMovies() }
    ).flow.map { pagingData -> pagingData.map { it.mapToModel() } }
}
