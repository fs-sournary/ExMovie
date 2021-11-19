/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.mapper

import com.itlifelang.extrememovie.model.VideoModel
import com.itlifelang.extrememovie.shared.data.api.VideoResponse

fun VideoResponse.mapToModel(): VideoModel = VideoModel(id, key, site, name, size, type)
