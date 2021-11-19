/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.movie

import com.itlifelang.extrememovie.model.MovieModel
import com.itlifelang.extrememovie.shared.data.api.MovieApi
import com.itlifelang.extrememovie.shared.mapper.mapToModel
import com.itlifelang.extrememovie.shared.result.Result
import com.itlifelang.extrememovie.shared.usecase.FlowUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFirstPageUpComingMovieUseCase @Inject constructor(private val movieApi: MovieApi) :
    FlowUseCase<Unit, List<MovieModel>>() {

    override fun execute(params: Unit): Flow<Result<List<MovieModel>>> = flow {
        val responseMovies = movieApi.getUpcomingMovies(DEF_FIRST_PAGE).results ?: emptyList()
        val movies = responseMovies.map { it.mapToModel() }
        emit(Result.Success(movies))
    }

    companion object {

        private const val DEF_FIRST_PAGE = 1
    }
}
