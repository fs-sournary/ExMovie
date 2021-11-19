/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.television

import com.itlifelang.extrememovie.model.TelevisionModel
import com.itlifelang.extrememovie.shared.data.api.TelevisionApi
import com.itlifelang.extrememovie.shared.mapper.mapToModel
import com.itlifelang.extrememovie.shared.result.Result
import com.itlifelang.extrememovie.shared.usecase.FlowUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFirstPageGenreTelevisionsUseCase @Inject constructor(
    private val televisionApi: TelevisionApi
) : FlowUseCase<Int, List<TelevisionModel>>() {

    override fun execute(params: Int): Flow<Result<List<TelevisionModel>>> = flow {
        emit(Result.Loading)
        val response = televisionApi.getGenreTelevisions(1, params)
        val result = response.results?.map { it.mapToModel() } ?: emptyList()
        emit(Result.Success(result))
    }
}
