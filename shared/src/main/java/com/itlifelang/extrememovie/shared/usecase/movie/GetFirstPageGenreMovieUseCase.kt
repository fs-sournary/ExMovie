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

class GetFirstPageGenreMovieUseCase @Inject constructor(private val movieApi: MovieApi) :
    FlowUseCase<Int, List<Movie>>() {
    override fun execute(params: Int): Flow<Result<List<Movie>>> {
        return flow {
            emit(Result.Loading)
            val responseMovies =
                movieApi.getGenreMovies(DEF_FIRST_PAGE, params).results ?: emptyList()
            val movies = responseMovies.map { it.toModel() }
            emit(Result.Success(movies))
        }
    }

    companion object {
        private const val DEF_FIRST_PAGE = 1
    }
}
