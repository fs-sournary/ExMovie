/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.library

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itlifelang.extrememovie.databinding.ItemLibraryMovieBinding
import com.itlifelang.extrememovie.mobile.data.Movie

class LibraryMovieViewHolder(
    private val binding: ItemLibraryMovieBinding,
    private val click: (View, Movie) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.root.setOnClickListener { click(it, movie) }
        binding.apply {
            item = movie
            executePendingBindings()
        }
    }

    companion object {

        fun create(parent: ViewGroup, click: (View, Movie) -> Unit): LibraryMovieViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemLibraryMovieBinding.inflate(inflater, parent, false)
            return LibraryMovieViewHolder(binding, click)
        }
    }
}
