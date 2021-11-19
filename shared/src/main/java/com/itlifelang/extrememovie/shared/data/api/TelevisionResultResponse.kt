/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.api

import com.google.gson.annotations.SerializedName

data class TelevisionResultResponse(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<TelevisionResponse>? = null,
    @SerializedName("total_results")
    val totalResult: Int? = null,
    @SerializedName("total_pages")
    val totalPage: Int? = null
)
