/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.itlifelang.extrememovie.model.Movie
import com.itlifelang.extrememovie.shared.data.db.SearchMovieDao
import com.itlifelang.extrememovie.shared.data.db.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSearchDatabaseMoviePagingUseCase @Inject constructor(
    private val searchMovieDao: SearchMovieDao
) {
    operator fun invoke(): Flow<PagingData<Pair<Movie, Long>>> {
        return Pager(
            config = PagingConfig(20),
            pagingSourceFactory = { searchMovieDao.getSearchMovies() }
        )
            .flow
            .map { pagingData ->
                pagingData.map { Pair(it.toModel(), it.insertTime) }
            }
    }
}
