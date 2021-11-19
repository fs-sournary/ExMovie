/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.mapper

import com.itlifelang.extrememovie.model.SpokenLanguageModel
import com.itlifelang.extrememovie.shared.data.api.SpokenLanguageResponse

fun SpokenLanguageResponse.mapToModel(): SpokenLanguageModel = SpokenLanguageModel(iso6391, name)
