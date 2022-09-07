/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.itlifelang.extrememovie.shared.data.api.MovieResponse
import com.itlifelang.extrememovie.shared.data.api.MovieResultResponse

class CategoryMoviePagingDataSource(
    private val apiAction: suspend (Int) -> MovieResultResponse
) : PagingSource<Int, MovieResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse> {
        return try {
            val page = params.key ?: 1
            val response = apiAction(page)
            LoadResult.Page(
                data = response.results ?: emptyList(),
                prevKey = null,
                nextKey = if (page < (response.totalPage ?: 0)) page + 1 else null
            )
        } catch (throwable: Throwable) {
            LoadResult.Error(throwable)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieResponse>): Int? {
        return state.anchorPosition?.let {
            val page = state.closestPageToPosition(it)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }
}
