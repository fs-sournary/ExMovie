/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.television

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.itlifelang.extrememovie.model.Genre
import kotlinx.coroutines.flow.StateFlow

class TelevisionGenreListAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val selectedGenre: StateFlow<Genre?>,
    private val click: (View, Genre) -> Unit
) : ListAdapter<Genre, TelevisionGenreViewHolder>(COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TelevisionGenreViewHolder {
        return TelevisionGenreViewHolder.create(parent, lifecycleOwner, selectedGenre, click)
    }

    override fun onBindViewHolder(holder: TelevisionGenreViewHolder, position: Int) {
        getItem(position)?.let { holder.bindData(it) }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Genre>() {
            override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}
