/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.mapper

import com.itlifelang.extrememovie.mobile.data.Video
import com.itlifelang.extrememovie.model.VideoModel

fun VideoModel.mapToMobile(): Video = Video(id, key, site, name, size, type)
