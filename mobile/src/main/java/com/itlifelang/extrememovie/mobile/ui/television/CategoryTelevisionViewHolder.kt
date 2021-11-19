/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.television

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itlifelang.extrememovie.databinding.ItemCategoryTelevisionBinding
import com.itlifelang.extrememovie.mobile.data.Television

class CategoryTelevisionViewHolder(
    private val binding: ItemCategoryTelevisionBinding,
    private val click: (View, Television) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(television: Television) {
        binding.root.setOnClickListener { click(it, television) }
        binding.apply {
            item = television
            executePendingBindings()
        }
    }

    companion object {

        fun create(
            parent: ViewGroup,
            click: (View, Television) -> Unit
        ): CategoryTelevisionViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemCategoryTelevisionBinding.inflate(inflater, parent, false)
            return CategoryTelevisionViewHolder(binding, click)
        }
    }
}
