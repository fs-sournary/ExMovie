/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.mapper

import com.itlifelang.extrememovie.model.SpokenLanguageModel
import com.itlifelang.extrememovie.shared.data.db.SpokenLanguageEntity

fun SpokenLanguageEntity.mapToModel(): SpokenLanguageModel = SpokenLanguageModel(iso6391, name)

fun SpokenLanguageModel.mapToEntity(): SpokenLanguageEntity = SpokenLanguageEntity(iso6391, name)
