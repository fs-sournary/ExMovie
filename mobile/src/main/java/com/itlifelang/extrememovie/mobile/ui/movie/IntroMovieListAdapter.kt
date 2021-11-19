/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.movie

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.itlifelang.extrememovie.R

class IntroMovieListAdapter(
    private val firstIntroClick: () -> Unit,
    private val secondIntroClick: () -> Unit
) : ListAdapter<Any, ViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            R.layout.item_first_intro_movie -> FirstIntroMovieViewHolder.create(
                parent,
                firstIntroClick
            )
            else -> SecondIntroMovieViewHolder.create(parent, secondIntroClick)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (position) {
            0 -> (holder as? FirstIntroMovieViewHolder)?.bindData()
            else -> (holder as? SecondIntroMovieViewHolder)?.bindData()
        }
    }

    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> R.layout.item_first_intro_movie
        else -> R.layout.item_second_intro_movie
    }

    companion object {

        private val COMPARATOR = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = false

            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = false
        }
    }
}
