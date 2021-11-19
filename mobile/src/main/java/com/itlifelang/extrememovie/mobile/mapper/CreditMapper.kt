/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.mapper

import com.itlifelang.extrememovie.mobile.data.Credit
import com.itlifelang.extrememovie.model.CreditModel

fun CreditModel.mapToMobile(): Credit = Credit(
    id = id,
    cast = cast?.map { it.mapToMobile() },
    crew = crew?.map { it.mapToMobile() }
)
