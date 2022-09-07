/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.moviesearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itlifelang.extrememovie.databinding.ItemHistorySearchMovieSeparatorBinding

class HistorySearchMovieSeparatorViewHolder(
    private val binding: ItemHistorySearchMovieSeparatorBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(time: String) {
        binding.apply {
            title = time
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup): HistorySearchMovieSeparatorViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemHistorySearchMovieSeparatorBinding.inflate(inflater, parent, false)
            return HistorySearchMovieSeparatorViewHolder(binding)
        }
    }
}
