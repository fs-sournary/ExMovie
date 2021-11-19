/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.mapper

import com.itlifelang.extrememovie.model.AuthorModel
import com.itlifelang.extrememovie.shared.data.api.AuthorResponse

fun AuthorResponse.mapToModel() = AuthorModel(
    author = author,
    authorDetails = authorDetails?.mapToModel(),
    content = content,
    createdAt = createdAt,
    id = id,
    updatedAt = updatedAt,
    url = url
)
