/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.moviedetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itlifelang.extrememovie.databinding.ItemSimilarMovieBinding
import com.itlifelang.extrememovie.mobile.data.Movie

class SimilarMovieViewHolder(
    private val binding: ItemSimilarMovieBinding,
    private val click: (View, Movie) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(movie: Movie) {
        binding.root.setOnClickListener { click(binding.root, movie) }
        binding.apply {
            item = movie
            executePendingBindings()
        }
    }

    companion object {

        fun create(
            parent: ViewGroup,
            click: (View, Movie) -> Unit
        ): SimilarMovieViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemSimilarMovieBinding.inflate(inflater, parent, false)
            return SimilarMovieViewHolder(binding, click)
        }
    }
}
