/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itlifelang.extrememovie.databinding.ItemFirstIntroMovieBinding

class FirstIntroMovieViewHolder(
    private val binding: ItemFirstIntroMovieBinding,
    private val click: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bindData() {
        binding.root.setOnClickListener { click() }
    }

    companion object {
        fun create(parent: ViewGroup, click: () -> Unit): FirstIntroMovieViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemFirstIntroMovieBinding.inflate(inflater, parent, false)
            return FirstIntroMovieViewHolder(binding, click)
        }
    }
}
