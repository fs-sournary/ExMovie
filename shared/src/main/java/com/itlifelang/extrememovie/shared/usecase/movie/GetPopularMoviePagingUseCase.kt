/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.itlifelang.extrememovie.model.MovieModel
import com.itlifelang.extrememovie.shared.data.api.MovieApi
import com.itlifelang.extrememovie.shared.data.paging.CategoryMoviePagingDataSource
import com.itlifelang.extrememovie.shared.mapper.mapToModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPopularMoviePagingUseCase @Inject constructor(private val movieApi: MovieApi) {

    operator fun invoke(): Flow<PagingData<MovieModel>> = Pager(
        config = PagingConfig(20),
        pagingSourceFactory = {
            CategoryMoviePagingDataSource { page -> movieApi.getPopularMovies(page) }
        }
    )
        .flow
        .map { pagingData -> pagingData.map { it.mapToModel() } }
}
