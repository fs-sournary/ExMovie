/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.api

import com.google.gson.annotations.SerializedName
import com.itlifelang.extrememovie.model.SpokenLanguage

data class SpokenLanguageResponse(
    @SerializedName("iso_639_1")
    val iso6391: String? = null,
    @SerializedName("name")
    val name: String? = null
)

fun SpokenLanguageResponse.toModel(): SpokenLanguage = SpokenLanguage(iso6391, name)
