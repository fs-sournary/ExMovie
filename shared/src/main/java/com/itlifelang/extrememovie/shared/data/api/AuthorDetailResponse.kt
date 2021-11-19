/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.api

import com.google.gson.annotations.SerializedName

data class AuthorDetailResponse(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("username")
    val username: String? = null,
    @SerializedName("avatar_path")
    val avatarPath: String? = null,
    @SerializedName("rating")
    val rating: Int? = null
)
