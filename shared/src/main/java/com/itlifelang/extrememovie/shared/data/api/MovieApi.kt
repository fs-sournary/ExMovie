/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page: Int): MovieResultResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): MovieResultResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page: Int): MovieResultResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int): MovieResultResponse

    @GET("discover/movie")
    suspend fun getGenreMovies(
        @Query("page") page: Int,
        @Query("with_genres") genreId: Int
    ): MovieResultResponse

    @GET("genre/movie/list")
    suspend fun getMovieGenres(): GenreResultResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") id: Int): MovieDetailResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") id: Int,
        @Query("page") page: Int
    ): MovieResultResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getVideos(@Path("movie_id") id: Int): VideoResultResponse

    /**
     * This method gets casts and crews of a movie.
     */
    @GET("movie/{movie_id}/credits")
    suspend fun getCredits(@Path("movie_id") id: Int): CreditResponse

    @GET("search/movie")
    suspend fun getSearchMovies(
        @Query("query") query: String,
        @Query("page") page: Int
    ): MovieResultResponse

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int
    ): AuthorResultResponse
}
