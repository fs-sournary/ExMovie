/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.television

import com.itlifelang.extrememovie.model.Genre
import com.itlifelang.extrememovie.shared.data.api.TelevisionApi
import com.itlifelang.extrememovie.shared.result.Result
import com.itlifelang.extrememovie.shared.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTelevisionGenresUseCase @Inject constructor(
    private val televisionApi: TelevisionApi
) : FlowUseCase<Unit, List<Genre>>() {
    override fun execute(params: Unit): Flow<Result<List<Genre>>> {
        return flow {
            emit(Result.Loading)
            val response = televisionApi.getTelevisionGenres()
            val result = response.genres?.map { Genre(it.id, it.name) } ?: emptyList()
            emit(Result.Success(result))
        }
    }
}
