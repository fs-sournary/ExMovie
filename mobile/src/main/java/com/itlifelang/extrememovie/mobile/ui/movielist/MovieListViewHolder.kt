/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itlifelang.extrememovie.databinding.ItemMovieListBinding
import com.itlifelang.extrememovie.model.Movie

class MovieListViewHolder(
    private val binding: ItemMovieListBinding,
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
        fun create(parent: ViewGroup, click: (View, Movie) -> Unit): MovieListViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemMovieListBinding.inflate(inflater, parent, false)
            return MovieListViewHolder(binding, click)
        }
    }
}
