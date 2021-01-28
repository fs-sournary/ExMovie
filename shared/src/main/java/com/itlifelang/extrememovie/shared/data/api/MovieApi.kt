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
    suspend fun getMoviesByGenre(
        @Query("page") page: Int,
        @Query("with_genres") genreId: Int
    ): MovieResultResponse

    @GET("genre/movie/list")
    suspend fun getGenres(): GenreResultResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") id: Int): MovieResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getRelatedMovies(
        @Path("movie_id") id: Int,
        @Query("page") page: Int
    ): MovieResultResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getVideos(@Path("movie_id") id: Int): MovieResultResponse
}
