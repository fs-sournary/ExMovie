/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.moviereview

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.itlifelang.extrememovie.model.Author

class MovieReviewPagingDataAdapter : PagingDataAdapter<Author, ReviewViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {

        private val COMPARATOR = object : DiffUtil.ItemCallback<Author>() {
            override fun areItemsTheSame(oldItem: Author, newItem: Author): Boolean {
                return oldItem.id == newItem.id
            }


            override fun areContentsTheSame(oldItem: Author, newItem: Author): Boolean {
                return oldItem.author == newItem.author &&
                        oldItem.authorDetails?.avatarPath == newItem.authorDetails?.avatarPath &&
                        oldItem.content == newItem.content
            }
        }
    }
}
