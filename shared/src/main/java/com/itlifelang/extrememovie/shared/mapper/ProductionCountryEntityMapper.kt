/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.mapper

import com.itlifelang.extrememovie.model.ProductionCountryModel
import com.itlifelang.extrememovie.shared.data.db.ProductionCountryEntity

fun ProductionCountryEntity.mapToModel(): ProductionCountryModel = ProductionCountryModel(iso31661, name)

fun ProductionCountryModel.mapToEntity(): ProductionCountryEntity =
    ProductionCountryEntity(iso31661, name)
