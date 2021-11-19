/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.api

import com.google.gson.annotations.SerializedName

data class ProductionCountryResponse(
    @SerializedName("iso_3166_1")
    val iso31661: String? = null,
    @SerializedName("name")
    val name: String? = null
)
