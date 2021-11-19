/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.api

import com.google.gson.annotations.SerializedName

data class TelevisionResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("first_air_date")
    val firstAirDate: String? = null,
    @SerializedName("origin_country")
    val originCountries: List<String>? = null,
    @SerializedName("genre_ids")
    val genreIds: List<Int>? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("original_name")
    val originalName: String? = null
)
