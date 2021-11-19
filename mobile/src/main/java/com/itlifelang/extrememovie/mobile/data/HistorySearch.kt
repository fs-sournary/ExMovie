/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.data

sealed class HistorySearch {

    data class SearchMovie(val movie: Movie, val insertTime: String) : HistorySearch()

    data class Separator(val time: String) : HistorySearch()
}
