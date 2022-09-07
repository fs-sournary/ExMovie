/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itlifelang.extrememovie.databinding.ItemCategoryMovieBinding
import com.itlifelang.extrememovie.model.Movie

class CategoryMovieViewHolder(
    private val binding: ItemCategoryMovieBinding,
    private val prefixTransitionName: String,
    private val click: (View, Movie) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(movie: Movie) {
        binding.root.setOnClickListener { click(binding.root, movie) }
        binding.apply {
            item = movie
            transitionName = "$prefixTransitionName${movie.id}"
            executePendingBindings()
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            prefixTransitionName: String,
            click: (View, Movie) -> Unit
        ): CategoryMovieViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemCategoryMovieBinding.inflate(inflater, parent, false)
            return CategoryMovieViewHolder(binding, prefixTransitionName, click)
        }
    }
}
