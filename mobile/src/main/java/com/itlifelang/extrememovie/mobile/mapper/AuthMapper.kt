/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.mapper

import com.itlifelang.extrememovie.mobile.data.Author
import com.itlifelang.extrememovie.model.AuthorModel

fun AuthorModel.mapToMobile(): Author = Author(
    author = author,
    authorDetails = authorDetails?.mapToMobile(),
    content = content,
    createdAt = createdAt,
    id = id,
    updatedAt = updatedAt,
    url = url
)
