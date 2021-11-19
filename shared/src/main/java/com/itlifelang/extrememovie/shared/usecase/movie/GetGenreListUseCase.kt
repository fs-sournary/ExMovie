/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.movie

import com.itlifelang.extrememovie.model.GenreModel
import com.itlifelang.extrememovie.shared.data.api.MovieApi
import com.itlifelang.extrememovie.shared.mapper.mapToModel
import com.itlifelang.extrememovie.shared.result.Result
import com.itlifelang.extrememovie.shared.usecase.FlowUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetGenreListUseCase @Inject constructor(private val movieApi: MovieApi) :
    FlowUseCase<Unit, List<GenreModel>>() {

    override fun execute(params: Unit): Flow<Result<List<GenreModel>>> = flow {
        val response = movieApi.getMovieGenres()
        val genres = response.genres?.map { it.mapToModel() } ?: emptyList()
        emit(Result.Success(genres))
    }
}
