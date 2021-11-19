/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.moviedetail

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.itlifelang.extrememovie.mobile.data.Crew

class CrewListAdapter(
    private val click: (View, Crew) -> Unit
) : ListAdapter<Crew, CrewViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewViewHolder =
        CrewViewHolder.create(parent, click)

    override fun onBindViewHolder(holder: CrewViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindData(item)
    }

    companion object {

        private val COMPARATOR = object : DiffUtil.ItemCallback<Crew>() {
            override fun areItemsTheSame(oldItem: Crew, newItem: Crew): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Crew, newItem: Crew): Boolean =
                oldItem.name == newItem.name
        }
    }
}
