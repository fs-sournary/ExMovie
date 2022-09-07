/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.movie

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.itlifelang.extrememovie.mobile.ui.common.MoreMovieViewHolder
import com.itlifelang.extrememovie.model.Movie

class CategoryMovieListAdapter(
    private val prefixTransitionName: String,
    private val itemClick: (View, Movie) -> Unit,
    private val viewMore: (View) -> Unit
) : ListAdapter<Movie, RecyclerView.ViewHolder>(COMPARATOR) {
    var hasExtraRow = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_ITEM -> CategoryMovieViewHolder.create(parent, prefixTransitionName, itemClick)
            else -> MoreMovieViewHolder.create(parent, viewMore)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CategoryMovieViewHolder -> getItem(position)?.let { holder.bindData(it) }
            is MoreMovieViewHolder -> holder.bindData()
        }
    }

    override fun getItemCount(): Int = super.getItemCount() + if (hasExtraRow) 1 else 0

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow && position == itemCount - 1) TYPE_MORE else TYPE_ITEM
    }

    companion object {
        private const val TYPE_MORE = 0
        private const val TYPE_ITEM = 1

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
