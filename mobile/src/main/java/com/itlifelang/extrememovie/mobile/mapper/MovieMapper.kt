/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.mapper

import com.itlifelang.extrememovie.mobile.data.Movie
import com.itlifelang.extrememovie.model.MovieModel

fun MovieModel.mapToMobile(): Movie = Movie(
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
    productionCountries = productionCountries?.map { it.mapToMobile() },
    releaseDate = releaseDate,
    revenue = revenue,
    runtime = runtime,
    spokenLanguages = spokenLanguages?.map { it.mapToMobile() },
    status = status,
    tagLine = tagLine,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
)

fun Movie.mapToModel(): MovieModel = MovieModel(
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
