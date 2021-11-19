/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase

import com.itlifelang.extrememovie.shared.result.Result

/**
 * The use-case class executes business logic asynchronously using Coroutine.
 *
 * @param P: the type of parameter.
 * @param T: the type of return type.
 */
abstract class CoroutineUseCase<P, T> {

    suspend operator fun invoke(params: P): Result<T> = try {
        val data = execute(params)
        Result.Success(data)
    } catch (e: Exception) {
        Result.Error(e)
    }

    abstract suspend fun execute(params: P): T
}
