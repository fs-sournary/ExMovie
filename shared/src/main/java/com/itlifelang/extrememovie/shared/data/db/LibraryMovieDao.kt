/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query

@Dao
interface LibraryMovieDao : BaseDao<LibraryMovieEntity> {
    @Query("SELECT * FROM library_movie")
    fun getLibraryMovies(): PagingSource<Int, LibraryMovieEntity>

    @Query("SELECT * FROM library_movie WHERE id = :movieId")
    suspend fun getLibraryMovieById(movieId: Int): LibraryMovieEntity
}
