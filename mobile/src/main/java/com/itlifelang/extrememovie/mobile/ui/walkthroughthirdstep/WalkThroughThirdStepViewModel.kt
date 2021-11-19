/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.walkthroughthirdstep

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itlifelang.extrememovie.shared.usecase.walkthorugh.SetLaunchedWalkThroughUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class WalkThroughThirdStepViewModel @Inject constructor(
    private val setLaunchedWalkThroughUseCase: SetLaunchedWalkThroughUseCase
) : ViewModel() {

    fun launchWalkThrough() {
        viewModelScope.launch {
            setLaunchedWalkThroughUseCase(true)
        }
    }
}
