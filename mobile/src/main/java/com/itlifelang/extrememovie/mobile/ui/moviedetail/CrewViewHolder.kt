/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.moviedetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itlifelang.extrememovie.databinding.ItemCrewBinding
import com.itlifelang.extrememovie.mobile.data.Crew

class CrewViewHolder(
    private val binding: ItemCrewBinding,
    private val click: (View, Crew) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(crew: Crew) {
        binding.root.setOnClickListener { click(it, crew) }
        binding.apply {
            item = crew
            executePendingBindings()
        }
    }

    companion object {

        fun create(parent: ViewGroup, click: (View, Crew) -> Unit): CrewViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemCrewBinding.inflate(inflater, parent, false)
            return CrewViewHolder(binding, click)
        }
    }
}
