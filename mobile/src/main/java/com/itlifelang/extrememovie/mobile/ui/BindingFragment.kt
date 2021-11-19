/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.itlifelang.extrememovie.BR
import com.itlifelang.extrememovie.shared.extension.autoClear

/**
 * An abstract class uses data binding to inflate and binds data into layout.
 *
 * @param B: the type of data binding class that is generated from the layout file.
 * @param VM: the type of [ViewModel] that is internally used for [BindingFragment]
 */
abstract class BindingFragment<B : ViewDataBinding, VM : ViewModel> : Fragment() {

    protected abstract val viewModel: VM

    protected val navController by lazy { findNavController() }
    protected var binding by autoClear<B>()

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            setVariable(BR.viewModel, viewModel)
            lifecycleOwner = viewLifecycleOwner
        }
    }
}
