/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.moviereview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itlifelang.extrememovie.databinding.ItemReviewBinding
import com.itlifelang.extrememovie.mobile.data.Author

class ReviewViewHolder(
    private val binding: ItemReviewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(author: Author) {
        binding.apply {
            item = author
            executePendingBindings()
        }
    }

    companion object {

        fun create(parent: ViewGroup): ReviewViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemReviewBinding.inflate(inflater, parent, false)
            return ReviewViewHolder(binding)
        }
    }
}
