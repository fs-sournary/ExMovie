/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.itlifelang.extrememovie.model.Author
import com.itlifelang.extrememovie.shared.data.api.MovieApi
import com.itlifelang.extrememovie.shared.data.api.toModel
import com.itlifelang.extrememovie.shared.data.paging.MovieReviewPagingDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMovieReviewPagingUseCase @Inject constructor(
    private val movieApi: MovieApi
) {
    operator fun invoke(movieId: Int): Result {
        val source = MovieReviewPagingDataSource(movieApi, movieId)
        val flow: Flow<PagingData<Author>> = Pager(
            config = PagingConfig(30),
            pagingSourceFactory = { source }
        )
            .flow
            .map { authorResponsePagingData ->
                authorResponsePagingData.map { authorResponse -> authorResponse.toModel() }
            }
        return Result(source.totalResult, flow)
    }

    class Result(val totalResult: StateFlow<Int>, val movieReviews: Flow<PagingData<Author>>)
}
