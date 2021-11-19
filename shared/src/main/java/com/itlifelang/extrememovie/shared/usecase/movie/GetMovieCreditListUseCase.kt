/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.movie

import com.itlifelang.extrememovie.model.CreditModel
import com.itlifelang.extrememovie.shared.data.api.MovieApi
import com.itlifelang.extrememovie.shared.mapper.mapToModel
import com.itlifelang.extrememovie.shared.result.Result
import com.itlifelang.extrememovie.shared.usecase.FlowUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMovieCreditListUseCase @Inject constructor(private val movieApi: MovieApi) :
    FlowUseCase<Int, CreditModel>() {

    override fun execute(params: Int): Flow<Result<CreditModel>> = flow {
        emit(Result.Loading)
        val credit = movieApi.getCredits(params).mapToModel()
        emit(Result.Success(credit))
    }
}
