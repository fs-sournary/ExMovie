/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.television

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.itlifelang.extrememovie.mobile.data.Television

class CategoryTelevisionListAdapter(
    private val itemClick: (View, Television) -> Unit
) : ListAdapter<Television, CategoryTelevisionViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryTelevisionViewHolder =
        CategoryTelevisionViewHolder.create(parent, itemClick)

    override fun onBindViewHolder(holder: CategoryTelevisionViewHolder, position: Int) {
        getItem(position)?.let { holder.bindData(it) }
    }

    companion object {

        private val COMPARATOR = object : DiffUtil.ItemCallback<Television>() {
            override fun areItemsTheSame(oldItem: Television, newItem: Television): Boolean =
                newItem.id == oldItem.id

            override fun areContentsTheSame(oldItem: Television, newItem: Television): Boolean =
                newItem.posterPath == oldItem.posterPath &&
                    newItem.backdropPath == oldItem.posterPath &&
                    oldItem.name == newItem.name &&
                    oldItem.voteAverage == newItem.voteAverage
        }
    }
}
