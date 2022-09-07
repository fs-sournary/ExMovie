/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.usecase

import com.itlifelang.extrememovie.shared.result.Result

/**
 * The use-case class executes business logic synchronously.
 *
 * @param P: the type of parameter.
 * @param T: the type of parameter.
 */
abstract class UseCase<P, T> {
    operator fun invoke(params: P): Result<T> = try {
        val data = execute(params)
        Result.Success(data)
    } catch (e: Exception) {
        Result.Error(e)
    }

    abstract fun execute(params: P): T
}
