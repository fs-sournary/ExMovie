/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int? = null,
    val imdbId: String? = null,
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val budget: Int? = null,
    val genreIds: List<Int>? = null,
    val homepage: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val productionCountries: List<ProductionCountry>? = null,
    val releaseDate: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    val spokenLanguages: List<SpokenLanguage>? = null,
    val status: String? = null,
    val tagLine: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null
) : Parcelable
