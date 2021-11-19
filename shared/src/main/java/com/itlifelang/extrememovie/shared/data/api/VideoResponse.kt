/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.api

import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("key")
    val key: String? = null,
    @SerializedName("site")
    val site: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("size")
    val size: Int? = null,
    @SerializedName("type")
    val type: String? = null
)
