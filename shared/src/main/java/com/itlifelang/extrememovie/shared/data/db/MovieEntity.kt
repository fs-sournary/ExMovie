package com.itlifelang.extrememovie.shared.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * The class represents Movie that is stored in Room.
 *
 * The [ColumnInfo] is explicitly declared to allow for renaming properties without changing column
 * names.
 */
@Entity(tableName = "movie")
data class MovieEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "title")
    val title: String
)
