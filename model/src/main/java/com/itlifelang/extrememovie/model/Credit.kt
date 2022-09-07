/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.model

data class Credit(
    val id: Int? = null,
    val cast: List<Cast>? = null,
    val crew: List<Crew>? = null
)
