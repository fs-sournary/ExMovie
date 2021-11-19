/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.library

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.itlifelang.extrememovie.mobile.data.Movie

class LibraryMoviePagingDataAdapter(
    private val itemClick: (View, Movie) -> Unit
) : PagingDataAdapter<Movie, LibraryMovieViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: LibraryMovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryMovieViewHolder =
        LibraryMovieViewHolder.create(parent, itemClick)

    companion object {

        private val COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.title == newItem.title && oldItem.backdropPath == newItem.backdropPath
        }
    }
}
