/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.library

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.itlifelang.extrememovie.model.MovieDetail

class LibraryMoviePagingDataAdapter(
    private val itemClick: (View, MovieDetail) -> Unit
) : PagingDataAdapter<MovieDetail, LibraryMovieViewHolder>(COMPARATOR) {
    override fun onBindViewHolder(holder: LibraryMovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryMovieViewHolder {
        return LibraryMovieViewHolder.create(parent, itemClick)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<MovieDetail>() {
            override fun areItemsTheSame(oldItem: MovieDetail, newItem: MovieDetail): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieDetail, newItem: MovieDetail): Boolean {
                return oldItem.title == newItem.title &&
                        oldItem.backdropPath == newItem.backdropPath
            }
        }
    }
}
