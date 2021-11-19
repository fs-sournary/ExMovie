/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.itlifelang.extrememovie.shared.data.api.MovieApi
import com.itlifelang.extrememovie.shared.data.api.MovieResponse

class GenreMoviePagingDataSource(
    private val movieApi: MovieApi,
    private val genreId: Int
) : PagingSource<Int, MovieResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse> = try {
        val page = params.key ?: 1
        val response = movieApi.getGenreMovies(page, genreId)
        LoadResult.Page(
            data = response.results ?: emptyList(),
            prevKey = null,
            nextKey = if (page < (response.totalPage ?: 0)) page + 1 else null
        )
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    override fun getRefreshKey(state: PagingState<Int, MovieResponse>): Int? =
        state.anchorPosition?.let {
            val page = state.closestPageToPosition(it)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
}
