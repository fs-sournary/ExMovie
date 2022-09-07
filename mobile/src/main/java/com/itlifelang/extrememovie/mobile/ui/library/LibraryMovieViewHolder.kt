/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.library

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itlifelang.extrememovie.databinding.ItemLibraryMovieBinding
import com.itlifelang.extrememovie.model.MovieDetail

class LibraryMovieViewHolder(
    private val binding: ItemLibraryMovieBinding,
    private val click: (View, MovieDetail) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movieDetail: MovieDetail) {
        binding.root.setOnClickListener { click(it, movieDetail) }
        binding.apply {
            item = movieDetail
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup, click: (View, MovieDetail) -> Unit): LibraryMovieViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemLibraryMovieBinding.inflate(inflater, parent, false)
            return LibraryMovieViewHolder(binding, click)
        }
    }
}
