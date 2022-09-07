/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itlifelang.extrememovie.databinding.ItemMoreMovieBinding

class MoreMovieViewHolder(
    private val binding: ItemMoreMovieBinding,
    private val viewMoreAction: (View) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bindData() {
        binding.moreAction.setOnClickListener { viewMoreAction(it) }
    }

    companion object {
        fun create(parent: ViewGroup, click: (View) -> Unit): MoreMovieViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemMoreMovieBinding.inflate(inflater, parent, false)
            return MoreMovieViewHolder(binding, click)
        }
    }
}
