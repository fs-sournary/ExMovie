/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.moviesearch

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.itlifelang.extrememovie.model.Movie

class HistorySearchMoviePagingDataAdapter(
    private val itemClick: (View, Movie) -> Unit
) : PagingDataAdapter<MovieSearch, ViewHolder>(COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ITEM_MOVIE_TYPE -> HistorySearchMovieViewHolder.create(parent, itemClick)
            ITEM_SEPARATOR_TYPE -> HistorySearchMovieSeparatorViewHolder.create(parent)
            else -> throw UnsupportedOperationException("Un-support this view holder")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is MovieSearch.Ui -> {
                (holder as? HistorySearchMovieViewHolder)?.bind(item.movie)
            }
            is MovieSearch.Separator -> {
                (holder as? HistorySearchMovieSeparatorViewHolder)?.bind(item.time)
            }
            else -> {}
        }
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is MovieSearch.Ui -> ITEM_MOVIE_TYPE
        is MovieSearch.Separator -> ITEM_SEPARATOR_TYPE
        else -> throw UnsupportedOperationException("Un-support item view: ${getItem(position)}")
    }

    companion object {
        private const val ITEM_MOVIE_TYPE = 0
        private const val ITEM_SEPARATOR_TYPE = 1

        private val COMPARATOR = object : DiffUtil.ItemCallback<MovieSearch>() {
            override fun areItemsTheSame(
                oldItem: MovieSearch,
                newItem: MovieSearch
            ): Boolean {
                return (oldItem is MovieSearch.Ui && newItem is MovieSearch.Ui &&
                        oldItem.movie.id == newItem.movie.id) ||
                        (oldItem is MovieSearch.Ui && newItem is MovieSearch.Ui)
            }

            override fun areContentsTheSame(
                oldItem: MovieSearch,
                newItem: MovieSearch
            ): Boolean = oldItem == newItem
        }
    }
}
