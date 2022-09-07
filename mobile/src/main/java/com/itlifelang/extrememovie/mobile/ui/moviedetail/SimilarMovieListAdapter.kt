/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.moviedetail

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.itlifelang.extrememovie.model.Movie

class SimilarMovieListAdapter(
    private val itemClick: (View, Movie) -> Unit
) : PagingDataAdapter<Movie, SimilarMovieViewHolder>(COMPARATOR) {
    override fun onBindViewHolder(holder: SimilarMovieViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bindData(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMovieViewHolder {
        return SimilarMovieViewHolder.create(parent, itemClick)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.title == newItem.title && oldItem.backdropPath == newItem.backdropPath
            }
        }
    }
}
