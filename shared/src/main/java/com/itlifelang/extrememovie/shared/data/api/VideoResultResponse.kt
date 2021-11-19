/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.api

import com.google.gson.annotations.SerializedName

data class VideoResultResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("results")
    val results: List<VideoResponse>? = null
)
