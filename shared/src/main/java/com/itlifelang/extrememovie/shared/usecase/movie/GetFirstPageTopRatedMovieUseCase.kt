/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.movie

import com.itlifelang.extrememovie.model.Movie
import com.itlifelang.extrememovie.shared.data.api.MovieApi
import com.itlifelang.extrememovie.shared.data.api.toModel
import com.itlifelang.extrememovie.shared.result.Result
import com.itlifelang.extrememovie.shared.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFirstPageTopRatedMovieUseCase @Inject constructor(
    private val movieApi: MovieApi
) : FlowUseCase<Unit, List<Movie>>() {
    override fun execute(params: Unit): Flow<Result<List<Movie>>> {
        return flow {
            val responseMovies = movieApi.getTopRatedMovies(DEF_FIRST_PAGE).results ?: emptyList()
            val movies = responseMovies.map { it.toModel() }
            emit(Result.Success(movies))
        }
    }

    companion object {
        private const val DEF_FIRST_PAGE = 1
    }
}
