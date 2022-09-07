/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.itlifelang.extrememovie.shared.data.api.AuthorResponse
import com.itlifelang.extrememovie.shared.data.api.MovieApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MovieReviewPagingDataSource(
    private val movieApi: MovieApi,
    private val movieId: Int
) : PagingSource<Int, AuthorResponse>() {
    private val _totalResult = MutableStateFlow(0)
    val totalResult: StateFlow<Int> = _totalResult
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AuthorResponse> {
        return try {
            val page = params.key ?: 1
            val response = movieApi.getMovieReviews(movieId, page)
            if (page == 1) {
                _totalResult.value = response.totalResults ?: 0
            }
            LoadResult.Page(
                data = response.results ?: emptyList(),
                prevKey = null,
                nextKey = if (page < (response.totalPage ?: 1)) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AuthorResponse>): Int? {
        return state.anchorPosition?.let {
            val page = state.closestPageToPosition(it)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }
}
