/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.movie

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.itlifelang.extrememovie.R
import com.itlifelang.extrememovie.mobile.ui.common.MoreMovieViewHolder
import com.itlifelang.extrememovie.model.Movie

class GenreMovieListAdapter(
    private val click: (View, Movie) -> Unit,
    private val viewMoreAction: (View) -> Unit
) : ListAdapter<Movie, ViewHolder>(COMPARATOR) {
    var hasExtraRow = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            R.layout.item_genre_movie -> GenreMovieViewHolder.create(parent, click)
            else -> MoreMovieViewHolder.create(parent, viewMoreAction)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is GenreMovieViewHolder -> getItem(position)?.let { holder.bindData(it) }
            is MoreMovieViewHolder -> holder.bindData()
        }
    }

    override fun getItemCount(): Int = super.getItemCount() + if (hasExtraRow) 1 else 0

    override fun getItemViewType(position: Int): Int {
        return if (position != itemCount - 1) {
            R.layout.item_genre_movie
        } else {
            R.layout.item_more_movie
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.title == newItem.title &&
                        oldItem.backdropPath == newItem.backdropPath
            }
        }
    }
}
