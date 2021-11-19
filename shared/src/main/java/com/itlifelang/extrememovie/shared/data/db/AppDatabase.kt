/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [SearchMovieEntity::class, LibraryMovieEntity::class],
    exportSchema = false,
    version = 1
)
@TypeConverters(
    value = [
        IntsToStringTypeConverter::class,
        ProductionCountriesToStringTypeConverter::class,
        SpokenLanguagesToStringTypeConverter::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getSearchMovieDao(): SearchMovieDao

    abstract fun getLibraryMovieDao(): LibraryMovieDao

    companion object {

        const val DB_NAME = "extreme_movie.db"
    }
}
