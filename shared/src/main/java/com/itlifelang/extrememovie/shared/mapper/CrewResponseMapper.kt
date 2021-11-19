/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.mapper

import com.itlifelang.extrememovie.model.CrewModel
import com.itlifelang.extrememovie.shared.data.api.CrewResponse

fun CrewResponse.mapToModel(): CrewModel = CrewModel(
    adult = adult,
    gender = gender,
    id = id,
    knownForDepartment = knownForDepartment,
    name = name,
    originalName = originalName,
    popularity = popularity,
    profilePath = profilePath,
    creditId = creditId,
    department = department,
    job = job
)
