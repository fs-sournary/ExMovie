/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.itlifelang.extrememovie.model.Movie
import com.itlifelang.extrememovie.shared.data.api.MovieApi
import com.itlifelang.extrememovie.shared.data.api.toModel
import com.itlifelang.extrememovie.shared.data.paging.GenreMoviePagingDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetGenreMoviePagingUseCase @Inject constructor(private val movieApi: MovieApi) {
    operator fun invoke(genreId: Int): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(20),
        pagingSourceFactory = { GenreMoviePagingDataSource(movieApi, genreId) }
    )
        .flow
        .map { pagingData ->
            pagingData.map { it.toModel() }
        }
}
