/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.mobile.ui.movie

sealed class GenreName(val name: String? = null) {

    object NowPLaying : GenreName("Now playing")

    object Popular : GenreName("Popular")

    object TopRated : GenreName("Top rated")

    object UpComing : GenreName("Up coming")

    // Unspecified, flexible because they are fetch from the server
    object Unspecified : GenreName()
}
