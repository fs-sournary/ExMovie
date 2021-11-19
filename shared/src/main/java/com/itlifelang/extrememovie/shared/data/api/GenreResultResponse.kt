/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.api

import com.google.gson.annotations.SerializedName

data class GenreResultResponse(
    @SerializedName("genres")
    val genres: List<GenreResponse>? = null
)
