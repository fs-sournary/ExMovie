/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.mapper

import com.itlifelang.extrememovie.model.ProductionCountryModel
import com.itlifelang.extrememovie.shared.data.api.ProductionCountryResponse

fun ProductionCountryResponse.mapToModel(): ProductionCountryModel = ProductionCountryModel(iso31661, name)
