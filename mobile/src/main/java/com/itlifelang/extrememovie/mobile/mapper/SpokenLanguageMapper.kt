/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.mapper

import com.itlifelang.extrememovie.mobile.data.SpokenLanguage
import com.itlifelang.extrememovie.model.SpokenLanguageModel

fun SpokenLanguageModel.mapToMobile(): SpokenLanguage = SpokenLanguage(iso6391, name)

fun SpokenLanguage.mapToModel(): SpokenLanguageModel = SpokenLanguageModel(iso6391, name)
