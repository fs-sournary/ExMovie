/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.mapper

import com.itlifelang.extrememovie.model.GenreModel
import com.itlifelang.extrememovie.shared.data.api.GenreResponse

fun GenreResponse.mapToModel(): GenreModel = GenreModel(id, name)
