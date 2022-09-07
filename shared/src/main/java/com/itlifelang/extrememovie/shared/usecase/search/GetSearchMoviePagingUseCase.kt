/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.itlifelang.extrememovie.model.Movie
import com.itlifelang.extrememovie.shared.data.api.MovieApi
import com.itlifelang.extrememovie.shared.data.api.toModel
import com.itlifelang.extrememovie.shared.data.paging.SearchMoviePagingDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSearchMoviePagingUseCase @Inject constructor(private val movieApi: MovieApi) {

    operator fun invoke(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(30),
            pagingSourceFactory = { SearchMoviePagingDataSource(movieApi, query) }
        )
            .flow
            .map { pagingData ->
                pagingData.map { it.toModel() }
            }
    }
}
