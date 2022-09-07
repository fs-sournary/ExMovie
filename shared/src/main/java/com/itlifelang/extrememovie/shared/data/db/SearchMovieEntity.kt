/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.itlifelang.extrememovie.model.Movie

/**
 * The class represents Movie that is stored in Room.
 *
 * The [ColumnInfo] is explicitly declared to allow for renaming properties without changing column
 * names.
 */
@Entity(tableName = "search_movie")
data class SearchMovieEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "imdbId")
    val imdbId: String? = null,
    @ColumnInfo(name = "adult")
    val adult: Boolean? = null,
    @ColumnInfo(name = "backdropPath")
    val backdropPath: String? = null,
    @ColumnInfo(name = "budget")
    val budget: Int? = null,
    @ColumnInfo(name = "genreIds")
    val genreIds: List<Int>? = null,
    @ColumnInfo(name = "homepage")
    val homepage: String? = null,
    @ColumnInfo(name = "originalLanguage")
    val originalLanguage: String? = null,
    @ColumnInfo(name = "originalTitle")
    val originalTitle: String? = null,
    @ColumnInfo(name = "overview")
    val overview: String? = null,
    @ColumnInfo(name = "popularity")
    val popularity: Double? = null,
    @ColumnInfo(name = "posterPath")
    val posterPath: String? = null,
    @ColumnInfo(name = "productionCountries")
    val productionCountries: List<ProductionCountryEntity>? = null,
    @ColumnInfo(name = "releaseDate")
    val releaseDate: String? = null,
    @ColumnInfo(name = "revenue")
    val revenue: Int? = null,
    @ColumnInfo(name = "runtime")
    val runtime: Int? = null,
    @ColumnInfo(name = "spokenLanguages")
    val spokenLanguages: List<SpokenLanguageEntity>? = null,
    @ColumnInfo(name = "status")
    val status: String? = null,
    @ColumnInfo(name = "tagLine")
    val tagLine: String? = null,
    @ColumnInfo(name = "title")
    val title: String? = null,
    @ColumnInfo(name = "video")
    val video: Boolean? = null,
    @ColumnInfo(name = "voteAverage")
    val voteAverage: Double? = null,
    @ColumnInfo(name = "voteCount")
    val voteCount: Int? = null,
    @ColumnInfo(name = "insertTime")
    val insertTime: Long
)

fun SearchMovieEntity.toModel(): Movie {
    return Movie(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun Movie.toEntity(insertTime: Long): SearchMovieEntity {
    return SearchMovieEntity(
        id = id ?: 0,
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        insertTime = insertTime
    )
}
