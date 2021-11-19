/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.mapper

import com.itlifelang.extrememovie.mobile.data.ProductionCountry
import com.itlifelang.extrememovie.model.ProductionCountryModel

fun ProductionCountryModel.mapToMobile(): ProductionCountry =
    ProductionCountry(iso31661, name)

fun ProductionCountry.mapToModel(): ProductionCountryModel =
    ProductionCountryModel(iso31661, name)
