/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.mapper

import com.itlifelang.extrememovie.model.AuthorDetailModel
import com.itlifelang.extrememovie.shared.data.api.AuthorDetailResponse

fun AuthorDetailResponse.mapToModel(): AuthorDetailModel = AuthorDetailModel(
    name, username, avatarPath, rating
)
