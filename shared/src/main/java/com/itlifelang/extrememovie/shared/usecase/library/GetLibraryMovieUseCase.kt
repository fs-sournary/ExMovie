/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.library

import com.itlifelang.extrememovie.model.MovieDetail
import com.itlifelang.extrememovie.shared.data.db.LibraryMovieDao
import com.itlifelang.extrememovie.shared.data.db.toModel
import com.itlifelang.extrememovie.shared.usecase.CoroutineUseCase
import javax.inject.Inject

class GetLibraryMovieUseCase @Inject constructor(
    private val libraryMovieDao: LibraryMovieDao
) : CoroutineUseCase<Int, MovieDetail>() {
    override suspend fun execute(params: Int): MovieDetail {
        val movieEntity = libraryMovieDao.getLibraryMovieById(params)
        return movieEntity.toModel()
    }
}
