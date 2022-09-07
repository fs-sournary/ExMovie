package com.itlifelang.extrememovie.shared.usecase.movie

import com.itlifelang.extrememovie.model.MovieDetail
import com.itlifelang.extrememovie.shared.data.api.MovieApi
import com.itlifelang.extrememovie.shared.data.api.toModel
import com.itlifelang.extrememovie.shared.result.Result
import com.itlifelang.extrememovie.shared.usecase.FlowUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMovieDetailUseCase @Inject constructor(
    private val movieApi: MovieApi
) : FlowUseCase<Int, MovieDetail>() {
    override fun execute(params: Int): Flow<Result<MovieDetail>> {
        return flow {
            emit(Result.Loading)
            val movieDetail = movieApi.getMovieDetail(params).toModel()
            emit(Result.Success(movieDetail))
        }
    }
}