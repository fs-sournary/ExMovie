/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.television

import com.itlifelang.extrememovie.model.Television
import com.itlifelang.extrememovie.shared.data.api.TelevisionApi
import com.itlifelang.extrememovie.shared.data.api.toModel
import com.itlifelang.extrememovie.shared.result.Result
import com.itlifelang.extrememovie.shared.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFirstPagePopularTelevisionUseCase @Inject constructor(
    private val televisionApi: TelevisionApi
) : FlowUseCase<Unit, List<Television>>() {
    override fun execute(params: Unit): Flow<Result<List<Television>>> {
        return flow {
            emit(Result.Loading)
            val response = televisionApi.getPopularTelevisions(1)
            val result = response.results?.map { it.toModel() } ?: emptyList()
            emit(Result.Success(result))
        }
    }
}
