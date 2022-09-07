/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.movie

import com.itlifelang.extrememovie.model.Credit
import com.itlifelang.extrememovie.shared.data.api.MovieApi
import com.itlifelang.extrememovie.shared.data.api.toModel
import com.itlifelang.extrememovie.shared.result.Result
import com.itlifelang.extrememovie.shared.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieCreditListUseCase @Inject constructor(
    private val movieApi: MovieApi
) : FlowUseCase<Int, Credit>() {
    override fun execute(params: Int): Flow<Result<Credit>> {
        return flow {
            emit(Result.Loading)
            val credit = movieApi.getCredits(params).toModel()
            emit(Result.Success(credit))
        }
    }
}
