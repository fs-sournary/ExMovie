/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.itlifelang.extrememovie.databinding.ItemGenreBinding
import com.itlifelang.extrememovie.mobile.data.Genre
import kotlinx.coroutines.flow.StateFlow

class GenreViewHolder(
    private val binding: ItemGenreBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val selectedGenre: StateFlow<Genre>,
    private val click: (View, Genre) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(genre: Genre) {
        binding.root.setOnClickListener { click(it, genre) }
        binding.apply {
            item = genre
            selectedItem = selectedGenre
            lifecycleOwner = this@GenreViewHolder.lifecycleOwner
            executePendingBindings()
        }
    }

    companion object {

        fun create(
            parent: ViewGroup,
            lifecycleOwner: LifecycleOwner,
            selectedGenre: StateFlow<Genre>,
            click: (View, Genre) -> Unit
        ): GenreViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemGenreBinding.inflate(inflater, parent, false)
            return GenreViewHolder(binding, lifecycleOwner, selectedGenre, click)
        }
    }
}
