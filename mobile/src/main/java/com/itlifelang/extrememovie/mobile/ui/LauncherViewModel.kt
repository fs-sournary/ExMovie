/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itlifelang.extrememovie.shared.result.data
import com.itlifelang.extrememovie.shared.usecase.walkthorugh.GetLaunchedWalkThroughUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted.Companion.Lazily
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlinx.coroutines.delay

@HiltViewModel
class LauncherViewModel @Inject constructor(
    getLaunchedWalkThroughUseCase: GetLaunchedWalkThroughUseCase
) : ViewModel() {
    val launcherDestination: StateFlow<LauncherNavigationDestination?> =
        getLaunchedWalkThroughUseCase(Unit).map {
            if (it.data == true) {
                LauncherNavigationDestination.Main
            } else {
                LauncherNavigationDestination.WalkThrough
            }
        }.stateIn(viewModelScope, Lazily, null)
}

sealed class LauncherNavigationDestination {
    object Main : LauncherNavigationDestination()

    object WalkThrough : LauncherNavigationDestination()
}
