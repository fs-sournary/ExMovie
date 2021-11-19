/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.mapper

import com.itlifelang.extrememovie.mobile.data.Television
import com.itlifelang.extrememovie.model.TelevisionModel

fun TelevisionModel.mapToMobile(): Television = Television(
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
