/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.library

import com.itlifelang.extrememovie.model.MovieModel
import com.itlifelang.extrememovie.shared.data.db.LibraryMovieDao
import com.itlifelang.extrememovie.shared.mapper.mapToEntity
import com.itlifelang.extrememovie.shared.usecase.CoroutineUseCase
import javax.inject.Inject

class SaveLibraryMovieUseCase @Inject constructor(
    private val libraryMovieDao: LibraryMovieDao
) : CoroutineUseCase<MovieModel, Unit>() {

    override suspend fun execute(params: MovieModel) {
        val libraryMovieEntity = params.mapToEntity()
        libraryMovieDao.insert(libraryMovieEntity)
    }
}
