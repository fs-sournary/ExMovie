package com.itlifelang.extrememovie.ui.profile

import androidx.fragment.app.viewModels
import com.itlifelang.extrememovie.R
import com.itlifelang.extrememovie.databinding.FragmentProfileBinding
import com.itlifelang.extrememovie.ui.BindingFragment

class ProfileFragment : BindingFragment<FragmentProfileBinding, ProfileViewModel>() {

    override val viewModel: ProfileViewModel by viewModels()

    override val layoutId: Int = R.layout.fragment_profile
}
