/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.walkthorugh

import com.itlifelang.extrememovie.shared.data.preference.AppPreference
import com.itlifelang.extrememovie.shared.usecase.CoroutineUseCase
import javax.inject.Inject

class SetLaunchedWalkThroughUseCase @Inject constructor(
    private val appPreference: AppPreference
) : CoroutineUseCase<Boolean, Unit>() {
    override suspend fun execute(params: Boolean) {
        appPreference.saveLaunchWalkThrough(params)
    }
}
