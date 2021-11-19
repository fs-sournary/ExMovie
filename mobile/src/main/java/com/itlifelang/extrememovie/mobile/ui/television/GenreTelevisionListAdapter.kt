/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.television

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.itlifelang.extrememovie.mobile.data.Television

class GenreTelevisionListAdapter(
    private val itemClick: (View, Television) -> Unit
) : ListAdapter<Television, GenreTelevisionViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreTelevisionViewHolder =
        GenreTelevisionViewHolder.create(parent, itemClick)

    override fun onBindViewHolder(holder: GenreTelevisionViewHolder, position: Int) {
        getItem(position)?.let { holder.bindData(it) }
    }

    companion object {

        private val COMPARATOR = object : DiffUtil.ItemCallback<Television>() {
            override fun areItemsTheSame(oldItem: Television, newItem: Television): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Television, newItem: Television): Boolean =
                oldItem.name == newItem.name &&
                    oldItem.posterPath == newItem.posterPath &&
                    oldItem.backdropPath == newItem.backdropPath &&
                    oldItem.voteAverage == newItem.voteAverage &&
                    oldItem.firstAirDate == newItem.firstAirDate
        }
    }
}
