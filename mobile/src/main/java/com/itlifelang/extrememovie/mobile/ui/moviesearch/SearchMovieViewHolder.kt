/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.moviesearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itlifelang.extrememovie.databinding.ItemSearchMovieBinding
import com.itlifelang.extrememovie.mobile.data.Movie

class SearchMovieViewHolder(
    private val binding: ItemSearchMovieBinding,
    private val click: (View, Movie) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(movie: Movie) {
        binding.root.setOnClickListener { click(it, movie) }
        binding.apply {
            item = movie
            executePendingBindings()
        }
    }

    companion object {

        fun create(parent: ViewGroup, click: (View, Movie) -> Unit): SearchMovieViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemSearchMovieBinding.inflate(inflater, parent, false)
            return SearchMovieViewHolder(binding, click)
        }
    }
}
