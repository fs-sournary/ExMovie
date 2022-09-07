/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.extension

/**
 * Coerce the receiving Float between [inputMin] and [inputMax]
 */
fun Float.normalize(inputMin: Float, inputMax: Float, outputMin: Float, outputMax: Float): Float {
    return when {
        this < inputMin -> outputMin
        this > inputMax -> outputMax
        else -> outputMin * (1 - (this - inputMin) / (inputMax - inputMin)) +
                outputMax * ((this - inputMin) / (inputMax - inputMin))
    }
}
