/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.movie

import com.itlifelang.extrememovie.model.VideoModel
import com.itlifelang.extrememovie.shared.data.api.MovieApi
import com.itlifelang.extrememovie.shared.mapper.mapToModel
import com.itlifelang.extrememovie.shared.result.Result
import com.itlifelang.extrememovie.shared.usecase.FlowUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMovieVideoUseCase @Inject constructor(private val movieApi: MovieApi) :
    FlowUseCase<Int, List<VideoModel>>() {

    override fun execute(params: Int): Flow<Result<List<VideoModel>>> = flow {
        emit(Result.Loading)
        val videos = movieApi.getVideos(params).results?.map { it.mapToModel() } ?: emptyList()
        emit(Result.Success(videos))
    }
}
