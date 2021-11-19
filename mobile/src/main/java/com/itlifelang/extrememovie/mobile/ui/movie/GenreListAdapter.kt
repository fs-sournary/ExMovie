/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.movie

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.itlifelang.extrememovie.mobile.data.Genre
import kotlinx.coroutines.flow.StateFlow

class GenreListAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val selectedGenre: StateFlow<Genre>,
    private val click: (View, Genre) -> Unit
) : ListAdapter<Genre, GenreViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder =
        GenreViewHolder.create(parent, lifecycleOwner, selectedGenre, click)

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        getItem(position)?.let { holder.bindData(it) }
    }

    companion object {

        private val COMPARATOR = object : DiffUtil.ItemCallback<Genre>() {
            override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean =
                oldItem.name == newItem.name
        }
    }
}
