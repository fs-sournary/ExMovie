package com.itlifelang.extrememovie.mobile.ui.moviesearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.itlifelang.extrememovie.databinding.ItemMovieSearchLoadStateBinding

class SearchMovieLoadStateViewHolder(
    private val binding: ItemMovieSearchLoadStateBinding,
    private val retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorText.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorText.isVisible = loadState is LoadState.Error
    }

    companion object {

        fun create(parent: ViewGroup, retry: () -> Unit): SearchMovieLoadStateViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemMovieSearchLoadStateBinding.inflate(inflater, parent, false)
            return SearchMovieLoadStateViewHolder(binding, retry)
        }
    }
}