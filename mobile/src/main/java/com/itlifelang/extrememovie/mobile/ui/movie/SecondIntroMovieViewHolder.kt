/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itlifelang.extrememovie.databinding.ItemSecondIntroMovieBinding

class SecondIntroMovieViewHolder(
    private val binding: ItemSecondIntroMovieBinding,
    private val click: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData() {
        binding.root.setOnClickListener { click() }
    }

    companion object {

        fun create(parent: ViewGroup, click: () -> Unit): SecondIntroMovieViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemSecondIntroMovieBinding.inflate(inflater, parent, false)
            return SecondIntroMovieViewHolder(binding, click)
        }
    }
}
