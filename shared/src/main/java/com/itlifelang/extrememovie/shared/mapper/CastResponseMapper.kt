/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.mapper

import com.itlifelang.extrememovie.model.CastModel
import com.itlifelang.extrememovie.shared.data.api.CastResponse

fun CastResponse.mapToModel(): CastModel = CastModel(
    adult = adult,
    gender = gender,
    id = id,
    knownForDepartment = knownForDepartment,
    name = name,
    originalName = originalName,
    popularity = popularity,
    profilePath = profilePath,
    castId = castId,
    character = character,
    creditId = creditId,
    order = order
)
