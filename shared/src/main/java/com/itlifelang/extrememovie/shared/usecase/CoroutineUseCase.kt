package com.itlifelang.extrememovie.shared.usecase

import com.itlifelang.extrememovie.shared.result.Result

abstract class CoroutineUseCase<P, T> {

    suspend operator fun invoke(params: P): Result<T> = try {
        val data = execute(params)
        Result.Success(data)
    } catch (e: Exception) {
        Result.Error(e)
    }

    abstract suspend fun execute(params: P): T
}
