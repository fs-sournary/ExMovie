/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.model

data class CreditModel(
    val id: Int? = null,
    val cast: List<CastModel>? = null,
    val crew: List<CrewModel>? = null
)
