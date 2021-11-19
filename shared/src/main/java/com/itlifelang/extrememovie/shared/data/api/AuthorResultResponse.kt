/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.api

import com.google.gson.annotations.SerializedName

data class AuthorResultResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<AuthorResponse>? = null,
    @SerializedName("total_pages")
    val totalPage: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
)
