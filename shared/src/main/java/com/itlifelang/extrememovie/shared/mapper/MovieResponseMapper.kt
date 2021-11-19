/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.mapper

import com.itlifelang.extrememovie.model.MovieModel
import com.itlifelang.extrememovie.shared.data.api.MovieResponse

fun MovieResponse.mapToModel(): MovieModel = MovieModel(
    id = id,
    imdbId = imdbId,
    adult = adult,
    backdropPath = backdropPath,
    budget = budget,
    genreIds = genreIds?.map { it },
    homepage = homepage,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    productionCountries = productionCountries?.map { it.mapToModel() },
    releaseDate = releaseDate,
    revenue = revenue,
    runtime = runtime,
    spokenLanguages = spokenLanguages?.map { it.mapToModel() },
    status = status,
    tagLine = tagLine,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
)
