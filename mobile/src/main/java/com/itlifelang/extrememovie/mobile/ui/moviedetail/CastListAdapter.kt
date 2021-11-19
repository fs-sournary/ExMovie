/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.moviedetail

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.itlifelang.extrememovie.mobile.data.Cast

class CastListAdapter(
    private val click: (View, Cast) -> Unit
) : ListAdapter<Cast, CastViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder =
        CastViewHolder.create(parent, click)

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bindData(item)
    }

    companion object {

        private val COMPARATOR = object : DiffUtil.ItemCallback<Cast>() {
            override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean =
                oldItem.name == newItem.name
        }
    }
}
