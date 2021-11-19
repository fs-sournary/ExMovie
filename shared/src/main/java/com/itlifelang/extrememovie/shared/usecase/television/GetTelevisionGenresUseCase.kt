/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.television

import com.itlifelang.extrememovie.model.GenreModel
import com.itlifelang.extrememovie.shared.data.api.TelevisionApi
import com.itlifelang.extrememovie.shared.mapper.mapToModel
import com.itlifelang.extrememovie.shared.result.Result
import com.itlifelang.extrememovie.shared.usecase.FlowUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTelevisionGenresUseCase @Inject constructor(
    private val televisionApi: TelevisionApi
) : FlowUseCase<Unit, List<GenreModel>>() {

    override fun execute(params: Unit): Flow<Result<List<GenreModel>>> = flow {
        emit(Result.Loading)
        val response = televisionApi.getTelevisionGenres()
        val result = response.genres?.map { it.mapToModel() } ?: emptyList()
        emit(Result.Success(result))
    }
}
