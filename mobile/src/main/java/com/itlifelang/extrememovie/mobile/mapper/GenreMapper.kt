/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.mapper

import com.itlifelang.extrememovie.mobile.data.Genre
import com.itlifelang.extrememovie.model.GenreModel

fun GenreModel.mapToMobile(): Genre = Genre(id, name)
