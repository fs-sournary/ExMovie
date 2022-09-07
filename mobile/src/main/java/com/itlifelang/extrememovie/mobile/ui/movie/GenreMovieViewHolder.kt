/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itlifelang.extrememovie.databinding.ItemGenreMovieBinding
import com.itlifelang.extrememovie.model.Movie

class GenreMovieViewHolder(
    private val binding: ItemGenreMovieBinding,
    private val click: (View, Movie) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(movie: Movie) {
        binding.root.setOnClickListener { click(it, movie) }
        binding.apply {
            item = movie
            transitionName = "genre_movie_${movie.id}"
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup, click: (View, Movie) -> Unit): GenreMovieViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemGenreMovieBinding.inflate(layoutInflater, parent, false)
            return GenreMovieViewHolder(binding, click)
        }
    }
}
