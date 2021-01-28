package com.itlifelang.extrememovie.shared.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MovieEntity::class],
    exportSchema = false,
    version = 1
)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getMovieDao(): MovieDao

    companion object {

        const val DB_NAME = "extreme_movie.db"
    }
}
