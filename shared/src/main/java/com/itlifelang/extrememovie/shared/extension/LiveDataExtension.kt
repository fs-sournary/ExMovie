package com.itlifelang.extrememovie.shared.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.itlifelang.extrememovie.shared.result.Event

/**
 * Generate a [Result]'s [LiveData] by combining two [LiveData]s via [combiner].
 */
fun <A, B, Result> LiveData<A>.combine(
    other: LiveData<B>,
    combiner: (A, B) -> Result
): LiveData<Result> {
    val result = MediatorLiveData<Result>()
    result.addSource(this) { a ->
        val b = other.value ?: return@addSource
        result.value = combiner(a, b)
    }
    result.addSource(other) { b ->
        val a = this.value ?: return@addSource
        result.value = combiner(a, b)
    }
    return result
}

/**
 * Generate a [Result]'s [LiveData] by combining three [LiveData]s via [combiner].
 */
fun <A, B, C, Result> LiveData<A>.combine(
    other1: LiveData<B>,
    other2: LiveData<C>,
    combiner: (A, B, C) -> Result
): LiveData<Result> {
    val result = MediatorLiveData<Result>()
    result.addSource(this) { a ->
        val b = other1.value ?: return@addSource
        val c = other2.value ?: return@addSource
        result.value = combiner(a, b, c)
    }
    result.addSource(other1) { b ->
        val a = this.value ?: return@addSource
        val c = other2.value ?: return@addSource
        result.value = combiner(a, b, c)
    }
    result.addSource(other2) { c ->
        val a = this.value ?: return@addSource
        val b = other1.value ?: return@addSource
        result.value = combiner(a, b, c)
    }
    return result
}

/**
 * observe [LiveData]s that is used to implement events by [Event]
 */
fun <T> LiveData<Event<T>>.observeEvent(lifecycleOwner: LifecycleOwner, action: (T) -> Unit) {
    this.observe(lifecycleOwner) { event ->
        event.getContentIfNotHandled()?.let { data ->
            action(data)
        }
    }
}
