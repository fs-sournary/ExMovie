package com.itlifelang.extrememovie.shared.data.db

import androidx.room.Dao
import com.itlifelang.extrememovie.model.Movie

@Dao
interface MovieDao : BaseDao<Movie>
