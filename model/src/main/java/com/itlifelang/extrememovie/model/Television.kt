/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.model

data class Television(
    val id: Int? = null,
    val posterPath: String? = null,
    val popularity: Double? = null,
    val backdropPath: String? = null,
    val voteAverage: Double? = null,
    val overview: String? = null,
    val firstAirDate: String? = null,
    val originCountries: List<String>? = null,
    val genreIds: List<Int>? = null,
    val originalLanguage: String? = null,
    val voteCount: Int? = null,
    val name: String? = null,
    val originalName: String? = null
)
