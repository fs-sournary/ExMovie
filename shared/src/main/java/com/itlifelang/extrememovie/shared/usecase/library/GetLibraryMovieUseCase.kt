/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.library

import com.itlifelang.extrememovie.model.MovieModel
import com.itlifelang.extrememovie.shared.data.db.LibraryMovieDao
import com.itlifelang.extrememovie.shared.mapper.mapToModel
import com.itlifelang.extrememovie.shared.usecase.CoroutineUseCase
import javax.inject.Inject

class GetLibraryMovieUseCase @Inject constructor(
    private val libraryMovieDao: LibraryMovieDao
) : CoroutineUseCase<Int, MovieModel>() {

    override suspend fun execute(params: Int): MovieModel {
        val movieEntity = libraryMovieDao.getLibraryMovieById(params)
        return movieEntity.mapToModel()
    }
}
