/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.mapper

import com.itlifelang.extrememovie.model.CreditModel
import com.itlifelang.extrememovie.shared.data.api.CreditResponse

fun CreditResponse.mapToModel(): CreditModel = CreditModel(
    id = id,
    cast = cast?.map { it.mapToModel() },
    crew = crew?.map { it.mapToModel() }
)
