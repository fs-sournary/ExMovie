/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.db

import com.itlifelang.extrememovie.model.SpokenLanguage

data class SpokenLanguageEntity(
    val iso6391: String? = null,
    val name: String? = null
)

fun SpokenLanguageEntity.toModel() = SpokenLanguage(iso6391, name)

fun SpokenLanguage.toEntity() = SpokenLanguageEntity(iso6391, name)
