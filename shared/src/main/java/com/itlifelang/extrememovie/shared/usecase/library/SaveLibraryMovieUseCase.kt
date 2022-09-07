/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.library

import com.itlifelang.extrememovie.model.MovieDetail
import com.itlifelang.extrememovie.shared.data.db.LibraryMovieDao
import com.itlifelang.extrememovie.shared.data.db.toEntity
import com.itlifelang.extrememovie.shared.usecase.CoroutineUseCase
import javax.inject.Inject

class SaveLibraryMovieUseCase @Inject constructor(
    private val libraryMovieDao: LibraryMovieDao
) : CoroutineUseCase<MovieDetail, Unit>() {
    override suspend fun execute(params: MovieDetail) {
        val libraryMovieEntity = params.toEntity()
        libraryMovieDao.insert(libraryMovieEntity)
    }
}
