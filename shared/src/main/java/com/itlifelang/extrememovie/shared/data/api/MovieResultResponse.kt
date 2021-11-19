/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.api

import com.google.gson.annotations.SerializedName

data class MovieResultResponse(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<MovieResponse>? = null,
    @SerializedName("total_pages")
    val totalPage: Int? = null,
    @SerializedName("total_results")
    val totalResult: Int? = null
)
