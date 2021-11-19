/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query

@Dao
interface SearchMovieDao : BaseDao<SearchMovieEntity> {

    @Query("SELECT * FROM search_movie ORDER BY insertTime DESC")
    fun getSearchMovies(): PagingSource<Int, SearchMovieEntity>
}
