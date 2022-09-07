/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.api

import com.google.gson.annotations.SerializedName
import com.itlifelang.extrememovie.model.ProductionCountry

data class ProductionCountryResponse(
    @SerializedName("iso_3166_1")
    val iso31661: String? = null,
    @SerializedName("name")
    val name: String? = null
)

fun ProductionCountryResponse.toModel() = ProductionCountry(iso31661, name)
