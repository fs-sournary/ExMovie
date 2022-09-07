/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.movie

import com.itlifelang.extrememovie.model.Genre
import com.itlifelang.extrememovie.shared.data.api.MovieApi
import com.itlifelang.extrememovie.shared.data.api.toModel
import com.itlifelang.extrememovie.shared.result.Result
import com.itlifelang.extrememovie.shared.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGenreListUseCase @Inject constructor(
    private val movieApi: MovieApi
) : FlowUseCase<Unit, List<Genre>>() {
    override fun execute(params: Unit): Flow<Result<List<Genre>>> {
        return flow {
            val response = movieApi.getMovieGenres()
            val genres = response.genres?.map { it.toModel() } ?: emptyList()
            emit(Result.Success(genres))
        }
    }
}
