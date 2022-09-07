/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.moviedetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itlifelang.extrememovie.databinding.ItemCastBinding
import com.itlifelang.extrememovie.model.Cast

class CastViewHolder(
    private val binding: ItemCastBinding,
    private val click: (View, Cast) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(cast: Cast) {
        binding.root.setOnClickListener { click(it, cast) }
        binding.apply {
            item = cast
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup, click: (View, Cast) -> Unit): CastViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemCastBinding.inflate(inflater, parent, false)
            return CastViewHolder(binding, click)
        }
    }
}
