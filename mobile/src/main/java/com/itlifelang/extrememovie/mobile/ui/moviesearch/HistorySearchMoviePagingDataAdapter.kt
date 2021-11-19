/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.moviesearch

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.itlifelang.extrememovie.mobile.data.HistorySearch
import com.itlifelang.extrememovie.mobile.data.HistorySearch.SearchMovie
import com.itlifelang.extrememovie.mobile.data.HistorySearch.Separator
import com.itlifelang.extrememovie.mobile.data.Movie

class HistorySearchMoviePagingDataAdapter(private val itemClick: (View, Movie) -> Unit) :
    PagingDataAdapter<HistorySearch, ViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            ITEM_MOVIE_TYPE -> HistorySearchMovieViewHolder.create(parent, itemClick)
            ITEM_SEPARATOR_TYPE -> HistorySearchMovieSeparatorViewHolder.create(parent)
            else -> throw UnsupportedOperationException("Un-support this view holder")
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is SearchMovie -> (holder as? HistorySearchMovieViewHolder)?.bind(item.movie)
            is Separator -> (holder as? HistorySearchMovieSeparatorViewHolder)?.bind(item.time)
        }
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is SearchMovie -> ITEM_MOVIE_TYPE
        is Separator -> ITEM_SEPARATOR_TYPE
        else -> throw UnsupportedOperationException("Un-support item view: ${getItem(position)}")
    }

    companion object {

        private const val ITEM_MOVIE_TYPE = 0
        private const val ITEM_SEPARATOR_TYPE = 1

        private val COMPARATOR = object : DiffUtil.ItemCallback<HistorySearch>() {
            override fun areItemsTheSame(
                oldItem: HistorySearch,
                newItem: HistorySearch
            ): Boolean = (oldItem is SearchMovie && newItem is SearchMovie &&
                oldItem.movie.id == newItem.movie.id) ||
                (oldItem is Separator && newItem is Separator)

            override fun areContentsTheSame(
                oldItem: HistorySearch,
                newItem: HistorySearch
            ): Boolean = oldItem == newItem
        }
    }
}
