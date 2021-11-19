/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.itlifelang.extrememovie.model.MovieModel
import com.itlifelang.extrememovie.shared.data.api.MovieApi
import com.itlifelang.extrememovie.shared.data.paging.SearchMoviePagingDataSource
import com.itlifelang.extrememovie.shared.mapper.mapToModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSearchMoviePagingUseCase @Inject constructor(private val movieApi: MovieApi) {

    operator fun invoke(query: String): Flow<PagingData<MovieModel>> = Pager(
        config = PagingConfig(30),
        pagingSourceFactory = { SearchMoviePagingDataSource(movieApi, query) }
    )
        .flow
        .map { pagingData ->
            pagingData.map { it.mapToModel() }
        }
}