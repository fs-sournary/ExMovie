package com.itlifelang.extrememovie.shared.usecase

import com.itlifelang.extrememovie.shared.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

/**
 * The base class handles use cases that uses [Flow] api.
 * @param P type of param.
 * @param T type of return result.
 */
abstract class FlowUseCase<P, T> {

    operator fun invoke(params: P): Flow<Result<T>> = execute(params)
        .catch { e -> emit(Result.Error(Exception(e))) }

    abstract fun execute(params: P): Flow<Result<T>>
}
