/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IntsToStringTypeConverter {

    @TypeConverter
    fun convertIntsToString(value: List<Int>?): String? =
        value?.joinToString(",")

    @TypeConverter
    fun convertStringToInts(value: String?): List<Int>? =
        value?.let { text -> text.split(",").map { it.toIntOrNull() ?: 0 } }
}

class ProductionCountriesToStringTypeConverter {

    private val typeToken = object : TypeToken<List<ProductionCountryEntity>>() {}.type
    private val gson = Gson()

    @TypeConverter
    fun convertProductionCountriesToString(value: List<ProductionCountryEntity>?): String? =
        value?.let { gson.toJson(it, typeToken) }

    @TypeConverter
    fun convertStringToProductionCountries(value: String?): List<ProductionCountryEntity>? =
        value?.let { gson.fromJson(it, typeToken) }
}

class SpokenLanguagesToStringTypeConverter {

    private val typeToken = object : TypeToken<List<SpokenLanguageEntity>>() {}.type
    private val gson = Gson()

    @TypeConverter
    fun convertSpokenLanguagesToString(value: List<SpokenLanguageEntity>?): String? =
        value?.let { gson.toJson(value, typeToken) }

    @TypeConverter
    fun convertStringToSpokenLanguages(value: String?): List<SpokenLanguageEntity>? =
        value?.let { gson.fromJson(value, typeToken) }
}
