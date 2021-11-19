/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase.walkthorugh

import com.itlifelang.extrememovie.shared.data.preference.AppPreference
import com.itlifelang.extrememovie.shared.result.Result
import com.itlifelang.extrememovie.shared.usecase.FlowUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetLaunchedWalkThroughUseCase @Inject constructor(
    private val appPreference: AppPreference
) : FlowUseCase<Unit, Boolean>() {

    override fun execute(params: Unit): Flow<Result<Boolean>> =
        appPreference.launchWalkThrough.map { Result.Success(it) }
}
