/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.api

import com.google.gson.annotations.SerializedName
import com.itlifelang.extrememovie.model.Credit

data class CreditResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("cast")
    val cast: List<CastResponse>? = null,
    @SerializedName("crew")
    val crew: List<CrewResponse>? = null
)

fun CreditResponse.toModel(): Credit {
    return Credit(
        id = id,
        cast = cast?.map { it.toModel() },
        crew = crew?.map { it.toModel() }
    )
}
