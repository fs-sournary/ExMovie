/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.mapper

import com.itlifelang.extrememovie.model.MovieModel
import com.itlifelang.extrememovie.shared.data.db.LibraryMovieEntity

fun LibraryMovieEntity.mapToModel(): MovieModel = MovieModel(
    id = id,
    imdbId = imdbId,
    adult = adult,
    backdropPath = backdropPath,
    budget = budget,
    genreIds = genreIds,
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

fun MovieModel.mapToEntity(): LibraryMovieEntity = LibraryMovieEntity(
    id = id ?: -1,
    imdbId = imdbId,
    adult = adult,
    backdropPath = backdropPath,
    budget = budget,
    genreIds = genreIds,
    homepage = homepage,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    productionCountries = productionCountries?.map { it.mapToEntity() },
    releaseDate = releaseDate,
    revenue = revenue,
    runtime = runtime,
    spokenLanguages = spokenLanguages?.map { it.mapToEntity() },
    status = status,
    tagLine = tagLine,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
)
