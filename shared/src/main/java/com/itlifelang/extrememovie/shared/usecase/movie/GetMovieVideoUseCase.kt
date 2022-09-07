/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.movie

import com.itlifelang.extrememovie.model.Video
import com.itlifelang.extrememovie.shared.data.api.MovieApi
import com.itlifelang.extrememovie.shared.data.api.toModel
import com.itlifelang.extrememovie.shared.result.Result
import com.itlifelang.extrememovie.shared.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieVideoUseCase @Inject constructor(
    private val movieApi: MovieApi
) : FlowUseCase<Int, List<Video>>() {
    override fun execute(params: Int): Flow<Result<List<Video>>> {
        return flow {
            emit(Result.Loading)
            val videos = movieApi.getVideos(params).results?.map { it.toModel() } ?: emptyList()
            emit(Result.Success(videos))
        }
    }
}
