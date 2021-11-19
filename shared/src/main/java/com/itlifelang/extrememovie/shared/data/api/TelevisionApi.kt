/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TelevisionApi {

    @GET("tv/airing_today")
    suspend fun getAiringTodayTelevisions(@Query("page") page: Int): TelevisionResultResponse

    @GET("tv/on_the_air")
    suspend fun getOnTheAirTelevisions(@Query("page") page: Int): TelevisionResultResponse

    @GET("tv/popular")
    suspend fun getPopularTelevisions(@Query("page") page: Int): TelevisionResultResponse

    @GET("tv/top_rated")
    suspend fun getTopRatedTelevisions(@Query("page") page: Int): TelevisionResultResponse

    @GET("discover/tv")
    suspend fun getGenreTelevisions(
        @Query("page") page: Int,
        @Query("with_genres") genreId: Int
    ): TelevisionResultResponse

    @GET("tv/{tv_id}/videos")
    suspend fun getTelevisionVideos(@Path("tv_id") televisionId: Int): VideoResultResponse

    @GET("genre/tv/list")
    suspend fun getTelevisionGenres(): GenreResultResponse

    @GET("tv/{tv_id}")
    suspend fun getTelevisionDetail(@Path("tv_id") televisionId: Int): TelevisionResponse

    @GET("tv/{tv_id}/similar")
    suspend fun getSimilarTelevisions(
        @Path("tv_id") televisionId: Int,
        @Query("page") page: Int
    ): MovieResultResponse

    @GET("tv/{tv_id}/videos")
    suspend fun getVideos(@Path("tv_id") televisionId: Int): VideoResultResponse

    @GET("tv/{tv_id}/credits")
    suspend fun getCredits(@Path("tv_id") televisionId: Int): CreditResponse

    @GET("search/tv")
    suspend fun getSearchTelevisions(
        @Query("query") query: String,
        @Query("page") page: Int
    ): TelevisionResultResponse

    @GET("tv/{tv_id}/reviews")
    suspend fun getTelevisionReviews(
        @Path("tv_id") televisionId: Int,
        @Query("page") page: Int
    ): AuthorResultResponse
}
