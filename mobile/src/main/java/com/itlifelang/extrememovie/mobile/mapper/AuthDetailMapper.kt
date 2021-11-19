/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.mapper

import com.itlifelang.extrememovie.mobile.data.AuthorDetail
import com.itlifelang.extrememovie.model.AuthorDetailModel

fun AuthorDetailModel.mapToMobile(): AuthorDetail = AuthorDetail(name, username, avatarPath, rating)
