package com.itlifelang.extrememovie.ui

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
 * An abstract class uses data binding to inflate and bind data into layout.
 */
abstract class BindingFragment<Binding : ViewDataBinding, FragmentViewModel : ViewModel> :
    Fragment() {

    protected abstract val viewModel: FragmentViewModel

    protected val navController by lazy { findNavController() }
    protected var binding by autoClear<Binding>()

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
