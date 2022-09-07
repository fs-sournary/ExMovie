/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.db

import com.itlifelang.extrememovie.model.ProductionCountry

data class ProductionCountryEntity(
    val iso31661: String? = null,
    val name: String? = null
)

fun ProductionCountryEntity.toModel() = ProductionCountry(iso31661, name)

fun ProductionCountry.toEntity() = ProductionCountryEntity(iso31661, name)
