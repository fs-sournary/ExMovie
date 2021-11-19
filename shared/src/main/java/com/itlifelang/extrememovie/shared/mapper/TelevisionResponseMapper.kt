/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.mapper

import com.itlifelang.extrememovie.model.TelevisionModel
import com.itlifelang.extrememovie.shared.data.api.TelevisionResponse

fun TelevisionResponse.mapToModel() = TelevisionModel(
    id = id,
    posterPath = posterPath,
    popularity = popularity,
    backdropPath = backdropPath,
    voteAverage = voteAverage,
    overview = overview,
    firstAirDate = firstAirDate,
    originCountries = originCountries,
    genreIds = genreIds,
    originalLanguage = originalLanguage,
    voteCount = voteCount,
    name = name,
    originalName = originalName
)
