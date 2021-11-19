/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.movielist

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.itlifelang.extrememovie.mobile.data.Movie

class MovieListPagingDataAdapter(
    private val itemClick: (View, Movie) -> Unit
) : PagingDataAdapter<Movie, MovieListViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder =
        MovieListViewHolder.create(parent, itemClick)

    companion object {

        private val COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.title == newItem.title && oldItem.backdropPath == newItem.backdropPath
        }
    }
}
