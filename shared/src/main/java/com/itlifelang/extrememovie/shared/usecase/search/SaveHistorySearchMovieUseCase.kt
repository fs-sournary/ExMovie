/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.search

import com.itlifelang.extrememovie.model.Movie
import com.itlifelang.extrememovie.shared.data.db.SearchMovieDao
import com.itlifelang.extrememovie.shared.data.db.toEntity
import com.itlifelang.extrememovie.shared.usecase.CoroutineUseCase
import javax.inject.Inject

/**
 * This use-case save a movie into the database. We also need to save the time that it is saved in
 * order to sort later.
 */
class SaveHistorySearchMovieUseCase @Inject constructor(
    private val searchMovieDao: SearchMovieDao
) : CoroutineUseCase<Pair<Movie, Long>, Unit>() {

    /**
     * @param params A pair of a movie and its saved time
     */
    override suspend fun execute(params: Pair<Movie, Long>) {
        val movieEntity = params.first.toEntity(params.second)
        searchMovieDao.insert(movieEntity)
    }
}
